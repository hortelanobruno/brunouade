package BusinessLogic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

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
	public SolicitudVO getVO(){
		Collection<ArticuloHeaderVO> articulos = new Vector<ArticuloHeaderVO>();
		/*	for(int i = 0; i< this.getArticulo().size();i++)
			articulos.add(new ArticuloHeaderVO(this.getArticulo())*/
		SolicitudVO vo = new SolicitudVO(numero,articulos,fechaEmision);
		return vo;
	}

	public void setVO(SolicitudVO vo){
		this.setNumero(vo.getNumero());
		this.setFechaEmision(vo.getFechaEmision());
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
