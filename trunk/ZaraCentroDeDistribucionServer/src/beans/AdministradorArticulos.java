package beans;

import java.util.Vector;
import javax.ejb.Remote;
import BusinessLogic.*;

@Remote
public interface AdministradorArticulos 
{
	public void guardarArticuloHogar(ArticuloHogarVO a);
	public void guardarArticuloRopa(ArticuloRopaVO a);
	public Vector<String> getDescripciones(Vector<Long> cods);
	public Vector<Integer> getStocks(Vector<Long> codigos);
	public void actualizarStock(Vector<ArticuloHeaderVO> arts);
}
