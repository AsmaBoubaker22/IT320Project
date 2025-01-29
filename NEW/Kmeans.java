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

public class Kmeans implements ClassificationModeling {
    private int k;
    private int maxIterations;
    private List<double[]> centroids;
    private List<Integer> labels;
    
    @Override
    public void knnClassifier(List<Object> data, String targetAttribute){
    }
    
    public Kmeans(int k, int maxIterations) {
        this.k = k;
        this.maxIterations = maxIterations;
        this.centroids = new ArrayList<>();
        this.labels = new ArrayList<>();
    }

    @Override
    public void fit(List<?> data) {
        List<double[]> dataset = (List<double[]>) data;

        if (dataset == null || dataset.isEmpty()) {
            throw new IllegalArgumentException("Dataset is empty or null.");
        }

        centroids = initializeCentroids(dataset);
        labels = assignClusters(dataset);

        for (int iter = 0; iter < maxIterations; iter++) {
            List<double[]> newCentroids = updateCentroids(dataset, labels);

            if (centroids.equals(newCentroids)) {
                break; // Centroids have stabilized
            }

            centroids = newCentroids;
            labels = assignClusters(dataset);
        }
    }

    
    public List<double[]> getCentroids() {
        return centroids;
    }

    
    public List<Integer> getLabels() {
        return labels;
    }

    private List<double[]> initializeCentroids(List<double[]> dataset) {
        List<double[]> initialCentroids = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            initialCentroids.add(dataset.get(i % dataset.size())); // Wrap around if k > dataset size
        }
        return initialCentroids;
    }

    private List<Integer> assignClusters(List<double[]> dataset) {
        List<Integer> assignments = new ArrayList<>();
        for (double[] point : dataset) {
            int closest = 0;
            double minDistance = Double.MAX_VALUE;

            for (int i = 0; i < centroids.size(); i++) {
                double distance = euclideanDistance(point, centroids.get(i));
                if (distance < minDistance) {
                    minDistance = distance;
                    closest = i;
                }
            }

            assignments.add(closest);
        }
        return assignments;
    }

    private List<double[]> updateCentroids(List<double[]> dataset, List<Integer> labels) {
        List<double[]> newCentroids = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            double[] sum = new double[2];
            int count = 0;

            for (int j = 0; j < dataset.size(); j++) {
                if (labels.get(j) == i) {
                    sum[0] += dataset.get(j)[0];
                    sum[1] += dataset.get(j)[1];
                    count++;
                }
            }

            if (count > 0) {
                sum[0] /= count;
                sum[1] /= count;
            }
            newCentroids.add(sum);
        }
        return newCentroids;
    }

    private double euclideanDistance(double[] a, double[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }
    
    @Override
    public double evaluate(List<?> testData) {
        throw new UnsupportedOperationException("Evaluate is not implemented for Clustering.");
    }
    
    @Override
    public List<?> predict(List<?> data) {
        throw new UnsupportedOperationException("Predict is not implemented for Clustering.");
    }
    
}