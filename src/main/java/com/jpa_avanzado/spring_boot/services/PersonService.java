package com.jpa_avanzado.spring_boot.services;

import com.jpa_avanzado.spring_boot.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class PersonService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AuditService auditService;

    public int plus(int a, int b){
        return a + b;
    }

    public Person create(String name, String city){
        auditService.log("Alta de " + name);

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
        Person person = em.find(Person.class, id);
        auditService.log(person);

        if (person.getQueries() > 3){
            throw new RuntimeException("Demasiadas consultas");
        }

        return person;
    }

    public void edit(long id, String name){
        Person person = this.getPersonById(id);
        String oldName = person.getName();
        //Ejecuta un commit internamente, por lo que los cambios se persisten en base de datos, este comportamiento se puede alterar modificando las propiedades de @Transactional (readOnly=true)
        person.setName(name);

        auditService.log("Modificado " + oldName + " por " + name);
    }
}
