package BusinessLogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import VO.ArticuloAEnviarVO;
import VO.SolicitudEnvioVO;

@Entity
@DiscriminatorValue("envio")
public class SolicitudEnvioATienda extends Solicitud
{
	private static final long serialVersionUID = 5001930866418434765L;
	private Tienda tienda;
	private Collection<ArticuloAEnviar> articulosAEnviar;

	public SolicitudEnvioATienda() {
		super();
	}
	
	public SolicitudEnvioATienda(int n, Collection<ArticuloAEnviar> a, Date f, Tienda t){
		super(n,f);
		this.tienda = t;
		this.setArticulosAEnviar(a);
	}
	
	@ManyToOne
	public Tienda getTienda() 
	{
		return tienda;
	}

	public void setTienda(Tienda tienda) 
	{
		this.tienda = tienda;
	}
	
	@OneToMany
	public Collection<ArticuloAEnviar> getArticulosAEnviar() {
		return articulosAEnviar;
	}

	public void setArticulosAEnviar(Collection<ArticuloAEnviar> articulosAEnviar) {
		this.articulosAEnviar = articulosAEnviar;
	}
	
	@Transient
	public SolicitudEnvioVO getVO(){
		SolicitudEnvioVO sol = new SolicitudEnvioVO();
		sol.setNumero(this.getNumero());
		sol.setFechaEmision(this.getFechaEmision());
		sol.setTienda(this.getTienda().getVO());
		sol.setCdVO(this.getCentro().getVO());
		Collection<ArticuloAEnviarVO> arts = new ArrayList<ArticuloAEnviarVO>();
		Iterator it = (Iterator) this.getArticulosAEnviar().iterator();
		while(it.hasNext()){
			ArticuloAEnviarVO art = ((ArticuloAEnviar)it.next()).getVO();
			arts.add(art);
		}
		sol.setArticulosAEnviar(arts);
		return sol;
	}

	public void setVO(SolicitudEnvioVO vo){
		this.setFechaEmision(vo.getFechaEmision());
		this.setNumero(vo.getNumero());
		Tienda tienda = new Tienda();
		tienda.setVO(vo.getTienda());
		this.setTienda(tienda);
		CentroDistribucion centro = new CentroDistribucion();
		centro.setVO(vo.getCdVO());
		this.setCentro(centro);
		Collection<ArticuloAEnviar> arts = new ArrayList<ArticuloAEnviar>();
		Iterator it = (Iterator) vo.getArticulosAEnviar().iterator();
		while(it.hasNext()){
			ArticuloAEnviar art = new ArticuloAEnviar();
			art.setVO(((ArticuloAEnviarVO)it.next()));
			arts.add(art);
		}
		this.setArticulosAEnviar(arts);
	}
}
