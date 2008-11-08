package xml;

public class XMLSolRepFabrica 
{
	private long codigo;
	private String fecha;
	private long codArt;
	private int cantidad;
	private int codSolFab;
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public long getCodArt() {
		return codArt;
	}
	public void setCodArt(long codArt) {
		this.codArt = codArt;
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getCodSolFab() {
		return codSolFab;
	}
	public void setCodSolFab(int codSolFab) {
		this.codSolFab = codSolFab;
	}
	
}
