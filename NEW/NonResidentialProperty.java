/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
public abstract class NonResidentialProperty extends Property { 
    private boolean isLandCleared;
    
    // Constructor
    public NonResidentialProperty(int propertyId, int landsize, Location location, boolean isLandCleared) {
        super(propertyId, landsize, location);
        this.isLandCleared = isLandCleared;
    }
    public void displayDetails() {
        System.out.println("Non-Residential Property Details:");
        super.displayBasicDetails();
        System.out.println("Land Cleared: " + (isLandCleared ? "Yes" : "No"));
    }
    public boolean isLandCleared() {
        return isLandCleared;
    }
    public void setIsLandCleared(boolean isLandCleared) {
	this.isLandCleared = isLandCleared;
    }
} 

