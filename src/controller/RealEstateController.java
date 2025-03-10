//package controller;
//
//import java.util.List;
//import java.util.Map;
//import model.RealEstate;
//import model.User;
//import repository.impl.RealEstateRepository;
//import service.AuthenticationService;
//import service.impl.RealEstateService;
//import service.impl.AuthenticationServiceImpl;
//import utils.Utils;
//import view.AuthenticationView;
//import view.Menu;
//import view.RealEstateView;
//import view.Validation;
//
//public class RealEstateController extends Menu {
//
//    private final AuthenticationView authView = new AuthenticationView();
//    private final AuthenticationService authService = new AuthenticationServiceImpl();
//    private final RealEstateService reSer = new RealEstateService();
//    private final RealEstateView reView = new RealEstateView();
//    private final RealEstateRepository reRepo = new RealEstateRepository();
//    private Validation v = new Validation();
//
//    public RealEstateController() {
//        super("=== Register or Login to continue ===", new String[]{
//            "Register",
//            "Login",
//            "Exit"
//        });
//    }
//
//    @Override
//    public void execute(int choice) {
//        switch (choice) {
//            case 1 ->
//                register();
//            case 2 ->
//                login();
//            case 3 ->
//                System.exit(0);
//        }
//    }
//
//    private void register() {
//        User user = authView.getUserDetails();
//        authService.register(user);
//        authView.displayRegisterSuccessfully();
//    }
//
//    private void login() {
//        while (true) {
//            User request_user = authView.getUserNamePassword();
//            User user = authService.login(request_user);
//            if (user != null) {
//                authView.displayLoginSucessfully();
//                menuManagement();
//            } else {
//                authView.displayLoginError();
//                if (!Utils.getAStringFormatYN("Do you want to login again?(Y/N): ", "Enter again, Y/N: ")) {
//                    break;
//                }
//            }
//        }
//    }
//
//    private void logout() {
//        User user = authService.logout();
//        authView.displayLogout(user.getUsername());
//        this.run();
//    }
//    
//    //Menu tổng sau khi login thành công
//    private void menuManagement() {
//        User user = authService.getLoggedInUser();
//        int roleId = user.getRoleId();
//        String menuTitle = "=== Welcome to Real Estate Web ===";
//
//        String[] options = {
//            "Show menu",
//            "Logout"
//        };
//
//        Menu userMenu = new Menu(menuTitle, options) {
//            @Override
//            public void execute(int choice) {
//                switch (choice) {
//                    case 1 -> {
//                        if (roleId == 0) {
//                            showMenuAdmin();
//                        } else {
//                            showMenuCustomer();
//                        }
//                    }
//                    case 2 ->
//                        logout();
//                }
//            }
//        };
//
//        userMenu.run();
//    }
//
//    //Menu của admin
//    private void showMenuAdmin() {
//        String[] options = {
//            "User management",
//            "Real Estate management",
//            "Real Estate Statistics", //Danh
//            "Return to main menu"
//        };
//        Menu menu = new Menu("Admin Menu", options) {
//            @Override
//            public void execute(int ch) {
//                switch (ch) {
//                    case 1 ->
//                        userManagement();
//                    case 2 ->
//                        realEstateAdminManagement();
//                    case 3 ->
//                        System.out.println("Danh tiến hành gọi hàm để thống kê (Tạo 1 menu con để gọi tiếp)");
//                    case 4 -> {
//                        this.stop();
//                    }
//                }
//            }
//        };
//        menu.run();
//    }
//    
//    //Menu của user
//    private void showMenuCustomer() {
//        String[] options = {
//            "Edit information",
//            "Real Estate",
//            "Return to main menu"
//        };
//        Menu menu = new Menu("Customer Menu", options) {
//            @Override
//            public void execute(int ch) {
//                switch (ch) {
//                    case 1 ->
//                        System.out.println("Gọi hàm update thay vì gọi nguyên 1 menu như admin");
//                    case 2 ->
//                        realEstateCustomerManagement();
//                    case 3 -> {
//                        this.stop();
//                    }
//                }
//            }
//        };
//        menu.run();
//    }
//
//    //Hàm của Admin dùng để quản lý người dùng (Module 5, Anh Thắng)
//    private void userManagement() {
//        String[] options = {
//            "List all user",
//            "Delete user",
//            "Edit information",
//            "Return to main menu"
//        };
//        Menu menu = new Menu("Admin User Management", options) {
//            @Override
//            public void execute(int ch) {
//                switch (ch) {
//                    case 1 ->
//                        System.out.println("Hiển thị danh sách tất cả user");
//                    case 2 ->
//                        System.out.println("Xóa user (set isdeleted = 1");
//                    case 3 ->
//                        System.out.println("Gọi hàm edit thông tin cá nhân của admin");
//                    case 4 -> {
//                        this.stop();
//                    }
//                }
//            }
//        };
//        menu.run();
//    }
//
//    // Hàm của ADMIN dùng để quản lý bất động sản (Đăng + Khôi)
//    private void realEstateAdminManagement() {
//        String[] options = {
//            "List all Real Estate",
//            "Search Real Estate",
//            "Transaction Management", //Khôi
//            "Return to main menu"
//        };
//        Menu menu = new Menu("Admin Menu", options) {
//            @Override
//            public void execute(int ch) {
//                switch (ch) {
//                    case 1 ->
//                        managementDisplayListRE();
//                    case 2 ->
//                        managementSearchREByCriteria();
//                    case 3 ->
//                        System.out.println("Gọi hàm phê duyệt giao dịch"); //Khôi
//                    case 4 -> {
//                        this.stop();
//                    }
//                }
//            }
//        };
//        menu.run();
//    }
//    // Hàm của Customer dùng để quản lý bất động sản (Đăng + Khôi)
//    private void realEstateCustomerManagement() {
//        String[] options = {
//            "List all Real Estate",
//            "Search Real Estate",
//            "Edit Real Estate of this customer sell",
//            "Delete Real Estate of this customer sell",
//            "Sell Real Estate", //Khôi
//            "Buy Real Estate", //Khôi 
//            "View contract", //Khôi (Xem lại tất cả các hợp đồng về căn nhà mà mình đã bán và mua
//            "Return to main menu"
//        };
//        Menu menu = new Menu("Admin Menu", options) {
//            @Override
//            public void execute(int ch) {
//                switch (ch) {
//                    case 1 ->
//                        managementDisplayListRE();
//                    case 2 ->
//                        managementSearchREByCriteria();
//                    case 3 ->
//                        managementEditRE();
//                    case 4 ->
//                        managementDeleteRE();
//                    case 5 ->
//                        managementAddNewRE(); //Đăng cần sửa hàm này để khi add sẽ có tên chủ sở hữu chính là id của ông user này
//                    case 6 ->
//                        System.out.println("Gọi hàm mua bđs"); //Khôi 
//                    case 7 ->
//                        System.out.println("Gọi hàm liệt kê danh sách bđs mua, bán của user này"); //Khôi
//                    case 8 -> {
//                        this.stop();
//                    }
//                }
//            }
//        };
//        menu.run();
//    }
//    
//    private void managementDisplayListRE() {
//        String[] options = {
//            "Display list house",
//            "Display list villa",
//            "Display list land",
//            "Display list apartment",
//            "Return to main menu"
//        };
//        Menu displayingREMenu = new Menu("Display List RE", options) {
//            @Override
//            public void execute(int ch) {
//                switch (ch) {
//                    case 1 ->
//                        reView.displayListHouse(reSer.getListHouse());
//                    case 2 ->
//                        reView.displayListVilla(reSer.getListVilla());
//                    case 3 ->
//                        reView.displayListLand(reSer.getListLand());
//                    case 4 ->
//                        reView.displayListApartment(reSer.getListApartment());
//                    case 5 -> {
//                        this.stop();
//                    }
//                    default ->
//                        System.out.println("Invalid choice. Please try again.");
//                }
//            }
//        };
//        displayingREMenu.run();
//    }
//
//    public void managementAddNewRE() {
//        String[] options = {
//            "Add new house",
//            "Add new villa",
//            "Add new land",
//            "Add new apartment",
//            "Return to main menu"
//        };
//        Menu displayingREMenu = new Menu("Add New RE", options) {
//            @Override
//            public void execute(int ch) {
//                switch (ch) {
//                    case 1 -> {
//                        reSer.add(reView.getInformationHouse());
//                        reView.addSuccessRE("HOUSE");
//                    }
//                    case 2 -> {
//                        reSer.add(reView.getInformationVilla());
//                        reView.addSuccessRE("VILLA");
//                    }
//                    case 3 -> {
//                        reSer.add(reView.getInformationLand());
//                        reView.addSuccessRE("LAND");
//                    }
//                    case 4 -> {
//                        reSer.add(reView.getInformationApartment());
//                        reView.addSuccessRE("APARTMENT");
//                    }
//                    case 5 -> {
//                        this.stop();
//                    }
//                    default ->
//                        System.out.println("Invalid choice. Please try again.");
//                }
//            }
//        };
//        displayingREMenu.run();
//    }
//
//    public void managementDeleteRE() {
//        reView.displayListHouse(reSer.getListHouse());
//        reView.displayListVilla(reSer.getListVilla());
//        reView.displayListLand(reSer.getListLand());
//        reView.displayListApartment(reSer.getListApartment());
//        String id = "";
//        while (true) {
//            id = v.getStringRegex("Enter id of RE you want to delete: ", "^.*$", "Invalid input, enter again: ");
//            if (!reSer.isExistREInSystem(id)) {
//                System.out.println("Invalid input, pls try again.");
//                continue;
//            }
//            break;
//        }
//        reRepo.readData();
//        RealEstate selectedRE = reRepo.findEstateById(id);
//        System.out.println("You have just delete " + reView.displayBasicInformation(selectedRE) + " from sytem.");
//        reSer.delete(id);
//    }
//
//    public void managementEditRE() {
//        String id = "";
//        while (true) {
//            id = v.getStringRegex("Enter id of RE you want to edit: ", "^.*$", "Invalid input, enter again: ");
//            if (!reSer.isExistREInSystem(id)) {
//                System.out.println("Invalid input, pls try again.");
//                continue;
//            }
//            break;
//        }
//        reSer.update(id);
//    }
//
//    public void managementSearchREByCriteria() {
//        String typeRE = reView.getUserChooseREType();
//        Map<String, Object> criteria = reView.getUserSearchByCriteria(typeRE);
//        List<RealEstate> reList = reSer.searchByCriteria(criteria, typeRE);
//        reView.displaySearchResults(reList);
//    }
//
//    public static void main(String[] args) {
//        RealEstateController realEstateController = new RealEstateController();
//        realEstateController.run();
//    }
//
//}