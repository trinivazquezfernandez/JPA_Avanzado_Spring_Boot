package com.jpa_avanzado.spring_boot;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public int plus(int a, int b){
        return a + b;
    }
}
