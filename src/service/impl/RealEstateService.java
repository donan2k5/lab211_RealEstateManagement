/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dal.RealEstateDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Apartment;
import model.House;
import model.Land;
import model.RealEstate;
import model.Villa;
import repository.impl.RealEstateRepository;
import service.IRealEstateService;
import view.RealEstateView;

/**
 *
 * @author DELL
 */
public class RealEstateService implements IRealEstateService {

    private RealEstateRepository reRepo = new RealEstateRepository();
    private List<RealEstate> reList = reRepo.readData();
    private RealEstateView reView = new RealEstateView();
    private RealEstateDAO reDAO = new RealEstateDAO();

    public boolean isExistREInSystem(String id) {
        return reRepo.findEstateById(id) != null;
    }
    public RealEstate getSpecificRE(String id) {
        return reRepo.findEstateById(id);
    }
    @Override
    public List<House> getListHouse() {
        List<House> houseList = new ArrayList<>();
        for (RealEstate realEstate : reList) {
            if (realEstate instanceof House && !(realEstate instanceof Villa) && !(realEstate instanceof Apartment)) {
                houseList.add((House) realEstate);
            }
        }
        return houseList;
    }

    @Override
    public List<Land> getListLand() {
        List<Land> landList = new ArrayList<>();
        for (RealEstate realEstate : reList) {
            if (realEstate instanceof Land) {
                landList.add((Land) realEstate);
            }
        }
        return landList;
    }

    @Override
    public List<Villa> getListVilla() {
        List<Villa> villaList = new ArrayList<>();
        for (RealEstate realEstate : reList) {
            if (realEstate instanceof Villa) {
                villaList.add((Villa) realEstate);
            }
        }
        return villaList;
    }

    @Override
    public List<Apartment> getListApartment() {
        List<Apartment> apartmentList = new ArrayList<>();
        for (RealEstate realEstate : reList) {
            if (realEstate instanceof Apartment) {
                apartmentList.add((Apartment) realEstate);
            }
        }
        return apartmentList;
    }

    @Override
    public List<RealEstate> getListRealEstate() {
        return reList;
    }

    public List<RealEstate> getListRealEstateUser(int userid) {
        return reRepo.readDataUser(userid);
    }
    
    public boolean isREOwnedByUser(int userid, int reid) {
        boolean result = false;
        List<RealEstate> reL = this.getListRealEstateUser(userid);
        for (RealEstate realEstate : reL) {
            if (realEstate.getID().equals(String.valueOf(reid))) {
                result = true;
            }
        }
        return result;
    }
    public void updateREUser(int id, int userid) {
        RealEstate selectedRE = reRepo.findEstateById(String.valueOf(id));
        reView.menuEdit(selectedRE);
        reRepo.updateUser(selectedRE, userid);
    }
    public void deleteREUser(int id, int userid) {
        RealEstate selectedRE = reRepo.findEstateById(String.valueOf(id));
        reRepo.deleteREUser(selectedRE, userid);
    }
    public void addNewREUser(RealEstate r, int userid) {
        reRepo.addNewREUser(r, userid);
    }
    @Override
    public void add(RealEstate t) {
        reRepo.addNewREAdmin(t);
    }

    @Override
    public void delete(String id) {
        reDAO.delete(Integer.parseInt(id));
    }

    @Override
    public void update(String id) {
        RealEstate selectedRE = reRepo.findEstateById(id);
        reView.menuEdit(selectedRE);
        reRepo.update(selectedRE);
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

}
