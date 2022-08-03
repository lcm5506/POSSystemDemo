package com.c57lee.possystemdemo.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class DAO <T,ID> {

    EntityManagerFactory emf;
    EntityManager em;


    public DAO() {
        emf = Persistence.createEntityManagerFactory("c57leePersistence");
        em = emf.createEntityManager();
    }

    public void executeInsideTransaction(Consumer<EntityManager> action){
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            action.accept(em);
            et.commit();
        } catch (RuntimeException e){
            et.rollback();
            throw e;
        }
    }

    public void save(T t) {
        executeInsideTransaction(em->em.persist(t));
    }

    public Optional<T> findByID(Class<T> t,ID id) {

        return Optional.ofNullable(em.find(t,id));
    }

    public List<T> findAll(Class<T> tClass) {
        String q = String.format("SELECT c FROM %s c",tClass.getName());
        return em.createQuery(q,tClass).getResultList();
    }

    public void update(T t) {
        executeInsideTransaction(em->em.merge(t));
    }

    public void remove(T t){
        executeInsideTransaction(em->em.remove(em.contains(t) ? t : em.merge(t)));
    }

    public boolean contains(T t){
        return em.contains(t);
    }
}
