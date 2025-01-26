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

    // JDBC parameters (you can pass these through the constructor or set them elsewhere)
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/java_db";  // Example: "jdbc:mysql://localhost:3306/your_database"
    private static final String USERNAME = "root";
    private static final String PASSWORD = "asma";

    public static List<Object> fetchColumn(String tableName, String columnName) {
        List<Object> columnData = new ArrayList<>();
        
        // Define the SQL query to retrieve the column data
        String sql = "SELECT " + columnName + " FROM " + tableName;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Iterate over the result set and add each value from the column to the list
            while (rs.next()) {
                // Depending on the column's data type, retrieve it appropriately
                // For simplicity, assuming the column can hold any type (using Object)
                columnData.add(rs.getObject(columnName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving column data: " + e.getMessage());
        }

        return columnData;
    }
}
