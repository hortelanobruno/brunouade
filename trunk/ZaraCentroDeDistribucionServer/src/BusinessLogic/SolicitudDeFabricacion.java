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
import VO.FabricaVO;
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
		super(n,f);
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
	
	@Transient
	public SolicitudFabricaVO getVO()
	{
		Collection<ArticuloAFabricarVO> articulos = new Vector<ArticuloAFabricarVO>();
		Iterator rec = (Iterator) this.getArticulosAFabricar().iterator();
		while(rec.hasNext()){
			ArticuloAFabricar arti = (ArticuloAFabricar)rec.next();
			ArticuloAFabricarVO art = new ArticuloAFabricarVO();
			art.setIdAAF(arti.getIdAAF());
			art.setCantidadPedida(arti.getCantidadPedida());
			art.setCantidadRecibida(arti.getCantidadRecibida());
			art.setArt(arti.getArt().getVO());
			art.setFabrica(arti.getFabrica().getVO());
			art.setSol(arti.getSol().getVO());
			articulos.add(art);
		}
		SolicitudFabricaVO vo = new SolicitudFabricaVO(this.getNumero(),this.getFechaEmision(),new FabricaVO(fabrica.getCodigoFabrica(),fabrica.getNombreFabrica()),articulos,this.getCentro().getVO());
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
		this.setFechaEmision(vo.getFechaEmision());
		Fabrica fab = new Fabrica();
		fab.setVO(vo.getFabrica());
		this.setFabrica(fab);
		this.setArticulosAFabricar(recibidos);
	}

	@OneToMany
	public Collection<ArticuloAFabricar> getArticulosAFabricar() {
		return articulosAFabricar;
	}

	public void setArticulosAFabricar(Collection<ArticuloAFabricar> articulosAFabricar) {
		this.articulosAFabricar = articulosAFabricar;
	}
}
