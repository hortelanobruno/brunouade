package brunoli.facade;

import javax.ejb.Local;

import brunoli.entities.Mulo;

@Local
public interface MuloFacadeLocal {
	public Mulo getById(Integer id);
}
