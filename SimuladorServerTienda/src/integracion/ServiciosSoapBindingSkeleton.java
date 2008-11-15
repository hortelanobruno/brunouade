/**
 * ServiciosSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package integracion;

public class ServiciosSoapBindingSkeleton implements integracion.Servicios, org.apache.axis.wsdl.Skeleton {
    private integracion.Servicios impl;
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
        _oper = new org.apache.axis.description.OperationDesc("recibirEnvio", _params, new javax.xml.namespace.QName("", "recibirEnvioReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:integracion", "recibirEnvio"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("recibirEnvio") == null) {
            _myOperations.put("recibirEnvio", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("recibirEnvio")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("recibirOfad", _params, new javax.xml.namespace.QName("", "recibirOfadReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:integracion", "recibirOfad"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("recibirOfad") == null) {
            _myOperations.put("recibirOfad", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("recibirOfad")).add(_oper);
    }

    public ServiciosSoapBindingSkeleton() {
        this.impl = new integracion.ServiciosSoapBindingImpl();
    }

    public ServiciosSoapBindingSkeleton(integracion.Servicios impl) {
        this.impl = impl;
    }
    public boolean recibirEnvio(java.lang.String in0) throws java.rmi.RemoteException
    {
        boolean ret = impl.recibirEnvio(in0);
        return ret;
    }

    public boolean recibirOfad(java.lang.String in0) throws java.rmi.RemoteException
    {
        boolean ret = impl.recibirOfad(in0);
        return ret;
    }

}
