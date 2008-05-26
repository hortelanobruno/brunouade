package BusinessLogic;

import java.io.Serializable;

public class ArticuloHeaderVO implements Serializable
{	
	private static final long serialVersionUID = -4113593238894788284L;
	private String descripcion;
	private long codigo;
	private int cantidad;
	
	public ArticuloHeaderVO(String desc, long codigo, int cantidad) {
		
		this.descripcion = desc;
		this.codigo = codigo;
		this.cantidad = cantidad;
	}
	
	public ArticuloHeaderVO() {
	
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
}
