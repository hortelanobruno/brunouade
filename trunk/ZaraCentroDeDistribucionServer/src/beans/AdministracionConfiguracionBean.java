package beans;

import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import BusinessLogic.CentroDistribucion;
import beans.AdministracionConfiguracion;

@Stateless
public  class AdministracionConfiguracionBean implements AdministracionConfiguracion 
{
	@PersistenceContext(unitName="CentroDeDistribucion")
	private EntityManager em;
	
	public void guardarDatosCD(int codigo, String nombre, float longitud, float latitud, Vector<String> lineasRopa, Vector<String> categoriasHogar) 
	{
		CentroDistribucion cd = em.find(CentroDistribucion.class, codigo);
		if(cd == null)
		{
			cd = new CentroDistribucion(codigo, nombre,longitud,latitud,lineasRopa,categoriasHogar);
			em.merge(cd);
		}
	}

	public void addCategoriaHogar(String categoria)
	{
		
	}

	public void addLineaRopa(String linea) 
	{
	
	}

	public Vector<String> getCategoriasHogar() 
	{
		return null;
	}

	public Vector<String> getLineasRopa()
	{
		return null;
	}
	
}
