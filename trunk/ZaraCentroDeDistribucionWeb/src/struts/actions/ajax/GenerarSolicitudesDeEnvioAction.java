package struts.actions.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.model.BusinessDelegate;
import exceptions.ErrorConectionException;


/**
 * 
 * @author Administrador
 *
 * Este action te genera con AJAX todos las solicitudes de envios segun lo cargado en la tabla.
 * 
 */
public class GenerarSolicitudesDeEnvioAction extends Action
{
	private BusinessDelegate bd;
	
	public GenerarSolicitudesDeEnvioAction()
	{
		try
		{
			bd = new BusinessDelegate();
		}
		catch (ErrorConectionException e) 
		{

		}
	}
	
	public ActionForward execute(ActionMapping mapping,	 ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		return null;
	}
}
