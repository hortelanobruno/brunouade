package struts.model;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.InitialContext;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

import servidor.AdministradorClientes;
import struts.forms.*;
import util.BookBean;

public class BusinessDelegate {
 
	private AdministradorClientes facade;
	private String naming = "AdministradorClientesApp/AdministradorClientesBean/remote";
	
	public BusinessDelegate() {
		// TODO Auto-generated constructor stub
		Hashtable props = new Hashtable();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
		props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
		try {
			InitialContext initialContext = new InitialContext(props);
			facade = (AdministradorClientes) initialContext.lookup(naming);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* doAltaCliente
	* 
	* Efectúa el alta de un cliente
	*
	* @return null si no hay errores o ActionErrors con los 
	* errores capturados en caso de error.
	*/
	public synchronized ActionErrors crearNuevoCliente (ActionForm altaClienteForm)
	{
					 
		//Instancia un objeto ActionErrors para recoger los errores
		ActionErrors theActionErrors = null;
		AltaClienteForm form = (AltaClienteForm) altaClienteForm;
		
		String nombre = form.getNombreCliente();
		String direccion = form.getDireccionCliente();

		System.out.println("Efectua el alta del cliente "+nombre+", con direccion: "+direccion);
		try {
			int codigo = facade.crearNuevoCliente(nombre, direccion);
			Integer i = new Integer(codigo);
			form.setCodigoCliente(i);
			ArrayList<BookBean> books = new ArrayList<BookBean>();
			books.add(new BookBean("1","Al fin"));
			books.add(new BookBean("2","Al fin222222222"));
			books.add(new BookBean("3","Al fin33333333333"));
			form.setBooks(books);
			if (codigo == 0)
				theActionErrors = new ActionErrors();
		} catch (Exception e) {
			theActionErrors = new ActionErrors();
		}
		return theActionErrors;
	}	
	
	

	

}




