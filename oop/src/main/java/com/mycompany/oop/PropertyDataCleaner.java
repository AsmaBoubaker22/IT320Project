package com.mycompany.oop;

import java.util.*;

public class PropertyDataCleaner implements DataCleaning {

    @Override
    public void removeMissingValues(List<List<Object>> data) {
        data.removeIf(row -> row.contains(null) || row.contains(""));
    }

    @Override
    public void removeDuplicates(List<List<Object>> data) {
        Set<List<Object>> uniqueRows = new HashSet<>(data);
        data.clear();
        data.addAll(uniqueRows);
    }

    @Override
    public void removeOutliers(List<List<Object>> data) {
        // Example logic for removing outliers
        data.removeIf(row -> {
            if (row.size() > 2) {
                try {
                    double value = Double.parseDouble(row.get(2).toString());
                    return value < 10 || value > 10000;
                } catch (NumberFormatException e) {
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    public void fixYear(List<List<Object>> data) {
        for (List<Object> row : data) {
            if (row.size() > 3) {
                try {
                    int year = Integer.parseInt(row.get(3).toString());
                    if (year < 1900 || year > 2100) {
                        row.set(3, 2000);
                    }
                } catch (NumberFormatException e) {
                    row.set(3, 2000);
                }
            }
        }
    }
}
