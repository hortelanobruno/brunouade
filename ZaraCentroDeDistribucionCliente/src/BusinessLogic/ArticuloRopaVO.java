package BusinessLogic;

public class ArticuloRopaVO extends ArticuloHeaderVO 
{
	private static final long serialVersionUID = -6795972581817254080L;
	private String talle;
	private String origen;
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
