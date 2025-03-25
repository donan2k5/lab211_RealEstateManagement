/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author PC
 */
public class HousePredict {
    private double area;
    private int floors;
    private int bedrooms;
    private int bathrooms;
    private double frontage;
    private double roadWidth;
    private String address;
//    private double distanceToCenter;

    // Constructor
    public HousePredict(double area, int floors, int bedrooms, int bathrooms,
                        double frontage, double roadWidth, String address) {
        this.area = area;
        this.floors = floors;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.frontage = frontage;
        this.roadWidth = roadWidth;
        this.address = address;
//        this.distanceToCenter = estimateDistance(address); // You can improve this later
    }

    public double getArea() {
        return area;
    }

    public int getFloors() {
        return floors;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public double getFrontage() {
        return frontage;
    }

    public double getRoadWidth() {
        return roadWidth;
    }

    public String getAddress() {
        return address;
    }
    
}
