package BusinessLogic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import VO.ArticuloAFabricarVO;

@Entity
@Table(name="ArticulosAFabricar")
public class ArticuloAFabricar implements Serializable
{
	private static final long serialVersionUID = 2677497498084042849L;
	private int idAAF;
	private Articulo art;
	private SolicitudDistribucion sol;
	private Fabrica fabrica;
	private int cantidadPedida;
	private int cantidadRecibida;
	private int cantidadAFabricar;



	public ArticuloAFabricar()
	{
		
	}
	
	public ArticuloAFabricar(int id, Articulo art, SolicitudDistribucion sol, Fabrica f, int cant)
	{
		this.setIdAAF(id);
		this.setArt(art);
		this.setSol(sol);
		this.setFabrica(f);
		this.setCantidadPedida(cant);
		this.setCantidadRecibida(0);
	}
	
	@ManyToOne
	public SolicitudDistribucion getSol() 
	{
		return sol;
	}
	
	public void setSol(SolicitudDistribucion sol) 
	{
		this.sol = sol;
	}

	@ManyToOne
	public Articulo getArt() {
		return art;
	}

	public void setArt(Articulo art) {
		this.art = art;
	}

	@Id
	@Column(name="id")
	public int getIdAAF() {
		return idAAF;
	}

	public void setIdAAF(int idAAF) {
		this.idAAF = idAAF;
	}

	@ManyToOne
	public Fabrica getFabrica() {
		return fabrica;
	}

	public void setFabrica(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	@Column
	public int getCantidadPedida() {
		return cantidadPedida;
	}

	public void setCantidadPedida(int cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}

	@Column
	public int getCantidadRecibida() {
		return cantidadRecibida;
	}

	public void setCantidadRecibida(int cantidadRecibida) {
		this.cantidadRecibida = cantidadRecibida;
	}

	@Column
	public int getCantidadAFabricar() {
		return cantidadAFabricar;
	}

	public void setCantidadAFabricar(int cantidadAFabricar) {
		this.cantidadAFabricar = cantidadAFabricar;
	}
	
	@Transient
	public ArticuloAFabricarVO getVO(){
		ArticuloAFabricarVO art = new ArticuloAFabricarVO();
		art.setIdAAF(this.getIdAAF());
		art.setCantidadPedida(this.getCantidadPedida());
		art.setCantidadRecibida(this.getCantidadRecibida());
		art.setFabrica(this.getFabrica().getVO());
		art.setSol(this.getSol().getVO());
		art.setArt(this.getArt().getVO());
		art.setCantidadAFabricar(this.getCantidadAFabricar());
		return art;
	}
	
	public void setVO(ArticuloAFabricarVO art){
		this.setIdAAF(art.getIdAAF());
		this.setCantidadPedida(art.getCantidadPedida());
		this.setCantidadRecibida(art.getCantidadRecibida());
		this.setCantidadAFabricar(art.getCantidadAFabricar());
		Fabrica fab = new Fabrica();
		fab.setVO(art.getFabrica());
		this.setFabrica(fab);
		Articulo art2 = new Articulo();
		art2.setVO(art.getArt());
		this.setArt(art2);
		SolicitudDistribucion sol = new SolicitudDistribucion();
		sol.setVO(art.getSol());
		this.setSol(sol);
	}
}
