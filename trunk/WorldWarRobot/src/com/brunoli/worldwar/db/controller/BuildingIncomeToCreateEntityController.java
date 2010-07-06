package com.brunoli.worldwar.db.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.brunoli.worldwar.db.controller.exceptions.NonexistentEntityException;
import com.brunoli.worldwar.db.entity.BuildingIncomeToCreateEntity;

public class BuildingIncomeToCreateEntityController {

	public BuildingIncomeToCreateEntityController() {
        emf = Persistence.createEntityManagerFactory("WorldWarRobot");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BuildingIncomeToCreateEntity buildingEntity) {
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

    public void edit(BuildingIncomeToCreateEntity buildingEntity) throws NonexistentEntityException, Exception {
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
            BuildingIncomeToCreateEntity buildingEntity;
            try {
                buildingEntity = em.getReference(BuildingIncomeToCreateEntity.class, id);
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
		List<BuildingIncomeToCreateEntity> buildings = findBuildingEntityEntities();
		for (BuildingIncomeToCreateEntity buildingEntity : buildings) {
			destroy(buildingEntity.getId());
		}
	}

    public List<BuildingIncomeToCreateEntity> findBuildingEntityEntities() {
        return findBuildingEntityEntities(true, -1, -1);
    }

    public List<BuildingIncomeToCreateEntity> findBuildingEntityEntities(int maxResults, int firstResult) {
        return findBuildingEntityEntities(false, maxResults, firstResult);
    }

    private List<BuildingIncomeToCreateEntity> findBuildingEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BuildingIncomeToCreateEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
			return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BuildingIncomeToCreateEntity findBuildingEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BuildingIncomeToCreateEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getBuildingEntityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BuildingIncomeToCreateEntity as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
