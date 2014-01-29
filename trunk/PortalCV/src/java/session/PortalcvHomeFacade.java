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
public class PortalcvHomeFacade extends AbstractFacade<PortalcvHome> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    @EJB
    private PortalcvHomeresourceFacade portalcvHomeresourceFacade;
    @EJB
    private PortalcvApgroupFacade portalcvApgroupFacade;
    private Logger logger = Logger.getLogger("portal");
    private Logger loggerError = Logger.getLogger("portalError");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvHomeFacade() {
        super(PortalcvHome.class);
    }

    public PortalcvHomeresource getPortalLayout(PortalcvAp idAP, String url, String type) {
        return portalcvHomeresourceFacade.getPortalLayout(idAP.getIdapgroups().getIdhomes().getIdhomes(), url, type);
    }

    public PortalcvHomeresource getPortalLayout(Integer homeId, String url, String type) {
        if (homeId != null && homeId > 0) {
            //Valid
//            PortalcvHome home = getHomeByHomeId(homeId);
            return portalcvHomeresourceFacade.getPortalLayout(homeId, url, type);
        } else {
            //Invalid
            return portalcvHomeresourceFacade.getPortalLayoutDefault(url, type);
        }
    }

    public PortalcvHome getHomeByHomeId(Integer homeId) {
        try {
            return (PortalcvHome) em.createNamedQuery("PortalcvHome.findByIdhomes").setParameter("idhomes", homeId).getSingleResult();
        } catch (Exception ex) {
            loggerError.error("ERROR en PortalcvHomeFacade getHomeByHomeId. homeId: " + homeId, ex);
        }
        return null;
    }

    public PortalcvHome getDefaultHome() {
        return getHomeByHomeId(1);
    }
}
