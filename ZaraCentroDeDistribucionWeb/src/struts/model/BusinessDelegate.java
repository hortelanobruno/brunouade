package struts.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import varios.Constantes;
import vo.ArticuloAEnviarVO;
import vo.ArticuloAFabricarVO;
import vo.ArticuloAReponerVO;
import vo.ArticuloHeaderVO;
import vo.ArticuloHogarVO;
import vo.ArticuloRopaVO;
import vo.CentroDistribucionVO;
import vo.FabricaVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudDistribucionVO;
import vo.SolicitudEnvioVO;
import vo.SolicitudFabricaVO;
import vo.TiendaVO;
import businesslogic.ServerFacade;
import exceptions.ErrorConectionException;
import exceptions.ExistingProductException;

public class BusinessDelegate
{
	private ServerFacade modCD = null;
	private String naming = Constantes.BEAN_STRING;

	public BusinessDelegate() throws ErrorConectionException
	{
		super();
		this.getConnection();
		cagarDatosIniciales();
	}
	
	public void cagarDatosIniciales() {
		if(this.getModCD().getCentro() == null){
			this.guardarDatosCD();
			this.guardarFabrica();
			this.guardarTiendas();
		}
	}

	private synchronized void guardarTiendas(){
		TiendaVO tienda = new TiendaVO();
		tienda.setCodigoTienda(Integer.parseInt(Constantes.TIENDA1CODIGO));
		tienda.setNombreTienda("Paseo Alcorta");
		this.getModCD().guardarTienda(tienda);
		tienda = new TiendaVO();
		tienda.setCodigoTienda(Integer.parseInt(Constantes.TIENDA2CODIGO));
		tienda.setNombreTienda("Punta Carretas");
		this.getModCD().guardarTienda(tienda);
	}
	
	private synchronized void guardarDatosCD()
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
	
	private synchronized void guardarFabrica(){
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
        	Hashtable props = new Hashtable();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL,"jnp://"+Constantes.IP_CENTRODISTRIBUCION+":1099");
			InitialContext context = new InitialContext(props);
			this.modCD = (ServerFacade) context.lookup(naming);
        } catch (Exception e) {
        	throw new ErrorConectionException("No se pudo conectar");
        }
    }    
	 
    @SuppressWarnings("unused")
	private static Context getInitialContext() throws javax.naming.NamingException {
        return new javax.naming.InitialContext();
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

	public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab) {
		this.getModCD().guardarSolFab(solFab);
	}

	public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO) {
		this.getModCD().guardarSolicitudReposicion(solRepVO);
	}

	public void actualizarStock(List<ArticuloAReponerVO> articulo) {
		this.getModCD().actualizarStock(articulo);
	}
	
	public ArticuloHeaderVO getArticulo(long cod) {
		return this.getModCD().getArticulo(cod);
	}

	public int getNumeroSolFab() {
		return this.getModCD().getNumeroSolFab();
	}

	public void guardarArticulosAFabricar(Collection<ArticuloAFabricarVO> artic) {
		this.getModCD().guardarArticulosAFabricar(artic);
	}
	
	public CentroDistribucionVO getCentro() {
		return this.getModCD().getCentro();
	}

	public int getNextIdArtPed() {
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

	public ArrayList<ArticuloAFabricarVO> getArticulosAFabricarVO() {
		return this.getModCD().getArticulosAFabricar();
	}

	public boolean existeArticulo(long codigo) {
		return this.getModCD().existeArticulo(codigo);
	}

	public void actualizarSolicitudFabricacion(SolicitudFabricaVO solFabVO) {
		this.getModCD().actualizarSolFab(solFabVO);
	}

	public int getNextIdARep() {
		return this.getModCD().getNextIdArticuloAReponer();
	}
	
	public ArrayList<SolicitudDistribucionVO> obtenerSolicitudesDeTiendas() {
		return this.getModCD().getAllSolicitudesDistribucion();
	}

	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnvio) {
		this.getModCD().guardarSolEnv(solEnvio);
	}

	public void actualizarSolicitudDistribucion(SolicitudDistribucionVO solDis) {
		this.getModCD().actualizarSolDis(solDis);
	}

	public ArrayList<TiendaVO> obtenerTiendas() {
		return this.getModCD().getTiendas();
	}

	public int getNextId() {
		return this.getModCD().getNextId();
	}

	public ArrayList<Long> existenArts(ArrayList<Long> codigos) {
		return this.getModCD().existenArts(codigos);
	}

	public SolicitudDistribucionVO obtenerSolicitudDistribucion(int codSolDis) {
		return this.getModCD().obtenerSolicitudDistribucion(codSolDis);
	}
	
	public int getNextIdSolDis(){
		return this.getModCD().getNextIdSolDis();
	}

	public int getNexIdSolRep() {
		return this.getModCD().getNexIdSolRep();
	}

	public List<SolicitudDeReposicionVO> obtenerSolicitudesDeReposicionAProcesar() {
		return this.getModCD().obtenerSolicitudesDeReposicionAProcesar();
	}

	public List<SolicitudDistribucionVO> obtenerSolDisAbiertas() {
		return this.getModCD().obtenerSolDisAbiertas();
	}

	public HashMap<Long, Integer> getStocks() {
		return this.getModCD().getStocks();
	}

	public List<SolicitudFabricaVO> getSolicitudesDeFabricacionAbiertas() {
		return this.getModCD().getSolicitudesDeFabricacionAbiertas();
	}

	public void actualizarStock(HashMap<Long, Integer> stocks) {
		this.getModCD().actualizarStock(stocks);
	}

	public void actualizarStock(ArrayList<ArticuloAEnviarVO> articulosAEnviar) {
		this.getModCD().actualizarStock(articulosAEnviar);
	}
	
	public int getNextIdSolEnv(){
		return this.getModCD().getNextIdSolEnv();
	}

	public List<ArticuloAFabricarVO> getNuevosArticulosAFabricarVO() {
		return this.getModCD().getNuevosArticulosAFabricarVO();
	}
}
