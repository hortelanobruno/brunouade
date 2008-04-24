package BusinessLogic;

public class SolicitudDistribucion extends Solicitud
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
