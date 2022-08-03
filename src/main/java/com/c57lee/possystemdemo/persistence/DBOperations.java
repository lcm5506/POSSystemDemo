package com.c57lee.possystemdemo.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class DBOperations <T, ID> {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("c57leePersistence");
    EntityManager em = emf.createEntityManager();


    public <T> void persist(T obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }


    public <T, ID> T findByID(Class<T> t, ID id) {
        T found = em.find(t,id);
        em.detach(found);
        return found;
    }


    public <T, ID> T findByIDPersistent(Class<T> t, ID id) {
        T found = em.find(t,id);
        return found;
    }



    public <T, ID> T remove(Class<T> t,ID id) {
        T found = em.find(t, id);
        em.remove(found);
        return found;
    }

    public List getAll(String entityName){
        return em.createQuery("FROM "+entityName).getResultList();
    }


}
