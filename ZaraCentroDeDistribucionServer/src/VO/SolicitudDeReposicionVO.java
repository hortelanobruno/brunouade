package VO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class SolicitudDeReposicionVO extends SolicitudVO implements Serializable
{
	private static final long serialVersionUID = 1L;
	private FabricaVO fabrica;
	private SolicitudFabricaVO solFab;
	private Collection<ArticuloAReponerVO> articulosAReponer;
	
	public SolicitudDeReposicionVO(){
		
	}
	
	public SolicitudDeReposicionVO(int n, Collection<ArticuloAReponerVO> a, Date f, FabricaVO fa, CentroDistribucionVO centro ){
		super(n,f, centro);
		this.fabrica = fa;
		this.setArticulosAReponer(a);
	}
	
	public FabricaVO getFabrica() {
		return fabrica;
	}
	public void setFabrica(FabricaVO fabrica) {
		this.fabrica = fabrica;
	}

	public Collection<ArticuloAReponerVO> getArticulosAReponer() {
		return articulosAReponer;
	}

	public void setArticulosAReponer(Collection<ArticuloAReponerVO> articulosAReponer) {
		this.articulosAReponer = articulosAReponer;
	}

	public SolicitudFabricaVO getSolFab() {
		return solFab;
	}

	public void setSolFab(SolicitudFabricaVO solFab) {
		this.solFab = solFab;
	}
}
