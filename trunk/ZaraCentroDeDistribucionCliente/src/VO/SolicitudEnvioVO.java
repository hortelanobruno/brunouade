package VO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class SolicitudEnvioVO extends SolicitudVO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8304187393253826615L;
	private TiendaVO tienda;
	
    public SolicitudEnvioVO() {

	}
	
	public SolicitudEnvioVO(int n, Collection<ArticuloHeaderVO> a, Date f, TiendaVO t){
		super(n,a,f);
		this.tienda = t;
	}
	
	public TiendaVO getTienda() {
		return tienda;
	}

	public void setTienda(TiendaVO tienda) {
		this.tienda = tienda;
	}

	
	
}
