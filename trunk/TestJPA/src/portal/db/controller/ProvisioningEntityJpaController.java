/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portal.db.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import portal.db.controller.exceptions.NonexistentEntityException;
import portal.db.entity.ProvisioningEntity;

/**
 *
 * @author brunoli
 */
public class ProvisioningEntityJpaController {

    public ProvisioningEntityJpaController() {
        emf = Persistence.createEntityManagerFactory("TestJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProvisioningEntity provisioningEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(provisioningEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProvisioningEntity provisioningEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            provisioningEntity = em.merge(provisioningEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = provisioningEntity.getId();
                if (findProvisioningEntity(id) == null) {
                    throw new NonexistentEntityException("The provisioningEntity with id " + id + " no longer exists.");
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
            ProvisioningEntity provisioningEntity;
            try {
                provisioningEntity = em.getReference(ProvisioningEntity.class, id);
                provisioningEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provisioningEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(provisioningEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProvisioningEntity> findProvisioningEntityEntities() {
        return findProvisioningEntityEntities(true, -1, -1);
    }

    public List<ProvisioningEntity> findProvisioningEntityEntities(int maxResults, int firstResult) {
        return findProvisioningEntityEntities(false, maxResults, firstResult);
    }

    private List<ProvisioningEntity> findProvisioningEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ProvisioningEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProvisioningEntity findProvisioningEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProvisioningEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getProvisioningEntityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ProvisioningEntity as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
