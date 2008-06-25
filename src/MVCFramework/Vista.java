/*
 * Ingenieria de Sistemas II - 1C2008
 * Marzo/2008
 * 
 * Ejercicio 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package MVCFramework;

import BusinessLogic.BusinessDelegate;

public abstract class Vista
{
    protected BusinessDelegate modelo;
    protected Controlador controlador;

    public Vista(BusinessDelegate bd) 
    {
        modelo = bd;
        bd.setVista(this);
    }

    public void addControlador(Controlador cp)
    {
        controlador = cp;
    }

    public BusinessDelegate getModelo() 
    {
        return modelo;
    }

    public Controlador getControlador() 
    {
        return controlador;
    }
    
    public abstract void actualizar(); 
}
