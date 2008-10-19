package varios;

import java.util.Date;
import java.util.Vector;

public class XMLSolFab
{
	private int numero;
	private Date fecha;
	private XMLCentro centro;
	private XMLFabrica fabrica;
	private Vector<XMLArticuloFabrica> articulosAFabricar;
	
	public Vector<XMLArticuloFabrica> getArticulosAFabricar() {
		return articulosAFabricar;
	}
	public void setArticulosAFabricar(Vector<XMLArticuloFabrica> articulosAFabricar) {
		this.articulosAFabricar = articulosAFabricar;
	}
	public XMLCentro getCentro() {
		return centro;
	}
	public void setCentro(XMLCentro centro) {
		this.centro = centro;
	}
	public XMLFabrica getFabrica() {
		return fabrica;
	}
	public void setFabrica(XMLFabrica fabrica) {
		this.fabrica = fabrica;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
}
