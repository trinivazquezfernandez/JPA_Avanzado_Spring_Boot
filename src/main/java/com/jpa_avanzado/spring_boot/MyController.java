package com.jpa_avanzado.spring_boot;
import com.jpa_avanzado.spring_boot.entities.Person;
import com.jpa_avanzado.spring_boot.services.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @Autowired
    private MyService myService;

    @Value("{spring.jpa.open-in.view}")
    private String openInView;

    @RequestMapping(path = "/hello")
    @ResponseBody
    public String SayHello(String name){
        return "Hello " + name;
    }

    @RequestMapping(path = "/plus")
    @ResponseBody
    public String plus(int a, int b){
        return "Suma de " + a + " + " + b + " = " + myService.plus(a,b);
    }

    @RequestMapping(path = "/createPerson")
    @ResponseBody
    public Person createPerson(String name, String city){
        return myService.create(name, city);
    }

    @RequestMapping(path = "/getPersonById")
    @ResponseBody
    public boolean getPerson(long id){
        log();
        Person person1 = myService.getPersonById(id);
        Person person2 = myService.getPersonById(id);

        //Si spring.jpa.open-in-view vale true, devuelve true (el EntityManager se mantiene en ambas peticiones al servicio)
        //Si spring.jpa.open-in-view vale false, devuelve false (el EntityManager se crea y se destruye en cada transacción)
        return person1 == person2;
    }

    @RequestMapping(path = "/editPerson")
    @ResponseBody
    public String editPerson(long id, String name){
        log();
        myService.edit(id, name);
        return myService.getPersonById(id).getName();
    }

    private void log(){
        System.out.println("------ usando: spring.jpa.open-in-view="+ openInView);
    }
}
