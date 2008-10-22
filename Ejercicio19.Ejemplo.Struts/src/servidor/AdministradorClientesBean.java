package servidor;

import java.util.Vector;

import javax.ejb.Stateless;

public @Stateless class AdministradorClientesBean implements AdministradorClientes {

	private Vector clientes = new Vector();
	private Vector direcciones = new Vector();
	
	
	/**
	 * Crea un nuevo Cliente.
	 * 1.Si cliente no existe => se da de alta
	 * 2.Si cliente existe => no se vuelve a dar de alta
	 */
	public int crearNuevoCliente(String nombre, String direccion) {
		int codigo = 0;
		if (!clientes.contains(nombre))	{
			clientes.add(nombre);
			direcciones.add(direccion);
			codigo = clientes.size() ; // código asignado
		}
		return codigo;
	}

		
}
