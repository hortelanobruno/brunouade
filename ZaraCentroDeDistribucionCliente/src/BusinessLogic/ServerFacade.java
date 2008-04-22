package BusinessLogic;

import javax.ejb.Remote;

@Remote
public interface ServerFacade 
{
	public void guardarSolicitud(SolicitudDistribucion soldist);
}
