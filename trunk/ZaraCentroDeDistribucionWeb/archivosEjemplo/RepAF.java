package src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class RepAF implements Serializable{
	
	int centroDistribucionReferencia;
	float cantidad;
	Collection productoList;
	
	public RepAF(){
		productoList = new ArrayList();
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public int getCentroDistribucionReferencia() {
		return centroDistribucionReferencia;
	}

	public void setCentroDistribucionReferencia(int centroDistribucionReferencia) {
		this.centroDistribucionReferencia = centroDistribucionReferencia;
	}

	public Collection getProductoList() {
		return productoList;
	}

	public void setProductoList(Collection productoList) {
		this.productoList = productoList;
	}
	
	
	
	

}
