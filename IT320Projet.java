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

public class IT320Projet {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        // Create an instance of CSVIngestion
        CSVIngestion csvIngestion = new CSVIngestion();
        
        // Define the file path of the CSV file you want to import
        String filePath = "C:\\Users\\Asma\\Desktop\\SampleData.csv"; // Replace with actual CSV file path
        
        // Import the CSV file and store the data in a list of rows
        List<List<String>> data = csvIngestion.csvImport(filePath);
        
        PropertyDataCleaner cleaner = new PropertyDataCleaner();
        
        cleaner.removeMissingValues(data);
        cleaner.removeDuplicates(data);
        cleaner.removeOutliers(data);
        cleaner.fixYear(data);
        
        // Now, inject the data into the respective objects
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
        String filePath2 = "C:\\Users\\Asma\\Desktop\\SampleDataX.xlsx"; // Replace with actual CSV file path
        List<List<String>> data2 = xlsIngestion.xlsImport(filePath2);
        
        cleaner.removeMissingValues(data2);
        cleaner.removeDuplicates(data2);
        cleaner.removeOutliers(data2);
        cleaner.fixYear(data2);
        
        // Now, inject the data into the respective objects
        xlsIngestion.xlsInject(data2);
        System.out.println("Buyers List: " + xlsIngestion.buyersList);
        System.out.println("Agents List: " + xlsIngestion.agentsList);
        System.out.println("Houses List: " + xlsIngestion.housesList);
        System.out.println("Locations List: " + xlsIngestion.locationList);
        System.out.println("Units List: " + xlsIngestion.unitsList);
        System.out.println("Townhouses List: " + xlsIngestion.townhousesList);
        System.out.println("Development Sites List: " + xlsIngestion.developmentSitesList);
        System.out.println("Transactions List: " + xlsIngestion.transactionsList);
        
        // Combine the lists from both CSV and XLS into a unified list for each type
        List<Buyer> unifiedBuyersList = new ArrayList<>();
        unifiedBuyersList.addAll(csvIngestion.buyersList);
        unifiedBuyersList.addAll(xlsIngestion.buyersList);

        List<Agent> unifiedAgentsList = new ArrayList<>();
        unifiedAgentsList.addAll(csvIngestion.agentsList);
        unifiedAgentsList.addAll(xlsIngestion.agentsList);

        List<House> unifiedHousesList = new ArrayList<>();
        unifiedHousesList.addAll(csvIngestion.housesList);
        unifiedHousesList.addAll(xlsIngestion.housesList);

        List<Location> unifiedLocationList = new ArrayList<>();
        unifiedLocationList.addAll(csvIngestion.locationList);
        unifiedLocationList.addAll(xlsIngestion.locationList);

        List<Unit> unifiedUnitsList = new ArrayList<>();
        unifiedUnitsList.addAll(csvIngestion.unitsList);
        unifiedUnitsList.addAll(xlsIngestion.unitsList);

        List<Townhouse> unifiedTownhousesList = new ArrayList<>();
        unifiedTownhousesList.addAll(csvIngestion.townhousesList);
        unifiedTownhousesList.addAll(xlsIngestion.townhousesList);

        List<DevelopmentSite> unifiedDevelopmentSitesList = new ArrayList<>();
        unifiedDevelopmentSitesList.addAll(csvIngestion.developmentSitesList);
        unifiedDevelopmentSitesList.addAll(xlsIngestion.developmentSitesList);

        List<Transaction> unifiedTransactionsList = new ArrayList<>();
        unifiedTransactionsList.addAll(csvIngestion.transactionsList);
        unifiedTransactionsList.addAll(xlsIngestion.transactionsList);
        
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\units.csv", unifiedUnitsList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\agents.csv", unifiedAgentsList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\buyers.csv", unifiedBuyersList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\transactions.csv", unifiedTransactionsList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\houses.csv", unifiedHousesList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\dev.csv", unifiedDevelopmentSitesList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\townhouses.csv", unifiedTownhousesList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\locations.csv", unifiedLocationList);
        
}
}
