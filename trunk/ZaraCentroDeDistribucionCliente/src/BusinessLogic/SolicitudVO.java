package BusinessLogic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

public class SolicitudVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5288912159022449663L;
	protected int numero;
	protected Collection<ArticuloHeaderVO> articulo = new Vector<ArticuloHeaderVO>();
    protected Date fechaEmision;

    public SolicitudVO() {
		
	}
    
    public SolicitudVO(int n, Collection<ArticuloHeaderVO> a, Date f){
    	this.numero = n;
    	this.articulo =a;
    	this.fechaEmision =f;
    }

	public Collection<ArticuloHeaderVO> getArticulo() {
		return articulo;
	}

	public void setArticulo(Collection<ArticuloHeaderVO> articulo) {
		this.articulo = articulo;
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
