package BusinessLogic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.persistence.Transient;

public abstract class Solicitud implements Serializable
{
	protected int numero;
	protected Collection<Articulo> articulo = new Vector<Articulo>();
    protected Date fechaEmision;
    
	public Solicitud() {
		// TODO Auto-generated constructor stub
	}
	
	public Solicitud(int n, Collection<Articulo> a, Date f){
		this.numero = n;
		this.articulo = a;
		this.fechaEmision = f;
	}
	public int getNumero() 
	{
		return numero;
	}
	
	public void setNumero(int numero) 
	{
		this.numero = numero;
	}

	public Collection<Articulo> getArticulo() {
		return articulo;
	}

	public void setArticulo(Collection<Articulo> articulo) {
		this.articulo = articulo;
	}

	public Date getFecha()
	{
		return fechaEmision;
	}

	public void setFecha(Date fecha)
	{
		this.fechaEmision = fecha;
	}
	
	@Transient
	public SolicitudVO getVO(){
		Collection<ArticuloHeaderVO> articulos = new Vector<ArticuloHeaderVO>();
		/*	for(int i = 0; i< this.getArticulo().size();i++)
			articulos.add(new ArticuloHeaderVO(this.getArticulo())*/
		SolicitudVO vo = new SolicitudVO(numero,articulos,fechaEmision);
		return vo;
	}

	public void setVO(SolicitudVO vo){
		Collection<Articulo> articulos = new Vector<Articulo>();
		/*	for(int i = 0; i< this.getArticulo().size();i++)
			articulos.add(new ArticuloHeaderVO(this.getArticulo())*/
		
		
		this.setNumero(vo.getNumero());
		this.setArticulo(articulos);
		this.setFecha(vo.getFechaEmision());
	}	
    
}
