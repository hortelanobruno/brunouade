package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.AdministradorReposicion;

@Stateless
public class AdministradorReposicionBean implements AdministradorReposicion 
{
	@PersistenceContext(unitName="AdminArts")
	EntityManager em;
}
