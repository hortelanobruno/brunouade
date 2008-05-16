package src;

import javax.ejb.Stateless;
import BusinessLogic.ServerFacade;
import BusinessLogic.SolicitudDistribucionVO;

@Stateless
public class ServerFacadeBean implements ServerFacade 
{

	public void guardarSolicitud(SolicitudDistribucionVO soldist) {
		// TODO Auto-generated method stub
		
	}

	public int getTest() {
		// TODO Auto-generated method stub
		return 5000000;
	}

}
