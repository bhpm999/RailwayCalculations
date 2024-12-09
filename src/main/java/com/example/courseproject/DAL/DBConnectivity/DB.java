package com.example.courseproject.DAL.DBConnectivity;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection DB() throws SQLException,ClassNotFoundException{
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/railway",
                "root",
                "12345"
        );
        return connection;
    }

}
