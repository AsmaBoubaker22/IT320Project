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
import java.util.List;

public class LinearRegression implements PredictiveModeling {
    private List<Double> coefficients;

    @Override
    public void tsaAnalysis(List<List<Object>> data, int targetColumnIndex, int windowSize) {
    }
    
    // Implementing the linear regression method
    @Override
    public void linearRegression(List<List<Object>> data, List<Integer> featureColumns, int targetColumnIndex) {
        System.out.println("Performing linear regression...");

        // Prepare feature and target values
        List<Double> featureValues = new ArrayList<>();
        List<Double> targetValues = new ArrayList<>();
        
        // Extract features and target
        for (List<Object> dataPoint : data) {
            // Collecting the features dynamically
            double featureSum = 0.0;
            for (int featureColumnIndex : featureColumns) {
                featureSum += convertToDouble(dataPoint.get(featureColumnIndex));
            }
            featureValues.add(featureSum);
            
            // Target value
            Double target = convertToDouble(dataPoint.get(targetColumnIndex));
            targetValues.add(target);
        }

        // Calculate the mean of X and Y
        double meanX = featureValues.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double meanY = targetValues.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        // Calculate slope (b1) and intercept (b0) for linear regression
        double numerator = 0.0;
        double denominator = 0.0;
        
        for (int i = 0; i < featureValues.size(); i++) {
            numerator += (featureValues.get(i) - meanX) * (targetValues.get(i) - meanY);
            denominator += Math.pow(featureValues.get(i) - meanX, 2);
        }

        double slope = numerator / denominator;
        double intercept = meanY - slope * meanX;

        coefficients = new ArrayList<>();
        coefficients.add(slope);
        coefficients.add(intercept);

        System.out.println("Linear Regression Model Coefficients:");
        System.out.println("Slope: " + slope);
        System.out.println("Intercept: " + intercept);
    }

    // Implementing the fit method from MachineLearning interface
    @Override
    public void fit(List<?> data) {
        System.out.println("Fitting the model with the provided data...");
        
        // Casting data to List<List<Object>> to process
        List<List<Object>> castedData = (List<List<Object>>) data;
        
        // Example: dynamically choose feature columns and the target column index
        List<Integer> featureColumns = Arrays.asList(0, 1, 2);  // Example: rooms, bathrooms, landsize
        int targetColumnIndex = 3;  // Example: price column index
        
        linearRegression(castedData, featureColumns, targetColumnIndex);
    }

    // Implementing the predict method from MachineLearning interface
    @Override
    public List<?> predict(List<?> data) {
        System.out.println("Predicting using the model...");
        
        List<Double> predictions = new ArrayList<>();
        
        for (Object row : data) {
            List<Object> featureRow = (List<Object>) row;  // Assuming row is a list of features
            double featureSum = 0.0;
            for (int i = 0; i < featureRow.size(); i++) {
                featureSum += convertToDouble(featureRow.get(i));
            }
            double prediction = coefficients.get(0) * featureSum + coefficients.get(1);  // Prediction formula
            predictions.add(prediction);
        }

        return predictions;
    }

    // Implementing the evaluate method from MachineLearning interface
    @Override
    public double evaluate(List<?> testData) {
        System.out.println("Evaluating the model...");

        // Assuming testData is a list of actual target values
        double totalError = 0.0;
        int count = 0;

        for (Object value : testData) {
            Double targetValue = convertToDouble(value);
            Double predictedValue = 0.0; // We would have actual prediction logic here
            totalError += Math.pow(targetValue - predictedValue, 2); // Sum of squared errors
            count++;
        }

        double mse = totalError / count;
        System.out.println("Model evaluation result (MSE): " + mse);

        return mse;
    }

    // Helper method to convert to Double if the value is either Integer or Double
    private Double convertToDouble(Object value) {
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        } else if (value instanceof Double) {
            return (Double) value;
        } else {
            throw new IllegalArgumentException("Unsupported data type: " + value.getClass());
        }
    }
}
