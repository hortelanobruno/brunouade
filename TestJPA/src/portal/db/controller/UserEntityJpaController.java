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
import portal.db.entity.UserEntity;

/**
 *
 * @author brunoli
 */
public class UserEntityJpaController {

    public UserEntityJpaController() {
        emf = Persistence.createEntityManagerFactory("TestJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserEntity userEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(userEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserEntity userEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userEntity = em.merge(userEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = userEntity.getId();
                if (findUserEntity(id) == null) {
                    throw new NonexistentEntityException("The userEntity with id " + id + " no longer exists.");
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
            UserEntity userEntity;
            try {
                userEntity = em.getReference(UserEntity.class, id);
                userEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(userEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserEntity> findUserEntityEntities() {
        return findUserEntityEntities(true, -1, -1);
    }

    public List<UserEntity> findUserEntityEntities(int maxResults, int firstResult) {
        return findUserEntityEntities(false, maxResults, firstResult);
    }

    private List<UserEntity> findUserEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from UserEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UserEntity findUserEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserEntityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from UserEntity as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
