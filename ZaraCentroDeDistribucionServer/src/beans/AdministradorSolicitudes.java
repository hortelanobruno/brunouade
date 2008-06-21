package beans;

import java.util.ArrayList;

import javax.ejb.Remote;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;

@Remote
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
	public int getNextIdArticuloPedido();
	public int getNextIdArticuloAEnviar();
	public int getNextIdArticuloAFabricar();
	public boolean existeSolDis(int codigo);
	public boolean existeSolRep(int codigo);
	public int getNextIdArticuloReservado();
	public void actualizarSolFab(SolicitudFabricaVO solFabVO);
	public int getNextIdArticuloAReponer();
}
