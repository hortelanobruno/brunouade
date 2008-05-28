package src;

import java.util.Vector;

import javax.ejb.Stateless;

import BusinessLogic.ArticuloHeaderVO;
import BusinessLogic.ArticuloHogarVO;
import BusinessLogic.ArticuloRopaVO;
import BusinessLogic.ServerFacade;
import BusinessLogic.SolicitudDistribucionVO;
import BusinessLogic.SolicitudEnvioVO;
import BusinessLogic.SolicitudFabricaVO;

@Stateless
public class ServerFacadeBean implements ServerFacade 
{
	public int getTest() {
		// TODO Auto-generated method stub
		return 5000000;
	}

	public void actualizarSolFab(Vector<ArticuloHeaderVO> arts) {
		// TODO Auto-generated method stub
		
	}

	public void actualizarStock(Vector<ArticuloHeaderVO> arts) {
		// TODO Auto-generated method stub
		
	}

	public void guardarSolicitud(SolicitudDistribucionVO soldist) {
		// TODO Auto-generated method stub
		
	}

	public Vector<SolicitudFabricaVO> getAllSolFab() {
		// TODO Auto-generated method stub
		return null;
	}

	public SolicitudFabricaVO getSolFab(long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<SolicitudFabricaVO> getSolsFab(int codTienda) {
		// TODO Auto-generated method stub
		return null;
	}

	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnv) {
		// TODO Auto-generated method stub
		
	}

	public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab) {
		// TODO Auto-generated method stub
		
	}

	public void guardarArticuloHogar(ArticuloHogarVO art) {
		// TODO Auto-generated method stub
		
	}

	public void guardarArticuloRopa(ArticuloRopaVO art) {
		// TODO Auto-generated method stub
		
	}

	public Vector<String> getDescripciones(Vector<Long> codigos) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<Integer> getStocks(Vector<Long> codigos) {
		// TODO Auto-generated method stub
		return null;
	}


}
