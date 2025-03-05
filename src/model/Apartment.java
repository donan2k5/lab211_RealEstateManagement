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

    public Apartment(String additionalService, int floorCount, int roomCount, String ID, String Name, String owner, double price, String address, double area) {
        super(floorCount, roomCount, ID, Name, owner, price, address, area);
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
