package com.qa.opencart.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvUtil {

    public static String CSV_Path = "./src/main/resources/testdata/";
    public static List<String[]> rows;
    public static Object[][] getCSVData(String csvName){
        String csvFile = CSV_Path + csvName + ".csv";

        CSVReader reader;
        try {
        reader = new CSVReader(new FileReader(csvFile));
        rows = reader.readAll();
        reader.close();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        Object[][] data = new Object[rows.size()][];
        for (int i=0;i<rows.size();i++){
            data[i] = rows.get(i);
        }
        return data;
    }
}