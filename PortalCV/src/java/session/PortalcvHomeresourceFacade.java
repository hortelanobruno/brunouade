/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvAp;
import entity.PortalcvHome;
import entity.PortalcvHomeresource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Brunoli
 */
@Stateless
public class PortalcvHomeresourceFacade extends AbstractFacade<PortalcvHomeresource> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    @EJB
    private SettingsFacade settingsFacade;
    private Logger logger = Logger.getLogger("portal");
    private Logger loggerError = Logger.getLogger("portalError");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvHomeresourceFacade() {
        super(PortalcvHomeresource.class);
    }

    public PortalcvHomeresource getPortalLayout(Integer homeId, String url, String type) {
        PortalcvHomeresource resource = null;
        if (homeId != null) {
            try {
                resource = (PortalcvHomeresource) em.createNamedQuery("PortalcvHomeresource.findByUrlAndTypeAndIdhomes").setParameter("url", url).setParameter("type", type).setParameter("idhomes", homeId).getSingleResult();
            } catch (Exception ex) {
            }
            if (resource != null) {
                return resource;
            } else {
                loggerError.error("No encontro portal layout: homeId: " + homeId + ". url: " + url + ". type: " + type);
                //Me fijo si existe en el Portal Default
                resource = getPortalLayoutDefault(url, type);
                if (resource != null) {
                    return resource;
                } else {
                    loggerError.error("No encontro portal layout2: homeId: " + homeId + ". url: " + url + ". type: " + type);
                    //Si no existe devuelvo el other
                    return getPortalLayoutDefault(url, "other");
                }
            }
        }
        try {
            resource = (PortalcvHomeresource) em.createNamedQuery("PortalcvHomeresource.findByUrlAndTypeAndIdhomes").setParameter("url", url).setParameter("type", type).setParameter("idhomes", 1).getSingleResult();
        } catch (Exception ex) {
        }
        return resource;
    }

    public PortalcvHomeresource getPortalLayoutDefault(String url, String type) {
        Integer homeId = 1;
        try {
            homeId = Integer.valueOf(settingsFacade.getSettingsConfiguration().getProperty("defaulthomepage"));
        } catch (Exception ex) {
            loggerError.error("Invalid default HOMEId. ", ex);
        }
        PortalcvHomeresource resource = null;
        try {
            resource = (PortalcvHomeresource) em.createNamedQuery("PortalcvHomeresource.findByUrlAndTypeAndIdhomes").setParameter("url", url).setParameter("type", type).setParameter("idhomes", homeId).getSingleResult();
        } catch (Exception ex) {
        }
        return resource;
    }
}
