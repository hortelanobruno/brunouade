package beans;

import java.util.ArrayList;

import javax.ejb.Remote;

import VO.FabricaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;

@Remote
public interface AdministradorSolicitudes 
{
	
	public void guardarSolicitud(SolicitudDistribucionVO soldist);
	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnv);
    public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab);
    public ArrayList<SolicitudFabricaVO> getSolsFab(int codTienda);
    public SolicitudFabricaVO getSolFab(long codigo);
    public ArrayList<SolicitudFabricaVO> getAllSolFab();
    public void actualizarSolFab(SolicitudFabricaVO solFab);
    public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO);
    public SolicitudFabricaVO cargarSolicitudFabricacion(long codigoSolFab);
    public int getNumeroSolEnv();
	public int getNumeroSolFab();
	public FabricaVO getFabrica();
}
