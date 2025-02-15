/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Map;
import java.util.HashMap;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import javax.swing.*;
import java.util.*;
import java.awt.GridLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;

public class DataVisualization extends DataExploration {

    public static void generateBarChart(String tableName, String xAxis, String yAxis) {
        // Fetch the data for X-axis (qualitative) and Y-axis (quantitative)
        List<Object> xAxisData = fetchColumn(tableName, xAxis);
        List<Object> yAxisData = fetchColumn(tableName, yAxis);

        // Get unique values for X and Y axes
        Set<Object> uniqueXValues = new LinkedHashSet<>(xAxisData);
        Set<Object> uniqueYValues = new LinkedHashSet<>(yAxisData);

        // Check the number of unique values for Y-axis
        boolean yAxisHasFourOrLess = uniqueYValues.size() <= 4;
        boolean yAxisHasMoreThan1500 = uniqueYValues.size() > 1500;

        // If Y-axis has more than 4 unique values, divide them into 5 ranges
        List<String> yAxisRanges = new ArrayList<>();
        if (!yAxisHasFourOrLess && !yAxisHasMoreThan1500) {
            List<Double> yValues = uniqueYValues.stream().map(v -> Double.parseDouble(v.toString())).sorted().toList();
            double min = yValues.get(0);
            double max = yValues.get(yValues.size() - 1);
            int rangeSize = (int) Math.ceil((max - min + 1) / 5.0);

            for (int i = 0; i < 5; i++) {
                double rangeStart = min + i * rangeSize;
                double rangeEnd = Math.min(rangeStart + rangeSize - 1, max);

                // Check if the range contains only one element
                if (rangeStart == rangeEnd) {
                    yAxisRanges.add(String.valueOf(rangeStart)); // Just the element itself
                } else {
                    yAxisRanges.add(rangeStart + "-" + rangeEnd); // Regular range format
                }
            }
        }

        // If Y-axis has more than 1500 unique values, sum Y elements based on X elements
        Map<Object, Double> summedYValues = new LinkedHashMap<>();
        if (yAxisHasMoreThan1500) {
            for (int i = 0; i < xAxisData.size(); i++) {
                Object xValue = xAxisData.get(i);
                double yValue = Double.parseDouble(yAxisData.get(i).toString());
                summedYValues.put(xValue, summedYValues.getOrDefault(xValue, 0.0) + yValue);
            }
        }

        // If X-axis has more than 8 unique values, split them into 2 mini-charts
        List<List<Object>> xAxisChunks = new ArrayList<>();
        List<Object> chunk = new ArrayList<>();
        for (Object xValue : uniqueXValues) {
            chunk.add(xValue);
            if (chunk.size() == 8) {
                xAxisChunks.add(new ArrayList<>(chunk));
                chunk.clear();
            }
        }
        if (!chunk.isEmpty()) xAxisChunks.add(chunk); // Add any remaining X-axis values

        // Create and display the charts
        JFrame mainFrame = new JFrame(tableName + " - " + yAxis + " || " + xAxis);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(0, 1)); // Arrange mini charts vertically

