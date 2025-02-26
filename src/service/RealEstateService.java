/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Apartment;
import model.House;
import model.Land;
import model.RealEstate;
import model.Villa;
import repository.RealEstateRepository;
import view.RealEstateView;

/**
 *
 * @author DELL
 */
public class RealEstateService implements IRealEstateService {

    private RealEstateRepository reRepo = new RealEstateRepository();
    private List<RealEstate> reList = reRepo.readData();
    private RealEstateView reView = new RealEstateView();

    public boolean isExistREInSystem(String id) {
        return reRepo.findEstateById(id) != null;
    }

    @Override
    public List<House> getListHouse() {
        List<House> houseList = new ArrayList<>();
        for (RealEstate realEstate : reList) {
            if (realEstate.getID().startsWith("H")) {
                houseList.add((House) realEstate);
            }
        }
        return houseList;
    }

    @Override
    public List<Land> getListLand() {
        List<Land> landList = new ArrayList<>();
        for (RealEstate realEstate : reList) {
            if (realEstate.getID().startsWith("L")) {
                landList.add((Land) realEstate);
            }
        }
        return landList;
    }

    @Override
    public List<Villa> getListVilla() {
        List<Villa> villaList = new ArrayList<>();
        for (RealEstate realEstate : reList) {
            if (realEstate.getID().startsWith("VL")) {
                villaList.add((Villa) realEstate);
            }
        }
        return villaList;
    }

    @Override
    public List<Apartment> getListApartment() {
        List<Apartment> apartmentList = new ArrayList<>();
        for (RealEstate realEstate : reList) {
            if (realEstate.getID().startsWith("APT")) {
                apartmentList.add((Apartment) realEstate);
            }
        }
        return apartmentList;
    }

    @Override
    public List<RealEstate> getListRealEstate() {
        return reList;
    }

    @Override
    public void add(RealEstate t) {
        if (isExistREInSystem(t.getID())) {
            return;
        }
        reList.add(t);
        reRepo.save();
    }

    @Override
    public void delete(String id) {
        RealEstate selectedRealEstate = reRepo.findEstateById(id);
        if (selectedRealEstate == null) {
            return;
        }
        reRepo.deleteRE(selectedRealEstate);
        reRepo.save();
    }

    @Override
    public void update(String id) {
        RealEstate selectedRE = reRepo.findEstateById(id);
        reView.menuEdit(selectedRE);
        reRepo.save();
    }

    public List<RealEstate> searchByCriteria(Map<String, Object> criteria, String typeRE) {
        List<RealEstate> results = new ArrayList<>();
        for (RealEstate realEstate : reList) {
            boolean matches = true;
            if (!realEstate.getClass().getSimpleName().equalsIgnoreCase(typeRE)) {
                continue;
            }
            if (criteria.containsKey("area") && realEstate.getArea() < (double) criteria.get("area")) {
                matches = false;
            }
            if (criteria.containsKey("price") && realEstate.getPrice() < (double) criteria.get("price")) {
                matches = false;
            }
            if (realEstate instanceof Land land) {
                if (criteria.containsKey("landtype") && !land.getLandType().equalsIgnoreCase((String) criteria.get("landType"))) {
                    matches = false;
                }
            }
            if (realEstate instanceof House house) {
                if (criteria.containsKey("floors") && house.getFloorCount() < (int) criteria.get("floors")) {
                    matches = false;
                }
                if (criteria.containsKey("rooms") && house.getRoomCount() < (int) criteria.get("rooms")) {
                    matches = false;
                }
                if (realEstate instanceof Villa villa) {
                    if (criteria.containsKey("poolArea") && villa.getPoolArea() < (double) criteria.get("poolArea")) {
                        matches = false;
                    }
                } else if (realEstate instanceof Apartment apartment) {
                    if (criteria.containsKey("services") && !apartment.getAdditionalService().contains((String) criteria.get("services"))) {
                        matches = false;
                    }
                }
            }
            if (matches) {
                results.add(realEstate);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        RealEstateService r = new RealEstateService();
        RealEstateView reView = new RealEstateView();
        String typeRE = reView.getUserChooseREType();
        Map<String, Object> criteria = reView.getUserSearchByCriteria(typeRE);
        List<RealEstate> reList = r.searchByCriteria(criteria, typeRE);
        reView.displaySearchResults(reList);
    }
}
