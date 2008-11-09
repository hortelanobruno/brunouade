/**
 * ServiciosSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package webservices;

public class ServiciosSoapBindingImpl implements webservices.Servicios{
    public boolean recibirSolRep(java.lang.String in0) throws java.rmi.RemoteException {
        return false;
    }

    public boolean recibirSolDis(java.lang.String in0) throws java.rmi.RemoteException {
        System.out.println(in0);
    	return true;
    }

}
