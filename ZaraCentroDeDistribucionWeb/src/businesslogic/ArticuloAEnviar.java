package businesslogic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import vo.ArticuloAEnviarVO;

@Entity
@Table(name="ArticulosAEnviar")
public class ArticuloAEnviar implements Serializable
{
	private static final long serialVersionUID = 7304748378651802875L;
	private int idAAE;
	private Articulo art;
	private int cantidadAEnviar;
	private int idPedido; //Dato que necesita Gaby
	
	@Column
	public int getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	
	public ArticuloAEnviar()
	{
		
	}
	
	public ArticuloAEnviar(int id, Articulo art, int cant)
	{
		this.setIdAAE(id);
		this.setArt(art);
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

	@Column
	public int getCantidadAEnviar() {
		return cantidadAEnviar;
	}

	public void setCantidadAEnviar(int cantidadAEnviar) {
		this.cantidadAEnviar = cantidadAEnviar;
	}
	
	@Transient
	public ArticuloAEnviarVO getVO(){
		ArticuloAEnviarVO art = new ArticuloAEnviarVO();
		art.setIdAAE(this.getIdAAE());
		art.setCantidadAEnviar(this.getCantidadAEnviar());
		art.setArt(this.getArt().getVO());
		art.setIdPedido(this.getIdPedido());
		return art;
	}
	
	public void setVO(ArticuloAEnviarVO art){
		this.setIdAAE(art.getIdAAE());
		this.setCantidadAEnviar(art.getCantidadAEnviar());
		this.setIdPedido(art.getIdPedido());
		Articulo art2 = new Articulo();
		art2.setVO(art.getArt());
		this.setArt(art2);
	}
	
}