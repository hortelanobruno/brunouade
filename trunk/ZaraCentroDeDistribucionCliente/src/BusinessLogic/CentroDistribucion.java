/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BusinessLogic;

import java.util.Vector;
/**
 *
 * @author Administrador
 */
public class CentroDistribucion {

    private Vector<SolicitudDistribucion> solicitudes = new Vector<SolicitudDistribucion>();

    public Vector<SolicitudDistribucion> getSolicituddistribucion() {
        return solicitudes;
    }

    public void setSolicituddistribucion(Vector<SolicitudDistribucion> solicituddistribucion) {
        this.solicitudes = solicituddistribucion;
    }
    
    public void agregarSolicitud(SolicitudDistribucion sol){
        solicitudes.add(sol);
    }
    
}
