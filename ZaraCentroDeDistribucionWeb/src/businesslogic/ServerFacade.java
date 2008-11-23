package businesslogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;

import vo.ArticuloAEnviarVO;
import vo.ArticuloAFabricarVO;
import vo.ArticuloAReponerVO;
import vo.ArticuloHeaderVO;
import vo.ArticuloHogarVO;
import vo.ArticuloRopaVO;
import vo.CentroDistribucionVO;
import vo.FabricaVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudDistribucionVO;
import vo.SolicitudEnvioVO;
import vo.SolicitudFabricaVO;
import vo.TiendaVO;
import exceptions.ExistingProductException;

@Remote
public interface ServerFacade 
{
	public int getTest(); //Prueba
	public void guardarDatosCD(int codigo,String nombre,float longitud, float latitud, ArrayList<String> lineasRopa, ArrayList<String> categoriasHogar);
	public CentroDistribucionVO getCentro();
	public void guardarFabrica(FabricaVO fab);
	public void guardarTienda(TiendaVO tienda);
	
	//panel sol dist
	public ArrayList<FabricaVO> getFabricas();
	public int getNextIdArticuloPedido();
	public void guardarSolicitudDistribucion(SolicitudDistribucionVO soldist);
	public void guardarArticulosAFabricar(Collection<ArticuloAFabricarVO> artic);
	public int getNextIdArticuloAFabricar();
	public ArticuloHeaderVO getArticulo(long codigo);
	public int getNextId();
	public ArrayList<Long> existenArts(ArrayList<Long> codigos);
	public int getNextIdSolDis();
	public void actualizarStock(HashMap<Long, Integer> stocks);
	
	//	panel envios
	public int getNextIdArticuloAEnviar();
	public void guardarSolEnv(SolicitudEnvioVO solEnvio);	
	public void actualizarSolDis(SolicitudDistribucionVO solDis);
	public ArrayList<TiendaVO> getTiendas();
	public SolicitudDistribucionVO obtenerSolicitudDistribucion(int codSolDis);
	public ArrayList<SolicitudDistribucionVO> getAllSolicitudesDistribucion();
	public void actualizarStock(ArrayList<ArticuloAEnviarVO> articulosAEnviar);	
	public int getNextIdSolEnv();
	
	//panel gen sol fab
	public ArrayList<ArticuloAFabricarVO> getArticulosAFabricar();
	public int getNumeroSolFab();
	public void guardarSolFab(SolicitudFabricaVO solFab);
	public List<ArticuloAFabricarVO> getNuevosArticulosAFabricarVO();
	
	//panel reposicion
	public int getNextIdArticuloAReponer();
	public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO);
	public void actualizarSolFab(SolicitudFabricaVO solFabVO);
	public void actualizarStock(List<ArticuloAReponerVO> arts);
	public int getNexIdSolRep();
	public List<SolicitudDeReposicionVO> obtenerSolicitudesDeReposicionAProcesar();
	public List<SolicitudDistribucionVO> obtenerSolDisAbiertas();
	public HashMap<Long, Integer> getStocks();
	public List<SolicitudFabricaVO> getSolicitudesDeFabricacionAbiertas();
	
	//panel new art
	public boolean existeArticulo(long codigo);//Nuevo hay que hacer
	public void guardarArticuloHogar(ArticuloHogarVO a) throws ExistingProductException;
	public void guardarArticuloRopa(ArticuloRopaVO a) throws ExistingProductException;
	
	
}
