/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.apache.log4j.Logger;
import radiusserver.RadiusServerImpl;
import snmp.trap.receiver.TrapReceiverIntegration;

/**
 *
 * @author bruno
 */
@Startup
@Singleton
public class RadiusServerBean {

    private Logger logger = Logger.getLogger("radiusServer");
    private TrapReceiverIntegration trapReceiverImpl = null;
    private RadiusServerImpl radiusServerImpl = null;
    @EJB
    private PortalcvWlcFacade portalcvWlcFacade;

    /**
     * Inicializo el Radius Server
     */
    public void initRadiusServer() {
        try {
            logger.info("Init RadiusServer");
            if (radiusServerImpl == null) {
                logger.info("Creo Radius Server");
                radiusServerImpl = new RadiusServerImpl();
                radiusServerImpl.setWLCs(portalcvWlcFacade.getWLCs());
                radiusServerImpl.start();
            }
        } catch (Exception ex) {
            logger.error("ERROR en initRadiusServer.", ex);
        }
    }

    /**
     * Inicializo el SNMP Trap Receiver
     */
    public void initSNMPTrapReceiver() {
        logger.info("Init Trap Receiver");
        if (trapReceiverImpl == null) {
            logger.info("creo Trap receiver");
            trapReceiverImpl = new TrapReceiverIntegration();
            trapReceiverImpl.run();
        }
    }

    @PreDestroy
    public void close() {
        if (radiusServerImpl != null) {
            radiusServerImpl.close();
        }
    }
}
