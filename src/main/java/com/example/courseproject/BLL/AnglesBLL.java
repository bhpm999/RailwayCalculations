package com.example.courseproject.BLL;

public class AnglesBLL {
    double height;
    int sleeperWidth;
    public AnglesBLL(double height,int sleeperWidth){
        this.height = height;
        this.sleeperWidth = sleeperWidth;
    }
    public double angleResult(){
        return Math.atan(((double) sleeperWidth /2)/height)*180/3.14;
    }
}
