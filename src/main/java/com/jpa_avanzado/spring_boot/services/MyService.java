package com.jpa_avanzado.spring_boot.services;

import com.jpa_avanzado.spring_boot.entities.Person;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@org.springframework.stereotype.Service
@Transactional
public class MyService {

    @PersistenceContext
    private EntityManager em;

    public Person getPerson(long id){
        return em.find(Person.class, id);
    }
}
