package struts.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.forms.CargarGenerarEnviosForm;
import struts.model.BusinessDelegate;
import vo.ArticuloAEnviarVO;
import vo.ArticuloPedidoVO;
import vo.ArticuloReservadoVO;
import vo.SolicitudDistribucionVO;
import vo.SolicitudEnvioVO;
import exceptions.ErrorConectionException;


/**
 * 
 * @author Administrador
 *
 * Este action te genera con AJAX todos las solicitudes de envios segun lo cargado en la tabla.
 * 
 */
public class GenerarSolicitudesDeEnvioAction extends Action
{
	private BusinessDelegate bd;
	
	public GenerarSolicitudesDeEnvioAction()
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
			CargarGenerarEnviosForm frm = (CargarGenerarEnviosForm) form;
			SolicitudEnvioVO solEnvio = new SolicitudEnvioVO();
			int id = bd.getNextId();
			solEnvio.setId(id);
			int idAE = bd.getNextIdAEnv();
			SolicitudDistribucionVO solDis = bd.obtenerSolicitudDistribucion(Integer.parseInt(frm.getIdsoldis()));
			ArrayList<ArticuloAEnviarVO> articulosAEnviar = articulosAEnviarDeTabla(idAE,frm,solDis);
			solEnvio.setArticulosAEnviar(articulosAEnviar);
			solEnvio.setFechaEmision(new Date());
			//ACA FALTA SETEAR LA TIENDA
			int numero = bd.getNumeroSolEnv();
			solEnvio.setIdEnv(numero);
			solEnvio.setCdVO(solDis.getCdVO());
			boolean cerrado = comprobarCerrado(frm);
			if (cerrado){
				solDis.setCerrada(cerrado);
			}
			bd.guardarSolicitudDeEnvio(solEnvio);
			ArrayList<ArticuloReservadoVO> articulosReservados = bd.obtenerArticulosReservados(Integer.parseInt(frm.getIdsoldis()));
			bd.actualizarStock(articulosAEnviar,articulosReservados);
			for(int i = 0 ; i < frm.getCantidadaenviar().length ; i++)
			{
				long codd = Long.parseLong(frm.getCodigo()[i]);
				for(int r=0 ; r < articulosReservados.size() ; r++ ){
					if(codd == articulosReservados.get(r).getArt().getCodigo()){
						int enviar = Integer.parseInt(frm.getCantidadaenviar()[i]);
						if(enviar < articulosReservados.get(r).getCantidadReservada()){
							articulosReservados.get(r).setCantidadReservada(enviar);
						}
					}
				}
			}
			bd.actualizarArticulosReservados(articulosReservados);
			bd.actualizarSolicitudDistribucion(solDis);
			//TODO ACA SE GENERABA EL XML DE LA SOLENVIO
			return mapping.findForward("success");
		} catch (NumberFormatException e) {
			System.out.println("Error generando la solicitud de envio");
			return mapping.findForward("failure");
		}
	}
	
	public boolean comprobarCerrado(CargarGenerarEnviosForm frm){
		boolean cerrado = true;
		for(int i = 0 ; i < frm.getCantidadaenviar().length ; i++){
			int cantPed = Integer.parseInt(frm.getCantidadpedida()[i]);
			int cantEnv = Integer.parseInt(frm.getCantidadaenviar()[i]) +
			Integer.parseInt(frm.getCantidadenviada()[i]);
			if(cantPed != cantEnv){
				cerrado = false;
			}
		}
		return cerrado;
	}
	
	private ArrayList<ArticuloAEnviarVO> articulosAEnviarDeTabla(int idAE,CargarGenerarEnviosForm frm,SolicitudDistribucionVO solDis) {
		ArrayList<ArticuloAEnviarVO> articulosAEnviar = new ArrayList<ArticuloAEnviarVO>();
		Iterator art = solDis.getArticulosPedidos().iterator();
		for(int i = 0 ; i < frm.getCantidadaenviar().length ; i++){
			int cantEnv = Integer.parseInt(frm.getCantidadaenviar()[i]);
			if(cantEnv > 0){
				long codigo = Long.parseLong(frm.getCodigo()[i]);
				ArticuloAEnviarVO artEnv = new ArticuloAEnviarVO();
				artEnv.setSolDis(solDis);
				artEnv.setCantidadAEnviar(cantEnv);
				artEnv.setIdAAE(idAE);
				while(art.hasNext()){
					ArticuloPedidoVO artPed = (ArticuloPedidoVO) art.next();
					if(artPed.getArt().getCodigo() == codigo){
						artEnv.setArt(artPed.getArt());
					}
				}
				articulosAEnviar.add(artEnv);
				art = solDis.getArticulosPedidos().iterator();
				idAE++;
			}
		}
		return articulosAEnviar;
	}
}
