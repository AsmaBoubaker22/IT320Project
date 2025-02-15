/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.List;

public class ExportToTXTFile {

    public static <T> void saveToTXTFile(String filePath, List<T> dataList) {
    try (FileWriter writer = new FileWriter(filePath)) {
        if (dataList == null || dataList.isEmpty()) {
            System.out.println("Data list is empty or null.");
            return;
        }

        // Write the header row dynamically, including nested Location fields separately
        T firstItem = dataList.get(0);
        Field[] fields = getAllFields(firstItem.getClass());

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().equals(Location.class)) {
                // Add location-specific headers
                writer.append("regionName,address,latitude,longitude,distance,postcode,councilArea,propertyCount");
            } else {
                writer.append(fields[i].getName());
            }

            if (i < fields.length - 1) {
                writer.append(",");
            }
        }
        writer.append("\n");

        // Write each object's data dynamically
        for (T item : dataList) {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true); // Allow access to private fields
                try {
                    Object fieldValue = fields[i].get(item);

                    if (fieldValue instanceof Location) {
                        Location location = (Location) fieldValue;
                        writer.append(location.getRegionName()).append(",");
                        writer.append(location.getAddress()).append(",");
                        writer.append(String.valueOf(location.getLatitude())).append(",");
                        writer.append(String.valueOf(location.getLongitude())).append(",");
                        writer.append(String.valueOf(location.getDistance())).append(",");
                        writer.append(String.valueOf(location.getPostcode())).append(",");
                        writer.append(location.getCouncilArea()).append(",");
                        writer.append(String.valueOf(location.getPropertyCount()));
                    } else {
                        writer.append(fieldValue != null ? fieldValue.toString() : "null");
                    }

                    if (i < fields.length - 1) {
                        writer.append(",");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            writer.append("\n");  // New line after each row
        }

        System.out.println("Data has been successfully saved to " + filePath);
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
        e.printStackTrace();
    }
}


    // Helper method to get all fields of a class, including fields from parent classes
    private static Field[] getAllFields(Class<?> clazz) {
        // Start with the fields of the current class
        List<Field> allFields = new ArrayList<>();
        while (clazz != null) {
            allFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass(); // Move to the parent class
        }
        return allFields.toArray(new Field[0]); // Convert the list to an array
    } 

}