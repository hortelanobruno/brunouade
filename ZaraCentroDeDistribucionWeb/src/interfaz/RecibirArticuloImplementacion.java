package interfaz;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import exceptions.ErrorConectionException;
import exceptions.ExistingProductException;
import struts.model.BusinessDelegate;
import vo.ArticuloHeaderVO;
import vo.ArticuloHogarVO;
import vo.ArticuloPedidoVO;
import vo.ArticuloRopaVO;
import vo.SolicitudDistribucionVO;
import vo.TiendaVO;

public class RecibirArticuloImplementacion {

	private BusinessDelegate bd;
	
	public RecibirArticuloImplementacion() {
		
	}
	
	public void guardarArticuloFromJMS(String msj){
		try {
			bd = new BusinessDelegate();
			
			SAXBuilder builder = new SAXBuilder();
			Reader in = new StringReader(msj);
			Document doc;
			try {
				doc = builder.build(in);
				Element root = doc.getRootElement();
				SolicitudDistribucionVO soldis = new SolicitudDistribucionVO();
				if(root.getChild("talle") == null){
					//Articulo Hogar
					ArticuloHogarVO artHogarVO = new ArticuloHogarVO();
					artHogarVO.setCantidad(0);
					artHogarVO.setCategoria(root.getChild("categoria").getText());
					artHogarVO.setCodigo(Long.parseLong(root.getChild("referencia").getText()));
					artHogarVO.setColor(root.getChild("color").getText());
					artHogarVO.setComposicion(root.getChild("compocicion").getText());
					artHogarVO.setDescripcion(root.getChild("descripcion").getText());
					artHogarVO.setDetalles(root.getChild("nombre").getText());
					artHogarVO.setLinea(root.getChild("linea").getText());
					artHogarVO.setMedidas(root.getChild("medidas").getText());
					artHogarVO.setPrecio(Float.parseFloat(root.getChild("precioVentaUnitario").getText()));
					artHogarVO.setSeccion(root.getChild("seccion").getText());
					bd.guardarArticuloHogar(artHogarVO);
				}else{
					//Articulo Personal
					ArticuloRopaVO artRopaVO = new ArticuloRopaVO();
					artRopaVO.setCantidad(0);
					artRopaVO.setCodigo(Long.parseLong(root.getChild("referencia").getText()));
					artRopaVO.setColor(root.getChild("color").getText());
					artRopaVO.setDescripcion(root.getChild("descripcion").getText());
					artRopaVO.setLinea(root.getChild("linea").getText());
					artRopaVO.setOrigen(root.getChild("origen").getText());
					artRopaVO.setPrecio(Float.parseFloat(root.getChild("precioVentaUnitario").getText()));
					artRopaVO.setSeccion(root.getChild("seccion").getText());
					artRopaVO.setTalle(root.getChild("talle").getText());
					bd.guardarArticuloRopa(artRopaVO);
				}
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ExistingProductException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ErrorConectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
