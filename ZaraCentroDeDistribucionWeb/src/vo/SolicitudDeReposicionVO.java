package vo;

import java.util.Collection;
import java.util.Date;

public class SolicitudDeReposicionVO extends SolicitudVO
{
	private static final long serialVersionUID = -4383653090680302766L;
	private FabricaVO fabrica;
	private int idRep;
	private Collection<ArticuloAReponerVO> articulosAReponer;
	private boolean procesada;
	
	public SolicitudDeReposicionVO(){
		
	}
	
	public SolicitudDeReposicionVO(int id,int n, Collection<ArticuloAReponerVO> a, Date f, FabricaVO fa, CentroDistribucionVO centro ){
		super(id,f, centro);
		this.idRep = n;
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

	public int getIdRep() {
		return idRep;
	}

	public void setIdRep(int idRep) {
		this.idRep = idRep;
	}

	public void setProcesada(boolean procesada) {
		this.procesada = procesada;
	}

	public boolean getProcesada() {
		return procesada;
	}
}
