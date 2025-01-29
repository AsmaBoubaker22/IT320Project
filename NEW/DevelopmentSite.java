/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
public class DevelopmentSite extends NonResidentialProperty { 
    private boolean underConstruction;

    // Constructor
    public DevelopmentSite(int propertyId, int landsize, Location location, boolean isLandCleared, boolean underConstruction) {
        super(propertyId, landsize, location, isLandCleared);
        this.underConstruction = underConstruction;
    }

    @Override
    public String getPropertyType() {
        return "DevelopmentSite";
    }
    
    public void displayFeatures() {
        System.out.println("Development Site Features:");
        System.out.println("Under Construction: " + (underConstruction ? "Yes" : "No"));
        super.displayDetails();  
    }

    public boolean isUnderConstruction() { 
        return underConstruction; 
    }

    public void setUnderConstruction(boolean underConstruction) { 
        this.underConstruction = underConstruction; 
    }
    @Override
    public String toString() {
        return "[underConstruction=" + underConstruction  +  "]";
    }
}
