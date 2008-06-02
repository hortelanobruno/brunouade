/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BusinessLogic;

import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CentroDeDistribucion")
public class CentroDistribucion 
{
	private int codCentro;
	private String nombreCentro;
	private double latitud;
	private double longitud;
	private Vector<String> lineasRopa;
	private Vector<String> categoriasHogar;
	
	public CentroDistribucion(int codigo, String nombre, double longitud, double latitud, Vector<String> lineasRopa, Vector<String> categoriasHogar)
	{
		this.setCodCentro(codigo);
		this.setNombreCentro(nombre);
		this.setLongitud(longitud);
		this.setLatitud(latitud);
		this.setLineasRopa(lineasRopa);
		this.setCategoriasHogar(categoriasHogar);
	}
	
	@Id
	@Column
	public int getCodCentro()
	{
		return codCentro;
	}
	public void setCodCentro(int codCentro) 
	{
		this.codCentro = codCentro;
	}
	
	@Column
	public String getNombreCentro() 
	{
		return nombreCentro;
	}
	
	public void setNombreCentro(String nombreCentro) 
	{
		this.nombreCentro = nombreCentro;
	}
	
	@Column
	public double getLatitud() 
	{
		return latitud;
	}
	
	public void setLatitud(double latitud) 
	{
		this.latitud = latitud;
	}
	
	@OneToMany
	public Vector<String> getCategoriasHogar() 
	{
		return categoriasHogar;
	}
	
	public void setCategoriasHogar(Vector<String> categoriasHogar) 
	{
		this.categoriasHogar = categoriasHogar;
	}
	
	@OneToMany
	public Vector<String> getLineasRopa() 
	{
		return lineasRopa;
	}
	
	public void setLineasRopa(Vector<String> lineasRopa) 
	{
		this.lineasRopa = lineasRopa;
	}
	
	@Column
	public double getLongitud()
	{
		return longitud;
	}
	
	public void setLongitud(double longitud)
	{
		this.longitud = longitud;
	}
}
