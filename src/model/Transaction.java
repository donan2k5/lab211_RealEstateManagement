package model;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private int buyerId;
    private int sellerId;
    private double price;
    private String status; // ví dụ: "completed", "pending", v.v.
    private LocalDateTime createdAt;

    public Transaction() { }

    public Transaction(int transactionId, int buyerId, int sellerId, double price, String status, LocalDateTime createdAt) {
        this.transactionId = transactionId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public int getBuyerId() {
        return buyerId;
    }
    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }
    public int getSellerId() {
        return sellerId;
    }
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
