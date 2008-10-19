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
 *	Este action genera con AJAX la solicitud de envio correspondiente a los datos de la tabla de la
 *  solicitud de distribucion.
 *  
 */
public class AtenderSolicitudDistribucionAction extends Action
{
	private BusinessDelegate bd;
	
	public AtenderSolicitudDistribucionAction()
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
