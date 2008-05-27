package beans;

import javax.ejb.Remote;

import BusinessLogic.ArticuloHogarVO;

@Remote
public interface AdministradorArticulos 
{
	public void agregarArticuloHogar(ArticuloHogarVO art);
}
