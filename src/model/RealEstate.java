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
    private int ownerId;
    private double price;
    private String street;
    private String ward;
    private String district;
    private String city;
    private double area;

    public RealEstate() {
    }

    public RealEstate(String ID, String name, int ownerId, double price, String street, String ward, String district, String city, double area) {
        this.ID = ID;
        this.name = name;
        this.ownerId = ownerId;
        this.price = price;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.area = area;
    }

    public RealEstate(String name, int ownerId, double price, String street, String ward, String district, String city, double area) {
        this.name = name;
        this.ownerId = ownerId;
        this.price = price;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.area = area;
    }

    public int getOwner() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return ID + ", " + name + ", " + ownerId + ", " + street + ", " + ward + ", " + district + ", " + city + ", " + price + ", " + area;
    }
}
