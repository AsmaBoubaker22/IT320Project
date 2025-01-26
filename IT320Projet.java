/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

public class IT320Projet {

    public static void main(String[] args) {
        System.out.println("Hellooooooooooooooo!");
        
        CSVIngestion csvIngestion = new CSVIngestion();
        
        String filePath = "C:\\Users\\Asma\\Desktop\\SampleData.csv"; 
        
        List<List<String>> data = csvIngestion.csvImport(filePath);
        
        PropertyDataCleaner cleaner = new PropertyDataCleaner();
        
        cleaner.removeMissingValues(data);
        cleaner.removeDuplicates(data);
        cleaner.removeOutliers(data);
        cleaner.fixYear(data);
        
        // Now, inject the data into the respective objects CSV--------------------------------------------------------------
        csvIngestion.csvInject(data);
      
        System.out.println("Buyers List: " + csvIngestion.buyersList);
        System.out.println("Agents List: " + csvIngestion.agentsList);
        System.out.println("Houses List: " + csvIngestion.housesList);
        System.out.println("Locations List: " + csvIngestion.locationList);
        System.out.println("Units List: " + csvIngestion.unitsList);
        System.out.println("Townhouses List: " + csvIngestion.townhousesList);
        System.out.println("Development Sites List: " + csvIngestion.developmentSitesList);
        System.out.println("Transactions List: " + csvIngestion.transactionsList);
        
        
        XLSIngestion xlsIngestion = new XLSIngestion();
        String filePath2 = "C:\\Users\\Asma\\Desktop\\SampleDataX.xlsx"; 
        List<List<String>> data2 = xlsIngestion.xlsImport(filePath2);
        
        cleaner.removeMissingValues(data2);
        cleaner.removeDuplicates(data2);
        cleaner.removeOutliers(data2);
        cleaner.fixYear(data2);
        
        // Now, inject the data into the respective objects--------------------------------------------------------------
        xlsIngestion.xlsInject(data2);
        System.out.println("Buyers List: " + xlsIngestion.buyersList);
        System.out.println("Agents List: " + xlsIngestion.agentsList);
        System.out.println("Houses List: " + xlsIngestion.housesList);
        System.out.println("Locations List: " + xlsIngestion.locationList);
        System.out.println("Units List: " + xlsIngestion.unitsList);
        System.out.println("Townhouses List: " + xlsIngestion.townhousesList);
        System.out.println("Development Sites List: " + xlsIngestion.developmentSitesList);
        System.out.println("Transactions List: " + xlsIngestion.transactionsList);
        
        // Combine the lists from both CSV and XLS into a unified list for each type---------------------------
        List<Buyer> buyersList = new ArrayList<>();
        buyersList.addAll(csvIngestion.buyersList);
        buyersList.addAll(xlsIngestion.buyersList);

        List<Agent> agentsList = new ArrayList<>();
        agentsList.addAll(csvIngestion.agentsList);
        agentsList.addAll(xlsIngestion.agentsList);

        List<House> housesList = new ArrayList<>();
        housesList.addAll(csvIngestion.housesList);
        housesList.addAll(xlsIngestion.housesList);

        List<Location> locationList = new ArrayList<>();
        locationList.addAll(csvIngestion.locationList);
        locationList.addAll(xlsIngestion.locationList);

        List<Unit> unitsList = new ArrayList<>();
        unitsList.addAll(csvIngestion.unitsList);
        unitsList.addAll(xlsIngestion.unitsList);

        List<Townhouse> townhousesList = new ArrayList<>();
        townhousesList.addAll(csvIngestion.townhousesList);
        townhousesList.addAll(xlsIngestion.townhousesList);

        List<DevelopmentSite> developmentSitesList = new ArrayList<>();
        developmentSitesList.addAll(csvIngestion.developmentSitesList);
        developmentSitesList.addAll(xlsIngestion.developmentSitesList);

        List<Transaction> transactionsList = new ArrayList<>();
        transactionsList.addAll(csvIngestion.transactionsList);
        transactionsList.addAll(xlsIngestion.transactionsList);
        
        /*
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\units.csv", unitsList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\agents.csv", agentsList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\buyers.csv", buyersList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\transactions.csv", transactionsList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\houses.csv", housesList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\dev.csv", developmentSitesList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\townhouses.csv", townhousesList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\locations.csv", locationList);
*/
        
        // EXPORT TO DATABASE ---------------------------------------------------------------------------------
        DatabaseExporter exporter = new DatabaseExporter("jdbc:mysql://localhost:3306/java_db", "root", "asma");
        exporter.exportData(buyersList, agentsList, housesList, unitsList, townhousesList, developmentSitesList, transactionsList);

        
        
 
        
}
}

