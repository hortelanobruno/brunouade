package src;

import javax.ejb.Stateless;
import BusinessLogic.ServerFacade;
import BusinessLogic.SolicitudDistribucion;

@Stateless
public class ServerFacadeBean implements ServerFacade 
{

	public void guardarSolicitud(SolicitudDistribucion soldist) {
		// TODO Auto-generated method stub
		
	}

	public int getTest() {
		// TODO Auto-generated method stub
		return 5000000;
	}

}
