package model;

/**
 *
 * @author DELL
 */
public class Villa extends House {

    private double poolArea;

    public Villa() {
    }

    public Villa(double poolArea, int floorCount, int roomCount, boolean isHaveDiningroom, boolean isHaveKitchen, boolean isHaveTerrace, boolean isHaveCarPark, String ID, String name, int ownerId, double price, String street, String ward, String district, String city, double area) {
        super(floorCount, roomCount, isHaveDiningroom, isHaveKitchen, isHaveTerrace, isHaveCarPark, ID, name, ownerId, price, street, ward, district, city, area);
        this.poolArea = poolArea;
    }

    public Villa(double poolArea, int floorCount, int roomCount, boolean isHaveDiningroom, boolean isHaveKitchen, boolean isHaveTerrace, boolean isHaveCarPark, String name, int ownerId, double price, String street, String ward, String district, String city, double area) {
        super(floorCount, roomCount, isHaveDiningroom, isHaveKitchen, isHaveTerrace, isHaveCarPark, name, ownerId, price, street, ward, district, city, area);
        this.poolArea = poolArea;
    }

    public Villa(double poolArea, int floorCount, int roomCount, boolean isHaveDiningroom, boolean isHaveKitchen, boolean isHaveTerrace, boolean isHaveCarPark, String name, double price, String street, String ward, String district, String city, double area) {
        super(floorCount, roomCount, isHaveDiningroom, isHaveKitchen, isHaveTerrace, isHaveCarPark, name, price, street, ward, district, city, area);
        this.poolArea = poolArea;
    }

    public double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(double poolArea) {
        this.poolArea = poolArea;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + poolArea;
    }
}
