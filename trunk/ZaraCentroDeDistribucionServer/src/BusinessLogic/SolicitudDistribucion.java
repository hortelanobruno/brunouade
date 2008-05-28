package BusinessLogic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("fabricacion")
public class SolicitudDistribucion extends Solicitud implements Serializable
{
	private static final long serialVersionUID = 1694955054674640622L;
	private Tienda tienda;
	private Collection<Articulo> articulos;
    
	public SolicitudDistribucion() {
		super();
		this.articulos =new Vector<Articulo>();
	}
	
	public SolicitudDistribucion(int n, Collection<Articulo> a, Date f, Tienda t){
		super(n,f);
		this.tienda = t;
		this.articulos = a;
	}
	
	
	@ManyToOne
	public Tienda getTienda() 
	{
		return tienda;
	}

	public void setTienda(Tienda tienda) 
	{
		this.tienda = tienda;
	}
	
	@Transient
	public SolicitudDistribucionVO getVO(){
		Collection<ArticuloHeaderVO> articulos = new Vector<ArticuloHeaderVO>();
		/*	for(int i = 0; i< this.getArticulo().size();i++)
			articulos.add(new ArticuloHeaderVO(this.getArticulo())*/
		SolicitudDistribucionVO vo = new SolicitudDistribucionVO(this.getNumero(),articulos,this.getFechaEmision(),new TiendaVO(tienda.getCodTienda(),tienda.getNombreTienda()));
		return vo;
	}

	public void setVO(SolicitudDistribucionVO vo){
		Collection<Articulo> articulos = new Vector<Articulo>();
		/*	for(int i = 0; i< this.getArticulo().size();i++)
			articulos.add(new ArticuloHeaderVO(this.getArticulo())*/
		
		
		this.setNumero(vo.getNumero());
		this.setArticulos(articulos);
		this.setFechaEmision(vo.getFechaEmision());
		this.setTienda(new Tienda(vo.getTienda().getCodigoTienda(),vo.getTienda().getNombreTienda()));
	}

	@OneToMany
	public Collection<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(Collection<Articulo> articulos) {
		this.articulos = articulos;
	}
}
