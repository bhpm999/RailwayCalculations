package com.example.courseproject.domain.Entities;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

//Прогиб рессор, мм
@RequiredArgsConstructor
public class ZmaxEntity {
    private double a = 10.0;
    private double b = 16.0 * Math.pow(10,-4);
    @NonNull
    private Double V;

    public double getZmaxValue(){
        return a+(b*V*V);
    }
}
