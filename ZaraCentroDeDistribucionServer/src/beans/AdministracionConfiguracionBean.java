package beans;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import BusinessLogic.CategoriaHogar;
import BusinessLogic.CentroDistribucion;
import BusinessLogic.LineaRopa;
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
			
			/*int numerador = 1;
			Iterator it = lineasRopa.iterator();
			while(it.hasNext())
				cd.getLineasRopa().add(new LineaRopa(numerador++,(String)it.next()));
			
			numerador = 1;
			it = categoriasHogar.iterator();
			
			while(it.hasNext())
				cd.getCategoriasHogar().add(new CategoriaHogar(numerador++,(String)it.next()));*/
			
			System.out.println("ya arme el cd, ahora lo guardo.\n");
			em.persist(cd);
			System.out.println("ya lo guarde.\n");
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
