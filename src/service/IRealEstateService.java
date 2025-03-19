/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

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
public interface IRealEstateService extends Service<RealEstate> {

    List<House> getListHouse();

    List<Land> getListLand();

    List<Villa> getListVilla();

    List<Apartment> getListApartment();

    List<RealEstate> getListRealEstate();
}
