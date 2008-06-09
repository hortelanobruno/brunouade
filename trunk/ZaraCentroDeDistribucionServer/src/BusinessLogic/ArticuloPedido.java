package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import VO.ArticuloPedidoVO;

@Entity
@Table(name="ArticulosPedidos")
public class ArticuloPedido 
{
	private int idAP;
	private Articulo art;
	private int cantidad;
	private SolicitudDistribucion sd;
	
	
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
	
	@ManyToOne
	public SolicitudDistribucion getSd() {
		return sd;
	}
	
	public void setSd(SolicitudDistribucion sd) {
		this.sd = sd;
	}	
	
	@Transient
	public ArticuloPedidoVO getVO(){
		ArticuloPedidoVO art = new ArticuloPedidoVO();
		art.setIdAP(this.getIdAP());
		art.setCantidad(this.getCantidad());
		art.setArt(this.getArt().getVO());
		art.setSd(this.getSd().getVO());
		return art;
	}
	
	public void setVO(ArticuloPedidoVO art){
		this.setIdAP(art.getIdAP());
		this.setCantidad(art.getCantidad());
		Articulo art2 = new Articulo();
		art2.setVO(art.getArt());
		this.setArt(art2);
		SolicitudDistribucion sd = new SolicitudDistribucion();
		sd.setVO(art.getSd());
		this.setSd(sd);
	}
	
}
