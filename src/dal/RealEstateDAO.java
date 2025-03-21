/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Apartment;
import model.House;
import model.Land;
import model.RealEstate;
import model.Villa;

/**
 *
 * @author DELL
 */
public class RealEstateDAO extends DBContext<RealEstate> {

    // method for admin
    @Override
    public ArrayList<RealEstate> list() {
        ArrayList<RealEstate> reList = new ArrayList<>();
        String sql = "select *\n"
                + "from realestate where is_sold = 0";
        try {
            try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    String id = rs.getString("realestateid");
                    String name = rs.getString("description");
                    int ownerId = rs.getInt("ownerid");
                    double price = rs.getDouble("price");
                    double area = rs.getDouble("area");
                    String street = rs.getString("street");
                    String ward = rs.getString("ward");
                    String district = rs.getString("district");
                    String city = rs.getString("city");
                    boolean diningroom = rs.getBoolean("diningroom");
                    boolean kitchen = rs.getBoolean("kitchen");
                    boolean car_park = rs.getBoolean("car_park");
                    boolean terrace = rs.getBoolean("terrace");
                    if (rs.getString("type").equals("Land")) {
                        String landType = rs.getString("landtype");
                        Land land = new Land(landType, id, name, ownerId, price, street, ward, district, city, area);
                        reList.add(land);
                    } else {
                        int floorCount = rs.getInt("floors");
                        int roomCount = rs.getInt("bedrooms");
                        if (rs.getString("type").equals("House")) {
                            House house = new House(floorCount, roomCount, diningroom, kitchen, terrace, car_park, id, name, ownerId, price, street, ward, district, city, area);
                            reList.add(house);
                        } else {
                            if (rs.getString("type").equals("Villa")) {
                                double poolArea = rs.getDouble("pool_area");
                                Villa villa = new Villa(poolArea, floorCount, roomCount, diningroom, kitchen, terrace, car_park, id, name, ownerId, price, street, ward, district, city, area);
                                reList.add(villa);
                            } else {
                                String add_ser = rs.getString("additional_service");
                                Apartment apt = new Apartment(add_ser, floorCount, roomCount, diningroom, kitchen, terrace, car_park, id, name, ownerId, price, street, ward, district, city, area);
                                reList.add(apt);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reList;
    }

    @Override
    public RealEstate get(int id) {
        String sql = "select * from realestate where realestateid = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, id);
            String reID = String.valueOf(id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int ownerId = rs.getInt("ownerid");
                String desc = rs.getString("description");
                double price = rs.getDouble("price");
                double area = rs.getDouble("area");
                String type = rs.getString("type");
                int floors = rs.getInt("floors");
                int bedrooms = rs.getInt("bedrooms");
                boolean diningroom = rs.getBoolean("diningroom");
                boolean kitchen = rs.getBoolean("kitchen");
                boolean terrace = rs.getBoolean("terrace");
                boolean car_park = rs.getBoolean("car_park");
                double pool_area = rs.getDouble("pool_area");
                String landtype = rs.getString("landtype");
                boolean is_sold = rs.getBoolean("is_sold");
                String street = rs.getString("street");
                String ward = rs.getString("ward");
                String district = rs.getString("district");
                String city = rs.getString("city");
                String additional_ser = rs.getString("additional_service");
                switch (type) {
                    case "Land":
                        return new Land(landtype, reID, desc, ownerId, price, street, ward, district, city, area);
                    case "House":
                        return new House(floors, bedrooms, diningroom, kitchen, terrace, car_park, reID, desc, ownerId, price, street, ward, district, city, area);
                    case "Villa":
                        return new Villa(pool_area, floors, bedrooms, diningroom, kitchen, terrace, car_park, reID, desc, ownerId, price, street, ward, district, city, area);
                    case "Apartment":
                        return new Apartment(additional_ser, floors, bedrooms, diningroom, kitchen, terrace, car_park, reID, desc, ownerId, price, street, ward, district, city, area);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RealEstateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public RealEstate insert(RealEstate entity) {
        String sql = "INSERT INTO realestate(ownerid, description, price, area, type, floors, bedrooms, diningroom, kitchen, car_park, pool_area, landtype, is_sold, street, ward, district, city, terrace, additional_service, created_at, updated_at)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), GETDATE())"; // total 20 parameters
        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, entity.getOwner());
            pst.setString(2, entity.getName());
            pst.setDouble(3, entity.getPrice());
            pst.setDouble(4, entity.getArea());
            pst.setBoolean(13, false); // is_sold = false
            pst.setString(14, entity.getStreet());
            pst.setString(15, entity.getWard());
            pst.setString(16, entity.getDistrict());
            pst.setString(17, entity.getCity());

            if (entity instanceof Villa) {
                pst.setString(5, "Villa");
                pst.setInt(6, ((Villa) entity).getFloorCount());
                pst.setInt(7, ((Villa) entity).getRoomCount());
                pst.setBoolean(8, ((Villa) entity).isIsHaveDiningroom());
                pst.setBoolean(9, ((Villa) entity).isIsHaveKitchen());
                pst.setBoolean(10, ((Villa) entity).isIsHaveCarPark());
                pst.setNull(12, java.sql.Types.BIT); // LandType
                pst.setBoolean(18, ((Villa) entity).isIsHaveTerrace());
                pst.setDouble(11, ((Villa) entity).getPoolArea());
                pst.setNull(19, java.sql.Types.VARCHAR); // additional_service
            } else if (entity instanceof Apartment) {
                pst.setString(5, "Apartment");
                pst.setInt(6, ((Apartment) entity).getFloorCount());
                pst.setInt(7, ((Apartment) entity).getRoomCount());
                pst.setBoolean(8, ((Apartment) entity).isIsHaveDiningroom());
                pst.setBoolean(9, ((Apartment) entity).isIsHaveKitchen());
                pst.setBoolean(10, ((Apartment) entity).isIsHaveCarPark());
                pst.setNull(12, java.sql.Types.BIT); // LandType
                pst.setBoolean(18, ((Apartment) entity).isIsHaveTerrace());
                pst.setNull(11, java.sql.Types.DOUBLE); // pool_area
                pst.setString(19, ((Apartment) entity).getAdditionalService()); // additional_service
            } else if (entity instanceof House) {
                pst.setString(5, "House");
                pst.setInt(6, ((House) entity).getFloorCount());
                pst.setInt(7, ((House) entity).getRoomCount());
                pst.setBoolean(8, ((House) entity).isIsHaveDiningroom());
                pst.setBoolean(9, ((House) entity).isIsHaveKitchen());
                pst.setBoolean(10, ((House) entity).isIsHaveCarPark());
                pst.setNull(12, java.sql.Types.BIT); // LandType
                pst.setBoolean(18, ((House) entity).isIsHaveTerrace());

                pst.setNull(11, java.sql.Types.DOUBLE); // pool_area
                pst.setNull(19, java.sql.Types.VARCHAR); // additional_service
            } else if (entity instanceof Land) {
                pst.setString(5, "Land");
                pst.setNull(6, java.sql.Types.INTEGER); // floors
                pst.setNull(7, java.sql.Types.INTEGER); // bedrooms
                pst.setNull(8, java.sql.Types.BIT); // diningroom
                pst.setNull(9, java.sql.Types.BIT); // kitchen
                pst.setNull(10, java.sql.Types.BIT); // car_park
                pst.setNull(11, java.sql.Types.DOUBLE); // pool_area
                pst.setString(12, ((Land) entity).getLandType());
                pst.setNull(18, java.sql.Types.BIT); // terrace
                pst.setNull(19, java.sql.Types.VARCHAR); // additional_service
            }
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record inserted successfully.");
            } else {
                System.out.println("Insertion failed.");
            }
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    System.out.println("Generated ID: " + generatedId);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RealEstateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public RealEstate update(RealEstate entity) {
        String sql = "UPDATE realestate\n"
                + "SET\n"
                + "    ownerid = ?, \n"
                + "    description = ?, \n"
                + "    price = ?, \n"
                + "    area = ?, \n"
                + "    floors = ?, \n"
                + "    bedrooms = ?, \n"
                + "    diningroom = ?, \n"
                + "    kitchen = ?, \n"
                + "    terrace = ?, \n"
                + "    car_park = ?, \n"
                + "    pool_area = ?, \n"
                + "    landtype = ?, \n"
                + "    street = ?, \n"
                + "    ward = ?, \n"
                + "    district = ?, \n"
                + "    city = ?, \n"
                + "    updated_at = GETDATE(),\n"
                + "    additional_service = ?\n"
                + "WHERE realestateid = ?";
        int reID = Integer.parseInt(entity.getID());
        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(18, reID);
            pst.setInt(1, entity.getOwner());
            pst.setString(2, entity.getName());
            pst.setDouble(3, entity.getPrice());
            pst.setDouble(4, entity.getArea());
            pst.setString(13, entity.getStreet());
            pst.setString(14, entity.getWard());
            pst.setString(15, entity.getDistrict());
            pst.setString(16, entity.getCity());
            if (entity instanceof Land) {
                pst.setNull(5, java.sql.Types.INTEGER);
                pst.setNull(6, java.sql.Types.INTEGER);
                pst.setNull(7, java.sql.Types.BIT);
                pst.setNull(8, java.sql.Types.BIT);
                pst.setNull(9, java.sql.Types.BIT);
                pst.setNull(10, java.sql.Types.BIT);
                pst.setNull(11, java.sql.Types.DOUBLE);
                pst.setString(12, ((Land) entity).getLandType());
                pst.setNull(17, java.sql.Types.VARCHAR);
            } else {
                if (entity instanceof Villa) {
                    pst.setInt(5, ((Villa) entity).getFloorCount());
                    pst.setInt(6, ((Villa) entity).getRoomCount());
                    pst.setBoolean(7, ((Villa) entity).isIsHaveDiningroom());
                    pst.setBoolean(8, ((Villa) entity).isIsHaveKitchen());
                    pst.setBoolean(9, ((Villa) entity).isIsHaveTerrace());
                    pst.setBoolean(10, ((Villa) entity).isIsHaveCarPark());
                    pst.setDouble(11, ((Villa) entity).getPoolArea());
                    pst.setNull(12, java.sql.Types.VARCHAR);
                    pst.setNull(17, java.sql.Types.VARCHAR);
                } else if (entity instanceof Apartment) {
                    pst.setInt(5, ((Apartment) entity).getFloorCount());
                    pst.setInt(6, ((Apartment) entity).getRoomCount());
                    pst.setBoolean(7, ((Apartment) entity).isIsHaveDiningroom());
                    pst.setBoolean(8, ((Apartment) entity).isIsHaveKitchen());
                    pst.setBoolean(9, ((Apartment) entity).isIsHaveTerrace());
                    pst.setBoolean(10, ((Apartment) entity).isIsHaveCarPark());
                    pst.setNull(11, java.sql.Types.DOUBLE);
                    pst.setNull(12, java.sql.Types.VARCHAR);
                    pst.setString(17, ((Apartment) entity).getAdditionalService());
                } else {
                    pst.setInt(5, ((House) entity).getFloorCount());
                    pst.setInt(6, ((House) entity).getRoomCount());
                    pst.setBoolean(7, ((House) entity).isIsHaveDiningroom());
                    pst.setBoolean(8, ((House) entity).isIsHaveKitchen());
                    pst.setBoolean(9, ((House) entity).isIsHaveTerrace());
                    pst.setBoolean(10, ((House) entity).isIsHaveCarPark());
                    pst.setNull(11, java.sql.Types.DOUBLE);
                    pst.setNull(12, java.sql.Types.VARCHAR);
                    pst.setNull(17, java.sql.Types.VARCHAR);
                }
            }
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                return entity;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RealEstateDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public RealEstate delete(int id) {
        String sql = "delete from realestate\n"
                + "where realestateid = ?";
        RealEstate entityToDelete = get(id);
        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, id);
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                return entityToDelete;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RealEstateDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    // method for user

    public ArrayList<RealEstate> getListREOfUser(int userid) {
        ArrayList<RealEstate> reList = new ArrayList<>();
        String sql = "select * from realestate where is_sold = 0 and ownerid = ?";

        try {
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setInt(1, userid);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        String id = rs.getString("realestateid");
                        String name = rs.getString("description");
                        int ownerId = rs.getInt("ownerid");
                        double price = rs.getDouble("price");
                        double area = rs.getDouble("area");
                        String street = rs.getString("street");
                        String ward = rs.getString("ward");
                        String district = rs.getString("district");
                        String city = rs.getString("city");
                        boolean diningroom = rs.getBoolean("diningroom");
                        boolean kitchen = rs.getBoolean("kitchen");
                        boolean car_park = rs.getBoolean("car_park");
                        boolean terrace = rs.getBoolean("terrace");

                        if (rs.getString("type").equals("Land")) {
                            String landType = rs.getString("landtype");
                            Land land = new Land(landType, id, name, ownerId, price, street, ward, district, city, area);
                            reList.add(land);
                        } else {
                            int floorCount = rs.getInt("floors");
                            int roomCount = rs.getInt("bedrooms");
                            if (rs.getString("type").equals("House")) {
                                House house = new House(floorCount, roomCount, diningroom, kitchen, terrace, car_park, id, name, ownerId, price, street, ward, district, city, area);
                                reList.add(house);
                            } else {
                                if (rs.getString("type").equals("Villa")) {
                                    double poolArea = rs.getDouble("pool_area");
                                    Villa villa = new Villa(poolArea, floorCount, roomCount, diningroom, kitchen, terrace, car_park, id, name, ownerId, price, street, ward, district, city, area);
                                    reList.add(villa);
                                } else {
                                    String add_ser = rs.getString("additional_service");
                                    Apartment apt = new Apartment(add_ser, floorCount, roomCount, diningroom, kitchen, terrace, car_park, id, name, ownerId, price, street, ward, district, city, area);
                                    reList.add(apt);
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reList;
    }

    public RealEstate userDeleteRE(int reID, int userID) {
        String sql = "delete from realestate\n"
                + "where realestateid = ? and ownerid = ?";
        RealEstate entityToDelete = get(reID);
        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, reID);
            pst.setInt(2, userID);
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                return entityToDelete;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RealEstateDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public RealEstate userUpdate(RealEstate entity, int userid) {
        String sql = "UPDATE realestate\n"
                + "SET\n"
                + "    description = ?, \n"
                + "    price = ?, \n"
                + "    area = ?, \n"
                + "    floors = ?, \n"
                + "    bedrooms = ?, \n"
                + "    diningroom = ?, \n"
                + "    kitchen = ?, \n"
                + "    terrace = ?, \n"
                + "    car_park = ?, \n"
                + "    pool_area = ?, \n"
                + "    landtype = ?, \n"
                + "    street = ?, \n"
                + "    ward = ?, \n"
                + "    district = ?, \n"
                + "    city = ?, \n"
                + "    updated_at = GETDATE(),\n"
                + "    additional_service = ?\n"
                + "WHERE realestateid = ? and ownerid = ?";

        int reID = Integer.parseInt(entity.getID());
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, entity.getName());
            pst.setDouble(2, entity.getPrice());
            pst.setDouble(3, entity.getArea());

            if (entity instanceof Villa) {
                pst.setInt(4, ((Villa) entity).getFloorCount());
                pst.setInt(5, ((Villa) entity).getRoomCount());
                pst.setBoolean(6, ((Villa) entity).isIsHaveDiningroom());
                pst.setBoolean(7, ((Villa) entity).isIsHaveKitchen());
                pst.setBoolean(8, ((Villa) entity).isIsHaveTerrace());
                pst.setBoolean(9, ((Villa) entity).isIsHaveCarPark());
                pst.setDouble(10, ((Villa) entity).getPoolArea());
                pst.setNull(11, java.sql.Types.VARCHAR);
                pst.setNull(16, java.sql.Types.VARCHAR);
            } else if (entity instanceof Apartment) {
                pst.setInt(4, ((Apartment) entity).getFloorCount());
                pst.setInt(5, ((Apartment) entity).getRoomCount());
                pst.setBoolean(6, ((Apartment) entity).isIsHaveDiningroom());
                pst.setBoolean(7, ((Apartment) entity).isIsHaveKitchen());
                pst.setBoolean(8, ((Apartment) entity).isIsHaveTerrace());
                pst.setBoolean(9, ((Apartment) entity).isIsHaveCarPark());
                pst.setNull(10, java.sql.Types.DOUBLE);
                pst.setNull(11, java.sql.Types.VARCHAR);
                pst.setString(16, ((Apartment) entity).getAdditionalService());
            } else if (entity instanceof Land) {
                pst.setNull(4, java.sql.Types.INTEGER);
                pst.setNull(5, java.sql.Types.INTEGER);
                pst.setNull(6, java.sql.Types.BIT);
                pst.setNull(7, java.sql.Types.BIT);
                pst.setNull(8, java.sql.Types.BIT);
                pst.setNull(9, java.sql.Types.BIT);
                pst.setNull(10, java.sql.Types.DOUBLE);
                pst.setString(11, ((Land) entity).getLandType());
                pst.setNull(16, java.sql.Types.VARCHAR);
            } else {
                pst.setInt(4, ((House) entity).getFloorCount());
                pst.setInt(5, ((House) entity).getRoomCount());
                pst.setBoolean(6, ((House) entity).isIsHaveDiningroom());
                pst.setBoolean(7, ((House) entity).isIsHaveKitchen());
                pst.setBoolean(8, ((House) entity).isIsHaveTerrace());
                pst.setBoolean(9, ((House) entity).isIsHaveCarPark());
                pst.setNull(10, java.sql.Types.DOUBLE);
                pst.setNull(11, java.sql.Types.VARCHAR);
                pst.setNull(12, java.sql.Types.VARCHAR);
            }

            pst.setString(12, entity.getStreet());
            pst.setString(13, entity.getWard());
            pst.setString(14, entity.getDistrict());
            pst.setString(15, entity.getCity());
            pst.setInt(17, reID); 
            pst.setInt(18, userid); 

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                return entity;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RealEstateDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public RealEstate userInsert(RealEstate entity, int userid) {
        String sql = "INSERT INTO realestate(ownerid, description, price, area, type, floors, bedrooms, diningroom, kitchen, car_park, pool_area, landtype, is_sold, street, ward, district, city, terrace, additional_service, created_at, updated_at)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), GETDATE())"; // total 20 parameters
        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, userid);
            pst.setString(2, entity.getName());
            pst.setDouble(3, entity.getPrice());
            pst.setDouble(4, entity.getArea());
            pst.setBoolean(13, false); // is_sold = false
            pst.setString(14, entity.getStreet());
            pst.setString(15, entity.getWard());
            pst.setString(16, entity.getDistrict());
            pst.setString(17, entity.getCity());

            if (entity instanceof Villa) {
                pst.setString(5, "Villa");
                pst.setInt(6, ((Villa) entity).getFloorCount());
                pst.setInt(7, ((Villa) entity).getRoomCount());
                pst.setBoolean(8, ((Villa) entity).isIsHaveDiningroom());
                pst.setBoolean(9, ((Villa) entity).isIsHaveKitchen());
                pst.setBoolean(10, ((Villa) entity).isIsHaveCarPark());
                pst.setNull(12, java.sql.Types.BIT); // LandType
                pst.setBoolean(18, ((Villa) entity).isIsHaveTerrace());
                pst.setDouble(11, ((Villa) entity).getPoolArea());
                pst.setNull(19, java.sql.Types.VARCHAR); // additional_service
            } else if (entity instanceof Apartment) {
                pst.setString(5, "Apartment");
                pst.setInt(6, ((Apartment) entity).getFloorCount());
                pst.setInt(7, ((Apartment) entity).getRoomCount());
                pst.setBoolean(8, ((Apartment) entity).isIsHaveDiningroom());
                pst.setBoolean(9, ((Apartment) entity).isIsHaveKitchen());
                pst.setBoolean(10, ((Apartment) entity).isIsHaveCarPark());
                pst.setNull(12, java.sql.Types.BIT); // LandType
                pst.setBoolean(18, ((Apartment) entity).isIsHaveTerrace());
                pst.setNull(11, java.sql.Types.DOUBLE); // pool_area
                pst.setString(19, ((Apartment) entity).getAdditionalService()); // additional_service
            } else if (entity instanceof House) {
                pst.setString(5, "House");
                pst.setInt(6, ((House) entity).getFloorCount());
                pst.setInt(7, ((House) entity).getRoomCount());
                pst.setBoolean(8, ((House) entity).isIsHaveDiningroom());
                pst.setBoolean(9, ((House) entity).isIsHaveKitchen());
                pst.setBoolean(10, ((House) entity).isIsHaveCarPark());
                pst.setNull(12, java.sql.Types.BIT); // LandType
                pst.setBoolean(18, ((House) entity).isIsHaveTerrace());

                pst.setNull(11, java.sql.Types.DOUBLE); // pool_area
                pst.setNull(19, java.sql.Types.VARCHAR); // additional_service
            } else if (entity instanceof Land) {
                pst.setString(5, "Land");
                pst.setNull(6, java.sql.Types.INTEGER); // floors
                pst.setNull(7, java.sql.Types.INTEGER); // bedrooms
                pst.setNull(8, java.sql.Types.BIT); // diningroom
                pst.setNull(9, java.sql.Types.BIT); // kitchen
                pst.setNull(10, java.sql.Types.BIT); // car_park
                pst.setNull(11, java.sql.Types.DOUBLE); // pool_area
                pst.setString(12, ((Land) entity).getLandType());
                pst.setNull(18, java.sql.Types.BIT); // terrace
                pst.setNull(19, java.sql.Types.VARCHAR); // additional_service
            }
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record inserted successfully.");
            } else {
                System.out.println("Insertion failed.");
            }
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    System.out.println("Generated ID: " + generatedId);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RealEstateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        RealEstateDAO dao = new RealEstateDAO();
        ArrayList<RealEstate> l = dao.getListREOfUser(1);
        for (RealEstate realEstate : l) {
            System.out.println(realEstate.getID());
        }
    }

    public void updateRealEstateStatus(int reid) {
        String sql = "UPDATE [realestate] SET is_sold = ? WHERE realestateid = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setBoolean(1, true);  
            stm.setInt(2, reid); 

            int rowsAffected = stm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("[SUCCESS] Updated is_sold status for realestate_id: " + reid);
            } else {
                System.out.println("[INFO] No records found for realestate_id: " + reid);
            }
        } catch (SQLException ex) {
            System.out.println("[ERROR] Failed to update realestate: " + ex.getMessage());
        }
    }

}
