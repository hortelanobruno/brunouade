package brunoli.dao;

import javax.ejb.Local;

import brunoli.entities.Mulo;

@Local
public interface MuloDAOLocal {

	public Mulo getById(Integer id);

}
