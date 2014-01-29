/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvIsgaccounting;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bruno
 */
@Stateless
public class PortalcvIsgaccountingFacade extends AbstractFacade<PortalcvIsgaccounting> {
    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvIsgaccountingFacade() {
        super(PortalcvIsgaccounting.class);
    }
    
}
