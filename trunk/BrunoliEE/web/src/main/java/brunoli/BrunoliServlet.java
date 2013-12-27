package brunoli;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import brunoli.entities.Mulo;
import brunoli.facade.MuloFacadeLocal;

import com.google.gson.Gson;

/**
 * Servlet implementation class BrunoliServlet
 */
@WebServlet(name = "/BrunoliServlet", loadOnStartup = 1, urlPatterns = { "/brunoli.ajax" })
public class BrunoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			InitialContext context = new InitialContext();

			MuloFacadeLocal local = (MuloFacadeLocal) context
					.lookup("java:global/BrunoliEE-ear/BrunoliEE-ejb/MuloFacade!brunoli.facade.MuloFacadeLocal");
			Mulo m = local.getById(1);

			// Mulo m = LookupCache.getMuloFacadeLocal().getById(1);
			// Mulo m = new Mulo();
			// m.setId(1);
			// m.setNombre("Soy y asfasdfas o");
			Gson g = new Gson();
			String datos = g.toJson(m);
			response.setContentType("application/json");
			response.setContentLength(datos.length());
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(datos.getBytes());
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
