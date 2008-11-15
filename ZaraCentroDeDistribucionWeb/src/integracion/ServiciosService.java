/**
 * ServiciosService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package integracion;

public interface ServiciosService extends javax.xml.rpc.Service {
    public java.lang.String getServiciosAddress();

    public integracion.Servicios getServicios() throws javax.xml.rpc.ServiceException;

    public integracion.Servicios getServicios(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
