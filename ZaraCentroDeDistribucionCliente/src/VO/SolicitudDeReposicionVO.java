package VO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class SolicitudDeReposicionVO extends SolicitudVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private FabricaVO fabrica;
	private long codigoSolicitudFabricacion;
	
	public SolicitudDeReposicionVO(){
		
	}
	
	public SolicitudDeReposicionVO(int n, Collection<ArticuloHeaderVO> a, Date f, FabricaVO fa ){
		super(n,a,f);
		this.fabrica = fa;
	}
	
	public FabricaVO getFabrica() {
		return fabrica;
	}
	public void setFabrica(FabricaVO fabrica) {
		this.fabrica = fabrica;
	}

	public long getCodigoSolicitudFabricacion() {
		return codigoSolicitudFabricacion;
	}

	public void setCodigoSolicitudFabricacion(long codigoSolicitudFabricacion) {
		this.codigoSolicitudFabricacion = codigoSolicitudFabricacion;
	}

}
