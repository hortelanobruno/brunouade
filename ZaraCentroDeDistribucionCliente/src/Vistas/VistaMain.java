package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import MVCFramework.Vista;

public class VistaMain extends Vista 
{
    private MenuPrincipal ref;

    public VistaMain(BusinessDelegate mod) 
    {
        super(mod);
        
    }

    
    public void actualizar() 
    {
        getRef().updatePanel();
    }
    
    public MenuPrincipal getRef() 
    {
        return ref;
    }

    public void setRef(MenuPrincipal princ)
    {
        this.ref = princ;
    }          
}
