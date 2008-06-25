package VO;

import java.io.Serializable;

public class ArticuloReservadoVO implements Serializable{

	private static final long serialVersionUID = -8350410512051505416L;
	private int idAR;
	private ArticuloHeaderVO art;
	private int cantidadReservada;
	private SolicitudDistribucionVO solDis;
	
	public ArticuloReservadoVO() {
	
	}
	
	public ArticuloReservadoVO(int id, ArticuloHeaderVO art, int cant, SolicitudDistribucionVO sol) {
		this.setArt(art);
		this.setCantidadReservada(cant);
		this.setIdAR(id);
		this.setSolDis(sol);
	}
	
	public ArticuloHeaderVO getArt() {
		return art;
	}
	public void setArt(ArticuloHeaderVO art) {
		this.art = art;
	}
	public int getCantidadReservada() {
		return cantidadReservada;
	}
	public void setCantidadReservada(int cantidadReservada) {
		this.cantidadReservada = cantidadReservada;
	}
	public int getIdAR() {
		return idAR;
	}
	public void setIdAR(int idAR) {
		this.idAR = idAR;
	}
	public SolicitudDistribucionVO getSolDis() {
		return solDis;
	}
	public void setSolDis(SolicitudDistribucionVO solDis) {
		this.solDis = solDis;
	}
}
