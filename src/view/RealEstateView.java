/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Apartment;
import model.House;
import model.Land;
import model.RealEstate;
import model.Villa;
import repository.impl.RealEstateRepository;

/**
 *
 * @author DELL
 */
public class RealEstateView implements IRealEstateView {

    private RealEstateRepository reRepo = new RealEstateRepository();
    private Validation v = new Validation();

    {
        reRepo.readData();
    }

    @Override
    public void displayListHouse(List<House> houseList) {
        if (houseList.isEmpty()) {
            System.out.println("Not exist any house in system.");
            return;
        }
        System.out.println("House list:");
        System.out.printf("%-10s%-20s%-15s%-25s%-15s%-15s%-15s%-10s\n",
                "Id", "Name", "Owner", "Address",
                "Price", "Area", "FloorCount", "RoomCount");
        for (House house : houseList) {
            System.out.printf("%-10s%-20s%-15s%-25s%-15s%-15s%-15s%-10s\n",
                    house.getID(), house.getName(), house.getOwner(), house.getAddress(),
                    house.getPrice(), house.getArea(), house.getFloorCount(), house.getRoomCount());
        }
    }

    @Override
    public void displayListLand(List<Land> landList) {
        if (landList.isEmpty()) {
            System.out.println("Not exist any land in system.");
            return;
        }
        System.out.println("Land list:");
        System.out.printf("%-10s%-20s%-15s%-25s%-15s%-15s%-15s\n",
                "Id", "Name", "Owner", "Address",
                "Price", "Area", "LandType");
        for (Land land : landList) {
            System.out.printf("%-10s%-20s%-15s%-25s%-15s%-15s%-15s\n",
                    land.getID(), land.getName(), land.getOwner(), land.getAddress(),
                    land.getPrice(), land.getArea(), land.getLandType());
        }
    }

    @Override
    public void displayListVilla(List<Villa> villaList) {
        if (villaList.isEmpty()) {
            System.out.println("Not exist any villa in system.");
            return;
        }
        System.out.println("Villa list:");
        System.out.printf("%-10s%-20s%-15s%-25s%-15s%-15s%-15s%-15s%-15s\n",
                "Id", "Name", "Owner", "Address",
                "Price", "Area", "FloorCount", "RoomCount", "PoolArea");
        for (Villa villa : villaList) {
            System.out.printf("%-10s%-20s%-15s%-25s%-15s%-15s%-15s%-15s%-15s\n",
                    villa.getID(), villa.getName(), villa.getOwner(), villa.getAddress(),
                    villa.getPrice(), villa.getArea(), villa.getFloorCount(), villa.getRoomCount(), villa.getPoolArea());
        }
    }

    @Override
    public void displayListApartment(List<Apartment> apartmentList) {
        if (apartmentList.isEmpty()) {
            System.out.println("Not exist any apartment in system.");
            return;
        }
        System.out.println("Apartment list:");
        System.out.printf("%-10s%-20s%-15s%-25s%-15s%-15s%-15s%-15s%-15s\n",
                "Id", "Name", "Owner", "Address",
                "Price", "Area", "FloorCount", "RoomCount", "AdditionalService");
        for (Apartment apartment : apartmentList) {
            System.out.printf("%-10s%-20s%-15s%-25s%-15s%-15s%-15s%-15s%-15s\n",
                    apartment.getID(), apartment.getName(), apartment.getOwner(), apartment.getAddress(),
                    apartment.getPrice(), apartment.getArea(), apartment.getFloorCount(), apartment.getRoomCount(), apartment.getAdditionalService());
        }
    }

    public Land getInformationLand() {
        String id = "";
        while (true) {
            id = v.getStringRegex("Enter house id: ", "^L-\\d{4}$", "Invalid input, enter again: ");
            if (reRepo.findEstateById(id) != null) {
                System.out.println("This land already exist in system.");
                continue;
            }
            break;
        }
        String name = v.getStringRegex("Enter name of house: ", "^.*$", "Invalid input, enter again: ");
        String owner = v.getStringRegex("Enter name of owner: ", "^.*$", "Invalid input, enter again: ");
        double price = v.getValidDouble("Enter price of the land: ");
        String address = v.getStringRegex("Enter address of land: ", "^.*$", "Invalid input, enter again: ");
        double area = v.getValidDouble("Enter area of the land: ");
        String landType = v.getStringRegex("Enter type of land: ", "^.*$", "Invalid input, enter again: ");
        return new Land(landType, id, name, owner, price, address, area);
    }

