package businesslogic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import vo.ArticuloReservadoVO;

@Entity
@Table(name="ArticulosReservados")
public class ArticuloReservado implements Serializable
{
	private static final long serialVersionUID = -1513419184828706255L;
	private int idAR;
	private Articulo art;
	private int cantidadReservada;
	private SolicitudDistribucion solDis;
	
	public ArticuloReservado() {
	
	}
	
	public ArticuloReservado(int id, Articulo art, int cant, SolicitudDistribucion sol)
	{
		this.setArt(art);
		this.setCantidadReservada(cant);
		this.setIdAR(id);
		this.setSolDis(sol);
	}
	
	@ManyToOne
	public Articulo getArt() {
		return art;
	}
	public void setArt(Articulo art) {
		this.art = art;
	}
	
	@Column
	public int getCantidadReservada() {
		return cantidadReservada;
	}
	public void setCantidadReservada(int cantidadReservada) {
		this.cantidadReservada = cantidadReservada;
	}
	
	@Id
	@Column
	public int getIdAR() {
		return idAR;
	}
	public void setIdAR(int idAR) {
		this.idAR = idAR;
	}
	
	@ManyToOne
	public SolicitudDistribucion getSolDis() {
		return solDis;
	}
	public void setSolDis(SolicitudDistribucion solDis) {
		this.solDis = solDis;
	}
	
	@Transient
	public ArticuloReservadoVO getVO()
	{
		return new ArticuloReservadoVO(this.getIdAR(),this.getArt().getVO(),this.getCantidadReservada(),this.getSolDis().getVO());
	}
	
	public void setVO(ArticuloReservadoVO vo)
	{
		this.setIdAR(vo.getIdAR());
		this.setCantidadReservada(vo.getCantidadReservada());
		Articulo a = new Articulo();
		a.setVO(vo.getArt());
		this.setArt(a);
		SolicitudDistribucion s = new SolicitudDistribucion();
		s.setVO(vo.getSolDis());
		this.setSolDis(s);
	}
}
