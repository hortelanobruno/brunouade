package VO;

import java.io.Serializable;


public class ArticuloPedidoVO implements Serializable 
{
	private static final long serialVersionUID = 8841838453399640020L;
	private int idAP;
	private ArticuloHeaderVO art;
	private int cantidad;
	
	public ArticuloPedidoVO() {

	}

	public ArticuloPedidoVO(int id, ArticuloHeaderVO art, int cant)
	{
		this.setIdAP(id);
		this.setArt(art);
		this.setCantidad(cant);
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

}
