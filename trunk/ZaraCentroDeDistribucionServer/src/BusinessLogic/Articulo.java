package BusinessLogic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

public abstract class Articulo implements Serializable
{
	private String referencia;
	private String descripcion;
	private float precio;
	private int codigo;
	private String seccion;
	private String color;
	private String linea;
    private int cantidad;
    
    public Articulo(int codigo, int cantidad){
        this.cantidad=cantidad;
        this.codigo=codigo;
    }
    
    public Articulo(){
        
    }
	
	public String getColor() 
	{
		return color;
	}

	public void setColor(String color) 
	{
		this.color = color;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	public float getPrecio() 
	{
		return precio;
	}
	
	public void setPrecio(float precio)
{
		this.precio = precio;
	}
	
	public String getReferencia() 
	{
		return referencia;
	}
	
	public void setReferencia(String referencia) 
	{
		this.referencia = referencia;
	}
	
	public int getCodigo()
	{
		return codigo;
	}
	
	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}

	public String getSeccion()
	{
		return seccion;
	}

	public void setSeccion(String seccion) 
	{
		this.seccion = seccion;
	}

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}
	
	@Transient
	public ArticuloHeaderVO getVO(){
		ArticuloHeaderVO vo = new ArticuloHeaderVO(referencia,descripcion,cantidad);
		return vo;
	}

	public void setVO(ArticuloHeaderVO vo){
		this.setReferencia(vo.getReferencia());
		this.setDescripcion(vo.getDescripcion());
		this.setCantidad(vo.getCantidad());
	}
	
	
}
