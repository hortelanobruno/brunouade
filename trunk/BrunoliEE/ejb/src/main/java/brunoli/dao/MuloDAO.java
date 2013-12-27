package brunoli.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import brunoli.entities.Mulo;

@Stateless
public class MuloDAO implements MuloDAOLocal {
	
	@PersistenceContext(name="BRUNOLIDS",unitName="BRUNOLIDS")
	private EntityManager em;

	public Mulo getById(Integer id) {
		return em.find(Mulo.class, id);
	}
}
