package beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Local;

import vo.ArticuloAEnviarVO;
import vo.ArticuloAFabricarVO;
import vo.ArticuloAReponerVO;
import vo.ArticuloHeaderVO;
import vo.ArticuloHogarVO;
import vo.ArticuloReservadoVO;
import vo.ArticuloRopaVO;
import exceptions.ExistingProductException;


@Local
public interface AdministradorArticulos 
{

	public int getNextIdArticuloPedido();
	public int getNextIdArticuloAEnviar();
	public int getNextIdArticuloAFabricar();
	public int getNextIdArticuloAReponer();
	public int getNextIdArticuloReservado();
	public void guardarArticuloHogar(ArticuloHogarVO a) throws ExistingProductException;
	public void guardarArticuloRopa(ArticuloRopaVO a) throws ExistingProductException;
	public ArrayList<String> getDescripciones(ArrayList<Long> cods);
	public ArrayList<Integer> getStocks(ArrayList<Long> codigos);
	public void actualizarStock(ArrayList<ArticuloAReponerVO> arts);
	public ArticuloHeaderVO getArticulo(long codigo);
	public void guardarArticulosAFabricar(Collection<ArticuloAFabricarVO> artic2);
	public void guardarArticulosAEnviar(Collection<ArticuloAEnviarVO> artic2);
	public void modificarStock(Collection<ArticuloReservadoVO> artiAEnv);
	public ArrayList<ArticuloAFabricarVO> getArticulosAFabricar();
	public boolean existeArticulo(long codigo);
	public void guardarArticulosReservados(Collection<ArticuloReservadoVO> artiReser);
	public ArrayList<ArticuloHeaderVO> getArticulos(ArrayList<Long> codigos);
	public ArrayList<ArticuloReservadoVO> getArtsReservados(int codSolDis);
	public void actArtsRes(ArrayList<ArticuloReservadoVO> articulosReservados);
	public void actualizarStock(ArrayList<ArticuloAEnviarVO> articulosAEnviar, ArrayList<ArticuloReservadoVO> articulosReservados);
	public ArrayList<ArticuloAEnviarVO> getArtsAEnv(int codSolDis);
	public ArrayList<Long> existenArts(ArrayList<Long> codigos);
	public void guardarArticuloFromJMS(String msg);
	public int getStockArticulo(long codigo);
}