    public House getInformationHouse() {
        String houseId = "";
        while (true) {
            houseId = v.getStringRegex("Enter house id: ", "^H-\\d{4}$", "Invalid input, enter again: ");
            if (reRepo.findEstateById(houseId) != null) {
                System.out.println("This house already exist in system.");
                continue;
            }
            break;
        }
        String houseName = v.getStringRegex("Enter name of house: ", "^.*$", "Invalid input, enter again: ");
        String owner = v.getStringRegex("Enter name of owner: ", "^.*$", "Invalid input, enter again: ");
        double price = v.getValidDouble("Enter price of the house: ");
        String address = v.getStringRegex("Enter address of house: ", "^.*$", "Invalid input, enter again: ");
        double area = v.getValidDouble("Enter area of the house: ");
        int floorCount = v.getValidInteger("Enter number of floor: ");
        int roomCoount = v.getValidInteger("Enter number of room: ");
        return new House(floorCount, roomCoount, houseId, houseName, owner, price, address, area);
    }

    public Apartment getInformationApartment() {
        String id = "";
        while (true) {
            id = v.getStringRegex("Enter apartment id: ", "^APT-\\d{4}$", "Invalid input, enter again: ");
            if (reRepo.findEstateById(id) != null) {
                System.out.println("This apartment already exist in system.");
                continue;
            }
            break;
        }
        String name = v.getStringRegex("Enter name of apartment: ", "^.*$", "Invalid input, enter again: ");
        String owner = v.getStringRegex("Enter name of owner: ", "^.*$", "Invalid input, enter again: ");
        double price = v.getValidDouble("Enter price of the apartment: ");
        String address = v.getStringRegex("Enter address of apartment: ", "^.*$", "Invalid input, enter again: ");
        double area = v.getValidDouble("Enter area of the apartment: ");
        int floorCount = v.getValidInteger("Enter number of floor: ");
        int roomCoount = v.getValidInteger("Enter number of room: ");
        String additionalService = v.getStringRegex("Enter service: ", "^.*$", "Invalid input, enter again: ");
        return new Apartment(additionalService, floorCount, roomCoount, id, name, owner, price, address, area);
    }

    public Villa getInformationVilla() {
        String id = "";
        while (true) {
            id = v.getStringRegex("Enter villa id: ", "^VL-\\d{4}$", "Invalid input, enter again: ");
            if (reRepo.findEstateById(id) != null) {
                System.out.println("This villa already exist in system.");
                continue;
            }
            break;
        }
        String name = v.getStringRegex("Enter name of villa: ", "^.*$", "Invalid input, enter again: ");
        String owner = v.getStringRegex("Enter name of owner: ", "^.*$", "Invalid input, enter again: ");
        double price = v.getValidDouble("Enter price of the villa: ");
        String address = v.getStringRegex("Enter address of villa: ", "^.*$", "Invalid input, enter again: ");
        double area = v.getValidDouble("Enter area of the villa: ");
        int floorCount = v.getValidInteger("Enter number of floor: ");
        int roomCoount = v.getValidInteger("Enter number of room: ");
        double poolArea = v.getValidDouble("Enter area pool of the villa: ");
        return new Villa(poolArea, floorCount, roomCoount, id, name, owner, price, address, area);
    }

    public void addSuccessRE(String type) {
        System.out.println("You added new " + type + " success.");
    }

    public String displayBasicInformation(RealEstate r) {
        return (r.getID() + ", " + r.getName() + ", " + r.getOwner());
    }

