/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvFiberteluser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bruno
 */
@Stateless
public class PortalcvFiberteluserFacade extends AbstractFacade<PortalcvFiberteluser> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvFiberteluserFacade() {
        super(PortalcvFiberteluser.class);
    }

    public boolean exists(String email) {
        try {
            if (email.contains("@fibertel")) {
                return true;
            } else {
                em.createNamedQuery("PortalcvFiberteluser.findByEmail").setParameter("email", email).getSingleResult();
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }
}
