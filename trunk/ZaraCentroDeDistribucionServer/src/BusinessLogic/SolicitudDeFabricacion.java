package BusinessLogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import VO.ArticuloAFabricarVO;
import VO.SolicitudFabricaVO;

@Entity
@DiscriminatorValue("fabricacion")
public class SolicitudDeFabricacion extends Solicitud
{
	private static final long serialVersionUID = 4970004249380972083L;
	private Fabrica fabrica;
	private Collection<ArticuloAFabricar> articulosAFabricar;
	
	public SolicitudDeFabricacion() {
		super();
		this.articulosAFabricar = new Vector<ArticuloAFabricar>();
	}
	
	public SolicitudDeFabricacion(int n, Collection<ArticuloAFabricar> a, Date f, Fabrica fa)
	{
		super(n,new java.sql.Date(f.getTime()));
		this.fabrica = fa;
		this.setArticulosAFabricar(a);
	}
	
	@ManyToOne
	public Fabrica getFabrica() {
		return fabrica;
	}

	public void setFabrica(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	@OneToMany
	public Collection<ArticuloAFabricar> getArticulosAFabricar() {
		return articulosAFabricar;
	}

	public void setArticulosAFabricar(Collection<ArticuloAFabricar> articulosAFabricar) {
		this.articulosAFabricar = articulosAFabricar;
	}
	
	@Transient
	public SolicitudFabricaVO getVO()
	{
		SolicitudFabricaVO vo = new SolicitudFabricaVO();
		vo.setNumero(this.getNumero());
		vo.setFabrica(this.getFabrica().getVO());
		vo.setFechaEmision(this.getFechaEmision());
		vo.setCdVO(this.getCentro().getVO());
		Iterator it = (Iterator) this.getArticulosAFabricar().iterator();
		Collection<ArticuloAFabricarVO> arts = new ArrayList<ArticuloAFabricarVO>();
		while(it.hasNext()){
			ArticuloAFabricar art = ((ArticuloAFabricar)it.next());
			arts.add(art.getVO());
		}
		vo.setArticulosAFabricar(arts);
		return vo;
	}

	public void setVO(SolicitudFabricaVO vo)
	{
		Collection<ArticuloAFabricar> recibidos = new ArrayList<ArticuloAFabricar>();
		Iterator rec = (Iterator) vo.getArticulosAFabricar().iterator();
		while(rec.hasNext()){
			ArticuloAFabricarVO art = (ArticuloAFabricarVO)rec.next();
			ArticuloAFabricar arti = new ArticuloAFabricar();
			arti.setVO(art);
			recibidos.add(arti);
		}
				
		
		CentroDistribucion cent = new CentroDistribucion();
		cent.setVO(vo.getCdVO());
		this.setCentro(cent);
		this.setNumero(vo.getNumero());
		this.setFechaEmision(new java.sql.Date(vo.getFechaEmision().getTime()));
		Fabrica fab = new Fabrica();
		fab.setVO(vo.getFabrica());
		this.setFabrica(fab);
		this.setArticulosAFabricar(recibidos);
		
	}
}
