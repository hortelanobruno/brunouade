/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvApgroup;
import entity.Settings;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Brunoli
 */
@Stateless
public class PortalcvApgroupFacade extends AbstractFacade<PortalcvApgroup> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("portal");
    private Logger loggerError = Logger.getLogger("portalError");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvApgroupFacade() {
        super(PortalcvApgroup.class);
    }

    public PortalcvApgroup getDefaultApGroup() {
        Integer defaultPortalGroup = 1;
        try {
            Settings set = (Settings) em.createNamedQuery("Settings.findByName").setParameter("name", "defaulthomepage").getSingleResult();
            defaultPortalGroup = Integer.valueOf(set.getValue());
        } catch (Exception ex) {
        }
        try {
            return (PortalcvApgroup) em.createNamedQuery("PortalcvApgroup.findByIdapgroups").setParameter("idapgroups", defaultPortalGroup).getSingleResult();
        } catch (Exception ex) {
            loggerError.error("ERROR en PortalcvApgroupFacade getDefaultApGroup.", ex);
        }
        return null;
    }
}
