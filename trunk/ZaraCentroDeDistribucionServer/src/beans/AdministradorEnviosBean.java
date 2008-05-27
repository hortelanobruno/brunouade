package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.AdministradorEnvios;

@Stateless
public class AdministradorEnviosBean implements AdministradorEnvios 
{
	@PersistenceContext(unitName="AdminArts")
	EntityManager em;
}
