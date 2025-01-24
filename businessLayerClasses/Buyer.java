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

public class Buyer { 
    private int buyerId;
    private String buyerName;
    private char gender;
    private int age;

    // Constructor
    public Buyer(int buyerId, String buyerName, char gender, int age) {
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.gender = gender;
        this.age = age;
    }

    // Displays the buyer's name, age, and gender
    public void displayBuyerDetails() {
        System.out.println("Buyer Details:");
        System.out.println("Buyer ID: " + buyerId);
        System.out.println("Name: " + buyerName);
        System.out.println("Gender: " + gender);
        System.out.println("Age: " + age);
    }
    
    // Returns a list of transactions for this buyer from a provided list of transactions
    public List<Transaction> viewPurchaseHistory(List<Transaction> allTransactions) {
        List<Transaction> buyerTransactions = new ArrayList<>();
        for (Transaction transaction : allTransactions) {
            if (transaction.getBuyerId() == this.buyerId) {
                buyerTransactions.add(transaction);
            }
        }
        return buyerTransactions;
    }
    
    public double calculateTotalSpent(List<Transaction> allTransactions) {
        double totalSpent = 0.0;
        for (Transaction transaction : allTransactions) {
            if (transaction.getBuyerId() == this.buyerId) {
                totalSpent += transaction.getPrice();
            }
        }
        return totalSpent;
    }
    
    public int getAgeGroup() {
        if (age <= 12) {
            return 1; // Child
        } else if (age >= 13 && age <= 19) {
            return 2; // Young Adult
        } else if (age >= 20 && age <= 64) {
            return 3; // Adult
        } else {
            return 4; // Senior
        }
    }

    //Getters
    public int getBuyerId() {
        return buyerId;
    }
    public String getBuyerName() {
        return buyerName;
    }
    public char getGender() {
        return gender;
    }
    public int getAge() {
        return age;
    }

    //Setters
    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

