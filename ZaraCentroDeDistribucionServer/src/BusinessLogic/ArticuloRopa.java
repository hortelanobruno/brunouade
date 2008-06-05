package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import VO.ArticuloRopaVO;

@Entity
@DiscriminatorValue("ropa")
public class ArticuloRopa extends Articulo 
{
	private static final long serialVersionUID = -1550636008034632214L;
	//descripcion: Camiseta Algodon M/C 
	private String talle;
	private String origen;
	
	public ArticuloRopa()
	{
		super();
	}
	
	@Column
	public String getOrigen() 
	{
		return origen;
	}
	
	public void setOrigen(String origen) 
	{
		this.origen = origen;
	}

	@Column
	public String getTalle() 
	{
		return talle;
	}
	
	public void setTalle(String talle) 
	{
		this.talle = talle;
	}
	
	@Transient
	public ArticuloRopaVO getArticuloRopaVO()
	{
		return new ArticuloRopaVO(this.getDescripcion(), this.getCodigo(),this.getCantidad(),this.getColor(),this.getPrecio(), this.getLinea(), this.getSeccion(),this.getTalle(),this.getOrigen());
	}
	
	public void setArticuloRopaVO(ArticuloRopaVO art)
	{
		this.setDescripcion(art.getDescripcion());
		this.setCodigo(art.getCodigo());
		this.setCantidad(art.getCantidad());
		this.setColor(art.getColor());
		this.setPrecio(art.getPrecio());
		this.setLinea(art.getLinea());
		this.setSeccion(art.getSeccion());
		this.setTalle(art.getTalle());
		this.setOrigen(art.getOrigen());
	}
}
