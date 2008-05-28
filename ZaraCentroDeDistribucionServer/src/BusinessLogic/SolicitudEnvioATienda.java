package BusinessLogic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("envio")
public class SolicitudEnvioATienda extends Solicitud implements Serializable
{
	private static final long serialVersionUID = 5001930866418434765L;
	private Tienda tienda;
	private Collection<Articulo> articulos;

	public SolicitudEnvioATienda() {
		super();
		articulos = new Vector<Articulo>();
	}
	
	public SolicitudEnvioATienda(int n, Collection<Articulo> a, Date f, Tienda t){
		super(n,f);
		this.tienda = t;
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
	public SolicitudEnvioVO getVO(){
		Collection<ArticuloHeaderVO> articulos = new Vector<ArticuloHeaderVO>();
		/*	for(int i = 0; i< this.getArticulo().size();i++)
			articulos.add(new ArticuloHeaderVO(this.getArticulo())*/
		SolicitudEnvioVO vo = new SolicitudEnvioVO(numero,articulos,fechaEmision,new TiendaVO(tienda.getCodTienda(),tienda.getNombreTienda()));
		return vo;
	}

	public void setVO(SolicitudEnvioVO vo){
		Collection<Articulo> articulos = new Vector<Articulo>();
		/*	for(int i = 0; i< this.getArticulo().size();i++)
			articulos.add(new ArticuloHeaderVO(this.getArticulo())*/
		
		
		this.setNumero(vo.getNumero());
		this.setArticulos(articulos);
		this.setFecha(vo.getFechaEmision());
		this.setTienda(new Tienda(vo.getTienda().getCodigoTienda(),vo.getTienda().getNombreTienda()));
	}

	public Collection<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(Collection<Articulo> articulos) {
		this.articulos = articulos;
	}
}
