/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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


public class CSVIngestion { 
        // Declare final lists to hold all the objects
        final List<Buyer> buyersList = new ArrayList<>();
        final List<Agent> agentsList = new ArrayList<>();
        final List<House> housesList = new ArrayList<>();
        final List<Unit> unitsList = new ArrayList<>();
        final List<Townhouse> townhousesList = new ArrayList<>();
        final List<DevelopmentSite> developmentSitesList = new ArrayList<>();
        final List<Transaction> transactionsList = new ArrayList<>();
        final List<Location> locationList = new ArrayList<>();

     public List<List<String>> csvImport(String filePath) {
        List<List<String>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine(); // Read header 
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<String> row = new ArrayList<>(Arrays.asList(values)); 
                data.add(row); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    
    public void csvInject(List<List<String>> data) {
        for (List<String> row : data) {
            try {
                // Skip incomplete rows
                if (row.size() < 37) {
                    System.out.println("Skipping incomplete row: " + row);
                    continue;
                }

                // Extract and validate fields
                String address = row.get(1);
                int rooms = parseIntWithDefault(row.get(2), 0);
                String type = row.get(3);
                double price = parseDoubleWithDefault(row.get(4), 0.0);
                String method = row.get(5);
                String agentName = row.get(6);
                String date = row.get(7);
                double distance = parseDoubleWithDefault(row.get(8), 0.0);
                int postcode = parseIntWithDefault(row.get(9), 0);
                int bathroom = parseIntWithDefault(row.get(10), 0);
                int parkingSpot = parseIntWithDefault(row.get(11), 0);
                int landsize = parseIntWithDefault(row.get(12), 0);
                int buildingSize = parseIntWithDefault(row.get(13), 0);
                int yearBuilt = parseIntWithDefault(row.get(14), 0);
                String councilArea = row.get(15);
                double latitude = parseDoubleWithDefault(row.get(16), 0.0);
                double longitude = parseDoubleWithDefault(row.get(17), 0.0);
                String regionName = row.get(18);
                int propertyCount = parseIntWithDefault(row.get(19), 0);
                boolean hasGarden = parseBooleanWithDefault(row.get(20), false);
                boolean hasSwimmingPool = parseBooleanWithDefault(row.get(21), false);
                boolean hasFence = parseBooleanWithDefault(row.get(22), false);
                int propertyId = parseIntWithDefault(row.get(23), 0);
                boolean isLandCleared = parseBooleanWithDefault(row.get(24), false);
                int numberOfSharedWalls = parseIntWithDefault(row.get(25), 0);
                int numberOfLevels = parseIntWithDefault(row.get(26), 0);
                boolean underConstruction = parseBooleanWithDefault(row.get(27), false);
                int agentId = parseIntWithDefault(row.get(28), 0);
                String buyerName = row.get(29);
                int buyerId = parseIntWithDefault(row.get(30), 0);
                char gender = row.get(31).isEmpty() ? 'U' : row.get(31).charAt(0);
                int age = parseIntWithDefault(row.get(32), 0);
                int floorLevel = parseIntWithDefault(row.get(33), 0);
                boolean hasBalcony = parseBooleanWithDefault(row.get(34), false);
                int transactionId = parseIntWithDefault(row.get(35), 0);
                boolean hasElevator = parseBooleanWithDefault(row.get(36), false);

                // Create the Location object
                Location location = new Location(regionName, address, latitude, longitude, 
                                                 distance, postcode, councilArea, propertyCount);
                locationList.add(location);

                // Create property objects based on type
                switch (type) {
                    case "h":
                        House house = new House(propertyId, landsize, location, rooms, bathroom, parkingSpot, buildingSize, yearBuilt, hasGarden, hasSwimmingPool, hasFence);
                        housesList.add(house);
                        break;
                    case "u":
                        Unit unit = new Unit(propertyId, landsize, location, rooms, bathroom, parkingSpot, buildingSize, yearBuilt, floorLevel, hasBalcony, hasElevator);
                        unitsList.add(unit);
                        break;
                    case "t":
                        Townhouse townhouse = new Townhouse(propertyId, landsize, location, rooms, bathroom, parkingSpot, buildingSize, yearBuilt, numberOfSharedWalls, numberOfLevels);
                        townhousesList.add(townhouse);
                        break;
                    case "dev":
                        DevelopmentSite developmentSite = new DevelopmentSite(propertyId, landsize, location, isLandCleared, underConstruction);
                        developmentSitesList.add(developmentSite);
                        break;
                    default:
                        System.out.println("Unknown property type: " + type);
                }

                // Create and add Transaction
                Transaction transaction = new Transaction(transactionId, price, method, agentId, buyerId, propertyId, date);
                transactionsList.add(transaction);

                // Add Buyer if not exists
                addBuyerIfNotExists(buyerId, buyerName, gender, age);

                // Add Agent if not exists
                addAgentIfNotExists(agentId, agentName);

            } catch (Exception e) {
                System.out.println("Error processing row: " + row);
                e.printStackTrace();
            }
        }
    }

    private int parseIntWithDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private double parseDoubleWithDefault(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private boolean parseBooleanWithDefault(String value, boolean defaultValue) {
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // Helper methods to add Buyer and Agent if not exists
    private void addBuyerIfNotExists(int buyerId, String buyerName, char gender, int age) {
        boolean buyerExists = buyersList.stream().anyMatch(buyer -> buyer.getBuyerId() == buyerId);
        if (!buyerExists) {
            buyersList.add(new Buyer(buyerId, buyerName, gender, age));
        }
    }

    private void addAgentIfNotExists(int agentId, String agentName) {
        boolean agentExists = agentsList.stream().anyMatch(agent -> agent.getAgentId() == agentId);
        if (!agentExists) {
            agentsList.add(new Agent(agentId, agentName));
        }
    }
}