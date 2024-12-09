package com.example.courseproject.BLL.Entities;

import com.example.courseproject.BLL.Enums.Ballast;
import com.example.courseproject.BLL.Enums.Rails;
import com.example.courseproject.BLL.Enums.Sleepers;

//Характеристика рельсового пути
public class RailwayEntity {

    public RailwayEntity(Rails railsType, int epures, Sleepers sleepersType, Ballast ballastType) {
        this.railsType = railsType;
        this.epures = epures;
        this.sleepersType = sleepersType;
        this.ballastType = ballastType;
        switch (railsType){
            case P75 -> railsTypeForDB = "P75";
            case P50 -> railsTypeForDB = "P50";
            case P65 -> railsTypeForDB = "P65";
        }
        switch (sleepersType){
            case ironSleepers -> sleepersTypeForDB = "ЖБ";
            case woodenSleepers -> sleepersTypeForDB = "Д";
        }
        switch (ballastType){
            case pesok -> ballastTypeForDB = "П";
            case gravii -> ballastTypeForDB = "ГР";
            case sheben -> ballastTypeForDB = "Щ";
        }
    }

    private Rails railsType;
    private int epures;
    private Sleepers sleepersType;
    private Ballast ballastType;
    private String railsTypeForDB;
    private String sleepersTypeForDB;
    private String ballastTypeForDB;

    public Rails getRailsType() {
        return railsType;
    }

    public int getEpures() {
        return epures;
    }

    public Sleepers getSleepersType() {
        return sleepersType;
    }

    public Ballast getBallastType() {
        return ballastType;
    }

    public String getRailsTypeForDB() {
        return railsTypeForDB;
    }

    public String getSleepersTypeForDB() {
        return sleepersTypeForDB;
    }

    public String getBallastTypeForDB() {
        return ballastTypeForDB;
    }
}
