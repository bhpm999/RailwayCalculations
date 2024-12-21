package com.example.courseproject.services;

import com.example.courseproject.domain.Entities.RailwayEntity;
import com.example.courseproject.repositories.impl.OrdinatesRepositoryImpl;

public class OrdinatesService {
    //
    //
    double Jp = 2.0 * Math.pow(10,6);
    double Pct = 110.0 *Math.pow(10,3);

    //
    //для расчёта Sнп
    double Lsh;//в зависимости от эпюр укладки
    double beta;//в зависимости от типа рельс
    double y; // в зависимости от рода балласта
    double e; // в зависимости от типа шпал
    double a1; // в зависимости от типа шпал
    double qk = 9.95 * Math.pow(10,3);//из табл 4.2 для 2-осной тележки (4-осной);
    double V;//ввод
    //
    //

    // для расчёта Sинк
    //
    double d = 0.95;
    double e0 = 0.067*Math.pow(10,-2);
    double ymax = 1.47;
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

    public OrdinatesService(RailwayEntity railwayEntity, double v){
        this.railwayEntity = railwayEntity;
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
        System.out.println(U+" "+K);
        System.out.println("zmax "+getZmax());
        System.out.println("Ppmax "+getPpmax());
        System.out.println("Pcpp "+getPcpp());
        System.out.println("Pcp "+getPcp());
        System.out.println("Sp "+getSp());
        System.out.println("Snp "+getSnp());
        System.out.println("Sink1 "+getSink1());
        System.out.println("Sink2 "+getSink2());
        System.out.println("Ppac "+getPpac());
        System.out.println("x "+getX());
        System.out.println("K "+K);
        System.out.println("Kx " +K*getX());
        System.out.println("Nu "+getNu());
        System.out.println("Pcp*Nu "+getPcp()*getNu());
        System.out.println("Pekv "+getPekv());
        System.out.println("Q "+getQ());
    }
    public Double getNuq(){
        return getPekv()/getPcp();
    }
    public Double getQ(){
        return (K*Lsh)*getPekv()/2;
    }
    Double getPekv(){
        return getPpac()+ getPcp()*getNu();
    }
    Double getNu(){
        double x = getX();
        return (Math.exp(-K*x))*(Math.cos(K*x) + Math.sin(K*x));
    }
    Double getX(){
        return (3*3.14)/(4*K);
    }
    Double getPpac(){
        return getPcp() + 2.5*Math.sqrt(getSp()*getSp() + getSnp()*getSnp() + 0.05* getSink1()* getSink1() + 0.95* getSink2()*getSink2());
    }
    double getSink2(){
        return (6.739*Math.pow(10,-3)*U*V*V*a0*Math.sqrt(qk))/(d*d*Math.sqrt((K*U)-(326*K*K*qk*Math.pow(10,-6))));
    }
    Double getSink1(){
        return 0.5 * Math.pow(10,6)*ymax*(U/K)*a0*e0;
    }
    Double getSnp(){
        return 2.034*Math.pow(10,-5)*a1*e*beta*y*Lsh*Math.sqrt((U*qk)/K)* getPcp()*V;
    }
    Double getSp(){
        return 0.08*getPpmax();
    }
    Double getPcp(){ return Pct + getPcpp();}
    Double getPcpp(){
        return 0.75*getPpmax();
    }
    Double getPpmax(){
        return Jp*getZmax();
    }
    Double getZmax(){
        return (10 + (16*Math.pow(10,-4)*V*V))*Math.pow(10,-3);
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
