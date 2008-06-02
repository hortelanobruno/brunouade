package BusinessLogic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import VO.ArticuloHeaderVO;
import VO.SolicitudDistribucionVO;
import VO.TiendaVO;

@Entity
@DiscriminatorValue("fabricacion")
public class SolicitudDistribucion extends Solicitud implements Serializable
{
	private static final long serialVersionUID = 1694955054674640622L;
	private Tienda tienda;
    
	public SolicitudDistribucion() {
		super();
	}
	
	public SolicitudDistribucion(int n, Collection<Articulo> a, Date f, Tienda t){
		super(n,f);
		this.tienda = t;
		this.setArticulos(a);
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
		Iterator a = (Iterator) this.getArticulos().iterator();
		
		while(a.hasNext())
			articulos.add((ArticuloHeaderVO)a.next());	
		SolicitudDistribucionVO vo = new SolicitudDistribucionVO(this.getNumero(),articulos,this.getFechaEmision(),new TiendaVO(tienda.getCodTienda(),tienda.getNombreTienda()));
		return vo;
	}

	public void setVO(SolicitudDistribucionVO vo){
		Collection<Articulo> articulos = new Vector<Articulo>();
		
		Iterator a = (Iterator)vo.getArticulo().iterator();
		while(a.hasNext())
			articulos.add((Articulo)a.next());
		
		this.setNumero(vo.getNumero());
		this.setArticulos(articulos);
		this.setFechaEmision(vo.getFechaEmision());
		this.setTienda(new Tienda(vo.getTienda().getCodigoTienda(),vo.getTienda().getNombreTienda()));
	}
}
