package struts.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import Exceptions.ErrorConectionException;
import struts.model.BusinessDelegate;


/**
 * 
 * @author Administrador
 *
 *	Este action carga la lista de solicitudes de distribucion en la pagina de generar envios.
 *
 */

public class GenerarEnviosAction extends Action
{
	private BusinessDelegate bd;
	
	public GenerarEnviosAction()
	{
		try
		{
			bd = new BusinessDelegate();
		}
		catch(ErrorConectionException e)
		{
			
		}
	}
	
	public ActionForward execute(ActionMapping mapping,	 ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		return null;
	}
}
