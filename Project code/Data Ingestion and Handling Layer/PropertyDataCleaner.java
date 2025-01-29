/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PropertyDataCleaner implements DataCleaning {
        @Override
        public void removeMissingValues(List<List<String>> data) {
            if (data.isEmpty()) return;

            // Get the column indices
            int buildingAreaIndex = getColumnIndex(data, "BuildingArea");
            int yearBuiltIndex = getColumnIndex(data, "YearBuilt");

            // Remove rows based on conditions
            data.removeIf(row -> {
                boolean hasEmptyCell = false;
                boolean isBuildingAreaEmpty = buildingAreaIndex != -1 && (buildingAreaIndex >= row.size() || row.get(buildingAreaIndex).trim().isEmpty());
                boolean isYearBuiltEmpty = yearBuiltIndex != -1 && (yearBuiltIndex >= row.size() || row.get(yearBuiltIndex).trim().isEmpty());

                // Rule 1: Remove if any other column has an empty cell
                for (int i = 0; i < row.size(); i++) {
                    if (row.get(i).trim().isEmpty() && i != buildingAreaIndex && i != yearBuiltIndex) {
                        return true; 
                    }
                }

                // Rule 2: Remove if only one of "BuildingArea" or "YearBuilt" is empty
                if ((isBuildingAreaEmpty && !isYearBuiltEmpty) || (!isBuildingAreaEmpty && isYearBuiltEmpty)) {
                    return true;
                }

                // Rule 3: Keep row if both "BuildingArea" and "YearBuilt" are empty
                return false; // Row is valid
            });
        }

        // Helper method to get the column index by name
        private int getColumnIndex(List<List<String>> data, String columnName) {
            if (data.isEmpty()) return -1;
            return data.get(0).indexOf(columnName);
        }

        @Override
        public void removeDuplicates(List<List<String>> data) {
            if (data.isEmpty()) return;

            // Using a Set to store rows and automatically remove duplicates
            Set<List<String>> uniqueRows = new HashSet<>();

            // We need to go through the list backwards to maintain correct indexing after removal
            data.removeIf(row -> !uniqueRows.add(row));
        }

        @Override
  public void removeOutliers(List<List<String>> data) {
    if (data == null || data.isEmpty()) return;

    // List of column indices to exclude where "0" is allowed
    List<Integer> excludeIndices = List.of(11, 25, 26, 33); 

    // Filter rows by removing those with "0" in non-excluded columns
    data.removeIf(row -> {
        for (int i = 0; i < row.size(); i++) {
            if (!excludeIndices.contains(i) && row.get(i).equals("0")) {
                return true; 
            }
        }
        return false; 
    });
}

        @Override
     public void fixYear(List<List<String>> data) {
    if (data == null || data.isEmpty()) return;

    // Define the target date format (dd/MM/yyyy)
    SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Define the alternative format for French month names 
    SimpleDateFormat frenchFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    // Iterate over the rows to fix the date in the 7th column (index 7)
    for (int i = 0; i < data.size(); i++) {
        String dateString = data.get(i).get(7); 

        if (dateString != null && !dateString.isEmpty()) {
            try {
                // Try to parse the date as already being in the correct format
                targetFormat.parse(dateString); 
            } catch (ParseException e) {
                
                try {
                    Date parsedDate = frenchFormat.parse(dateString); 

                    // Reformat to the target format (dd/MM/yyyy)
                    String formattedDate = targetFormat.format(parsedDate);
                    data.get(i).set(7, formattedDate);
                } catch (ParseException ex) {
                    System.out.println("Error parsing date for row " + i + ": " + dateString);
                }
            }
        }
    }
}
              
 }