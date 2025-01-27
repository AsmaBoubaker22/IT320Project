/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.lang.reflect.Field;
import javax.swing.*;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class IT320Projet {

    public static void main(String[] args) {
        System.out.println("Hellooooooooooooooo!");
        
        CSVIngestion csvIngestion = new CSVIngestion();
        
        String filePath = "C:\\Users\\Asma\\Desktop\\SampleData.csv"; 
        
        List<List<String>> data = csvIngestion.csvImport(filePath);
        
        PropertyDataCleaner cleaner = new PropertyDataCleaner();
        
        //cleaner.removeMissingValues(data);
        cleaner.removeDuplicates(data);
        cleaner.removeOutliers(data);
        cleaner.fixYear(data);
        
        // Now, inject the data into the respective objects CSV--------------------------------------------------------------
        csvIngestion.csvInject(data);
      
        System.out.println("Buyers List: " + csvIngestion.buyersList);
        System.out.println("Agents List: " + csvIngestion.agentsList);
        System.out.println("Houses List: " + csvIngestion.housesList);
        System.out.println("Locations List: " + csvIngestion.locationList);
        System.out.println("Units List: " + csvIngestion.unitsList);
        System.out.println("Townhouses List: " + csvIngestion.townhousesList);
        System.out.println("Development Sites List: " + csvIngestion.developmentSitesList);
        System.out.println("Transactions List: " + csvIngestion.transactionsList);
        
        
        XLSIngestion xlsIngestion = new XLSIngestion();
        String filePath2 = "C:\\Users\\Asma\\Desktop\\SampleDataX.xlsx"; 
        List<List<String>> data2 = xlsIngestion.xlsImport(filePath2);
        
        //cleaner.removeMissingValues(data2);
        cleaner.removeDuplicates(data2);
        cleaner.removeOutliers(data2);
        cleaner.fixYear(data2);
        
        // Now, inject the data into the respective objects--------------------------------------------------------------
        xlsIngestion.xlsInject(data2);
        System.out.println("Buyers List: " + xlsIngestion.buyersList);
        System.out.println("Agents List: " + xlsIngestion.agentsList);
        System.out.println("Houses List: " + xlsIngestion.housesList);
        System.out.println("Locations List: " + xlsIngestion.locationList);
        System.out.println("Units List: " + xlsIngestion.unitsList);
        System.out.println("Townhouses List: " + xlsIngestion.townhousesList);
        System.out.println("Development Sites List: " + xlsIngestion.developmentSitesList);
        System.out.println("Transactions List: " + xlsIngestion.transactionsList);
        
        // Combine the lists from both CSV and XLS into a unified list for each type---------------------------
        List<Buyer> buyersList = new ArrayList<>();
        buyersList.addAll(csvIngestion.buyersList);
        buyersList.addAll(xlsIngestion.buyersList);

        List<Agent> agentsList = new ArrayList<>();
        agentsList.addAll(csvIngestion.agentsList);
        agentsList.addAll(xlsIngestion.agentsList);

        List<House> housesList = new ArrayList<>();
        housesList.addAll(csvIngestion.housesList);
        housesList.addAll(xlsIngestion.housesList);

        List<Location> locationList = new ArrayList<>();
        locationList.addAll(csvIngestion.locationList);
        locationList.addAll(xlsIngestion.locationList);

        List<Unit> unitsList = new ArrayList<>();
        unitsList.addAll(csvIngestion.unitsList);
        unitsList.addAll(xlsIngestion.unitsList);

        List<Townhouse> townhousesList = new ArrayList<>();
        townhousesList.addAll(csvIngestion.townhousesList);
        townhousesList.addAll(xlsIngestion.townhousesList);

        List<DevelopmentSite> developmentSitesList = new ArrayList<>();
        developmentSitesList.addAll(csvIngestion.developmentSitesList);
        developmentSitesList.addAll(xlsIngestion.developmentSitesList);

        List<Transaction> transactionsList = new ArrayList<>();
        transactionsList.addAll(csvIngestion.transactionsList);
        transactionsList.addAll(xlsIngestion.transactionsList);
        
        /*
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\units.csv", unitsList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\agents.csv", agentsList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\buyers.csv", buyersList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\transactions.csv", transactionsList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\houses.csv", housesList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\dev.csv", developmentSitesList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\townhouses.csv", townhousesList);
        CSVExporter.saveToTXTFile("C:\\Users\\Asma\\Desktop\\locations.csv", locationList);
*/
        
        // EXPORT TO DATABASE ---------------------------------------------------------------------------------
        //DatabaseExporter exporter = new DatabaseExporter("jdbc:mysql://localhost:3306/java_db", "root", "asma");
        //exporter.exportData(buyersList, agentsList, housesList, unitsList, townhousesList, developmentSitesList, transactionsList);

       
        
        // Create the main GUI frame
        // Create the frame
        JFrame frame = new JFrame("Machine Learning and Data Exploration");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false); // Prevent resizing

        // Title label
        JLabel titleLabel = new JLabel("Welcome to Data Exploration and Machine Learning", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Main Buttons Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));

        // Data Exploration button
        JButton dataExplorationButton = new JButton("Data Exploration");
        mainPanel.add(dataExplorationButton);

        // Machine Learning button
        JButton machineLearningButton = new JButton("Machine Learning");
        mainPanel.add(machineLearningButton);

        // Data Exploration Panel (hidden initially)
        JPanel dataExplorationPanel = new JPanel();
        dataExplorationPanel.setLayout(new GridLayout(3, 1));
        dataExplorationPanel.setVisible(false); // Hide initially

        JButton descriptiveStatsButton = new JButton("Descriptive Statistics");
        JButton dataVizButton = new JButton("Data Visualizations");
        JButton correlationAnalysisButton = new JButton("Correlation Analysis");

        dataExplorationPanel.add(descriptiveStatsButton);
        dataExplorationPanel.add(dataVizButton);
        dataExplorationPanel.add(correlationAnalysisButton);

        // Panel for machine learning options (hidden initially)
        JPanel mlPanel = new JPanel();
        mlPanel.setLayout(new GridLayout(3, 1));
        mlPanel.setVisible(false); // Hide initially

        JButton predictiveModelingButton = new JButton("Predictive Modeling");
        JButton classificationModelingButton = new JButton("Classification Modeling");

        mlPanel.add(predictiveModelingButton);
        mlPanel.add(classificationModelingButton);

        // Panel for predictive modeling options (hidden initially)
        JPanel predictivePanel = new JPanel();
        predictivePanel.setLayout(new GridLayout(3, 1));
        predictivePanel.setVisible(false); // Hide initially

        JButton linearRegressionButton = new JButton("Linear Regression");
        JButton tsaAnalysisButton = new JButton("TSA Analysis");

        predictivePanel.add(linearRegressionButton);
        predictivePanel.add(tsaAnalysisButton);

        // Panel for classification modeling options (hidden initially)
        JPanel classificationPanel = new JPanel();
        classificationPanel.setLayout(new GridLayout(3, 1));
        classificationPanel.setVisible(false); // Hide initially

        JButton clusteringButton = new JButton("Clustering");
        JButton knnButton = new JButton("KNN Classifier");
        JButton randomForestButton = new JButton("Random Forest");

        classificationPanel.add(clusteringButton);
        classificationPanel.add(knnButton);
        classificationPanel.add(randomForestButton);

        // Create a CardLayout for alternating panels (Predictive and Classification)
        JPanel rightPanel = new JPanel(new CardLayout());
        rightPanel.setVisible(false); // Hide initially

        rightPanel.add(predictivePanel, "Predictive Modeling");
        rightPanel.add(classificationPanel, "Classification Modeling");

        // Add panels to the frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(mlPanel, BorderLayout.SOUTH);
        frame.add(dataExplorationPanel, BorderLayout.WEST); // Data Exploration on the left
        frame.add(rightPanel, BorderLayout.EAST); // Machine Learning options on the right

        // Button actions
        machineLearningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the ML options (Predictive and Classification)
                mlPanel.setVisible(true); // Show the ML panel
                frame.revalidate();
                frame.repaint();
            }
        });

        dataExplorationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle visibility of data exploration panel on the left
                dataExplorationPanel.setVisible(!dataExplorationPanel.isVisible());
                frame.revalidate();
                frame.repaint();
            }
        });

        predictiveModelingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the predictive panel in the right side
                rightPanel.setVisible(true); // Show the right panel for ML options
                CardLayout cardLayout = (CardLayout) rightPanel.getLayout();
                cardLayout.show(rightPanel, "Predictive Modeling");
                frame.revalidate();
                frame.repaint();
            }
        });

        classificationModelingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the classification panel in the right side
                rightPanel.setVisible(true); // Show the right panel for ML options
                CardLayout cardLayout = (CardLayout) rightPanel.getLayout();
                cardLayout.show(rightPanel, "Classification Modeling");
                frame.revalidate();
                frame.repaint();
            }
        });


        // DESCRIPTIVE STATISTICS -------------------------------------------------------------------------------------------------------------------------------------------------
        descriptiveStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                JFrame mainFrame = new JFrame("Descriptive Statistics Tool");
                mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mainFrame.setSize(600, 400);
                mainFrame.setLayout(new BorderLayout());
                mainFrame.setResizable(false);

                JLabel titleLabel = new JLabel("Select a column for descriptive statistics", SwingConstants.CENTER);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
                mainFrame.add(titleLabel, BorderLayout.NORTH);

                // Available columns list
                String[] columns = {
                    "Age (buyers table)", "Price (transactions table)", "Rooms (houses table)",
                    "Building Size (houses table)", "Bathrooms (houses table)", "Landsize (houses table)",
                    "Parking Spot (houses table)", "Property Count (houses table)", "Rooms (units table)",
                    "Bathrooms (units table)", "Parking Spot (units table)", "Landsize (units table)",
                    "Building Size (units table)", "Floor Level (units table)", "Property Count (units table)",
                    "Landsize (townhouses table)", "Rooms (townhouses table)", "Bathrooms (townhouses table)",
                    "Parking Spot (townhouses table)", "Building Size (townhouses table)", 
                    "Number of Shared Walls (townhouses table)", "Number of Levels (townhouses table)",
                    "Landsize (developmentSite table)", "Property Count (developmentSite table)"
                };

                JComboBox<String> columnSelector = new JComboBox<>(columns);
                mainFrame.add(columnSelector, BorderLayout.CENTER);

                // Buttons panel
                JPanel buttonPanel = new JPanel();
                JButton analyzeButton = new JButton("Analyze");
                JButton exitButton = new JButton("Exit");

                buttonPanel.add(analyzeButton);
                buttonPanel.add(exitButton);
                mainFrame.add(buttonPanel, BorderLayout.SOUTH);

                analyzeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int choice = columnSelector.getSelectedIndex();
                        analyzeData(choice + 1, columns);
                    }
                });
                // Action for "Exit" button
                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainFrame.dispose();  // Close only this window
                    }
                });
                mainFrame.setVisible(true);
            });
                }
            
            });

        
        //DATA VISUALIZATION --------------------------------------------------------------------------------------------------------------------------------
        dataVizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Data Visualization feature coming soon!");
            }
        });

        
        //CORRELATIONNNNNNN BUTTON---------------------------------------------------------------------------------------------------------------------------------
        correlationAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, Map<String, List<String>>> combinations = new HashMap<>();

                // Populate the combinations map for each test type
                Map<String, List<String>> pearsonCombinations = new HashMap<>();
                pearsonCombinations.put("houses", Arrays.asList("rooms, bathrooms", "landsize, buildingSize"));
                pearsonCombinations.put("townhouses", Arrays.asList("landsize, buildingSize", "rooms, bathrooms", "numberOfSharedWalls, numberOfLevels"));
                pearsonCombinations.put("units", Arrays.asList("rooms, bathrooms", "landsize, buildingSize", "rooms, parkingSpot", "yearBuilt, buildingSize", "distance, postcode"));
                pearsonCombinations.put("transactions", Arrays.asList("price, distance"));
                pearsonCombinations.put("buyers", Arrays.asList("age, gender"));

                Map<String, List<String>> spearmanCombinations = new HashMap<>();
                spearmanCombinations.put("buyers", Arrays.asList("age, gender"));
                spearmanCombinations.put("townhouses", Arrays.asList("landsize, numberOfLevels", "rooms, numberOfLevels"));
                spearmanCombinations.put("houses", Arrays.asList( "rooms, hasGarden", "rooms, hasSwimmingPool"));
                spearmanCombinations.put("units", Arrays.asList("rooms, hasBalcony", "rooms, hasElevator"));

                Map<String, List<String>> chiSquareCombinations = new HashMap<>();
                chiSquareCombinations.put("houses", Arrays.asList("hasGarden, hasSwimmingPool", "hasGarden, hasFence"));
                chiSquareCombinations.put("townhouses", Arrays.asList("regionName, councilArea", "regionName, postcode"));
                chiSquareCombinations.put("developmentSite", Arrays.asList("regionName, councilArea", "regionName, isLandCleared"));
                chiSquareCombinations.put("buyers", Arrays.asList("gender, age"));
                chiSquareCombinations.put("houses", Arrays.asList("hasGarden, hasSwimmingPool", "hasGarden, hasFence"));

                combinations.put("pearson", pearsonCombinations);
                combinations.put("spearman", spearmanCombinations);
                combinations.put("chi-square", chiSquareCombinations);

                // Create the frame for the GUI
                JFrame frame = new JFrame("Correlation Test");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(600, 300);

                // Create components
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(5, 1));

                // Table selection
                JLabel tableLabel = new JLabel("Select Table:");
                JComboBox<String> tableComboBox = new JComboBox<>(new String[]{"houses", "townhouses", "units", "buyers", "transactions", "developmentSite"});
                panel.add(tableLabel);
                panel.add(tableComboBox);

                // Test type selection
                JLabel testLabel = new JLabel("Select Test:");
                JComboBox<String> testComboBox = new JComboBox<>(new String[]{"pearson", "spearman", "chi-square"});
                panel.add(testLabel);
                panel.add(testComboBox);

                // Column combination selection
                JLabel columnLabel = new JLabel("Select Column Pair:");
                JComboBox<String> columnComboBox = new JComboBox<>();
                panel.add(columnLabel);
                panel.add(columnComboBox);

                // Update column combinations based on selected test and table
                testComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateColumnCombinations(tableComboBox.getSelectedItem().toString(), testComboBox.getSelectedItem().toString(), columnComboBox, combinations);
                    }
                });

                tableComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateColumnCombinations(tableComboBox.getSelectedItem().toString(), testComboBox.getSelectedItem().toString(), columnComboBox, combinations);
                    }
                });

                // Result display area
                JTextArea resultArea = new JTextArea(5, 20);
                resultArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(resultArea);
                panel.add(scrollPane);

                // Calculate button
                JButton calculateButton = new JButton("Calculate Correlation");
                calculateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedTable = tableComboBox.getSelectedItem().toString();
                        String selectedTest = testComboBox.getSelectedItem().toString();
                        String selectedColumns = columnComboBox.getSelectedItem().toString();
                        String[] columns = selectedColumns.split(", ");
                        String column1 = columns[0];
                        String column2 = columns[1];

                        // Calculate the correlation result
                        try {
                            double result = Correlation.calculateCorrelation(selectedTable, column1, column2, selectedTest);
                            resultArea.setText("Correlation Result: " + result);
                        } catch (Exception ex) {
                            resultArea.setText("Error: " + ex.getMessage());
                        }
                    }
                });
                panel.add(calculateButton);

                // Add panel to frame
                frame.add(panel);
                frame.setVisible(true);

            }
        });
        
        
        //LINEAR REGRESSION-----------------------------------------------------------------------------------------------------------------------------------------------------
        linearRegressionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //------------------------------------------------------------------------
                JFrame frame = new JFrame("Linear Regression Model");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(500, 500);
                frame.setLayout(new BorderLayout());

                // Title label
                JLabel titleLabel = new JLabel("Welcome to the Linear Regression Model!", SwingConstants.CENTER);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
                frame.add(titleLabel, BorderLayout.NORTH);

                // Model Selection Panel
                JPanel modelPanel = new JPanel();
                modelPanel.setLayout(new GridLayout(5, 1));
                JLabel selectModelLabel = new JLabel("Select a model:");
                modelPanel.add(selectModelLabel);

                // Dropdown for model selection
                String[] models = {
                    "House Price Prediction",
                    "Units Price Prediction",
                    "Townhouse Price Prediction"
                };
                JComboBox<String> modelComboBox = new JComboBox<>(models);
                modelPanel.add(modelComboBox);

                // Input panel for features
                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new GridLayout(4, 2));

                JLabel feature1Label = new JLabel("Feature 1 (Rooms):");
                JTextField feature1Field = new JTextField();
                inputPanel.add(feature1Label);
                inputPanel.add(feature1Field);

                JLabel feature2Label = new JLabel("Feature 2 (Bathrooms):");
                JTextField feature2Field = new JTextField();
                inputPanel.add(feature2Label);
                inputPanel.add(feature2Field);

                JLabel feature3Label = new JLabel("Feature 3 (Land Size):");
                JTextField feature3Field = new JTextField();
                inputPanel.add(feature3Label);
                inputPanel.add(feature3Field);

                JLabel feature4Label = new JLabel("Feature 4 (Parking Spots - Units only):");
                JTextField feature4Field = new JTextField();
                inputPanel.add(feature4Label);
                inputPanel.add(feature4Field);

                modelPanel.add(inputPanel);

                // Button to confirm model selection
                JButton selectButton = new JButton("Run Model");
                modelPanel.add(selectButton);

                frame.add(modelPanel, BorderLayout.CENTER);

                // Result Area (To display outputs)
                JTextArea resultArea = new JTextArea(10, 40);
                resultArea.setEditable(false);
                frame.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

                // Button Click Action Listener
                selectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int modelChoice = modelComboBox.getSelectedIndex() + 1;

                        // Load data based on user selection
                        List<List<Object>> data = loadDataForModel(modelChoice);

                        // Feature and target columns
                        List<Integer> featureColumns = getFeatureColumnsForModel(modelChoice);
                        int targetColumnIndex = getTargetColumnForModel(modelChoice);

                        // Instantiate and fit the Linear Regression model
                        LinearRegression model = new LinearRegression();
                        model.linearRegression(data, featureColumns, targetColumnIndex);

                        // Prepare test data for evaluation
                        List<Object> actualTargetValues = new ArrayList<>();
                        for (List<Object> row : data) {
                            actualTargetValues.add(row.get(targetColumnIndex));
                        }

                        double mse = model.evaluate(actualTargetValues);

                        // Get user input values
                        try {
                            double feature1 = Double.parseDouble(feature1Field.getText());
                            double feature2 = Double.parseDouble(feature2Field.getText());
                            double feature3 = Double.parseDouble(feature3Field.getText());
                            double feature4 = feature4Field.getText().isEmpty() ? 0.0 : Double.parseDouble(feature4Field.getText());

                            // Prepare user input for prediction
                            List<Object> userInput;
                            if (modelChoice == 2) {
                                // Model 2 has an extra feature (parking spots)
                                userInput = Arrays.asList(feature1, feature2, feature4, feature3);
                            } else {
                                userInput = Arrays.asList(feature1, feature2, feature3);
                            }

                            List<List<Object>> testInput = Collections.singletonList(userInput);
                            List<?> predictions = model.predict(testInput);

                            // Display results in the text area
                            resultArea.setText("Model Selected: " + models[modelChoice - 1] + "\n");
                            resultArea.append("Mean Squared Error (MSE): " + mse + "\n");
                            resultArea.append("Model coefficients are printed in the console.\n");
                            resultArea.append("Predicted Value: " + predictions.get(0) + "\n");

                        } catch (NumberFormatException ex) {
                            resultArea.setText("Error: Please enter valid numerical values for features.");
                        }
                    }
                });

                frame.setVisible(true);
            }

            // Method to load data dynamically based on the model choice
            private static List<List<Object>> loadDataForModel(int modelChoice) {
                List<List<Object>> data = new ArrayList<>();
                try {
                    List<Object> rooms;
                    List<Object> bathrooms;
                    List<Object> landSize;
                    List<Object> parkingSpots = new ArrayList<>();
                    List<Object> prices;

                    switch (modelChoice) {
                        case 1: // House Price Prediction
                            // Fetch columns for House model
                            rooms = DataExploration.fetchColumn("houses", "rooms");
                            bathrooms = DataExploration.fetchColumn("houses", "bathrooms");
                            landSize = DataExploration.fetchColumn("houses", "landsize");
                            prices = DataExploration.fetchColumn("transactions", "price");

                            // Construct the rows for House model
                            for (int i = 0; i < rooms.size(); i++) {
                                List<Object> row = new ArrayList<>();
                                row.add(rooms.get(i));        // Feature 1: Rooms
                                row.add(bathrooms.get(i));    // Feature 2: Bathrooms
                                row.add(landSize.get(i));     // Feature 3: Land Size
                                row.add(prices.get(i));       // Target: Price
                                data.add(row);
                            }
                            break;

                        case 2: // Units Price Prediction
                            // Fetch columns for Units model (with parking spots)
                            rooms = DataExploration.fetchColumn("units", "rooms");
                            bathrooms = DataExploration.fetchColumn("units", "bathrooms");
                            landSize = DataExploration.fetchColumn("units", "landsize");
                            parkingSpots = DataExploration.fetchColumn("units", "parkingSpot");
                            prices = DataExploration.fetchColumn("transactions", "price");

                            // Construct the rows for Units model
                            for (int i = 0; i < rooms.size(); i++) {
                                List<Object> row = new ArrayList<>();
                                row.add(rooms.get(i));        // Feature 1: Rooms
                                row.add(bathrooms.get(i));    // Feature 2: Bathrooms
                                row.add(parkingSpots.get(i)); // Feature 3: Parking Spots
                                row.add(landSize.get(i));     // Feature 4: Land Size
                                row.add(prices.get(i));       // Target: Price
                                data.add(row);
                            }
                            break;

                        case 3: // Townhouse Price Prediction
                            // Fetch columns for Townhouse model
                            rooms = DataExploration.fetchColumn("townhouses", "rooms");
                            bathrooms = DataExploration.fetchColumn("townhouses", "bathrooms");
                            landSize = DataExploration.fetchColumn("townhouses", "landsize");
                            prices = DataExploration.fetchColumn("transactions", "price");

                            // Construct the rows for Townhouse model
                            for (int i = 0; i < rooms.size(); i++) {
                                List<Object> row = new ArrayList<>();
                                row.add(rooms.get(i));        // Feature 1: Rooms
                                row.add(bathrooms.get(i));    // Feature 2: Bathrooms
                                row.add(landSize.get(i));     // Feature 3: Land Size
                                row.add(prices.get(i));       // Target: Price
                                data.add(row);
                            }
                            break;

                        default:
                            System.out.println("Invalid model choice.");
                    }
                } catch (Exception e) {
                    System.err.println("Error loading data: " + e.getMessage());
                }

                return data;
            }

            // Method to return feature column indices based on the model
            private static List<Integer> getFeatureColumnsForModel(int modelChoice) {
                switch (modelChoice) {
                    case 1: return Arrays.asList(0, 1, 2);  // House: rooms, bathrooms, landsize
                    case 2: return Arrays.asList(0, 1, 2, 3);  // Units: rooms, bathrooms, parkingSpot, landsize
                    case 3: return Arrays.asList(0, 1, 2);  // Townhouse: rooms, bathrooms, landsize
                    default: return new ArrayList<>();
                }
            }

            // Method to get the target column index for the selected model
            private static int getTargetColumnForModel(int modelChoice) {
                switch (modelChoice) {
                    case 1: return 3;  // price column
                    case 2: return 4;  // price column
                    case 3: return 3;  // price column
                    default: return 3;
                }
                }
        });
        
        
        //TSA ANALYSIS ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        tsaAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Time Series Analysis Model");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(500, 500);
                frame.setLayout(new BorderLayout());

                // Title label
                JLabel titleLabel = new JLabel("Welcome to the Time Series Analysis Model!", SwingConstants.CENTER);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
                frame.add(titleLabel, BorderLayout.NORTH);

                // Model Selection Panel
                JPanel modelPanel = new JPanel();
                modelPanel.setLayout(new GridLayout(5, 1));
                JLabel selectModelLabel = new JLabel("Select a model:");
                modelPanel.add(selectModelLabel);

                // Dropdown for model selection
                String[] models = {
                    "Property Price Prediction",
                    "Transaction Volume Prediction",
                    "Regional Property Demand Prediction"
                };
                JComboBox<String> modelComboBox = new JComboBox<>(models);
                modelPanel.add(modelComboBox);

                // Input panel for window size
                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new GridLayout(2, 2));

                JLabel windowSizeLabel = new JLabel("Window Size (e.g., 3, 5, 10, 100):");
                JTextField windowSizeField = new JTextField();
                inputPanel.add(windowSizeLabel);
                inputPanel.add(windowSizeField);

                modelPanel.add(inputPanel);

                // Button to confirm model selection
                JButton selectButton = new JButton("Run Model");
                modelPanel.add(selectButton);

                frame.add(modelPanel, BorderLayout.CENTER);

                // Result Area (To display outputs)
                JTextArea resultArea = new JTextArea(10, 40);
                resultArea.setEditable(false);
                frame.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

                // Button Click Action Listener
                selectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int modelChoice = modelComboBox.getSelectedIndex() + 1;
                        String columnName = "";
                        String tableName = "";
                        List<Object> columnData = new ArrayList<>();

                        // Prepare data based on model choice
                        switch (modelChoice) {
                            case 1: // Property Price Prediction
                                tableName = "transactions";
                                columnName = "price";
                                columnData = DataExploration.fetchColumn(tableName, columnName);  // Fetch price data
                                break;
                            case 2: // Transaction Volume Prediction
                                tableName = "transactions";
                                columnName = "transactionId";
                                columnData = DataExploration.fetchColumn(tableName, columnName);  // Fetch transaction volume data
                                break;
                            case 3: // Regional Property Demand Prediction
                                List<Object> propertyCounts = new ArrayList<>();
                                propertyCounts.addAll(DataExploration.fetchColumn("houses", "propertyCount"));
                                propertyCounts.addAll(DataExploration.fetchColumn("units", "propertyCount"));
                                propertyCounts.addAll(DataExploration.fetchColumn("townhouses", "propertyCount"));
                                propertyCounts.addAll(DataExploration.fetchColumn("developmentSite", "propertyCount"));
                                columnData = propertyCounts;
                                break;
                            default:
                                resultArea.setText("Invalid model choice. Exiting...");
                                return;
                        }

                        // Convert the column data to a format suitable for TSA (timestamps + values)
                        List<List<Object>> data5 = new ArrayList<>();
                        for (int i = 0; i < columnData.size(); i++) {
                            data5.add(List.of(i + 1, columnData.get(i)));  // Adding a simple timestamp (i+1) as the first value
                        }

                        // Get the window size from the text field
                        int windowSize = 0;
                        try {
                            windowSize = Integer.parseInt(windowSizeField.getText());
                        } catch (NumberFormatException ex) {
                            resultArea.setText("Error: Please enter a valid numerical value for the window size.");
                            return;
                        }

                        // Perform TSA analysis
                        TimeSeriesAnalysis tsaModel = new TimeSeriesAnalysis();
                        tsaModel.tsaAnalysis(data5, 1, windowSize);

                        // Get moving averages and display results
                        List<Double> movingAverages = tsaModel.getMovingAverages();
                        resultArea.setText("Model Selected: " + models[modelChoice - 1] + "\n");
                        resultArea.append("Calculated Moving Averages: " + movingAverages + "\n");
                    }
                });

                frame.setVisible(true);
                    }
                });

        
        //CLUSTERING--------------------------------------------------------------------------------------------------------------------------------
        clusteringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "clustering feature coming soon!");
            }
        });
        
        //KNN CLASSIFIER--------------------------------------------------------------------------------------------------------------------------------
        knnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "KNN feature coming soon!");
            }
        });
        
        //RANDOM FOREST--------------------------------------------------------------------------------------------------------------------------------
        randomForestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "random forest feature coming soon!");
            }
        });
       
        // Display the window
        frame.setVisible(true);


    }
    
    
    // Update the column combinations in the columnComboBox
    private static void updateColumnCombinations(String tableName, String testType, JComboBox<String> columnComboBox, Map<String, Map<String, List<String>>> combinations) {
        Map<String, List<String>> testCombinations = combinations.get(testType);
        List<String> columnPairs = testCombinations.get(tableName);

        // Clear existing items
        columnComboBox.removeAllItems();

        // Add the new column combinations
        if (columnPairs != null) {
            for (String columnPair : columnPairs) {
                columnComboBox.addItem(columnPair);
            }
        }
    }
    
    private static void analyzeData(int choice, String[] columns) {
        // Mapping columns to table and column names
        String[][] mappings = {
            {"buyers", "age"}, {"transactions", "price"}, {"houses", "rooms"},
            {"houses", "buildingSize"}, {"houses", "bathrooms"}, {"houses", "landsize"},
            {"houses", "parkingSpot"}, {"houses", "propertyCount"}, {"units", "rooms"},
            {"units", "bathrooms"}, {"units", "parkingSpot"}, {"units", "landsize"},
            {"units", "buildingSize"}, {"units", "floorLevel"}, {"units", "propertyCount"},
            {"townhouses", "landsize"}, {"townhouses", "rooms"}, {"townhouses", "bathrooms"},
            {"townhouses", "parkingSpot"}, {"townhouses", "buildingSize"}, 
            {"townhouses", "numberOfSharedWalls"}, {"townhouses", "numberOfLevels"},
            {"developmentSite", "landsize"}, {"developmentSite", "propertyCount"}
        };

        String tableName = mappings[choice - 1][0];
        String columnName = mappings[choice - 1][1];

        // Fetch column data (using the existing logic provided)
        List<Object> data = DataExploration.fetchColumn(tableName, columnName);

        if (data == null || data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No data available for " + columnName + " in the " + tableName + " table.", "Data Unavailable", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Perform descriptive analysis
        DescriptiveAnalysis analysis = new DescriptiveAnalysis();
        showResultsWindow(analysis, data, columnName);
    }

    private static void showResultsWindow(DescriptiveAnalysis analysis, List<Object> data, String columnName) {
        JFrame resultFrame = new JFrame("Statistics for: " + columnName);
        resultFrame.setSize(500, 400);
        resultFrame.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Descriptive Analysis for " + columnName, SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultFrame.add(headerLabel, BorderLayout.NORTH);

        JTextArea resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);

        // Capture the analysis results (existing logic remains unchanged)
        analysis.displaySummary(data, columnName);
        
        // Display a placeholder message since we assume analysis prints to console
        resultArea.setText("Check console for detailed results.\nAnalysis completed successfully.");

        JScrollPane scrollPane = new JScrollPane(resultArea);
        resultFrame.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> resultFrame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        resultFrame.add(buttonPanel, BorderLayout.SOUTH);

        resultFrame.setVisible(true);
    }
}

