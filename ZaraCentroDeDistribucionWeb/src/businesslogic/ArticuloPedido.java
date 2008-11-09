package businesslogic;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	private int cantidad;
	
	@ManyToOne
	public Articulo getArt() {
		return art;
	}
	
	public void setArt(Articulo art) {
		this.art = art;
	}
	
	@Column
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
		art.setCantidad(this.getCantidad());
		art.setArt(this.getArt().getVO());
		art.setTienda(this.getTienda().getVO());
		return art;
	}
	
	public void setVO(ArticuloPedidoVO art){
		this.setIdAP(art.getIdAP());
		this.setCantidad(art.getCantidad());
		Articulo art2 = new Articulo();
		art2.setVO(art.getArt());
		this.setArt(art2);
		Tienda tienda = new Tienda();
		tienda.setVO(art.getTienda());
		this.setTienda(tienda);
	}

	
}
