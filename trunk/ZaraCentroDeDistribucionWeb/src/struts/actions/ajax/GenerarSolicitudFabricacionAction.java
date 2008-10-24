package struts.actions.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.forms.GenerarSolFabForm;
import struts.model.BusinessDelegate;
import vo.ArticuloAFabricarVO;
import vo.CentroDistribucionVO;
import vo.SolicitudFabricaVO;
import exceptions.ErrorConectionException;


/**
 * 
 * @author Administrador
 *
 *	Este action genera la solicitud a fabricacion con AJAX sacando los datos de la tabla.
 *
 */


public class GenerarSolicitudFabricacionAction extends Action
{
	private BusinessDelegate bd;
	
	public GenerarSolicitudFabricacionAction()
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
		
		GenerarSolFabForm frm = (GenerarSolFabForm) form;
		
		
		//Leo todos los articulos que me llegan y los paso a un array de vo
		List<ArticuloAFabricarVO> arts = new ArrayList<ArticuloAFabricarVO>();
		SolicitudFabricaVO solFab = new SolicitudFabricaVO();
		int id = bd.getNextId();
		solFab.setId(id);
		solFab.setArticulosAFabricar(arts);
		CentroDistribucionVO centroVO = bd.getCentro();
		solFab.setCdVO(centroVO);
		//Aca habia algo de que agarra la fabrica, que hacemos??
		solFab.setFechaEmision(new Date());
		int idSolFab = bd.getNumeroSolFab();
		solFab.setIdFab(idSolFab);
		solFab.setCerrada(false);
		bd.guardarSolicitudFabricacion(solFab);
		
		
		return null;
	}
}
