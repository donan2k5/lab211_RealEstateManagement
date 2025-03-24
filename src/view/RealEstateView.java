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
import model.User;
import model.Villa;
import repository.impl.RealEstateRepository;
import repository.UserRepository;
import repository.impl.RealEstateRepository;
import repository.impl.UserRepositoryImpl;

/**
 *
 * @author DELL
 */
public class RealEstateView implements IRealEstateView {

    private RealEstateRepository reRepo = new RealEstateRepository();
    private Validation v = new Validation();
    private UserRepository userRepository = new UserRepositoryImpl();

    {
        reRepo.readData();
    }

    public String getTypeRE(RealEstate r) {
        if (r instanceof Villa) {
            return "Villa";
        } else if (r instanceof Apartment) {
            return "Apartment";
        } else if (r instanceof House) {
            return "House";
        } else {
            return "Land";
        }
    }

    public void displayListREUser(List<RealEstate> reList, User u) {
        if (reList.isEmpty()) {
            System.out.println("User do not have any realestate.");
            return;
        }
        System.out.println("RE list of " + u.getFirstName() + " " + u.getLastName() + ": ");
        System.out.printf("%-10s%-60s%-15s%-15s%-15s%-50s\n",
                "Id", "Name", "Type",
                "Price", "Area", "Address");
        for (RealEstate realEstate : reList) {
            String address = realEstate.getStreet() + "," + realEstate.getWard() + "," + realEstate.getDistrict() + "," + realEstate.getCity();
            System.out.printf("%-10s%-60s%-15s%-15s%-15s%-50s\n",
                    realEstate.getID(), realEstate.getName(), this.getTypeRE(realEstate),
                    realEstate.getPrice(), realEstate.getArea(), address);
        }
    }

    @Override
    public void displayListLand(List<Land> landList) {
        if (landList.isEmpty()) {
            System.out.println("Not exist any land in system.");
            return;
        }
        System.out.println("Land list:");
        System.out.printf("%-10s%-60s%-10s%-15s%-15s%-20s%-50s\n",
                "Id", "Name", "OwnerID",
                "Price", "Area", "LandType", "Address");
        for (Land land : landList) {
            System.out.printf("%-10s%-60s%-10s%-15s%-15s%-20s%-50s\n",
                    land.getID(), land.getName(), land.getOwner(),
                    land.getPrice(), land.getArea(), land.getLandType(), land.getStreet() + "," + land.getWard() + "," + land.getDistrict() + "," + land.getCity());
        }
    }

    public String displayStatus(boolean status) {
        return status ? "Yes" : "No";
    }

    @Override
    public void displayListHouse(List<House> houseList) {
        if (houseList.isEmpty()) {
            System.out.println("Not exist any house in system.");
            return;
        }
        System.out.println("House list:");
        System.out.printf("%-10s%-60s%-10s%-15s%-15s%-10s%-10s%-30s%-20s%-20s%-20s%-50s\n",
                "Id", "Name", "OwnerID", "Price", "Area", "Floor", "Room", "isHaveDiningRoom", "isHaveKitchen", "isHaveTerrace", "isHaveCar Park", "Address");

        for (House house : houseList) {
            String address = house.getStreet() + "," + house.getWard() + "," + house.getDistrict() + "," + house.getCity();
            System.out.printf("%-10s%-60s%-10s%-15s%-15s%-10s%-10s%-30s%-20s%-20s%-20s%-50s\n",
                    house.getID(), house.getName(), house.getOwner(),
                    house.getPrice(), house.getArea(), house.getFloorCount(), house.getRoomCount(),
                    displayStatus(house.isIsHaveDiningroom()), displayStatus(house.isIsHaveKitchen()),
                    displayStatus(house.isIsHaveTerrace()), displayStatus(house.isIsHaveCarPark()),
                    address);
        }
    }

