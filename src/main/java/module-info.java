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
    exports com.example.courseproject.DAL.OrdinatesCRUD;
    opens com.example.courseproject.DAL.OrdinatesCRUD to javafx.fxml;
    exports com.example.courseproject.DAL.AnglesCRUD;
    opens com.example.courseproject.DAL.AnglesCRUD to javafx.fxml;
    exports com.example.courseproject.GUI;
    opens com.example.courseproject.GUI to javafx.fxml;
    exports com.example.courseproject.BLL;
    opens com.example.courseproject.BLL to javafx.fxml;
}