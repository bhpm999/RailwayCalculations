package com.example.courseproject.services;

public class AnglesService {
    double height;
    int sleeperWidth;
    public AnglesService(double height, int sleeperWidth){
        this.height = height;
        this.sleeperWidth = sleeperWidth;
    }
    public double angleResult(){
        return Math.atan(((double) sleeperWidth /2)/height)*180/3.14;
    }
}
