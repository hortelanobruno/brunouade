package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import VO.CategoriaHogarVO;

@Entity
@Table(name="categoriasHogar")
public class CategoriaHogar
{
	private int idCategoria;
	private String categoria;
	
	public CategoriaHogar()
	{
		
	}
	
	public CategoriaHogar(int codigo, String categoria)
	{
		this.setIdCategoria(codigo);
		this.setCategoria(categoria);
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
	
	@Id
	@Column
	public int getIdCategoria() 
	{
		return idCategoria;
	}
	
	public void setIdCategoria(int idCategoria)
	{
		this.idCategoria = idCategoria;
	}
	
	public CategoriaHogarVO getVO(){
		CategoriaHogarVO cat = new CategoriaHogarVO();
		cat.setIdCategoria(this.getIdCategoria());
		cat.setCategoria(this.getCategoria());
		return cat;
	}
	
	public void setVO(CategoriaHogarVO cat){
		this.setCategoria(cat.getCategoria());
		this.setIdCategoria(cat.getIdCategoria());
	}
}
