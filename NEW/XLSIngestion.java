/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.*;

public class XLSIngestion {
    // Declare final lists to hold all the objects
    final List<Buyer> buyersList = new ArrayList<>();
    final List<Agent> agentsList = new ArrayList<>();
    final List<House> housesList = new ArrayList<>();
    final List<Unit> unitsList = new ArrayList<>();
    final List<Townhouse> townhousesList = new ArrayList<>();
    final List<DevelopmentSite> developmentSitesList = new ArrayList<>();
    final List<Transaction> transactionsList = new ArrayList<>();
    final List<Location> locationList = new ArrayList<>();

    public List<List<String>> xlsImport(String filePath) {
    List<List<String>> data = new ArrayList<>();
    try (FileInputStream fis = new FileInputStream(filePath)) {
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);  // Get the first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            List<String> rowData = new ArrayList<>();
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                Cell cell = row.getCell(i);
                if (cell != null) {
                    rowData.add(cell.toString());  // Add cell value as string if cell is not null
                } else {
                    rowData.add("");  // Add an empty string if the cell is null
                }
            }
            data.add(rowData);  // Add the row data to the list
        }
        workbook.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return data;
    }


  public void xlsInject(List<List<String>> data) {
    for (List<String> row : data) {
        try {
            // Ensure the row has at least 37 elements, fill missing with empty strings
            while (row.size() < 37) {
                row.add("");
            }

            String address = row.get(1).trim();
            int rooms = parseIntOrDefault(row.get(2), 0);
            String type = row.get(3).trim();
            double price = parseDoubleOrDefault(row.get(4), 0.0);
            String method = row.get(5).trim();
            String agentName = row.get(6).trim();
            String date = row.get(7).trim();
            double distance = parseDoubleOrDefault(row.get(8), 0.0);
            int postcode = parseIntOrDefault(row.get(9), 0);
            int bathroom = parseIntOrDefault(row.get(10), 0);
            int parkingSpot = parseIntOrDefault(row.get(11), 0);
            int landsize = parseIntOrDefault(row.get(12), 0);
            int buildingSize = parseIntOrDefault(row.get(13), 0);
            int yearBuilt = parseIntOrDefault(row.get(14), 0);
            String councilArea = row.get(15).trim();
            double latitude = parseDoubleOrDefault(row.get(16), 0.0);
            double longitude = parseDoubleOrDefault(row.get(17), 0.0);
            String regionName = row.get(18).trim();
            int propertyCount = parseIntOrDefault(row.get(19), 0);
            boolean hasGarden = parseBooleanOrDefault(row.get(20), false);
            boolean hasSwimmingPool = parseBooleanOrDefault(row.get(21), false);
            boolean hasFence = parseBooleanOrDefault(row.get(22), false);
            int propertyId = parseIntOrDefault(row.get(23), 0);
            boolean isLandCleared = parseBooleanOrDefault(row.get(24), false);
            int numberOfSharedWalls = parseIntOrDefault(row.get(25), 0);
            int numberOfLevels = parseIntOrDefault(row.get(26), 0);
            boolean underConstruction = parseBooleanOrDefault(row.get(27), false);
            int agentId = parseIntOrDefault(row.get(28), 0);
            String buyerName = row.get(29).trim();
            int buyerId = parseIntOrDefault(row.get(30), 0);
            char gender = row.get(31).isEmpty() ? 'U' : row.get(31).trim().charAt(0);
            int age = parseIntOrDefault(row.get(32), 0);
            int floorLevel = parseIntOrDefault(row.get(33), 0);
            boolean hasBalcony = parseBooleanOrDefault(row.get(34), false);
            int transactionId = parseIntOrDefault(row.get(35), 0);
            boolean hasElevator = parseBooleanOrDefault(row.get(36), false);

            // Create the Location object
            Location location = new Location(regionName, address, latitude, longitude, distance, postcode, councilArea, propertyCount);
            locationList.add(location);

            if (type.equals("h")) {
                House house = new House(propertyId, landsize, location, rooms, bathroom, parkingSpot, buildingSize, yearBuilt, hasGarden, hasSwimmingPool, hasFence);
                housesList.add(house);
            } else if (type.equals("u")) {
                Unit unit = new Unit(propertyId, landsize, location, rooms, bathroom, parkingSpot, buildingSize, yearBuilt, floorLevel, hasBalcony, hasElevator);
                unitsList.add(unit);
            } else if (type.equals("t")) {
                Townhouse townhouse = new Townhouse(propertyId, landsize, location, rooms, bathroom, parkingSpot, buildingSize, yearBuilt, numberOfSharedWalls, numberOfLevels);
                townhousesList.add(townhouse);
            } else if (type.equals("dev")) {
                DevelopmentSite developmentSite = new DevelopmentSite(propertyId, landsize, location, isLandCleared, underConstruction);
                developmentSitesList.add(developmentSite);
            } else {
                System.out.println("Unknown property type: " + type);
            }

            // Create transaction object
            Transaction transaction = new Transaction(transactionId, price, method, agentId, buyerId, propertyId, date);
            transactionsList.add(transaction);

            // Handle buyers
            boolean buyerExists = buyersList.stream().anyMatch(buyer -> buyer.getBuyerId() == buyerId);
            if (!buyerExists) {
                Buyer buyer = new Buyer(buyerId, buyerName, gender, age);
                buyersList.add(buyer);
            }

            // Handle agents
            boolean agentExists = agentsList.stream().anyMatch(agent -> agent.getAgentId() == agentId);
            if (!agentExists) {
                Agent agent = new Agent(agentId, agentName);
                agentsList.add(agent);
            }

        } catch (Exception e) {
            System.out.println("Error processing row: " + row);
            e.printStackTrace();
        }
    }
}


    // Helper methods to safely parse values

    private int parseIntOrDefault(String value, int defaultValue) {
    try {
        return value == null || value.trim().isEmpty() ? defaultValue : (int) Double.parseDouble(value.trim());
    } catch (NumberFormatException e) {
        return defaultValue;
    }
}

private double parseDoubleOrDefault(String value, double defaultValue) {
    try {
        return value == null || value.trim().isEmpty() ? defaultValue : Double.parseDouble(value.trim());
    } catch (NumberFormatException e) {
        return defaultValue;
    }
}

private boolean parseBooleanOrDefault(String value, boolean defaultValue) {
    return value == null || value.trim().isEmpty() ? defaultValue : Boolean.parseBoolean(value.trim());
}

}