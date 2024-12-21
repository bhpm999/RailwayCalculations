package com.example.courseproject.controllers;


import com.example.courseproject.GUI.Alerts;
import com.example.courseproject.exeptions.IncorrectDataException;
import com.example.courseproject.services.AnglesService;
import com.example.courseproject.domain.Entities.RailwayEntity;
import com.example.courseproject.domain.Enums.Ballast;
import com.example.courseproject.domain.Enums.Rails;
import com.example.courseproject.domain.Enums.Sleepers;
import com.example.courseproject.services.OrdinatesService;
import com.example.courseproject.GUI.Interfaces.ElementsVisibility;
import com.example.courseproject.statics.STATICS;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.Objects;

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
    public ComboBox<Integer> sleeperWidthCombobox;
    public Text forceResultText;
    ToggleGroup toggleGroup = new ToggleGroup();
    EventHandler<KeyEvent> enterEvent;

    public void initialize(){
        woodenSleepersButton.setToggleGroup(toggleGroup);
        ironSleepersButton.setToggleGroup(toggleGroup);
        vValueTextfield.requestFocus();
        heightTextfield.requestFocus();


    }
    private EventHandler<KeyEvent> setEnterEvent(Button button){
        enterEvent = keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER)
                button.fire();
        };
        return enterEvent;
    }
    public void setForOrdinatesButton(){
        setVisibility(workAnchor,"forOrdinates");
        ordinateResultText.setVisible(false);
        forceResultText.setVisible(false);
        vValueTextfield.requestFocus();
        if(enterEvent!=null)
            workAnchor.removeEventFilter(KeyEvent.KEY_PRESSED,enterEvent);
        workAnchor.addEventFilter(KeyEvent.KEY_PRESSED,setEnterEvent(calculateOrdinateButton));
    }
    public void setForAnglesButton(){
        setVisibility(workAnchor,"forAngles");
        angleResultText.setVisible(false);
        heightTextfield.requestFocus();
        if(enterEvent!=null)
            workAnchor.removeEventFilter(KeyEvent.KEY_PRESSED, enterEvent);
        workAnchor.addEventFilter(KeyEvent.KEY_PRESSED, setEnterEvent(calculateAngleButton));
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
        try {
            checkOrdinateValues();
            if(getV()<0)
                throw new IncorrectDataException();
            getRailwayInstance();
            RailwayEntity railwayEntity = new RailwayEntity(railsType, epureYkladkiCombobox.getValue(), sleepersType, ballastType);
            OrdinatesService ordinatesService = new OrdinatesService(railwayEntity, getV());
            String nu = String.format("%.2f",ordinatesService.getNuq());
            String Q = String.format("%.0f, Н",ordinatesService.getQ());
            ordinateResultText.setText(nu);
            forceResultText.setText(Q);
            ordinateResultText.setVisible(true);
            forceResultText.setVisible(true);
        }catch (NullPointerException e){
            Alerts.blankDataAlert();
        } catch (IncorrectDataException | NumberFormatException e) {
            Alerts.incorrectData();
        }
    }
    private void getRailwayInstance(){
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
    private double getV(){
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
        try {
            checkAngleValues();
            if(getHeight()<30)
                throw new IncorrectDataException();

            AnglesService anglesService = new AnglesService(getHeight(), getWidth());
            String angle = String.format("%.2f°",anglesService.angleResult());
            angleResultText.setText(angle);
            angleResultText.setVisible(true);
        }catch (NullPointerException e){
            Alerts.blankDataAlert();
        } catch (IncorrectDataException | NumberFormatException e) {
            Alerts.incorrectData();
        }
    }

    private int getWidth() {
        return sleeperWidthCombobox.getValue();
    }
    private double getHeight(){
        return Double.parseDouble(heightTextfield.getText());
    }
    private void checkOrdinateValues(){
            if(vValueTextfield.getText().isEmpty() ||
            typeOfRailsCombobox.getValue() == null ||
            typeOfBallastCombobox.getValue() == null ||
            epureYkladkiCombobox.getValue() == null ||
                    !ironSleepersButton.isSelected() && !woodenSleepersButton.isSelected())
                throw new NullPointerException();
    }
    private void checkAngleValues(){
        if(heightTextfield.getText().isEmpty() || sleeperWidthCombobox.getValue() == null)
            throw  new NullPointerException();
    }
}