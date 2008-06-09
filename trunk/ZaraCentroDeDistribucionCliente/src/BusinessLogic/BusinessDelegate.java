package BusinessLogic;

import Exceptions.ErrorConectionException;
import Exceptions.ExistingProductException;
import MVCFramework.ProxyModelo;
import VO.ArticuloHeaderVO;
import VO.FabricaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudFabricaVO;
import VO.SolicitudEnvioVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import Varios.Constantes;

import java.util.ArrayList;
import java.util.Vector;
import javax.naming.Context;

public class BusinessDelegate extends ProxyModelo 
{
	private ServerFacade modCD = null;
	private String naming = Constantes.BEAN_STRING;

	public BusinessDelegate() throws ErrorConectionException
	{
		super();
		this.getConnection();
		//this.guardarDatosCD();
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
	 * @throws ErrorConectionException 
	 * 
	 */
    protected void getConnection() throws ErrorConectionException {
        try {
        	Context jndiContext = getInitialContext();
			this.modCD = (ServerFacade)jndiContext.lookup(naming);

        } catch (Exception e) {
        	e.printStackTrace();
        	throw new ErrorConectionException("No se pudo conectar");
        }
    }    
	 
    private static Context getInitialContext() throws javax.naming.NamingException {
        return new javax.naming.InitialContext();
    }
	
	public ArrayList<String> getDescripciones(ArrayList<Long> codigos) {
		ArrayList<String> descripciones = new ArrayList<String>();
		try {
			descripciones = getModCD().getDescripciones(codigos);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return descripciones;
	}

	public ArrayList<Integer> getStocks(ArrayList<Long> codigos) {
		ArrayList<Integer> stocks = new ArrayList<Integer>();
		try {
			stocks = getModCD().getStocks(codigos);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return stocks;
	}

	public void guardarAritucloHogar(ArticuloHogarVO a)  throws ExistingProductException
	{
		this.getModCD().guardarArticuloHogar(a);
	}
	
	public void guardarAritucloRopa(ArticuloRopaVO a) throws ExistingProductException
	{
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

	public void actualizarStock(ArrayList<ArticuloHeaderVO> articulo) {
		this.getModCD().actualizarStock((ArrayList<ArticuloHeaderVO>) articulo);
	}

	public Integer getNumeroSolEnv() {	
		return this.getModCD().getNumeroSolEnv();
	}

	public ArticuloHeaderVO getArticulo(long cod) {
		return this.getModCD().getArticulo(cod);
	}

	public int getNumeroSolFab() {
		return this.getModCD().getNumeroSolFab();
	}

	public FabricaVO getFabrica() {
		return this.getModCD().getFabrica();
	}

	public void guardarArticulosPendientes() {
		this.getModCD().guardarArticulosPendientes();
	}


}
