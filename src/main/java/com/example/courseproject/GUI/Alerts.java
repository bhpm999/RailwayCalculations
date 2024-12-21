package com.example.courseproject.GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.concurrent.CancellationException;

public class Alerts {

    public static void incorrectData(){
        Alert blankData = new Alert(Alert.AlertType.ERROR,"Некорректно введённые данные", ButtonType.OK);
        blankData.setTitle("Неверные данные");
        blankData.show();
    }
    public static void notExistAlert(){
        Alert blankData = new Alert(Alert.AlertType.ERROR,"Рельсового пути с такими характеристиками не существует", ButtonType.OK);
        blankData.setTitle("Неверные данные");
        blankData.show();
    }

    public static void blankDataAlert(){
        Alert blankData = new Alert(Alert.AlertType.ERROR,"Незаполненные данные", ButtonType.OK);
        blankData.setTitle("Неверные данные");
        blankData.show();
    }
}