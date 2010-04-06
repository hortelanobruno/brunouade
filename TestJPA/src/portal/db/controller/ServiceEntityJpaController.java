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
import portal.db.entity.ServiceEntity;

/**
 *
 * @author brunoli
 */
public class ServiceEntityJpaController {

    public ServiceEntityJpaController() {
        emf = Persistence.createEntityManagerFactory("TestJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceEntity serviceEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(serviceEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceEntity serviceEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            serviceEntity = em.merge(serviceEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = serviceEntity.getId();
                if (findServiceEntity(id) == null) {
                    throw new NonexistentEntityException("The serviceEntity with id " + id + " no longer exists.");
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
            ServiceEntity serviceEntity;
            try {
                serviceEntity = em.getReference(ServiceEntity.class, id);
                serviceEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(serviceEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceEntity> findServiceEntityEntities() {
        return findServiceEntityEntities(true, -1, -1);
    }

    public List<ServiceEntity> findServiceEntityEntities(int maxResults, int firstResult) {
        return findServiceEntityEntities(false, maxResults, firstResult);
    }

    private List<ServiceEntity> findServiceEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ServiceEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ServiceEntity findServiceEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceEntityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ServiceEntity as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
