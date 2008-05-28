package BusinessLogic;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("fabricacion")
public class SolicitudDeFabricacion extends Solicitud
{
	private static final long serialVersionUID = 4970004249380972083L;
	private Fabrica fabrica;
	private Collection<Articulo> articulos;
	
	public SolicitudDeFabricacion() {
		super();
		this.articulos = new Vector<Articulo>();
	}
	
	public SolicitudDeFabricacion(int n, Collection<Articulo> a, Date f, Fabrica fa){
		super(n,f);
		this.fabrica = fa;
		this.articulos = a;
	}
	
	@OneToMany
	public Collection<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulo(Collection<Articulo> articulo) {
		this.articulos = articulo;
	}
	
	@ManyToOne
	public Fabrica getFabrica() {
		return fabrica;
	}

	public void setFabrica(Fabrica fabrica) {
		this.fabrica = fabrica;
	}
	
	@Transient
	public SolicitudFabricaVO getVO(){
		Collection<ArticuloHeaderVO> articulos = new Vector<ArticuloHeaderVO>();
		/*	for(int i = 0; i< this.getArticulo().size();i++)
			articulos.add(new ArticuloHeaderVO(this.getArticulo())*/
		SolicitudFabricaVO vo = new SolicitudFabricaVO(numero,articulos,fechaEmision,new FabricaVO(fabrica.getCodigoFabrica(),fabrica.getNombreFabrica()));
		return vo;
	}

	public void setVO(SolicitudFabricaVO vo){
		Collection<Articulo> articulos = new Vector<Articulo>();
		/*	for(int i = 0; i< this.getArticulo().size();i++)
			articulos.add(new ArticuloHeaderVO(this.getArticulo())*/
		
		
		this.setNumero(vo.getNumero());
		this.setArticulo(articulos);
		this.setFecha(vo.getFechaEmision());
		this.setFabrica(new Fabrica(vo.getFabrica().getCodigoFabrica(),vo.getFabrica().getNombreFabrica()));
	}
}
