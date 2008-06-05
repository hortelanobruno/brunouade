package BusinessLogic;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ArticulosPendientes")
public class ArticuloPendiente 
{
	private PKArtPendiente id;
	private Solicitud sol;
	private Articulo art;
	private int cantidad;
	
	public ArticuloPendiente()
	{
		
	}
	
	public ArticuloPendiente(Solicitud sol, Articulo art, int cant)
	{
		this.setArt(art);
		this.setCantidad(cant);
		this.setSol(sol);
		this.setId(new PKArtPendiente(art.getCodigo(),sol.getNumero()));
	}
	
	public Articulo getArt() {
		return art;
	}
	public void setArt(Articulo art) {
		this.art = art;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Solicitud getSol() {
		return sol;
	}
	public void setSol(Solicitud sol) {
		this.sol = sol;
	}

	@EmbeddedId
	public PKArtPendiente getId() {
		return id;
	}

	public void setId(PKArtPendiente id) {
		this.id = id;
	}
}
