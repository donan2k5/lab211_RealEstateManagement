/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Apartment;
import model.House;
import model.Land;
import model.RealEstate;
import model.Villa;
import repository.IRealEstateRepository;

/**
 *
 * @author DELL
 */
public class RealEstateRepository implements IRealEstateRepository {

    private String filename = "src/data/realestate.txt";
    private List<RealEstate> listRealEstate = new ArrayList<>();

    @Override
    public RealEstate findEstateById(String id) {
        for (RealEstate realEstate : listRealEstate) {
            if (realEstate.getID().equals(id.trim())) {
                return realEstate;
            }
        }
        return null;
    }

    @Override
    public List<RealEstate> readData() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(", ");
                String id = parts[0];
                String name = parts[1];
                String owner = parts[2];
                String address = parts[3];
                double price = Double.parseDouble(parts[4]);
                double area = Double.parseDouble(parts[5]);
                RealEstate r;
                if (id.startsWith("L-")) {
                    if (parts.length < 7) {
                        continue;
                    }
                    String landType = parts[6];
                    r = new Land(landType, id, name, owner, price, address, area);
                } else {
                    if (parts.length < 8) {
                        continue;
                    }
                    int floorCount = Integer.parseInt(parts[6]);
                    int roomCount = Integer.parseInt(parts[7]);

                    if (id.startsWith("VL")) {
                        if (parts.length < 9) {
                            continue;
                        }
                        double poolArea = Double.parseDouble(parts[8]);
                        r = new Villa(poolArea, floorCount, roomCount, id, name, owner, price, address, area);
                    } else if (id.startsWith("APT")) {
                        StringBuilder additionalService = new StringBuilder();
                        for (int i = 8; i < parts.length; i++) {
                            if (i > 8) {
                                additionalService.append(", ");
                            }
                            additionalService.append(parts[i]);
                        }
                        r = new Apartment(additionalService.toString(), floorCount, roomCount, id, name, owner, price, address, area);
                    } else {
                        r = new House(floorCount, roomCount, id, name, owner, price, address, area);
                    }
                }
                listRealEstate.add(r);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listRealEstate;
    }

    @Override
    public void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            if (listRealEstate.isEmpty()) {
                return;
            }
            for (RealEstate realEstate : listRealEstate) {
                bw.write(realEstate.toString());
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        @Override
    public void deleteRE(RealEstate r) {
        listRealEstate.remove(r);
    }
    public static void main(String[] args) {
        RealEstateRepository r = new RealEstateRepository();
        r.readData();
        System.out.println( r.findEstateById("L-5678").toString());
    }



}
