package BusinessLogic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class SolicitudDistribucionVO extends SolicitudVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5633098382339627713L;
	private TiendaVO tienda;
	
    public SolicitudDistribucionVO() {

	}
	
	public SolicitudDistribucionVO(int n, Collection<ArticuloHeaderVO> a, Date f, TiendaVO t){
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
