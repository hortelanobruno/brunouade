package beans;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import BusinessLogic.Articulo;
import BusinessLogic.ArticuloHogar;
import BusinessLogic.ArticuloRopa;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import beans.AdministradorArticulos;

@Stateless
public class AdministradorArticulosBean implements AdministradorArticulos 
{
	@PersistenceContext(unitName="CentroDeDistribucion")
	EntityManager em;

	public Vector<String> getDescripciones(Vector<Long> cods) 
	{
		Vector<String> descs = new Vector<String>();
		for(int i = 0; i < cods.size();i++)
		{
			Query q = em.createQuery("select a from articulos a where a.codigo = :codigo");
			q.setParameter("codigo", cods.elementAt(i));
			List l = q.getResultList();
			descs.add(((Articulo)l.get(0)).getDescripcion());
		}
		return descs;
	}

	public Vector<Integer> getStocks(Vector<Long> cods) 
	{
		Vector<Integer> stocks = new Vector<Integer>();
		for(int i = 0; i < cods.size();i++)
		{
			Query q = em.createQuery("select a from articulos a where a.codigo = :codigo");
			q.setParameter("codigo", cods.elementAt(i));
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
			a.setVO(arts.elementAt(i));
			em.merge(a);
		}
	}

	public void guardarArticuloHogar(ArticuloHogarVO art)
	{
		System.out.println("voy a guardar el articulo hogar");
		ArticuloHogar ah = new ArticuloHogar();
		ah.setCodigo(art.getCodigo());
		ah.setCantidad(art.getCantidad());
		ah.setCategoria(art.getCategoria());
		ah.setColor(art.getColor());
		ah.setComposicion(art.getComposicion());
		ah.setDescripcion(art.getDescripcion());
		ah.setDetalles(art.getDetalles());
		ah.setLinea(art.getLinea());
		ah.setPrecio(art.getPrecio());
		ah.setSeccion(art.getSeccion());
		em.persist(ah);
	}

	public void guardarArticuloRopa(ArticuloRopaVO art) 
	{
		System.out.println("voy a guardar el articulo ropa");
		ArticuloRopa ar = new ArticuloRopa();
		ar.setTalle(art.getTalle());
		ar.setOrigen(ar.getOrigen());
		ar.setCodigo(art.getCodigo());
		ar.setCantidad(art.getCantidad());
		ar.setColor(art.getColor());
		ar.setDescripcion(art.getDescripcion());
		ar.setLinea(art.getLinea());
		ar.setPrecio(art.getPrecio());
		ar.setSeccion(art.getSeccion());
		em.persist(ar);
	}
}
