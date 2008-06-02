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
import VO.FabricaVO;
import VO.SolicitudFabricaVO;

@Entity
@DiscriminatorValue("fabricacion")
public class SolicitudDeFabricacion extends Solicitud
{
	private static final long serialVersionUID = 4970004249380972083L;
	private Fabrica fabrica;
	private Collection<Articulo> articulosRecibidos;
	
	public SolicitudDeFabricacion() {
		super();
		this.articulosRecibidos = new Vector<Articulo>();
	}
	
	public SolicitudDeFabricacion(int n, Collection<Articulo> a, Date f, Fabrica fa){
		super(n,f);
		this.fabrica = fa;
		this.setArticulos(a);
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
		Collection<ArticuloHeaderVO> articulos = new Vector<ArticuloHeaderVO>();
		Collection<ArticuloHeaderVO> recibidos = new Vector<ArticuloHeaderVO>();
		
		Iterator a = (Iterator) this.getArticulos().iterator();
		while(a.hasNext())
			articulos.add((ArticuloHeaderVO)a.next());
		
		Iterator rec = (Iterator)this.getArticulos().iterator();
		while(rec.hasNext())
			recibidos.add((ArticuloHeaderVO)rec.next());
		
		SolicitudFabricaVO vo = new SolicitudFabricaVO(this.getNumero(),articulos,this.getFechaEmision(),new FabricaVO(fabrica.getCodigoFabrica(),fabrica.getNombreFabrica()),recibidos);
		return vo;
	}

	public void setVO(SolicitudFabricaVO vo)
	{
		Collection<Articulo> articulos = new Vector<Articulo>();
		Collection<Articulo> recibidos = new Vector<Articulo>();
		
		Iterator a = (Iterator)vo.getArticulo().iterator();
		Iterator rec = (Iterator) vo.getArticulosRecibidos().iterator();
		
		while(a.hasNext())
			articulos.add((Articulo)a.next());
		
		while(rec.hasNext())
			recibidos.add((Articulo)rec.next());	
		
		this.setNumero(vo.getNumero());
		this.setArticulos(articulos);
		this.setFechaEmision(vo.getFechaEmision());
		this.setFabrica(new Fabrica(vo.getFabrica().getCodigoFabrica(),vo.getFabrica().getNombreFabrica()));
		this.setArticulosRecibidos(recibidos);
	}

	@OneToMany
	public Collection<Articulo> getArticulosRecibidos() {
		return articulosRecibidos;
	}

	public void setArticulosRecibidos(Collection<Articulo> articulosRecibidos) {
		this.articulosRecibidos = articulosRecibidos;
	}
}
