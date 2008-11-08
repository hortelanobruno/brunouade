 package vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;



public class ArticuloVO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int referencia;
	private String linea;
	private String descripcion;
	private String color;
	private String seccion;
	private double precioVentaUnitario;
	private Date ultimoPedido;
	private int mesRebaja;
	private Collection<CentroDistribucionVO> centros = new HashSet<CentroDistribucionVO>( );
	private boolean esNuevo;
	
	
	public ArticuloVO(){};
	
	public ArticuloVO(int referencia, String linea, String descripcion, String color, String seccion, double precioVentaUnitario, Date ultimoPedido, int mesRebaja, Collection<CentroDistribucionVO> centros, boolean esNuevo) {
		super();
		this.referencia = referencia;
		this.linea = linea;
		this.descripcion = descripcion;
		this.color = color;
		this.seccion = seccion;
		this.precioVentaUnitario = precioVentaUnitario;
		this.ultimoPedido = ultimoPedido;
		this.mesRebaja = mesRebaja;
		this.centros = centros;
		this.esNuevo = esNuevo;
	}

	public int getReferencia() {
		return referencia;
	}
	
	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getLinea() {
		return linea;
	}
	
	public void setLinea(String linea) {
		this.linea = linea;
	}
	
	public double getPrecioVentaUnitario() {
		return precioVentaUnitario;
	}
	
	public void setPrecioVentaUnitario(double precioVentaUnitario) {
		this.precioVentaUnitario = precioVentaUnitario;
	}
	
	public String getSeccion() {
		return seccion;
	}
	
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public Collection<CentroDistribucionVO> getCentros() {
		return centros;
	}

	public void setCentros(Collection<CentroDistribucionVO> centros) {
		this.centros = centros;
	}

	public boolean isEsNuevo() {
		return esNuevo;
	}

	public void setEsNuevo(boolean esNuevo) {
		this.esNuevo = esNuevo;
	}

	public int getMesRebaja() {
		return mesRebaja;
	}

	public void setMesRebaja(int mesRebaja) {
		this.mesRebaja = mesRebaja;
	}

	public Date getUltimoPedido() {
		return ultimoPedido;
	}

	public void setUltimoPedido(Date ultimoPedido) {
		this.ultimoPedido = ultimoPedido;
	}
	
	
}
	