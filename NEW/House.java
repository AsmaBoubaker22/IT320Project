/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
public class House extends ResidentialProperty { 
    private boolean hasGarden;
    private boolean hasSwimmingPool;
    private boolean hasFence;

    // Constructor
    public House(int propertyId, int landsize, Location location, int rooms, int bathrooms, int parkingSpot, int buildingSize, int yearBuilt, boolean hasGarden, boolean hasSwimmingPool, boolean hasFence) {
        super(propertyId, landsize, location, rooms, bathrooms, parkingSpot, buildingSize, yearBuilt);
        this.hasGarden = hasGarden;
        this.hasSwimmingPool = hasSwimmingPool;
        this.hasFence = hasFence;
    }

    @Override
    public String getPropertyType() {
        return "House";
    }
    
    public void displayFeatures() {
        System.out.println("House Features:");
        System.out.println("Garden: " + (hasGarden ? "Yes" : "No"));
        System.out.println("Swimming Pool: " + (hasSwimmingPool ? "Yes" : "No"));
        System.out.println("Fence: " + (hasFence ? "Yes" : "No"));
        super.displayResidentialDetails();
    }
    
    public boolean hasGarden() { 
        return hasGarden; 
    }
    public boolean hasSwimmingPool() { 
        return hasSwimmingPool; 
    }
    public boolean hasFence() { 
        return hasFence; 
    }

    public void setHasGarden(boolean hasGarden) { 
        this.hasGarden = hasGarden; 
    }
    public void setHasSwimmingPool(boolean hasSwimmingPool) { 
        this.hasSwimmingPool = hasSwimmingPool; 
    }
    public void setHasFence(boolean hasFence) { 
        this.hasFence = hasFence; 
    }
    @Override
    public String toString() {
        return "[ hasGarden=" + hasGarden + ", hasSwimmingPool=" + hasSwimmingPool + ", hasFence=" + hasFence + "]" ;
    }
}
