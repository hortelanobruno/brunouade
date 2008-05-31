package VO;

import java.io.Serializable;

public class FabricaVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7156368985814924419L;
	private String nombreFabrica;
	private int codigoFabrica;
	
	public FabricaVO() {
		// TODO Auto-generated constructor stub
	}
	
	public FabricaVO(int c, String n){
		this.nombreFabrica = n;
		this.codigoFabrica = c;
	}

	public int getCodigoFabrica() {
		return codigoFabrica;
	}

	public void setCodigoFabrica(int codigoFabrica) {
		this.codigoFabrica = codigoFabrica;
	}

	public String getNombreFabrica() {
		return nombreFabrica;
	}

	public void setNombreFabrica(String nombreFabrica) {
		this.nombreFabrica = nombreFabrica;
	}
}
