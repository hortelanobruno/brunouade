package BusinessLogic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.persistence.Transient;

public class SolicitudEnvioATienda extends Solicitud implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5001930866418434765L;
	private Tienda tienda;

	public SolicitudEnvioATienda() {
		super();
	}
	
	public SolicitudEnvioATienda(int n, Collection<Articulo> a, Date f, Tienda t){
		super(n,a,f);
		this.tienda = t;
	}
	
	
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
		this.setArticulo(articulos);
		this.setFecha(vo.getFechaEmision());
		this.setTienda(new Tienda(vo.getTienda().getCodigoTienda(),vo.getTienda().getNombreTienda()));
	}
}
