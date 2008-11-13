package struts.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import vo.ArticuloHeaderVO;
import vo.ArticuloPedidoVO;
import vo.CentroDistribucionVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudDistribucionVO;
import vo.SolicitudFabricaVO;
import vo.TiendaVO;
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
			
			List<ArticuloAReponerVO> artsRep = null;
			SolicitudDeReposicionVO solicitudRep = null;
			List<SolicitudFabricaVO> solsFab = null;
			for(int i=0 ; i<solicitudesDeReposicion.size() ; i++){
				solicitudRep = solicitudesDeReposicion.get(i);
				//Actualizo stock real
				artsRep = new ArrayList<ArticuloAReponerVO>(solicitudRep.getArticulosAReponer());
				bd.actualizarStock(artsRep);
				
				//Actualizo solicitudes de fabricacion
				solsFab = bd.getSolicitudesDeFabricacionAbiertas();
				solsFab = ordenarSolicitudesPorFecha(solsFab);
				for(int j=0 ; j<solsFab.size() ; j++ ){
					actualizarSolsFab(solsFab.get(j),artsRep);
					bd.actualizarSolicitudFabricacion(solsFab.get(j));
				}
				//Proceso la sol rep
				solicitudRep.setProcesada(true);
				bd.guardarSolicitudReposicion(solicitudRep);
			}
			//Chequeo si se pueden atender todas las solicitudes de distribucion
			boolean auto = chequearSiAtenderSolDisAutomaticamente();
			if(auto){
				//Se atendieron todas, notificar
				
			}else{
				//No se puedieron atender a todas, asi que habilitar el boton para que pueda atenderlas
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
		
		for(int i = 0; i<solsDisAbiertas.size();i++)
		{
			SolicitudDistribucionVO sol = solsDisAbiertas.get(i);
			
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

	private List<SolicitudFabricaVO> ordenarSolicitudesPorFecha(List<SolicitudFabricaVO> solsFab) {
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
				
				if(solsFab.get(i).getFechaEmision().after(min.getFechaEmision()))
				{
					min = solsFab.get(i);
				}
			}
			aux.add(min);
			solsFab.remove(min);
		}
		return aux;
	}

	

	private void cargarForm(ReposicionForm frm, long codigoSolRep,
			SolicitudDeReposicionVO solRepVO, ArrayList<Long> codigos,
			ArrayList<String> descripciones, Collection<SolicitudFabricaVO> solFabVO,
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
					cantidadareponer = arti.getCantidadRecibida();
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
