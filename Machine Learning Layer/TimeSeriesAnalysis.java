/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
import java.util.ArrayList;
import java.util.List;

public class TimeSeriesAnalysis implements PredictiveModeling {
    private List<Double> movingAverages;
    private int windowSize;

    @Override
    public void linearRegression(List<List<Object>> data, List<Integer> featureColumns, int targetColumnIndex) {
        // Empty for now
    }

    // Implementing the TSA method
    @Override
    public void tsaAnalysis(List<List<Object>> data, int targetColumnIndex, int windowSize) {
        System.out.println("Performing Time Series Analysis...");

        this.windowSize = windowSize;
        List<Double> targetValues = new ArrayList<>();

        // Extract target values
        for (List<Object> dataPoint : data) {
            Double target = convertToDouble(dataPoint.get(targetColumnIndex));
            targetValues.add(target);
        }

        movingAverages = calculateMovingAverages(targetValues, windowSize);

        System.out.println("Time Series Analysis Completed. Moving Averages calculated.");
    }

    // Implementing the fit method
    @Override
    public void fit(List<?> data) {
        System.out.println("Fitting the Time Series model with the provided data...");
        List<List<Object>> castedData = (List<List<Object>>) data;
        tsaAnalysis(castedData, 0, 3);  // Example: using column 0 with a window size of 3
    }

    // Implementing the predict method
    @Override
    public List<?> predict(List<?> data) {
        System.out.println("Predicting future values based on Time Series Analysis...");
        List<Double> predictions = new ArrayList<>();
        if (movingAverages != null && !movingAverages.isEmpty()) {
            predictions.add(movingAverages.get(movingAverages.size() - 1)); // Predict next value using the last moving average
        }
        return predictions;
    }

    // Implementing the evaluate method
    @Override
    public double evaluate(List<?> testData) {
        System.out.println("Evaluating the Time Series model...");
        return 0.0;  // Placeholder for evaluation logic
    }

    // Helper method to calculate moving averages
    private List<Double> calculateMovingAverages(List<Double> data, int windowSize) {
        List<Double> movingAverages = new ArrayList<>();
        for (int i = 0; i <= data.size() - windowSize; i++) {
            double sum = 0.0;
            for (int j = 0; j < windowSize; j++) {
                sum += data.get(i + j);
            }
            movingAverages.add(sum / windowSize);
        }
        return movingAverages;
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

    // Getter for moving averages
    public List<Double> getMovingAverages() {
        return movingAverages;
    }
}
