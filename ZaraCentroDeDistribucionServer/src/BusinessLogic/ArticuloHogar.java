package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("hogar")
public class ArticuloHogar extends Articulo 
{
	private static final long serialVersionUID = 8366834104451397702L;
	//descripcion: Alfombra Havana
	private String detalles; //Alfombra de piel de vaca con patchwork
	private String composicion;
	private String categoria;
	private String medidas;
	
	public ArticuloHogar()
	{
		super();
	}

	@Column
	public String getCategoria() 
	{
		return categoria;
	}

	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}

	@Column
	public String getComposicion()
	{
		return composicion;
	}

	public void setComposicion(String composicion)
	{
		this.composicion = composicion;
	}

	@Column
	public String getDetalles() 
	{
		return detalles;
	}

	public void setDetalles(String detalles) 
	{
		this.detalles = detalles;
	}

	@Column
	public String getMedidas() 
	{
		return medidas;
	}

	public void setMedidas(String medidas)
	{
		this.medidas = medidas;
	}
}
