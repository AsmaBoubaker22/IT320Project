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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;


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
        ExportToTXTFile.saveToTXTFile("C:\\Users\\Asma\\Desktop\\units.csv", unitsList);
        ExportToTXTFile.saveToTXTFile("C:\\Users\\Asma\\Desktop\\agents.csv", agentsList);
        ExportToTXTFile.saveToTXTFile("C:\\Users\\Asma\\Desktop\\buyers.csv", buyersList);
        ExportToTXTFile.saveToTXTFile("C:\\Users\\Asma\\Desktop\\transactions.csv", transactionsList);
        ExportToTXTFile.saveToTXTFile("C:\\Users\\Asma\\Desktop\\houses.csv", housesList);
        ExportToTXTFile.saveToTXTFile("C:\\Users\\Asma\\Desktop\\dev.csv", developmentSitesList);
        ExportToTXTFile.saveToTXTFile("C:\\Users\\Asma\\Desktop\\townhouses.csv", townhousesList);
        ExportToTXTFile.saveToTXTFile("C:\\Users\\Asma\\Desktop\\locations.csv", locationList);
*/
        
        // EXPORT TO DATABASE ---------------------------------------------------------------------------------
        //DatabaseExporter exporter = new DatabaseExporter("jdbc:mysql://localhost:3306/java_db", "root", "asma");
        //exporter.exportData(buyersList, agentsList, housesList, unitsList, townhousesList, developmentSitesList, transactionsList);

        
        
        // Create the main GUI frame
        JFrame frame = new JFrame("Machine Learning and Data Exploration");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false); // Prevent resizing

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to Data Exploration and Machine Learning", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0x001880)); // Navy Blue
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Main Buttons Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1, 10, 10)); // Spacing between buttons
        mainPanel.setBackground(new Color(0xEAEAEA)); // Light Gray

        // Data Exploration button
        JButton dataExplorationButton = createStyledButton("Data Exploration", new Color(0x3055a3)); // Coral
        mainPanel.add(dataExplorationButton);

        // Machine Learning button
        JButton machineLearningButton = createStyledButton("Machine Learning", new Color(0x3055a3)); // Coral
        mainPanel.add(machineLearningButton);

        // Data Exploration Panel (hidden initially)
        JPanel dataExplorationPanel = new JPanel();
        dataExplorationPanel.setLayout(new GridLayout(3, 1, 10, 10));
        dataExplorationPanel.setVisible(false); // Hide initially
        dataExplorationPanel.setBackground(new Color(0xF4F4F4)); // Very Light Gray
        dataExplorationPanel.setBorder(BorderFactory.createTitledBorder("Data Exploration Options"));

        JButton descriptiveStatsButton = createStyledButton("Descriptive Statistics", new Color(0x7baede)); // Green
        JButton dataVizButton = createStyledButton("Data Visualizations", new Color(0x7baede));
        JButton correlationAnalysisButton = createStyledButton("Correlation Analysis", new Color(0x7baede));

        dataExplorationPanel.add(descriptiveStatsButton);
        dataExplorationPanel.add(dataVizButton);
        dataExplorationPanel.add(correlationAnalysisButton);

        // Machine Learning Panel (hidden initially)
        JPanel mlPanel = new JPanel();
        mlPanel.setLayout(new GridLayout(3, 1, 10, 10));
        mlPanel.setVisible(false); // Hide initially
        mlPanel.setBackground(new Color(0xF4F4F4));
        mlPanel.setBorder(BorderFactory.createTitledBorder("Machine Learning Options"));

        JButton predictiveModelingButton = createStyledButton("Predictive Modeling", new Color(0x7baede)); // Orange
        JButton classificationModelingButton = createStyledButton("Classification Modeling", new Color(0x7baede));

        mlPanel.add(predictiveModelingButton);
        mlPanel.add(classificationModelingButton);

        // Predictive Modeling Panel (hidden initially)
        JPanel predictivePanel = new JPanel();
        predictivePanel.setLayout(new GridLayout(3, 1, 10, 10));
        predictivePanel.setVisible(false);
        predictivePanel.setBackground(new Color(0xF4F4F4));
        predictivePanel.setBorder(BorderFactory.createTitledBorder("Predictive Modeling Options"));

        JButton linearRegressionButton = createStyledButton("Linear Regression", new Color(0x7baede)); // Blue
        JButton tsaAnalysisButton = createStyledButton("TSA Analysis", new Color(0x7baede));

        predictivePanel.add(linearRegressionButton);
        predictivePanel.add(tsaAnalysisButton);

        // Classification Modeling Panel (hidden initially)
        JPanel classificationPanel = new JPanel();
        classificationPanel.setLayout(new GridLayout(3, 1, 10, 10));
        classificationPanel.setVisible(false);
        classificationPanel.setBackground(new Color(0xF4F4F4));
        classificationPanel.setBorder(BorderFactory.createTitledBorder("Classification Modeling Options"));

        JButton clusteringButton = createStyledButton("Clustering", new Color(0x7baede)); // Purple
        JButton knnButton = createStyledButton("KNN Classifier", new Color(0x7baede));

        classificationPanel.add(clusteringButton);
        classificationPanel.add(knnButton);

        // Right Panel with CardLayout
        JPanel rightPanel = new JPanel(new CardLayout());
        rightPanel.setVisible(false); // Hide initially
        rightPanel.setBackground(new Color(0xFFFFFF)); // White background

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
                JButton analyzeButton = createStyledButton("Analyze", new Color(0x239B56)); // Example green
                JButton exitButton = createStyledButton("Exit", new Color(0xB03A2E)); // Example red


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
                // Initialize the main frame
                JFrame frame = new JFrame("Data Visualization");
                frame.setSize(200, 200);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setLayout(new BorderLayout());

                // ComboBox for selecting the chart type
                String[] chartTypes = {"Select Chart", "Bar Chart", "Scatter Plot", "Pie Chart"};
                JComboBox<String> chartTypeComboBox = new JComboBox<>(chartTypes);

                // Panel for options (will remain empty)
                JPanel optionsPanel = new JPanel();
                optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

                // Button to generate the chart
                JButton generateButton = new JButton("Generate Chart");

                // ActionListener for chart type selection (no options will be added here)
                chartTypeComboBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Nothing happens here now, since we want no extra options to appear
                    }
                });

                // Generate Button ActionListener
                generateButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String selectedChart = (String) chartTypeComboBox.getSelectedItem();

                        // Bar Chart option
                        if (selectedChart.equals("Bar Chart")) {
                            // List of tables to choose from
                            List<String> tables = Arrays.asList("Houses", "Units", "Townhouses", "DevelopmentSite", "Transactions");

                            String tableChoice = (String) JOptionPane.showInputDialog(
                                frame,
                                "Please choose a table:",
                                "Select Table for Bar Chart",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                tables.toArray(),
                                tables.get(0)
                            );

                            if (tableChoice == null) return;  // User cancelled

                            // Get the qualitative columns for the selected table (X-axis options)
                            List<String> qualitativeColumns = getQualitativeColumnsForTable(tableChoice);
                            String xAxis = (String) JOptionPane.showInputDialog(
                                frame,
                                "Please choose the variable for the X axis (Qualitative):",
                                "Select X Axis",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                qualitativeColumns.toArray(),
                                qualitativeColumns.get(0)
                            );

                            if (xAxis == null) return;  // User cancelled

                            // Get the quantitative columns for the selected table (Y-axis options)
                            List<String> quantitativeColumns = getQuantitativeColumnsForTable(tableChoice);
                            String yAxis = (String) JOptionPane.showInputDialog(
                                frame,
                                "Please choose the variable for the Y axis (Quantitative):",
                                "Select Y Axis",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                quantitativeColumns.toArray(),
                                quantitativeColumns.get(0)
                            );

                            if (yAxis == null) return;  // User cancelled

                            // Call the generateBarChart method with selected options
                            System.out.println("Bar Chart setup complete.");
                            System.out.println("X Axis: " + xAxis + " from " + tableChoice);
                            System.out.println("Y Axis: " + yAxis + " from " + tableChoice);

                            // Now generate the bar chart based on the user's selection
                            DataVisualization.generateBarChart(tableChoice, xAxis, yAxis);
                        } 

                        // Pie Chart option
                        else if (selectedChart.equals("Pie Chart")) {
                            // List of available columns for Pie Chart
                            List<String> pieChartColumns = Arrays.asList("Gender", "HasElevator", "HasFence", "NumberofSharedWalls", "ParkingSpot");

                            String columnChoice = (String) JOptionPane.showInputDialog(
                                frame,
                                "Please choose a column for the Pie Chart:",
                                "Select Column for Pie Chart",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                pieChartColumns.toArray(),
                                pieChartColumns.get(0)
                            );

                            if (columnChoice == null) return;  // User cancelled

                            // List of tables to check for the selected column
                            List<String> tables = Arrays.asList("DevelopmentSite", "Houses", "Transactions", "Townhouses", "Units", "Buyers");

                            // Identify tables containing the selected column
                            List<String> tablesWithColumn = new ArrayList<>();
                            for (String table : tables) {
                                if (DataVisualization.doesColumnExistInTable(table, columnChoice)) {
                                    tablesWithColumn.add(table);
                                }
                            }

                            if (tablesWithColumn.isEmpty()) {
                                JOptionPane.showMessageDialog(frame, "The selected column does not exist in any table. Exiting.");
                                return;
                            }

                            System.out.println("Generating Pie Charts for column: " + columnChoice);
                            DataVisualization.generatePieChart(columnChoice, tablesWithColumn);
                        }

                        // Scatter Plot option
                        else if (selectedChart.equals("Scatter Plot")) {
                            // List of scatter plot options
                            String[] scatterChoices = {"Price & Landsize", "Price & Distance", "Price & BuildingSize"};
                            String scatterChoice = (String) JOptionPane.showInputDialog(
                                frame,
                                "Please choose the scatter plot variables:",
                                "Select Scatter Plot Variables",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                scatterChoices,
                                scatterChoices[0]
                            );

                            if (scatterChoice == null) return;  // User cancelled

                            // Generate the scatter plot based on the selection
                            if (scatterChoice.equals("Price & Landsize")) {
                                System.out.println("Generating Scatter Plot for Price & Landsize...");
                                DataVisualization.generateScatterPlot("price", "Landsize");
                            } else if (scatterChoice.equals("Price & Distance")) {
                                System.out.println("Generating Scatter Plot for Price & Distance...");
                                DataVisualization.generateScatterPlot("price", "Distance");
                            } else if (scatterChoice.equals("Price & BuildingSize")) {
                                System.out.println("Generating Scatter Plot for Price & BuildingSize...");
                                DataVisualization.generateScatterPlot("price", "BuildingSize");
                            }
                        }
                    }
                });

                // Set up the main window layout
                frame.add(chartTypeComboBox, BorderLayout.NORTH);
                frame.add(optionsPanel, BorderLayout.CENTER);
                frame.add(generateButton, BorderLayout.SOUTH);

                frame.setVisible(true);

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
                SwingUtilities.invokeLater(() -> {
                    JFrame clusterFrame = new JFrame("K-Means Clustering");
                    clusterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    clusterFrame.setSize(500, 400);
                    clusterFrame.setLayout(new BorderLayout());

                    JLabel clusterTitle = new JLabel("K-Means Clustering Tool", SwingConstants.CENTER);
                    clusterTitle.setFont(new Font("Arial", Font.BOLD, 16));
                    clusterFrame.add(clusterTitle, BorderLayout.NORTH);

                    // Main Panel with GridLayout for inputs
                    JPanel inputPanel = new JPanel(new GridLayout(8, 1, 5, 5)); // 8 rows, 1 column, 5px gap
                    inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the panel

                    // Select Column 1
                    JLabel column1Label = new JLabel("Select Column 1:");
                    String[] columns = {
                        "Rooms", "Bathrooms", "parkingSpot", "Landsize", "BuildingArea",
                        "YearBuilt", "Distance", "Price", "Lattitude", "Longtitude"
                    };
                    JComboBox<String> column1Dropdown = new JComboBox<>(columns);

                    // Select Column 2
                    JLabel column2Label = new JLabel("Select Column 2:");
                    JComboBox<String> column2Dropdown = new JComboBox<>(columns);

                    // Number of Clusters
                    JLabel kLabel = new JLabel("Enter number of clusters (K):");
                    JTextField kField = new JTextField();

                    // Maximum Iterations
                    JLabel iterationsLabel = new JLabel("Enter maximum iterations:");
                    JTextField iterationsField = new JTextField("100"); // Default to 100 iterations

                    // Add components to the input panel
                    inputPanel.add(column1Label);
                    inputPanel.add(column1Dropdown);
                    inputPanel.add(column2Label);
                    inputPanel.add(column2Dropdown);
                    inputPanel.add(kLabel);
                    inputPanel.add(kField);
                    inputPanel.add(iterationsLabel);
                    inputPanel.add(iterationsField);

                    // Add input panel to the frame
                    clusterFrame.add(inputPanel, BorderLayout.CENTER);

                    // Run and Exit Buttons
                    JButton runClusteringButton = new JButton("Run Clustering");
                    JButton exitButton = new JButton("Exit");
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.add(runClusteringButton);
                    buttonPanel.add(exitButton);

                    clusterFrame.add(buttonPanel, BorderLayout.SOUTH);

                    // Results area
                    JTextArea resultArea = new JTextArea(10, 30);
                    resultArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(resultArea);
                    clusterFrame.add(scrollPane, BorderLayout.EAST);

                    // Run Clustering Button Action
                    runClusteringButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                int k = Integer.parseInt(kField.getText());
                                int maxIterations = Integer.parseInt(iterationsField.getText());

                                // Get selected columns
                                String col1Name = column1Dropdown.getSelectedItem().toString();
                                String col2Name = column2Dropdown.getSelectedItem().toString();

                                // Fetch selected data
                                List<Object> col1Data = DataExploration.fetchColumn("houses", col1Name);
                                List<Object> col2Data = DataExploration.fetchColumn("houses", col2Name);

                                // Validate and prepare dataset
                                List<double[]> dataset = ClassificationModeling.getDataset(col1Data, col2Data);

                                // Run k-means clustering
                                Kmeans kMeans = new Kmeans(k, maxIterations);
                                kMeans.fit(dataset);

                                // Display results
                                StringBuilder result = new StringBuilder();
                                result.append("Clustering Complete!\n\n");
                                result.append("Centroids:\n");
                                for (int i = 0; i < k; i++) {
                                    result.append("Cluster ").append(i + 1).append(": ");
                                    result.append(Arrays.toString(kMeans.getCentroids().get(i))).append("\n");
                                }
                                result.append("\nCluster Assignments:\n");
                                for (int i = 0; i < dataset.size(); i++) {
                                    result.append("Data Point ").append(i + 1).append(": Cluster ");
                                    result.append(kMeans.getLabels().get(i) + 1).append("\n");
                                }

                                resultArea.setText(result.toString());
                            } catch (NumberFormatException ex) {
                                resultArea.setText("Error: Please enter valid numbers for K and iterations.");
                            } catch (IllegalArgumentException ex) {
                                resultArea.setText("Error: " + ex.getMessage());
                            }
                        }
                    });

                    // Exit Button Action
                    exitButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clusterFrame.dispose();
                        }
                    });

                    clusterFrame.setVisible(true);
                });
            }
        });
        
        //KNN CLASSIFIER--------------------------------------------------------------------------------------------------------------------------------
        knnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KNN knn = new KNN();

                // Create the JFrame window
                JFrame frame = new JFrame("KNN Model Selection");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(600, 400);

                // Panel for content
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                // Create the model selection dropdown
                String[] models = {
                    "Property Price Prediction",
                    "Property Type Classification",
                    "Region Name Prediction",
                    "Buyer Affordability Prediction",
                    "Property Suitability Classification"
                };
                JComboBox<String> modelComboBox = new JComboBox<>(models);
                panel.add(new JLabel("Select a Model to Run:"));
                panel.add(modelComboBox);

                // Create the input for the value of k (number of nearest neighbors)
                JPanel kPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                kPanel.add(new JLabel("Please type the value for k:"));
                JTextField kInputField = new JTextField(5); // Reduced width for smaller input field
                kPanel.add(kInputField);
                panel.add(kPanel);

               // Panel for dynamically displaying feature input fields
                JPanel featureInputPanel = new JPanel();
                featureInputPanel.setLayout(new BoxLayout(featureInputPanel, BoxLayout.Y_AXIS));

                // Set the label for feature input to be aligned to the left
                JLabel featureInputLabel = new JLabel("Enter feature values for prediction:");
                featureInputLabel.setAlignmentX(Component.LEFT_ALIGNMENT);  // Align label to the left
                panel.add(featureInputLabel);
                panel.add(featureInputPanel);
                // Button to run the selected model
                JButton runButton = new JButton("Run Model");
                panel.add(runButton);

                // Output area for displaying results after running the model
                JTextArea outputArea = new JTextArea(8, 30);
                outputArea.setEditable(false); 
                outputArea.setLineWrap(true);
                outputArea.setWrapStyleWord(true);
                JScrollPane scrollPane = new JScrollPane(outputArea); 
                panel.add(scrollPane);

                // Add panel to frame
                frame.add(panel);
                frame.setVisible(true);

                // Add action listener to the Model selection dropdown
                modelComboBox.addActionListener(e1 -> updateFeatureInputs(modelComboBox.getSelectedIndex(), featureInputPanel));

                // Add action listener to the Run button
                runButton.addActionListener(e1 -> {
                    try {
                        // Get model selection
                        int choice = modelComboBox.getSelectedIndex() + 1; // Index starts at 0, but we need to start from 1

                        // Fetch data for the selected model
                        List<Map<String, Object>> trainingData = fetchTrainingDataFromDB(choice);
                        List<Map<String, Object>> testData = fetchTestDataFromDB(choice);

                        if (trainingData.isEmpty() || testData.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "No data found for the selected model. Please check the database.");
                            return;
                        }

                        // Get the value for k
                        int k = Integer.parseInt(kInputField.getText());

                        if (k <= 0) {
                            JOptionPane.showMessageDialog(frame, "Invalid k value. k must be a positive integer.");
                            return;
                        }

                        // Train the KNN model with the chosen k value
                        knn.setK(k); // Update k in the KNN model
                        knn.fit(trainingData);

                        // Evaluate the model
                        double accuracy = knn.evaluate(testData);

                        // Get the feature values from the dynamically created user input fields
                        List<Double> inputFeatures = new ArrayList<>();
                        for (Component component : featureInputPanel.getComponents()) {
                            if (component instanceof JTextField) {
                                inputFeatures.add(Double.parseDouble(((JTextField) component).getText().trim()));
                            }
                        }

                        // Predict the label for the user input data point
                        List<String> userPrediction = (List<String>) knn.predict(Arrays.asList(inputFeatures.stream().mapToDouble(i -> i).toArray()));

                        // Display output (Evaluation and Prediction for user input)
                        StringBuilder output = new StringBuilder();
                        output.append("Model Accuracy: ").append(String.format("%.2f", accuracy * 100)).append("%\n\n");
                        output.append("Prediction for your input data: ").append(userPrediction.get(0)).append("\n");

                        outputArea.setText(output.toString()); // Set the updated text in the output area

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage());
                    }
                });
            }
        });
        
        
        // Display the window
        frame.setVisible(true);

}
    
    
//ALL HELPER METHODS ARE HERE   
    
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

    
    // Update the feature input fields based on the selected model
    private static void updateFeatureInputs(int modelIndex, JPanel featureInputPanel) {
        // Clear previous inputs
        featureInputPanel.removeAll();

        // Define the feature labels based on the selected model
        List<String> featureLabels = new ArrayList<>();
        switch (modelIndex) {
            case 0: // Property Price Prediction
                featureLabels.add("Number of Rooms");
                break;
            case 1: // Property Type Classification
                featureLabels.add("Number of Rooms");
                break;
            case 2: // Region Name Prediction
                featureLabels.add("Distance from City Center");
                break;
            case 3: // Buyer Affordability Prediction
                featureLabels.add("Buyer Age");
                break;
            case 4: // Property Suitability Classification
                featureLabels.add("Number of Rooms");
                break;
            default:
                break;
        }

        // Add text fields for each feature (smaller width)
        for (String label : featureLabels) {
            featureInputPanel.add(new JLabel(label));
            JTextField featureInputField = new JTextField(10); // Smaller width for the input field
            featureInputField.setPreferredSize(new Dimension(100, 25)); // Ensure smaller size
            featureInputPanel.add(featureInputField);
        }

        // Revalidate and repaint to update the UI
        featureInputPanel.revalidate();
        featureInputPanel.repaint();
    }

    // Fetch training data from the database based on the model for the KNN algo
    private static List<Map<String, Object>> fetchTrainingDataFromDB(int choice) {
        List<Map<String, Object>> data = new ArrayList<>();
        try {
            switch (choice) {
                case 1: // Property Price Prediction
                    List<Object> featuresPrice = DataExploration.fetchColumn("houses", "rooms");
                    List<Object> labelsPrice = DataExploration.fetchColumn("transactions", "price");
                    data = combineFeaturesAndLabels(featuresPrice, labelsPrice);
                    break;

                case 2: // Property Type Classification
                    List<Object> featuresType = DataExploration.fetchColumn("houses", "rooms");
                    List<Object> labelsType = DataExploration.fetchColumn("houses", "regionName");
                    data = combineFeaturesAndLabels(featuresType, labelsType);
                    break;

                case 3: // Region Name Prediction
                    List<Object> featuresRegion = DataExploration.fetchColumn("houses", "distance");
                    List<Object> labelsRegion = DataExploration.fetchColumn("houses", "regionName");
                    data = combineFeaturesAndLabels(featuresRegion, labelsRegion);
                    break;

                case 4: // Buyer Affordability Prediction
                    List<Object> featuresBuyer = DataExploration.fetchColumn("buyers", "age");
                    List<Object> labelsBuyer = DataExploration.fetchColumn("transactions", "price");
                    data = combineFeaturesAndLabels(featuresBuyer, labelsBuyer);
                    break;

                case 5: // Property Suitability Classification
                    List<Object> featuresSuitability = DataExploration.fetchColumn("houses", "rooms");
                    List<Object> labelsSuitability = DataExploration.fetchColumn("houses", "hasSwimmingPool");
                    data = combineFeaturesAndLabels(featuresSuitability, labelsSuitability);
                    break;

                default:
                    System.out.println("Invalid model choice.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
        return data;
    }

    // Fetch test data from the database (for simplicity, we reuse training data in this example) KNN
    private static List<Map<String, Object>> fetchTestDataFromDB(int choice) {
        return fetchTrainingDataFromDB(choice); // In a real-world scenario, fetch separate test data
    }

    // Combine features and labels into a training data format KNN
    private static List<Map<String, Object>> combineFeaturesAndLabels(List<Object> features, List<Object> labels) {
        List<Map<String, Object>> combinedData = new ArrayList<>();

        for (int i = 0; i < Math.min(features.size(), labels.size()); i++) {
            Map<String, Object> row = new HashMap<>();
            double[] featureArray = {Double.parseDouble(features.get(i).toString())}; // Assuming single feature for simplicity
            row.put("features", featureArray);
            row.put("label", labels.get(i).toString());
            combinedData.add(row);
        }

        return combinedData;
    }
    
    
    // VISUALIZATION HELPER METHODS
    // Helper methods for getting the columns (same as in your original code)
    private static List<String> getQualitativeColumnsForTable(String table) {
        List<String> columns = new ArrayList<>();
        switch (table) {
            case "Houses":
                columns.add("RegionName");
                columns.add("CouncilArea");
                columns.add("HasGarden");
                break;
            case "Units":
                columns.add("RegionName");
                columns.add("CouncilArea");
                columns.add("HasBalcony");
                break;
            // Add other cases as per your original logic
        }
        return columns;
    }

    private static List<String> getQuantitativeColumnsForTable(String table) {
        List<String> columns = new ArrayList<>();
        switch (table) {
            case "Houses":
            case "Units":
                columns.add("Rooms");
                columns.add("Bathrooms");
                columns.add("Landsize");
                break;
            // Add other cases as per your original logic
        }
        return columns;
    }

    // Helper methods for adding chart options
    private static void addBarChartOptions(JPanel optionsPanel) {
        List<String> tables = Arrays.asList("Houses", "Units", "Townhouses", "DevelopmentSite", "Transactions");
        JComboBox<String> tableComboBox = new JComboBox<>(tables.toArray(new String[0]));
        tableComboBox.setSelectedIndex(-1);
        tableComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBarChartColumns(tableComboBox.getSelectedItem().toString(), optionsPanel);
            }
        });

        optionsPanel.add(new JLabel("Choose Table for Bar Chart:"));
        optionsPanel.add(tableComboBox);
    }

    private static void updateBarChartColumns(String table, JPanel optionsPanel) {
        List<String> qualitativeColumns = getQualitativeColumnsForTable(table);
        List<String> quantitativeColumns = getQuantitativeColumnsForTable(table);

        JComboBox<String> xComboBox = new JComboBox<>(qualitativeColumns.toArray(new String[0]));
        JComboBox<String> yComboBox = new JComboBox<>(quantitativeColumns.toArray(new String[0]));

        // Clear previous options and add new ones
        optionsPanel.removeAll();
        optionsPanel.add(new JLabel("Select X Axis (Qualitative):"));
        optionsPanel.add(xComboBox);
        optionsPanel.add(new JLabel("Select Y Axis (Quantitative):"));
        optionsPanel.add(yComboBox);
        optionsPanel.revalidate();
        optionsPanel.repaint();
    }

    private static void addScatterPlotOptions(JPanel optionsPanel) {
        String[] scatterChoices = {"Price & Landsize", "Price & Distance", "Price & BuildingSize"};
        JComboBox<String> scatterChoiceComboBox = new JComboBox<>(scatterChoices);
        scatterChoiceComboBox.setSelectedIndex(-1);

        optionsPanel.add(new JLabel("Choose Scatter Plot Variables:"));
        optionsPanel.add(scatterChoiceComboBox);
        optionsPanel.revalidate();
        optionsPanel.repaint();
    }

    private static void addPieChartOptions(JPanel optionsPanel) {
        List<String> pieChartColumns = Arrays.asList("Gender", "HasElevator", "HasFence", "NumberofSharedWalls", "ParkingSpot");
        JComboBox<String> pieChartColumnComboBox = new JComboBox<>(pieChartColumns.toArray(new String[0]));
        pieChartColumnComboBox.setSelectedIndex(-1);

        optionsPanel.add(new JLabel("Choose Column for Pie Chart:"));
        optionsPanel.add(pieChartColumnComboBox);
        optionsPanel.revalidate();
        optionsPanel.repaint();
    }
    
    // Helper method to create styled buttons
    private static JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        return button;
    }
}