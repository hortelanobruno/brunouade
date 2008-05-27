package BusinessLogic;

import java.io.Serializable;

public class ArticuloHeaderVO implements Serializable
{	
	private static final long serialVersionUID = -4113593238894788284L;
	private String descripcion;
	private long codigo;
	private int cantidad;
	private String linea;
	private float precio;
	private String seccion;
	private String color;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

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

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
}
