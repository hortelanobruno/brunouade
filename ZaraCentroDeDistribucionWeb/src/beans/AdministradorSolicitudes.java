package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import vo.ArticuloAFabricarVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudDistribucionVO;
import vo.SolicitudEnvioVO;
import vo.SolicitudFabricaVO;

@Local
public interface AdministradorSolicitudes 
{
	public void guardarSolicitudDistribucion(SolicitudDistribucionVO soldist);
	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnv);
    public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab);
    public ArrayList<SolicitudFabricaVO> getSolsFab(int codTienda); //no se usa
    public SolicitudFabricaVO getSolFab(int codigo);
    public ArrayList<SolicitudFabricaVO> getAllSolFab();
    public void guardarSolFab(SolicitudFabricaVO solFab);
    public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO);
	public int getNumeroSolFab();
	public void actualizarSolFab(SolicitudFabricaVO solFabVO);
	public void guardarSolEnv(SolicitudEnvioVO solEnvio);
	public void actualizarSolDis(SolicitudDistribucionVO solDis);
	public int getNextId();
	public ArrayList<SolicitudDistribucionVO> getAllSolicitudesDistribucion();
	public SolicitudDistribucionVO obtenerSolicitudDistribucion(int codSolDis);
	public int getNextIdSolDis();
	public int getNexIdSolRep();
	public int getNextIdSolEnv();
	public List<SolicitudDeReposicionVO> obtenerSolicitudesDeReposicionAProcesar();
	public List<SolicitudDistribucionVO> obtenerSolDisAbiertas();
	public List<SolicitudFabricaVO> getSolicitudesDeFabricacionAbiertas();
	
	
}
