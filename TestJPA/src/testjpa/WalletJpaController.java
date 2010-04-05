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
public class WalletJpaController {

    public WalletJpaController() {
        emf = Persistence.createEntityManagerFactory("TestJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Wallet wallet) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(wallet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Wallet wallet) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            wallet = em.merge(wallet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = wallet.getId();
                if (findWallet(id) == null) {
                    throw new NonexistentEntityException("The wallet with id " + id + " no longer exists.");
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
            Wallet wallet;
            try {
                wallet = em.getReference(Wallet.class, id);
                wallet.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The wallet with id " + id + " no longer exists.", enfe);
            }
            em.remove(wallet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Wallet> findWalletEntities() {
        return findWalletEntities(true, -1, -1);
    }

    public List<Wallet> findWalletEntities(int maxResults, int firstResult) {
        return findWalletEntities(false, maxResults, firstResult);
    }

    private List<Wallet> findWalletEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Wallet as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Wallet findWallet(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Wallet.class, id);
        } finally {
            em.close();
        }
    }

    public int getWalletCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Wallet as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
