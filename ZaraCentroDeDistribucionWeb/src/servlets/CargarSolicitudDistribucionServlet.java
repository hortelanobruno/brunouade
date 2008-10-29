package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CargarSolicitudDistribucionServlet extends HttpServlet {

	private ServletContext context;
	
	public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
        
    }
	
	public  void doGet(HttpServletRequest request, HttpServletResponse  response)
    throws IOException, ServletException {
        
        
    }
    
    public  void doPost(HttpServletRequest request, HttpServletResponse  response)
    throws IOException, ServletException {
        
    }
	
}
