package BusinessLogic;

import java.io.Serializable;

public class ArticuloHeaderVO implements Serializable{
	
	private String descripcion;
	private String referencia;
	private int stock;
	
	public ArticuloHeaderVO(String d, String r, int s) {
		
		this.descripcion = d;
		this.referencia = r;
		this.stock = s;
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

}
