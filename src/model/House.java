/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class House extends RealEstate {

    private int floorCount;
    private int roomCount;
    private boolean isHaveDiningroom;
    private boolean isHaveKitchen;
    private boolean isHaveTerrace;
    private boolean isHaveCarPark;

    public House() {
    }

    public House(int floorCount, int roomCount, boolean isHaveDiningroom, boolean isHaveKitchen, boolean isHaveTerrace, boolean isHaveCarPark, String ID, String name, int ownerId, double price, String street, String ward, String district, String city, double area) {
        super(ID, name, ownerId, price, street, ward, district, city, area);
        this.floorCount = floorCount;
        this.roomCount = roomCount;
        this.isHaveDiningroom = isHaveDiningroom;
        this.isHaveKitchen = isHaveKitchen;
        this.isHaveTerrace = isHaveTerrace;
        this.isHaveCarPark = isHaveCarPark;
    }

    public House(int floorCount, int roomCount, boolean isHaveDiningroom, boolean isHaveKitchen, boolean isHaveTerrace, boolean isHaveCarPark, String name, int ownerId, double price, String street, String ward, String district, String city, double area) {
        super(name, ownerId, price, street, ward, district, city, area);
        this.floorCount = floorCount;
        this.roomCount = roomCount;
        this.isHaveDiningroom = isHaveDiningroom;
        this.isHaveKitchen = isHaveKitchen;
        this.isHaveTerrace = isHaveTerrace;
        this.isHaveCarPark = isHaveCarPark;
    }

    public House(int floorCount, int roomCount, boolean isHaveDiningroom, boolean isHaveKitchen, boolean isHaveTerrace, boolean isHaveCarPark, String name, double price, String street, String ward, String district, String city, double area) {
        super(name, price, street, ward, district, city, area);
        this.floorCount = floorCount;
        this.roomCount = roomCount;
        this.isHaveDiningroom = isHaveDiningroom;
        this.isHaveKitchen = isHaveKitchen;
        this.isHaveTerrace = isHaveTerrace;
        this.isHaveCarPark = isHaveCarPark;
    }

    public boolean isIsHaveDiningroom() {
        return isHaveDiningroom;
    }

    public void setIsHaveDiningroom(boolean isHaveDiningroom) {
        this.isHaveDiningroom = isHaveDiningroom;
    }

    public boolean isIsHaveKitchen() {
        return isHaveKitchen;
    }

    public void setIsHaveKitchen(boolean isHaveKitchen) {
        this.isHaveKitchen = isHaveKitchen;
    }

    public boolean isIsHaveTerrace() {
        return isHaveTerrace;
    }

    public void setIsHaveTerrace(boolean isHaveTerrace) {
        this.isHaveTerrace = isHaveTerrace;
    }

    public boolean isIsHaveCarPark() {
        return isHaveCarPark;
    }

    public void setIsHaveCarPark(boolean isHaveCarPark) {
        this.isHaveCarPark = isHaveCarPark;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

}
