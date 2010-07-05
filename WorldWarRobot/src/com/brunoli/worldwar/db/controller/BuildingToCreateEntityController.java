package com.brunoli.worldwar.db.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.brunoli.worldwar.db.controller.exceptions.NonexistentEntityException;
import com.brunoli.worldwar.db.entity.BuildingToCreateEntity;

public class BuildingToCreateEntityController {

	public BuildingToCreateEntityController() {
        emf = Persistence.createEntityManagerFactory("WorldWarRobot");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BuildingToCreateEntity buildingEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(buildingEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BuildingToCreateEntity buildingEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            buildingEntity = em.merge(buildingEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = buildingEntity.getId();
                if (findBuildingEntity(id) == null) {
                    throw new NonexistentEntityException("The buildingEntity with id " + id + " no longer exists.");
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
            BuildingToCreateEntity buildingEntity;
            try {
                buildingEntity = em.getReference(BuildingToCreateEntity.class, id);
                buildingEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The buildingEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(buildingEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    

	public void destroyAll() throws NonexistentEntityException {
		List<BuildingToCreateEntity> buildings = findBuildingEntityEntities();
		for (BuildingToCreateEntity buildingEntity : buildings) {
			destroy(buildingEntity.getId());
		}
	}

    public List<BuildingToCreateEntity> findBuildingEntityEntities() {
        return findBuildingEntityEntities(true, -1, -1);
    }

    public List<BuildingToCreateEntity> findBuildingEntityEntities(int maxResults, int firstResult) {
        return findBuildingEntityEntities(false, maxResults, firstResult);
    }

    private List<BuildingToCreateEntity> findBuildingEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BuildingToCreateEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
			return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BuildingToCreateEntity findBuildingEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BuildingToCreateEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanEntityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BuildingToCreateEntity as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
