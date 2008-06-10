package Varios;

import java.util.Vector;

import VO.TiendaVO;

public class XMLSolDis 
{
	private int numero;
	private TiendaVO tienda;
	private Vector<XMLArticuloPedido> articulosPedidos;
	
	public Vector<XMLArticuloPedido> getArticulosPedidos() {
		return articulosPedidos;
	}
	public void setArticulosPedidos(Vector<XMLArticuloPedido> articulosPedidos) {
		this.articulosPedidos = articulosPedidos;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public TiendaVO getTienda() {
		return tienda;
	}
	public void setTienda(TiendaVO tienda) {
		this.tienda = tienda;
	}
}
