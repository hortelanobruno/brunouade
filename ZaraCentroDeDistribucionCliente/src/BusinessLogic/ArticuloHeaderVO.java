package BusinessLogic;

import java.io.Serializable;

public class ArticuloHeaderVO implements Serializable{
	
	private String descripcion;
	private String referencia;
	private int cantidad;
	
	public ArticuloHeaderVO(String d, String r, int s) {
		
		this.descripcion = d;
		this.referencia = r;
		this.cantidad = s;
	}
	
	public ArticuloHeaderVO() {
	
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
