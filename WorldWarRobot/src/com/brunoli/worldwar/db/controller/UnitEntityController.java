package com.brunoli.worldwar.db.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.brunoli.worldwar.db.controller.exceptions.NonexistentEntityException;
import com.brunoli.worldwar.db.entity.UnitEntity;

public class UnitEntityController {

	public UnitEntityController() {
		emf = Persistence.createEntityManagerFactory("WorldWarRobot");
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(UnitEntity planEntity) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(planEntity);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(UnitEntity planEntity) throws NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			planEntity = em.merge(planEntity);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = planEntity.getId();
				if (findUnitEntity(id) == null) {
					throw new NonexistentEntityException(
							"The planEntity with id " + id
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
			UnitEntity planEntity;
			try {
				planEntity = em.getReference(UnitEntity.class, id);
				planEntity.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The planEntity with id "
						+ id + " no longer exists.", enfe);
			}
			em.remove(planEntity);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroyAll() throws NonexistentEntityException {
		List<UnitEntity> units = findUnitEntityEntities();
		for (UnitEntity unitEntity : units) {
			destroy(unitEntity.getId());
		}
	}

	public List<UnitEntity> findUnitEntityEntities() {
		return findUnitEntityEntities(true, -1, -1);
	}

	public List<UnitEntity> findUnitEntityEntities(int maxResults,
			int firstResult) {
		return findUnitEntityEntities(false, maxResults, firstResult);
	}

	private List<UnitEntity> findUnitEntityEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			Query q = em.createQuery("select object(o) from UnitEntity as o");
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public UnitEntity findUnitEntity(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(UnitEntity.class, id);
		} finally {
			em.close();
		}
	}

	public int getUnitEntityCount() {
		EntityManager em = getEntityManager();
		try {
			Query q = em.createQuery("select count(o) from UnitEntity as o");
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

	public UnitEntity findUnitEntityByUrlImg(String url) {
		EntityManager em = getEntityManager();
		try {
			Query q = em
					.createQuery(
							"select object(o) from UnitEntity as o where o.urlImg = :url")
					.setParameter("url", url);
			return (UnitEntity) q.getSingleResult();
		}catch(Exception ex){
			return null;
		} finally {
			em.close();
		}
	}
}
