package VO;

import java.io.Serializable;


public class ArticuloAReponerVO implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idAAR;
	private ArticuloHeaderVO art;
	private int cantidad;
	private SolicitudDeReposicionVO sr;
	
	public ArticuloAReponerVO() 
	{
	
	}
	
	public ArticuloAReponerVO(int id, ArticuloHeaderVO art, int cant, SolicitudDeReposicionVO sol) 
	{
		this.setIdAAR(id);
		this.setArt(art);
		this.setCantidad(cant);
		this.setSr(sol);
	}
	
	public ArticuloHeaderVO getArt() {
		return art;
	}
	public void setArt(ArticuloHeaderVO art) {
		this.art = art;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getIdAAR() {
		return idAAR;
	}
	public void setIdAAR(int idAAR) {
		this.idAAR = idAAR;
	}
	public SolicitudDeReposicionVO getSr() {
		return sr;
	}
	public void setSr(SolicitudDeReposicionVO sr) {
		this.sr = sr;
	}
}
