package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import vo.ArticuloAEnviarVO;
import vo.ArticuloAFabricarVO;
import vo.ArticuloAReponerVO;
import vo.ArticuloHeaderVO;
import vo.ArticuloHogarVO;
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
	public void actualizarStock(List<ArticuloAReponerVO> arts);
	public ArticuloHeaderVO getArticulo(long codigo);
	public void guardarArticulosAFabricar(Collection<ArticuloAFabricarVO> artic2);
	public void guardarArticulosAEnviar(Collection<ArticuloAEnviarVO> artic2);
	public ArrayList<ArticuloAFabricarVO> getArticulosAFabricar();
	public boolean existeArticulo(long codigo);
	public ArrayList<ArticuloHeaderVO> getArticulos(ArrayList<Long> codigos);
	public ArrayList<Long> existenArts(ArrayList<Long> codigos);
	public int getStockArticulo(long codigo);
	public HashMap<Long, Integer> getStocks();
	public void actualizarStock(HashMap<Long, Integer> stocks);
}
