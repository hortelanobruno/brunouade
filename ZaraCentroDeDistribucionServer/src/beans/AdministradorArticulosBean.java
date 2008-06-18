package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import BusinessLogic.Articulo;
import BusinessLogic.ArticuloAEnviar;
import BusinessLogic.ArticuloAFabricar;
import BusinessLogic.ArticuloHogar;
import BusinessLogic.ArticuloRopa;
import BusinessLogic.Tienda;
import Exceptions.ExistingProductException;
import VO.ArticuloAEnviarVO;
import VO.ArticuloAFabricarVO;
import VO.ArticuloAReponerVO;
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
	
	public ArrayList<String> getDescripciones(ArrayList<Long> cods) 
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
	public ArrayList<Integer> getStocks(ArrayList<Long> cods) 
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

	public void actualizarStock(ArrayList<ArticuloAReponerVO> arts) 
	{
		for(int i = 0; i < arts.size();i++)
		{
			Articulo a = em.find(Articulo.class, arts.get(i).getArt().getCodigo());
			
			if(a != null)
			{
				a.setVO(arts.get(i).getArt());
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
	

	public Vector<TiendaVO> getTiendas() 
	{
		Query q = em.createQuery("SELECT t From Tienda t");
		List l = q.getResultList();
		
		Vector<TiendaVO> ret = new Vector<TiendaVO>();
		
		for(Iterator i = l.iterator();i.hasNext();)
			ret.add(((Tienda)i).getVO());
		
		return ret;
	}


	public void guardarArticulosAEnviar(Collection<ArticuloAEnviarVO> artic2) {
		Iterator it = artic2.iterator();
		while(it.hasNext()){
			ArticuloAEnviarVO artVO = (ArticuloAEnviarVO) it.next();
			ArticuloAEnviar art = new ArticuloAEnviar();
			art.setVO(artVO);
			em.persist(art);
		}
	}

	public void guardarArticulosAFabricar(Collection<ArticuloAFabricarVO> artic2) {
		Iterator it = artic2.iterator();
		while(it.hasNext()){
			ArticuloAFabricarVO artVO = (ArticuloAFabricarVO) it.next();
			ArticuloAFabricar art = new ArticuloAFabricar();
			art.setVO(artVO);
			em.persist(art);
		}
	}

	public void modificarStock(Collection<ArticuloAEnviarVO> artiAEnv) 
	{
		Iterator i = (Iterator)artiAEnv.iterator();
		while(i.hasNext())
		{
			ArticuloAEnviarVO avo = (ArticuloAEnviarVO) i.next();
			Articulo a = em.find(Articulo.class,avo.getArt().getCodigo());
			if(a != null)
			{
				int newCant = a.getCantidad() - avo.getCantidadAEnviar();
				a.setCantidad(newCant);
				em.merge(a);
			}
		}
	}

	public ArrayList<ArticuloAFabricarVO> getArticulosAFabricar() {
		Query q = em.createQuery("SELECT a FROM ArticuloAFabricar a");
		List l = q.getResultList();
		
		ArrayList<ArticuloAFabricarVO> ret = new ArrayList<ArticuloAFabricarVO>();
		Iterator it = l.iterator();
		while(it.hasNext()){
			ArticuloAFabricarVO artVO = ((ArticuloAFabricar)it.next()).getVO();
			ret.add(artVO);
		}
		return ret;
	}

	public boolean existeArticulo(long codigo) 
	{
		return (em.find(Articulo.class, codigo) == null)?false:true;
	}
}
