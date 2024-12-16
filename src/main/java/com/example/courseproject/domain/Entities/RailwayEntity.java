package com.example.courseproject.domain.Entities;

import com.example.courseproject.domain.Enums.Ballast;
import com.example.courseproject.domain.Enums.Rails;
import com.example.courseproject.domain.Enums.Sleepers;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;

//Характеристика рельсового пути
@Getter
public class RailwayEntity {
    private Rails railsType;
    private Integer epures;
    private Sleepers sleepersType;
    private Ballast ballastType;
    private String railsTypeForDB;
    private String sleepersTypeForDB;
    private String ballastTypeForDB;

    public RailwayEntity( Rails railsType,  Integer epures,  Sleepers sleepersType,  Ballast ballastType) {
        this.railsType = railsType;
        this.epures = epures;
        this.sleepersType = sleepersType;
        this.ballastType = ballastType;
        setStringFields();
    }

    private void setStringFields(){
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
            case gravii -> ballastTypeForDB = "Гр";
            case sheben -> ballastTypeForDB = "Щ";
        }
    }
}
