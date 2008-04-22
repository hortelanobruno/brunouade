package server;

import javax.ejb.Stateful;
import BusinessLogic.ModeloCDBusinessDelegate;

@Stateful
public class ServidorZaraBean implements ModeloCDBusinessDelegate
{
	private static final long serialVersionUID = -8405463230113129035L;

	public String getDescripcion(int codigo) 
	{
		return null;
	}

	public String getStock(int codigo) 
	{
		return null;
	}
}
