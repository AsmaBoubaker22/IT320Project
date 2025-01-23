/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
public class Townhouse extends ResidentialProperty { 
    private int numberOfSharedWalls;
    private int numberOfLevels;

    // Constructor
    public Townhouse(int propertyId, int landsize, Location location, int rooms, int bathrooms, int parkingSpot, int buildingSize, int yearBuilt, int numberOfSharedWalls, int numberOfLevels) {
        super(propertyId, landsize, location, rooms, bathrooms, parkingSpot, buildingSize, yearBuilt);
        this.numberOfSharedWalls = numberOfSharedWalls;
        this.numberOfLevels = numberOfLevels;
    }

    @Override
    public String getPropertyType() {
        return "TownHouse";
    }

    public void displayFeatures() {
        System.out.println("Townhouse Features:");
        System.out.println("Number of Shared Walls: " + numberOfSharedWalls);
        System.out.println("Number of Levels: " + numberOfLevels);
        super.displayResidentialDetails();
    }

    public int getNumberOfSharedWalls() { 
        return numberOfSharedWalls; 
    } 
    public int getNumberOfLevels() { 
        return numberOfLevels; 
    }

    public void setNumberOfSharedWalls(int numberOfSharedWalls) { 
        this.numberOfSharedWalls = numberOfSharedWalls; 
    }
    public void setNumberOfLevels(int numberOfLevels) { 
        this.numberOfLevels = numberOfLevels; 
    }
}
