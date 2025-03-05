/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class RealEstate {
    private String ID;
    private String name;
    private String owner;
    private double price;
    private String address;
    private double area;

    public RealEstate() {
    }

    public RealEstate(String ID, String Name, String owner, double price, String address, double area) {
        this.ID = ID;
        this.name = Name;
        this.owner = owner;
        this.price = price;
        this.address = address;
        this.area = area;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

   

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
    @Override
    public String toString() {
        return ID + ", " + name + ", " + owner + ", " + address + ", " + price + ", " + area;
    }
}
