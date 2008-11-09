package main;

import webservices.Servicios;
import webservices.ServiciosService;
import webservices.ServiciosServiceLocator;

public class Test 
{
	public static void main(String[] args) 
	{
		ServiciosService service = new ServiciosServiceLocator();
		
		try
		{
			Servicios port = service.getServicios();
			
			String soldis = "<SolDist>  " +
					"<fechaSolicitud>2008-10-27 10:11:38.363 GST</fechaSolicitud> " +
					"<items class='set'>"
			    + "<ItemSolDist><tienda><codigo>7</codigo> <nombre>Parque Arauco</nombre> <pais>Chile</pais> </tienda>"
			      +"<catidad>73</catidad>  <articulo>  <referencia>7</referencia>  <color>Unico</color>  </articulo> </ItemSolDist>"
			  + "</items><centro><codigo>2</codigo><nombre>Centro</nombre><pais>Argentina</pais></centro></SolDist>";
			
			boolean r = port.recibirSolDis(soldis);
			
			System.out.println("Devolvio: " + r);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
