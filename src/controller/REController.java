/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.util.Map;
import model.RealEstate;
import repository.RealEstateRepository;
import service.RealEstateService;
import view.Menu;
import view.RealEstateView;
import view.Validation;

/**
 *
 * @author DELL
 */
public class REController extends Menu<String> {

    private RealEstateService reSer = new RealEstateService();
    private RealEstateView reView = new RealEstateView();
    private RealEstateRepository reRepo = new RealEstateRepository();
    private Validation v = new Validation();

    public REController(String title, String[] mc) {
        super(title, mc);
    }

    private void managementDisplayListRE() {
        String[] options = {
            "Display list house",
            "Display list villa",
            "Display list land",
            "Display list apartment",
            "Return to main menu"
        };
        Menu displayingREMenu = new Menu("Display List RE", options) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 ->
                        reView.displayListHouse(reSer.getListHouse());
                    case 2 ->
                        reView.displayListVilla(reSer.getListVilla());
                    case 3 ->
                        reView.displayListLand(reSer.getListLand());
                    case 4 ->
                        reView.displayListApartment(reSer.getListApartment());
                    case 5 -> {
                        this.stop();
                    }
                    default ->
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        };
        displayingREMenu.run();
    }

    public void managementAddNewRE() {
        String[] options = {
            "Add new house",
            "Add new villa",
            "Add new land",
            "Add new apartment",
            "Return to main menu"
        };
        Menu displayingREMenu = new Menu("Add New RE", options) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 -> {
                        reSer.add(reView.getInformationHouse());
                        reView.addSuccessRE("HOUSE");
                    }
                    case 2 -> {
                        reSer.add(reView.getInformationVilla());
                        reView.addSuccessRE("VILLA");
                    }
                    case 3 -> {
                        reSer.add(reView.getInformationLand());
                        reView.addSuccessRE("LAND");
                    }
                    case 4 -> {
                        reSer.add(reView.getInformationApartment());
                        reView.addSuccessRE("APARTMENT");
                    }
                    case 5 -> {
                        this.stop();
                    }
                    default ->
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        };
        displayingREMenu.run();
    }

    public void managementDeleteRE() {
        reView.displayListHouse(reSer.getListHouse());
        reView.displayListVilla(reSer.getListVilla());
        reView.displayListLand(reSer.getListLand());
        reView.displayListApartment(reSer.getListApartment());
        String id = "";
        while (true) {
            id = v.getStringRegex("Enter id of RE you want to delete: ", "^.*$", "Invalid input, enter again: ");
            if (!reSer.isExistREInSystem(id)) {
                System.out.println("Invalid input, pls try again.");
                continue;
            }
            break;
        }
        reRepo.readData();
        RealEstate selectedRE = reRepo.findEstateById(id);
        System.out.println("You have just delete " + reView.displayBasicInformation(selectedRE) + " from sytem.");
        reSer.delete(id);
    }

    public void managementEditRE() {
        String id = "";
        while (true) {
            id = v.getStringRegex("Enter id of RE you want to edit: ", "^.*$", "Invalid input, enter again: ");
            if (!reSer.isExistREInSystem(id)) {
                System.out.println("Invalid input, pls try again.");
                continue;
            }
            break;
        }
        reSer.update(id);
    }

    public void managementSearchREByCriteria() {
        String typeRE = reView.getUserChooseREType();
        Map<String, Object> criteria = reView.getUserSearchByCriteria(typeRE);
        List<RealEstate> reList = reSer.searchByCriteria(criteria, typeRE);
        reView.displaySearchResults(reList);
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1 ->
                managementDisplayListRE();
            case 2 ->
                managementAddNewRE();
            case 3 ->
                managementDeleteRE();
            case 4 ->
                managementEditRE();
            case 5 ->
                managementSearchREByCriteria();
            case 6 ->
                System.out.println("Exiting...");
        }
    }

    public static void main(String[] args) {
        REController re = new REController("Real Estate System", new String[]{
            "Display list real estate",
            "Add new real estate",
            "Delete",
            "Edit",
            "Find RE by criteria",
            "Exit"
        });
        re.run();
    }

}
