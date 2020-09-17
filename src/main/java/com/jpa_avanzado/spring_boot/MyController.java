package com.jpa_avanzado.spring_boot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @Autowired
    private MyService myService;

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

    @RequestMapping(path = "/create")
    @ResponseBody
    public Persona createPerson(String name){
        return myService.create(name);
    }
}
