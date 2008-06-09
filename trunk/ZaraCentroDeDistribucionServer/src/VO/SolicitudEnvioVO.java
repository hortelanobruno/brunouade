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
	private Collection<ArticuloAEnviarVO> articulosAEnviar;
	
    public SolicitudEnvioVO() {

	}
	
	public SolicitudEnvioVO(int n, Collection<ArticuloAEnviarVO> a, Date f, TiendaVO t, CentroDistribucionVO centro){
		super(n,f,centro);
		this.tienda = t;
		this.articulosAEnviar = a;
	}
	
	public TiendaVO getTienda() {
		return tienda;
	}

	public void setTienda(TiendaVO tienda) {
		this.tienda = tienda;
	}

	public Collection<ArticuloAEnviarVO> getArticulosAEnviar() {
		return articulosAEnviar;
	}

	public void setArticulosAEnviar(Collection<ArticuloAEnviarVO> articulosAEnviar) {
		this.articulosAEnviar = articulosAEnviar;
	}

	
	
}
