package beans;

import java.util.Vector;

import javax.ejb.Remote;

import BusinessLogic.*;

@Remote
public interface AdministradorArticulos 
{
	public void agregarArticuloHogar(ArticuloHogarVO art);
	public void agregarArticuloRopa(ArticuloRopaVO art);
	public Vector<String> getDescripciones(Vector<Long> cods);
	public Vector<Integer> getStocks(Vector<Long> codigos);
	public void actualizarStock(Vector<ArticuloHeaderVO> arts);
}
