 package vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


public class ArtHogarVO extends ArticuloVO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String compocicion;
	private String categoria;
	private String medidas;
	
	
	
	public ArtHogarVO(int referencia, String linea, String descripcion, String color, String seccion, double precioVentaUnitario, Date ultimoPedido, int mesRebaja, Collection<CentroDistribucionVO> centros, boolean esNuevo, String nombre, String compocicion, String categoria, String medidas) {
		super(referencia, linea, descripcion, color, seccion, precioVentaUnitario, ultimoPedido, mesRebaja, centros, esNuevo);
		this.nombre = nombre;
		this.compocicion = compocicion;
		this.categoria = categoria;
		this.medidas = medidas;
	}
	public ArtHogarVO() {
		// TODO Auto-generated constructor stub
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getCompocicion() {
		return compocicion;
	}
	public void setCompocicion(String compocicion) {
		this.compocicion = compocicion;
	}
	public String getMedidas() {
		return medidas;
	}
	public void setMedidas(String medidas) {
		this.medidas = medidas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
				"\nNombre: "+this.getNombre()+
				"\nComposicion: "+this.getCompocicion()+ 
				"\nCategoria: "+this.getCategoria()+
				"\nMedidas: "+this.getMedidas(); 
		return salida;
	}
}
	