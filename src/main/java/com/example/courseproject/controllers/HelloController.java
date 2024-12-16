package com.example.courseproject.controllers;


import com.example.courseproject.GUI.Alerts;
import com.example.courseproject.services.AnglesService;
import com.example.courseproject.domain.Entities.RailwayEntity;
import com.example.courseproject.domain.Entities.ZmaxEntity;
import com.example.courseproject.domain.Enums.Ballast;
import com.example.courseproject.domain.Enums.Rails;
import com.example.courseproject.domain.Enums.Sleepers;
import com.example.courseproject.services.OrdinatesService;
import com.example.courseproject.GUI.Interfaces.ElementsVisibility;
import com.example.courseproject.statics.STATICS;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class HelloController implements ElementsVisibility {

    public RadioButton ironSleepersButton;
    public RadioButton woodenSleepersButton;
    public ComboBox<String> typeOfRailsCombobox;
    public ComboBox<Integer> epureYkladkiCombobox;
    public ComboBox<String> typeOfBallastCombobox;
    public TextField vValueTextfield;
    public Button calculateOrdinateButton;
    public Text ordinateResultText;
    public Button forOrdinatesButton;
    public Button forAnglesButton;
    public AnchorPane workAnchor;
    public Sleepers sleepersType;
    public Ballast ballastType;
    public Rails railsType;
    public TextField heightTextfield;
    public Button calculateAngleButton;
    public Text angleResultText;
    public ComboBox sleeperWidthCombobox;
    ToggleGroup toggleGroup = new ToggleGroup();

    public void initialize(){
        woodenSleepersButton.setToggleGroup(toggleGroup);
        ironSleepersButton.setToggleGroup(toggleGroup);

    }
    public void setForOrdinatesButton(){
        setVisibility(workAnchor,"forOrdinates");
    }
    public void setForAnglesButton(){
        setVisibility(workAnchor,"forAngles");
    }

    //
    //для ординат
    public void setTypeOfRailsCombobox(){
        typeOfRailsCombobox.setItems(FXCollections.observableArrayList(
                STATICS.railsTypes
        ));
    }
    public void setTypeOfBallastCombobox(){
        typeOfBallastCombobox.setItems(FXCollections.observableArrayList(
                STATICS.ballastTypes
        ));
    }
    public void setEpureYkladkiCombobox(){
        epureYkladkiCombobox.setItems(FXCollections.observableArrayList(
                STATICS.epures
        ));
    }
    public void setCalculateOrdinateButton(){
        if(Double.parseDouble(vValueTextfield.getText())>0)
        {
            getRailwayInstance();
            RailwayEntity railwayEntity = new RailwayEntity(railsType,epureYkladkiCombobox.getValue(),sleepersType,ballastType);
            ZmaxEntity zmax = new ZmaxEntity(getV());
            OrdinatesService nu = new OrdinatesService(railwayEntity,zmax,getV());
            ordinateResultText.setText(nu.getNu().toString());
            ordinateResultText.setVisible(true);
        }else{
            System.out.println("!");
        }
    }
    public void getRailwayInstance(){
        switch (typeOfRailsCombobox.getValue()){
            case "P50" -> railsType = Rails.P50;
            case "P65" -> railsType = Rails.P65;
            case "P75" -> railsType = Rails.P75;
        }
        switch (typeOfBallastCombobox.getValue()){
            case "Щебень" -> ballastType = Ballast.sheben;
            case "Песок" -> ballastType = Ballast.pesok;
            case "Гравий" -> ballastType = Ballast.gravii;

        }
        if (woodenSleepersButton.isSelected()){
            sleepersType = Sleepers.woodenSleepers;
        } else if (ironSleepersButton.isSelected()) {
            sleepersType = Sleepers.ironSleepers;
        }
    }
    public double getV(){
        return Double.parseDouble(vValueTextfield.getText());
    }
    //
    //

    //
    //для углов
    public void setSleeperWidthCombobox(){
        sleeperWidthCombobox.setItems(FXCollections.observableArrayList(
                STATICS.sleeperWidths
        ));
    }
    public void setCalculateAngleButton(){
        AnglesService anglesService = new AnglesService(getHeight(),getWidth());
        angleResultText.setText( String.valueOf(anglesService.angleResult()));
        angleResultText.setVisible(true);
    }
    public double getHeight(){
        double h = Double.parseDouble(heightTextfield.getText());

        if(h<30){
            Alerts.blankDataAlert();
        }
        return h;
    }
    public int getWidth(){
        int w = 0;
        try {
            w = (int)sleeperWidthCombobox.getValue();
        }catch (NullPointerException e){Alerts.blankDataAlert();}
        return w;
    }
}