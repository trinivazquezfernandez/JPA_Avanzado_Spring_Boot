package com.jpa_avanzado.spring_boot.services;

import com.jpa_avanzado.spring_boot.entities.Person;
import org.springframework.stereotype.Component;

@Component
public class WebService {
    public String criminalRecord(Person person){
        System.out.println("esperando informe antecedentes " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000*20);
        }catch (InterruptedException e) {
            System.out.println("despierto!");
        }
        System.out.println("obtenida respuesta");
        return "muy mal todo!";
    }
}
