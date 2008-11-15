package webservices;

import integracion.ImplementacionMandarSolEnv;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import struts.model.BusinessDelegate;
import varios.Constantes;
import varios.XMLConverter;
import vo.ArticuloAEnviarVO;
import vo.ArticuloAFabricarVO;
import vo.ArticuloPedidoVO;
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
			int id = bd.getNextIdARep();
			SolicitudDeReposicionVO solrep = XMLConverter.getSolRepVOFromString(in0, id);
			solrep.setProcesada(false);
			solrep.setId(bd.getNextId());
			solrep.setCdVO(bd.getCentro());
			solrep.setFabrica(bd.getFabricas().get(0));
			solrep.setIdRep(bd.getNexIdSolRep());
			// Guardar la solicitud de reposicion
			bd.guardarSolicitudReposicion(solrep);
			return true;
		} catch (ErrorConectionException e) {
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
				logger.debug("Se esta procesando una Solicitud Distribucion");
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
				ArrayList<Long> verCod = bd.existenArts(codigos);
				if (!verCod.isEmpty()) {
					String codsfalse = "Cod. " + verCod.get(0);
					for (int q = 1; q < verCod.size(); q++) {
						codsfalse = codsfalse + " Cod. " + verCod.get(q);
					}
					logger.debug("La solicitud contiene articulos que no existen en el Centro de Distribucion");
					return false;
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
				logger.debug("Solicitud de Distribucion guardada en el Centro de Distribucion");
				return true;
			} else {
				logger.debug("Error al leer la solicitud de distribucion");
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
		int idArtFab = bd.getNextIdAFab();
		for(int i=0 ; i < artsPed.size() ; i++){
			int cantEnv = artsPed.get(i).getCantidadEnviada();
			int cantPed = artsPed.get(i).getCantidadPedida();
			if(cantEnv < cantPed){
				artAFab = new ArticuloAFabricarVO();
				artAFab.setArt(bd.getArticulo(artsPed.get(i).getArt().getCodigo()));
				artAFab.setCantidadAFabricar(0);
				artAFab.setCantidadPedida(cantPed);
				artAFab.setCantidadRecibida(0);
				artAFab.setCantMinAPedir((cantPed-cantEnv)*2);
				artAFab.setIdAAF(idArtFab++);
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
			String xmlSolEnv = XMLConverter.getStringFromSolEnv(solEnv);
			ImplementacionMandarSolEnv envSolEnv = new ImplementacionMandarSolEnv();
			envSolEnv.enviarSolEnv(xmlSolEnv, Constantes.IP_TIENDA1);
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
			String xmlSolEnv = XMLConverter.getStringFromSolEnv(solEnv);
			ImplementacionMandarSolEnv envSolEnv = new ImplementacionMandarSolEnv();
			envSolEnv.enviarSolEnv(xmlSolEnv, Constantes.IP_TIENDA2);
		}
	}
}
