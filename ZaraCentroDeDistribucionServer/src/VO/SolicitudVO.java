package VO;

import java.io.Serializable;
import java.util.Date;

public class SolicitudVO implements Serializable
{
	private static final long serialVersionUID = 5288912159022449663L;
    protected Date fechaEmision;
    protected CentroDistribucionVO cdVO;
    protected int id;
    
	public SolicitudVO() {
		
	}
    
    public SolicitudVO(int id,Date f, CentroDistribucionVO centro){
    	this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
