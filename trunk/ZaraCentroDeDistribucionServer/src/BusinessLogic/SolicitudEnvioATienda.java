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
import VO.SolicitudEnvioVO;
import VO.TiendaVO;

@Entity
@DiscriminatorValue("envio")
public class SolicitudEnvioATienda extends Solicitud implements Serializable
{
	private static final long serialVersionUID = 5001930866418434765L;
	private Tienda tienda;

	public SolicitudEnvioATienda() {
		super();
	}
	
	public SolicitudEnvioATienda(int n, Collection<Articulo> a, Date f, Tienda t){
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
	public SolicitudEnvioVO getVO(){
		Collection<ArticuloHeaderVO> articulos = new Vector<ArticuloHeaderVO>();
		Iterator a = (Iterator) this.getArticulos().iterator();
		
		while(a.hasNext())
			articulos.add((ArticuloHeaderVO)a.next());	
		SolicitudEnvioVO vo = new SolicitudEnvioVO(this.getNumero(),articulos,this.getFechaEmision(),new TiendaVO(tienda.getCodTienda(),tienda.getNombreTienda()));
		return vo;
	}

	public void setVO(SolicitudEnvioVO vo){
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
