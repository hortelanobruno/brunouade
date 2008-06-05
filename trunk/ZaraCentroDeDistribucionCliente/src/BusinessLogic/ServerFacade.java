package BusinessLogic;

import java.util.Vector;
import javax.ejb.Remote;

import Exceptions.ExistingProductException;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import VO.FabricaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;

@Remote
public interface ServerFacade 
{
	public int getTest(); //Prueba
	public void guardarDatosCD(int codigo,String nombre,float longitud, float latitud, Vector<String> lineasRopa, Vector<String> categoriasHogar);
	
	
	//panel sol dist
	public Vector<String> getDescripciones(Vector<Long> codigos);
	public Vector<Integer> getStocks(Vector<Long> codigos);
	
	public void guardarSolicitud(SolicitudDistribucionVO soldist);
	public int getNumeroSolEnv();
	public int getNumeroSolFab();
	public ArticuloHeaderVO getArticulo(long codigo);
	public FabricaVO getFabrica();
	public void guardarArticulosPendientes();
	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnv);
	public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab);
	
	//	panel envios
	public Vector<SolicitudFabricaVO> getSolsFab(int codTienda);
	
	
	//panel gen sol fab
	public SolicitudFabricaVO getSolFab(long codigo);
	public Vector<SolicitudFabricaVO> getAllSolFab();
	
	//panel reposicion
	public void actualizarStock(Vector<ArticuloHeaderVO> arts);
	public void actualizarSolFab(SolicitudFabricaVO solFab);
	public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO);
	public SolicitudFabricaVO cargarSolicitudFabricacion(long codigoSolFab);
	
	
	//panel new art
	public void guardarArticuloHogar(ArticuloHogarVO a) throws ExistingProductException;
	public void guardarArticuloRopa(ArticuloRopaVO a) throws ExistingProductException;
	
	
	
	

	
}
