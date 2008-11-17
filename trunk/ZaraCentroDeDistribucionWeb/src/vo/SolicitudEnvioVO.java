package vo;

import java.util.Collection;
import java.util.Date;

public class SolicitudEnvioVO extends SolicitudVO
{
	private static final long serialVersionUID = -8304187393253826615L;
	private TiendaVO tienda;
	private Collection<ArticuloAEnviarVO> articulosAEnviar;
	private int idEnv;
	private SolicitudDistribucionVO solDis;

	public SolicitudEnvioVO() {

	}
	
	public SolicitudEnvioVO(int id,int n, SolicitudDistribucionVO solDis, Collection<ArticuloAEnviarVO> a, Date f, TiendaVO t, CentroDistribucionVO centro){
		super(id,f,centro);
		this.idEnv = n;
		this.tienda = t;
		this.articulosAEnviar = a;
		this.solDis = solDis;
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

	public int getIdEnv() {
		return idEnv;
	}

	public void setIdEnv(int idEnv) {
		this.idEnv = idEnv;
	}

	public SolicitudDistribucionVO getSolDis() {
		return solDis;
	}
	public void setSolDis(SolicitudDistribucionVO solDis) {
		this.solDis = solDis;
	}
	
}
