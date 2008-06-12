package VO;

import java.io.Serializable;
import java.util.Date;

public class SolicitudVO implements Serializable
{
	private static final long serialVersionUID = 5288912159022449663L;
	protected int numero;
    protected Date fechaEmision;
    protected CentroDistribucionVO cdVO;

	public SolicitudVO() {
		
	}
    
    public SolicitudVO(int n, Date f, CentroDistribucionVO centro){
    	this.numero = n;
    	this.fechaEmision =f;
    	this.cdVO = centro;
    }

    public CentroDistribucionVO getCdVO() {
		return cdVO;
	}

	public void setCdVO(CentroDistribucionVO cdVO) {
		this.cdVO = cdVO;
	}

	
	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
