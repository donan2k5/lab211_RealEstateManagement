/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import dal.RealEstateDAO;
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

    private RealEstateDAO reDAO = new RealEstateDAO();

    @Override
    public RealEstate findEstateById(String id) {
        return reDAO.get(Integer.parseInt(id));
    }
    
    @Override
    public List<RealEstate> readData() {
        return reDAO.list();
    }

    @Override
    public void save() {

    }

    @Override
    public void deleteRE(RealEstate r) {

    }

    public RealEstate delete(RealEstate r) {
        return reDAO.delete(Integer.parseInt(r.getID()));
    }
    public void update(RealEstate r) {
        reDAO.update(r);
    } 
    public static void main(String[] args) {
        RealEstateRepository n = new RealEstateRepository();
        List<RealEstate> l = new ArrayList<>();
        l = n.readData();
        for (RealEstate realEstate : l) {
            System.out.println(realEstate.toString());
        }
    }

    public void updateRealEstateStatus(int reid) {
        reDAO.updateRealEstateStatus(reid);
    }
}