    @Override
    public void displayListVilla(List<Villa> villaList) {
        if (villaList.isEmpty()) {
            System.out.println("Not exist any villa in system.");
            return;
        }
        System.out.println("Villa list:");
        System.out.printf("%-10s%-60s%-10s%-15s%-15s%-15s%-10s%-10s%-30s%-20s%-20s%-20s%-50s\n",
                "Id", "Name", "OwnerID", "Price", "Area", "PoolArea", "Floor", "Room", "isHaveDiningRoom", "isHaveKitchen", "isHaveTerrace", "isHaveCar Park", "Address");
        for (Villa villa : villaList) {
            String address = villa.getStreet() + "," + villa.getWard() + "," + villa.getDistrict() + "," + villa.getCity();
            System.out.printf("%-10s%-60s%-10s%-15s%-15s%-15s%-10s%-10s%-30s%-20s%-20s%-20s%-50s\n",
                    villa.getID(), villa.getName(), villa.getOwner(),
                    villa.getPrice(), villa.getArea(), villa.getPoolArea(), villa.getFloorCount(), villa.getRoomCount(),
                    displayStatus(villa.isIsHaveDiningroom()), displayStatus(villa.isIsHaveKitchen()),
                    displayStatus(villa.isIsHaveTerrace()), displayStatus(villa.isIsHaveCarPark()),
                    address);
        }
    }

    @Override
    public void displayListApartment(List<Apartment> apartmentList) {
        if (apartmentList.isEmpty()) {
            System.out.println("Not exist any apartment in system.");
            return;
        }
        System.out.println("Apartment list:");
        System.out.printf("%-10s%-60s%-10s%-15s%-15s%-10s%-10s%-30s%-20s%-20s%-20s%-30s%-50s\n",
                "Id", "Name", "OwnerID", "Price", "Area", "Floor", "Room", "isHaveDiningRoom", "isHaveKitchen", "isHaveTerrace", "isHaveCar Park", "AddiotinalService", "Address");
        for (Apartment apartment : apartmentList) {
            String address = apartment.getStreet() + "," + apartment.getWard() + "," + apartment.getDistrict() + "," + apartment.getCity();
            System.out.printf("%-10s%-60s%-10s%-15s%-15s%-10s%-10s%-30s%-20s%-20s%-20s%-30s%-50s\n",
                    apartment.getID(), apartment.getName(), apartment.getOwner(),
                    apartment.getPrice(), apartment.getArea(), apartment.getFloorCount(), apartment.getRoomCount(),
                    displayStatus(apartment.isIsHaveDiningroom()), displayStatus(apartment.isIsHaveKitchen()),
                    displayStatus(apartment.isIsHaveTerrace()), displayStatus(apartment.isIsHaveCarPark()), apartment.getAdditionalService(),
                    address);
        }
    }

    private int getOwnerId() {
        int ownerId;
        while (true) {
            ownerId = v.getValidInteger("Enter id of owner:");
            if (userRepository.existsById(ownerId)) {
                return ownerId;
            }
            System.out.println("Id not exist in system, pls try again.");
        }
    }

    public Land getInformationLand(String role) {
        String name = v.getStringRegex("Enter name of land: ", "^.*$", "Invalid input, enter again: ");
        double price = v.getValidDouble("Enter price of the land: ");
        String street = v.getStringRegex("Enter street of land: ", "^.*$", "Invalid input, enter again: ");
        String ward = v.getStringRegex("Enter ward of land: ", "^.*$", "Invalid input, enter again: ");
        String district = v.getStringRegex("Enter district of land: ", "^.*$", "Invalid input, enter again: ");
        String city = v.getStringRegex("Enter city of land: ", "^.*$", "Invalid input, enter again: ");
        double area = v.getValidDouble("Enter area of the land: ");
        String landType = v.getStringRegex("Enter type of land: ", "^.*$", "Invalid input, enter again: ");
        if (role.equals("admin")) {
            int ownerId = this.getOwnerId();
            return new Land(landType, name, ownerId, price, street, ward, district, city, area);
        }
        return new Land(landType, name, price, street, ward, district, city, area);
    }

    public House getInformationHouse(String role) {
        String houseName = v.getStringRegex("Enter name of house: ", "^.*$", "Invalid input, enter again: ");
        double price = v.getValidDouble("Enter price of the house: ");
        String street = v.getStringRegex("Enter street of house: ", "^.*$", "Invalid input, enter again: ");
        String ward = v.getStringRegex("Enter ward of house: ", "^.*$", "Invalid input, enter again: ");
        String district = v.getStringRegex("Enter district of house: ", "^.*$", "Invalid input, enter again: ");
        String city = v.getStringRegex("Enter city of house: ", "^.*$", "Invalid input, enter again: ");
        double area = v.getValidDouble("Enter area of the house: ");
        int floorCount = v.getValidInteger("Enter number of floor: ");
        int roomCoount = v.getValidInteger("Enter number of room: ");
        boolean diningroom = this.getUserChooseYesNo("diningroom");
        boolean kitchen = this.getUserChooseYesNo("kitchen");
        boolean terrace = this.getUserChooseYesNo("terrace");
        boolean car_park = this.getUserChooseYesNo("carpark");
        if (role.equals("admin")) {
            int ownerId = this.getOwnerId();
            return new House(floorCount, roomCoount, diningroom, kitchen, terrace, car_park, houseName, ownerId, price, street, ward, district, city, area);
        }
        return new House(floorCount, roomCoount, diningroom, kitchen, terrace, car_park, houseName, price, street, ward, district, city, area);
    }

