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
import java.util.*;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.apache.commons.math3.stat.inference.ChiSquareTest;


public class Correlation extends DataExploration {

    // Pearson correlation method (assumes numeric data)
    public static double pearsonCorrelation(String tableName, String column1, String column2) {
        List<Object> columnData1 = fetchColumn(tableName, column1);
        List<Object> columnData2 = fetchColumn(tableName, column2);

        // Convert to arrays of doubles for Pearson calculation
        double[] data1 = new double[columnData1.size()];
        double[] data2 = new double[columnData2.size()];

        // Iterate over the columns and cast to numbers
        for (int i = 0; i < columnData1.size(); i++) {
            data1[i] = convertToDouble(columnData1.get(i), column1);
            data2[i] = convertToDouble(columnData2.get(i), column2);
        }

        PearsonsCorrelation correlation = new PearsonsCorrelation();
        return correlation.correlation(data1, data2);
    }

    // Spearman correlation method (assumes numeric data)
    public static double spearmanCorrelation(String tableName, String column1, String column2) {
    List<Object> columnData1 = fetchColumn(tableName, column1);
    List<Object> columnData2 = fetchColumn(tableName, column2);

    // Convert to arrays of doubles for Spearman calculation
    double[] data1 = new double[columnData1.size()];
    double[] data2 = new double[columnData2.size()];

    // Iterate over the columns and cast to numbers
    for (int i = 0; i < columnData1.size(); i++) {
        data1[i] = convertToDouble2(columnData1.get(i), column1);
        data2[i] = convertToDouble2(columnData2.get(i), column2);
    }

    SpearmansCorrelation correlation = new SpearmansCorrelation();
    return correlation.correlation(data1, data2);
}

// Helper method to convert values to double
private static double convertToDouble2(Object value, String columnName) {
    if (value == null) {
        return Double.NaN; // Handle null values
    }

    // Check if the value is a Boolean (e.g., hasGarden, hasSwimmingPool)
    if (value instanceof Boolean) {
        return ((Boolean) value) ? 1.0 : 0.0; // Convert Boolean to 1.0 (true) or 0.0 (false)
    }

    // If it's a numeric type (Integer, Double, etc.), return it as a double
    if (value instanceof Number) {
        return ((Number) value).doubleValue();
    }

    // Otherwise, try to parse the value as a Double (for strings like "2.5")
    try {
        return Double.parseDouble(value.toString());
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Unsupported type for conversion: " + value.getClass());
    }
}

     // Chi-Square test method (assumes categorical data)
    public static double chiSquareTest(String tableName, String column1, String column2) {
        // Fetch columns as lists of Objects, which we will later cast to Strings
        List<Object> columnData1 = fetchColumn(tableName, column1);
        List<Object> columnData2 = fetchColumn(tableName, column2);

        // Cast the column data to List<String> since we are working with categorical data
        List<String> categoricalData1 = new ArrayList<>();
        List<String> categoricalData2 = new ArrayList<>();

        // Cast the objects to Strings
        for (Object obj : columnData1) {
            categoricalData1.add(obj.toString());
        }
        for (Object obj : columnData2) {
            categoricalData2.add(obj.toString());
        }

        // Check if the data is categorical (strings) and not numeric
        if (!isCategoricalColumn(categoricalData1) || !isCategoricalColumn(categoricalData2)) {
            throw new IllegalArgumentException("Chi-Square test requires categorical data.");
        }

        // Create a map to count the frequency of each pair of values (contingency table)
        Map<String, Integer> frequencyTable = new HashMap<>();

        // Build the frequency table
        for (int i = 0; i < categoricalData1.size(); i++) {
            String pair = categoricalData1.get(i) + "," + categoricalData2.get(i);
            frequencyTable.put(pair, frequencyTable.getOrDefault(pair, 0) + 1);
        }

        // Convert the frequency table into a 2D array (contingency table)
        Set<String> uniqueValues1 = new HashSet<>(categoricalData1);  // Explicitly use String
        Set<String> uniqueValues2 = new HashSet<>(categoricalData2);  // Explicitly use String

        // Create a 2D matrix to store the frequencies
        long[][] observed = new long[uniqueValues1.size()][uniqueValues2.size()];

        // Fill the contingency table with frequencies
        int row = 0;
        for (String value1 : uniqueValues1) {
            int col = 0;
            for (String value2 : uniqueValues2) {
                String key = value1 + "," + value2;
                observed[row][col] = frequencyTable.getOrDefault(key, 0);
                col++;
            }
            row++;
        }

        // Perform the Chi-Square test using the observed contingency table
        ChiSquareTest chiSquareTest = new ChiSquareTest();
        return chiSquareTest.chiSquareTest(observed);
    }

    // Helper method to check if a column contains categorical data (strings)
    private static boolean isCategoricalColumn(List<String> columnData) {
        for (String value : columnData) {
            if (!(value instanceof String)) {
                return false;  // Non-string values found, which is not suitable for categorical columns
            }
        }
        return true;  // All values are strings (categorical data)
    }
    
    // Helper method to convert data to double (to handle possible numeric or categorical types)
    private static double convertToDouble(Object value, String columnName) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else if (value instanceof String) {
            // Handle categorical variables like gender or method (e.g., 'M' -> 1, 'F' -> 0)
            if (isCategoricalColumn(columnName)) {
                // Map specific values to numbers, e.g., 'M' -> 1, 'F' -> 0, etc.
                String stringValue = (String) value;
                if (stringValue.equalsIgnoreCase("M")) {
                    return 1;  // Male
                } else if (stringValue.equalsIgnoreCase("F")) {
                    return 0;  // Female
                } else {
                    throw new IllegalArgumentException("Unsupported categorical value: " + value);
                }
            } else {
                // For non-categorical columns, parse as a number
                try {
                    return Double.parseDouble((String) value);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("The value cannot be converted to a number: " + value);
                }
            }
        }
        throw new IllegalArgumentException("Unsupported type for conversion: " + value.getClass());
    }

    // Helper method to check if a column is categorical (e.g., gender, method)
    private static boolean isCategoricalColumn(String columnName) {
        // Add column names that are categorical here (example: 'gender', 'method')
        return columnName.equalsIgnoreCase("gender") || columnName.equalsIgnoreCase("method");
    }

    // Helper method to decide which correlation test to use based on column types
    public static double calculateCorrelation(String tableName, String column1, String column2, String testType) {
        switch (testType.toLowerCase()) {
            case "pearson":
                return pearsonCorrelation(tableName, column1, column2);
            case "spearman":
                return spearmanCorrelation(tableName, column1, column2);
            case "chi-square":
                return chiSquareTest(tableName, column1, column2);
            default:
                throw new IllegalArgumentException("Invalid correlation test type");
        }
    }
}
