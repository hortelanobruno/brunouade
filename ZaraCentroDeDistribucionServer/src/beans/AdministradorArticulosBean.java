package beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import BusinessLogic.Articulo;
import BusinessLogic.ArticuloHogar;
import BusinessLogic.ArticuloRopa;
import BusinessLogic.Tienda;
import Exceptions.ExistingProductException;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import VO.TiendaVO;
import beans.AdministradorArticulos;

@Stateless
public class AdministradorArticulosBean implements AdministradorArticulos 
{
	@PersistenceContext(unitName="CentroDeDistribucion")
	private EntityManager em;

	public AdministradorArticulosBean() 
	{

	}
	
	public ArrayList<String> getDescripciones(ArrayList<Integer> cods) 
	{
		ArrayList<String> descs = new ArrayList<String>();
		for(int i = 0; i < cods.size();i++)
		{
			Query q = em.createQuery("SELECT a FROM Articulo a WHERE a.codigo = :codigo");
			q.setParameter("codigo", cods.get(i));
			List l = q.getResultList();
			descs.add(((Articulo)l.get(0)).getDescripcion());
		}
		return descs;
	}
	public ArrayList<Integer> getStocks(ArrayList<Integer> cods) 
	{
		ArrayList<Integer> stocks = new ArrayList<Integer>();
		for(int i = 0; i < cods.size();i++)
		{
			Query q = em.createQuery("SELECT a FROM Articulo a WHERE a.codigo = :codigo");
			q.setParameter("codigo", cods.get(i));
			List l = q.getResultList();
			stocks.add(((Articulo)l.get(0)).getCantidad());
		}
		return stocks;
	}

	public void actualizarStock(Vector<ArticuloHeaderVO> arts) 
	{
		for(int i = 0; i < arts.size();i++)
		{
			Articulo a = em.find(Articulo.class, arts.elementAt(i).getCodigo());
			
			if(a != null)
			{
				a.setVO(arts.elementAt(i));
				em.merge(a);
			}
		}
	}

	public void guardarArticuloHogar(ArticuloHogarVO art) throws ExistingProductException
	{
		ArticuloHogar ah = em.find(ArticuloHogar.class, art.getCodigo());
		
		if(ah == null)
		{
			ah = new ArticuloHogar();
			ah.setArticuloHogarVO(art);
			em.persist(ah);
		}
		else throw new ExistingProductException("El producto ya existe.");
	}

	public void guardarArticuloRopa(ArticuloRopaVO art) throws ExistingProductException 
	{
		ArticuloRopa ar = em.find(ArticuloRopa.class, art.getCodigo());
		
		if(ar == null)
		{
			ar = new ArticuloRopa();
			ar.setArticuloRopaVO(art);
			em.persist(ar);
		}
		else throw new ExistingProductException("El producto ya existe.");
	}
	
	public ArticuloHeaderVO getArticulo(long codigo)
	{
		return em.find(Articulo.class, codigo).getVO();
	}
	
	public void guardarArticulosAFabricar()
	{
		
	}

	public Vector<TiendaVO> getTiendas() 
	{
		Query q = em.createQuery("SELECT t From Tienda t");
		List l = q.getResultList();
		
		Vector<TiendaVO> ret = new Vector<TiendaVO>();
		
		for(Iterator i = l.iterator();i.hasNext();)
			ret.add(((Tienda)i).getVO());
		
		return ret;
	}

	public void guardarArticulosAEnviar() 
	{

	}
}
