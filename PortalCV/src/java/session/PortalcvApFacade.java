/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvAp;
import entity.PortalcvApgroup;
import entity.PortalcvWlc;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import snmp.SNMPGetAPsFromWSDL;

/**
 *
 * @author Brunoli
 */
@Stateless
public class PortalcvApFacade extends AbstractFacade<PortalcvAp> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("portal");
    private Logger loggerError = Logger.getLogger("portalError");
    @EJB
    private PortalcvWlcFacade portalcvWlcFacade;
    @EJB
    private PortalcvApgroupFacade portalcvApgroupFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvApFacade() {
        super(PortalcvAp.class);
    }

    public PortalcvAp getAPByMac(String apMac, boolean search) {
        try {
            PortalcvAp ap = (PortalcvAp) em.createNamedQuery("PortalcvAp.findByMac").setParameter("mac", apMac).getSingleResult();
            return ap;
        } catch (Exception ex) {
            //Obtains APs via SNMP and save
            if (search) {
                PortalcvAp apEnc = null;
                try {
                    PortalcvWlc wlc;
                    PortalcvAp ap;
                    List wlcs = portalcvWlcFacade.getWLCs();
                    if (wlcs != null && !wlcs.isEmpty()) {
                        for (Object obj : wlcs) {
                            wlc = (PortalcvWlc) obj;
                            ap = SNMPGetAPsFromWSDL.getSNMPFromWLC(wlc.getIp(), wlc.getSnmpPort(), wlc.getSnmpComunity(), apMac);
                            if (ap != null) {
                                ap.setIdapgroups(portalcvApgroupFacade.getDefaultApGroup());
                                if (ap.getMac().equals(apMac)) {
                                    apEnc = ap;
                                }
                                em.persist(ap);
                                em.flush();
                            }
                        }
                    } else {
                        logger.warn("NO WSDL Configured.");
                        apEnc = new PortalcvAp();
                        apEnc.setMac(apMac);
                        apEnc.setIdapgroups(portalcvApgroupFacade.getDefaultApGroup());
                        em.persist(apEnc);
                        em.flush();
                    }
                } catch (Exception e) {
                    loggerError.error("Error en getAPByMac. ", e);
                }
                if (apEnc == null) {
                    loggerError.error("Error en getAPByMac apEnc null. apMac: " + apMac);
                }
                return apEnc;
            }
        }
        return null;
    }

    public void loadAllAPs() {
        try {
            logger.info("Loading All APs...");
            List wlcs = portalcvWlcFacade.getWLCs();
            PortalcvWlc wlc;
            List<PortalcvAp> aps;
            PortalcvAp ap2;
            PortalcvApgroup group = portalcvApgroupFacade.getDefaultApGroup();
            if (wlcs != null && !wlcs.isEmpty()) {
                for (Object obj : wlcs) {
                    wlc = (PortalcvWlc) obj;
                    aps = SNMPGetAPsFromWSDL.getAllAPsFromWLC(wlc.getIp(), wlc.getSnmpPort(), wlc.getSnmpComunity());
                    for (PortalcvAp ap : aps) {
                        try {
                            ap2 = (PortalcvAp) em.createNamedQuery("PortalcvAp.findByMac").setParameter("mac", ap.getMac()).getSingleResult();
                            ap2.setName(ap.getName());
                            ap2.setDireccion(ap.getDireccion());
                            ap2.setSNMPAPIndex(ap.getSNMPAPIndex());
                            em.persist(ap2);
                            em.flush();
                        } catch (NoResultException ex) {
                            logger.info("New AP: " + ap.toStringAll());
                            ap.setIdapgroups(group);
                            em.persist(ap);
                            em.flush();
                        } catch (Exception ex) {
                            loggerError.error("Error en PortalcvApFacade loadAllAPs. ap: " + ap.toStringAll(), ex);
                        }
                    }
                }
            }
            logger.info("Finish loading All APs.");
        } catch (Exception ex) {
            loggerError.error("Error en PortalcvApFacade loadAllAPs.", ex);
        }
    }
}
