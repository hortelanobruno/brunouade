package Varios;

import java.util.Vector;

import VO.TiendaVO;

public class XMLSolDis 
{
	private int numero;
	private TiendaVO tienda;
	private Vector<XMLArticuloPedido> articulospedidos;
	
	public Vector<XMLArticuloPedido> getArticulosPedidos() {
		return articulospedidos;
	}
	public void setArticulosPedidos(Vector<XMLArticuloPedido> articulosPedidos) {
		this.articulospedidos = articulosPedidos;
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
