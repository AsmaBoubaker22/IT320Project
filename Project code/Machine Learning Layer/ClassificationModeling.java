/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
import java.util.List;
import java.util.ArrayList;

public interface ClassificationModeling extends MachineLearning {
    double evaluate(List<?> testData);
    void knnClassifier(List<Object> data, String targetAttribute);
    
    
    // Static method for creating dataset from columns
    static List<double[]> getDataset(List<Object> col1, List<Object> col2) {
        if (col1 == null || col2 == null || col1.size() != col2.size()) {
            throw new IllegalArgumentException("Columns are null or have unequal sizes.");
        }

        List<double[]> dataset = new ArrayList<>();
        for (int i = 0; i < col1.size(); i++) {
            try {
                double value1 = Double.parseDouble(col1.get(i).toString());
                double value2 = Double.parseDouble(col2.get(i).toString());
                dataset.add(new double[]{value1, value2});
            } catch (NumberFormatException e) {
                System.err.println("Invalid data at index " + i + ", skipping.");
            }
        }

        if (dataset.isEmpty()) {
            throw new IllegalArgumentException("Dataset is empty after filtering.");
        }

        return dataset;
    }
    
}
