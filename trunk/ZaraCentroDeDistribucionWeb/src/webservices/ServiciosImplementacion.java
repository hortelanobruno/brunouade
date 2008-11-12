package webservices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import struts.model.BusinessDelegate;
import varios.XMLConverter;
import vo.ArticuloAFabricarVO;
import vo.ArticuloHeaderVO;
import vo.ArticuloPedidoVO;
import vo.ArticuloReservadoVO;
import vo.CentroDistribucionVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudDistribucionVO;
import exceptions.ErrorConectionException;

public class ServiciosImplementacion {
	
	private BusinessDelegate bd;
	private Logger logger = Logger.getLogger("zara.centro");
	
	public ServiciosImplementacion() {
		
	}
	
	
	public boolean recibirSolRep(String in0){
		try {
			bd = new BusinessDelegate();
			//Parsea la solicitud de reposicion
			SolicitudDeReposicionVO solrep = XMLConverter.getSolRepVOFromString(in0, bd.getNextIdARep());
			solrep.setProcesada(false);
			solrep.setCdVO(bd.getCentro());
			solrep.setFabrica(bd.getFabricas().get(0));
			solrep.setIdRep(bd.getNexIdSolRep());
			//Guardar la solicitud de reposicion
			bd.guardarSolicitudReposicion(solrep);
			return true;
		} catch (ErrorConectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean recibirSolDis(String in0){
		try {
			bd = new BusinessDelegate();
			SolicitudDistribucionVO soldis = XMLConverter.getSolDisVOFromString(in0);
			if(soldis != null){
				soldis.setIdDis(bd.getNextIdSolDis());
				
				ArrayList<Long> codigos = new ArrayList<Long>();
				int idMax = bd.getNextIdArtPed();
				Iterator arts = (Iterator) soldis.getArticulosPedidos().iterator();
				ArrayList<ArticuloPedidoVO> artsVO = new ArrayList<ArticuloPedidoVO>();
				while (arts.hasNext()) {
					ArticuloPedidoVO artVO = ((ArticuloPedidoVO) arts.next());
					artVO.setIdAP(idMax);
					idMax++;
					codigos.add(artVO.getArt().getCodigo());
					artsVO.add(artVO);
				}
				ArrayList<Long>  verCod = bd.existenArts(codigos);
				if(!verCod.isEmpty()){
					String codsfalse = "Cod. "+verCod.get(0);
					for(int q = 1 ; q < verCod.size() ; q++){
						codsfalse = codsfalse + " Cod. "+verCod.get(q);
					}
					logger.debug(": La solicitud contiene articulos que no existen en el Centro de Distribucion");
				}else{
					soldis.setArticulosPedidos(artsVO);
					int id = bd.getNextId();
					soldis.setId(id);
					CentroDistribucionVO centroVO = bd.getCentro();
					soldis.setCdVO(centroVO);
					soldis.setCerrada(false);
				}
				Collection<ArticuloAFabricarVO> artiAFab = (Collection<ArticuloAFabricarVO>) articulosFabricarDeTabla(soldis);
				Collection<ArticuloReservadoVO> artiReser = (Collection<ArticuloReservadoVO>) articulosEnviarDeTabla(soldis);
				bd.guardarSolicitud(soldis);
				bd.guardarArticulosReservados(artiReser);
				bd.guardarArticulosAFabricar(artiAFab);
				bd.modificarStock(artiReser);
				logger.debug("Solicitudes de Distribucion guardada en el Centro de Distribucion");
				return true;
			}else{
				return false;
			}
		} catch (ErrorConectionException e) {
			e.printStackTrace();
		}
    	return false;
	}
	
	private Collection<ArticuloAFabricarVO> articulosFabricarDeTabla(SolicitudDistribucionVO soldis) {
		Collection<ArticuloAFabricarVO> art = new ArrayList<ArticuloAFabricarVO>();
		ArticuloHeaderVO arti;
		int idMax = bd.getNextIdAFab();
		List<ArticuloPedidoVO> arts = (List<ArticuloPedidoVO>) soldis.getArticulosPedidos();
		for (int i = 0; i < arts.size(); i++) {
			ArticuloPedidoVO artVO = arts.get(i);
			long cod = artVO.getArt().getCodigo();
			int stock = bd.getStockArticulo(cod);
			int ped = artVO.getCantidad();
			if (ped > stock) {
				arti = bd.getArticulo(cod);
				ArticuloAFabricarVO aFab = new ArticuloAFabricarVO();
				aFab.setArt(arti);
				aFab.setCantidadPedida(ped);
				aFab.setCantidadRecibida(0);
				aFab.setCantidadAFabricar(0);
				aFab.setIdAAF(idMax);
				idMax++;
				aFab.setSol(soldis);
				art.add(aFab);
			}
		}
		return art;
	}
    
    private Collection<ArticuloReservadoVO> articulosEnviarDeTabla(SolicitudDistribucionVO soldis) {
		Collection<ArticuloReservadoVO> art = new ArrayList<ArticuloReservadoVO>();
		ArticuloHeaderVO arti;
		int idMax = bd.getNextIdARes();
		List<ArticuloPedidoVO> arts = (List<ArticuloPedidoVO>) soldis.getArticulosPedidos();
		for (int i = 0; i < arts.size(); i++) {
			ArticuloPedidoVO artVO = arts.get(i);
			long cod = artVO.getArt().getCodigo();
			int cantPed = artVO.getCantidad(); 
			int stock = bd.getStockArticulo(cod);
			if(stock > cantPed){
				arti = bd.getArticulo(cod);
				ArticuloReservadoVO aRes = new ArticuloReservadoVO();
				aRes.setIdAR(idMax);
				idMax++;
				aRes.setArt(arti);
				aRes.setCantidadReservada(cantPed);
				aRes.setSolDis(soldis);
				art.add(aRes);
			}
		}
		return art;
    }
}
