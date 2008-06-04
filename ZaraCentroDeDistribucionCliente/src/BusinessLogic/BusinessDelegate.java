package BusinessLogic;

import MVCFramework.ProxyModelo;
import VO.ArticuloHeaderVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudFabricaVO;
import VO.SolicitudEnvioVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import Varios.Constantes;
import java.util.Vector;
import javax.naming.Context;

public class BusinessDelegate extends ProxyModelo 
{
	private ServerFacade modCD = null;
	private String naming = Constantes.BEAN_STRING;

	public BusinessDelegate()
	{
		super();
		this.getConnection();
		this.guardarDatosCD();
	}
	
	private void guardarDatosCD()
	{
		Vector<String> lineasRopa = new Vector<String>();
		lineasRopa.add("woman");
		lineasRopa.add("basic");
		lineasRopa.add("sport");
		lineasRopa.add("vestir");
		
		Vector<String> categoriasHogar = new Vector<String>();
		categoriasHogar.add("baño");
		categoriasHogar.add("mesa");
		categoriasHogar.add("decoracion");
		categoriasHogar.add("baño");
		
		this.getModCD().guardarDatosCD(1, "Shopping Centro", 56.887521f, 34.90417f, lineasRopa, categoriasHogar);
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

	public void guardarAritucloHogar(ArticuloHogarVO a)
	{
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

	public SolicitudFabricaVO getSolicitudFabricacion(long codigoSolFab) {
		return this.getModCD().cargarSolicitudFabricacion(codigoSolFab);
	}

	public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab) {
		this.getModCD().actualizarSolFab(solFab);
	}

	public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO) {
		this.getModCD().guardarSolicitudReposicion(solRepVO);
	}

	public void actualizarStock(Vector<ArticuloHeaderVO> articulo) {
		this.getModCD().actualizarStock((Vector<ArticuloHeaderVO>) articulo);
	}
}
