package BusinessLogic;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ArticulosPendientes")
public class ArticuloPendiente implements Serializable
{
	private static final long serialVersionUID = 2677497498084042849L;
	//private PKArtPendiente id;
	private Articulo art;
	private Solicitud sol;

	public ArticuloPendiente()
	{
		
	}
	
	public ArticuloPendiente(Articulo art, Solicitud sol)
	{
		this.setArt(art);
		this.setSol(sol);
	//	this.setId(new PKArtPendiente(art.getCodigo(),sol.getNumero()));
	}
	
	@Transient
	public Solicitud getSol() 
	{
		return sol;
	}
	
	public void setSol(Solicitud sol) 
	{
		this.sol = sol;
	}

/*	@EmbeddedId
	public PKArtPendiente getId() 
	{
		return id;
	}

	public void setId(PKArtPendiente id) 
	{
		this.id = id;
	}*/

	@Transient
	public Articulo getArt() {
		return art;
	}

	public void setArt(Articulo art) {
		this.art = art;
	}
}
