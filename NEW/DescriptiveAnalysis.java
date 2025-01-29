/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

public class DescriptiveAnalysis extends DataExploration {

    public void displaySummary(List<Object> column, String columnName) {
        System.out.println("Inside displaySummary method...");  // Debugging print

        // Convert Object list to Double list
        List<Double> numericData = column.stream()
                .map(obj -> Double.parseDouble(obj.toString()))
                .collect(Collectors.toList());

        // Calculate summary statistics
        DoubleSummaryStatistics stats = numericData.stream().mapToDouble(Double::doubleValue).summaryStatistics();
        double mean = stats.getAverage();
        double max = stats.getMax();
        double min = stats.getMin();
        double sum = stats.getSum();
        long count = stats.getCount();

        // Calculate median
        Collections.sort(numericData);
        double median = (numericData.size() % 2 == 0)
                ? (numericData.get(numericData.size() / 2 - 1) + numericData.get(numericData.size() / 2)) / 2.0
                : numericData.get(numericData.size() / 2);

        // Calculate standard deviation
        double variance = numericData.stream()
                .mapToDouble(num -> Math.pow(num - mean, 2))
                .average()
                .orElse(0.0);
        double stdDev = Math.sqrt(variance);

        // Display the statistics in a GUI window
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Summary Statistics for " + columnName);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new GridLayout(9, 1));

            frame.add(createLabel("Summary for: " + columnName, true));
            frame.add(createLabel("Count: " + count, false));
            frame.add(createLabel("Sum: " + String.format("%.2f", sum), false));
            frame.add(createLabel("Mean: " + String.format("%.2f", mean), false));
            frame.add(createLabel("Median: " + String.format("%.2f", median), false));
            frame.add(createLabel("Min: " + String.format("%.2f", min), false));
            frame.add(createLabel("Max: " + String.format("%.2f", max), false));
            frame.add(createLabel("Standard Deviation: " + String.format("%.2f", stdDev), false));

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> frame.dispose());
            frame.add(closeButton);

            frame.setVisible(true);
        });
    }

    // Helper method to create formatted JLabel
    private JLabel createLabel(String text, boolean isTitle) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        if (isTitle) {
            label.setFont(new Font("Arial", Font.BOLD, 16));
        } else {
            label.setFont(new Font("Arial", Font.PLAIN, 14));
        }
        return label;
    }
}
