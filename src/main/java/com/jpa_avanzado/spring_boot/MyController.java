package com.jpa_avanzado.spring_boot;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @RequestMapping(path = "/hello")
    @ResponseBody
    public String SayHello(String name){
        return "Hello " + name;
    }
}
