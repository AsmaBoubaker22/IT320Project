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
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\units.csv", unitsList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\agents.csv", agentsList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\buyers.csv", buyersList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\transactions.csv", transactionsList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\houses.csv", housesList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\dev.csv", developmentSitesList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\townhouses.csv", townhousesList);
        ExportData.saveToTXTFile("C:\\Users\\Asma\\Desktop\\locations.csv", locationList);
*/
        
        
        DatabaseExporter exporter = new DatabaseExporter("jdbc:mysql://localhost:3306/java_db", "root", "asma");
        exporter.exportData(buyersList, agentsList, housesList, unitsList, townhousesList, developmentSitesList, transactionsList);

        
        
        
        /*
        // 7) Load MySQL driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
            return;
        }

        // 8) Export data to SQL
        ExportData exporter = new ExportData();
        String jdbcUrl = "jdbc:mysql://localhost:3306/java_db";
        String username = "root";
        String password = "asma";
        
         System.out.println("Exporting CSV data to SQL...");
        exporter.ExportToSQL("Buyers", convertToListOfLists(csvIngestion.buyersList), jdbcUrl, username, password);
        exporter.ExportToSQL("Agents", convertToListOfLists(csvIngestion.agentsList), jdbcUrl, username, password);
        exporter.ExportToSQL("Houses", convertToListOfLists(csvIngestion.housesList), jdbcUrl, username, password);
        exporter.ExportToSQL("Units", convertToListOfLists(csvIngestion.unitsList), jdbcUrl, username, password);
        exporter.ExportToSQL("Townhouses", convertToListOfLists(csvIngestion.townhousesList), jdbcUrl, username, password);
        exporter.ExportToSQL("DevelopmentSite", convertToListOfLists(csvIngestion.developmentSitesList), jdbcUrl, username, password);
        exporter.ExportToSQL("Transactions", convertToListOfLists(csvIngestion.transactionsList), jdbcUrl, username, password);

        System.out.println("Exporting XLS data to SQL...");
        exporter.ExportToSQL("Buyers", convertToListOfLists(xlsIngestion.buyersList), jdbcUrl, username, password);
        exporter.ExportToSQL("Agents", convertToListOfLists(xlsIngestion.agentsList), jdbcUrl, username, password);
        exporter.ExportToSQL("Houses", convertToListOfLists(xlsIngestion.housesList), jdbcUrl, username, password);
        exporter.ExportToSQL("Units", convertToListOfLists(xlsIngestion.unitsList), jdbcUrl, username, password);
        exporter.ExportToSQL("Townhouses", convertToListOfLists(xlsIngestion.townhousesList), jdbcUrl, username, password);
        exporter.ExportToSQL("DevelopmentSite", convertToListOfLists(xlsIngestion.developmentSitesList), jdbcUrl, username, password);
        exporter.ExportToSQL("Transactions", convertToListOfLists(xlsIngestion.transactionsList), jdbcUrl, username, password);
    }
    public static List<List<Object>> convertToListOfLists(List<?> objects) {
        List<List<Object>> tableData = new ArrayList<>();
        if (objects == null || objects.isEmpty()) {
            return tableData;
        }

        // Reflect on the fields of the first object
        Field[] allFields = objects.get(0).getClass().getDeclaredFields();
        List<String> headers = new ArrayList<>();
        List<Field> usableFields = new ArrayList<>();

        for (Field f : allFields) {
            f.setAccessible(true);
            String fieldName = f.getName();

            // Skip the real auto-increment PK fields
            // (If you want to preserve them from CSV, comment out the relevant lines.)
            if (fieldName.equalsIgnoreCase("houseId") ||
                fieldName.equalsIgnoreCase("unitId") ||
                fieldName.equalsIgnoreCase("townhouseId") ||
                fieldName.equalsIgnoreCase("developmentSiteId") ||
                fieldName.equalsIgnoreCase("transactionId") ||
                fieldName.equalsIgnoreCase("locationId")) {
                continue;
            }

            // Also skip any "properties" or List<?> typed fields
            if ("properties".equals(fieldName) ||
                List.class.isAssignableFrom(f.getType())) {
                continue;
            }

            // Keep everything else, including "address" for House/Unit/Townhouse
            headers.add(fieldName);
            usableFields.add(f);
        }

        // First row: column names
        tableData.add(new ArrayList<>(headers));

        // Build the data rows
        for (Object obj : objects) {
            List<Object> row = new ArrayList<>();
            for (Field field : usableFields) {
                try {
                    Object value = field.get(obj);

                    if (value == null) {
                        row.add(null);
                        continue;
                    }

                    // Convert boolean -> "0"/"1"
                    if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                        boolean boolVal = (boolean) value;
                        row.add(boolVal ? "1" : "0");
                        continue;
                    }

                    // If field name is "date", parse from dd/MM/yyyy or MM/dd/yyyy => yyyy-MM-dd
                    if (field.getName().equalsIgnoreCase("date")) {
                        String dateStr = value.toString().trim();
                        String isoDate = parseToISODate(dateStr);
                        row.add(isoDate.isEmpty() ? null : isoDate);
                        continue;
                    }

                    // Otherwise, just convert to string
                    row.add(value.toString());

                } catch (IllegalAccessException e) {
                    row.add(null);
                }
            }
            tableData.add(row);
        }

        return tableData;
    }

    private static String parseToISODate(String raw) {
        if (raw == null || raw.isEmpty()) {
            return "";
        }
        String[] patterns = {"dd/MM/yyyy", "MM/dd/yyyy"};
        for (String pattern : patterns) {
            try {
                java.time.format.DateTimeFormatter inFmt =
                    java.time.format.DateTimeFormatter.ofPattern(pattern);
                java.time.LocalDate date = java.time.LocalDate.parse(raw, inFmt);
                return date.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (Exception e) {
                // keep trying next pattern
            }
        }
        return "";
    }
*/

        
}
}

