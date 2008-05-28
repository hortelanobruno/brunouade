package server;

import java.util.Vector;
import javax.ejb.Stateless;
import BusinessLogic.ArticuloHeaderVO;
import BusinessLogic.ArticuloHogarVO;
import BusinessLogic.ArticuloRopaVO;
import BusinessLogic.ServerFacade;
import BusinessLogic.SolicitudDistribucionVO;
import BusinessLogic.SolicitudEnvioVO;
import BusinessLogic.SolicitudFabricaVO;
import beans.*;

@Stateless
public class ServerFacadeBean implements ServerFacade 
{
	private AdministradorArticulos admArt;
	private AdministradorDistribucion admDist;
	private AdministradorEnvios admEnv;
	private AdministradorFabricacion admFab;
	private AdministradorReposicion admRep;
	
	public ServerFacadeBean()
	{
		admArt = new AdministradorArticulosBean();
		admDist = new AdministradorDistribucionBean();
		admEnv = new AdministradorEnviosBean();
		admFab = new AdministradorFabricacionBean();
		admRep = new AdministradorReposicionBean();
	}
	
	public int getTest() 
	{
		return 5000000;
	}

	public void actualizarSolFab(Vector<ArticuloHeaderVO> arts) 
	{
		// TODO Auto-generated method stub
	}

	public void actualizarStock(Vector<ArticuloHeaderVO> arts) 
	{
		admArt.actualizarStock(arts);
	}

	public void guardarSolicitud(SolicitudDistribucionVO soldist) 
	{
		// TODO Auto-generated method stub
	}

	public Vector<SolicitudFabricaVO> getAllSolFab() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public SolicitudFabricaVO getSolFab(int codigo) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<SolicitudFabricaVO> getSolsFab(int codTienda) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnv) 
	{
		// TODO Auto-generated method stub
	}

	public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab) 
	{
		// TODO Auto-generated method stub
	}

	public void guardarArticuloHogar(ArticuloHogarVO art) 
	{
		this.admArt.agregarArticuloHogar(art);
	}

	public void guardarArticuloRopa(ArticuloRopaVO art) 
	{
		this.admArt.agregarArticuloRopa(art);		
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
		// TODO Auto-generated method stub
		return null;
	}
}
