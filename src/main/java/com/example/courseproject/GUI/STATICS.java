package com.example.courseproject.GUI;

import com.example.courseproject.BLL.Enums.Rails;

import java.util.Arrays;
import java.util.List;

public class STATICS {
    public static List<String> ballastTypes = Arrays.asList("Щебень","Песок","Гравий");
    public static List<Integer> epures = Arrays.asList(2000,1840,1600);
    public static List<String> railsTypes = Arrays.asList(Rails.P50.toString(),Rails.P65.toString(),Rails.P75.toString());
    public static List<Integer> sleeperWidths = Arrays.asList(23,25,27);
}
