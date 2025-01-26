/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
import java.sql.*;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseExporter {

    private String jdbcUrl;
    private String username;
    private String password;

    public DatabaseExporter(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public void exportData(
        List<Buyer> buyersList,
        List<Agent> agentsList,
        List<House> housesList,
        List<Unit> unitsList,
        List<Townhouse> townhousesList,
        List<DevelopmentSite> developmentSitesList,
        List<Transaction> transactionsList
    ) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            conn.setAutoCommit(false);  // Use transactions for performance and consistency

            insertAgents(conn, agentsList);
            insertBuyers(conn, buyersList);
            insertProperties(conn, housesList, unitsList, townhousesList, developmentSitesList);
            insertHouses(conn, housesList);
            insertUnits(conn, unitsList);
            insertTownhouses(conn, townhousesList);
            insertDevelopmentSites(conn, developmentSitesList);
            insertTransactions(conn, transactionsList);

            conn.commit();  // Commit all changes after successful insertion
            System.out.println("Data inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }

    private void insertAgents(Connection conn, List<Agent> agentsList) throws SQLException {
        String sql = "INSERT IGNORE INTO agents (agentId, agentName) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (Agent agent : agentsList) {
                stmt.setInt(1, agent.getAgentId());
                stmt.setString(2, agent.getAgentName());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void insertBuyers(Connection conn, List<Buyer> buyersList) throws SQLException {
        String sql = "INSERT IGNORE INTO buyers (buyerId, buyerName, gender, age) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (Buyer buyer : buyersList) {
                stmt.setInt(1, buyer.getBuyerId());
                stmt.setString(2, buyer.getBuyerName());
                stmt.setString(3, String.valueOf(buyer.getGender()));
                stmt.setInt(4, buyer.getAge());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void insertProperties(Connection conn, List<House> houses, List<Unit> units, 
                              List<Townhouse> townhouses, List<DevelopmentSite> devSites) throws SQLException {
    String sql = "INSERT IGNORE INTO properties (propertyId) VALUES (?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        // Insert property IDs for houses
        for (House house : houses) {
            stmt.setInt(1, house.getpropertyId());
            stmt.addBatch();
        }

        // Insert property IDs for units
        for (Unit unit : units) {
            stmt.setInt(1, unit.getpropertyId());
            stmt.addBatch();
        }

        // Insert property IDs for townhouses
        for (Townhouse townhouse : townhouses) {
            stmt.setInt(1, townhouse.getpropertyId());
            stmt.addBatch();
        }

        // Insert property IDs for development sites
        for (DevelopmentSite devSite : devSites) {
            stmt.setInt(1, devSite.getpropertyId());
            stmt.addBatch();
        }

        // Execute the batch insert
        int[] result = stmt.executeBatch();
        System.out.println(result.length + " property records inserted.");

    } catch (SQLException e) {
        System.err.println("Error inserting properties: " + e.getMessage());
    }
}


    private void insertHouses(Connection conn, List<House> housesList) throws SQLException {
        String sql = "INSERT IGNORE INTO houses (propertyId, rooms, bathrooms, landsize, hasGarden, " +
                     "parkingSpot, buildingSize, yearBuilt, regionName, address, latitude, longitude, distance, postcode, councilArea, " +
                     "propertyCount, hasSwimmingPool, hasFence) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (House house : housesList) {
                stmt.setInt(1, house.getpropertyId());
                stmt.setInt(2, house.getRooms());
                stmt.setInt(3, house.getBathrooms());
                stmt.setInt(4, house.getLandsize());
                stmt.setBoolean(5, house.hasGarden());
                stmt.setInt(6, house.getParkingSpot());
                stmt.setInt(7, house.getBuildingSize());
                stmt.setInt(8, house.getYearBuilt());
                stmt.setString(9, house.getLocation().getRegionName());
                stmt.setString(10, house.getLocation().getAddress());
                stmt.setDouble(11, house.getLocation().getLatitude());
                stmt.setDouble(12, house.getLocation().getLongitude());
                stmt.setDouble(13, house.getLocation().getDistance());
                stmt.setInt(14, house.getLocation().getPostcode());
                stmt.setString(15, house.getLocation().getCouncilArea());
                stmt.setInt(16, house.getLocation().getPropertyCount());
                stmt.setBoolean(17, house.hasSwimmingPool());
                stmt.setBoolean(18, house.hasFence());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void insertTransactions(Connection conn, List<Transaction> transactionsList) throws SQLException {
    String sql = "INSERT IGNORE INTO transactions (transactionId, price, method, agentId, buyerId, propertyId, dateT) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yyyy");  // Input format
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");  // Desired output format
        
        for (Transaction transaction : transactionsList) {
            stmt.setInt(1, transaction.getTransactionId());
            stmt.setDouble(2, transaction.getPrice());
            stmt.setString(3, transaction.getMethod());
            stmt.setInt(4, transaction.getAgentId());
            stmt.setInt(5, transaction.getBuyerId());
            stmt.setInt(6, transaction.getPropertyId());

            // Validate and parse the date
            String dateStr = transaction.getDate();
            if (dateStr == null || dateStr.isEmpty()) {
                stmt.setNull(7, java.sql.Types.DATE); // If the date is null or empty, set it to NULL
            } else {
                try {
                    // Parse the date from MM/dd/yyyy format
                    Date utilDate = inputDateFormat.parse(dateStr);
                    // Convert to the desired yyyy-MM-dd format
                    String formattedDate = outputDateFormat.format(utilDate);
                    // Convert to SQL date
                    java.sql.Date sqlDate = java.sql.Date.valueOf(formattedDate);
                    stmt.setDate(7, sqlDate); // Set the date in the PreparedStatement
                } catch (Exception e) {
                    System.err.println("Invalid date format for transaction ID " + transaction.getTransactionId() + ": " + dateStr);
                    stmt.setNull(7, java.sql.Types.DATE); // If the date format is incorrect, set it to NULL
                }
            }

            stmt.addBatch(); // Add the current batch
        }
        stmt.executeBatch(); // Execute the batch insert
    }
}

private void insertTownhouses(Connection conn, List<Townhouse> townhousesList) throws SQLException {
    String sql = "INSERT IGNORE INTO townhouses (propertyId, landsize, rooms, bathrooms, parkingSpot, buildingSize, " +
                 "regionName, address, latitude, longitude, distance, postcode, councilArea, propertyCount, yearBuilt, " +
                 "numberOfSharedWalls, numberOfLevels) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        for (Townhouse townhouse : townhousesList) {
            stmt.setInt(1, townhouse.getpropertyId());
            stmt.setInt(2, townhouse.getLandsize());
            stmt.setInt(3, townhouse.getRooms());
            stmt.setInt(4, townhouse.getBathrooms());
            stmt.setInt(5, townhouse.getParkingSpot());
            stmt.setInt(6, townhouse.getBuildingSize());
            stmt.setString(7, townhouse.getLocation().getRegionName());
            stmt.setString(8, townhouse.getLocation().getAddress());
            stmt.setDouble(9, townhouse.getLocation().getLatitude());
            stmt.setDouble(10, townhouse.getLocation().getLongitude());
            stmt.setDouble(11, townhouse.getLocation().getDistance());
            stmt.setInt(12, townhouse.getLocation().getPostcode());
            stmt.setString(13, townhouse.getLocation().getCouncilArea());
            stmt.setInt(14, townhouse.getLocation().getPropertyCount());
            stmt.setInt(15, townhouse.getYearBuilt());
            stmt.setInt(16, townhouse.getNumberOfSharedWalls());
            stmt.setInt(17, townhouse.getNumberOfLevels());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }
}

private void insertDevelopmentSites(Connection conn, List<DevelopmentSite> developmentSitesList) throws SQLException {
    String sql = "INSERT IGNORE INTO developmentSite (propertyId, landsize, regionName, address, latitude, longitude, " +
                 "distance, postcode, councilArea, propertyCount, isLandCleared, underConstruction) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        for (DevelopmentSite devSite : developmentSitesList) {
            stmt.setInt(1, devSite.getpropertyId());
            stmt.setInt(2, devSite.getLandsize());
            stmt.setString(3, devSite.getLocation().getRegionName());
            stmt.setString(4, devSite.getLocation().getAddress());
            stmt.setDouble(5, devSite.getLocation().getLatitude());
            stmt.setDouble(6, devSite.getLocation().getLongitude());
            stmt.setDouble(7, devSite.getLocation().getDistance());
            stmt.setInt(8, devSite.getLocation().getPostcode());
            stmt.setString(9, devSite.getLocation().getCouncilArea());
            stmt.setInt(10, devSite.getLocation().getPropertyCount());
            stmt.setBoolean(11, devSite.isLandCleared());
            stmt.setBoolean(12, devSite.isUnderConstruction());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }
}

    private void insertUnits(Connection conn, List<Unit> unitsList) throws SQLException {
        String sql = "INSERT IGNORE INTO units (propertyId, rooms, bathrooms, parkingSpot, landsize, buildingSize, yearBuilt, " +
                     "floorLevel, regionName, address, latitude, longitude, distance, postcode, councilArea, propertyCount, " +
                     "hasBalcony, hasElevator) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (Unit unit : unitsList) {
                stmt.setInt(1, unit.getpropertyId());
                stmt.setInt(2, unit.getRooms());
                stmt.setInt(3, unit.getBathrooms());
                stmt.setInt(4, unit.getParkingSpot());
                stmt.setInt(5, unit.getLandsize());
                stmt.setInt(6, unit.getBuildingSize());
                stmt.setInt(7, unit.getYearBuilt());
                stmt.setInt(8, unit.getFloorLevel());
                stmt.setString(9, unit.getLocation().getRegionName());
                stmt.setString(10, unit.getLocation().getAddress());
                stmt.setDouble(11, unit.getLocation().getLatitude());
                stmt.setDouble(12, unit.getLocation().getLongitude());
                stmt.setDouble(13, unit.getLocation().getDistance());
                stmt.setInt(14, unit.getLocation().getPostcode());
                stmt.setString(15, unit.getLocation().getCouncilArea());
                stmt.setInt(16, unit.getLocation().getPropertyCount());
                stmt.setBoolean(17, unit.hasBalcony());
                stmt.setBoolean(18, unit.hasElevator());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }
}
