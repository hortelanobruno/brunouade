package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import javax.ejb.Remote;

import Exceptions.ExistingProductException;
import VO.ArticuloAEnviarVO;
import VO.ArticuloAFabricarVO;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import VO.TiendaVO;

@Remote
public interface AdministradorArticulos 
{
	
	public void guardarArticuloHogar(ArticuloHogarVO a) throws ExistingProductException;
	public void guardarArticuloRopa(ArticuloRopaVO a) throws ExistingProductException;
	public ArrayList<String> getDescripciones(ArrayList<Long> cods);
	public ArrayList<Integer> getStocks(ArrayList<Long> codigos);
	public void actualizarStock(Vector<ArticuloHeaderVO> arts);
	public ArticuloHeaderVO getArticulo(long codigo);
	public void guardarArticulosAFabricar(Collection<ArticuloAFabricarVO> artic2);
	public void guardarArticulosAEnviar(Collection<ArticuloAEnviarVO> artic2);
	public Vector<TiendaVO> getTiendas();
}
