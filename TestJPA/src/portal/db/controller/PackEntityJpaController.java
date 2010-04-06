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
import portal.db.entity.PackEntity;

/**
 *
 * @author brunoli
 */
public class PackEntityJpaController {

    public PackEntityJpaController() {
        emf = Persistence.createEntityManagerFactory("TestJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PackEntity packEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(packEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PackEntity packEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            packEntity = em.merge(packEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = packEntity.getId();
                if (findPackEntity(id) == null) {
                    throw new NonexistentEntityException("The packEntity with id " + id + " no longer exists.");
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
            PackEntity packEntity;
            try {
                packEntity = em.getReference(PackEntity.class, id);
                packEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The packEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(packEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PackEntity> findPackEntityEntities() {
        return findPackEntityEntities(true, -1, -1);
    }

    public List<PackEntity> findPackEntityEntities(int maxResults, int firstResult) {
        return findPackEntityEntities(false, maxResults, firstResult);
    }

    private List<PackEntity> findPackEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PackEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PackEntity findPackEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PackEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getPackEntityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from PackEntity as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
