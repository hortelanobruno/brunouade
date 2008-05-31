package BusinessLogic;

import java.util.Vector;
import javax.ejb.Remote;

import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;

@Remote
public interface ServerFacade 
{
	public int getTest(); //Prueba
	
	//panel sol dist
	public Vector<String> getDescripciones(Vector<Long> codigos);
	public Vector<Integer> getStocks(Vector<Long> codigos);
	
	public void guardarSolicitud(SolicitudDistribucionVO soldist);
	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnv);
	public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab);
	
	//	panel envios
	public Vector<SolicitudFabricaVO> getSolsFab(int codTienda);
	
	
	//panel gen sol fab
	public SolicitudFabricaVO getSolFab(int codigo);
	public Vector<SolicitudFabricaVO> getAllSolFab();
	
	//panel reposicion
	public void actualizarStock(Vector<ArticuloHeaderVO> arts);
	public void actualizarSolFab(Vector<ArticuloHeaderVO> arts);
	
	//panel new art
	public void guardarArticuloHogar(ArticuloHogarVO a);
	public void guardarArticuloRopa(ArticuloRopaVO a);
}