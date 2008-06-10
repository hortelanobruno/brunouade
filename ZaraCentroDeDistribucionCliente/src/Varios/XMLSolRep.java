package Varios;

import java.util.Vector;

public class XMLSolRep 
{
	private int codSolFab;
	private Vector<XMLArticuloRecibido> articulosRecibidos;
	
	public Vector<XMLArticuloRecibido> getArticulosRecibidos() {
		return articulosRecibidos;
	}
	public void setArticulosRecibidos(Vector<XMLArticuloRecibido> articulosRecibidos) {
		this.articulosRecibidos = articulosRecibidos;
	}
	public int getCodSolFab() {
		return codSolFab;
	}
	public void setCodSolFab(int codSolFab) {
		this.codSolFab = codSolFab;
	}

}
