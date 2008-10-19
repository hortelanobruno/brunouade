package businesslogic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import vo.LineaRopaVO;

@Entity
@Table(name="lineasRopa")
public class LineaRopa  implements Serializable
{
	private static final long serialVersionUID = -5341000794952655618L;
	private int idLinea;
	private String linea;
	
	public LineaRopa()
	{
		
	}
	
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
	
	@Transient
	public LineaRopaVO getVO(){
		LineaRopaVO lin = new LineaRopaVO();
		lin.setIdLinea(this.getIdLinea());
		lin.setLinea(this.getLinea());
		return lin;
	}
	
	public void setVO(LineaRopaVO lin){
		this.setIdLinea(lin.getIdLinea());
		this.setLinea(lin.getLinea());
	}
}
