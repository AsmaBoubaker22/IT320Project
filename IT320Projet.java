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

public class IT320Projet {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        // Create an instance of CSVIngestion
        CSVIngestion csvIngestion = new CSVIngestion();
        
        // Define the file path of the CSV file you want to import
        String filePath = "C:\\Users\\Asma\\Desktop\\SampleData.csv"; // Replace with actual CSV file path
        
        // Import the CSV file and store the data in a list of rows
        List<List<String>> data = csvIngestion.csvImport(filePath);
        
        // Now, inject the data into the respective objects
        csvIngestion.csvInject(data);
        
        System.out.println("Buyers List: " + csvIngestion.buyersList);
        System.out.println("Agents List: " + csvIngestion.agentsList);
        System.out.println("Houses List: " + csvIngestion.housesList);
        System.out.println("Units List: " + csvIngestion.unitsList);
        System.out.println("Townhouses List: " + csvIngestion.townhousesList);
        System.out.println("Development Sites List: " + csvIngestion.developmentSitesList);
        System.out.println("Transactions List: " + csvIngestion.transactionsList);
    
        
    }
}
