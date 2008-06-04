package beans;

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
			System.out.println("ya arme el cd.\n");
			
		/*	for(int i = 0; i< lineasRopa.size();i++)
				this.addLineaRopa((i+1), lineasRopa.elementAt(i));
			System.out.println("ya guarde las lineas de ropa. \n");
			
			for(int i = 0; i< categoriasHogar.size(); i++)
				this.addCategoriaHogar((i+1), categoriasHogar.elementAt(i));
			
			System.out.println("ya guarde las categorias de hogar. \n");*/

			System.out.println("guardo el cd.\n");
			em.persist(cd);
			System.out.println("ya guarde el cd.\n");
		}
	}
	
	public void addCategoriaHogar(int cod, String categoria)
	{
		if(em.find(CategoriaHogar.class, cod) == null)
		{
			CategoriaHogar c = new CategoriaHogar(cod, categoria);
			em.persist(c);
		}
	}

	public void addLineaRopa(int cod, String linea) 
	{
		if(em.find(LineaRopa.class, cod) == null)
		{
			LineaRopa r = new LineaRopa(cod, linea);
			em.persist(r);
		}
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
