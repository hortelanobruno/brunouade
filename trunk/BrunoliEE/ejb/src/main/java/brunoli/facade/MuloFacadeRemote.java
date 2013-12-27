package brunoli.facade;

import javax.ejb.Remote;

import brunoli.entities.Mulo;

@Remote
public interface MuloFacadeRemote {
	public Mulo getById(Integer id);
}
