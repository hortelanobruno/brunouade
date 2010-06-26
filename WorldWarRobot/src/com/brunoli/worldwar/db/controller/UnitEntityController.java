package com.brunoli.worldwar.db.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.brunoli.worldwar.db.entity.UnitEntity;

public class UnitEntityController {

	private EntityManagerFactory emf;

	public UnitEntityController() {

	}

	public EntityManagerFactory getEMF() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("WorldWarRobot");
		}
		return emf;
	}
	
	public void  createNewOrder(UnitEntity order){
	    EntityManager em = getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        em.persist(order);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	public UnitEntity getOrderById(long orderId){
	    EntityManager em = getEMF().createEntityManager();
	    try{
	        return em.find(UnitEntity.class, orderId);
	    }finally{
	        em.close();
	    }
	}
	
	public void alterOrderQuantity(UnitEntity updatedUnit){
	    EntityManager em = getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        UnitEntity unit = em.find(UnitEntity.class, updatedUnit.getId());
	        em.merge(unit);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	public void requestCancelOrder(Long id){
	    EntityManager em = getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        UnitEntity order = em.find(UnitEntity.class, id);
	        em.remove(order);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
}
