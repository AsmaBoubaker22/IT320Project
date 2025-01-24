package com.mycompany.oop;

public class Transaction {
    private String transactionId;
    private double amount;

    public Transaction(String transactionId, double amount) {
        this.transactionId = transactionId;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{transactionId='" + transactionId + "', amount=" + amount + "}";
    }
}
