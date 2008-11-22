/**
 * RecibirSolFabrSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package fabricacion;

public class RecibirSolFabrSoapBindingSkeleton implements fabricacion.RecibirSolFabr, org.apache.axis.wsdl.Skeleton {
    private fabricacion.RecibirSolFabr impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("recibirSolFabr", _params, new javax.xml.namespace.QName("", "recibirSolFabrReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:fabricacion", "recibirSolFabr"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("recibirSolFabr") == null) {
            _myOperations.put("recibirSolFabr", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("recibirSolFabr")).add(_oper);
    }

    public RecibirSolFabrSoapBindingSkeleton() {
        this.impl = new fabricacion.RecibirSolFabrSoapBindingImpl();
    }

    public RecibirSolFabrSoapBindingSkeleton(fabricacion.RecibirSolFabr impl) {
        this.impl = impl;
    }
    public boolean recibirSolFabr(java.lang.String in0) throws java.rmi.RemoteException
    {
        boolean ret = impl.recibirSolFabr(in0);
        return ret;
    }

}
