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

	public void guardarArticuloHogar(ArticuloHogarVO a) 
	{
		this.admArt.guardarArticuloHogar(a);
	}

	public void guardarArticuloRopa(ArticuloRopaVO a)
	{
		this.admArt.guardarArticuloRopa(a);
	}

	public void actualizarSolFab(SolicitudFabricaVO solFab) {
		// TODO Auto-generated method stub
		
	}

	public SolicitudFabricaVO cargarSolicitudFabricacion(long codigoSolFab) {
		// TODO Auto-generated method stub
		return null;
	}

	public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO) {
		// TODO Auto-generated method stub
		
	}
}
