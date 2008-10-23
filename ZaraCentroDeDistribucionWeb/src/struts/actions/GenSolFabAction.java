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

import struts.forms.GenerarSolFabForm;
import struts.model.BusinessDelegate;
import vo.ArticuloAFabricarVO;
import exceptions.ErrorConectionException;

/**
 * 
 * @author Administrador
 *
 *	Este action carga la tabla de la pagina de articulos a fabricar
 *
 */


public class GenSolFabAction extends Action 
{
	private BusinessDelegate bd;
	
	public GenSolFabAction()
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
		//Ir a la base a ver si hay articulos a fabricar, y si hay los carga en la tabla, sino
		//redirecciona a la pagina que dice que no hay articulos
		
		List<ArticuloAFabricarVO> afvo = bd.getArticulosAFabricarVO();

		if(afvo != null)
		{
			GenerarSolFabForm gsfForm = (GenerarSolFabForm) form;
			
			List<ArticuloAFabricarVO> aux = new ArrayList<ArticuloAFabricarVO>();
			aux.add(afvo.get(0));
			gsfForm.setArticulosAFabricar(aux);
			gsfForm.setPrueba("bruno");
			
			return (mapping.findForward("success"));
		}else{
			return (mapping.findForward("failure"));
		}
	}
}
