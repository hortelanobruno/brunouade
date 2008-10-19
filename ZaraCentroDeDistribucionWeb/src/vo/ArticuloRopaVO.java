package vo;

public class ArticuloRopaVO extends ArticuloHeaderVO 
{
	private static final long serialVersionUID = -6795972581817254080L;
	private String talle;
	private String origen;
	

	
	public ArticuloRopaVO(String desc, long codigo, int cantidad,String color,float precio, String linea, String seccion,String talle,String origen)
	{
		super( desc,  codigo,  cantidad, color, precio,  linea,  seccion);
		this.setTalle(talle);
		this.setOrigen(origen);
	}
	public ArticuloRopaVO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getTalle() {
		return talle;
	}
	public void setTalle(String talle) {
		this.talle = talle;
	}

}
