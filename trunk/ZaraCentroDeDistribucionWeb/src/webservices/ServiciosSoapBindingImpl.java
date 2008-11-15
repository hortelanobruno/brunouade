/**
 * ServiciosSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package webservices;

import org.apache.log4j.Logger;

public class ServiciosSoapBindingImpl implements webservices.Servicios{
    public boolean recibirSolRep(java.lang.String in0) throws java.rmi.RemoteException {
    	ServiciosImplementacion sI = new ServiciosImplementacion();
    	return sI.recibirSolRep(in0);
    }

    public boolean recibirSolDis(java.lang.String in0) throws java.rmi.RemoteException {
    	ServiciosImplementacion sI = new ServiciosImplementacion();
    	return sI.recibirSolDis(in0);
    }

}
