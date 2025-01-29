/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
public abstract class Property { 
    private int propertyId; 
    private int landsize;
    private Location location;
    
    // Constructor
    public Property(int propertyId, int landsize, Location location) {
        this.propertyId = propertyId;
        this.landsize = landsize;
        this.location = location;
    }
    
    public void displayBasicDetails() {
        System.out.println("Property ID: " + propertyId);
        System.out.println("Land Size: " + landsize + " sqm");
        if (location != null) {
            location.displayLocationDetails();
        } else {
            System.out.println("Location details not available.");
        }
    }
    
    public abstract String getPropertyType();
    
    public boolean isLargeProperty(double sizeThreshold) {
        return landsize > sizeThreshold;
    }

    public int getLandsize() {
        return landsize; 
    }

    public int getpropertyId() {
        return propertyId; 
    }
    
    public Location getLocation() { 
        return location; 
    }

    public void setPropertyId(int propertyId) { 
        this.propertyId = propertyId; 
    }
    
    public void setLandsize(int landsize) { 
        if (landsize > 0) { 
            this.landsize = landsize; } 
        else { 
            System.out.println("Land size must be greater than zero."); } 
        }
    
    public void setLocation(Location location) { 
        this.location = location; 
    }
}
