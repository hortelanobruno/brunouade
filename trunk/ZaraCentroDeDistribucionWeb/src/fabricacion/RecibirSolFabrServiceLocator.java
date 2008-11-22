/**
 * RecibirSolFabrServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package fabricacion;

import varios.Constantes;

public class RecibirSolFabrServiceLocator extends org.apache.axis.client.Service implements fabricacion.RecibirSolFabrService {

    public RecibirSolFabrServiceLocator() {
    }


    public RecibirSolFabrServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RecibirSolFabrServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RecibirSolFabr
    private java.lang.String RecibirSolFabr_address = "http://"+Constantes.IP_FABRICA+":8080/axis/services/RecibirSolFabr";

    public java.lang.String getRecibirSolFabrAddress() {
        return RecibirSolFabr_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RecibirSolFabrWSDDServiceName = "RecibirSolFabr";

    public java.lang.String getRecibirSolFabrWSDDServiceName() {
        return RecibirSolFabrWSDDServiceName;
    }

    public void setRecibirSolFabrWSDDServiceName(java.lang.String name) {
        RecibirSolFabrWSDDServiceName = name;
    }

    public fabricacion.RecibirSolFabr getRecibirSolFabr() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RecibirSolFabr_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRecibirSolFabr(endpoint);
    }

    public fabricacion.RecibirSolFabr getRecibirSolFabr(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            fabricacion.RecibirSolFabrSoapBindingStub _stub = new fabricacion.RecibirSolFabrSoapBindingStub(portAddress, this);
            _stub.setPortName(getRecibirSolFabrWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRecibirSolFabrEndpointAddress(java.lang.String address) {
        RecibirSolFabr_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (fabricacion.RecibirSolFabr.class.isAssignableFrom(serviceEndpointInterface)) {
                fabricacion.RecibirSolFabrSoapBindingStub _stub = new fabricacion.RecibirSolFabrSoapBindingStub(new java.net.URL(RecibirSolFabr_address), this);
                _stub.setPortName(getRecibirSolFabrWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RecibirSolFabr".equals(inputPortName)) {
            return getRecibirSolFabr();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:fabricacion", "RecibirSolFabrService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:fabricacion", "RecibirSolFabr"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RecibirSolFabr".equals(portName)) {
            setRecibirSolFabrEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
