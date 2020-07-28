package com.totemti.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import com.totemti.model.Funcionario;


public class FuncionarioDao implements IFuncionarioDao {
	
	EntityManagerFactory factory;
	EntityManager entityManager;
	
	

	@Override
	public boolean saveFuncionario(Funcionario funcionario) {
		
		boolean flag = false;
		try {
			factory = Persistence.createEntityManagerFactory("totem");
	        entityManager = factory.createEntityManager();
	         
	        entityManager.getTransaction().begin();
	         
	         
	        entityManager.persist(funcionario);
	         
	        entityManager.getTransaction().commit();
	         
	        entityManager.close();
	        factory.close();
	        flag = true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
        
        return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> getFuncionarios() {
		
		List<Funcionario> funcionarios = null;
		try {
			factory = Persistence.createEntityManagerFactory("totem");
	        entityManager = factory.createEntityManager();
	         
	        entityManager.getTransaction().begin();
	         
	         
	        Query query = entityManager.createQuery("select funcionario from Funcionario funcionario");
			funcionarios = query.getResultList();
	         
	        entityManager.getTransaction().commit();
	         
	        entityManager.close();
	        factory.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
        
        return funcionarios;
		
	}

	@Override
	public Funcionario getFuncionarioById(int id) {
		
		Funcionario funcionario = null;
		try {
		factory = Persistence.createEntityManagerFactory("totem");
        entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
         
         
        funcionario = entityManager.find(Funcionario.class, id);
         
        entityManager.getTransaction().commit();
         
        entityManager.close();
        factory.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        
        return funcionario;
		
	}

	@Override
	public boolean updateFuncionario(Funcionario funcionario) {
		
		boolean flag = false;
		try {
		factory = Persistence.createEntityManagerFactory("totem");
        entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
         
         
        entityManager.merge(funcionario);
         
        entityManager.getTransaction().commit();
         
        entityManager.close();
        factory.close();
        flag = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        
        return flag;
	}

	@Override
	public boolean deleteFuncionario(int id) {
		boolean flag = false;
		
		try {
		factory = Persistence.createEntityManagerFactory("totem");
        entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
		
		Funcionario funcionario = entityManager.getReference(Funcionario.class, id);
		entityManager.remove(funcionario);
         
        entityManager.getTransaction().commit();
         
        entityManager.close();
        factory.close();
        flag = true;
        
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return flag;
	}

}
