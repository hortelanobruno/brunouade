package beans;

import java.util.Vector;

import javax.ejb.Remote;

import BusinessLogic.*;

@Remote
public interface AdministradorArticulos 
{
	public void agregarArticuloHogar(ArticuloHogarVO art);
	public void agregarArticuloRopa(ArticuloRopaVO art);
	public Vector<String> getDescripciones(Vector<Integer> cods);
	public Vector<Integer> getStocks(Vector<Integer> codigos);
	public void actualizarStock(Vector<ArticuloHeaderVO> arts);
}
