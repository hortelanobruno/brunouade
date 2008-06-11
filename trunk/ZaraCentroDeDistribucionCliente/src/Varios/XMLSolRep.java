package Varios;

import java.util.Vector;

import VO.FabricaVO;

public class XMLSolRep 
{
	private int codigo;
	private int codSolFab;
	private FabricaVO fabrica;	
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
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public FabricaVO getFabrica() {
		return fabrica;
	}
	public void setFabrica(FabricaVO fabrica) {
		this.fabrica = fabrica;
	}

}
