package Vistas;

import GUI.MenuPrincipal;
import RemoteMVCFramework.ProxyModelo;
import RemoteMVCFramework.Vista;
import VO.SolicitudDistribucionVO;

public class VistaMain extends Vista 
{
    private MenuPrincipal princ;

    public VistaMain(ProxyModelo mod) 
    {
        super(mod);
        this.princ = MenuPrincipal.getInstance(this);//new MenuPrincipal(this);
        princ.setVisible(true);
    }

    @Override
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
