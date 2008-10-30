package struts.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.forms.ReposicionForm;
import struts.model.BusinessDelegate;
import vo.ArticuloAFabricarVO;
import vo.ArticuloAReponerVO;
import vo.ArticuloAReponerWebVO;
import vo.ArticuloHeaderVO;
import vo.CentroDistribucionVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudFabricaVO;
import exceptions.ErrorConectionException;


/**
 * 
 * @author Administrador
 *
 *	Este action de carga la tabla de la pagina reposicion
 *
 */

public class ReposicionAction extends Action
{
	private BusinessDelegate bd;
	
	public ReposicionAction()
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
		ReposicionForm frm = (ReposicionForm) form;
		//ACA ESTA solRepVO nos llega por webservice o jms y hay q hacer q vaya agarrando de a una
		SolicitudDeReposicionVO solRepVO = new SolicitudDeReposicionVO();
		if(bd.existeSolRep(solRepVO.getIdRep())){
			System.out.println(new Date()+": Solicitud de Reposicion 'existente' en el Centro de Distribucion \n");
		}else{
			int numSolFab = solRepVO.getSolFab().getIdFab();
			if(!bd.existeSolFab(numSolFab)){
				System.out.println(new Date()+": La Solicitud de Reposicion contiene una Solicitud de Fabricacion que no existe\n");
			}else{
				ArrayList<Long> codigos = new ArrayList<Long>();
				Iterator arts = (Iterator) solRepVO.getArticulosAReponer().iterator();
				while (arts.hasNext()) {
					codigos.add(((ArticuloAReponerVO) arts.next()).getArt().getCodigo());
				}
				ArrayList<Long>  verCod = bd.existenArts(codigos);
				if(!verCod.isEmpty()){
					String codsfalse = "Cod. "+verCod.get(0);
					for(int q = 1 ; q < verCod.size() ; q++){
						codsfalse = codsfalse + " Cod. "+verCod.get(q);
					}
					System.out.println(new Date()+": La solicitud contiene articulos que no existen en el Centro de Distribucion\n");
				}else{
					int idAR = bd.getNextIdARep();
					int id = bd.getNextId();
					solRepVO.setId(id);
					Iterator itit = solRepVO.getArticulosAReponer().iterator();
					ArrayList<ArticuloAReponerVO> artsRep = new ArrayList<ArticuloAReponerVO>();
					while(itit.hasNext()){
						ArticuloAReponerVO aRep = (ArticuloAReponerVO) itit.next();
						aRep.setIdAAR(idAR);
						idAR++;
						artsRep.add(aRep);
					}
					solRepVO.setArticulosAReponer(artsRep);
					ArrayList<ArticuloHeaderVO> articulos = bd.getArticulos(codigos);				
					Iterator itit2 = solRepVO.getArticulosAReponer().iterator();
					Collection<ArticuloAReponerVO> artsReVO = new ArrayList<ArticuloAReponerVO>();
					while(itit2.hasNext()){
						ArticuloAReponerVO artRe = (ArticuloAReponerVO) itit2.next();
						long co = artRe.getArt().getCodigo();
						for(int j=0 ; j < articulos.size() ; j++){
							if(co == articulos.get(j).getCodigo()){
								artRe.setArt(articulos.get(j));
							}
						}
						artsReVO.add(artRe);
					}
					solRepVO.setArticulosAReponer(artsReVO);
					ArrayList<String> descripciones = new ArrayList<String>();
					for(int k=0 ; k < articulos.size() ; k++){
						descripciones.add(articulos.get(k).getDescripcion());
					}
					int idSolFab = solRepVO.getSolFab().getIdFab();
					SolicitudFabricaVO solFabVO = bd.getSolicitudFabricacion(idSolFab);
					solRepVO.setSolFab(solFabVO);
					CentroDistribucionVO centroVO = bd.getCentro();
					solRepVO.setCdVO(centroVO);
					long codigoSolRep = solRepVO.getIdRep();
					String fabrica = solRepVO.getFabrica().getNombreFabrica();
					cargarForm(frm,codigoSolRep,solRepVO, codigos, descripciones, solFabVO,fabrica);
					Iterator it = solRepVO.getArticulosAReponer().iterator();
					while(it.hasNext()){
						ArticuloAReponerVO arti = (ArticuloAReponerVO) it.next();
						cargarArticuloEnSolFab(solFabVO,arti);	
					}
					System.out.println(new Date()+": Solicitud de Reposicion Cargada\n");
					//ACA HAY QUE VER SI SE ACTUALIZA BIEN LA solFabVO
					bd.actualizarSolicitudFabricacion(solFabVO);
					return (mapping.findForward("success"));
				}	
				return (mapping.findForward("failure"));
			}	
			return (mapping.findForward("failure"));
		}
		return (mapping.findForward("failure"));
	}
	
	private void cargarArticuloEnSolFab(SolicitudFabricaVO solFabVO,ArticuloAReponerVO arti)
	{
		Collection<ArticuloAFabricarVO> articulos = solFabVO.getArticulosAFabricar();
		Iterator itArt = articulos.iterator();
		while(itArt.hasNext()){
			ArticuloAFabricarVO art = (ArticuloAFabricarVO)itArt.next();
			if(arti.getArt().getCodigo() == (art.getArt().getCodigo())){
				int cantidad = art.getCantidadRecibida();
				art.setCantidadRecibida(cantidad + arti.getCantidad());
			}
		}
	}

	private void cargarForm(ReposicionForm frm, long codigoSolRep,
			SolicitudDeReposicionVO solRepVO, ArrayList<Long> codigos,
			ArrayList<String> descripciones, SolicitudFabricaVO solFabVO,
			String fabrica) {
		
		Iterator iteradorRep = (Iterator) solRepVO.getArticulosAReponer().iterator();
		Iterator iteradorFab = (Iterator) solFabVO.getArticulosAFabricar().iterator();
		int cantidpedida = 0;
		int cantidadareponer = 0;
		int catidadafabricar = 0;
		int cantidadrecibida = 0;
		for (int i = 0; i < codigos.size(); i++) {
			while (iteradorRep.hasNext()) {
				ArticuloAReponerVO arti = (((ArticuloAReponerVO) iteradorRep.next()));
				if((arti.getArt().getCodigo()) == codigos.get(i)){
					cantidadareponer = arti.getCantidad();
				}
			}
			while (iteradorFab.hasNext()) {
				ArticuloAFabricarVO arti2 = (((ArticuloAFabricarVO) iteradorFab.next()));
				if((arti2.getArt().getCodigo()) == codigos.get(i)){
					catidadafabricar = (arti2.getCantidadAFabricar());
					cantidpedida = (arti2.getCantidadPedida());
					cantidadrecibida = (arti2.getCantidadRecibida());
				}
			}
			ArticuloAReponerWebVO vo = new ArticuloAReponerWebVO(codigoSolRep,solFabVO.getIdFab(),fabrica,
					codigos.get(i),descripciones.get(i),cantidpedida,catidadafabricar,cantidadrecibida,cantidadareponer);
			frm.getArticulosAReponer().add(vo);
			iteradorRep = (Iterator) solRepVO.getArticulosAReponer().iterator();
			iteradorFab = (Iterator) solFabVO.getArticulosAFabricar().iterator();
		}
	}
}
