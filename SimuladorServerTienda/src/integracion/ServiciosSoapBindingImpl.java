/**
 * ServiciosSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package integracion;

public class ServiciosSoapBindingImpl implements integracion.Servicios{
    public boolean recibirEnvio(java.lang.String in0) throws java.rmi.RemoteException {
    	System.out.println("llego " + in0);
    	if(in0.equalsIgnoreCase(""))return false;
    	else return true;
    }

    public boolean recibirOfad(java.lang.String in0) throws java.rmi.RemoteException {
    	System.out.println(in0);
        return false;
    }

}
