module com.example.courseproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;
    requires java.persistence;
    requires org.hibernate.commons.annotations;
    requires org.hibernate.orm.core;
    requires org.hibernate.orm.c3p0;
    requires jakarta.annotation;
    requires lombok;
    requires java.naming;



    exports com.example.courseproject.domain.Enums;
    opens com.example.courseproject.domain.Enums to javafx.fxml;
    exports com.example.courseproject.GUI.Interfaces;
    opens com.example.courseproject.GUI.Interfaces to javafx.fxml;
    exports com.example.courseproject.domain.Entities;
    opens com.example.courseproject.domain.Entities to javafx.fxml;
    exports com.example.courseproject.GUI;
    opens com.example.courseproject.GUI to javafx.fxml;
    exports com.example.courseproject.repositories.impl;
    opens com.example.courseproject.repositories.impl to javafx.fxml;
    exports com.example.courseproject.services;
    opens com.example.courseproject.services to javafx.fxml;
    exports com.example.courseproject;
    opens com.example.courseproject to javafx.fxml;
    exports com.example.courseproject.statics;
    opens com.example.courseproject.statics to javafx.fxml;
    exports com.example.courseproject.controllers;
    opens com.example.courseproject.controllers to javafx.fxml;
}