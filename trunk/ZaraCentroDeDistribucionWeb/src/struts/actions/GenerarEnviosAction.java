package struts.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.forms.GenerarEnviosForm;
import struts.model.BusinessDelegate;
import vo.SolicitudDistribucionVO;
import exceptions.ErrorConectionException;


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
			this.bd = new BusinessDelegate();
		}
		catch(ErrorConectionException e)
		{
			
		}
	}
	
	public ActionForward execute(ActionMapping mapping,	 ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		ArrayList<SolicitudDistribucionVO> lista = this.bd.obtenerSolicitudesDeTiendas();
		
		if(!(lista.isEmpty())){
			List codigos = new ArrayList();
			
			for(int i=0; i < lista.size() ; i++){
				codigos.add(((SolicitudDistribucionVO)lista.get(i)).getIdDis());
			}
			GenerarEnviosForm frm = (GenerarEnviosForm) form;
			frm.setCodigosSolDist(codigos);
			return mapping.findForward("success");
		}else{
			return mapping.findForward("failure");
		}
			
	}
}
