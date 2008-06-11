package BusinessLogic;

import java.util.ArrayList;

import javax.ejb.Remote;

import Exceptions.ExistingProductException;
import VO.ArticuloAReponerVO;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import VO.CentroDistribucionVO;
import VO.FabricaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;

@Remote
public interface ServerFacade 
{
	public int getTest(); //Prueba
	public void guardarDatosCD(int codigo,String nombre,float longitud, float latitud, ArrayList<String> lineasRopa, ArrayList<String> categoriasHogar);
	public CentroDistribucionVO getCentro();
	
	//panel sol dist
	public ArrayList<String> getDescripciones(ArrayList<Long> codigos);
	public ArrayList<Integer> getStocks(ArrayList<Long> codigos);
	
	public void guardarSolicitud(SolicitudDistribucionVO soldist);
	public int getNumeroSolEnv();
	public int getNumeroSolFab();
	public ArticuloHeaderVO getArticulo(long codigo);
	public FabricaVO getFabrica();
	public void guardarArticulosAFabricar();
	public void guardarArticulosAEnviar();
	
	//	panel envios
	public ArrayList<SolicitudFabricaVO> getSolsFab(int codTienda);
	
	
	//panel gen sol fab
	public SolicitudFabricaVO getSolFab(long codigo);
	public ArrayList<SolicitudFabricaVO> getAllSolFab();
	
	//panel reposicion
	public void actualizarStock(ArrayList<ArticuloAReponerVO> arts);
	public void actualizarSolFab(SolicitudFabricaVO solFab);
	public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO);
	public SolicitudFabricaVO cargarSolicitudFabricacion(long codigoSolFab);
	
	
	//panel new art
	public void guardarArticuloHogar(ArticuloHogarVO a) throws ExistingProductException;
	public void guardarArticuloRopa(ArticuloRopaVO a) throws ExistingProductException;
	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnv);
	public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab);
	public void guardarArticulosPendientes();
	
	
	
	
	

	
}
