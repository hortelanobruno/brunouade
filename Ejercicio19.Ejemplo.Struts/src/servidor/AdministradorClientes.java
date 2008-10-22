package servidor;

import javax.ejb.Remote;

@Remote
public interface AdministradorClientes {
	
	public int crearNuevoCliente(String cliente, String direccion);
}
