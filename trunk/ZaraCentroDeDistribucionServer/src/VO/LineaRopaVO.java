package VO;

public class LineaRopaVO {

	private int idLinea;
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
