package webservices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import struts.model.BusinessDelegate;
import varios.XMLConverter;
import vo.ArticuloAEnviarVO;
import vo.ArticuloAFabricarVO;
import vo.ArticuloHeaderVO;
import vo.ArticuloPedidoVO;
import vo.ArticuloReservadoVO;
import vo.CentroDistribucionVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudDistribucionVO;
import vo.SolicitudEnvioVO;
import exceptions.ErrorConectionException;

public class ServiciosImplementacion {

	private BusinessDelegate bd;
	private Logger logger = Logger.getLogger("zara.centro");

	public ServiciosImplementacion() {

	}

	public boolean recibirSolRep(String in0) {
		try {
			bd = new BusinessDelegate();
			// Parsea la solicitud de reposicion
			SolicitudDeReposicionVO solrep = XMLConverter
					.getSolRepVOFromString(in0, bd.getNextIdARep());
			solrep.setProcesada(false);
			solrep.setCdVO(bd.getCentro());
			solrep.setFabrica(bd.getFabricas().get(0));
			solrep.setIdRep(bd.getNexIdSolRep());
			// Guardar la solicitud de reposicion
			bd.guardarSolicitudReposicion(solrep);
			return true;
		} catch (ErrorConectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean recibirSolDis(String in0) {
		try {
			bd = new BusinessDelegate();
			SolicitudDistribucionVO soldis = XMLConverter
					.getSolDisVOFromString(in0);// Setea la fecha y los
												// articulos
			if (soldis != null) {
				soldis.setIdDis(bd.getNextIdSolDis());
				ArrayList<Long> codigos = new ArrayList<Long>();
				int idMax = bd.getNextIdArtPed();
				Iterator arts = (Iterator) soldis.getArticulosPedidos()
						.iterator();
				ArrayList<ArticuloPedidoVO> artsVO = new ArrayList<ArticuloPedidoVO>();
				while (arts.hasNext()) {
					ArticuloPedidoVO artVO = ((ArticuloPedidoVO) arts.next());
					artVO.setIdAP(idMax);
					idMax++;
					codigos.add(artVO.getArt().getCodigo());
					artsVO.add(artVO);
				}
				ArrayList<Long> verCod = bd.existenArts(codigos);
				if (!verCod.isEmpty()) {
					String codsfalse = "Cod. " + verCod.get(0);
					for (int q = 1; q < verCod.size(); q++) {
						codsfalse = codsfalse + " Cod. " + verCod.get(q);
					}
					logger
							.debug("La solicitud contiene articulos que no existen en el Centro de Distribucion");
				} else {
					soldis.setArticulosPedidos(artsVO);
					int id = bd.getNextId();
					soldis.setId(id);
					CentroDistribucionVO centroVO = bd.getCentro();
					soldis.setCdVO(centroVO);
					soldis.setCerrada(false);
				}
				// Hay que generar lo que puedo y lo que no puedo lo mando a
				// fabricar
				enviarArticulosDisponibles(soldis);
				bd.guardarSolicitud(soldis);
				if(soldis.getCerrada() == false){
					//genero articulos a fabricar
					List<ArticuloAFabricarVO> artiAFab = generarArticulosAFabricar(soldis);
					bd.guardarArticulosAFabricar(artiAFab);
				}
				logger.debug("Solicitudes de Distribucion guardada en el Centro de Distribucion");
				return true;
			} else {
				return false;
			}
		} catch (ErrorConectionException e) {
			e.printStackTrace();
		}
		return false;
	}

	private List<ArticuloAFabricarVO> generarArticulosAFabricar(SolicitudDistribucionVO soldis) {
		List<ArticuloAFabricarVO> artsAFab = new ArrayList<ArticuloAFabricarVO>();
		ArticuloAFabricarVO artAFab = null;
		List<ArticuloPedidoVO> artsPed = new ArrayList<ArticuloPedidoVO>(soldis.getArticulosPedidos());
		for(int i=0 ; i < artsPed.size() ; i++){
			int cantEnv = artsPed.get(i).getCantidadEnviada();
			int cantPed = artsPed.get(i).getCantidadPedida();
			if(cantEnv < cantPed){
				artAFab = new ArticuloAFabricarVO();
				artAFab.setArt(bd.getArticulo(artsPed.get(i).getArt().getCodigo()));
				artAFab.setCantidadAFabricar((cantPed-cantEnv)*2);
				artAFab.setCantidadPedida(cantPed);
				artAFab.setCantidadRecibida(0);
				artAFab.setCantMinAPedir((cantPed-cantEnv)*2);
				artAFab.setIdAAF(bd.getNextIdAFab());
				artAFab.setSol(soldis);
				artsAFab.add(artAFab);
			}
		}
		return artsAFab;
	}

	public void enviarArticulosDisponibles(SolicitudDistribucionVO soldis) {
		int codTienda1 = bd.obtenerTiendas().get(0).getCodigoTienda();
		Collection<ArticuloAEnviarVO> artsAEnvTienda1 = new ArrayList<ArticuloAEnviarVO>();
		Collection<ArticuloAEnviarVO> artsAEnvTienda2 = new ArrayList<ArticuloAEnviarVO>();
		HashMap<Long, Integer> stocks = bd.getStocks();
		List<ArticuloPedidoVO> artsPed = new ArrayList<ArticuloPedidoVO>(soldis
				.getArticulosPedidos());
		ArticuloAEnviarVO artAEnv = null;
		for (int i = 0; i < artsPed.size(); i++) {
			int cantPedida = artsPed.get(i).getCantidadPedida();
			long cod = artsPed.get(i).getArt().getCodigo();
			int stock = stocks.get(cod);
			if (stock > 0) {
				if (stock >= cantPedida) {
					// se puede mandar todo el articulo
					artsPed.get(i).setCantidadEnviada(cantPedida);
					if (artsPed.get(i).getTienda().getCodigoTienda() == codTienda1) {
						artAEnv = new ArticuloAEnviarVO();
						artAEnv.setCantidadAEnviar(cantPedida);
						artAEnv.setArt(bd.getArticulo(cod));
						artsAEnvTienda1.add(artAEnv);
					} else {
						artAEnv = new ArticuloAEnviarVO();
						artAEnv.setCantidadAEnviar(cantPedida);
						artAEnv.setArt(bd.getArticulo(cod));
						artsAEnvTienda2.add(artAEnv);
					}
					stocks.put(cod, stocks.get(cod) - cantPedida);
				} else {
					artsPed.get(i).setCantidadEnviada(stock);
					if (artsPed.get(i).getTienda().getCodigoTienda() == codTienda1) {
						artAEnv = new ArticuloAEnviarVO();
						artAEnv.setCantidadAEnviar(stock);
						artAEnv.setArt(bd.getArticulo(cod));
						artsAEnvTienda1.add(artAEnv);
					} else {
						artAEnv = new ArticuloAEnviarVO();
						artAEnv.setCantidadAEnviar(stock);
						artAEnv.setArt(bd.getArticulo(cod));
						artsAEnvTienda2.add(artAEnv);
					}
					stocks.put(cod, stocks.get(cod) - stock);
				}
			}
		}
		//Actualizo stocks
		bd.actualizarStock(stocks);
		soldis.setArticulosPedidos(artsPed);
		int aux = 0;
		for (int i = 0; i < artsPed.size(); i++) {
			if (artsPed.get(i).getCantidadEnviada() != artsPed.get(i)
					.getCantidadPedida()) {
				aux++;
				break;
			}
		}
		if (aux == 0) {
			// Seteo soldis en cerrada
			soldis.setCerrada(true);
		} else {
			soldis.setCerrada(false);
		}
		// Genero soldis a las tiendas
		if (!artsAEnvTienda1.isEmpty()) {
			//Genero envio a la tienda 1
			SolicitudEnvioVO solEnv = new SolicitudEnvioVO();
			solEnv.setArticulosAEnviar(artsAEnvTienda1);
			solEnv.setCdVO(bd.getCentro());
			solEnv.setFechaEmision(new Date());
			solEnv.setId(bd.getNextId());
			solEnv.setIdEnv(bd.getNextIdSolDis());
			solEnv.setSolDis(soldis);
			solEnv.setTienda(bd.obtenerTiendas().get(0));
			bd.guardarSolicitudDeEnvio(solEnv);
			//TODO ACA HAY QUE LLAMAR AL WEBSERVICE Y ENVIARLO A LA TIENDA
		}
		if (!artsAEnvTienda1.isEmpty()) {
			//Genero envio a la tienda 2
			SolicitudEnvioVO solEnv = new SolicitudEnvioVO();
			solEnv.setArticulosAEnviar(artsAEnvTienda2);
			solEnv.setCdVO(bd.getCentro());
			solEnv.setFechaEmision(new Date());
			solEnv.setId(bd.getNextId());
			solEnv.setIdEnv(bd.getNextIdSolDis());
			solEnv.setSolDis(soldis);
			solEnv.setTienda(bd.obtenerTiendas().get(1));
			bd.guardarSolicitudDeEnvio(solEnv);
			//TODO ACA HAY QUE LLAMAR AL WEBSERVICE Y ENVIARLO A LA TIENDA
		}
	}
}
