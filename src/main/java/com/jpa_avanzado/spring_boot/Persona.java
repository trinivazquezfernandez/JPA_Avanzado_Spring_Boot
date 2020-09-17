package com.jpa_avanzado.spring_boot;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Persona {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Id
    private String name;

}
