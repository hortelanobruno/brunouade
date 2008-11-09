/**
 * ServiciosSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package webservices;

public class ServiciosSoapBindingSkeleton implements webservices.Servicios, org.apache.axis.wsdl.Skeleton {
    private webservices.Servicios impl;
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
        _oper = new org.apache.axis.description.OperationDesc("recibirSolRep", _params, new javax.xml.namespace.QName("", "recibirSolRepReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:webservices", "recibirSolRep"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("recibirSolRep") == null) {
            _myOperations.put("recibirSolRep", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("recibirSolRep")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("recibirSolDis", _params, new javax.xml.namespace.QName("", "recibirSolDisReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:webservices", "recibirSolDis"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("recibirSolDis") == null) {
            _myOperations.put("recibirSolDis", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("recibirSolDis")).add(_oper);
    }

    public ServiciosSoapBindingSkeleton() {
        this.impl = new webservices.ServiciosSoapBindingImpl();
    }

    public ServiciosSoapBindingSkeleton(webservices.Servicios impl) {
        this.impl = impl;
    }
    public boolean recibirSolRep(java.lang.String in0) throws java.rmi.RemoteException
    {
        boolean ret = impl.recibirSolRep(in0);
        return ret;
    }

    public boolean recibirSolDis(java.lang.String in0) throws java.rmi.RemoteException
    {
        boolean ret = impl.recibirSolDis(in0);
        return ret;
    }

}
