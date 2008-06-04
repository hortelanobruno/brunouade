package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lineasRopa")
public class LineaRopa 
{
	private int idLinea;
	private String linea;
	
	public LineaRopa(int codigo, String linea)
	{
		this.setIdLinea(codigo);
		this.setLinea(linea);
	}
	
	@Id
	@Column
	public int getIdLinea()
	{
		return idLinea;
	}
	
	public void setIdLinea(int idLinea) 
	{
		this.idLinea = idLinea;
	}
	
	@Column
	public String getLinea() 
	{
		return linea;
	}
	
	public void setLinea(String linea) 
	{
		this.linea = linea;
	}
}
