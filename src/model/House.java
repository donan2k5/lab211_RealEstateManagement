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

    public House() {
    }

    public House(int floorCount, int roomCount, String ID, String Name, String owner, double price, String address, double area) {
        super(ID, Name, owner, price, address, area);
        this.floorCount = floorCount;
        this.roomCount = roomCount;
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

    @Override
    public String toString() {
        return super.toString() + ", " + floorCount + ", " + roomCount;
    }
}