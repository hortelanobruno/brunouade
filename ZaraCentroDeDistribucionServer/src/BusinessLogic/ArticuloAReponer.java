package BusinessLogic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ArticulosAReponer")
public class ArticuloAReponer implements Serializable 
{
	private static final long serialVersionUID = 1409325381648482564L;
	private int idAAR;
	private Articulo art;
	private int cantidad;
	private SolicitudReposicion sr;
	
	public ArticuloAReponer() {
		// TODO Auto-generated constructor stub
	}
	
	public ArticuloAReponer(int id, Articulo art, int cant, SolicitudReposicion sr)
	{
		this.setIdAAR(id);
		this.setArt(art);
		this.setCantidad(cant);
		this.setSr(sr);
	}
	
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
	public int getIdAAR() {
		return idAAR;
	}
	
	public void setIdAAR(int idAAR) {
		this.idAAR = idAAR;
	}
	
	@ManyToOne
	public SolicitudReposicion getSr() {
		return sr;
	}
	
	public void setSr(SolicitudReposicion sr) {
		this.sr = sr;
	}
	
}
