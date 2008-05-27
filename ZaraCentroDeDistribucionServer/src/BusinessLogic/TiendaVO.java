package BusinessLogic;

import java.io.Serializable;

public class TiendaVO implements Serializable 
{
	private static final long serialVersionUID = 1607295637062830217L;
	private int codigoTienda;
	private String nombreTienda;
	
	public TiendaVO() {
		// TODO Auto-generated constructor stub
	}
	
	public TiendaVO(int c, String n) {
		this.codigoTienda = c;
		this.nombreTienda = n;
	}

	public int getCodigoTienda() {
		return codigoTienda;
	}

	public void setCodigoTienda(int codigoTienda) {
		this.codigoTienda = codigoTienda;
	}

	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}
	
	

}
