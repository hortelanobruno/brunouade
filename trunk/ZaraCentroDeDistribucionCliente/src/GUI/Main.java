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
	    @SuppressWarnings("unused")
		ControladorMain cMain = new ControladorMain(modelo,vMain);
	}
    
    public static void main(String[] args) 
    {
    	@SuppressWarnings("unused")
		Main m = new Main();
    }
}
