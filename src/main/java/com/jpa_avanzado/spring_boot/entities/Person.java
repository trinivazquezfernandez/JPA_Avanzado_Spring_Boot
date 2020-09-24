package com.jpa_avanzado.spring_boot.entities;

import javax.persistence.*;

@Entity
public class Person {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private String city;
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    private int queries;
    public int getQueries() {
        return queries;
    }
    public void setQueries(int queries) {
        this.queries = queries;
    }
}
