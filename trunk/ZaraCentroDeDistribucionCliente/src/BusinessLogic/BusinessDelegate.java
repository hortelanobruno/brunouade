package BusinessLogic;

import MVCFramework.ProxyModelo;
import VO.SolicitudDistribucionVO;
import VO.SolicitudFabricaVO;
import VO.SolicitudEnvioVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import Varios.Constantes;
//import java.util.Hashtable;
import java.util.Vector;

//import javax.ejb.Remote;
import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;

public class BusinessDelegate extends ProxyModelo 
{
	private ServerFacade modCD = null;
	//private InitialContext initialContect = null;
	private String naming = Constantes.BEAN_STRING;

	public BusinessDelegate()
	{
		super();
		//this.inicializarContexto();
		this.getConnection();
	}

	// Test de nacho
	public int getTestNumber()
	{
		return this.getModCD().getTest();
	}

	/**
	 * Se indica url del servidor de aplicaciones
	 * 
	 */
    protected void getConnection() {
        try {
        	Context jndiContext = getInitialContext();
			this.modCD = (ServerFacade)jndiContext.lookup(naming);

        } catch (Exception e) {
        	e.printStackTrace(); 
        }
    }    
	 
    private static Context getInitialContext() throws javax.naming.NamingException {
        return new javax.naming.InitialContext();
    }
	
	/*@SuppressWarnings("unchecked")
	private void inicializarContexto() {
		try {
			Hashtable props = new Hashtable();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			// Url completa de ubicacion del servidor de aplicaciones
			props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
			// Objeto del tipo InitialContext
			initialContect = new InitialContext(props);
			System.out.println("paso ic");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}*/



	public Vector<String> getDescripciones(Vector<Long> codigos) {
		Vector<String> descripciones = new Vector<String>();
		try {
			descripciones = getModCD().getDescripciones(codigos);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return descripciones;
	}

	public Vector<Integer> getStocks(Vector<Long> codigos) {
		Vector<Integer> stocks = new Vector<Integer>();
		try {
			stocks = getModCD().getStocks(codigos);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return stocks;
	}

	public void guardarAritucloHogar(ArticuloHogarVO a){
		/*Hashtable props = new Hashtable();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		props.put(InitialContext.PROVIDER_URL,"jnp://127.0.0.1:1099");
		InitialContext context = null;
		try {
			context = new InitialContext(props);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServerFacade administradorProductos = null;
		try {
			administradorProductos = (ServerFacade) context.lookup("ServerApp/ServerFacadeBean/remote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("voy a guardar el articulo hogar en el Business Delegate");
		this.getModCD().guardarArticuloHogar(a);
	}
	
	public void guardarAritucloRopa(ArticuloRopaVO a){
		this.getModCD().guardarArticuloRopa(a);
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
