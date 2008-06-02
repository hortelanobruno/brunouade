package server;

import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import BusinessLogic.ServerFacade;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;
import beans.*;

@Stateless
public class ServerFacadeBean implements ServerFacade 
{
	@EJB private AdministradorArticulos admArt; 
	@EJB private AdministradorSolicitudes admSol;
	
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

	public Vector<SolicitudFabricaVO> getAllSolFab() 
	{
		return this.admSol.getAllSolFab();
	}

	public SolicitudFabricaVO getSolFab(int codigo) 
	{
		return this.admSol.getSolFab(codigo);
	}

	public Vector<SolicitudFabricaVO> getSolsFab(int codTienda) 
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

	public Vector<String> getDescripciones(Vector<Long> codigos) 
	{
		return admArt.getDescripciones(codigos);
	}

	public Vector<Integer> getStocks(Vector<Long> codigos) 
	{
		return admArt.getStocks(codigos);
	}

	public SolicitudFabricaVO getSolFab(long codigo) 
	{
		return this.admSol.getSolFab(codigo);
	}

	public void guardarArticuloHogar(ArticuloHogarVO a) 
	{
		this.admArt.guardarArticuloHogar(a);
	}

	public void guardarArticuloRopa(ArticuloRopaVO a)
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

	public void guardarDatosCD(int codigo, String nombre, double longitud, double latitud, Vector<String> lineasRopa, Vector<String> categoriasHogar) 
	{
		this.admArt.guardarDatosCD(codigo, nombre, longitud, latitud, lineasRopa, categoriasHogar);
	}
}
