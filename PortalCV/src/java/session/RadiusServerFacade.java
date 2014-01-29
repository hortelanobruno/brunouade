/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvAp;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

/**
 *
 * @author bruno
 */
@Startup
@Stateless
public class RadiusServerFacade implements RadiusServerFacadeLocal {

    private Logger logger = Logger.getLogger("radiusServer");
    @EJB
    private PortalcvApFacade portalcvApFacade;
    @EJB
    private PortalcvWlcaccountingFacade portalcvWlcaccountingFacade;

    @PostConstruct
    public void init() {
        try {
            logger.info("RadiusServerFacade initing ...");
            portalcvApFacade.loadAllAPs();
            logger.info("RadiusServerFacade inited.");
        } catch (Exception ex) {
            logger.error("Error en init.", ex);
        }
    }

    @Override
    public void acctStartWLC(String cpeIP, String cpeMac, String apMac, String sessionId) {
        try {
            if (cpeMac != null) {
                cpeMac = cpeMac.toLowerCase().replaceAll(":", "").replaceAll("-", "");
            }
            if (apMac != null) {
                apMac = apMac.toLowerCase().replaceAll(":", "").replaceAll("-", "");
            }
            logger.info("Llego acctStartWLC: cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". apMac: " + apMac + ". sessionId: " + sessionId + ".");

            PortalcvAp ap = portalcvApFacade.getAPByMac(apMac, true);
            portalcvWlcaccountingFacade.createAcctStart(System.currentTimeMillis(), cpeIP, cpeMac, ap, sessionId);

            logger.info("fin acctStartWLC.");
        } catch (Exception ex) {
            logger.error("Error en acctStartWLC. cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". apMac: " + apMac + ". sessionId: " + sessionId + ".", ex);
        }
    }

    @Override
    public void acctAliveWLC(String cpeIP, String cpeMac, String apMac, String sessionId) {
        try {
            if (cpeMac != null) {
                cpeMac = cpeMac.toLowerCase().replaceAll(":", "").replaceAll("-", "");
            }
            if (apMac != null) {
                apMac = apMac.toLowerCase().replaceAll(":", "").replaceAll("-", "");
            }
            logger.info("Llego acctAlive: cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". apMac: " + apMac + ". sessionId: " + sessionId + ".");

            PortalcvAp ap = portalcvApFacade.getAPByMac(apMac, false);
            if (ap != null) {
                portalcvWlcaccountingFacade.createAcctAlive(System.currentTimeMillis(), cpeIP, cpeMac, ap, sessionId);
            } else {
                logger.warn("Descartando Alive AP null. cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". apMac: " + apMac + ". sessionId: " + sessionId + ".");
            }

            logger.info("fin acctAliveWLC.");
        } catch (Exception ex) {
            logger.error("Error en acctAliveWLC. cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". apMac: " + apMac + ". sessionId: " + sessionId + ".", ex);
        }
    }

    @Override
    public void acctStopWLC(String cpeIP, String cpeMac, String apMac, String sessionId) {
        try {
            if (cpeMac != null) {
                cpeMac = cpeMac.toLowerCase().replaceAll(":", "").replaceAll("-", "");
            }
            if (apMac != null) {
                apMac = apMac.toLowerCase().replaceAll(":", "").replaceAll("-", "");
            }
            logger.info("Llego acctStop: cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". apMac: " + apMac + ". sessionId: " + sessionId + ".");

            portalcvWlcaccountingFacade.createAcctStop(System.currentTimeMillis(), cpeIP, cpeMac, sessionId);

            logger.info("fin acctStopWLC.");
        } catch (Exception ex) {
            logger.error("Error en acctStopWLC. cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". apMac: " + apMac + ". sessionId: " + sessionId + ".", ex);
        }
    }
}
