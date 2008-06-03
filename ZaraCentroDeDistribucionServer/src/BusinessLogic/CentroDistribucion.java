package BusinessLogic;

import java.util.Collection;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="CentroDeDistribucion")
public class CentroDistribucion 
{
	private int codCentro;
	private String nombreCentro;
	private double latitud;
	private double longitud;
	private Collection<String> lineasRopa;
	private Collection<String> categoriasHogar;
	
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
	
	@Transient
	public Collection<String> getCategoriasHogar() 
	{
		return categoriasHogar;
	}
	
	public void setCategoriasHogar(Vector<String> categoriasHogar) 
	{
		this.categoriasHogar = categoriasHogar;
	}
	
	@Transient
	public Collection<String> getLineasRopa() 
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
