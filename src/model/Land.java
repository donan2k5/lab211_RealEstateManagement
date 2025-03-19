/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class Land extends RealEstate {

    private String landType;

    public Land() {
    }

    public Land(String landType, String ID, String name, int ownerId, double price, String street, String ward, String district, String city, double area) {
        super(ID, name, ownerId, price, street, ward, district, city, area);
        this.landType = landType;
    }

    public Land(String landType, String name, int ownerId, double price, String street, String ward, String district, String city, double area) {
        super(name, ownerId, price, street, ward, district, city, area);
        this.landType = landType;
    }

    public Land(String landType, String name, double price, String street, String ward, String district, String city, double area) {
        super(name, price, street, ward, district, city, area);
        this.landType = landType;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + landType;
    }

}
