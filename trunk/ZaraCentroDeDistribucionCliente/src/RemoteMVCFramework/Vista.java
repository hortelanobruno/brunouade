/*
 * Ingenieria de Sistemas II - 1C2008
 * Marzo/2008
 * 
 * Ejercicio 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package RemoteMVCFramework;

public abstract class Vista
{
    ProxyModelo modelo;
    Controlador controlador;

    public Vista(ProxyModelo mod) 
    {
        modelo = mod;
        mod.setVista(this);
    }

    public void addControlador(Controlador cp)
    {
        controlador = cp;
    }

    public ProxyModelo getModelo() 
    {
        return modelo;
    }

    public Controlador getControlador() 
    {
        return controlador;
    }
    
    public abstract void actualizar(); 
}