    public void menuEdit(RealEstate re) {
        System.out.println("You are editing for " + re.getName() + "(" + re.getID() + ")");
        boolean isEditing = true;
        while (isEditing) {
            System.out.println("Choose information to edit:");
            System.out.println("1. Save changes");
            System.out.println("2. Name");
            System.out.println("3. Owner");
            System.out.println("4. Address");
            System.out.println("5. Price");
            System.out.println("6. Area");
            if (re instanceof Land) {
                System.out.println("7. Land type");
            } else {
                System.out.println("7. Numbers of floor");
                System.out.println("8. Numbers of room");
                if (re instanceof Villa) {
                    System.out.println("9. Area of pool");
                } else if (re instanceof Apartment) {
                    System.out.println("9. Service");
                }
            }

            int choice = v.getValidInteger("Enter selection: ");
            if ((re instanceof Land && choice > 7) || (re instanceof House && choice > 8)
                    || (re instanceof Villa && choice > 9) || (re instanceof Apartment && choice > 9)) {
                System.out.println("Invalid choice for this type of real estate. Please try again.");
                continue;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("Saving data..");
                    isEditing = false;
                }
                case 2 -> {
                    String newName = v.getStringRegex("Enter new name: ", "^.*$", "Invalid input, enter again: ");
                    re.setName(newName);
                }
                case 3 -> {
                    String newOwner = v.getStringRegex("Enter new owner: ", "^.*$", "Invalid input, enter again: ");
                    re.setOwner(newOwner);
                }
                case 4 -> {
                    String newAddress = v.getStringRegex("Enter new address: ", "^.*$", "Invalid input, enter again: ");
                    re.setAddress(newAddress);
                }
                case 5 -> {
                    double newPrice = v.getValidDouble("Enter new price: ");
                    re.setPrice(newPrice);
                }
                case 6 -> {
                    double newArea = v.getValidDouble("Enter new area: ");
                    re.setArea(newArea);
                }
                case 7 -> {
                    if (re instanceof Land land) {
                        String newLandType = v.getStringRegex("Enter new type of land: ", "^.*$", "Invalid input, enter again: ");
                        land.setLandType(newLandType);
                        break;
                    }
                    int newFloorCount = v.getValidInteger("Enter new number of floor: ");
                    ((House) re).setFloorCount(newFloorCount);
                }
                case 8 -> {
                    int newRoomCount = v.getValidInteger("Enter new number of room: ");
                    ((House) re).setRoomCount(newRoomCount);
                }
                case 9 -> {
                    if (re instanceof Villa villa) {
                        double newPoolArea = v.getValidDouble("Enter new pool area: ");
                        villa.setPoolArea(newPoolArea);
                    } else if (re instanceof Apartment apartment) {
                        String newService = v.getStringRegex("Enter new additional service: ", "^.*$", "Invalid input, try again: ");
                        apartment.setAdditionalService(newService);
                    }
                }
                default ->
                    System.out.println("Invalid choice, please enter again.");
            }
        }
    }

    public String getUserChooseREType() {
        String typeRE = "";
        System.out.println("Choose RE type you want to search:");
        System.out.println("1. Land");
        System.out.println("2. House");
        System.out.println("3. Villa");
        System.out.println("4. Apartment");
        int choice = 0;
        do {
            choice = v.getValidInteger("Enter selection: ");
            switch (choice) {
                case 1 ->
                    typeRE = "Land";
                case 2 ->
                    typeRE = "House";
                case 3 ->
                    typeRE = "Villa";
                case 4 ->
                    typeRE = "Apartment";
                default ->
                    System.out.println("Invalid input, please try again.");
            }
        } while (choice > 4);

        return typeRE;
    }

    public Map<String, Object> getUserSearchByCriteria(String typeRE) {
        Map<String, Object> criteria = new HashMap<>();
        boolean isChosingCriteria = true;
        while (isChosingCriteria) {
            System.out.println("Choose criteria to search:");
            System.out.println("1. Finish choosing criteria");
            System.out.println("2. Area");
            System.out.println("3. Price");
            if (typeRE.equals("Land")) {
                System.out.println("4. Land type");
            } else {
                System.out.println("4. Numbers of floor");
                System.out.println("5. Numbers of room");
                if (typeRE.equals("Villa")) {
                    System.out.println("6. Pool area");
                } else if (typeRE.equals("Apartment")) {
                    System.out.println("6. Service");
                }
            }
            int choice = v.getValidInteger("Enter selection: ");
            if ((typeRE.equals("Land") && choice > 4) || (typeRE.equals("House") && choice > 5)
                    || (typeRE.equals("Villa") && choice > 6) || (typeRE.equals("Apartment") && choice > 6)) {
                System.out.println("Invalid choice for this type of real estate. Please try again.");
                continue;
            }
            switch (choice) {
                case 1:
                    isChosingCriteria = false;
                    break;
                case 2:
                    criteria.put("area", v.getValidDouble("Enter min area: "));
                    break;
                case 3:
                    criteria.put("price", v.getValidDouble("Enter min price: "));
                    break;
                case 4:
                    if (typeRE.equals("Land")) {
                        criteria.put("landtype", v.getStringRegex("Enter land type: ", "^.*$", "Invalid input, enter again: "));
                    }
                    criteria.put("floors", v.getValidInteger("Enter min floor: "));
                    break;
                case 5:
                    criteria.put("rooms", v.getValidInteger("Enter min room: "));
                    break;
                case 6:
                    if (typeRE.equals("Villa")) {
                        criteria.put("poolArea", v.getValidDouble("Enter min pool area: "));
                    } else if (typeRE.equals("Apartment")) {
                        criteria.put("services", v.getStringRegex("Enter service: ", "^.*$", "Invalid input, enter again: "));
                    }
                    break;
                default:
                    System.out.println("Invalid inout, please try again.");
            }
        }
        return criteria;
    }

    public void displaySearchResults(List<RealEstate> results) {
        if (results.isEmpty()) {
            System.out.println("Not any result matches.");
            return;
        }
        System.out.println("Results:");
        System.out.printf("%-20s%-15s%-15s\n",
                "Name", "Price", "Owner");
        for (RealEstate result : results) {
            System.out.printf("%-20s%-15s%-15s\n",
                result.getName(), result.getPrice(), result.getOwner());
        }
    }
    
    public void displaySearchSingleResult(RealEstate result) {
        if (result == null) {
            System.out.println("Not any result matches.");
            return;
        }
        System.out.printf("%-20s%-15s%-15s\n",
                "Name", "Price", "Owner");
        System.out.printf("%-20s%-15s%-15s\n",
        result.getName(), result.getPrice(), result.getOwner());
    }

    public static void main(String[] args) {
        RealEstateView r = new RealEstateView();
    }
}
