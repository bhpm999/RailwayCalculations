package com.example.courseproject.BLL.Entities;

//Прогиб рессор, мм
public class ZmaxEntity {
    private double a = 10.0;
    private double b = 16.0 * Math.pow(10,-4);
    private double V;

    public ZmaxEntity(double v) {
        V = v;
    }

    public double getZmaxValue(){
        return a+(b*V*V);
    }
}
