package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;

import struts.model.BusinessDelegate;
import vo.ArticuloAEnviarVO;
import vo.ArticuloPedidoVO;
import vo.ArticuloReservadoVO;
import vo.SolicitudDistribucionVO;
import exceptions.ErrorConectionException;

public class CargarSolicitudDistribucionServlet extends HttpServlet {

	private ServletContext context;
	private BusinessDelegate bd;
	
	public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
        try {
			bd = new BusinessDelegate();
		} catch (ErrorConectionException e) {
			e.printStackTrace();
		}
    }
	
	public  void doPost(HttpServletRequest request, HttpServletResponse  response)
    throws IOException, ServletException {
		int codSolDis = Integer.parseInt(request.getParameter("codigo"));
		SolicitudDistribucionVO solDis = bd.obtenerSolicitudDistribucion(codSolDis);
		ArrayList<ArticuloReservadoVO> articulosReservados = bd.obtenerArticulosReservados(codSolDis);
		ArrayList<Long> codigos = new ArrayList<Long>();
		Iterator solArt = solDis.getArticulosPedidos().iterator();
		while(solArt.hasNext()){
			ArticuloPedidoVO artPed = (ArticuloPedidoVO) solArt.next();
			codigos.add(artPed.getArt().getCodigo());
		}
		ArrayList<Integer> stocks = bd.getStocks(codigos);
		ArrayList<ArticuloAEnviarVO> artsAEnviar = bd.getArtsAEnv(codSolDis);
		cargarTable(solDis,articulosReservados,stocks,artsAEnviar,response);
    }
    
    
    private void cargarTable(SolicitudDistribucionVO solDis2, ArrayList<ArticuloReservadoVO> articulosReservados2, ArrayList<Integer> stocks, ArrayList<ArticuloAEnviarVO> artsAEnviar,HttpServletResponse  response) {
    	try {
    	Iterator artsReserv = articulosReservados2.iterator();
		Iterator artsPed = solDis2.getArticulosPedidos().iterator();
		Iterator artsEnvs = artsAEnviar.iterator();
		ArticuloAEnviarVO artEnv;
		int count = 0;
		ArticuloReservadoVO artRes;
		int cantres = 0;
		int cantenv = 0;
		response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("<articulos>");
        response.getWriter().write("<cantidad>"+solDis2.getArticulosPedidos().size()+"</cantidad>");
        response.getWriter().write("<estado>lleno</estado>");
		while(artsPed.hasNext()){
			ArticuloPedidoVO artPed = (ArticuloPedidoVO) artsPed.next();
			int stock = stocks.get(count);
			long codigo = artPed.getArt().getCodigo();
			while(artsReserv.hasNext()){
				artRes = (ArticuloReservadoVO) artsReserv.next();
				if(codigo == artRes.getArt().getCodigo()){
					cantres = artRes.getCantidadReservada();
					break;
				}
			}
			while(artsEnvs.hasNext()){
				artEnv = (ArticuloAEnviarVO) artsEnvs.next();
				if(codigo == artEnv.getArt().getCodigo()){
					cantenv = artEnv.getCantidadAEnviar();
					break;
				}
			}
			//int numero = artPed.getCantidad()-cantenv;
			//if(numero > stock)numero= stock;
		    response.getWriter().write("<articulo>");
            response.getWriter().write("<codigo>"+codigo+"</codigo>");
            response.getWriter().write("<descripcion>"+artPed.getArt().getDescripcion()+"</descripcion>");
            response.getWriter().write("<cantpedida>"+artPed.getCantidad()+"</cantpedida>");
            response.getWriter().write("<cantreservada>"+cantres+"</cantreservada>");
            response.getWriter().write("<stock>"+stock+"</stock>");
            response.getWriter().write("<cantenviada>"+cantenv+"</cantenviada>");
            response.getWriter().write("</articulo>");
			artsReserv = articulosReservados2.iterator();
			artsEnvs = artsAEnviar.iterator();
			count++;
			cantres = 0;
			cantenv = 0;    
		}
		response.getWriter().write("</articulos>");
		} catch (IOException e) {
			System.out.println("Error cargando los articulos de la solicitud de distribucion en el servlet de ajax para genenvios");
		}
    }
}
