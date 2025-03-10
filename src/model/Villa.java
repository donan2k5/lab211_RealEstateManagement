
package model;


public class Villa extends House{
    private double poolArea;

    public Villa() {
    }

    public Villa(double poolArea, int floorCount, int roomCount, String ID, String Name, String owner, double price, String address, double area) {
        super(floorCount, roomCount, ID, Name, owner, price, address, area);
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