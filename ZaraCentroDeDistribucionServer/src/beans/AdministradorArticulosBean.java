package beans;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import BusinessLogic.ArticuloHogar;
import BusinessLogic.ArticuloHogarVO;
import BusinessLogic.ArticuloRopa;
import BusinessLogic.ArticuloRopaVO;
import beans.AdministradorArticulos;

@Stateless
public class AdministradorArticulosBean implements AdministradorArticulos 
{
	@PersistenceContext(unitName="CentroDeDistribucion")
	EntityManager em;
	
	public void agregarArticuloHogar(ArticuloHogarVO art) 
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
		em.persist(ah);
	}

	public void agregarArticuloRopa(ArticuloRopaVO art) 
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
		em.persist(ar);
		
	}

	public Vector<String> getDescripciones(Vector<Integer> cods) 
	{
		Vector<String> descs = new Vector<String>();
		for(int i = 0; i < cods.size();i++)
		{
			Query q = em.createQuery("select a.descripcion from articulos a where a.codigo = :codigo");
			q.setParameter("codigo", cods.elementAt(i));
			List l = q.getResultList();
			descs.add((String)l.get(0));
		}
		return descs;
	}
}
