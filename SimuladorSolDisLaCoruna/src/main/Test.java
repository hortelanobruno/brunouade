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
			    + "<ItemSolDist><tienda><codigo>1</codigo> <nombre>Florida</nombre> <pais>Chile</pais> </tienda>"
			      +"<catidad>1000</catidad>  <articulo>  <referencia>1002</referencia>  <color>Unico</color>  </articulo> </ItemSolDist>"
			  + "</items><centro><codigo>1</codigo><nombre>Shopping Centro</nombre><pais>Argentina</pais></centro></SolDist>";
			
			System.out.println("Mandando...");
			boolean r = port.recibirSolDis(soldis);
			
			System.out.println("Devolvio: " + r);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Se pudrio todo!");
		}
	}
}
