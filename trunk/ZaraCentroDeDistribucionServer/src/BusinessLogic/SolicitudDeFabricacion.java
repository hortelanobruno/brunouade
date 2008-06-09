package BusinessLogic;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import VO.ArticuloHeaderVO;
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
		this.setarticulosAFabricar(a);
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
		while(rec.hasNext())
			articulos.add((ArticuloAFabricarVO)rec.next());
		
		SolicitudFabricaVO vo = new SolicitudFabricaVO(this.getNumero(),this.getFechaEmision(),new FabricaVO(fabrica.getCodigoFabrica(),fabrica.getNombreFabrica()),articulos);
		return vo;
	}

	public void setVO(SolicitudFabricaVO vo)
	{
		Collection<ArticuloAFabricar> recibidos = new Vector<ArticuloAFabricar>();
		
		Iterator rec = (Iterator) vo.getarticulosAFabricar().iterator();

		while(rec.hasNext())
			recibidos.add((ArticuloAFabricar)rec.next());	
		
		this.setNumero(vo.getNumero());
		this.setFechaEmision(vo.getFechaEmision());
		this.setFabrica(new Fabrica(vo.getFabrica().getCodigoFabrica(),vo.getFabrica().getNombreFabrica()));
		this.setarticulosAFabricar(recibidos);
	}

	@OneToMany
	public Collection<ArticuloAFabricar> getArticulosAFabricar() {
		return articulosAFabricar;
	}

	public void setarticulosAFabricar(Collection<ArticuloAFabricar> articulosAFabricar) {
		this.articulosAFabricar = articulosAFabricar;
	}
}
