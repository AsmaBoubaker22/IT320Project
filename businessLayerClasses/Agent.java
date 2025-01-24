/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
import java.util.List;
import java.util.ArrayList;
public class Agent { 
    private int agentId;
    private String agentName;
    private List<Property> properties;

    // Constructor
    public Agent(int agentId, String agentName) {
        this.agentId = agentId;
        this.agentName = agentName;
        this.properties = new ArrayList<>(); // Initialize the list to hold properties
    }
    
    // Displays the agent's name and ID
    public void displayAgentDetails() {
        System.out.println("Agent Details:");
        System.out.println("Agent ID: " + agentId);
        System.out.println("Agent Name: " + agentName);
    }
    
    // Adds a property to the agent's list of managed properties
    public void addProperty(Property property) { 
        properties.add(property); 
        System.out.println("Property with ID " + property.getpropertyId() + " added to the agent's list.");
    }
    
    public void removeProperty(int propertyId) { 
        Property propertyToRemove = null;
        for (Property property : properties) {
            if (property.getpropertyId() == propertyId) {
                propertyToRemove = property;
                break;
            }
        }
        if (propertyToRemove != null) {
            properties.remove(propertyToRemove);
            System.out.println("Property with ID " + propertyId + " removed from the agent's list.");
        } else {
            System.out.println("Property with ID " + propertyId + " not found.");
        }
    }
    
    public List<Transaction> getTransactions(List<Transaction> allTransactions) { 
        List<Transaction> agentTransactions = new ArrayList<>();
        for (Transaction transaction : allTransactions) {
            if (transaction.getAgentId() == this.agentId) {
                agentTransactions.add(transaction);
            }
        }
        return agentTransactions;
    }

    public int getAgentId() { 
        return agentId; 
    }
    public String getAgentName() { 
        return agentName; 
    }

    public void setAgentId(int agentId) { 
        this.agentId = agentId; 
    }
    public void setAgentName(String agentName) { 
        this.agentName = agentName; 
    }
    @Override
    public String toString() {
        return "[agentId=" + agentId + ", agentName=" + agentName + "]";
    }
}
