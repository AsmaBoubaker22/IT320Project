package com.mycompany.main;

public class House extends ResidentialProperty {

    private String address;              // matches house.address in MySQL
    private boolean hasGarden;
    private boolean hasSwimmingPool;
    private boolean hasFence;

    // Constructor
    public House(
            int propertyId,
            int landsize,
            Location location,
            int rooms,
            int bathrooms,
            int parkingSpot,
            int buildingSize,
            int yearBuilt,
            boolean hasGarden,
            boolean hasSwimmingPool,
            boolean hasFence) {
        super(propertyId, landsize, location, rooms, bathrooms, parkingSpot, buildingSize, yearBuilt);
        // copy address from location, if set
        this.address = (location != null) ? location.getAddress() : null;
        this.hasGarden = hasGarden;
        this.hasSwimmingPool = hasSwimmingPool;
        this.hasFence = hasFence;
    }

    // If ResidentialProperty has an abstract getPropertyType(), this overrides it:
    @Override
    public String getPropertyType() {
        return "House";
    }

    // GET/SET address
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // GET/SET for booleans
    public boolean hasGarden() { return hasGarden; }
    public void setHasGarden(boolean hasGarden) { this.hasGarden = hasGarden; }

    public boolean hasSwimmingPool() { return hasSwimmingPool; }
    public void setHasSwimmingPool(boolean hasSwimmingPool) {
        this.hasSwimmingPool = hasSwimmingPool;
    }

    public boolean hasFence() { return hasFence; }
    public void setHasFence(boolean hasFence) { this.hasFence = hasFence; }

    // This is NOT overriding anything in the parent, so remove @Override
    public void displayFeatures() {
        System.out.println("House Features:");
        System.out.println("Address: " + (address == null ? "N/A" : address));
        System.out.println("Garden: " + (hasGarden ? "Yes" : "No"));
        System.out.println("Swimming Pool: " + (hasSwimmingPool ? "Yes" : "No"));
        System.out.println("Fence: " + (hasFence ? "Yes" : "No"));
        super.displayResidentialDetails(); // presumably in the parent
    }

    // Override toString() from Object
    @Override
    public String toString() {
        return "House [address=" + address
                + ", hasGarden=" + hasGarden
                + ", hasSwimmingPool=" + hasSwimmingPool
                + ", hasFence=" + hasFence + "]";
    }
}
