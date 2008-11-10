package integracion;

public class ImplementacionMandarSolFab 
{
	public ImplementacionMandarSolFab()
	{
		
	}
	
	public boolean enviarSolFab(String solFab)
	{
		RecibirSolFabrService service = new RecibirSolFabrServiceLocator();
		
		try
		{
			System.out.println("Enviando Solicitud de fabricacion...");
			RecibirSolFabr port = service.getRecibirSolFabr();
			return port.recibirSolFabr(solFab);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
