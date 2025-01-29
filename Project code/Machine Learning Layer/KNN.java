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
import java.util.stream.Collectors;

public class KNN implements ClassificationModeling {
    private List<double[]> trainingData;
    private List<String> labels;       
    private int k; 

    
    public KNN() {
        this.trainingData = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.k = 3; 
    }

    // Setter for k, so it can be dynamically changed
    public void setK(int k) {
        this.k = k;
    }

    @Override
    public void fit(List<?> data) {
        if (data.isEmpty() || !(data.get(0) instanceof Map)) {
            throw new IllegalArgumentException("Invalid training data format. Expected List<Map<String, Object>>.");
        }

        trainingData.clear();
        labels.clear();

        for (Object obj : data) {
            Map<String, Object> row = (Map<String, Object>) obj;
            double[] features = (double[]) row.get("features");
            String label = (String) row.get("label");

            trainingData.add(features);
            labels.add(label);
        }
    }

    @Override
    public List<?> predict(List<?> data) {
        List<String> predictions = new ArrayList<>();

        for (Object obj : data) {
            double[] testData = (double[]) obj;
            predictions.add(knnClassifier(trainingData, labels, testData, k)); 
        }

        return predictions;
    }

    @Override
    public double evaluate(List<?> testData) {
        if (testData.isEmpty() || !(testData.get(0) instanceof Map)) {
            throw new IllegalArgumentException("Invalid test data format. Expected List<Map<String, Object>>.");
        }

        int correct = 0;

        for (Object obj : testData) {
            Map<String, Object> row = (Map<String, Object>) obj;
            double[] features = (double[]) row.get("features");
            String trueLabel = (String) row.get("label");
            String predictedLabel = knnClassifier(trainingData, labels, features, k);

            if (trueLabel.equals(predictedLabel)) {
                correct++;
            }
        }

        return (double) correct / testData.size();
    }

    @Override
    public void knnClassifier(List<Object> data, String targetAttribute) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    // KNN classification logic
    private String knnClassifier(List<double[]> trainingData, List<String> labels, double[] testData, int k) {
        List<Double> distances = new ArrayList<>();
        for (double[] trainPoint : trainingData) {
            distances.add(euclideanDistance(trainPoint, testData));
        }

        List<Integer> nearestIndices = distances.stream()
                .sorted()
                .limit(k)
                .map(distances::indexOf)
                .collect(Collectors.toList());

        List<String> nearestLabels = nearestIndices.stream()
                .map(labels::get)
                .collect(Collectors.toList());

        // Count the frequency of each label and return the most frequent label
        Map<String, Long> labelCounts = nearestLabels.stream()
                .collect(Collectors.groupingBy(label -> label, Collectors.counting()));

        return Collections.max(labelCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    // Helper method to calculate Euclidean distance
    private double euclideanDistance(double[] point1, double[] point2) {
        double sum = 0.0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(point1[i] - point2[i], 2);
        }
        return Math.sqrt(sum);
    }
    
}
