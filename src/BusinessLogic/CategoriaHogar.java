package BusinessLogic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import VO.CategoriaHogarVO;

@Entity
@Table(name="categoriasHogar")
public class CategoriaHogar implements Serializable
{
	private static final long serialVersionUID = 2964951293297748610L;
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
	
	@Transient
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
