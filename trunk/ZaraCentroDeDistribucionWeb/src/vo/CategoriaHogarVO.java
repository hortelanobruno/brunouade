package vo;

import java.io.Serializable;

public class CategoriaHogarVO implements Serializable
{
	private static final long serialVersionUID = -2983316760950291797L;
	private int idCategoria;
	private String categoria;
	
	public CategoriaHogarVO()
	{
		
	}
	
	public CategoriaHogarVO(int codigo, String categoria)
	{
		this.setIdCategoria(codigo);
		this.setCategoria(categoria);
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
}
