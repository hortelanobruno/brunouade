package BusinessLogic;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Remote;

import Exceptions.ExistingProductException;
import VO.ArticuloAEnviarVO;
import VO.ArticuloAFabricarVO;
import VO.ArticuloAReponerVO;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloReservadoVO;
import VO.ArticuloRopaVO;
import VO.CentroDistribucionVO;
import VO.FabricaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;
import VO.TiendaVO;

@Remote
public interface ServerFacade 
{
	public int getTest(); //Prueba
	public void guardarDatosCD(int codigo,String nombre,float longitud, float latitud, ArrayList<String> lineasRopa, ArrayList<String> categoriasHogar);
	public CentroDistribucionVO getCentro();
	public void guardarFabrica(FabricaVO fab);
	
	//panel sol dist
	public ArrayList<FabricaVO> getFabricas();
	public boolean existeSolDis(int numero);//Nuevo hay que hacer
	public int getNextIdArticuloPedido();
	public ArrayList<String> getDescripciones(ArrayList<Long> codigos);
	public ArrayList<Integer> getStocks(ArrayList<Long> codigos);
	public void guardarSolicitudDistribucion(SolicitudDistribucionVO soldist);
	public void guardarArticulosReservado(Collection<ArticuloReservadoVO> artiReser);
	public void guardarArticulosAFabricar(Collection<ArticuloAFabricarVO> artic);
	public void modificarStock(Collection<ArticuloReservadoVO> artiAEnv);
	public int getNextIdArticuloReservado();
	public int getNextIdArticuloAFabricar();
	public ArticuloHeaderVO getArticulo(long codigo);
	public int getNextId();
	public ArrayList<Long> existenArts(ArrayList<Long> codigos);
	
	//estos no van - verificar
	//public int getNumeroSolEnv();
	//public void guardarArticulosAEnviar(Collection<ArticuloAEnviarVO> artic2);
	//public SolicitudFabricaVO getSolFab(int codigo);
	//public ArrayList<SolicitudFabricaVO> getAllSolFab();
	//public ArrayList<SolicitudFabricaVO> getSolsFab(int codTienda);
	//ublic void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnv);
	//public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab);
	/////////////////////////////////////
	
	//	panel envios
	public int getNextIdArticuloAEnviar();
	public ArrayList<SolicitudDistribucionVO> getSolicitudesDistribucion(String tienda);
	public ArrayList<ArticuloReservadoVO> getArtsReservados(int codSolDis);
	public void guardarSolEnv(SolicitudEnvioVO solEnvio);
	public void actualizarStock(ArrayList<ArticuloAEnviarVO> articulosAEnviar, ArrayList<ArticuloReservadoVO> articulosReservados);
	public void actArtsRes(ArrayList<ArticuloReservadoVO> articulosReservados);
	public void actualizarSolDis(SolicitudDistribucionVO solDis);
	public ArrayList<TiendaVO> getTiendas();
	public ArrayList<ArticuloAEnviarVO> getArtsAEnv(int codSolDis);
	public int getNumeroSolEnv();
	
	
	//panel gen sol fab
	public ArrayList<ArticuloAFabricarVO> getArticulosAFabricar();
	public int getNumeroSolFab();
	public void guardarSolFab(SolicitudFabricaVO solFab);
	
	
	//panel reposicion
	public boolean existeSolRep(int numero);//Nuevo hay que hacer
	public int getNextIdArticuloAReponer();
	public ArrayList<ArticuloHeaderVO> getArticulos(ArrayList<Long> codigos);
	public SolicitudFabricaVO cargarSolicitudFabricacion(int codigoSolFab);
	public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO);
	public void actualizarSolFab(SolicitudFabricaVO solFabVO);
	public void actualizarStock(ArrayList<ArticuloAReponerVO> arts);
	
	//panel new art
	public boolean existeArticulo(long codigo);//Nuevo hay que hacer
	public void guardarArticuloHogar(ArticuloHogarVO a) throws ExistingProductException;
	public void guardarArticuloRopa(ArticuloRopaVO a) throws ExistingProductException;
	public boolean existeSolFab(int numSolFab);
	public ArrayList<SolicitudDistribucionVO> getAllSolicitudesDistribucion();
	
}
