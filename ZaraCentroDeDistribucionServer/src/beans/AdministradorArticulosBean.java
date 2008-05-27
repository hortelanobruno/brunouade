package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import BusinessLogic.ArticuloHogar;
import BusinessLogic.ArticuloHogarVO;
import BusinessLogic.ArticuloRopa;
import BusinessLogic.ArticuloRopaVO;
import beans.AdministradorArticulos;

public @Stateless class AdministradorArticulosBean implements AdministradorArticulos 
{
	@PersistenceContext(unitName="AdminArts")
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
}
