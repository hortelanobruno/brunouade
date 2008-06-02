package beans;

import java.util.Vector;

import javax.ejb.Stateless;

import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;
import beans.AdministradorSolicitudes;

@Stateless
public class AdministradorSolicitudesBean implements AdministradorSolicitudes
{
	public void actualizarSolFab(SolicitudFabricaVO solFab) 
	{
		
	}

	public SolicitudFabricaVO cargarSolicitudFabricacion(long codigoSolFab) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<SolicitudFabricaVO> getAllSolFab() {
		// TODO Auto-generated method stub
		return null;
	}

	public SolicitudFabricaVO getSolFab(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<SolicitudFabricaVO> getSolsFab(int codTienda) {
		// TODO Auto-generated method stub
		return null;
	}

	public void guardarSolicitud(SolicitudDistribucionVO soldist) {
		// TODO Auto-generated method stub
		
	}

	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnv) {
		// TODO Auto-generated method stub
		
	}

	public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab) {
		// TODO Auto-generated method stub
		
	}

	public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO) {
		// TODO Auto-generated method stub
		
	}
	
}
