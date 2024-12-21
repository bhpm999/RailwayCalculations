package com.example.courseproject.GUI.Interfaces;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Objects;

public interface ElementsVisibility {
    default void setVisibility(AnchorPane anchorPane, String elementID){
        ObservableList<Node> adminPanelElements = FXCollections.observableArrayList();
        adminPanelElements.addAll(anchorPane.getChildren());
        for (Node element: adminPanelElements) {
            if(Objects.equals(element.getId(), elementID) || Objects.equals(element.getId(),"systemElement")){
                element.setVisible(true);
            }
            else
                element.setVisible(false);
        }
    }
}
