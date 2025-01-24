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
            String headerLine = br.readLine(); // Read header (skip this if you don't need to store headers)
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Split the row by commas
                List<String> row = new ArrayList<>(Arrays.asList(values)); // Convert array to list
                data.add(row); // Add the row to data
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void csvInject(List<List<String>> data) {
        for (List<String> row : data) {
            try {
                // Extract values from the row based on expected column indexes
                String address = (String) row.get(1);
                int rooms = Integer.parseInt((String) row.get(2));
                String type = (String) row.get(3);
                double price = Double.parseDouble((String) row.get(4));
                String method = (String) row.get(5);
                String agentName = (String) row.get(6);
                String date = (String) row.get(7);
                double distance = Double.parseDouble((String) row.get(8));
                int postcode = Integer.parseInt((String) row.get(9));
                int bathroom = Integer.parseInt((String) row.get(10));
                int parkingSpot = Integer.parseInt((String) row.get(11));
                int landsize = Integer.parseInt((String) row.get(12));
                int buildingSize = Integer.parseInt((String) row.get(13));
                int yearBuilt = row.get(14).equals("") ? 0 : Integer.parseInt((String) row.get(14));
                String councilArea = (String) row.get(15);
                double latitude = Double.parseDouble((String) row.get(16));
                double longitude = Double.parseDouble((String) row.get(17));
                String regionName = (String) row.get(18);
                int propertyCount = Integer.parseInt((String) row.get(19));
                boolean hasGarden = Boolean.parseBoolean((String) row.get(20));
                boolean hasSwimmingPool = Boolean.parseBoolean((String) row.get(21));
                boolean hasFence = Boolean.parseBoolean((String) row.get(22));
                int propertyId = Integer.parseInt((String) row.get(23));
                boolean isLandCleared = Boolean.parseBoolean((String) row.get(24));
                int numberOfSharedWalls = Integer.parseInt((String) row.get(25));
                int numberOfLevels = Integer.parseInt((String) row.get(26));
                boolean underConstruction = Boolean.parseBoolean((String) row.get(27));
                int agentId = Integer.parseInt((String) row.get(28));
                String buyerName = (String) row.get(29);
                int buyerId = Integer.parseInt((String) row.get(30));
                char gender = ((String) row.get(31)).charAt(0);
                int age = Integer.parseInt((String) row.get(32));
                int floorLevel = Integer.parseInt((String) row.get(33));
                boolean hasBalcony = Boolean.parseBoolean((String) row.get(34));
                int transactionId = Integer.parseInt((String) row.get(35));
                boolean hasElevator = Boolean.parseBoolean((String) row.get(36));
                
                // Create the Location object
                Location location = new Location(regionName, address, latitude, longitude, 
                                                 distance, postcode, councilArea, propertyCount);
                locationList.add(location);
                
                if (type.equals("h")) {
                    // Create the House object
                    House house = new House(propertyId, landsize, location, rooms, bathroom, parkingSpot, buildingSize, yearBuilt, hasGarden, hasSwimmingPool, hasFence);
                    housesList.add(house);
                } else if (type.equals("u")) {
                    // Create the Unit object
                    Unit unit = new Unit(propertyId, landsize, location, rooms, bathroom, parkingSpot, buildingSize, yearBuilt, floorLevel, hasBalcony, hasElevator);
                    unitsList.add(unit); 
                } else if (type.equals("t")) {
                    // Create the Townhouse object
                    Townhouse townhouse = new Townhouse(propertyId, landsize, location, rooms, bathroom, parkingSpot, buildingSize, yearBuilt, numberOfSharedWalls, numberOfLevels);
                    townhousesList.add(townhouse);
                } else if (type.equals("dev")) {
                    // Create the DevelopmentSite object
                    DevelopmentSite developmentSite = new DevelopmentSite(propertyId, landsize, location, isLandCleared, underConstruction);
                    developmentSitesList.add(developmentSite);
                } else {
                    System.out.println("Unknown property type: " + type);
                }

                // Create transaction object
                Transaction transaction = new Transaction(transactionId, price, method, agentId, buyerId, propertyId, date);
                transactionsList.add(transaction);
                
                // Check if the Buyer exists
                boolean buyerExists = false;
                for (Buyer buyer : buyersList) {
                    if (buyer.getBuyerId() == buyerId) {
                        buyerExists = true;
                        break;
                    }
                }

                // If the Buyer doesn't exist, create and add to the list
                if (!buyerExists) {
                    Buyer buyer = new Buyer(buyerId, buyerName, gender, age);
                    buyersList.add(buyer);
                }
                
                // Check if the Agent exists
                boolean agentExists = false;
                for (Agent agent : agentsList) {
                    if (agent.getAgentId() == agentId) {
                        agentExists = true;
                        break;
                    }
                }

                // If the Agent doesn't exist, create and add to the list
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
}
