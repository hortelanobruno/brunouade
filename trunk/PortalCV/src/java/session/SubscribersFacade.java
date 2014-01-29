/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Subscribers;
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
public class SubscribersFacade extends AbstractFacade<Subscribers> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    @Resource
    private SessionContext context;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubscribersFacade() {
        super(Subscribers.class);
    }

    public void crearSubscriber(String subscriberName, String subscriberPlan, String subscriberPriority) {
        try {
            Subscribers sub = new Subscribers();
            sub.setUserName(subscriberName);
            sub.setGroupName(subscriberPlan);
            sub.setPriority(Integer.valueOf(subscriberPriority));
            em.persist(sub);
            em.flush();
        } catch (Exception ex) {
            context.setRollbackOnly();
            ex.printStackTrace();
        }
    }

    public void editSubscriber(String oldSubscriberName, String subscriberName, String subscriberPlan, String subscriberPriority) {
        try {
            Subscribers sub = (Subscribers) em.createNamedQuery("Subscribers.findByUserName").
                    setParameter("userName", oldSubscriberName).getSingleResult();
            sub.setUserName(subscriberName);
            sub.setGroupName(subscriberPlan);
            sub.setPriority(Integer.valueOf(subscriberPriority));
            em.merge(sub);
            em.flush();
        } catch (Exception ex) {
            context.setRollbackOnly();
            ex.printStackTrace();
        }
    }

    public void removeSubscriber(String subscriberName) {
        try {
            //remuevo de subs
            Subscribers sub = (Subscribers) em.createNamedQuery("Subscribers.findByUserName").setParameter("userName", subscriberName).getSingleResult();
            em.remove(sub);
            em.flush();
        } catch (Exception ex) {
            context.setRollbackOnly();
            ex.printStackTrace();
        }
    }

    public List<String> getSubsByPlan(String planName) {
        return em.createNamedQuery("Subscribers.findAllSubsByGroupName").setParameter("groupName", planName).getResultList();
    }

    public void updatePlanDefault(String planDefault) {
        try {
            //remuevo de subs
            Subscribers sub = (Subscribers) em.createNamedQuery("Subscribers.findByUserName").setParameter("userName", "DEFAULT").getSingleResult();
            sub.setGroupName(planDefault);
            em.merge(sub);
            em.flush();
        } catch (Exception ex) {
            Subscribers sub = new Subscribers();
            sub.setUserName("DEFAULT");
            sub.setGroupName(planDefault);
            sub.setPriority(1);
            em.persist(sub);
            em.flush();
        }
    }

    public Subscribers getSubscriber(String username) {
        try {
            Subscribers sub = (Subscribers) em.createNamedQuery("Subscribers.findByUserName").
                    setParameter("userName", username).getSingleResult();
            return sub;
        } catch (Exception ex) {
        }
        return null;
    }

    public boolean validSubscriber(String username) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public List<String> getAllSubscribersNames() {
        return em.createNamedQuery("Subscribers.findAllNames").getResultList();
    }

    public List<Subscribers> getSubsWithPagination(Integer displayStart, Integer displayLength, String search, Integer sortCol, String sortDir) {
//        Query q = em.createQuery("select distinct SUBSTRING(pla.value,2) from Planes pla where pla.groupName like :planName and pla.attribute like 'Cisco-Account-Info'");
//        q.setParameter("planName", planName);
//        List lista = q.getResultList();
        String orderby = "";
        if (sortCol.intValue() == 0) {
            orderby = " ORDER BY s.userName " + sortDir;
        } else if (sortCol.intValue() == 1) {
            orderby = " ORDER BY s.groupName " + sortDir;
        }
        if (search.isEmpty()) {
            return em.createQuery("SELECT s FROM Subscribers s" + orderby).setFirstResult(displayStart).setMaxResults(displayLength).getResultList();
        } else {
            return em.createQuery("SELECT s FROM Subscribers s WHERE s.userName like :userName" + orderby).setParameter("userName", "%" + search + "%").setFirstResult(displayStart).setMaxResults(displayLength).getResultList();
        }
    }

    public Integer getSubscribersCountWithPagination(String search) {
        if (search.isEmpty()) {
            return em.createQuery("SELECT s FROM Subscribers s").getResultList().size();
        } else {
            return em.createQuery("SELECT s FROM Subscribers s WHERE s.userName like :userName").setParameter("userName", "%" + search + "%").getResultList().size();
        }
    }

    public void changePlanName(String oldName, String planName) {
        try {
            //remuevo de subs
            List subs = em.createNamedQuery("Subscribers.findByGroupName").setParameter("groupName", oldName).getResultList();
            Subscribers sub;
            for (Object obj : subs) {
                sub = (Subscribers) obj;
                sub.setGroupName(planName);
                em.merge(sub);
            }
            em.flush();
        } catch (Exception ex) {
        }
    }
}
