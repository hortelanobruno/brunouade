package BusinessLogic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import VO.TiendaVO;

@Entity
@Table(name="tiendas")
public class Tienda implements Serializable
{
	private static final long serialVersionUID = 1277997535507848892L;
	private int codigoTienda;
	private String nombreTienda;
	
	public Tienda()
	{
		
	}
	
	public Tienda(int cod, String nombre)
	{
		this.setCodTienda(cod);
		this.setNombreTienda(nombre);
	}
	
	@Id
	@Column(name="CodTienda")
	public int getCodTienda() 
	{
		return codigoTienda;
	}
	
	public void setCodTienda(int codTienda) 
	{
		this.codigoTienda = codTienda;
	}
	
	@Column
	public String getNombreTienda() 
	{
		return nombreTienda;
	}
	
	public void setNombreTienda(String nombreTienda) 
	{
		this.nombreTienda = nombreTienda;
	}
	
	@Transient
	public TiendaVO getVO(){
		TiendaVO vo = new TiendaVO(codigoTienda,nombreTienda);
		return vo;
	}

	public void setVO(TiendaVO vo){
		this.setCodTienda(vo.getCodigoTienda());
		this.setNombreTienda(vo.getNombreTienda());
	}
}
