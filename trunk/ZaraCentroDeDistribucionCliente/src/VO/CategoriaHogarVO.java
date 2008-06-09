package VO;

public class CategoriaHogarVO {

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
