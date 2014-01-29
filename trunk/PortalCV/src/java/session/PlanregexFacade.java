/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Planes;
import entity.Planregex;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bruno
 */
@Stateless
public class PlanregexFacade extends AbstractFacade<Planregex> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    @Resource
    private SessionContext context;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanregexFacade() {
        super(Planregex.class);
    }

    public void createPlanRegex(String planRegex, String plaName) {
        try {
            Planregex pla = (Planregex) em.createNamedQuery("Planregex.findByRegex").
                    setParameter("regex", planRegex).getSingleResult();
        } catch (Exception ex) {
            Planregex pla = new Planregex();
            pla.setRegex(planRegex);
            pla.setPlanname(plaName);
            em.merge(pla);
            em.flush();
        }
    }

    public void editCreatePlanRegex(String oldPlanRegex, String planRegex, String plaName) {
        try {
            Planregex pla = (Planregex) em.createNamedQuery("Planregex.findByRegex").
                    setParameter("regex", oldPlanRegex).getSingleResult();
            pla.setRegex(planRegex);
            pla.setPlanname(plaName);
            em.merge(pla);
            em.flush();
        } catch (Exception ex) {
            context.setRollbackOnly();
            ex.printStackTrace();
        }
    }

    public void removePlanRegex(String planRegex) {
        try {
            Planregex pla = (Planregex) em.createNamedQuery("Planregex.findByRegex").
                    setParameter("regex", planRegex).getSingleResult();
            em.remove(pla);
            em.flush();
        } catch (Exception ex) {
        }
    }

    public void removeByPlanName(String planName) {
        try {
            List list = (List) em.createNamedQuery("Planregex.findByPlanname").
                    setParameter("planname", planName).getResultList();
            for (Object obj : list) {
                em.remove(obj);
            }
            em.flush();
        } catch (Exception ex) {
            context.setRollbackOnly();
            ex.printStackTrace();
        }
    }

    public void changePlanName(String oldName, String planName) {
        try {
            List list = (List) em.createNamedQuery("Planregex.findByPlanname").
                    setParameter("planname", oldName).getResultList();
            Planregex pla;
            for (Object obj : list) {
                pla = (Planregex) obj;
                pla.setPlanname(planName);
                em.merge(obj);
            }
            em.flush();
        } catch (Exception ex) {
            context.setRollbackOnly();
            ex.printStackTrace();
        }
    }

    public String getPlanByRegex(String resultplan) {
        try {
            List a = (List) em.createNamedQuery("Planregex.findPlanNameByRegex").setParameter("regex",resultplan).getResultList();
            if (a != null) {
                return (String) a.get(0);
            }
        } catch (Exception ex) {
        }
        return null;
    }
}
