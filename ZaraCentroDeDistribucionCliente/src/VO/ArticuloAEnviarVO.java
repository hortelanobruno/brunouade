package VO;

import java.io.Serializable;

public class ArticuloAEnviarVO implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int idAAE;
	private ArticuloHeaderVO art;
	private int cantidadAEnviar;
	private SolicitudDistribucionVO solDis;
	
	public ArticuloAEnviarVO() {

	}
	
	public ArticuloAEnviarVO(int id, ArticuloHeaderVO art, int cantE, SolicitudDistribucionVO sol)
	{
		this.setIdAAE(id);
		this.setArt(art);
		this.setCantidadAEnviar(cantE);
		this.setSolDis(sol);
	}

	
	public ArticuloHeaderVO getArt() {
		return art;
	}
	public void setArt(ArticuloHeaderVO art) {
		this.art = art;
	}
	public int getCantidadAEnviar() {
		return cantidadAEnviar;
	}
	public void setCantidadAEnviar(int cantidadAEnviar) {
		this.cantidadAEnviar = cantidadAEnviar;
	}
	public int getIdAAE() {
		return idAAE;
	}
	public void setIdAAE(int idAAE) {
		this.idAAE = idAAE;
	}
	public SolicitudDistribucionVO getSolDis() {
		return solDis;
	}
	public void setSolDis(SolicitudDistribucionVO solDis) {
		this.solDis = solDis;
	}
}
