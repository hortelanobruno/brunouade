package server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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
import beans.AdministracionConfiguracion;
import beans.AdministradorArticulos;
import beans.AdministradorSolicitudes;
import businesslogic.ServerFacade;
import exceptions.ExistingProductException;

@Stateless
public class ServerFacadeBean implements ServerFacade 
{
	@EJB private AdministradorArticulos admArt; 
	@EJB private AdministradorSolicitudes admSol;
	@EJB private AdministracionConfiguracion admConf;
	
	public ServerFacadeBean()
	{

	}
	
	public int getTest() 
	{
		return 5000000;
	}

	public void guardarSolicitudDistribucion(SolicitudDistribucionVO soldist) 
	{
		this.admSol.guardarSolicitudDistribucion(soldist);
	}

	public ArrayList<SolicitudFabricaVO> getAllSolFab() 
	{
		return this.admSol.getAllSolFab();
	}

	public SolicitudFabricaVO getSolFab(int codigo) 
	{
		return this.admSol.getSolFab(codigo);
	}

	public ArrayList<SolicitudFabricaVO> getSolsFab(int codTienda) 
	{
		return this.admSol.getSolsFab(codTienda);
	}

	public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab) 
	{
		this.admSol.guardarSolicitudFabricacion(solFab);
	}

	public void guardarArticuloHogar(ArticuloHogarVO a) throws ExistingProductException
	{
		this.admArt.guardarArticuloHogar(a);
	}

	public void guardarArticuloRopa(ArticuloRopaVO a) throws ExistingProductException
	{
		this.admArt.guardarArticuloRopa(a);
	}

	public void guardarSolFab(SolicitudFabricaVO solFab)
	{
		this.admSol.guardarSolFab(solFab);
	}


	public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO)
	{
		this.admSol.guardarSolicitudReposicion(solRepVO);
	}

	public void guardarDatosCD(int codigo, String nombre, float longitud, float latitud, ArrayList<String> lineasRopa, ArrayList<String> categoriasHogar) 
	{
		this.admConf.guardarDatosCD(codigo, nombre, longitud, latitud, lineasRopa, categoriasHogar);
	}

	public ArticuloHeaderVO getArticulo(long codigo) 
	{
		return this.admArt.getArticulo(codigo);
	}

	public int getNumeroSolFab() 
	{
		return this.admSol.getNumeroSolFab();
	}

	public FabricaVO getFabrica() 
	{
		return this.admConf.getFabrica();
	}

	public ArrayList<TiendaVO> getTiendas() 
	{
		return this.admConf.getTiendas();
	}

	public CentroDistribucionVO getCentro() 
	{
		return this.admConf.getCentro();
	}

	public int getNextIdArticuloPedido() 
	{
		return this.admArt.getNextIdArticuloPedido();
	}
	
	public int getNextIdArticuloAEnviar() 
	{
		return this.admArt.getNextIdArticuloAEnviar();
	}
	
	public int getNextIdArticuloAFabricar() 
	{
		return this.admArt.getNextIdArticuloAFabricar();
	}

	public void guardarArticulosAEnviar(Collection<ArticuloAEnviarVO> artic2) {
		this.admArt.guardarArticulosAEnviar(artic2);
	}

	public void guardarArticulosAFabricar(Collection<ArticuloAFabricarVO> artic) {
		this.admArt.guardarArticulosAFabricar(artic);
	}

	public ArrayList<FabricaVO> getFabricas() 
	{
		return this.admConf.getFabricas();
	}

	public void guardarFabrica(FabricaVO fab) 
	{
		this.admConf.guardarFabrica(fab);
	}

	public void actualizarStock(List<ArticuloAReponerVO> arts)
	{
		admArt.actualizarStock(arts);
	}

	public ArrayList<ArticuloAFabricarVO> getArticulosAFabricar() {
		return this.admArt.getArticulosAFabricar();
	}

	public boolean existeArticulo(long codigo) 
	{
		return this.admArt.existeArticulo(codigo);
	}
	
	public int getNextIdArticuloAReponer() {
		return this.admArt.getNextIdArticuloAReponer();
	}

	public void actualizarSolFab(SolicitudFabricaVO solFabVO) {
		this.admSol.actualizarSolFab(solFabVO);
	}

	public void guardarSolEnv(SolicitudEnvioVO solEnvio) {
		this.admSol.guardarSolEnv(solEnvio);
	}

	public void actualizarSolDis(SolicitudDistribucionVO solDis) {
		this.admSol.actualizarSolDis(solDis);
	}

	public int getNextId() {
		return this.admSol.getNextId();
	}

	public ArrayList<Long> existenArts(ArrayList<Long> codigos) {
		return this.admArt.existenArts(codigos);
	}

	public ArrayList<SolicitudDistribucionVO> getAllSolicitudesDistribucion() {
		return admSol.getAllSolicitudesDistribucion();
	}

	public SolicitudDistribucionVO obtenerSolicitudDistribucion(int codSolDis) {
		return this.admSol.obtenerSolicitudDistribucion(codSolDis);
	}

	public int getNextIdSolDis() {
		return this.admSol.getNextIdSolDis();
	}

	public void guardarTienda(TiendaVO tienda) {
		this.admConf.guardarTienda(tienda);
	}

	public int getNexIdSolRep() {
		return this.admSol.getNexIdSolRep();
	}
	
	public List<SolicitudDeReposicionVO> obtenerSolicitudesDeReposicionAProcesar() {
		return this.admSol.obtenerSolicitudesDeReposicionAProcesar();
	}

	public List<SolicitudDistribucionVO> obtenerSolDisAbiertas() {
		return this.admSol.obtenerSolDisAbiertas();
	}

	public HashMap<Long, Integer> getStocks() {
		return this.admArt.getStocks();
	}

	public List<SolicitudFabricaVO> getSolicitudesDeFabricacionAbiertas() {
		return this.admSol.getSolicitudesDeFabricacionAbiertas();
	}

	public void actualizarStock(HashMap<Long, Integer> stocks) {
		this.admArt.actualizarStock(stocks);
	}

	public void actualizarStock(ArrayList<ArticuloAEnviarVO> articulosAEnviar) {
		this.admArt.actualizarStock(articulosAEnviar);
	}

	public int getNextIdSolEnv() {
		return this.admSol.getNextIdSolEnv();
	}

	public List<ArticuloAFabricarVO> getNuevosArticulosAFabricarVO() {
		return this.admArt.getNuevosArticulosAFabricarVO();
	}
}
