/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class Land extends RealEstate{
    private String landType;

    public Land() {
    }

    public Land(String landType, String ID, String Name, String owner, double price, String address, double area) {
        super(ID, Name, owner, price, address, area);
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
