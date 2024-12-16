package com.example.courseproject.services;

import com.example.courseproject.domain.Entities.RailwayEntity;
import com.example.courseproject.domain.Entities.ZmaxEntity;
import com.example.courseproject.repositories.impl.OrdinatesRepositoryImpl;

public class OrdinatesService {
    //
    //для расчёта Sнп
    double Lsh;//в зависимости от эпюр укладки
    double beta;//в зависимости от типа рельс
    double y; // в зависимости от рода балласта
    double e; // в зависимости от типа шпал
    double a1; // в зависимости от типа шпал
    double qk = 9.95;//из табл 4.2 для 2-осной тележки (4-осной);
    double V;//ввод
    //
    //

    // для расчёта Sинк
    //
    double e0 = 0.133*Math.pow(10,-2);
    double ymax = 1.47*e0;
    double a0;// в зависимости от типа шпал
    //
    //

    //
    // применятеся в расчётах Sнп и Sинк
    double K;//из базы данных
    int U;//из базы данных
    //
    //
    private RailwayEntity railwayEntity;
    private ZmaxEntity zmax;

    public OrdinatesService(RailwayEntity railwayEntity, ZmaxEntity zmax, double v){
        this.railwayEntity = railwayEntity;
        this.zmax = zmax;
        this.V = v;
        setLsh();
        setBeta();
        setA1();
        setE();
        setY();
        setA0();
        OrdinatesRepositoryImpl ordinatesRepositoryImpl = new OrdinatesRepositoryImpl(railwayEntity);
        U = ordinatesRepositoryImpl.readU();
        K = ordinatesRepositoryImpl.readK();
    }
    public Double getNu(){
        return getPcp()/getPekv();
    }
    Double getPcp(){
        return 0.75*getPpmax();
    }
    Double getPekv(){
        return getPpac()+getPcp();
    }
    Double getPpmax(){
        return 2*zmax.getZmaxValue();
    }

    Double getPpac(){
        return getPcp() + 2.5*Math.sqrt(getSp()*getSp() + getSnp()*getSnp() + 0.05*getSink()*getSink() + 0.95*getSink());
    }

    Double getSp(){
        return 0.08*getPpmax();
    }
    Double getSnp(){
        return 2.034*Math.pow(10,-5)*a1*e*beta*y*Lsh*Math.sqrt((U*qk)/K)*getPcp()*V;
    }
    Double getSink(){
        return 0.5 * Math.pow(10,6)*ymax*(U/K)*a0*e0;
    }


    void setLsh() {
        switch (railwayEntity.getEpures()){
            case 1600 -> Lsh = 0.63;
            case 1840 -> Lsh = 0.55;
            case 2000 -> Lsh = 0.5;
        }
    }

    void setBeta() {
        switch (railwayEntity.getRailsType()){
            case P50 -> beta = 1.0;
            case P65 -> beta = 0.87;
            case P75 -> beta= 0.82;
        }
    }

    void setY() {
        switch (railwayEntity.getBallastType()){
            case sheben -> y = 1.00;
            case gravii -> y = 1.1;
            case pesok -> y = 1.5;
        }
    }

    void setE() {
        switch (railwayEntity.getSleepersType()){
            case woodenSleepers -> e = 1.00;
            case ironSleepers -> e = 0.322;
        }
    }

    void setA1() {
        switch (railwayEntity.getSleepersType()){
            case woodenSleepers -> a1 = 1.00;
            case ironSleepers -> a1 = 0.931;
        }
    }

    void setA0() {
        switch (railwayEntity.getSleepersType()){
            case ironSleepers -> a0 = 0.403;
            case woodenSleepers -> a0 = 0.433;
        }
    }
}
