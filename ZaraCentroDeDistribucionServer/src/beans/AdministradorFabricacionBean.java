package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.AdministradorFabricacion;

@Stateless
public class AdministradorFabricacionBean implements AdministradorFabricacion 
{
	@PersistenceContext(unitName="AdminArts")
	EntityManager em;
}
