package BusinessLogic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import VO.ArticuloHeaderVO;
import VO.FabricaVO;
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
	
	@Transient
	public SolicitudDeReposicionVO getVO()
	{
		Collection<ArticuloHeaderVO> articulos = new Vector<ArticuloHeaderVO>();
		Iterator a = (Iterator) this.getArticulos().iterator();
		
		while(a.hasNext())
			articulos.add((ArticuloHeaderVO)a.next());	
		SolicitudDeReposicionVO vo = new SolicitudDeReposicionVO(this.getNumero(), articulos, this.getFechaEmision(), new FabricaVO(fabrica.getCodigoFabrica(),fabrica.getNombreFabrica()));
		return vo;
	}

	public void setVO(SolicitudDeReposicionVO vo)
	{
		Collection<Articulo> articulos = new Vector<Articulo>();
		Iterator a = (Iterator)vo.getArticulo().iterator();
		while(a.hasNext())
			articulos.add((Articulo)a.next());
		
		this.setNumero(vo.getNumero());
		this.setArticulos(articulos);
		this.setFechaEmision(vo.getFechaEmision());
		this.setFabrica(new Fabrica(vo.getFabrica().getCodigoFabrica(),vo.getFabrica().getNombreFabrica()));
	}

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
}
