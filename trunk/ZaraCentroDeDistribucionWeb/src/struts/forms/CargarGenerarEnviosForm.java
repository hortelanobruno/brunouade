package struts.forms;

import org.apache.struts.action.ActionForm;


public class CargarGenerarEnviosForm extends ActionForm 
{
	private static final long serialVersionUID = -1120299888996545826L;
	private String[] codigo;
	private String[] descripcion;
	private String[] cantidadpedida;
	private String[] stock;
	private String[] cantidadenviada;
	private String[] cantidadaenviar;
	private String idsoldis;
	
	public void setCodigo(String[] codigo) {
		this.codigo = codigo;
	}
	public String[] getCodigo() {
		return codigo;
	}
	public void setDescripcion(String[] descripcion) {
		this.descripcion = descripcion;
	}
	public String[] getDescripcion() {
		return descripcion;
	}
	public void setCantidadpedida(String[] cantidadpedida) {
		this.cantidadpedida = cantidadpedida;
	}
	public String[] getCantidadpedida() {
		return cantidadpedida;
	}
	public void setStock(String[] stock) {
		this.stock = stock;
	}
	public String[] getStock() {
		return stock;
	}
	public void setCantidadenviada(String[] cantidadenviada) {
		this.cantidadenviada = cantidadenviada;
	}
	public String[] getCantidadenviada() {
		return cantidadenviada;
	}
	public void setCantidadaenviar(String[] cantidadaenviar) {
		this.cantidadaenviar = cantidadaenviar;
	}
	public String[] getCantidadaenviar() {
		return cantidadaenviar;
	}
	public void setIdsoldis(String idsoldis) {
		this.idsoldis = idsoldis;
	}
	public String getIdsoldis() {
		return idsoldis;
	}


}
