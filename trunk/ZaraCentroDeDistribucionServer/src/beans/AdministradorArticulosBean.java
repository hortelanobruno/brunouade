package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import BusinessLogic.ArticuloHogarVO;
import beans.AdministradorArticulos;

public @Stateless class AdministradorArticulosBean implements AdministradorArticulos 
{
	@PersistenceContext(unitName="AdminArts")
	EntityManager em;
	
	public void agregarArticuloHogar(ArticuloHogarVO art) 
	{
		
	}
}
