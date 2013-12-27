package brunoli.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import brunoli.dao.MuloDAOLocal;
import brunoli.entities.Mulo;

@Stateless
public class MuloFacade implements MuloFacadeLocal, MuloFacadeRemote {

	@EJB
	// @IgnoreDependency
	private MuloDAOLocal muloDao;

	public Mulo getById(Integer id) {
		return muloDao.getById(id);
	}
}
