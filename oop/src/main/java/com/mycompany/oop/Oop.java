package com.mycompany.oop;

import java.util.List;

public class Oop {
    public static void main(String[] args) {
        CSVIngestion ingestion = new CSVIngestion();

        // Base file path for all CSV files
        String baseFilePath = "\"C:\\Users\\seifb\\Desktop\\data\\";
        // Import and print Agents
        String agentsFilePath = baseFilePath + "agents.csv.txt";
        List<Agent> agents = ingestion.csvImport(agentsFilePath, Agent.class);
        System.out.println("Agents:");
        for (Agent agent : agents) {
            System.out.println(agent);
        }

        // Import and print Buyers
        String buyersFilePath = baseFilePath + "buyers.csv.txt";
        List<Buyer> buyers = ingestion.csvImport(buyersFilePath, Buyer.class);
        System.out.println("\nBuyers:");
        for (Buyer buyer : buyers) {
            System.out.println(buyer);
        }

        // Import and print Development Sites
        String developmentSitesFilePath = baseFilePath + "development_sites.csv.txt";
        List<DevelopmentSite> developmentSites = ingestion.csvImport(developmentSitesFilePath, DevelopmentSite.class);
        System.out.println("\nDevelopment Sites:");
        for (DevelopmentSite site : developmentSites) {
            System.out.println(site);
        }

        // Import and print Houses
        String housesFilePath = baseFilePath + "houses.csv.txt";
        List<House> houses = ingestion.csvImport(housesFilePath, House.class);
        System.out.println("\nHouses:");
        for (House house : houses) {
            System.out.println(house);
        }

        // Import and print Locations
        String locationsFilePath = baseFilePath + "locations.csv.txt";
        List<Location> locations = ingestion.csvImport(locationsFilePath, Location.class);
        System.out.println("\nLocations:");
        for (Location location : locations) {
            System.out.println(location);
        }

        // Import and print Non-Residential Properties
        String nonResidentialPropertiesFilePath = baseFilePath + "non_residential_properties.csv.txt";
        List<NonResidentialProperty> nonResidentialProperties = ingestion.csvImport(nonResidentialPropertiesFilePath, NonResidentialProperty.class);
        System.out.println("\nNon-Residential Properties:");
        for (NonResidentialProperty property : nonResidentialProperties) {
            System.out.println(property);
        }

        // Import and print Properties
        String propertiesFilePath = baseFilePath + "properties.csv.txt";
        List<Property> properties = ingestion.csvImport(propertiesFilePath, Property.class);
        System.out.println("\nProperties:");
        for (Property property : properties) {
            System.out.println(property);
        }

        // Import and print Residential Properties
        String residentialPropertiesFilePath = baseFilePath + "residential_properties.csv.txt";
        List<ResidentialProperty> residentialProperties = ingestion.csvImport(residentialPropertiesFilePath, ResidentialProperty.class);
        System.out.println("\nResidential Properties:");
        for (ResidentialProperty property : residentialProperties) {
            System.out.println(property);
        }

        // Import and print Townhouses
        String townhousesFilePath = baseFilePath + "townhouses.csv.txt";
        List<Townhouse> townhouses = ingestion.csvImport(townhousesFilePath, Townhouse.class);
        System.out.println("\nTownhouses:");
        for (Townhouse townhouse : townhouses) {
            System.out.println(townhouse);
        }

        // Import and print Transactions
        String transactionsFilePath = baseFilePath + "transactions.csv.txt";
        List<Transaction> transactions = ingestion.csvImport(transactionsFilePath, Transaction.class);
        System.out.println("\nTransactions:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        // Import and print Units
        String unitsFilePath = baseFilePath + "units.csv.txt";
        List<Unit> units = ingestion.csvImport(unitsFilePath, Unit.class);
        System.out.println("\nUnits:");
        for (Unit unit : units) {
            System.out.println(unit);
        }
    }
}
