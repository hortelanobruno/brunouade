/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvAp;
import entity.PortalcvWlc;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bruno
 */
@Stateless
public class PortalcvWlcFacade extends AbstractFacade<PortalcvWlc> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvWlcFacade() {
        super(PortalcvWlc.class);
    }

    public List getWLCs() {
        try {
            List lista = (List) em.createNamedQuery("PortalcvWlc.findAll").getResultList();
            if(lista!=null&&!lista.isEmpty()){
                return lista;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
