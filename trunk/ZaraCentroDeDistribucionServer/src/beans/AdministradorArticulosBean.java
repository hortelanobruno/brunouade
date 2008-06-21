package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import BusinessLogic.Articulo;
import BusinessLogic.ArticuloAEnviar;
import BusinessLogic.ArticuloAFabricar;
import BusinessLogic.ArticuloHogar;
import BusinessLogic.ArticuloReservado;
import BusinessLogic.ArticuloRopa;
import Exceptions.ExistingProductException;
import VO.ArticuloAEnviarVO;
import VO.ArticuloAFabricarVO;
import VO.ArticuloAReponerVO;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloReservadoVO;
import VO.ArticuloRopaVO;
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

	public void modificarStock(Collection<ArticuloReservadoVO> artiAEnv) 
	{
		Iterator i = (Iterator)artiAEnv.iterator();
		while(i.hasNext())
		{
			ArticuloReservadoVO avo = (ArticuloReservadoVO) i.next();
			Articulo a = em.find(Articulo.class,avo.getArt().getCodigo());
			if(a != null)
			{
				int newCant = a.getCantidad() - avo.getCantidadReservada();
				a.setCantidad(newCant);
				em.merge(a);
			}
		}
	}

	public ArrayList<ArticuloAFabricarVO> getArticulosAFabricar() {
		Query q = em.createQuery("SELECT a FROM ArticuloAFabricar a WHERE cantidadAFabricar =:cant");
		q.setParameter("cant", 0);
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

	public void guardarArticulosReservados(Collection<ArticuloReservadoVO> artiReser) {
		Iterator it = artiReser.iterator();
		while(it.hasNext()){
			ArticuloReservadoVO artVO = (ArticuloReservadoVO) it.next();
			ArticuloReservado art = new ArticuloReservado();
			art.setVO(artVO);
			em.persist(art);
		}
	}
}
