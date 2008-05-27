package beans;

import javax.ejb.Remote;

import BusinessLogic.*;

@Remote
public interface AdministradorArticulos 
{
	public void agregarArticuloHogar(ArticuloHogarVO art);
	public void agregarArticuloRopa(ArticuloRopaVO art);
}
