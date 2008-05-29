package BusinessLogic;

import RemoteMVCFramework.ProxyModelo;
import VO.SolicitudDistribucionVO;
import VO.SolicitudFabricaVO;
import VO.SolicitudEnvioVO;
import Varios.Constantes;
import java.util.Hashtable;
import java.util.Vector;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BusinessDelegate extends ProxyModelo {
	private ServerFacade modCD = null;

	private InitialContext initialContect = null;

	private String naming = Constantes.BEAN_STRING;

	// private Conexion con; //Conexion SQL Temporal

	public BusinessDelegate() {
		super();
		this.inicializarContexto();
	}

	// Test de nacho
	public int getTestNumber() {
		return this.getModCD().getTest();
	}

	/**
	 * Se indica url del servidor de aplicaciones
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void inicializarContexto() {
		try {
			Hashtable props = new Hashtable();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			// Url completa de ubicacion del servidor de aplicaciones
			props.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
			// Objeto del tipo InitialContext
			initialContect = new InitialContext(props);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Obtiene una "referencia" al session bean administrador de productos
	 * 
	 * @return el AdministradorProductos
	 */
	private ServerFacade getServerFacade() {
		try {
			if (this.modCD == null) {
				// Obtencion de referencia del session bean dentro del servidor
				// de aplicaciones
				this.modCD = (ServerFacade) this.initialContect.lookup(naming);
			}
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
		return this.modCD;
	}

	public Vector<String> getDescripciones(Vector<Long> codigos) {
		Vector<String> descripciones = new Vector<String>();
		try {
			descripciones = getServerFacade().getDescripciones(codigos);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return descripciones;
	}

	public Vector<Integer> getStocks(Vector<Long> codigos) {
		Vector<Integer> stocks = new Vector<Integer>();
		try {
			stocks = getServerFacade().getStocks(codigos);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return stocks;
	}

	public void guardarSolicitud(SolicitudDistribucionVO soldis) {
		this.getModCD().guardarSolicitud(soldis);
	}

	public void guardarSolicitudEnvios(SolicitudEnvioVO solEnv) {
		this.getModCD().guardarSolicitudDeEnvio(solEnv);
	}

	public void guardarSolicitudFabrica(SolicitudFabricaVO solFab) {
		this.getModCD().guardarSolicitudFabricacion(solFab);
	}

	public ServerFacade getModCD() {
		return modCD;
	}
}
