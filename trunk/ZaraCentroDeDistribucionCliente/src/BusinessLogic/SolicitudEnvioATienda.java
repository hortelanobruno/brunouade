package BusinessLogic;

public class SolicitudEnvioATienda extends Solicitud
{
    private Tienda tienda;
    
	public Tienda getTienda() 
	{
		return tienda;
	}

	public void setTienda(Tienda tienda) 
	{
		this.tienda = tienda;
	}
}
