/**
 * ServiciosSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package webservices;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import struts.model.BusinessDelegate;
import vo.ArticuloAFabricarVO;
import vo.ArticuloHeaderVO;
import vo.ArticuloPedidoVO;
import vo.ArticuloReservadoVO;
import vo.CentroDistribucionVO;
import vo.SolicitudDistribucionVO;
import vo.TiendaVO;
import exceptions.ErrorConectionException;


public class ServiciosSoapBindingImpl implements webservices.Servicios{
	
	private Logger logger = Logger.getLogger("zara.centro");
	private BusinessDelegate bd;
	
    public boolean recibirSolRep(java.lang.String in0) throws java.rmi.RemoteException {
        return false;
    }

    public boolean recibirSolDis(java.lang.String in0) throws java.rmi.RemoteException {
    	try {
			bd = new BusinessDelegate();
			SolicitudDistribucionVO soldis = generarSolDisFromString(in0);
			soldis.setIdDis(bd.getNextIdSolDis());
			
			ArrayList<Long> codigos = new ArrayList<Long>();
			int idMax = bd.getNextIdArtPed();
			Iterator arts = (Iterator) soldis.getArticulosPedidos().iterator();
			ArrayList<ArticuloPedidoVO> artsVO = new ArrayList<ArticuloPedidoVO>();
			while (arts.hasNext()) {
				ArticuloPedidoVO artVO = ((ArticuloPedidoVO) arts.next());
				artVO.setIdAP(idMax);
				idMax++;
				codigos.add(artVO.getArt().getCodigo());
				artsVO.add(artVO);
			}
			ArrayList<Long>  verCod = bd.existenArts(codigos);
			if(!verCod.isEmpty()){
				String codsfalse = "Cod. "+verCod.get(0);
				for(int q = 1 ; q < verCod.size() ; q++){
					codsfalse = codsfalse + " Cod. "+verCod.get(q);
				}
				logger.debug(": La solicitud contiene articulos que no existen en el Centro de Distribucion\n");
			}else{
				soldis.setArticulosPedidos(artsVO);
				int id = bd.getNextId();
				soldis.setId(id);
				CentroDistribucionVO centroVO = bd.getCentro();
				soldis.setCdVO(centroVO);
				soldis.setCerrada(false);
			}
			Collection<ArticuloAFabricarVO> artiAFab = (Collection<ArticuloAFabricarVO>) articulosFabricarDeTabla(soldis);
			Collection<ArticuloReservadoVO> artiReser = (Collection<ArticuloReservadoVO>) articulosEnviarDeTabla();
			bd.guardarSolicitud(soldis);
			bd.guardarArticulosReservados(artiReser);
			bd.guardarArticulosAFabricar(artiAFab);
			bd.modificarStock(artiReser);
			logger.debug("Solicitudes de Distribucion guardada en el Centro de Distribucion\n");
			
		} catch (ErrorConectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
    }

    private Collection<ArticuloAFabricarVO> articulosFabricarDeTabla(SolicitudDistribucionVO soldis) {
		Collection<ArticuloAFabricarVO> art = new ArrayList<ArticuloAFabricarVO>();
		ArticuloHeaderVO arti;
		int idMax = bd.getNextIdAFab();
		List<ArticuloPedidoVO> arts = (List<ArticuloPedidoVO>) soldis.getArticulosPedidos();
		for (int i = 0; i < arts.size(); i++) {
			
			int ped = Integer.parseInt((((DefaultTableModel) tableArticulos
					.getModel()).getValueAt(i, 4)).toString());
			int sel = Integer.parseInt((((DefaultTableModel) tableArticulos
					.getModel()).getValueAt(i, 6)).toString());
			if (ped > sel) {
				long cod = (Long
						.parseLong((String) ((DefaultTableModel) tableArticulos
								.getModel()).getValueAt(i, 2)));
				arti = ((BusinessDelegate) vistaSolDis.getModelo())
						.getArticulo(cod);
				ArticuloAFabricarVO aFab = new ArticuloAFabricarVO();
				aFab.setArt(arti);
				aFab.setCantidadPedida(ped - sel);
				aFab.setCantidadRecibida(0);
				aFab.setCantidadAFabricar(0);
				// aFab.setFabrica()
				// aFab.setIdAAF();
				aFab.setIdAAF(idMax);
				idMax++;
				aFab.setSol(solDisVO);
				art.add(aFab);
			}
		}
		return art;
	}
    
    private Collection<ArticuloReservadoVO> articulosEnviarDeTabla() {
		Collection<ArticuloReservadoVO> art = new ArrayList<ArticuloReservadoVO>();
		ArticuloHeaderVO arti;
		int idMax = ((BusinessDelegate)this.ref.getVistaSolDis().getModelo()).getNextIdARes();
		for (int i = 0; i < tableArticulos.getRowCount(); i++) {
			int cant = Integer.parseInt((((DefaultTableModel) tableArticulos.getModel()).getValueAt(i, 6)).toString());
			if(cant > 0){
				long cod = (Long
						.parseLong((String) ((DefaultTableModel) tableArticulos
								.getModel()).getValueAt(i, 2)));
				arti = ((BusinessDelegate) vistaSolDis.getModelo()).getArticulo(cod);
				ArticuloReservadoVO aRes = new ArticuloReservadoVO();
				aRes.setIdAR(idMax);
				idMax++;
				aRes.setArt(arti);
				aRes.setCantidadReservada(cant);
				aRes.setSolDis(solDisVO);
				art.add(aRes);
			}
		}
		return art;
	}
    
    private SolicitudDistribucionVO generarSolDisFromString(String in0){
    	SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(in0);
		Document doc;
		try {
			doc = builder.build(in);
			Element root = doc.getRootElement();
			SolicitudDistribucionVO soldis = new SolicitudDistribucionVO();
			soldis.setFechaEmision(new Date(root.getChild("fechaSolicitud").getText()));
			Element items = root.getChild("items");
			List hijos = items.getChildren();
			List<ArticuloPedidoVO> articulos = new ArrayList<ArticuloPedidoVO>();
			for(Object hijo : hijos){
				ArticuloPedidoVO articulo = new ArticuloPedidoVO();
				ArticuloHeaderVO art = new ArticuloHeaderVO();
				TiendaVO tienda = new TiendaVO();
				tienda.setCodigoTienda(Integer.parseInt(((Element)hijo).getChild("tienda").getChild("codigo").getText()));
				tienda.setNombreTienda(((Element)hijo).getChild("tienda").getChild("nombre").getText());
				articulo.setTienda(tienda);
				art.setCodigo(Integer.parseInt(((Element)hijo).getChild("articulo").getChild("referencia").getText()));
				articulo.setCantidad(Integer.parseInt(((Element)hijo).getChild("catidad").getText()));
				articulo.setArt(art);
				articulos.add(articulo);
			}
			soldis.setArticulosPedidos(articulos);
			return soldis;
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
