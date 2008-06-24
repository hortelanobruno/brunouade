package VO;

import java.util.Collection;
import java.util.Date;

public class SolicitudFabricaVO extends SolicitudVO
{
	private static final long serialVersionUID = -339743656499411780L;
	private FabricaVO fabrica;
	private Collection<ArticuloAFabricarVO> articulosAFabricar;
	private int idFab;
	
	public SolicitudFabricaVO() {
		super();
	}
	
	public SolicitudFabricaVO(int n, Date f, FabricaVO fa,Collection<ArticuloAFabricarVO> recibidos,CentroDistribucionVO centro )
	{
		super(f,centro);
		this.fabrica = fa;
		this.idFab = n;
		this.setArticulosAFabricar(recibidos);
	}

	public FabricaVO getFabrica() {
		return fabrica;
	}


	public void setFabrica(FabricaVO fabrica) {
		this.fabrica = fabrica;
	}

	public Collection<ArticuloAFabricarVO> getArticulosAFabricar() {
		return articulosAFabricar;
	}

	public void setArticulosAFabricar(Collection<ArticuloAFabricarVO> articulosAFabricar) {
		this.articulosAFabricar = articulosAFabricar;
	}

	public int getIdFab() {
		return idFab;
	}

	public void setIdFab(int idFab) {
		this.idFab = idFab;
	}
	
}
