package webservices;

import integracion.ImplementacionMandarSolEnv;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import businesslogic.ServerFacade;

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

	private Logger logger = Logger.getLogger("zara.centro");
	private ServerFacade modCD = null;
	private String naming = Constantes.BEAN_STRING;

	private ServerFacade getModCD() {
		return modCD;
	}

	@SuppressWarnings("unchecked")
	protected void getConnection() throws ErrorConectionException {
		try {
			Hashtable props = new Hashtable();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL, "jnp://"
					+ Constantes.IP_CENTRODISTRIBUCION + ":1099");
			InitialContext context = new InitialContext(props);
			this.modCD = (ServerFacade) context.lookup(naming);
		} catch (Exception e) {
			throw new ErrorConectionException("No se pudo conectar");
		}
	}

	@SuppressWarnings("unused")
	private static Context getInitialContext()
			throws javax.naming.NamingException {
		return new javax.naming.InitialContext();
	}

	public ServiciosImplementacion() {
		try {
			this.getConnection();
		} catch (ErrorConectionException e) {
			e.printStackTrace();
		}
	}

	public boolean recibirSolRep(String in0) {
		try{
			// Parsea la solicitud de reposicion
			int id = this.getModCD().getNextIdArticuloAReponer();
			SolicitudDeReposicionVO solrep = XMLConverter
					.getSolRepVOFromString(in0, id);
			logger.debug("LLego una solicitud de reposicion");
			solrep.setProcesada(false);
			solrep.setId(this.getModCD().getNextId());
			solrep.setCdVO(this.getModCD().getCentro());
			solrep.setFabrica(this.getModCD().getFabricas().get(0));
			solrep.setIdRep(this.getModCD().getNexIdSolRep());
			// Guardar la solicitud de reposicion
			this.getModCD().guardarSolicitudReposicion(solrep);
			logger.debug("La solicitud des reposicion se guardo exitosamente.");
			return true;
		}catch(Exception e){
			logger.debug("Llego mal la solicitud de reposicion.");
			return false;
		}	
	}

	public boolean recibirSolDis(String in0) {
		System.out.println("Esta llegando");
		SolicitudDistribucionVO soldis = XMLConverter.getSolDisVOFromString(in0);// Setea la fecha y los
		// articulos
		if (soldis != null) {
			logger.debug("Se esta procesando una Solicitud Distribucion");
			soldis.setIdDis(this.getModCD().getNextIdSolDis());
			ArrayList<Long> codigos = new ArrayList<Long>();
			int idMax = this.getModCD().getNextIdArticuloPedido();
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
			ArrayList<Long> verCod = this.getModCD().existenArts(codigos);
			if (!verCod.isEmpty()) {
				String codsfalse = "Cod. " + verCod.get(0);
				for (int q = 1; q < verCod.size(); q++) {
					codsfalse = codsfalse + " Cod. " + verCod.get(q);
				}
				logger.debug("La solicitud contiene articulos que no existen en el Centro de Distribucion");
				return false;
			} else {
				soldis.setArticulosPedidos(artsVO);
				int id = this.getModCD().getNextId();
				soldis.setId(id);
				CentroDistribucionVO centroVO = this.getModCD().getCentro();
				soldis.setCdVO(centroVO);
				soldis.setCerrada(false);
			}
			// Hay que generar lo que puedo y lo que no puedo lo mando a
			// fabricar
			//this.enviarArticulosDisponibles(soldis);
			if (soldis.getCerrada() == false) {
				// genero articulos a fabricar
				List<ArticuloAFabricarVO> artiAFab = generarArticulosAFabricar(soldis);
				this.getModCD().guardarSolicitudDistribucion(soldis);
				this.getModCD().guardarArticulosAFabricar(artiAFab);
				this.getModCD().actualizarSolDis(soldis);
			}else{
				this.getModCD().guardarSolicitudDistribucion(soldis);
			}
			logger.debug("Solicitud de Distribucion guardada en el Centro de Distribucion");
			return true;
		} else {
			logger.debug("Error al leer la solicitud de distribucion");
			return false;
		}
	}

	private List<ArticuloAFabricarVO> generarArticulosAFabricar(SolicitudDistribucionVO soldis) {
		List<ArticuloAFabricarVO> artsAFab = new ArrayList<ArticuloAFabricarVO>();
		ArticuloAFabricarVO artAFab = null;
		List<ArticuloPedidoVO> artsPed = new ArrayList<ArticuloPedidoVO>(soldis
				.getArticulosPedidos());
		int idArtFab = this.getModCD().getNextIdArticuloAFabricar();
		HashMap<Long,Integer> stocks = this.getModCD().getStocks();
		for (int i = 0; i < artsPed.size(); i++) {
			int cantEnv = artsPed.get(i).getCantidadEnviada();
			int cantPed = artsPed.get(i).getCantidadPedida();
			long cod = artsPed.get(i).getArt().getCodigo();
			int stock = stocks.get(cod);
			if(stock <cantPed){
				if (cantEnv < cantPed) {
					artAFab = new ArticuloAFabricarVO();
					artAFab.setArt(this.getModCD().getArticulo(artsPed.get(i).getArt().getCodigo()));
					artAFab.setCantidadAFabricar(0);
					artAFab.setCantidadPedida(cantPed);
					artAFab.setCantidadRecibida(0);
					artAFab.setCantMinAPedir((cantPed-stock) * 2);
					artAFab.setIdAAF(idArtFab++);
					artAFab.setSol(soldis);
					artsAFab.add(artAFab);
				}
			}else{
				List<SolicitudDistribucionVO> sols =this.getModCD().obtenerSolDisAbiertas();
				int cantaux = 0;
				for(int j=0 ; j < sols.size() ; j++){
					Iterator<ArticuloPedidoVO> it = sols.get(j).getArticulosPedidos().iterator();
					while(it.hasNext()){
						ArticuloPedidoVO avo = it.next();
						if(avo.getArt().getCodigo()==cod){
							cantaux += (avo.getCantidadPedida()-avo.getCantidadEnviada());
						}
					}
				}
				if(stock < cantaux+cantPed){
					artAFab = new ArticuloAFabricarVO();
					artAFab.setArt(this.getModCD().getArticulo(artsPed.get(i).getArt().getCodigo()));
					artAFab.setCantidadAFabricar(0);
					artAFab.setCantidadPedida(cantPed);
					artAFab.setCantidadRecibida(0);
					artAFab.setCantMinAPedir(((cantaux+cantPed)-(stock)) * 2);
					artAFab.setIdAAF(idArtFab++);
					artAFab.setSol(soldis);
					artsAFab.add(artAFab);
				}
			}
		}
		return artsAFab;
	}

	public void enviarArticulosDisponibles(SolicitudDistribucionVO soldis) {
		int codTienda1 = this.getModCD().getTiendas().get(0).getCodigoTienda();
		Collection<ArticuloAEnviarVO> artsAEnvTienda1 = new ArrayList<ArticuloAEnviarVO>();
		Collection<ArticuloAEnviarVO> artsAEnvTienda2 = new ArrayList<ArticuloAEnviarVO>();
		HashMap<Long, Integer> stocks = this.getModCD().getStocks();
		List<ArticuloPedidoVO> artsPed = new ArrayList<ArticuloPedidoVO>(soldis.getArticulosPedidos());
		ArticuloAEnviarVO artAEnv = null;
		int idAAE = this.getModCD().getNextIdArticuloAEnviar();
		for (int i = 0; i < artsPed.size(); i++) {
			int cantPedida = artsPed.get(i).getCantidadPedida();
			int cantEnviada = artsPed.get(i).getCantidadEnviada();
			long cod = artsPed.get(i).getArt().getCodigo();
			int stock = stocks.get(cod);
			if (stock > 0) {
				if (stock >= (cantPedida-cantEnviada)) {
					// se puede mandar todo el articulo
					artsPed.get(i).setCantidadEnviada(cantPedida);
					if (artsPed.get(i).getTienda().getCodigoTienda() == codTienda1) {
						artAEnv = new ArticuloAEnviarVO();
						artAEnv.setCantidadAEnviar(cantPedida-cantEnviada);
						artAEnv.setIdPedido(soldis.getIdPedido());
						artAEnv.setArt(this.getModCD().getArticulo(cod));
						artAEnv.setIdAAE(idAAE);
						artsAEnvTienda1.add(artAEnv);
					} else {
						artAEnv = new ArticuloAEnviarVO();
						artAEnv.setCantidadAEnviar(cantPedida-cantEnviada);
						artAEnv.setIdPedido(soldis.getIdPedido());
						artAEnv.setArt(this.getModCD().getArticulo(cod));
						artAEnv.setIdAAE(idAAE);
						artsAEnvTienda2.add(artAEnv);
					}
					stocks.put(cod, stocks.get(cod) - (cantPedida-cantEnviada));
				} else {
					artsPed.get(i).setCantidadEnviada(stock);
					if (artsPed.get(i).getTienda().getCodigoTienda() == codTienda1) {
						artAEnv = new ArticuloAEnviarVO();
						artAEnv.setCantidadAEnviar(stock);
						artAEnv.setIdPedido(soldis.getIdPedido());
						artAEnv.setArt(this.getModCD().getArticulo(cod));
						artAEnv.setIdAAE(idAAE);
						artsAEnvTienda1.add(artAEnv);
					} else {
						artAEnv = new ArticuloAEnviarVO();
						artAEnv.setCantidadAEnviar(stock);
						artAEnv.setIdPedido(soldis.getIdPedido());
						artAEnv.setArt(this.getModCD().getArticulo(cod));
						artAEnv.setIdAAE(idAAE);
						artsAEnvTienda2.add(artAEnv);
					}
					stocks.put(cod, stocks.get(cod) - stock);
				}
				idAAE++;
			}
		}
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
		int aux2 = 0;
		if (!artsAEnvTienda1.isEmpty()) {
			// Genero envio a la tienda 1
			logger.debug("Se va a generar automaticamente una solicitud de envio hacia la tienda");
			SolicitudEnvioVO solEnv = new SolicitudEnvioVO();
			solEnv.setArticulosAEnviar(artsAEnvTienda1);
			solEnv.setCdVO(this.getModCD().getCentro());
			solEnv.setFechaEmision(new Date());
			solEnv.setId(this.getModCD().getNextId());
			solEnv.setIdEnv(this.getModCD().getNextIdSolEnv());
			solEnv.setSolDis(soldis);
			solEnv.setTienda(this.getModCD().getTiendas().get(0));
			String xmlSolEnv = XMLConverter.getStringFromSolEnv(solEnv);
			ImplementacionMandarSolEnv envSolEnv = new ImplementacionMandarSolEnv();
			Constantes.IP_TINEDADINAMICA = Constantes.IP_TIENDA1;
			boolean b = envSolEnv.enviarSolEnv(xmlSolEnv);
			if (b) {
				logger.debug("Se envio la solicitud de envio correctamente a la Tienda "+Constantes.TIENDA1CODIGO+" (devolvio true)");
				this.getModCD().guardarSolEnv(solEnv);
				// Actualizo stocks
				this.getModCD().actualizarStock(stocks);
				this.getModCD().actualizarSolDis(soldis);
				aux2++;
			} else {
				logger.debug("Error al enviar la solicitud de envio a la Tienda "+Constantes.TIENDA1CODIGO+" (devolvio false)");
			}
		}
		if (!artsAEnvTienda2.isEmpty()) {
			// Genero envio a la tienda 2
			logger.debug("Se va a generar automaticamente una solicitud de envio hacia la tienda");
			SolicitudEnvioVO solEnv = new SolicitudEnvioVO();
			solEnv.setArticulosAEnviar(artsAEnvTienda2);
			solEnv.setCdVO(this.getModCD().getCentro());
			solEnv.setFechaEmision(new Date());
			solEnv.setId(this.getModCD().getNextId());
			solEnv.setIdEnv(this.getModCD().getNextIdSolEnv());
			solEnv.setSolDis(soldis);
			solEnv.setTienda(this.getModCD().getTiendas().get(1));
			String xmlSolEnv = XMLConverter.getStringFromSolEnv(solEnv);
			ImplementacionMandarSolEnv envSolEnv = new ImplementacionMandarSolEnv();
			Constantes.IP_TINEDADINAMICA = Constantes.IP_TIENDA2;
			boolean b = envSolEnv.enviarSolEnv(xmlSolEnv);
			if (b) {
				logger.debug("Se envio la solicitud de envio correctamente a la Tienda "+Constantes.TIENDA2CODIGO+" (devolvio true)");
				this.getModCD().guardarSolEnv(solEnv);
				if(aux==0){
					this.getModCD().actualizarStock(stocks);
					this.getModCD().actualizarSolDis(soldis);
				}
			} else {
				logger.debug("Error al enviar la solicitud de envio a la Tienda "+Constantes.TIENDA2CODIGO+" (devolvio false)");
			}
		}
		
	}
	
}
