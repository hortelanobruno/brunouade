package struts.actions;

import integracion.ImplementacionMandarSolFab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.forms.GenerarSolFabForm;
import struts.model.BusinessDelegate;
import varios.XMLConverter;
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
	private Logger logger = Logger.getLogger("zara.centro");
	
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
				solFab.setFabrica(bd.getFabricas().get(0));
				solFab.setFechaEmision(new Date());
				int idSolFab = bd.getNumeroSolFab();
				solFab.setIdFab(idSolFab);
				solFab.setCerrada(false);
				String xmlSolFab = XMLConverter.getStringFromSolFab(solFab);
				ImplementacionMandarSolFab envSolFab = new ImplementacionMandarSolFab();
				boolean estado = envSolFab.enviarSolFab(xmlSolFab);
				logger.debug("Se envio la solicitud de fabricacion a la Fabrica");
				if(estado){
					bd.guardarSolicitudFabricacion(solFab);
					logger.debug("La solicitud de fabricacion fue recibida con exito");
					return (mapping.findForward("success"));
				}else{
					logger.debug("La recepcion de la solicitud de fabricacion ha fallado");
					return (mapping.findForward("failure"));
				}
			}
		} catch (NumberFormatException e) {
			logger.debug("Error al generar la solicitud de fabricacion");
			e.printStackTrace();
			return (mapping.findForward("failure"));
		}
		return (mapping.findForward("failure"));
	}
}
