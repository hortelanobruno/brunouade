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
import BusinessLogic.CentroDistribucion;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import beans.AdministradorArticulos;

@Stateless
public class AdministradorArticulosBean implements AdministradorArticulos 
{
	@PersistenceContext(unitName="CentroDeDistribucion")
	private EntityManager em;

	public AdministradorArticulosBean() {
		// TODO Auto-generated constructor stub
	}
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
		em.merge(ah);
	}

	public void guardarArticuloRopa(ArticuloRopaVO art) 
	{
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
		em.merge(ar);
	}
	
	public void guardarDatosCD(int codigo, String nombre, float longitud, float latitud, Vector<String> lineasRopa, Vector<String> categoriasHogar) 
	{
		CentroDistribucion cd = em.find(CentroDistribucion.class, codigo);
		if(cd == null)
		{
			cd = new CentroDistribucion(codigo, nombre,longitud,latitud,lineasRopa,categoriasHogar);
			em.merge(cd);
		}
	}
}
