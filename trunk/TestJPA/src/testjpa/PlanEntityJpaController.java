/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testjpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import testjpa.exceptions.NonexistentEntityException;

/**
 *
 * @author brunoli
 */
public class PlanEntityJpaController {

    public PlanEntityJpaController() {
        emf = Persistence.createEntityManagerFactory("TestJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlanEntity planEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(planEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlanEntity planEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            planEntity = em.merge(planEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = planEntity.getId();
                if (findPlanEntity(id) == null) {
                    throw new NonexistentEntityException("The planEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanEntity planEntity;
            try {
                planEntity = em.getReference(PlanEntity.class, id);
                planEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(planEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlanEntity> findPlanEntityEntities() {
        return findPlanEntityEntities(true, -1, -1);
    }

    public List<PlanEntity> findPlanEntityEntities(int maxResults, int firstResult) {
        return findPlanEntityEntities(false, maxResults, firstResult);
    }

    private List<PlanEntity> findPlanEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PlanEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PlanEntity findPlanEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlanEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanEntityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from PlanEntity as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
