/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
public class Location { 
    private String regionName;
    private String address;
    private double latitude;
    private double longitude;
    private double distance;
    private int postcode;
    private String councilArea;
    private int propertyCount;
    
    // Default constructor
    public Location() {
        this.regionName = "";
        this.address = "";
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.distance = 0.0;
        this.postcode = 0;
        this.councilArea = "";
        this.propertyCount = 0;
    }

    // Parameterized constructor
    public Location(String regionName, String address, double latitude, double longitude, 
                    double distance, int postcode, String councilArea, int propertyCount) {
        this.regionName = regionName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.postcode = postcode;
        this.councilArea = councilArea;
        this.propertyCount = propertyCount;
    }


    public void displayLocationDetails() {
        System.out.println("Location Details:");
        System.out.println("Region Name: " + (regionName != null ? regionName : "N/A"));
        System.out.println("Council Area: " + (councilArea != null ? councilArea : "N/A"));
        System.out.println("Postcode: " + postcode);
        System.out.println("Address: " + (address != null ? address : "N/A"));
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Property Count: " + propertyCount);
    }

    //Getters: Methods to retrieve location attributes
    public String getRegionName() { 
        return regionName; 
    }
    public String getAddress() { 
        return address; 
    }
    public double getLatitude() { 
        return latitude; 
    }
    public double getLongitude() { 
        return longitude; 
    }
    public double getDistance() { 
        return distance; 
    }
    public int getPostcode() { 
        return postcode; 
    }
    public String getCouncilArea() { 
        return councilArea; 
    }
    public int getPropertyCount() { 
        return propertyCount; 
    }

    //Setters: Methods to update the location attributes
    public void setRegionName(String regionName) { 
        this.regionName = regionName; 
    }
    public void setAddress(String address) { 
        this.address = address; 
    }
    public void setLatitude(double latitude) { 
        this.latitude = latitude; 
    }
    public void setLongitude(double longitude) { 
        this.longitude = longitude; 
    }
    public void setDistance(double distance) { 
        this.distance = distance; 
    }
    public void setPostcode(int postcode) { 
        this.postcode = postcode; 
    }
    public void setCouncilArea(String councilArea) { 
        this.councilArea = councilArea; 
    }
    public void setPropertyCount(int propertyCount) { 
        this.propertyCount = propertyCount; 
    }
    @Override
    public String toString() {
        return "[regionName=" + regionName + ", address=" + address + ", latitude=" + latitude + 
               ", longitude=" + longitude + ", distance=" + distance + ", postcode=" + postcode + 
               ", councilArea=" + councilArea + ", propertyCount=" + propertyCount + "]";
    }
} 
