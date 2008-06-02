package VO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

public class SolicitudFabricaVO extends SolicitudVO implements Serializable{


	private static final long serialVersionUID = -339743656499411780L;
	private FabricaVO fabrica;
	private Collection<ArticuloHeaderVO> articulosRecibidos = new Vector<ArticuloHeaderVO>();
	
	
	public SolicitudFabricaVO() {
		super();
	}
	
	public SolicitudFabricaVO(int n, Collection<ArticuloHeaderVO> a, Date f, FabricaVO fa ){
		super(n,a,f);
		this.fabrica = fa;
	}


	public FabricaVO getFabrica() {
		return fabrica;
	}


	public void setFabrica(FabricaVO fabrica) {
		this.fabrica = fabrica;
	}

	public Collection<ArticuloHeaderVO> getArticulosRecibidos() {
		return articulosRecibidos;
	}

	public void setArticulosRecibidos(
			Collection<ArticuloHeaderVO> articulosRecibidos) {
		this.articulosRecibidos = articulosRecibidos;
	}
	
	
}
