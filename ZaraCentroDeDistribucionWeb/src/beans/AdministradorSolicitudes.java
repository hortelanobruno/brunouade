package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

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
    public int getNumeroSolEnv();
	public int getNumeroSolFab();
	public boolean existeSolDis(int codigo);
	public boolean existeSolRep(int codigo);
	public void actualizarSolFab(SolicitudFabricaVO solFabVO);
	public ArrayList<SolicitudDistribucionVO> getSolsDis(String tienda);
	public void guardarSolEnv(SolicitudEnvioVO solEnvio);
	public void actualizarSolDis(SolicitudDistribucionVO solDis);
	public int getNextId();
	public boolean existeSolFab(int numSolFab);
	public ArrayList<SolicitudDistribucionVO> getAllSolicitudesDistribucion();
	public SolicitudDistribucionVO obtenerSolicitudDistribucion(int codSolDis);
	public int getNextIdSolDis();
	public int getNexIdSolRep();
	public boolean existenSolsFab(List<Integer> numsSolFab);
	public Collection<SolicitudFabricaVO> getSolicitudesDeFabricacion(List<Integer> numsSolFab);
	public List<SolicitudDeReposicionVO> obtenerSolicitudesDeReposicionAProcesar();
	public List<SolicitudDistribucionVO> obtenerSolDisAbiertas();
	public List<SolicitudFabricaVO> getSolicitudesDeFabricacionAbiertas();
	
}
