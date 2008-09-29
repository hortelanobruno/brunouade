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
import Exceptions.ErrorConectionException;

/**
 * 
 * @author Administrador
 *
 *	Este action carga con AJAX la tabla de los articulos pedidos por la solicitud de distribucion
 *  seleccionada de la lista.
 *
 */
public class CargarSolicitudDistribucionAction extends Action
{
	private BusinessDelegate bd;
	
	public CargarSolicitudDistribucionAction()
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
