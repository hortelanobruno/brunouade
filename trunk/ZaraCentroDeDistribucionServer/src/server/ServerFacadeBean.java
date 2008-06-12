package server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import BusinessLogic.ServerFacade;
import Exceptions.ExistingProductException;
import VO.ArticuloAEnviarVO;
import VO.ArticuloAFabricarVO;
import VO.ArticuloAReponerVO;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import VO.CentroDistribucionVO;
import VO.FabricaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;
import VO.TiendaVO;
import beans.AdministracionConfiguracion;
import beans.AdministradorArticulos;
import beans.AdministradorSolicitudes;

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

	public void actualizarStock(Vector<ArticuloHeaderVO> arts) 
	{
		admArt.actualizarStock(arts);
	}

	public void guardarSolicitud(SolicitudDistribucionVO soldist) 
	{
		this.admSol.guardarSolicitud(soldist);
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

	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnv) 
	{
		this.admSol.guardarSolicitudDeEnvio(solEnv);
	}

	public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab) 
	{
		this.admSol.guardarSolicitudFabricacion(solFab);
	}

	public ArrayList<String> getDescripciones(ArrayList<Long> codigos) 
	{
		return admArt.getDescripciones(codigos);
	}

	public ArrayList<Integer> getStocks(ArrayList<Long> codigos) 
	{
		return admArt.getStocks(codigos);
	}

	public SolicitudFabricaVO getSolFab(long codigo) 
	{
		return this.admSol.getSolFab(codigo);
	}

	public void guardarArticuloHogar(ArticuloHogarVO a) throws ExistingProductException
	{
		this.admArt.guardarArticuloHogar(a);
	}

	public void guardarArticuloRopa(ArticuloRopaVO a) throws ExistingProductException
	{
		this.admArt.guardarArticuloRopa(a);
	}

	public void actualizarSolFab(SolicitudFabricaVO solFab)
	{
		this.admSol.actualizarSolFab(solFab);
	}

	public SolicitudFabricaVO cargarSolicitudFabricacion(long codigoSolFab) 
	{
		return this.admSol.cargarSolicitudFabricacion(codigoSolFab);
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

	public int getNumeroSolEnv() 
	{
		return this.admSol.getNumeroSolEnv();
	}

	public int getNumeroSolFab() 
	{
		return this.admSol.getNumeroSolFab();
	}

	public FabricaVO getFabrica() 
	{
		return this.admSol.getFabrica();
	}


	public Vector<TiendaVO> getTiendas() 
	{
		return this.admArt.getTiendas();
	}



	public CentroDistribucionVO getCentro() 
	{
		return this.admConf.getCentro();
	}
	
	public void actualizarStock(ArrayList<ArticuloAReponerVO> arts) {
		
		// TODO Auto-generated method stub
		
	}

	public int getNextIdArticuloPedido() 
	{
		return this.admSol.getNextIdArticuloPedido();
	}
	
	public int getNextIdArticuloAEnviar() 
	{
		return this.admSol.getNextIdArticuloAEnviar();
	}
	
	public int getNextIdArticuloAFabricar() 
	{
		return this.admSol.getNextIdArticuloAFabricar();
	}

	public void guardarArticulosAEnviar(Collection<ArticuloAEnviarVO> artic2) {
		this.admArt.guardarArticulosAEnviar(artic2);
	}

	public void guardarArticulosAFabricar(Collection<ArticuloAFabricarVO> artic) {
		this.admArt.guardarArticulosAFabricar(artic);
	}

	public ArrayList<FabricaVO> getFabricas() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
