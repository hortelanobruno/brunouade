package GUI;

import BusinessLogic.BusinessDelegate;
import BusinessLogic.ControladorMain;
import BusinessLogic.VistaMain;

public class Main 
{
	public Main()
	{
	    BusinessDelegate modelo = new BusinessDelegate();
	    VistaMain vMain = new VistaMain(modelo);
	    ControladorMain cMain = new ControladorMain(modelo,vMain);
	}
    
    public static void main(String[] args) 
    {
    	Main m = new Main();
    }
}
