/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package view;

import java.util.List;
import model.Apartment;
import model.House;
import model.Land;
import model.RealEstate;
import model.Villa;

/**
 *
 * @author DELL
 */
public interface IRealEstateView {
//    void displayListRealEstate(List<RealEstate> reList);
    void displayListHouse(List<House> houseList);
    void displayListLand(List<Land> landList);
    void displayListVilla(List<Villa> villaList);
    void displayListApartment(List<Apartment> apartmenList);
}
