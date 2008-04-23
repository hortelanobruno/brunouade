package BusinessLogic;

public class ArticuloHogar extends Articulo 
{
	//descripcion: Alfombra Havana
	private String detalles; //Alfombra de piel de vaca con patchwork
	private String composicion;
	private String categoria;
	private String linea;
	private String medidas;
	
	public ArticuloHogar()
	{
		super();
	}

	public String getCategoria() 
	{
		return categoria;
	}

	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}

	public String getComposicion()
	{
		return composicion;
	}

	public void setComposicion(String composicion)
	{
		this.composicion = composicion;
	}

	public String getDetalles() 
	{
		return detalles;
	}

	public void setDetalles(String detalles) 
	{
		this.detalles = detalles;
	}

	public String getLinea()
	{
		return linea;
	}

	public void setLinea(String linea) 
	{
		this.linea = linea;
	}

	public String getMedidas() 
	{
		return medidas;
	}

	public void setMedidas(String medidas)
	{
		this.medidas = medidas;
	}
}
