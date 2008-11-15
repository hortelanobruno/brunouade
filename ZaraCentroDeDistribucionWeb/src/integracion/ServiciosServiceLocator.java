/**
 * ServiciosServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package integracion;

public class ServiciosServiceLocator extends org.apache.axis.client.Service implements integracion.ServiciosService {

	String ip;
	
    public ServiciosServiceLocator(String ip) {
    	this.ip = ip;
    }


    public ServiciosServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServiciosServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Servicios
    private java.lang.String Servicios_address = "http://"+ip+":8080/axis/services/Servicios";

    public java.lang.String getServiciosAddress() {
        return Servicios_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServiciosWSDDServiceName = "Servicios";

    public java.lang.String getServiciosWSDDServiceName() {
        return ServiciosWSDDServiceName;
    }

    public void setServiciosWSDDServiceName(java.lang.String name) {
        ServiciosWSDDServiceName = name;
    }

    public integracion.Servicios getServicios() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Servicios_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServicios(endpoint);
    }

    public integracion.Servicios getServicios(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            integracion.ServiciosSoapBindingStub _stub = new integracion.ServiciosSoapBindingStub(portAddress, this);
            _stub.setPortName(getServiciosWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServiciosEndpointAddress(java.lang.String address) {
        Servicios_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (integracion.Servicios.class.isAssignableFrom(serviceEndpointInterface)) {
                integracion.ServiciosSoapBindingStub _stub = new integracion.ServiciosSoapBindingStub(new java.net.URL(Servicios_address), this);
                _stub.setPortName(getServiciosWSDDServiceName());
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
        if ("Servicios".equals(inputPortName)) {
            return getServicios();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:integracion", "ServiciosService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:integracion", "Servicios"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Servicios".equals(portName)) {
            setServiciosEndpointAddress(address);
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
