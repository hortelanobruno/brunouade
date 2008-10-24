package struts.actions;

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
		try {
			GenerarSolFabForm frm = (GenerarSolFabForm) form;
			List<ArticuloAFabricarVO> arts = bd.getArticulosAFabricarVO();
			List<ArticuloAFabricarVO> articulosAFAb = new ArrayList<ArticuloAFabricarVO>();
			for(int i=0;i<frm.getCodigo().length;i++){
				long codigo = Long.parseLong(frm.getCodigo()[i]);
				int cantFab = Integer.parseInt(frm.getCantAFab()[i]);
				if(cantFab > 0){
					ArticuloAFabricarVO artVO = null;
					for(int j=0; j < arts.size() ; j++){
						artVO = (ArticuloAFabricarVO) arts.get(j);
						if(artVO.getArt().getCodigo() == codigo){
							artVO.setCantidadAFabricar(cantFab);
							articulosAFAb.add(artVO);
							arts.remove(j);
							break;
						}
					}
				}
			}
			if(!articulosAFAb.isEmpty()){
				SolicitudFabricaVO solFab = new SolicitudFabricaVO();
				int id = bd.getNextId();
				solFab.setId(id);
				solFab.setArticulosAFabricar(articulosAFAb);
				CentroDistribucionVO centroVO = bd.getCentro();
				solFab.setCdVO(centroVO);
				//TODO Aca habia algo de que agarra la fabrica, que hacemos??
				solFab.setFechaEmision(new Date());
				int idSolFab = bd.getNumeroSolFab();
				solFab.setIdFab(idSolFab);
				solFab.setCerrada(false);
				bd.guardarSolicitudFabricacion(solFab);
				// TODO aca hay que hacer que te genere el xml y haga algo
				
				// TODO poner mensaje al log
				return (mapping.findForward("gensolfabok"));
			}
		} catch (NumberFormatException e) {
			// TODO poner mensaje al log
			e.printStackTrace();
			return (mapping.findForward("gensolfabfalse"));
		}
		return (mapping.findForward("gensolfabfalse"));
	}
}
