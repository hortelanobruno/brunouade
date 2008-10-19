package businesslogic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import vo.ArticuloAReponerVO;

@Entity
@Table(name="ArticulosAReponer")
public class ArticuloAReponer implements Serializable 
{
	
	private static final long serialVersionUID = 1409325381648482564L;
	private int idAAR;
	private Articulo art;
	private int cantidad;
	
	public ArticuloAReponer() {
		// TODO Auto-generated constructor stub
	}
	
	public ArticuloAReponer(int id, Articulo art, int cant)
	{
		this.setIdAAR(id);
		this.setArt(art);
		this.setCantidad(cant);
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
	
	
	@Transient
	public ArticuloAReponerVO getVO(){
		ArticuloAReponerVO art = new ArticuloAReponerVO();
		art.setIdAAR(this.getIdAAR());
		art.setCantidad(this.getCantidad());
		art.setArt(this.getArt().getVO());
		return art;
	}
	
	public void setVO(ArticuloAReponerVO art){
		this.setIdAAR(art.getIdAAR());
		this.setCantidad(art.getCantidad());
		Articulo art2 = new Articulo();
		art2.setVO(art.getArt());
		this.setArt(art2);
	}
}