    public Apartment getInformationApartment(String role) {
        String name = v.getStringRegex("Enter name of apartment: ", "^.*$", "Invalid input, enter again: ");
        double price = v.getValidDouble("Enter price of the apartment: ");
        String street = v.getStringRegex("Enter street of house: ", "^.*$", "Invalid input, enter again: ");
        String ward = v.getStringRegex("Enter ward of house: ", "^.*$", "Invalid input, enter again: ");
        String district = v.getStringRegex("Enter district of house: ", "^.*$", "Invalid input, enter again: ");
        String city = v.getStringRegex("Enter city of house: ", "^.*$", "Invalid input, enter again: ");
        double area = v.getValidDouble("Enter area of the apartment: ");
        int floorCount = v.getValidInteger("Enter number of floor: ");
        int roomCoount = v.getValidInteger("Enter number of room: ");
        String additionalService = v.getStringRegex("Enter service: ", "^.*$", "Invalid input, enter again: ");
        boolean diningroom = this.getUserChooseYesNo("diningroom");
        boolean kitchen = this.getUserChooseYesNo("kitchen");
        boolean terrace = this.getUserChooseYesNo("terrace");
        boolean car_park = this.getUserChooseYesNo("carpark");
        if (role.equals("admin")) {
            int ownerId = this.getOwnerId();
            return new Apartment(additionalService, floorCount, roomCoount, diningroom, kitchen, terrace, car_park, name, ownerId, price, street, ward, district, city, area);
        }
        return new Apartment(additionalService, floorCount, roomCoount, diningroom, kitchen, terrace, car_park, name, price, street, ward, district, city, area);
    }

