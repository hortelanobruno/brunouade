package GUI;

import BusinessLogic.BusinessDelegate;
import BusinessLogic.VistaMain;

public class Main 
{
	public Main()
	{
	    BusinessDelegate modelo = new BusinessDelegate();
	    @SuppressWarnings("unused")
		VistaMain vMain = new VistaMain(modelo);
	    /*@SuppressWarnings("unused")
		ControladorPanelEnvios cMain = new ControladorPanelEnvios(modelo,vMain);*/
	}
    
    public static void main(String[] args) 
    {
    	@SuppressWarnings("unused")
		Main m = new Main();
    }
}
