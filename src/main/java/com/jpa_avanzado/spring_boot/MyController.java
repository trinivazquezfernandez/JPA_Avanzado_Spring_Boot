package com.jpa_avanzado.spring_boot;
import com.jpa_avanzado.spring_boot.entities.Audit;
import com.jpa_avanzado.spring_boot.entities.Person;
import com.jpa_avanzado.spring_boot.services.AuditService;
import com.jpa_avanzado.spring_boot.services.MyService;
import com.jpa_avanzado.spring_boot.services.PersonService;
import com.jpa_avanzado.spring_boot.services.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.persistence.PersistenceContext;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private PersonService personService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private MyService myService;

    @Autowired WebService webService;


    @Value("{spring.jpa.open-in-view}")
    private String openInView;

    @PostConstruct
    public void init(){
        personService.init();
    }

    @RequestMapping(path = "/hello")
    @ResponseBody
    public String SayHello(String name){
        return "Hello " + name;
    }

    @RequestMapping(path = "/plus")
    @ResponseBody
    public String plus(int a, int b){
        return "Suma de " + a + " + " + b + " = " + personService.plus(a,b);
    }

    @RequestMapping(path = "/createPerson")
    @ResponseBody
    public Person createPerson(String name, String city){
        return personService.create(name, city);
    }

    @RequestMapping(path = "/getAll")
    @ResponseBody
    public int getAll(){
        StopWatch sp = new StopWatch();
        sp.start("consulta");
        log();
        int name = personService.getAll().size();
        sp.stop();
        System.out.println(sp.prettyPrint());
        return name;
    }

    @RequestMapping(path = "/editPerson")
    @ResponseBody
    public String editPerson(long id, String name){
        log();
        personService.edit(id, name);
        return personService.getPersonById(id).getName();
    }

    @RequestMapping(path = "/logs")
    @ResponseBody
    public List<Audit> logs(){
        log();
        return auditService.getAll();
    }

    @RequestMapping(path = "/antecedentes")
    @ResponseBody
    public String criminalRecord(long id){
        Person person = myService.getPerson(id);
        String result = webService.criminalRecord(person);
        return result;
    }

    @RequestMapping(path = "/name")
    @ResponseBody
    public String getName(long id){
        return myService.getPerson(id).getName();
    }


    private void log(){
        System.out.println("------ usando: spring.jpa.open-in-view="+ openInView);
    }
}
