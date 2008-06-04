package BusinessLogic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CentroDeDistribucion")
public class CentroDistribucion implements Serializable
{
	private static final long serialVersionUID = 1459311864724348026L;
	private int codCentro;
	private String nombreCentro;
	private float latitud;
	private float longitud;
	private Collection<LineaRopa> lineasRopa;
	private Collection<CategoriaHogar> categoriasHogar;
	
	public CentroDistribucion()
	{
		
	}
	
	public CentroDistribucion(int codigo, String nombre, float longitud, float latitud, Vector<String> lineasRopa, Vector<String> categoriasHogar)
	{
		this.setCodCentro(codigo);
		this.setNombreCentro(nombre);
		this.setLongitud(longitud);
		this.setLatitud(latitud);
		this.setLineasRopa(lineasRopa);
		this.setCategoriasHogar(categoriasHogar);
	}
	
	@Id
	@Column(name="CodCentro")
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
	public float getLatitud() 
	{
		return latitud;
	}
	
	public void setLatitud(float latitud) 
	{
		this.latitud = latitud;
	}
	
	@Column
	public float getLongitud()
	{
		return longitud;
	}
	
	public void setLongitud(float longitud)
	{
		this.longitud = longitud;
	}

	@OneToMany
	public Collection<CategoriaHogar> getCategoriasHogar()
	{
		return this.categoriasHogar;
	}

	public void setCategoriasHogar(Collection<String> catHogar) 
	{
		int numerador = 1;
		Iterator it = catHogar.iterator();
		this.categoriasHogar = new Vector<CategoriaHogar>();
		
		while(it.hasNext())
			this.categoriasHogar.add(new CategoriaHogar(numerador++,(String)it.next()));
	}

	@OneToMany
	public Collection<LineaRopa> getLineasRopa()
	{
		return this.lineasRopa;
	}

	public void setLineasRopa(Collection<String> liRopa) 
	{
		int numerador = 1;
		Iterator it = liRopa.iterator();
		this.lineasRopa= new Vector<LineaRopa>();
		
		while(it.hasNext())
			this.lineasRopa.add(new LineaRopa(numerador++,(String)it.next()));
	}
}