    public Villa getInformationVilla(String role) {
        String name = v.getStringRegex("Enter name of villa: ", "^.*$", "Invalid input, enter again: ");
        double price = v.getValidDouble("Enter price of the villa: ");
        String street = v.getStringRegex("Enter street of house: ", "^.*$", "Invalid input, enter again: ");
        String ward = v.getStringRegex("Enter ward of house: ", "^.*$", "Invalid input, enter again: ");
        String district = v.getStringRegex("Enter district of house: ", "^.*$", "Invalid input, enter again: ");
        String city = v.getStringRegex("Enter city of house: ", "^.*$", "Invalid input, enter again: ");
        double area = v.getValidDouble("Enter area of the villa: ");
        int floorCount = v.getValidInteger("Enter number of floor: ");
        int roomCoount = v.getValidInteger("Enter number of room: ");
        double poolArea = v.getValidDouble("Enter area pool of the villa: ");
        boolean diningroom = this.getUserChooseYesNo("diningroom");
        boolean kitchen = this.getUserChooseYesNo("kitchen");
        boolean terrace = this.getUserChooseYesNo("terrace");
        boolean car_park = this.getUserChooseYesNo("carpark");
        if (role.equals("admin")) {
            int ownerId = this.getOwnerId();
            return new Villa(poolArea, floorCount, roomCoount, diningroom, kitchen, terrace, car_park, name, ownerId, price, street, ward, district, city, area);
        }
        return new Villa(poolArea, floorCount, roomCoount, diningroom, kitchen, terrace, car_park, name, price, street, ward, district, city, area);
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
            System.out.println("4. Street");
            System.out.println("5. Ward");
            System.out.println("6. District");
            System.out.println("7. City");
            System.out.println("8. Price");
            System.out.println("9. Area");
            if (re instanceof Land) {
                System.out.println("10. Land type");
            } else {
                System.out.println("10. Numbers of floor");
                System.out.println("11. Numbers of room");
                System.out.println("12. Diningroom");
                System.out.println("13. Kitchen");
                System.out.println("14. Terrace");
                System.out.println("15. Car park");
                if (re instanceof Villa) {
                    System.out.println("16. Area of pool");
                } else if (re instanceof Apartment) {
                    System.out.println("16. Service");
                }
            }

            int choice = v.getValidInteger("Enter selection: ");
            if ((re instanceof Land && choice > 10) || (re instanceof House && choice > 15)
                    || (re instanceof Villa && choice > 16) || (re instanceof Apartment && choice > 16)) {
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
                    int newOwnerId = this.getOwnerId();
                    re.setOwnerId(newOwnerId);
                }
                case 4 -> {
                    String newStreet = v.getStringRegex("Enter new street: ", "^.*$", "Invalid input, enter again: ");
                    re.setStreet(newStreet);
                }
                case 5 -> {
                    String newWard = v.getStringRegex("Enter new ward: ", "^.*$", "Invalid input, enter again: ");
                    re.setWard(newWard);
                }
                case 6 -> {
                    String newDistrict = v.getStringRegex("Enter new district: ", "^.*$", "Invalid input, enter again: ");
                    re.setDistrict(newDistrict);
                }
                case 7 -> {
                    String newCity = v.getStringRegex("Enter new city: ", "^.*$", "Invalid input, enter again: ");
                    re.setCity(newCity);
                }
                case 8 -> {
                    double newPrice = v.getValidDouble("Enter new price: ");
                    re.setPrice(newPrice);
                }
                case 9 -> {
                    double newArea = v.getValidDouble("Enter new area: ");
                    re.setArea(newArea);
                }
                case 10 -> {
                    if (re instanceof Land land) {
                        String newLandType = v.getStringRegex("Enter new type of land: ", "^.*$", "Invalid input, enter again: ");
                        land.setLandType(newLandType);
                        break;
                    }
                    int newFloorCount = v.getValidInteger("Enter new number of floor: ");
                    ((House) re).setFloorCount(newFloorCount);
                }
                case 11 -> {
                    int newRoomCount = v.getValidInteger("Enter new number of room: ");
                    ((House) re).setRoomCount(newRoomCount);
                }
                case 12 -> {
                    boolean isHaveDiningRoom = getUserChooseYesNo("diningroom");
                    ((House) re).setIsHaveDiningroom(isHaveDiningRoom);
                }
                case 13 -> {
                    boolean isHaveKitchen = getUserChooseYesNo("kitchen");
                    ((House) re).setIsHaveKitchen(isHaveKitchen);
                }
                case 14 -> {
                    boolean isHaveTerrace = getUserChooseYesNo("terrace");
                    ((House) re).setIsHaveTerrace(isHaveTerrace);
                }
                case 15 -> {
                    boolean isHaveCarPark = getUserChooseYesNo("car park");
                    ((House) re).setIsHaveCarPark(isHaveCarPark);
                }
                case 16 -> {
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

    public String getUserChooseREType(String action) {
        String typeRE = "";
        System.out.println("Choose RE type you want to " + action + ": ");
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
        System.out.printf("%-60s%-20s%-20s\n",
                "Name", "Price", "Owner");
        for (RealEstate result : results) {
            System.out.printf("%-60s%-20s%-20s\n",
                    result.getName(), result.getPrice(), result.getOwner());
        }
    }

    public boolean getUserChooseYesNo(String att) {
        boolean result = false;
        System.out.println("Is have " + att + "?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = 0;
        do {
            choice = v.getValidInteger("Enter your selection: ");
            switch (choice) {
                case 1:
                    result = true;
                    break;
                case 2:
                    result = false;
                    break;
                default:
                    System.out.println("Invalid input, pls try again.");
            }
        } while (choice > 2);
        return result;
    }

    public void displayNotification(RealEstate r, String action) {
        if (r == null) {
            System.out.println("You " + action + " " + r.getName() + "(" + r.getID() + ")" + " failed.");
        } else {
            System.out.println("You " + action + " " + r.getName() + "(" + r.getID() + ")" + " success.");
        }
    }
    
    public void displaySearchSingleResult(RealEstate result) {
        if (result == null) {
            System.out.println("Not any result matches.");
            return;
        }
        System.out.printf("%-15s%-20s%-15s%-15s\n",
                "ID","Name", "Price", "Owner");
        System.out.printf("%-15s%-20s%-15s%-15s\n",
        result.getID(),result.getName(), result.getPrice(), result.getOwner());
    }

    public static void main(String[] args) {
        Validation v = new Validation();
        RealEstateView rv = new RealEstateView();
        boolean result = rv.getUserChooseYesNo("ạd");
        System.out.println(result);
    }
}
