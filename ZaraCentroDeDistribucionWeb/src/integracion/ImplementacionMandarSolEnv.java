package integracion;

public class ImplementacionMandarSolEnv {

	public ImplementacionMandarSolEnv() {

	}
	
	public boolean enviarSolEnv(String solEnv)
	{
		ServiciosService service = new ServiciosServiceLocator();
		
		try
		{
			System.out.println("Enviando Solicitud de fabricacion...");
			Servicios port = service.getServicios();
			return port.recibirEnvio(solEnv);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
