package BusinessLogic;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import Exceptions.ErrorConectionException;
import Exceptions.ExistingProductException;
import MVCFramework.ProxyModelo;
import VO.ArticuloAEnviarVO;
import VO.ArticuloAFabricarVO;
import VO.ArticuloAReponerVO;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloReservadoVO;
import VO.ArticuloRopaVO;
import VO.CentroDistribucionVO;
import VO.FabricaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;
import Varios.Constantes;

public class BusinessDelegate extends ProxyModelo 
{
	private ServerFacade modCD = null;
	private String naming = Constantes.BEAN_STRING;

	public BusinessDelegate() throws ErrorConectionException
	{
		super();
		this.getConnection();
		this.guardarDatosCD();
		this.guardarFabrica();
	}
	
	private void guardarDatosCD()
	{
		ArrayList<String> lineasRopa = new ArrayList<String>();
		lineasRopa.add("woman");
		lineasRopa.add("basic");
		lineasRopa.add("sport");
		lineasRopa.add("vestir");
		
		ArrayList<String> categoriasHogar = new ArrayList<String>();
		categoriasHogar.add("baño");
		categoriasHogar.add("mesa");
		categoriasHogar.add("decoracion");
		categoriasHogar.add("baño");
		
		this.getModCD().guardarDatosCD(1, "Shopping Centro", 56.887521f, 34.90417f, lineasRopa, categoriasHogar);
	}
	
	private void guardarFabrica(){
		FabricaVO fabrica = new FabricaVO();
		fabrica.setCodigoFabrica(1);
		fabrica.setNombreFabrica("Lanus Este");
		this.getModCD().guardarFabrica(fabrica);
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
    @SuppressWarnings("unchecked")
	protected void getConnection() throws ErrorConectionException {
        try {
        	//Context jndiContext = getInitialContext();
        	//this.modCD = (ServerFacade)jndiContext.lookup(naming);
        	Hashtable props = new Hashtable();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL,"jnp://127.0.0.1:1099");
			InitialContext context = new InitialContext(props);
			this.modCD = (ServerFacade) context.lookup(naming);
			

        } catch (Exception e) {
        	e.printStackTrace();
        	throw new ErrorConectionException("No se pudo conectar");
        }
    }    
	 
    @SuppressWarnings("unused")
	private static Context getInitialContext() throws javax.naming.NamingException {
        return new javax.naming.InitialContext();
    }
	
	public ArrayList<String> getDescripciones(ArrayList<Long> codigos) {
		ArrayList<String> descripciones = new ArrayList<String>();
		try {
			descripciones = getModCD().getDescripciones(codigos);
		} catch (UndeclaredThrowableException ex) {
			ex.getUndeclaredThrowable();
			ex.getMessage();
			ex.getCause();
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

	public void guardarArticuloHogar(ArticuloHogarVO a)  throws ExistingProductException
	{
		this.getModCD().guardarArticuloHogar(a);
	}
	
	public void guardarArticuloRopa(ArticuloRopaVO a) throws ExistingProductException
	{
		this.getModCD().guardarArticuloRopa(a);
	}
	
	public void guardarSolicitud(SolicitudDistribucionVO soldis) {
		this.getModCD().guardarSolicitudDistribucion(soldis);
	}


	public ServerFacade getModCD() {
		return modCD;
	}

	public SolicitudFabricaVO getSolicitudFabricacion(int codigoSolFab) {
		return this.getModCD().cargarSolicitudFabricacion(codigoSolFab);
	}

	public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab) {
		this.getModCD().guardarSolFab(solFab);
	}

	public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO) {
		this.getModCD().guardarSolicitudReposicion(solRepVO);
	}

	public void actualizarStock(ArrayList<ArticuloAReponerVO> articulo) {
		this.getModCD().actualizarStock((ArrayList<ArticuloAReponerVO>) articulo);
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

	public void guardarArticulosAFabricar(Collection<ArticuloAFabricarVO> artic) {
		this.getModCD().guardarArticulosAFabricar(artic);
	}

	public void guardarArticulosAEnviar(Collection<ArticuloAEnviarVO> artic2) {
		this.getModCD().guardarArticulosAEnviar(artic2);
	}
	
	public CentroDistribucionVO getCentro() {
		return this.getModCD().getCentro();
	}

	public int getNextId() {
		return this.getModCD().getNextIdArticuloPedido();
	}

	public int getNextIdAEnv() {
		return this.getModCD().getNextIdArticuloAEnviar();
	}

	public int getNextIdAFab() {
		return this.getModCD().getNextIdArticuloAFabricar();
	}

	public ArrayList<FabricaVO> getFabricas() {
		return this.getModCD().getFabricas();
	}

	public void modificarStock(Collection<ArticuloReservadoVO> artiAEnv) {
		this.getModCD().modificarStock(artiAEnv);		
	}

	public ArrayList<ArticuloAFabricarVO> getArticulosAFabricarVO() {
		return this.getModCD().getArticulosAFabricar();
	}

	public boolean existeArticulo(long codigo) {
		return this.getModCD().existeArticulo(codigo);
	}

	public boolean existeSolDis(int numero) {
		return this.getModCD().existeSolDis(numero);
	}

	public boolean existeSolRep(int numero) {
		return this.getModCD().existeSolRep(numero);
	}

	public int getNextIdARes() {
		return this.getModCD().getNextIdArticuloReservado();
	}

	public void guardarArticulosReservados(Collection<ArticuloReservadoVO> artiReser) {
		this.getModCD().guardarArticulosReservado(artiReser);
	}

	public void actualizarSolicitudFabricacion(SolicitudFabricaVO solFabVO) {
		this.getModCD().actualizarSolFab(solFabVO);
	}

	public int getNextIdARep() {
		return this.getModCD().getNextIdArticuloAReponer();
	}

	public ArrayList<ArticuloHeaderVO> getArticulos(ArrayList<Long> codigos) {
		return this.getModCD().getArticulos(codigos);
	}

	public ArrayList<SolicitudDistribucionVO> obtenerSolicitudesDeTienda(String tienda) {
		return this.getModCD().getSolicitudesDistribucion(tienda);
	}

	public ArrayList<ArticuloReservadoVO> obtenerArticulosReservados(int codSolDis) {
		return this.getModCD().getArtsReservados(codSolDis);
	}

	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnvio) {
		this.getModCD().guardarSolEnv(solEnvio);
	}

	public void actualizarArticulosReservados(ArrayList<ArticuloReservadoVO> articulosReservados) {
		this.getModCD().actArtsRes(articulosReservados);
	}

	public void actualizarStock(ArrayList<ArticuloAEnviar> articulosAEnviar, ArrayList<ArticuloReservadoVO> articulosReservados) {
		this.getModCD().actualizarStock(articulosAEnviar,articulosReservados);
	}

	public void actualizarSolicitudDistribucion(SolicitudDistribucionVO solDis) {
		this.getModCD().actualizarSolDis(solDis);
	}




}
