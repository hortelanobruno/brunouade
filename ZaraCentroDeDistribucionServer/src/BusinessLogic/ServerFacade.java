package BusinessLogic;

import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface ServerFacade 
{
	public int getTest(); //Prueba
	
	//panel sol dist
	public Vector<String> getDescripciones(Vector<Integer> codigos);
	public Vector<Integer> getStocks(Vector<Integer> codigos);
	
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
	public void guardarArticuloHogar(ArticuloHogarVO art);
	public void guardarArticuloRopa(ArticuloRopaVO art);
}
