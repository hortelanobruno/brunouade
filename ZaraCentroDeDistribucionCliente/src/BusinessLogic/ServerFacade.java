package BusinessLogic;

import javax.ejb.Remote;

@Remote
public interface ServerFacade 
{
	public void guardarSolicitud(SolicitudDistribucionVO soldist);
	public int getTest();
}
