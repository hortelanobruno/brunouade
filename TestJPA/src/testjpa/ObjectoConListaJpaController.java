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
public class ObjectoConListaJpaController {

    public ObjectoConListaJpaController() {
        emf = Persistence.createEntityManagerFactory("TestJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ObjectoConLista objectoConLista) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(objectoConLista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ObjectoConLista objectoConLista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            objectoConLista = em.merge(objectoConLista);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = objectoConLista.getId();
                if (findObjectoConLista(id) == null) {
                    throw new NonexistentEntityException("The objectoConLista with id " + id + " no longer exists.");
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
            ObjectoConLista objectoConLista;
            try {
                objectoConLista = em.getReference(ObjectoConLista.class, id);
                objectoConLista.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objectoConLista with id " + id + " no longer exists.", enfe);
            }
            em.remove(objectoConLista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ObjectoConLista> findObjectoConListaEntities() {
        return findObjectoConListaEntities(true, -1, -1);
    }

    public List<ObjectoConLista> findObjectoConListaEntities(int maxResults, int firstResult) {
        return findObjectoConListaEntities(false, maxResults, firstResult);
    }

    private List<ObjectoConLista> findObjectoConListaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ObjectoConLista as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ObjectoConLista findObjectoConLista(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ObjectoConLista.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjectoConListaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ObjectoConLista as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
