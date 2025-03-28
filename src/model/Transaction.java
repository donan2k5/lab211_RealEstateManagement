package model;

import java.time.LocalDate;

public class Transaction {

    private int transID;
    private int REID;
    private int sellerID;
    private int buyerID;
    private double price;
    private double deposit;
    private LocalDate expirationTime;
    private String status;
    private boolean isDeleted;
    private LocalDate createTime;
    private LocalDate updateTime;

    public Transaction(int REID, int sellerID, int buyerID, double price, double deposit, LocalDate expirationTime) {
        this.REID = REID;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.price = price;
        this.deposit = deposit;
        this.expirationTime = expirationTime;
    }

    public Transaction(int transID, int REID, int sellerID, int buyerID, double price, double deposit, LocalDate expirationTime, String status, boolean isDeleted, LocalDate createTime, LocalDate updateTime) {
        this.transID = transID;
        this.REID = REID;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.price = price;
        this.deposit = deposit;
        this.expirationTime = expirationTime;
        this.status = status;
        this.isDeleted = isDeleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Transaction() {
    }

    public int getTransID() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public int getREID() {
        return REID;
    }

    public void setREID(int REID) {
        this.REID = REID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public LocalDate getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDate expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return transID + "," + REID + "," + sellerID + "," + buyerID + "," + price + "," + deposit + "," + expirationTime + "," + status + "," + createTime + "," + updateTime;
    }

}