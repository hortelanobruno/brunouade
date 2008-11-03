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

import struts.forms.CargarArtFabForm;
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
	//private Logger logger;
	
	public GenSolFabAction()
	{
		try
		{
			bd = new BusinessDelegate();
			//PropertyConfigurator.configure("loggin.xml");
			//logger = Logger.getLogger(GenSolFabAction.class);
		}
		catch (ErrorConectionException e) 
		{

		}
		
	}
	
	public ActionForward execute(ActionMapping mapping,	 ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		//Ir a la base a ver si hay articulos a fabricar, y si hay los carga en la tabla, sino
		//redirecciona a la pagina que dice que no hay articulos
		
		//logger.debug("Hacela vos brunoli");
		//System.out.println("HOLA");
		List<ArticuloAFabricarVO> afvo = bd.getArticulosAFabricarVO();
		if(afvo != null)
		{
			CargarArtFabForm gsfForm = (CargarArtFabForm) form;
			List<ArticuloAFabricarVO> artAFab = new ArrayList<ArticuloAFabricarVO>();
			List<ArticuloAFabricarVO> artFabricandose = new ArrayList<ArticuloAFabricarVO>();
			
			for(int i=0; i < afvo.size() ; i++){
				((ArticuloAFabricarVO)afvo.get(i)).setCantMinAPedir((((ArticuloAFabricarVO)afvo.get(i)).getCantidadPedida())*2);
				int cant = ((ArticuloAFabricarVO)afvo.get(i)).getCantidadAFabricar();
				if(cant == 0){
					artAFab.add(((ArticuloAFabricarVO)afvo.get(i)));
				}else{
					artFabricandose.add(((ArticuloAFabricarVO)afvo.get(i)));
				}
			}
			gsfForm.setArticulosAFabricar(artAFab);
			gsfForm.setArticulosFabricandose(artFabricandose);
			return (mapping.findForward("success"));
		}else{
			return (mapping.findForward("failure"));
		}
	}
}
