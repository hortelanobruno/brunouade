/**
 * RecibirSolFabrSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package webServices;

public class RecibirSolFabrSoapBindingImpl implements webServices.RecibirSolFabr{
    public boolean recibirSolFabr(java.lang.String in0) throws java.rmi.RemoteException {
    	System.out.println("llego: "+in0 );
    	if(in0.equalsIgnoreCase("")) return false;
    	else return true;
    }

}
