package com.jpa_avanzado.spring_boot;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class MyService {

    @PersistenceContext
    private EntityManager em;

    public int plus(int a, int b){
        return a + b;
    }

    public Persona create(String name){
        Persona newPerson = new Persona();
        newPerson.setName(name);

        em.persist(newPerson);
        return newPerson;
    }
}
