/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvStat;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bruno
 */
@Stateless
public class PortalcvStatFacade extends AbstractFacade<PortalcvStat> {
    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvStatFacade() {
        super(PortalcvStat.class);
    }
    
}
