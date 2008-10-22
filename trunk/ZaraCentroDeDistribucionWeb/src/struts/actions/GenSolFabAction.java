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
		
		//ArrayList<ArticuloAFabricarVO> afvo = bd.getArticulosAFabricarVO();
		List<ArticuloAFabricarVO> afvo = new ArrayList<ArticuloAFabricarVO>();
		ArticuloAFabricarVO a1 = new ArticuloAFabricarVO();
		ArticuloAFabricarVO a2 = new ArticuloAFabricarVO();
		ArticuloAFabricarVO a3 = new ArticuloAFabricarVO();
		a1.setIdAAF(1000);
		a2.setIdAAF(1050);
		a3.setIdAAF(1070);
		
		if(afvo != null)
		{
			GenerarSolFabForm gsfForm = (GenerarSolFabForm)form;
			
			Long[] cods = new Long[afvo.size()];
			Integer[] cantsAFab = new Integer[afvo.size()];
			Integer[] cantsRec = new Integer[afvo.size()];
			Integer[] cantsPed = new Integer[afvo.size()];
			
			for(int i = 0; i< afvo.size();i++)
			{
				cods[i] =  (long) afvo.get(i).getIdAAF();
			}
			
			gsfForm.setCodigo(cods);
			
			return mapping.findForward("gensolfab");
		}
		else return mapping.findForward("nohayarticulosafabricar");
	}
}
