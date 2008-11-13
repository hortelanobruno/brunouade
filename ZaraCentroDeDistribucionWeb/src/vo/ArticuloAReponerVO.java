package vo;

import java.io.Serializable;


public class ArticuloAReponerVO implements Serializable 
{
	private static final long serialVersionUID = -6231271589298853218L;
	private int idAAR;
	private ArticuloHeaderVO art;
	private int cantidadRecibida;
	
	public ArticuloAReponerVO() 
	{
	
	}
	
	public ArticuloAReponerVO(int id, ArticuloHeaderVO art, int cant) 
	{
		this.setIdAAR(id);
		this.setArt(art);
		this.setCantidadRecibida(cant);
	}
	
	public ArticuloHeaderVO getArt() {
		return art;
	}
	public void setArt(ArticuloHeaderVO art) {
		this.art = art;
	}
	public int getCantidadRecibida() {
		return cantidadRecibida;
	}
	public void setCantidadRecibida(int cantidad) {
		this.cantidadRecibida = cantidad;
	}
	public int getIdAAR() {
		return idAAR;
	}
	public void setIdAAR(int idAAR) {
		this.idAAR = idAAR;
	}
}
