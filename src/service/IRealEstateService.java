package service;

import java.util.List;
import model.Apartment;
import model.House;
import model.Land;
import model.RealEstate;
import model.Villa;

public interface IRealEstateService extends Service<RealEstate> {

    List<House> getListHouse();

    List<Land> getListLand();

    List<Villa> getListVilla();

    List<Apartment> getListApartment();
    List<RealEstate> getListRealEstate();
}
