package com.c57lee.possystemdemo.persistence;

import com.c57lee.possystemdemo.obj.Client;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ClientDAO implements IDataAcessObject<Client, Long> {

    EntityManagerFactory emf;
    EntityManager em;

    public ClientDAO(){
        emf = Persistence.createEntityManagerFactory("c57leePersistence");
        em = emf.createEntityManager();
    }

    @Override
    public void save(Client client) {
        executeInsideTransaction(entityManager->entityManager.persist(client));

    }

    @Override
    public Optional<Client> findByID(Long id) {
        return Optional.ofNullable(em.find(Client.class,id));
    }

    @Override
    public List<Client> findAll() {
        TypedQuery<Client> tq = em.createQuery("SELECT c FROM Client c", Client.class);
        return tq.getResultList();
    }

    @Override
    public void update(Client client) {
        em.merge(client);
    }

    public void executeInsideTransaction(Consumer<EntityManager> action){
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            action.accept(em);
            et.commit();
        } catch(RuntimeException e){
            et.rollback();
            throw e;
        }
    }
}
