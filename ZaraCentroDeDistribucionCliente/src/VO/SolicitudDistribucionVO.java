package VO;

import java.util.Collection;
import java.util.Date;

public class SolicitudDistribucionVO extends SolicitudVO
{
	private static final long serialVersionUID = -5633098382339627713L;
	private TiendaVO tienda;
	private Collection<ArticuloPedidoVO> articulosPedidos;
	private boolean cerrada;
	private int idDis;

	public SolicitudDistribucionVO() {
		
	}
	
	public SolicitudDistribucionVO(int id,int n, Collection<ArticuloPedidoVO> a, Date f, TiendaVO t, CentroDistribucionVO centro, boolean cerrada){
		super(id,f,centro);
		this.articulosPedidos = a;
		this.tienda = t;
		this.setCerrada(cerrada);
		this.idDis = n;
	}
	
	public TiendaVO getTienda() {
		return tienda;
	}

	public void setTienda(TiendaVO tienda) {
		this.tienda = tienda;
	}

	public Collection<ArticuloPedidoVO> getArticulosPedidos() {
		return articulosPedidos;
	}

	public void setArticulosPedidos(Collection<ArticuloPedidoVO> articulosPedidos) {
		this.articulosPedidos = articulosPedidos;
	}
	
    public boolean getCerrada() {
		return cerrada;
	}

	public void setCerrada(boolean cerrada) {
		this.cerrada = cerrada;
	}

	public int getIdDis() {
		return idDis;
	}

	public void setIdDis(int idDis) {
		this.idDis = idDis;
	}
}
