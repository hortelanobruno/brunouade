package BusinessLogic;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import VO.ArticuloAEnviarVO;

@Entity
@Table(name="ArticulosAEnviar")
public class ArticuloAEnviar implements Serializable
{
	private static final long serialVersionUID = 7304748378651802875L;
	private int idAAE;
	private Articulo art;
	private int cantidadAEnviar;
	private SolicitudDistribucion solDis;
	
	public ArticuloAEnviar()
	{
		
	}
	
	public ArticuloAEnviar(int id, Articulo art, SolicitudDistribucion sd, int cant)
	{
		this.setIdAAE(id);
		this.setArt(art);
		this.setSolDis(sd);
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

	@Column
	public int getCantidadAEnviar() {
		return cantidadAEnviar;
	}

	public void setCantidadAEnviar(int cantidadAEnviar) {
		this.cantidadAEnviar = cantidadAEnviar;
	}
	
	public ArticuloAEnviarVO getVO(){
		ArticuloAEnviarVO art = new ArticuloAEnviarVO();
		art.setIdAAE(this.getIdAAE());
		art.setCantidadAEnviar(this.getCantidadAEnviar());
		art.setArt(this.getArt().getVO());
		art.setSolDis(this.getSolDis().getVO());
		return art;
	}
	
	public void setVO(ArticuloAEnviarVO art){
		this.setIdAAE(art.getIdAAE());
		this.setCantidadAEnviar(art.getCantidadAEnviar());
		Articulo art2 = new Articulo();
		art2.setVO(art.getArt());
		this.setArt(art2);
		SolicitudDistribucion solDis = new SolicitudDistribucion();
		solDis.setVO(art.getSolDis());
		this.setSolDis(solDis);
	}
	
}