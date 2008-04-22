/*
 * Ingenieria de Sistemas II - 1C2008
 * Marzo/2008
 * 
 * Ejercicio 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package RemoteMVCFramework;

public abstract class Controlador 
{
	Vista vista;
	ProxyModelo modelo;
	
	public Controlador(ProxyModelo mod, Vista vis) 
        {
            vista = vis;
            modelo = mod;
            vista.addControlador(this);
	}

	public ProxyModelo getModelo() 
        {
            return modelo;
	}
	
	public Vista getVista() 
        {
            return vista;
	}
}
