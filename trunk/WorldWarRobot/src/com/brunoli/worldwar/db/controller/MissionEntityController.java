package com.brunoli.worldwar.db.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.brunoli.worldwar.db.controller.exceptions.NonexistentEntityException;
import com.brunoli.worldwar.db.entity.MissionEntity;

public class MissionEntityController {

	public MissionEntityController() {
		emf = Persistence.createEntityManagerFactory("WorldWarRobot");
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(MissionEntity missionEntity) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(missionEntity);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(MissionEntity missionEntity) throws NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			missionEntity = em.merge(missionEntity);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = missionEntity.getId();
				if (findMissionEntity(id) == null) {
					throw new NonexistentEntityException(
							"The missionEntity with id " + id
									+ " no longer exists.");
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
			MissionEntity missionEntity;
			try {
				missionEntity = em.getReference(MissionEntity.class, id);
				missionEntity.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The missionEntity with id "
						+ id + " no longer exists.", enfe);
			}
			em.remove(missionEntity);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroyAll() throws NonexistentEntityException {
		List<MissionEntity> missions = findMissionEntityEntities();
		for (MissionEntity missionEntity : missions) {
			destroy(missionEntity.getId());
		}
	}

	public List<MissionEntity> findMissionEntityEntities() {
		return findMissionEntityEntities(true, -1, -1);
	}

	public List<MissionEntity> findMissionEntityEntities(int maxResults,
			int firstResult) {
		return findMissionEntityEntities(false, maxResults, firstResult);
	}

	private List<MissionEntity> findMissionEntityEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			Query q = em.createQuery("select object(o) from MissionEntity as o");
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public MissionEntity findMissionEntity(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(MissionEntity.class, id);
		} finally {
			em.close();
		}
	}

	public int getMissionEntityCount() {
		EntityManager em = getEntityManager();
		try {
			Query q = em.createQuery("select count(o) from MissionEntity as o");
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

	public MissionEntity findEntityByName(String missionName) {
		EntityManager em = getEntityManager();
		try {
			Query q = em.createQuery("select o from MissionEntity as o where o.missionName = :name").setParameter("name", missionName);
			return ((MissionEntity) q.getSingleResult());
		} finally {
			em.close();
		}
	}
}
