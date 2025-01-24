/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
public class Transaction { 
    private int transactionId; 
    private double price;
    private String method;
    private int agentId;
    private int buyerId; 
    private int propertyId; 
    private String date;

    // Constructor
    public Transaction(int transactionId, double price, String method, int agentId, int buyerId, int propertyId, String date ) {
        this.transactionId = transactionId;
        this.price = price;
        this.method = method;
        this.agentId = agentId;
        this.buyerId = buyerId;
        this.propertyId = propertyId;
        this.date = date;
    }

    // Displays the transaction details like price, method, and IDs of the agent, buyer, and property
    public void displayTransactionDetails() {
        System.out.println("Transaction Details:");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Price: $" + price);
        System.out.println("Method: " + method);
        System.out.println("Agent ID: " + agentId);
        System.out.println("Buyer ID: " + buyerId);
        System.out.println("Property ID: " + propertyId);
        System.out.println("date: " + date);
    }

    //Getters
    public int getTransactionId() { 
        return transactionId; 
    }
    public double getPrice() { 
        return price; 
    }
    public String getMethod() { 
        return method; 
    }
    public int getAgentId() { 
        return agentId; 
    }
    public int getBuyerId() { 
        return buyerId; 
    }
    public int getPropertyIdId() { 
        return propertyId; 
    }
    public String getDate() { 
        return date; 
    }

    //Setters   
    public void setTransactionId(int transactionId) { 
        this.transactionId = transactionId; 
    }
    public void setPrice(double price) { 
        this.price = price; 
    }
    public void setMethod(String method) { 
        this.method = method; 
    }
    public void setAgentId(int agentId) { 
        this.agentId = agentId; 
    }
    public void setBuyerId(int buyerId) { 
        this.buyerId = buyerId; 
    }
    public void setPropertyId(int propertyId) { 
        this.propertyId = propertyId; 
    }
    public void setDate(String date) { 
        this.date = date; 
    }
    @Override
    public String toString() {
        return "[transactionId=" + transactionId + ", price=$" + price + ", method=" + method +
               ", agentId=" + agentId + ", buyerId=" + buyerId + ", propertyId=" + propertyId + ", date=" + date + "]";
    }
}
