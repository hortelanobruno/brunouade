package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("hogar")
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
		return null;
	}
	
	public void setArticuloRopaVO(ArticuloRopaVO art)
	{
		
	}
}
