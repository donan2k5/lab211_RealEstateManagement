/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class Apartment extends House {

    private String additionalService;

    public Apartment() {
    }

    public Apartment(String additionalService, int floorCount, int roomCount, boolean isHaveDiningroom, boolean isHaveKitchen, boolean isHaveTerrace, boolean isHaveCarPark, String ID, String name, int ownerId, double price, String street, String ward, String district, String city, double area) {
        super(floorCount, roomCount, isHaveDiningroom, isHaveKitchen, isHaveTerrace, isHaveCarPark, ID, name, ownerId, price, street, ward, district, city, area);
        this.additionalService = additionalService;
    }

    public Apartment(String additionalService, int floorCount, int roomCount, boolean isHaveDiningroom, boolean isHaveKitchen, boolean isHaveTerrace, boolean isHaveCarPark, String name, int ownerId, double price, String street, String ward, String district, String city, double area) {
        super(floorCount, roomCount, isHaveDiningroom, isHaveKitchen, isHaveTerrace, isHaveCarPark, name, ownerId, price, street, ward, district, city, area);
        this.additionalService = additionalService;
    }

    public Apartment(String additionalService, int floorCount, int roomCount, boolean isHaveDiningroom, boolean isHaveKitchen, boolean isHaveTerrace, boolean isHaveCarPark, String name, double price, String street, String ward, String district, String city, double area) {
        super(floorCount, roomCount, isHaveDiningroom, isHaveKitchen, isHaveTerrace, isHaveCarPark, name, price, street, ward, district, city, area);
        this.additionalService = additionalService;
    }

    public String getAdditionalService() {
        return additionalService;
    }

    public void setAdditionalService(String additionalService) {
        this.additionalService = additionalService;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + additionalService;
    }
}
