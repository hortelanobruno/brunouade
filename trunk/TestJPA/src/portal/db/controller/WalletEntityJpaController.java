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
import portal.db.entity.WalletEntity;

/**
 *
 * @author brunoli
 */
public class WalletEntityJpaController {

    public WalletEntityJpaController() {
        emf = Persistence.createEntityManagerFactory("TestJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(WalletEntity walletEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(walletEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(WalletEntity walletEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            walletEntity = em.merge(walletEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = walletEntity.getId();
                if (findWalletEntity(id) == null) {
                    throw new NonexistentEntityException("The walletEntity with id " + id + " no longer exists.");
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
            WalletEntity walletEntity;
            try {
                walletEntity = em.getReference(WalletEntity.class, id);
                walletEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The walletEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(walletEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<WalletEntity> findWalletEntityEntities() {
        return findWalletEntityEntities(true, -1, -1);
    }

    public List<WalletEntity> findWalletEntityEntities(int maxResults, int firstResult) {
        return findWalletEntityEntities(false, maxResults, firstResult);
    }

    private List<WalletEntity> findWalletEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from WalletEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public WalletEntity findWalletEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(WalletEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getWalletEntityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from WalletEntity as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
