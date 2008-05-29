package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import RemoteMVCFramework.Vista;

public class VistaMain extends Vista 
{
    private MenuPrincipal princ;

    public VistaMain(BusinessDelegate mod) 
    {
        super(mod);
        princ = new MenuPrincipal(this);
        princ.setVisible(true);
    }

    
    public void actualizar() 
    {
        getPrinc().updatePanel();
    }
    
    public MenuPrincipal getPrinc() 
    {
        return princ;
    }

    public void setPrinc(MenuPrincipal princ)
    {
        this.princ = princ;
    }          
}