        for (List<Object> xChunk : xAxisChunks) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            for (Object xValue : xChunk) {
                if (yAxisHasMoreThan1500) {
                    // Use summed Y values for more than 1500 unique Y-axis values
                    dataset.addValue(summedYValues.getOrDefault(xValue, 0.0), yAxis, xValue.toString());
                } else {
                    Map<Object, Integer> yValueCounts = new LinkedHashMap<>();
                    for (int i = 0; i < xAxisData.size(); i++) {
                        if (xAxisData.get(i).equals(xValue)) {
                            Object yValue = yAxisData.get(i);

                            if (yAxisHasFourOrLess) {
                                // Directly group by unique Y-axis values
                                yValueCounts.put(yValue, yValueCounts.getOrDefault(yValue, 0) + 1);
                            } else {
                                // Group Y-axis values into ranges
                                double y = Double.parseDouble(yValue.toString());
                                for (String range : yAxisRanges) {
                                    String[] bounds = range.split("-");
                                    if (bounds.length == 1) {
                                        // Handle single value range 
                                        double rangeValue = Double.parseDouble(bounds[0]);
                                        if (y == rangeValue) {
                                            yValueCounts.put(range, yValueCounts.getOrDefault(range, 0) + 1);
                                            break;
                                        }
                                    } else {
                                        // Handle regular range
                                        double rangeStart = Double.parseDouble(bounds[0]);
                                        double rangeEnd = Double.parseDouble(bounds[1]);
                                        if (y >= rangeStart && y <= rangeEnd) {
                                            yValueCounts.put(range, yValueCounts.getOrDefault(range, 0) + 1);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Add data to the dataset
                    for (Map.Entry<Object, Integer> entry : yValueCounts.entrySet()) {
                        dataset.addValue(entry.getValue(), entry.getKey().toString(), xValue.toString());
                    }
                }
            }

            // Create the bar chart
            JFreeChart barChart = ChartFactory.createBarChart(
                    tableName + " - " + yAxis + " || " + xAxis, // Title
                    xAxis, // X-axis label
                    "Frequency", // Y-axis label
                    dataset, // Dataset
                    PlotOrientation.VERTICAL, // Orientation
                    true, // Include legend
                    true, // Tooltips
                    false // URLs
            );

            // Display bar values on top of each bar
            CategoryPlot plot = barChart.getCategoryPlot();
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setDefaultItemLabelsVisible(true);

            // Add the chart to the main frame
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(800, 400));
            mainFrame.add(chartPanel);
        }

        mainFrame.pack();
        mainFrame.setVisible(true);
    }



    public static void generatePieChart(String columnName, List<String> tables) {
        Map<String, Map<Object, Integer>> tableWiseCounts = new HashMap<>();
        Map<Object, Integer> combinedCounts = new HashMap<>();
        int tableCountWithColumn = 0;

        for (String table : tables) {
            if (doesColumnExistInTable(table, columnName)) {
                tableCountWithColumn++;
                List<Object> columnData = fetchColumn(table, columnName);

                if (columnData.isEmpty()) {
                    continue;
                }

                // Get unique values from the column
                Set<Object> uniqueValues = new HashSet<>(columnData);

                Map<Object, Integer> valueCounts = new HashMap<>();

                // If there are more than 20 unique values, group them into ranges
                if (uniqueValues.size() > 20) {
                    List<Object> valuesList = new ArrayList<>(uniqueValues);

                    // Sort the values in ascending order
                    valuesList.sort((o1, o2) -> ((Comparable<Object>) o1).compareTo(o2));

                    int totalValues = valuesList.size();
                    int rangeSize = totalValues / 12;  // Define the number of ranges 
                    int remainder = totalValues % 12;  // Handle any remainder

                    List<List<Object>> ranges = new ArrayList<>();
                    int startIdx = 0;

                    // Create the ranges
                    for (int i = 0; i < 12; i++) {
                        int endIdx = startIdx + rangeSize + (i < remainder ? 1 : 0) - 1;  // Adjust for remainder
                        if (i == 11) {
                            endIdx = totalValues - 1; // Ensure last range covers all remaining values
                        }

                        // Add range to the list
                        ranges.add(valuesList.subList(startIdx, endIdx + 1));
                        startIdx = endIdx + 1;
                    }

                    // Count occurrences for each range
                    for (List<Object> range : ranges) {
                        // Define the range label by the lowest and highest value in the range
                        String rangeLabel = range.get(0) + " - " + range.get(range.size() - 1);
                        for (Object value : range) {
                            valueCounts.put(rangeLabel, valueCounts.getOrDefault(rangeLabel, 0) + 1);
                            combinedCounts.put(rangeLabel, combinedCounts.getOrDefault(rangeLabel, 0) + 1);
                        }
                    }
                } else {
                    // For fewer than 20 unique values, count them directly
                    for (Object value : columnData) {
                        valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1);
                        combinedCounts.put(value, combinedCounts.getOrDefault(value, 0) + 1);
                    }
                }

                tableWiseCounts.put(table, valueCounts);
            }
        }

        // Now create the pie chart
        JPanel chartsPanel = new JPanel();
        chartsPanel.setLayout(new GridLayout(2, 2)); 

        for (Map.Entry<String, Map<Object, Integer>> entry : tableWiseCounts.entrySet()) {
            String tableName = entry.getKey();
            Map<Object, Integer> valueCounts = entry.getValue();

            DefaultPieDataset dataset = new DefaultPieDataset();
            int totalEntries = valueCounts.values().stream().mapToInt(Integer::intValue).sum();

            for (Map.Entry<Object, Integer> valueEntry : valueCounts.entrySet()) {
                double percentage = (valueEntry.getValue() * 100.0) / totalEntries;
                String label = valueEntry.getKey() + " (" + String.format("%.2f", percentage) + "%, " + valueEntry.getValue() + ")";
                dataset.setValue(label, valueEntry.getValue());
            }

            JFreeChart pieChart = ChartFactory.createPieChart(
                    "Pie Chart for " + columnName + " in " + tableName,
                    dataset,
                    true,
                    true,
                    false
            );

            ChartPanel chartPanel = new ChartPanel(pieChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(300, 300)); 
            chartsPanel.add(chartPanel);
        }

        // Combined chart
        if (tableCountWithColumn > 1) {
            DefaultPieDataset combinedDataset = new DefaultPieDataset();

            int totalCombinedEntries = combinedCounts.values().stream().mapToInt(Integer::intValue).sum();

            for (Map.Entry<Object, Integer> entry : combinedCounts.entrySet()) {
                double percentage = (entry.getValue() * 100.0) / totalCombinedEntries;
                String label = entry.getKey() + " (" + String.format("%.2f", percentage) + "%, " + entry.getValue() + ")";
                combinedDataset.setValue(label, entry.getValue());
            }

            JFreeChart combinedPieChart = ChartFactory.createPieChart(
                    "Combined Pie Chart for " + columnName,
                    combinedDataset,
                    true,
                    true,
                    false
            );

            ChartPanel combinedChartPanel = new ChartPanel(combinedPieChart);
            combinedChartPanel.setPreferredSize(new java.awt.Dimension(300, 300));
            chartsPanel.add(combinedChartPanel);
        }

        // Show the charts
        SwingUtilities.invokeLater(() -> {
            JFrame chartFrame = new JFrame("Pie Charts for " + columnName);
            chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            chartFrame.add(new JScrollPane(chartsPanel));
            chartFrame.setSize(1000, 800);
            chartFrame.setVisible(true);
        });
    }


    public static void generateScatterPlot(String xAxis, String yAxis) {
        // Fetch data for all scatter plots
        List<Object> priceData = fetchColumn("Transactions", xAxis); 
        List<Object> transactionPropertyIds = fetchColumn("Transactions", "propertyId");

        // Define the property types (Houses, Units, Townhouses)
        List<String> tables = Arrays.asList("Houses", "Units", "Townhouses");

        // Create lists to hold the data for each plot
        List<Double> housePrices = new ArrayList<>();
        List<Double> houseYAxisValues = new ArrayList<>();
        List<Double> unitPrices = new ArrayList<>();
        List<Double> unitYAxisValues = new ArrayList<>();
        List<Double> townhousePrices = new ArrayList<>();
        List<Double> townhouseYAxisValues = new ArrayList<>();
        List<Double> combinedPrices = new ArrayList<>();
        List<Double> combinedYAxisValues = new ArrayList<>();

        // Loop through each table (Houses, Units, Townhouses)
        for (String table : tables) {
            // Lists to hold the matching data points for each property type
            List<Double> matchedPrices = new ArrayList<>();
            List<Double> matchedYAxisValues = new ArrayList<>();  // This will store LandSize, Distance, etc.

            // Fetch y-axis column (LandSize, Distance, etc.) for the current table
            List<Object> yAxisColumnData = fetchColumn(table, yAxis);  // y-axis corresponds to LandSize/Distance
            // Fetch propertyId from the current table
            List<Object> propertyIdsFromTable = fetchColumn(table, "propertyId");

            // Loop through propertyIds in Transactions and match with propertyIds in current table
            for (int i = 0; i < transactionPropertyIds.size(); i++) {
                Object transactionPropertyId = transactionPropertyIds.get(i);

                // If the propertyId matches, add the corresponding data to the lists
                if (propertyIdsFromTable.contains(transactionPropertyId)) {
                    int index = propertyIdsFromTable.indexOf(transactionPropertyId);

                    // Add corresponding price from Transactions
                    if (priceData.get(i) instanceof Double) {
                        matchedPrices.add((Double) priceData.get(i));  // Add corresponding price
                    }

                    // Add corresponding y-axis data (LandSize, Distance, etc.)
                    Object yValue = yAxisColumnData.get(index);

                    // Convert y-axis value to Double if it's not already
                    if (yValue instanceof Number) {
                        matchedYAxisValues.add(((Number) yValue).doubleValue());  // Convert Integer/Double to Double
                    }
                }
            }

            // Add data to specific lists based on property type
            if (table.equals("Houses")) {
                housePrices.addAll(matchedPrices);
                houseYAxisValues.addAll(matchedYAxisValues);
            } else if (table.equals("Units")) {
                unitPrices.addAll(matchedPrices);
                unitYAxisValues.addAll(matchedYAxisValues);
            } else if (table.equals("Townhouses")) {
                townhousePrices.addAll(matchedPrices);
                townhouseYAxisValues.addAll(matchedYAxisValues);
            }

            // Combine the matched data into the global lists for the combined plot
            combinedPrices.addAll(matchedPrices);
            combinedYAxisValues.addAll(matchedYAxisValues);
        }

        // Now create the four scatter plots and display them in the same window
        XYSeriesCollection datasetHouse = createScatterDataset(housePrices, houseYAxisValues, "Houses - Price || " + yAxis);
        XYSeriesCollection datasetUnit = createScatterDataset(unitPrices, unitYAxisValues, "Units - Price || " + yAxis);
        XYSeriesCollection datasetTownhouse = createScatterDataset(townhousePrices, townhouseYAxisValues, "Townhouses - Price || " + yAxis);
        XYSeriesCollection datasetCombined = createScatterDataset(combinedPrices, combinedYAxisValues, "Combined - Price || " + yAxis);

        // Create the scatter plot panels
        JFreeChart chartHouse = createChart(datasetHouse, "Houses - Price vs " + yAxis);
        JFreeChart chartUnit = createChart(datasetUnit, "Units - Price vs " + yAxis);
        JFreeChart chartTownhouse = createChart(datasetTownhouse, "Townhouses - Price vs " + yAxis);
        JFreeChart chartCombined = createChart(datasetCombined, "Combined - Price vs " + yAxis);

        // Create the main frame with GridLayout
        JFrame frame = new JFrame("Scatter Plots");
        frame.setLayout(new GridLayout(2, 2));  // 2x2 grid layout for 4 plots
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add the charts to the frame
        frame.add(new ChartPanel(chartHouse));
        frame.add(new ChartPanel(chartUnit));
        frame.add(new ChartPanel(chartTownhouse));
        frame.add(new ChartPanel(chartCombined));

        // Pack and display the frame
        frame.pack();
        frame.setVisible(true);
    }

    private static XYSeriesCollection createScatterDataset(List<Double> prices, List<Double> yAxisValues, String seriesName) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries(seriesName);
        for (int i = 0; i < prices.size(); i++) {
            series.add(yAxisValues.get(i), prices.get(i));  
        }
        dataset.addSeries(series);
        return dataset;
    }

    private static JFreeChart createChart(XYSeriesCollection dataset, String title) {
        return ChartFactory.createScatterPlot(
                title,  // Chart title
                "",  // x-axis label
                "Price",  // y-axis label
                dataset,  // Dataset
                PlotOrientation.VERTICAL,
                true,  // Include legend
                true,  // Tooltips
                false  // URLs
        );
    }
    }



