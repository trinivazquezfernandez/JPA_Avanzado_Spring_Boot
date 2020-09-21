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

    public Person create(String name, String city){
        Person newPerson = new Person();
        newPerson.setName(name);
        newPerson.setCity(city);

        em.persist(newPerson);
        return newPerson;
    }

    public Person getPersonById(long id){
        //getReference devuelve el objeto solo si está en el presistence constext, no busca en base de datos
        //return em.getReference(Person.class, id);

        //find busca el objeto en base de datos solo si previamente no lo encuentra en el persistence context
        return em.find(Person.class, id);
    }

    public void edit(long id, String name){
        Person person = this.getPersonById(id);
        //Ejecuta un commit internamente, por lo que los cambios se persisten en base de datos, este comportamiento se puede alterar modificando las propiedades de @Transactional (readOnly=true)
        person.setName(name);
    }
}
