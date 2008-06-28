package Varios;

import java.util.Vector;

public class XMLSolEnv 
{
	private int numero;
	private XMLCentro centro;
	private XMLTienda tienda;
	private int solDis;
	private Vector<XMLArticuloAEnviar> articulosAEnviar;
	private String fechaEmision;
	
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Vector<XMLArticuloAEnviar> getArticulosAEnviar() {
		return articulosAEnviar;
	}
	public void setArticulosAEnviar(Vector<XMLArticuloAEnviar> articulosAEnviar) {
		this.articulosAEnviar = articulosAEnviar;
	}
	public XMLCentro getCentro() {
		return centro;
	}
	public void setCentro(XMLCentro centro) {
		this.centro = centro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getSolDis() {
		return solDis;
	}
	public void setSolDis(int solDis) {
		this.solDis = solDis;
	}
	public XMLTienda getTienda() {
		return tienda;
	}
	public void setTienda(XMLTienda tienda) {
		this.tienda = tienda;
	}
}
