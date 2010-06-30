package com.brunoli.worldwar.db.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.brunoli.worldwar.db.controller.exceptions.NonexistentEntityException;
import com.brunoli.worldwar.db.entity.BuildingEntity;
import com.brunoli.worldwar.db.entity.UnitEntity;

public class BuildingEntityController {

	public BuildingEntityController() {
        emf = Persistence.createEntityManagerFactory("WorldWarRobot");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BuildingEntity buildingEntity) {
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

    public void edit(BuildingEntity buildingEntity) throws NonexistentEntityException, Exception {
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
            BuildingEntity buildingEntity;
            try {
                buildingEntity = em.getReference(BuildingEntity.class, id);
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
		List<BuildingEntity> buildings = findBuildingEntityEntities();
		for (BuildingEntity buildingEntity : buildings) {
			destroy(buildingEntity.getId());
		}
	}

    public List<BuildingEntity> findBuildingEntityEntities() {
        return findBuildingEntityEntities(true, -1, -1);
    }

    public List<BuildingEntity> findBuildingEntityEntities(int maxResults, int firstResult) {
        return findBuildingEntityEntities(false, maxResults, firstResult);
    }

    private List<BuildingEntity> findBuildingEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BuildingEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
			return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BuildingEntity findBuildingEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BuildingEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanEntityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BuildingEntity as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

	public BuildingEntity findBuildingEntityByUrlImg(String url) {
		EntityManager em = getEntityManager();
		try {
			Query q = em
					.createQuery(
							"select object(o) from BuildingEntity as o where o.urlImg = :url")
					.setParameter("url", url);
			return (BuildingEntity) q.getSingleResult();
		}catch(Exception ex){
			return null;
		} finally {
			em.close();
		}
	}
}
