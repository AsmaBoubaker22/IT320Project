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

        //DESCRIPTIVE ANALYSIS -------------------------------------------------------------------
        /*
        // Create a scanner object to get user input
        Scanner scanner = new Scanner(System.in);

        // Loop until the user types STOP
        while (true) {
            System.out.println("\nWhich data would you like to apply descriptive statistics to?");
            System.out.println("Choose a number corresponding to the data you want to analyze (or type 'STOP' to exit):");

            // Display available columns with their numbers
            String[] columns = {
                "Age (buyers table)",
                "Price (transactions table)",
                "Rooms (houses table)",
                "Building Size (houses table)",
                "Bathrooms (houses table)",
                "Landsize (houses table)",
                "Parking Spot (houses table)",
                "Property Count (houses table)",
                "Rooms (units table)",
                "Bathrooms (units table)",
                "Parking Spot (units table)",
                "Landsize (units table)",
                "Building Size (units table)",
                "Floor Level (units table)",
                "Property Count (units table)",
                "Landsize (townhouses table)",
                "Rooms (townhouses table)",
                "Bathrooms (townhouses table)",
                "Parking Spot (townhouses table)",
                "Building Size (townhouses table)",
                "Number of Shared Walls (townhouses table)",
                "Number of Levels (townhouses table)",
                "Landsize (developmentSite table)",
                "Property Count (developmentSite table)"
            };

            for (int i = 0; i < columns.length; i++) {
                System.out.println((i + 1) + ". " + columns[i]);
            }

            System.out.print("\nEnter your choice (number between 1-" + columns.length + ") or type 'STOP' to exit: ");

            // Read the user's choice
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice < 1 || choice > columns.length) {
                    System.out.println("Invalid number! Please choose a number between 1 and " + columns.length + ".");
                    continue;
                }

                // Map choices to table and column names
                String[][] mappings = {
                    {"buyers", "age"},
                    {"transactions", "price"},
                    {"houses", "rooms"},
                    {"houses", "buildingSize"},
                    {"houses", "bathrooms"},
                    {"houses", "landsize"},
                    {"houses", "parkingSpot"},
                    {"houses", "propertyCount"},
                    {"units", "rooms"},
                    {"units", "bathrooms"},
                    {"units", "parkingSpot"},
                    {"units", "landsize"},
                    {"units", "buildingSize"},
                    {"units", "floorLevel"},
                    {"units", "propertyCount"},
                    {"townhouses", "landsize"},
                    {"townhouses", "rooms"},
                    {"townhouses", "bathrooms"},
                    {"townhouses", "parkingSpot"},
                    {"townhouses", "buildingSize"},
                    {"townhouses", "numberOfSharedWalls"},
                    {"townhouses", "numberOfLevels"},
                    {"developmentSite", "landsize"},
                    {"developmentSite", "propertyCount"}
                };

                String tableName = mappings[choice - 1][0];
                String columnName = mappings[choice - 1][1];

                System.out.println("Fetching data for column: " + columnName + " from table: " + tableName);

                // Fetch the selected column data
                List<Object> data3 = DataExploration.fetchColumn(tableName, columnName);

                // Check if data is empty, print a message and continue
                if (data3 == null || data3.isEmpty()) {
                    System.out.println("No data available for " + columnName + " in the " + tableName + " table. Please choose another column.");
                    continue;  // Skip the current iteration and ask the user to choose again
                }

                // Perform descriptive analysis
                DescriptiveAnalysis analysis = new DescriptiveAnalysis();
                analysis.displaySummary(data3, columnName);

            } else {
                String input = scanner.next();
                if (input.equalsIgnoreCase("STOP")) {
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a valid number or type 'STOP' to exit.");
                }
                scanner.nextLine(); // Consume the invalid input
            }
        }

        scanner.close();
        */
        
        /*
        //LINEAR REGRESSION --------------------------------------------------------------------------
        // Display the explanation to the user
        // Create the main GUI frame
        JFrame frame = new JFrame("Linear Regression Model");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
*/
    
      /*  
    // Sample data: each entry contains a timestamp and a value
    // For simplicity, we'll only use one column as the target for time series analysis
    // User selection for the predictive model
     // Create the main GUI frame
        JFrame frame = new JFrame("Time Series Analysis Model");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        */
        
        
        // Create the main GUI frame
        JFrame frame = new JFrame("Machine Learning and Data Exploration");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Welcome to Data Exploration and Machine Learning", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Main Buttons Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));

        // Data Exploration button (unfunctional for now)
        JButton dataExplorationButton = new JButton("Data Exploration (Unfunctional)");
        dataExplorationButton.setEnabled(false); // Disable for now
        mainPanel.add(dataExplorationButton);

        // Machine Learning button
        JButton machineLearningButton = new JButton("Machine Learning");
        mainPanel.add(machineLearningButton);

        // Panel for machine learning options (hidden initially)
        JPanel mlPanel = new JPanel();
        mlPanel.setLayout(new GridLayout(3, 1));
        mlPanel.setVisible(false); // Hide it initially

        JButton predictiveModelingButton = new JButton("Predictive Modeling");
        JButton classificationModelingButton = new JButton("Classification Modeling (Unfunctional)");

        // Disable classification button for now
        classificationModelingButton.setEnabled(false);

        mlPanel.add(predictiveModelingButton);
        mlPanel.add(classificationModelingButton);

        // Panel for predictive modeling options (hidden initially)
        JPanel predictivePanel = new JPanel();
        predictivePanel.setLayout(new GridLayout(3, 1));
        predictivePanel.setVisible(false); // Hide it initially

        JButton linearRegressionButton = new JButton("Linear Regression");
        JButton tsaAnalysisButton = new JButton("TSA Analysis");

        predictivePanel.add(linearRegressionButton);
        predictivePanel.add(tsaAnalysisButton);

        // Add panels to the frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(mlPanel, BorderLayout.SOUTH);
        frame.add(predictivePanel, BorderLayout.EAST);

        // Button actions
        machineLearningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the machine learning options
                mlPanel.setVisible(true);
            }
        });

        predictiveModelingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the predictive modeling options
                predictivePanel.setVisible(true);
            }
        });

        
        
        
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

        
        
        
        
        
        // Display the window
        frame.setVisible(true);


    }
}

