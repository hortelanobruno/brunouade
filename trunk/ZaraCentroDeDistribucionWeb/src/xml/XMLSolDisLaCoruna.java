package xml;

import java.util.Vector;

import xmlVOSolDisLaCoruna.itemSolDis;

public class XMLSolDisLaCoruna 
{
	private String fecha;
	private Vector<itemSolDis> items;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Vector<itemSolDis> getItems() {
		return items;
	}
	public void setItems(Vector<itemSolDis> items) {
		this.items = items;
	}
	
}
