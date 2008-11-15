package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		HashMap<Long,Integer> stocks = bd.getStocks();
		cargarTable(solDis,stocks,response);
    }
    
    
    private void cargarTable(SolicitudDistribucionVO solDis, HashMap<Long,Integer> stocks,HttpServletResponse  response) {
    	try {
		Iterator artsPed = solDis.getArticulosPedidos().iterator();
		response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("<articulos>");
        response.getWriter().write("<cantidad>"+solDis.getArticulosPedidos().size()+"</cantidad>");
        response.getWriter().write("<estado>lleno</estado>");
		while(artsPed.hasNext()){
			ArticuloPedidoVO artPed = (ArticuloPedidoVO) artsPed.next();
			long codigo = artPed.getArt().getCodigo();
			int stock = stocks.get(codigo);
		    response.getWriter().write("<articulo>");
            response.getWriter().write("<codigo>"+codigo+"</codigo>");
            response.getWriter().write("<descripcion>"+artPed.getArt().getDescripcion()+"</descripcion>");
            response.getWriter().write("<cantpedida>"+artPed.getCantidadPedida()+"</cantpedida>");
            response.getWriter().write("<stock>"+stock+"</stock>");
            response.getWriter().write("<cantenviada>"+artPed.getCantidadEnviada()+"</cantenviada>");
            response.getWriter().write("</articulo>");
		}
		response.getWriter().write("</articulos>");
		} catch (IOException e) {
			System.out.println("Error cargando los articulos de la solicitud de distribucion en el servlet de ajax para genenvios");
		}
    }
}
