package BusinessLogic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import server.Constantes;

@Entity
@Table(name="Articulos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminador",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("articulo")
public class Articulo implements Serializable
{
	private static final long serialVersionUID = 2235551485752041708L;
	private long codigo;
	private String descripcion;
	private float precio;
	private String seccion;
	private String color;
	private String linea;
    private int cantidad;
    
    public Articulo(long codigo, int cantidad){
        this.cantidad=cantidad;
        this.codigo=codigo;
    }
    
    public Articulo(){
        
    }
	
    @Column
	public String getColor() 
	{
		return color;
	}

	public void setColor(String color) 
	{
		this.color = color;
	}

	@Column
	public String getDescripcion() 
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	@Column
	public float getPrecio() 
	{
		return precio;
	}
	
	public void setPrecio(float precio)
{
		this.precio = precio;
	}
	
	@Column
	public String getSeccion()
	{
		return seccion;
	}

	public void setSeccion(String seccion) 
	{
		this.seccion = seccion;
	}

	@Column
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Column
	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}
	
	@Transient
	public ArticuloHeaderVO getVO()
	{
		ArticuloHeaderVO vo = new ArticuloHeaderVO(descripcion,codigo,cantidad,color,precio,linea,seccion);
		return vo;
	}

	public void setVO(ArticuloHeaderVO vo){
		this.setCodigo(vo.getCodigo());
		this.setDescripcion(vo.getDescripcion());
		this.setCantidad(vo.getCantidad());
		this.setColor(vo.getColor());
		this.setLinea(vo.getLinea());
		this.setPrecio(vo.getPrecio());
		this.setSeccion(vo.getSeccion());
	}

	@Id
	@Column
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
}
