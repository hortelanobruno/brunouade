package struts.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.Locale;
import org.apache.struts.util.MessageResources;

import struts.forms.AltaClienteForm;
import struts.model.BusinessDelegate;
import util.BookBean;


/**
 * Invoca al bean con la lógica de negocio, para efectuar el alta del cliente.
 * 
 * @author rorosco
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AltaClienteAction extends Action {
	BusinessDelegate businessDelegate; 
	
	public AltaClienteAction() {
		//Instancia el bean de lógica de negocio				
        businessDelegate = new BusinessDelegate();
		
	}
	
	
		/**
    	* Procesa la peticion HTTP (request) especificada y genera su correspondiente
    	* respuesta HTTP (response) (o lo redirige a otro componente web que podria crear).
    	* Devuelve una instancia code>ActionForward</code> que describe a DONDE y COMO
    	* se redirige el control, o sino, si la respuesta se ha completado se devolveria
    	* <code>null</code>
    	*
    	* @param mapping El mapeo utilizado para seleccionar esta instancia
    	* @param request El request que estamos procesando
    	* @param actionForm La instancia ActionForm que estamos utilizando (si la hay)
    	* @param response La respuesta HTTP que creamos.
    	*
    	* @exception IOException en caso de error de entrada/salida (i/o)
    	* @exception ServletException en caso de error de servlet
    	*/
        public ActionForward execute(ActionMapping mapping,	 ActionForm form,
    						HttpServletRequest request, HttpServletResponse response)
    						throws IOException, ServletException {

            ActionErrors errors = null;
    		try
    		{
   			
    			//Invoca al proceso de carga y recoge el valor de vuelta
    			errors = businessDelegate.crearNuevoCliente(form);
    			if (errors==null ) {//Se ejecutó correctamente
    			    System.out.println(" **********  Todo OK ! ");
    				return (mapping.findForward("success"));
    			}
    			else { //Hubo errores
    			    System.out.println(" **********  Errores ! ");
    				return (mapping.findForward("failure"));
    			}	
    		}
    		catch (Exception e)
    		{
    			return (mapping.findForward("failure"));
    		}
        }


    }