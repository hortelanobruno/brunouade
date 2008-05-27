package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.AdministradorDistribucion;

@Stateless
public class AdministradorDistribucionBean implements AdministradorDistribucion 
{
	@PersistenceContext(unitName="CentroDeDistribucion")
	EntityManager em;

}
