package com.example.courseproject.DAL.OrdinatesCRUD;


import com.example.courseproject.BLL.Entities.RailwayEntity;
import com.example.courseproject.DAL.DBConnectivity.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdinatesCRUD extends DB {
    private RailwayEntity railwayEntity;
    public OrdinatesCRUD(RailwayEntity railwayEntity){
        this.railwayEntity = railwayEntity;
    }
    public int readU(){
        String insert = "select U from railstype where railstype = '"+ railwayEntity.getRailsTypeForDB() +
                "' and epures = '"+ railwayEntity.getEpures()+"' and sleepersType = '"+ railwayEntity.getSleepersTypeForDB() +
                "' and ballast = '" + railwayEntity.getBallastTypeForDB() + "';";
        System.out.println(insert);
        int U;
        try{
                PreparedStatement preparedStatement = DB().prepareStatement(insert);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                U = Integer.parseInt(resultSet.getString("U"));

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return U;
    }
    public double readK(){
        String insert = "select K from railstype where railstype = '"+ railwayEntity.getRailsTypeForDB() +
                "' and epures = '"+ railwayEntity.getEpures()+"' and sleepersType = '"+ railwayEntity.getSleepersTypeForDB() +
                "' and ballast = '" + railwayEntity.getBallastTypeForDB() + "';";
        double K;
        try{
            PreparedStatement preparedStatement = DB().prepareStatement(insert);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            K = Double.parseDouble(resultSet.getString("K"));

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return K;
    }
}
