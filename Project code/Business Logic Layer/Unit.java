/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
public class Unit extends ResidentialProperty { 
    private int floorLevel;
    private boolean hasBalcony;
    private boolean hasElevator;

    // Constructor
    public Unit(int propertyId, int landsize, Location location, int rooms, int bathrooms, int parkingSpot, int buildingSize, int yearBuilt, int floorLevel, boolean hasBalcony, boolean hasElevator) {
        super(propertyId, landsize, location, rooms, bathrooms, parkingSpot, buildingSize, yearBuilt);
        this.floorLevel = floorLevel;
        this.hasBalcony = hasBalcony;
        this.hasElevator = hasElevator;
    }

    @Override
    public String getPropertyType() {
        return "Unit";
    }

    public void displayFeatures() {
        System.out.println("Unit Features:");
        System.out.println("Floor Level: " + floorLevel);
        System.out.println("Balcony: " + (hasBalcony ? "Yes" : "No"));
        System.out.println("Elevator: " + (hasElevator ? "Yes" : "No"));
        super.displayResidentialDetails();
    }

    public int getFloorLevel() { 
        return floorLevel; 
    }
    public boolean hasBalcony() { 
        return hasBalcony; 
    }
    public boolean hasElevator() { 
        return hasElevator; 
    }

    public void setFloorLevel(int floorLevel) { 
        this.floorLevel = floorLevel; 
    }
    public void setHasBalcony(boolean hasBalcony) { 
        this.hasBalcony = hasBalcony; 
    }
    public void setHasElevator(boolean hasElevator) { 
        this.hasElevator = hasElevator; 
    }
    @Override
    public String toString() {
        return "[floorLevel=" + floorLevel + ", hasBalcony=" + hasBalcony  +
               ", hasElevator=" + hasElevator + "]";
    }
}
