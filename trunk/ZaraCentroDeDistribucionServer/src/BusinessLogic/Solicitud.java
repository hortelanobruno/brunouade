package BusinessLogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import VO.CategoriaHogarVO;
import VO.LineaRopaVO;
import VO.SolicitudVO;

@Entity
@Table(name="Solicitudes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminador",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("solicitud")
public class Solicitud implements Serializable
{
	private static final long serialVersionUID = -6174778687164025228L;
	private int numero;
	private Date fechaEmision;
	private CentroDistribucion centro;
    
	public Solicitud() {
		// TODO Auto-generated constructor stub
	}
	
	public Solicitud(int n, Date f){
		this.numero = n;		
		this.fechaEmision = f;
	}
	
	@Id
	@Column
	public int getNumero() 
	{
		return numero;
	}
	
	public void setNumero(int numero) 
	{
		this.numero = numero;
	}
	
	@Transient
	public SolicitudVO getVO()
	{	
		SolicitudVO vo = new SolicitudVO(numero,fechaEmision,this.centro.getVO());
		return vo;
	}

	public void setVO(SolicitudVO vo)
	{
		this.setNumero(vo.getNumero());
		this.setFechaEmision(vo.getFechaEmision());
		
		Iterator it = this.centro.getLineasRopa().iterator();
		Collection<LineaRopa> lineas = new ArrayList<LineaRopa>();
		while(it.hasNext()){
			LineaRopaVO lineaVO = (LineaRopaVO) it.next();
			LineaRopa linea = new LineaRopa(lineaVO.getIdLinea(),lineaVO.getLinea());
			lineas.add(linea);
		}
		
		Iterator it2 = this.centro.getCategoriasHogar().iterator();
		Collection<CategoriaHogar> categorias = new ArrayList<CategoriaHogar>();
		while(it2.hasNext()){
			CategoriaHogarVO catVO = (CategoriaHogarVO) it.next();
			CategoriaHogar cat = new CategoriaHogar(catVO.getIdCategoria(),catVO.getCategoria());
			categorias.add(cat);
		}
		
		CentroDistribucion centro = new CentroDistribucion(vo.getCdVO().getCodCentro(),vo.getCdVO().getNombreCentro(),vo.getCdVO().getLongitud(),vo.getCdVO().getLatitud(),lineas,categorias);
		this.setCentro(centro);
		
	}

	@ManyToOne
	public CentroDistribucion getCentro() {
		return centro;
	}

	public void setCentro(CentroDistribucion centro) {
		this.centro = centro;
	}

	@Column
	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

}
