package businesslogic;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import vo.ArticuloPedidoVO;

@Entity
@Table(name="ArticulosPedidos")
public class ArticuloPedido  implements Serializable
{
	private static final long serialVersionUID = -1411171933550730204L;
	private Tienda tienda;
	private int idAP;
	private Articulo art;
	private int cantidadPedida;
	private int cantidadEnviada;
	
	@Column
	public int getCantidadEnviada() {
		return cantidadEnviada;
	}

	public void setCantidadEnviada(int cantidadEnviada) {
		this.cantidadEnviada = cantidadEnviada;
	}

	@ManyToOne
	public Articulo getArt() {
		return art;
	}
	
	public void setArt(Articulo art) {
		this.art = art;
	}
	
	@Column
	public int getCantidadPedida() {
		return cantidadPedida;
	}
	
	public void setCantidadPedida(int cantidad) {
		this.cantidadPedida = cantidad;
	}
	
	@Id
	@Column
	public int getIdAP() {
		return idAP;
	}
	
	public void setIdAP(int idAP) {
		this.idAP = idAP;
	}

	@ManyToOne(cascade={CascadeType.MERGE})
	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public Tienda getTienda() {
		return tienda;
	}
	
	@Transient
	public ArticuloPedidoVO getVO(){
		ArticuloPedidoVO art = new ArticuloPedidoVO();
		art.setIdAP(this.getIdAP());
		art.setCantidadPedida(this.getCantidadPedida());
		art.setArt(this.getArt().getVO());
		art.setTienda(this.getTienda().getVO());
		art.setCantidadEnviada(this.getCantidadEnviada());
		return art;
	}
	
	public void setVO(ArticuloPedidoVO art){
		this.setIdAP(art.getIdAP());
		this.setCantidadPedida(art.getCantidadPedida());
		Articulo art2 = new Articulo();
		art2.setVO(art.getArt());
		this.setArt(art2);
		Tienda tienda = new Tienda();
		tienda.setVO(art.getTienda());
		this.setTienda(tienda);
		this.setCantidadEnviada(art.getCantidadEnviada());
	}

	
}
