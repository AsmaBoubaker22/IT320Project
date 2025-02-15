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
import java.util.ArrayList;
import java.util.List;

public abstract class DataExploration {

    // JDBC parameters 
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/java_db"; 
    private static final String USERNAME = "root";
    private static final String PASSWORD = "asma";

    public static List<Object> fetchColumn(String tableName, String columnName) {
        List<Object> columnData = new ArrayList<>();
        
        String sql = "SELECT " + columnName + " FROM " + tableName;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Iterate over the result set and add each value from the column to the list
            while (rs.next()) {
                columnData.add(rs.getObject(columnName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving column data: " + e.getMessage());
        }

        return columnData;
    }
    
    public static boolean doesColumnExistInTable(String tableName, String columnName) {
    String sql = "SELECT * FROM " + tableName + " LIMIT 1";  // Query to check table schema

    try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Check column names against the provided column name
        for (int i = 1; i <= columnCount; i++) {
            if (metaData.getColumnName(i).equalsIgnoreCase(columnName)) {
                return true;
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
    }
}
