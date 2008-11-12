package core;

import integracion.RecibirSolFabr;
import integracion.RecibirSolFabrService;
import integracion.RecibirSolFabrServiceLocator;

public class Test 
{
	public static void main(String[] args) 
	{
		RecibirSolFabrService service = new RecibirSolFabrServiceLocator();
		try
		{
			RecibirSolFabr port = service.getRecibirSolFabr();
			boolean a = port.recibirSolFabr("hjoa");
			System.out.println("Recibi: " +a);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
