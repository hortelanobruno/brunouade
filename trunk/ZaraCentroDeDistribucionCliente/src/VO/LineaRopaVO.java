package VO;

import java.io.Serializable;

public class LineaRopaVO implements Serializable
{
	private static final long serialVersionUID = 3372249390252003974L;
	int idLinea;
	private String linea;
	
	public LineaRopaVO()
	{
		
	}
	
	public LineaRopaVO(int codigo, String linea)
	{
		this.setIdLinea(codigo);
		this.setLinea(linea);
	}

	public int getIdLinea() {
		return idLinea;
	}

	public void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}
}
