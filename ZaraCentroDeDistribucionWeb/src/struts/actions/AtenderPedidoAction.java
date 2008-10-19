package struts.actions;

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
 * Este action te lleva a la pagina para atender pedidos, y te carga la tabla de la misma,
 * con cada articulo con sus solicitudes pendientes.
 *
 */

public class AtenderPedidoAction extends Action
{
	private BusinessDelegate bd;
	
	public AtenderPedidoAction()
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