 package vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


public class ArtPersonalVO extends ArticuloVO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	
	private String talle;
	private String origen;
	
	public ArtPersonalVO(){}
	

	public ArtPersonalVO(int referencia, String linea, String descripcion, String color, String seccion, double precioVentaUnitario, Date ultimoPedido, int mesRebaja, Collection<CentroDistribucionVO> centros, boolean esNuevo, String talle, String origen) {
		super(referencia, linea, descripcion, color, seccion, precioVentaUnitario, ultimoPedido, mesRebaja, centros, esNuevo);
		this.talle = talle;
		this.origen = origen;
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
	public String toString(){
		String salida = new String();
		salida= "Referencia: "+this.getReferencia()+
				"\nDescripcion: "+this.getDescripcion()+
				"\nColor: "+this.getColor()+
				"\nLinea: "+this.getLinea()+
				"\nSeccion: "+this.getSeccion()+
				"\nPVU: "+this.getPrecioVentaUnitario()+
				"\nMes de Rebaja: "+this.getMesRebaja()+
				"\nTalle: "+this.getTalle()+
				"\nOrigen: "+this.getOrigen(); 
				
		return salida;
	}
			
}
	