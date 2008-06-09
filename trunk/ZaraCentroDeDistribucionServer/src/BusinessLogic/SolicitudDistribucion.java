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
import VO.ArticuloPedidoVO;
import VO.SolicitudDistribucionVO;

@Entity
@DiscriminatorValue("fabricacion")
public class SolicitudDistribucion extends Solicitud implements Serializable 
{
	
	private static final long serialVersionUID = 1694955054674640622L;
	private Tienda tienda;
	private Collection<ArticuloPedido> articulosPedidos;

	public SolicitudDistribucion() {
		super();
	}

	public SolicitudDistribucion(int n, Date f, Tienda t, Collection<ArticuloPedido> a) {
		super(n, f);
		this.tienda = t;
		this.setArticulosPedidos(a);
	}

	@ManyToOne
	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public Collection<ArticuloPedido> getArticulosPedidos() {
		return articulosPedidos;
	}

	public void setArticulosPedidos(Collection<ArticuloPedido> articulosPedidos) {
		this.articulosPedidos = articulosPedidos;
	}

	@Transient
	public SolicitudDistribucionVO getVO() {
		SolicitudDistribucionVO sol = new SolicitudDistribucionVO();
		sol.setNumero(this.getNumero());
		sol.setFechaEmision(this.getFechaEmision());
		sol.setTienda(this.getTienda().getVO());
		sol.setCdVO(this.getCentro().getVO());
		Collection<ArticuloPedidoVO> arts = new ArrayList<ArticuloPedidoVO>();
		Iterator it = (Iterator) this.getArticulosPedidos().iterator();
		while(it.hasNext()){
			ArticuloPedidoVO art = ((ArticuloPedido)it.next()).getVO();
			arts.add(art);
		}
		sol.setArticulosPedidos(arts);
		return sol;
	}

	public void setVO(SolicitudDistribucionVO vo) {
		this.setFechaEmision(vo.getFechaEmision());
		this.setNumero(vo.getNumero());
		Tienda tienda = new Tienda();
		tienda.setVO(vo.getTienda());
		this.setTienda(tienda);
		CentroDistribucion centro = new CentroDistribucion();
		centro.setVO(vo.getCdVO());
		this.setCentro(centro);
		Collection<ArticuloPedido> arts = new ArrayList<ArticuloPedido>();
		Iterator it = (Iterator) vo.getArticulosPedidos().iterator();
		while(it.hasNext()){
			ArticuloPedido art = new ArticuloPedido();
			art.setVO(((ArticuloPedidoVO)it.next()));
			arts.add(art);
		}
		this.setArticulosPedidos(arts);
	}
}
