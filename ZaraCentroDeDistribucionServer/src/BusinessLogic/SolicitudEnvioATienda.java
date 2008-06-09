package BusinessLogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import VO.ArticuloAEnviarVO;
import VO.SolicitudEnvioVO;
import VO.TiendaVO;

@Entity
@DiscriminatorValue("envio")
public class SolicitudEnvioATienda extends Solicitud implements Serializable
{
	private static final long serialVersionUID = 5001930866418434765L;
	private Tienda tienda;
	private Collection<ArticuloAEnviar> articulosAEnviar;

	public SolicitudEnvioATienda() {
		super();
	}
	
	public SolicitudEnvioATienda(int n, Collection<ArticuloAEnviar> a, Date f, Tienda t){
		super(n,f);
		this.tienda = t;
		this.setArticulosAEnviar(a);
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
		Collection<ArticuloAEnviarVO> articulos = new ArrayList<ArticuloAEnviarVO>();
		Iterator a = (Iterator) this.getArticulosAEnviar().iterator();
		
		while(a.hasNext())
			articulos.add((ArticuloAEnviarVO)a.next());	
		SolicitudEnvioVO vo = new SolicitudEnvioVO(this.getNumero(),articulos,this.getFechaEmision(),new TiendaVO(tienda.getCodTienda(),tienda.getNombreTienda()),this.getCentro().getVO());
		return vo;
	}

	public void setVO(SolicitudEnvioVO vo){
		Collection<ArticuloAEnviar> articulos = new ArrayList<ArticuloAEnviar>();
		Iterator a = (Iterator)vo.getArticulosAEnviar().iterator();
		while(a.hasNext())
			articulos.add((ArticuloAEnviar)a.next());
	
		this.setNumero(vo.getNumero());
		this.setArticulosAEnviar(articulos);
		this.setFechaEmision(vo.getFechaEmision());
		this.setTienda(new Tienda(vo.getTienda().getCodigoTienda(),vo.getTienda().getNombreTienda()));
	}

	public Collection<ArticuloAEnviar> getArticulosAEnviar() {
		return articulosAEnviar;
	}

	public void setArticulosAEnviar(Collection<ArticuloAEnviar> articulosAEnviar) {
		this.articulosAEnviar = articulosAEnviar;
	}
}
