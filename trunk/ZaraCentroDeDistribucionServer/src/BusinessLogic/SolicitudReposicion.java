package BusinessLogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import VO.ArticuloAReponerVO;
import VO.SolicitudDeReposicionVO;

@Entity
@DiscriminatorValue("reposicion")
public class SolicitudReposicion extends Solicitud
{
	private static final long serialVersionUID = -3577225904639518643L;
	private Fabrica fabrica;
	private SolicitudDeFabricacion solFab;
	private Collection<ArticuloAReponer> articulosAReponer;

	public SolicitudReposicion() 
	{
		super();
	}
	
	@ManyToOne
	public Fabrica getFabrica() 
	{
		return fabrica;
	}
	
	public void setFabrica(Fabrica fabrica)
	{
		this.fabrica = fabrica;
	}
	
	@OneToMany(mappedBy="sr")
	public Collection<ArticuloAReponer> getArticulosAReponer() {
		return articulosAReponer;
	}

	public void setArticulosAReponer(Collection<ArticuloAReponer> articulosAReponer) {
		this.articulosAReponer = articulosAReponer;
	}

	public SolicitudDeFabricacion getSolFab() {
		return solFab;
	}

	public void setSolFab(SolicitudDeFabricacion solFab) {
		this.solFab = solFab;
	}
	
	@Transient
	public SolicitudDeReposicionVO getVO()
	{
		SolicitudDeReposicionVO sol = new SolicitudDeReposicionVO();
		sol.setNumero(this.getNumero());
		sol.setFechaEmision(this.getFechaEmision());
		sol.setFabrica(this.getFabrica().getVO());
		sol.setSolFab(this.getSolFab().getVO());
		sol.setCdVO(this.getCentro().getVO());
		Collection<ArticuloAReponerVO> arts = new ArrayList<ArticuloAReponerVO>();
		Iterator it = (Iterator) this.getArticulosAReponer().iterator();
		while(it.hasNext()){
			ArticuloAReponerVO art = ((ArticuloAReponer)it.next()).getVO();
			arts.add(art);
		}
		sol.setArticulosAReponer(arts);
		return sol;
	}

	public void setVO(SolicitudDeReposicionVO vo)
	{
		this.setNumero(vo.getNumero());
		this.setFechaEmision(vo.getFechaEmision());
		Fabrica fab = new Fabrica();
		fab.setVO(vo.getFabrica());
		this.setFabrica(fab);
		CentroDistribucion centro = new CentroDistribucion();
		centro.setVO(vo.getCdVO());
		this.setCentro(centro);
		SolicitudDeFabricacion solFab = new SolicitudDeFabricacion();
		solFab.setVO(vo.getSolFab());
		this.setSolFab(solFab);
		Collection<ArticuloAReponer> arts = new ArrayList<ArticuloAReponer>();
		Iterator it = (Iterator) vo.getArticulosAReponer().iterator();
		while(it.hasNext()){
			ArticuloAReponer art = new ArticuloAReponer();
			art.setVO(((ArticuloAReponerVO)it.next()));
			arts.add(art);
		}
		this.setArticulosAReponer(arts);
	}
}
