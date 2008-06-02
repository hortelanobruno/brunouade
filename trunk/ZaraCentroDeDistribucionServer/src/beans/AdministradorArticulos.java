package beans;

import java.util.Vector;
import javax.ejb.Remote;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;

@Remote
public interface AdministradorArticulos 
{
	public void guardarArticuloHogar(ArticuloHogarVO a);
	public void guardarArticuloRopa(ArticuloRopaVO a);
	public Vector<String> getDescripciones(Vector<Long> cods);
	public Vector<Integer> getStocks(Vector<Long> codigos);
	public void actualizarStock(Vector<ArticuloHeaderVO> arts);
	public void guardarDatosCD(int codigo,String nombre,double longitud, double latitud, Vector<String> lineasRopa, Vector<String> categoriasHogar);
}
