module com.example.courseproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;



    exports com.example.courseproject.BLL.Enums;
    opens com.example.courseproject.BLL.Enums to javafx.fxml;
    exports com.example.courseproject.GUI.Interfaces;
    opens com.example.courseproject.GUI.Interfaces to javafx.fxml;
    exports com.example.courseproject.BLL.Entities;
    opens com.example.courseproject.BLL.Entities to javafx.fxml;
    exports com.example.courseproject.DAL.DBConnectivity;
    opens com.example.courseproject.DAL.DBConnectivity to javafx.fxml;
    exports com.example.courseproject.GUI;
    opens com.example.courseproject.GUI to javafx.fxml;
    exports com.example.courseproject.DAL.repositories.impl;
    opens com.example.courseproject.DAL.repositories.impl to javafx.fxml;
    exports com.example.courseproject.BLL.services;
    opens com.example.courseproject.BLL.services to javafx.fxml;
}