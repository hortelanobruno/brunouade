package struts.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import vo.ArticuloPedidoVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudDistribucionVO;
import vo.SolicitudFabricaVO;
import webservices.ServiciosImplementacion;
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
	
	/*
	 * 1) Levantar de la base todas las solicitudes de reposicion que no han sido procesadas
	 * 2) Actualizar stock real
	 * 3) Actualizar las solicitudes de fabricacion que generaron esta solicitud de reposicion
	 * 4) Si se pueden atender a todos los pedidos, se atienden y se notifica, sino no hace nada y
	 *    habilita el boton para atender a los que quiera
	 * 
	 */
	public ActionForward execute(ActionMapping mapping,	 ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		try{
			ReposicionForm frm = (ReposicionForm) form;
			//Obtiene las solicitudes de reposicion a procesar
			List<SolicitudDeReposicionVO> solicitudesDeReposicion = bd.obtenerSolicitudesDeReposicionAProcesar();
			if(solicitudesDeReposicion.isEmpty()){
				return (mapping.findForward("failure"));
			}
			List<ArticuloAReponerVO> artsRep = null;
			SolicitudDeReposicionVO solicitudRep = null;
			List<SolicitudFabricaVO> solsFab = null;
			solsFab = bd.getSolicitudesDeFabricacionAbiertas();
			solsFab = ordenarSolicitudesPorAntiguedad(solsFab);
			List<SolicitudFabricaVO> allSolsFab = new ArrayList<SolicitudFabricaVO>(solsFab);
			cargarForm(frm,solicitudesDeReposicion,allSolsFab);
			for(int i=0 ; i<solicitudesDeReposicion.size() ; i++){
				solicitudRep = solicitudesDeReposicion.get(i);
				//Actualizo stock real
				artsRep = new ArrayList<ArticuloAReponerVO>(solicitudRep.getArticulosAReponer());
				bd.actualizarStock(artsRep);
				//Proceso la sol rep
				solicitudRep.setProcesada(true);
				bd.guardarSolicitudReposicion(solicitudRep);
				//Actualizo solicitudes de fabricacion
				for(int j=0 ; j<solsFab.size() ; j++ ){
					actualizarSolsFab(solsFab.get(j),artsRep);
					bd.actualizarSolicitudFabricacion(solsFab.get(j));
				}
			}
			//Chequeo si se pueden atender todas las solicitudes de distribucion
			boolean auto = chequearSiAtenderSolDisAutomaticamente();
			if(auto){
				//Se atendieron todas, notificar
				request.setAttribute("prenderBoton", "no");
			}else{
				//No se puedieron atender a todas, asi que habilitar el boton para que pueda atenderlas
				request.setAttribute("prenderBoton", "si");
			}
			return (mapping.findForward("success"));
		}catch(Exception e){
			return (mapping.findForward("failure"));
		}
	}


	private boolean chequearSiAtenderSolDisAutomaticamente() {
		// TODO Chequea si se pueden atender todas los sol dis que no estan cerradas
		// Si se pueden atender, se atiende y devuelve true,
		// sino no hace nada y devuelve false
		
		List<SolicitudDistribucionVO> solsDis = bd.obtenerSolDisAbiertas();
		HashMap<Long,Integer> stocks = bd.getStocks();
		for(int i=0 ; i < solsDis.size() ; i++){
			SolicitudDistribucionVO sol = (SolicitudDistribucionVO) solsDis.get(i);
			List<ArticuloPedidoVO> artsPed = new ArrayList<ArticuloPedidoVO>(sol.getArticulosPedidos());
			for(int j=0 ; j<artsPed.size() ; j++){
				ArticuloPedidoVO art = (ArticuloPedidoVO) artsPed.get(j);
				long codigo = art.getArt().getCodigo();
				int cantPed = art.getCantidadPedida();
				int stock = stocks.get(codigo);
				if(stock>=cantPed){
					stocks.put(codigo, stock-cantPed);
				}else{
					return false;
				}
			}
		}
		//Se pueden atender a todos
		atenderTodasLasSolDis();
		return true;
	}

	private void atenderTodasLasSolDis() {
		// TODO Atender a todas las soldis que hay en la base
		// Generar Envios
		List<SolicitudDistribucionVO> solsDisAbiertas = bd.obtenerSolDisAbiertas();
		ServiciosImplementacion serviciosImplementacion = new ServiciosImplementacion();
		for(int i = 0; i<solsDisAbiertas.size();i++)
		{
			SolicitudDistribucionVO sol = solsDisAbiertas.get(i);
			serviciosImplementacion.enviarArticulosDisponibles(sol);//Este metodo ya lo usamos para gensoldis
			//bd.actualizarSolicitudDistribucion(sol);
		}
	}

	private void actualizarSolsFab(SolicitudFabricaVO solicitudFabricaVO,List<ArticuloAReponerVO> artsRep) {
		// TODO Actualizar la solicitud, y setear "cerrada" si corresponde
		List<ArticuloAFabricarVO> artsAFab = new ArrayList<ArticuloAFabricarVO>( solicitudFabricaVO.getArticulosAFabricar());
		
		for(int i = 0; i<artsAFab.size();i++)
		{
			long id = artsAFab.get(i).getArt().getCodigo();
			for(int j = 0; j < artsRep.size();j++)
			{
				if(artsRep.get(j).getArt().getCodigo() == id)
				{
					int cantARep = artsRep.get(j).getCantidadRecibida();      //10 -> Recibi 10 ahora
					int cantRecibida = artsAFab.get(i).getCantidadRecibida(); //40 -> Recibi antes
					int cantAFab = artsAFab.get(i).getCantidadAFabricar(); 	  //50 -> Mande
					
					if(cantARep > 0)
					{
						if(cantARep >= (cantAFab-cantRecibida) )
						{
							//Puedo satisfacer todo
							int aux = cantAFab - cantRecibida;  //10
							artsRep.get(j).setCantidadRecibida(artsRep.get(j).getCantidadRecibida() - aux);
							artsAFab.get(i).setCantidadRecibida(artsAFab.get(i).getCantidadRecibida() + aux);
						}
						else
						{
							//No puedo satisfacer todo
							int aux = cantARep;
							artsRep.get(j).setCantidadRecibida(0);
							artsAFab.get(i).setCantidadRecibida(artsAFab.get(i).getCantidadRecibida() + aux);
						}
					}
				}
			}
		}
		
		int aux2 = 0;
		for(int j = 0; j < artsAFab.size();j++)
		{
			if( artsAFab.get(j).getCantidadAFabricar() != artsAFab.get(j).getCantidadRecibida())
				aux2++;
		}
		if(aux2 == 0)solicitudFabricaVO.setCerrada(true);
		else solicitudFabricaVO.setCerrada(false);
		
		Collection<ArticuloAFabricarVO> col = new ArrayList<ArticuloAFabricarVO>(artsAFab);
		solicitudFabricaVO.setArticulosAFabricar(col);
	}

	private List<SolicitudFabricaVO> ordenarSolicitudesPorAntiguedad(List<SolicitudFabricaVO> solsFab) {
		// TODO Ordenar las solicitudes por fecha de forma que el index 0 sea el mas viejo
		List<SolicitudFabricaVO> aux = new ArrayList<SolicitudFabricaVO>();
		int size = solsFab.size();
		SolicitudFabricaVO min = null;
		for(int j = 0; j < size; j++)
		{
			for(int i=0 ; i < solsFab.size() ; i++)
			{
				if(i == 0)
				{
					min = solsFab.get(i);
				}
				
				if(solsFab.get(i).getIdFab()<min.getIdFab())
				{
					min = solsFab.get(i);
				}
			}
			aux.add(min);
			solsFab.remove(min);
		}
		return aux;
	}

	private void cargarForm(ReposicionForm frm,List<SolicitudDeReposicionVO> solsRep, List<SolicitudFabricaVO> allSolsFab) {
		List<ArticuloAReponerWebVO> artsWeb = new ArrayList<ArticuloAReponerWebVO>();
		List<ArticuloAFabricarVO> artsFab = new ArrayList<ArticuloAFabricarVO>();
		for(int i=0 ; i < allSolsFab.size() ; i++){
			artsFab.addAll(allSolsFab.get(i).getArticulosAFabricar());
		}
		for(int i=0 ; i < artsFab.size() ; i++){
			if(artsFab.get(i).getCantidadAFabricar() <= artsFab.get(i).getCantidadRecibida()){
				artsFab.remove(i);
			}
		}
		SolicitudDeReposicionVO solRep = null;
		ArticuloAReponerWebVO artWeb = null;
		ArticuloAReponerVO artRep = null;
		ArticuloAFabricarVO artFab = null;
		List<ArticuloAReponerVO> artsRep = null;
		for(int k=0 ; k< solsRep.size() ; k++){
			solRep = solsRep.get(k);
			artsRep = new ArrayList<ArticuloAReponerVO>(solRep.getArticulosAReponer());
			for(int i=0 ; i<artsRep.size() ; i++){
				artRep = artsRep.get(i);
				artWeb = new ArticuloAReponerWebVO();
				for(int j=0 ; j< artsFab.size() ; j++){
					artFab = artsFab.get(j);
					if(artFab.getArt().getCodigo() == artRep.getArt().getCodigo()){
						artWeb.setCantidadAFabricar(artFab.getCantidadAFabricar());
						break;
					}
				}
				artWeb.setCantidadAReponer(artRep.getCantidadRecibida());
				for(int j=0 ; j< artsFab.size() ; j++){
					artFab = artsFab.get(j);
					if(artFab.getArt().getCodigo() == artRep.getArt().getCodigo()){
						artWeb.setCantidadPedida(artFab.getCantidadPedida());
						break;
					}
				}
				for(int j=0 ; j< artsFab.size() ; j++){
					artFab = artsFab.get(j);
					if(artFab.getArt().getCodigo() == artRep.getArt().getCodigo()){
						artWeb.setCantidadRecibida(artFab.getCantidadRecibida());
						break;
					}
				}
				artWeb.setCodigoArticulo(artRep.getArt().getCodigo());
				artWeb.setCodSolRep(solRep.getIdRep());
				artWeb.setDescripcion(artRep.getArt().getDescripcion());
				artWeb.setFabrica(bd.getFabricas().get(0).getNombreFabrica());
				for(int j=0 ; j < allSolsFab.size() ; j++){
					Iterator<ArticuloAFabricarVO> it = allSolsFab.get(j).getArticulosAFabricar().iterator();
					while(it.hasNext()){
						ArticuloAFabricarVO artvo = it.next();
						if(artvo.getArt().getCodigo() == artRep.getArt().getCodigo()){
							artWeb.setCodSolFab(allSolsFab.get(j).getIdFab());
							break;
						}
					}
				}
			}
			artsWeb.add(artWeb);
		}
		frm.setArticulosAReponer(artsWeb);
	}
}
