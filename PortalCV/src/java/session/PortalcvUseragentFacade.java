/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvUseragent;
import entity.PortalcvWlcaccounting;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import ua_parser.Client;
import ua_parser.Parser;

/**
 *
 * @author bruno
 */
@Stateless
public class PortalcvUseragentFacade extends AbstractFacade<PortalcvUseragent> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    private Logger loggerError = Logger.getLogger("portalError");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvUseragentFacade() {
        super(PortalcvUseragent.class);
    }

    public PortalcvUseragent generateUserAgent(String userAgent) {
        PortalcvUseragent ua = null;
        PortalcvUseragent ua2 = null;
        try {
            if (userAgent != null) {
                ua = new PortalcvUseragent();
                //Parseo useragent
                Parser uaParser = new Parser();
                Client c = uaParser.parse(userAgent);
                ua.setFullUserAgent(userAgent);
                ua.setDevice(c.device.family);
                ua.setOs(c.os.family);
                if (c.os.major != null && c.os.minor != null) {
                    ua.setOsVersion(c.os.major + "." + c.os.minor);
                }
                ua.setBrowser(c.userAgent.family);
                if (c.userAgent.major != null && c.userAgent.minor != null) {
                    ua.setBrowserVersion(c.userAgent.major + "." + c.userAgent.minor);
                }

                //busco useragent si existe
                ua = (PortalcvUseragent) em.createNamedQuery("PortalcvUseragent.findByAll")
                        .setParameter("device", ua.getDevice()).setParameter("os", ua.getOs())
                        .setParameter("browser", ua.getBrowser()).setParameter("osVersion", ua.getOsVersion()).
                        setParameter("browserVersion", ua.getBrowserVersion()).setParameter("fullUserAgent", ua.getFullUserAgent()).getSingleResult();
            }
        } catch (NoResultException ex) {
            try {
                List lista = em.createNamedQuery("PortalcvUseragent.findByAllLessFullUserAgent")
                        .setParameter("device", ua.getDevice()).setParameter("os", ua.getOs())
                        .setParameter("browser", ua.getBrowser()).setParameter("osVersion", ua.getOsVersion()).
                        setParameter("browserVersion", ua.getBrowserVersion()).getResultList();
                if (!lista.isEmpty()) {
                    ua2 = (PortalcvUseragent) lista.get(0);
                    ua2.setFullUserAgent(ua.getFullUserAgent());
                    em.persist(ua2);
                    em.flush();
                    ua = ua2;
                } else {
                    em.persist(ua);
                    em.flush();
                }
            } catch (Exception exx) {
                loggerError.error("Error en generateUserAgent. userAgent: " + userAgent + ". ua: " + ua, exx);
                em.persist(ua);
                em.flush();
            }
        } catch (Exception ex) {
            loggerError.error("Error en generateUserAgent. userAgent: " + userAgent, ex);
        }
        return ua;
    }
}
