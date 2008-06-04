package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categoriasHogar")
public class CategoriaHogar
{
	private int idCategoria;
	private String categoria;
	
	@Column
	public String getCategoria() 
	{
		return categoria;
	}
	
	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}
	
	@Id
	public int getIdCategoria() 
	{
		return idCategoria;
	}
	
	public void setIdCategoria(int idCategoria)
	{
		this.idCategoria = idCategoria;
	}
}
