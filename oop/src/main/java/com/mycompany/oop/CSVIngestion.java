package com.mycompany.oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVIngestion {

    public <T> List<T> csvImport(String filePath, Class<T> clazz) {
        List<T> objects = new ArrayList<>();
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                T object = mapToEntity(values, clazz);
                if (object != null) {
                    objects.add(object);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objects;
    }

    private <T> T mapToEntity(String[] values, Class<T> clazz) {
        try {
            if (clazz == Agent.class) {
                return clazz.cast(new Agent(values[0], values[1]));
            } else if (clazz == Buyer.class) {
                return clazz.cast(new Buyer(values[0], values[1], Integer.parseInt(values[2])));
            }
            // Add more mappings as needed for other classes
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
