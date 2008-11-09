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
			
			String solrep = "<src.RepAF> <centroDistribucionReferencia>5</centroDistribucionReferencia>"
			  + "<cantidad>550.0</cantidad> <productoList class='list'><int>9697005</int> </productoList></src.RepAF>";
			
			boolean r = port.recibirSolRep(solrep);
			
			System.out.println("Devolvio: " + r);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
