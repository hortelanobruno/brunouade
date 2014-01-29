/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Userdata;
import java.util.Calendar;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import ua_parser.Client;
import ua_parser.Parser;

/**
 *
 * @author bruno
 */
@Stateless
public class UserdataFacade extends AbstractFacade<Userdata> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    private Logger loggerError = Logger.getLogger("portalError");
    private Logger logger = Logger.getLogger("portal");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserdataFacade() {
        super(Userdata.class);
    }

    public void saveInfoUserData(String username, String ipAddr, String userAgent, String logintype) {
        Userdata p = null;
        try {
            p = new Userdata();
            p.setEmail(username);
            p.setIpaddress(ipAddr);
            p.setUseragent(userAgent);
            p.setCreationdate(Calendar.getInstance().getTime());
            p.setLogintype(logintype);

            try {
                if (userAgent != null) {
                    Parser uaParser = new Parser();
                    Client c = uaParser.parse(userAgent);

                    p.setDevice(c.device.family);
                    p.setOs(c.os.family);
                    if (c.os.major != null && c.os.minor != null) {
                        p.setOsVersion(c.os.major + "." + c.os.minor);
                    }
                    p.setBrowser(c.userAgent.family);
                    if (c.userAgent.major != null && c.userAgent.minor != null) {
                        p.setBrowserVersion(c.userAgent.major + "." + c.userAgent.minor);
                    }
                    logger.debug("saveInfoUserData: p: " + p + ".");
                }
            } catch (Exception ex) {
                loggerError.error("saveInfoUserData2. userdata: " + p, ex);
            }

            em.persist(p);
            em.flush();
        } catch (Exception e) {
            loggerError.error("saveInfoUserData. userdata: " + p, e);
            context.setRollbackOnly();
            e.printStackTrace();
        }
    }
}
