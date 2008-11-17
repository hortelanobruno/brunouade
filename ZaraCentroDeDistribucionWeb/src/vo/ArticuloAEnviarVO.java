package vo;

import java.io.Serializable;

public class ArticuloAEnviarVO implements Serializable
{
	private static final long serialVersionUID = 1856595699966063794L;
	private int idAAE;
	private ArticuloHeaderVO art;
	private int cantidadAEnviar;
	private int idPedido; //Dato que necesita Gaby
	
	public int getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}


	public ArticuloAEnviarVO() {

	}
	
	
	public ArticuloAEnviarVO(int id, ArticuloHeaderVO art, int cantE)
	{
		this.setIdAAE(id);
		this.setArt(art);
		this.setCantidadAEnviar(cantE);
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

}
