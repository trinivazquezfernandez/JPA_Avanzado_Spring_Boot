package com.jpa_avanzado.spring_boot.services;

import com.jpa_avanzado.spring_boot.entities.Audit;
import com.jpa_avanzado.spring_boot.entities.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
//Fuerza que el servicio tenga una transaccion propia aunque derive de otra y por tanto maneje su propio persistenceContext
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AuditService {

    @PersistenceContext
    private EntityManager em;

    public void log(String event){
        Audit audit = new Audit();
        audit.setEvent(event);
        em.persist(audit);
    }

    public void log (Person person){
        Audit audit = new Audit();
        String message = String.format("Consulta num %d a %s", person.getQueries(), person.getName());
        audit.setEvent(message);
        person.setQueries(person.getQueries() + 1);

        em.persist(audit);
    }

    public List<Audit> getAll(){
        return em.createQuery("select a from Audit a order by a.id", Audit.class).getResultList();
    }
}
