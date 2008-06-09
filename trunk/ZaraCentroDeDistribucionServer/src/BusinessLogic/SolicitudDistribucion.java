package BusinessLogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import VO.ArticuloPedidoVO;
import VO.SolicitudDistribucionVO;
import VO.TiendaVO;

@Entity
@DiscriminatorValue("fabricacion")
public class SolicitudDistribucion extends Solicitud implements Serializable {
	
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
		Collection<ArticuloPedidoVO> articulos = new Vector<ArticuloPedidoVO>();
		Iterator a = (Iterator) this.getArticulosPedidos().iterator();

		while (a.hasNext())
			articulos.add((ArticuloPedidoVO) a.next());
		SolicitudDistribucionVO vo = new SolicitudDistribucionVO(this.getNumero(), articulos, this.getFechaEmision(), new TiendaVO(tienda.getCodTienda(), tienda.getNombreTienda()),this.getCentro().getVO());
		return vo;
	}

	public void setVO(SolicitudDistribucionVO vo) {
		Collection<ArticuloPedido> articulos = new ArrayList<ArticuloPedido>();

		Iterator a = (Iterator) vo.getArticulosPedidos().iterator();
		while (a.hasNext())
			articulos.add((ArticuloPedido) a.next());

		this.setNumero(vo.getNumero());
		this.setArticulosPedidos(articulos);
		this.setFechaEmision(vo.getFechaEmision());
		this.setTienda(new Tienda(vo.getTienda().getCodigoTienda(), vo
				.getTienda().getNombreTienda()));
	}
}
