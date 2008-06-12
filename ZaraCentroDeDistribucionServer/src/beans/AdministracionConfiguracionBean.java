package beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import BusinessLogic.CategoriaHogar;
import BusinessLogic.CentroDistribucion;
import BusinessLogic.Fabrica;
import BusinessLogic.LineaRopa;
import VO.CentroDistribucionVO;
import VO.FabricaVO;
import beans.AdministracionConfiguracion;

@Stateless
public  class AdministracionConfiguracionBean implements AdministracionConfiguracion 
{
	@PersistenceContext(unitName="CentroDeDistribucion")
	private EntityManager em;
	
	public void guardarDatosCD(int codigo, String nombre, float longitud, float latitud, ArrayList<String> lineasRopa, ArrayList<String> categoriasHogar) 
	{
		CentroDistribucion cd = em.find(CentroDistribucion.class, codigo);
		if(cd == null)
		{
			ArrayList<LineaRopa> lr = new ArrayList<LineaRopa>();
			ArrayList<CategoriaHogar> ch = new ArrayList<CategoriaHogar>();
			
			for(int i = 0; i< lineasRopa.size();i++)
				lr.add(new LineaRopa((i+1),lineasRopa.get(i)));	
			
			for(int i = 0; i< categoriasHogar.size(); i++)
				ch.add(new CategoriaHogar((i+1),categoriasHogar.get(i)));

			cd = new CentroDistribucion(codigo, nombre,longitud,latitud,lr,ch);
			System.out.println("ya arme el cd.\n");
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

	public CentroDistribucionVO getCentro() 
	{
		CentroDistribucion centro = em.find(CentroDistribucion.class, 1);
		return centro.getVO();
	}

	public ArrayList<FabricaVO> getFabricas() 
	{
		Query q = em.createQuery("SELECT f From Fabrica f");
		List l = q.getResultList();
		
		ArrayList<FabricaVO> ret = new ArrayList<FabricaVO>();
		
		for(Iterator i = l.iterator();i.hasNext();)
			ret.add(((Fabrica)i).getVO());
		
		return ret;
	}

	public void guardarFabrica(FabricaVO fab) 
	{
		if(em.find(Fabrica.class, fab.getCodigoFabrica()) == null)
		{
			Fabrica f = new Fabrica(fab.getCodigoFabrica(), fab.getNombreFabrica());
			em.persist(f);
		}
	}
}