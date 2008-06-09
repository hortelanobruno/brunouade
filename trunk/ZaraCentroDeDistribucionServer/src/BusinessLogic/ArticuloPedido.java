package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
}
