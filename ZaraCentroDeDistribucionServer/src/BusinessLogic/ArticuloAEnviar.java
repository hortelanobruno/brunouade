package BusinessLogic;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ArticulosAEnviar")
public class ArticuloAEnviar implements Serializable
{
	private static final long serialVersionUID = 7304748378651802875L;
	private int idAAE;
	private Articulo art;
	private int cantidadAEnviar;
	private SolicitudDistribucion solDis;
	private SolicitudDeFabricacion solFab;
	
	public ArticuloAEnviar()
	{
		
	}
	
	public ArticuloAEnviar(int id, Articulo art, SolicitudDistribucion sd, SolicitudDeFabricacion sf, int cant)
	{
		this.setIdAAE(id);
		this.setArt(art);
		this.setSolDis(sd);
		this.setSolFab(sf);
		this.setCantidadAEnviar(cant);
	}
	
	@ManyToOne
	public Articulo getArt() 
	{
		return art;
	}

	public void setArt(Articulo art) 
	{
		this.art = art;
	}

	@Id
	@Column
	public int getIdAAE() 
	{
		return idAAE;
	}

	public void setIdAAE(int idAAE) 
	{
		this.idAAE = idAAE;
	}

	@ManyToOne
	public SolicitudDistribucion getSolDis() 
	{
		return solDis;
	}

	public void setSolDis(SolicitudDistribucion solDis) 
	{
		this.solDis = solDis;
	}

	@ManyToOne
	public SolicitudDeFabricacion getSolFab() 
	{
		return solFab;
	}

	public void setSolFab(SolicitudDeFabricacion solFab) 
	{
		this.solFab = solFab;
	}

	@Column
	public int getCantidadAEnviar() {
		return cantidadAEnviar;
	}

	public void setCantidadAEnviar(int cantidadAEnviar) {
		this.cantidadAEnviar = cantidadAEnviar;
	}
}