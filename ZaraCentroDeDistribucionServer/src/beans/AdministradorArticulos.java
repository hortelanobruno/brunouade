package beans;

import java.util.Vector;
import javax.ejb.Remote;

import Exceptions.ExistingProductException;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;

@Remote
public interface AdministradorArticulos 
{
	public void guardarArticuloHogar(ArticuloHogarVO a) throws ExistingProductException;
	public void guardarArticuloRopa(ArticuloRopaVO a) throws ExistingProductException;
	public Vector<String> getDescripciones(Vector<Long> cods);
	public Vector<Integer> getStocks(Vector<Long> codigos);
	public void actualizarStock(Vector<ArticuloHeaderVO> arts);
	public void guardarDatosCD(int codigo,String nombre,float longitud, float latitud, Vector<String> lineasRopa, Vector<String> categoriasHogar);
}
