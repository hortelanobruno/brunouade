package struts.actions;

import integracion.ImplementacionMandarSolEnv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.forms.CargarGenerarEnviosForm;
import struts.model.BusinessDelegate;
import varios.Constantes;
import varios.XMLConverter;
import vo.ArticuloAEnviarVO;
import vo.ArticuloPedidoVO;
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
	private Logger logger = Logger.getLogger("zara.centro");
	
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
			int idAE = bd.getNextIdAEnv();
			SolicitudDistribucionVO solDis = bd.obtenerSolicitudDistribucion(Integer.parseInt(frm.getIdsoldis()));
			ArrayList<ArticuloAEnviarVO> articulosAEnviar = articulosAEnviarDeTabla(idAE,frm,solDis);
			actualizarSolDis(solDis,articulosAEnviar);
			List<ArticuloPedidoVO> artsPed = new ArrayList<ArticuloPedidoVO>(solDis.getArticulosPedidos());
			Collection<ArticuloAEnviarVO> artsAEnvTienda1 = new ArrayList<ArticuloAEnviarVO>();
			Collection<ArticuloAEnviarVO> artsAEnvTienda2 = new ArrayList<ArticuloAEnviarVO>();
			int codTienda1 = bd.obtenerTiendas().get(0).getCodigoTienda();
			for(int i=0 ; i < artsPed.size() ; i++){
				ArticuloPedidoVO artPed = artsPed.get(i);
				if(artPed.getTienda().getCodigoTienda()==codTienda1){
					for(int j=0 ; j < articulosAEnviar.size() ; j++){
						if(articulosAEnviar.get(j).getArt().getCodigo()==artPed.getArt().getCodigo()){
							artsAEnvTienda1.add(articulosAEnviar.get(j));
						}
					}
				}else{
					for(int j=0 ; j < articulosAEnviar.size() ; j++){
						if(articulosAEnviar.get(j).getArt().getCodigo()==artPed.getArt().getCodigo()){
							artsAEnvTienda2.add(articulosAEnviar.get(j));
						}
					}
				}
			}
			boolean cerrado = comprobarCerrado(frm);
			if (cerrado){
				solDis.setCerrada(cerrado);
			}
			bd.actualizarStock(articulosAEnviar);
			bd.actualizarSolicitudDistribucion(solDis);
			if (!artsAEnvTienda1.isEmpty()) {
				//Genero envio a la tienda 1
				SolicitudEnvioVO solEnv = new SolicitudEnvioVO();
				solEnv.setArticulosAEnviar(artsAEnvTienda1);
				solEnv.setCdVO(bd.getCentro());
				solEnv.setFechaEmision(new Date());
				solEnv.setId(bd.getNextId());
				solEnv.setIdEnv(bd.getNextIdSolDis());
				solEnv.setSolDis(solDis);
				solEnv.setTienda(bd.obtenerTiendas().get(0));
				bd.guardarSolicitudDeEnvio(solEnv);
				String xmlSolEnv = XMLConverter.getStringFromSolEnv(solEnv);
				ImplementacionMandarSolEnv envSolEnv = new ImplementacionMandarSolEnv();
				Constantes.IP_TINEDADINAMICA = Constantes.IP_TIENDA1;
				boolean b = envSolEnv.enviarSolEnv(xmlSolEnv);
				if(b){
					logger.debug("Se envio la solicitud de envio correctamente a la tienda");
				}else{
					logger.debug("Error al enviar la solicitud de envio a la tienda");
					return mapping.findForward("failure");
				}
			}
			if (!artsAEnvTienda2.isEmpty()) {
				//Genero envio a la tienda 2
				SolicitudEnvioVO solEnv = new SolicitudEnvioVO();
				solEnv.setArticulosAEnviar(artsAEnvTienda2);
				solEnv.setCdVO(bd.getCentro());
				solEnv.setFechaEmision(new Date());
				solEnv.setId(bd.getNextId());
				solEnv.setIdEnv(bd.getNextIdSolDis());
				solEnv.setSolDis(solDis);
				solEnv.setTienda(bd.obtenerTiendas().get(1));
				bd.guardarSolicitudDeEnvio(solEnv);
				String xmlSolEnv = XMLConverter.getStringFromSolEnv(solEnv);
				ImplementacionMandarSolEnv envSolEnv = new ImplementacionMandarSolEnv();
				Constantes.IP_TINEDADINAMICA = Constantes.IP_TIENDA2;
				boolean b = envSolEnv.enviarSolEnv(xmlSolEnv);
				if(b){
					logger.debug("Se envio la solicitud de envio correctamente a la tienda");
				}else{
					logger.debug("Error al enviar la solicitud de envio a la tienda");
					return mapping.findForward("failure");
				}
			}
			return mapping.findForward("success");
		} catch (NumberFormatException e) {
			System.out.println("Error generando la solicitud de envio");
			return mapping.findForward("failure");
		}
	}
	
	private void actualizarSolDis(SolicitudDistribucionVO solDis,ArrayList<ArticuloAEnviarVO> articulosAEnviar) {
		List<ArticuloPedidoVO> artsPed = new ArrayList<ArticuloPedidoVO>(solDis.getArticulosPedidos());
		for(int i=0 ; i < articulosAEnviar.size() ; i++){
			ArticuloAEnviarVO art = articulosAEnviar.get(i);
			int cantEnv = art.getCantidadAEnviar();
			if(cantEnv >0){
				long cod = art.getArt().getCodigo();
				for(int j=0 ; j < artsPed.size() ; j++){
					if(artsPed.get(j).getArt().getCodigo()==cod){
						artsPed.get(j).setCantidadEnviada(artsPed.get(j).getCantidadEnviada()+cantEnv);
					}
				}
			}
		}
		solDis.setArticulosPedidos(artsPed);
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
				artEnv.setCantidadAEnviar(cantEnv);
				artEnv.setIdAAE(idAE);
				while(art.hasNext()){
					ArticuloPedidoVO artPed = (ArticuloPedidoVO) art.next();
					if(artPed.getArt().getCodigo() == codigo){
						artEnv.setArt(artPed.getArt());
					}
				}
				artEnv.setIdPedido(solDis.getIdPedido());
				articulosAEnviar.add(artEnv);
				art = solDis.getArticulosPedidos().iterator();
				idAE++;
			}
		}
		return articulosAEnviar;
	}
}
