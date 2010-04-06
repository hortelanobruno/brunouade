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
import portal.db.entity.UserPersonalInformationEntity;

/**
 *
 * @author brunoli
 */
public class UserPersonalInformationEntityJpaController {

    public UserPersonalInformationEntityJpaController() {
        emf = Persistence.createEntityManagerFactory("TestJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserPersonalInformationEntity userPersonalInformationEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(userPersonalInformationEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserPersonalInformationEntity userPersonalInformationEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userPersonalInformationEntity = em.merge(userPersonalInformationEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = userPersonalInformationEntity.getId();
                if (findUserPersonalInformationEntity(id) == null) {
                    throw new NonexistentEntityException("The userPersonalInformationEntity with id " + id + " no longer exists.");
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
            UserPersonalInformationEntity userPersonalInformationEntity;
            try {
                userPersonalInformationEntity = em.getReference(UserPersonalInformationEntity.class, id);
                userPersonalInformationEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userPersonalInformationEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(userPersonalInformationEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserPersonalInformationEntity> findUserPersonalInformationEntityEntities() {
        return findUserPersonalInformationEntityEntities(true, -1, -1);
    }

    public List<UserPersonalInformationEntity> findUserPersonalInformationEntityEntities(int maxResults, int firstResult) {
        return findUserPersonalInformationEntityEntities(false, maxResults, firstResult);
    }

    private List<UserPersonalInformationEntity> findUserPersonalInformationEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from UserPersonalInformationEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UserPersonalInformationEntity findUserPersonalInformationEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserPersonalInformationEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserPersonalInformationEntityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from UserPersonalInformationEntity as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
