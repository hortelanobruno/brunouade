package VO;


public class ArticuloPedidoVO {

	private int idAP;
	private ArticuloHeaderVO art;
	private int cantidad;
	private SolicitudDistribucionVO sd;
	
	public ArticuloPedidoVO() {
		// TODO Auto-generated constructor stub
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

	public int getIdAP() {
		return idAP;
	}

	public void setIdAP(int idAP) {
		this.idAP = idAP;
	}

	public SolicitudDistribucionVO getSd() {
		return sd;
	}

	public void setSd(SolicitudDistribucionVO sd) {
		this.sd = sd;
	}
	
	
}
