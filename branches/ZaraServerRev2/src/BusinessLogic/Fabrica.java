package BusinessLogic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import VO.FabricaVO;

@Entity
@Table(name="fabricas")
public class Fabrica implements Serializable 
{
	private static final long serialVersionUID = 4851504514650207347L;
	private String nombreFabrica;
	private int codigoFabrica;
	
	public Fabrica() {
	
	}
	
	public Fabrica(int c, String n){
		this.nombreFabrica = n;
		this.codigoFabrica = c;
	}

	@Id
	@Column(name="CodFabrica")
	public int getCodigoFabrica() {
		return codigoFabrica;
	}

	public void setCodigoFabrica(int codigoFabrica) {
		this.codigoFabrica = codigoFabrica;
	}

	@Column
	public String getNombreFabrica() {
		return nombreFabrica;
	}

	public void setNombreFabrica(String nombreFabrica) {
		this.nombreFabrica = nombreFabrica;
	}
	
	@Transient
	public FabricaVO getVO(){
		FabricaVO vo = new FabricaVO(codigoFabrica,nombreFabrica);
		return vo;
	}

	public void setVO(FabricaVO vo){
		this.setCodigoFabrica(vo.getCodigoFabrica());
		this.setNombreFabrica(vo.getNombreFabrica());
	}
	
}
