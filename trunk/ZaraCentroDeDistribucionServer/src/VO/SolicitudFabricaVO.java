package VO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class SolicitudFabricaVO extends SolicitudVO implements Serializable
{

	private static final long serialVersionUID = -339743656499411780L;
	private FabricaVO fabrica;
	private Collection<ArticuloPendienteVO> articulosRecibidos;
	
	public SolicitudFabricaVO() {
		super();
	}
	
	public SolicitudFabricaVO(int n, Collection<ArticuloHeaderVO> a, Date f, FabricaVO fa,Collection<ArticuloPendienteVO> recibidos ){
		super(n,a,f);
		this.fabrica = fa;
		this.setArticulosRecibidos(recibidos);
	}


	public FabricaVO getFabrica() {
		return fabrica;
	}


	public void setFabrica(FabricaVO fabrica) {
		this.fabrica = fabrica;
	}

	public Collection<ArticuloPendienteVO> getArticulosRecibidos() {
		return articulosRecibidos;
	}

	public void setArticulosRecibidos(Collection<ArticuloPendienteVO> articulosRecibidos) {
		this.articulosRecibidos = articulosRecibidos;
	}
	
}
