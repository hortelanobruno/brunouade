/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvAutologin;
import java.sql.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Brunoli
 */
@Stateless
public class PortalcvAutologinFacade extends AbstractFacade<PortalcvAutologin> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("portal");
    private Logger loggerError = Logger.getLogger("portalError");
    private Integer defaultAutoLoginValidTimeInMinutes = 60 * 24 * 30;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvAutologinFacade() {
        super(PortalcvAutologin.class);
    }

    public PortalcvAutologin getAutoLogin(String cpeMac) {
        PortalcvAutologin home = null;
        try {
            home = (PortalcvAutologin) em.createNamedQuery("PortalcvAutologin.findByCpeMac").setParameter("cpeMac", cpeMac).getSingleResult();
        } catch (NoResultException ex) {
        } catch (Exception ex) {
            loggerError.error("Error en getAutoLogin. cpeMac: " + cpeMac, ex);
        }
        return home;
    }

    public void updateAutoLoginInfo(PortalcvAutologin autoLogin) {
        try {
            autoLogin.setTimestamp(new Date(System.currentTimeMillis()));
            em.persist(autoLogin);
            em.flush();
        } catch (NoResultException ex) {
        } catch (Exception ex) {
            loggerError.error("Error en updateAutoLoginInfo. autoLogin:" + autoLogin, ex);
        }
    }

    public void createAutoLoginInfo(String username, String cpeMac, String logintype) {
        try {
            PortalcvAutologin autoLogin = getAutoLogin(cpeMac);
            if (autoLogin == null) {
                autoLogin = new PortalcvAutologin();
                autoLogin.setUsername(username);
                autoLogin.setCpeMac(cpeMac);
                autoLogin.setLogintype(logintype);
                autoLogin.setTimestamp(new Date(System.currentTimeMillis()));
                em.persist(autoLogin);
                em.flush();
            } else {
                autoLogin.setUsername(username);
                autoLogin.setLogintype(logintype);
                autoLogin.setTimestamp(new Date(System.currentTimeMillis()));
                em.merge(autoLogin);
                em.flush();
            }
        } catch (Exception ex) {
            loggerError.error("Error en createAutoLoginInfo. username:" + username + ". cpeMac: " + cpeMac + ". logintype: " + logintype, ex);
        }
    }

    public boolean validAutoLoginData(PortalcvAutologin autoLogin, String configuredValidTime) {
        if (autoLogin != null) {
            logger.debug("validAutoLoginData. cpemac: " + autoLogin.getCpeMac() + "...");
            long aux = System.currentTimeMillis() - autoLogin.getTimestamp().getTime();
            long validTime;
            if (configuredValidTime == null) {
                validTime = defaultAutoLoginValidTimeInMinutes;
            } else {
                validTime = Integer.valueOf(configuredValidTime);
            }
//            2013-10-04 16:34:34,585 DEBUG validAutoLoginData cpemac: 38aa3c5732ca. validTime: 43200. aux: 4306710585. otraParte: 2592000000
//            2013-10-04 16:34:34,585 DEBUG validAutoLoginData fuera de fecha. cpemac: 38aa3c5732ca.

            logger.debug("validAutoLoginData cpemac: " + autoLogin.getCpeMac() + ". validTime: " + validTime + ". aux: " + aux + ". otraParte: " + (1000L * 60 * validTime));
            if (aux < (1000L * 60 * validTime)) {
                logger.debug("validAutoLoginData valido. cpemac: " + autoLogin.getCpeMac() + ".");
                return true;
            } else {
                logger.debug("validAutoLoginData fuera de fecha. cpemac: " + autoLogin.getCpeMac() + ".");
            }
        } else {
            logger.debug("validAutoLoginData null.");
        }
        return false;
    }

    public void updateAutoLoginInfo(String username, String logintype) {
        PortalcvAutologin home;
        try {
            home = (PortalcvAutologin) em.createNamedQuery("PortalcvAutologin.findByUsername").setParameter("username", username).getSingleResult();
            home.setLogintype(logintype);
            em.merge(home);
            em.flush();
        } catch (Exception ex) {
//            loggerError.error("Error en updateAutoLoginInfo. username: " + username + ". logintype: " + logintype, ex);
        }
    }

    public void removeAutoLogin(String cpeMac) {
        try {
            PortalcvAutologin auto = getAutoLogin(cpeMac);
            if (auto != null) {
                em.remove(auto);
                em.flush();
            }
        } catch (Exception ex) {
            loggerError.error("Error en removeAutoLogin. cpeMac: " + cpeMac, ex);
        }
    }
}