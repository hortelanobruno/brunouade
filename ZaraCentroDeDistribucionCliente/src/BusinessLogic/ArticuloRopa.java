package BusinessLogic;

public class ArticuloRopa extends Articulo 
{
	//descripcion: Camiseta Algodon M/C 
	private String talle;
	private String origen;
	
	public ArticuloRopa()
	{
		super();
	}
	
	public String getOrigen() 
	{
		return origen;
	}
	
	public void setOrigen(String origen) 
	{
		this.origen = origen;
	}

	
	public String getTalle() 
	{
		return talle;
	}
	
	public void setTalle(String talle) 
	{
		this.talle = talle;
	}	
}
