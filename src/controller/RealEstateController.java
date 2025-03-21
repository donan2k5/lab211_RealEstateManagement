package controller;

import java.util.List;
import java.util.Map;
import model.RealEstate;
import model.Transaction;
import model.User;
import repository.impl.RealEstateRepository;
import repository.impl.TransactionRepository;
import service.AuthenticationService;
import service.impl.RealEstateService;
import service.impl.AuthenticationServiceImpl;
import service.impl.TransactionService;
import utils.TransactionValidation;
import service.impl.UserServiceImpl;
import utils.Utils;
import view.AuthenticationView;
import view.Menu;
import view.RealEstateView;
import view.TransactionView;
import view.UserManagementView;
import view.Validation;

public class RealEstateController extends Menu {
    private final TransactionValidation tval = new TransactionValidation();
    private final TransactionRepository transRepo = new TransactionRepository();
    private final TransactionService transService = new TransactionService();
    private final TransactionView tvi = new TransactionView();
    private final AuthenticationView authView = new AuthenticationView();
    private final AuthenticationService authService = new AuthenticationServiceImpl();
    private final RealEstateService reSer = new RealEstateService();
    private final RealEstateView reView = new RealEstateView();
    private final RealEstateRepository reRepo = new RealEstateRepository();
    private final UserServiceImpl userservice = new UserServiceImpl();
    private Validation v = new Validation();
    private final UserManagementView umView = new UserManagementView();

    public RealEstateController() {
        super("=== Register or Login to continue ===", new String[]{
            "Register",
            "Login",
            "Exit"
        });
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 ->
                register();
            case 2 ->
                login();
            case 3 ->
                System.exit(0);
        }
    }

    private void register() {
        User user = authView.getUserDetails();
        authService.register(user);
        authView.displayRegisterSuccessfully();
    }

    private void login() {
        while (true) {
            User request_user = authView.getUserNamePassword();
            User user = authService.login(request_user);
            if (user != null) {
                authView.displayLoginSucessfully();
                menuManagement();
            } else {
                authView.displayLoginError();
                if (!Utils.getAStringFormatYN("Do you want to login again?(Y/N): ", "Enter again, Y/N: ")) {
                    break;
                }
            }
        }
    }

    private void logout() {
        User user = authService.logout();
        authView.displayLogout(user.getUsername());
        this.run();
    }

    //Menu tổng sau khi login thành công
    private void menuManagement() {
        User user = authService.getLoggedInUser();
        int roleId = user.getRoleId();
        String menuTitle = "=== Welcome to Real Estate Web ===";

        String[] options = {
            "Show menu",
            "Logout"
        };

        Menu userMenu = new Menu(menuTitle, options) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 -> {
                        if (roleId == 0) {
                            showMenuAdmin();
                        } else {
                            showMenuCustomer();
                        }
                    }
                    case 2 ->
                        logout();
                }
            }
        };

        userMenu.run();
    }

    //Menu của admin
    private void showMenuAdmin() {
        String[] options = {
            "User management",
            "Real Estate management",
            "Real Estate Statistics", //Danh
            "Return to main menu"
        };
        Menu menu = new Menu("Admin Menu", options) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 ->
                        userManagement();
                    case 2 ->
                        realEstateAdminManagement();
                    case 3 ->
                        System.out.println("Danh tiến hành gọi hàm để thống kê (Tạo 1 menu con để gọi tiếp)");
                    case 4 -> {
                        this.stop();
                    }
                }
            }
        };
        menu.run();
    }

    //Menu của user
    private void showMenuCustomer() {
        String[] options = {
            "Edit information",
            "Real Estate",
            "Return to main menu"
        };
        Menu menu = new Menu("Customer Menu", options) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 ->
                        editInformation();
                    case 2 ->
                        realEstateCustomerManagement();
                    case 3 -> {
                        this.stop();
                    }
                }
            }
        };
        menu.run();
    }

    //Hàm của Admin dùng để quản lý người dùng (Module 5, Anh Thắng)
    private void userManagement() {
        String[] options = {
            "List all user",
            "Delete user",
            "Edit information",
            "Return to main menu"
        };
        Menu menu = new Menu("Admin User Management", options) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 ->
                        listAllUsers();
                    case 2 ->
                        deleteUser();
                    case 3 ->
                        editInformation();
                    case 4 -> {
                        this.stop();
                    }
                }
            }
        };
        menu.run();
    }

    private void listAllUsers() {
        List<User> users = userservice.getAllUsers();
        umView.displayUserList(users);
    }

    // Task 2: Xóa user theo kiểu xóa mềm (set isDelete = 1)
    private void deleteUser() {
        int id = umView.promptForUserId();

        userservice.deleteUser(id);

    }

    // Task 3: Chỉnh sửa thông tin cá nhân của user đang đăng nhập
    private void editInformation() {
        userservice.editInformation();
    }

    // Hàm của ADMIN dùng để quản lý bất động sản (Đăng + Khôi)
    private void realEstateAdminManagement() {
        String[] options = {
            "Display list real estate",
            "Add new real estate",
            "Delete",
            "Edit",
            "Find RE by criteria",
            "Transaction Management", //Khôi
            "Return to main menu"
        };
        Menu menu = new Menu("Admin Menu", options) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 ->
                        managementDisplayListREAdmin();
                    case 2 ->
                        managementAddNewREAdmin();
                    case 3 ->
                        managementDeleteREAdmin();
                    case 4 ->
                        managementEditREAdmin();
                    case 5 ->
                        managementSearchREByCriteria();
                    case 6 ->
                        transService.checkStatusTransaction();
                    case 7 -> {
                        this.stop();
                    }
                }
            }
        };
        menu.run();
    }

    // Hàm của Customer dùng để quản lý bất động sản (Đăng + Khôi)
    private void realEstateCustomerManagement() {
        String[] options = {
            "List all Real Estate", //Dang
            "Search Real Estate", //Dang
            "Edit Real Estate of this customer sell", //Dang
            "Delete Real Estate of this customer sell",//Dang
            "Add Real Estate", //Dang
            "Buy Real Estate", //Khôi 
            "View contract", //Khôi (Xem lại tất cả các hợp đồng về căn nhà mà mình đã bán và mua
            "Return to main menu"
        };
        Menu menu = new Menu("Customer Menu", options) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 ->
                        System.out.println("Hien thi tat ca bds");
                    case 2 ->
                        managementSearchREByCriteria();
                    case 3 ->
                        System.out.println("Viet ham edit bds ma user so huu");
                    case 4 ->
                        System.out.println("Xoa bds ma user so huu");
                    case 5 ->
                        System.out.println("Add"); //Đăng cần sửa hàm này để khi add sẽ có tên chủ sở hữu chính là id của ông user này
                    case 6 ->{
                        Transaction newTrans = tvi.createTransactionBuyRE(authService.getLoggedInUser().getUserId());
                        if (newTrans != null) transService.add(newTrans);
                    }
                    case 7 ->
                        tvi.displayListTransaction(transService.getAllTransactionByUserID(authService.getLoggedInUser().getUserId())); //viewTransactionHistory; //System.out.println("Gọi hàm liệt kê danh sách bđs mua, bán của user này"); //Khôi
                    case 8 -> {
                        this.stop();
                    }
                }
            }
        };
        menu.run();
    }

    private void managementDisplayListREAdmin() {
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

    public void managementAddNewREAdmin() {
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
                        reSer.add(reView.getInformationHouse("admin"));
                        reView.addSuccessRE("HOUSE");
                    }
                    case 2 -> {
                        reSer.add(reView.getInformationVilla("admin"));
                        reView.addSuccessRE("VILLA");
                    }
                    case 3 -> {
                        reSer.add(reView.getInformationLand("admin"));
                        reView.addSuccessRE("LAND");
                    }
                    case 4 -> {
                        reSer.add(reView.getInformationApartment("admin"));
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

    public void managementDeleteREAdmin() {
        reView.displayListHouse(reSer.getListHouse());
        reView.displayListVilla(reSer.getListVilla());
        reView.displayListLand(reSer.getListLand());
        reView.displayListApartment(reSer.getListApartment());
        int id = 0;
        while (true) {
            id = v.getValidInteger("Enter id of RE you want to delete: ");
            if (!reSer.isExistREInSystem(String.valueOf(id))) {
                System.out.println("This id not existed in system.");
            }
            break;
        }
        reRepo.readData();
        RealEstate selectedRE = reRepo.findEstateById(String.valueOf(id));
        reSer.delete(String.valueOf(id));
        reView.displayNotification(selectedRE, "delete ");
    }

    public void managementEditREAdmin() {
        int id = 0;
        while (true) {
            id = v.getValidInteger("Enter id of RE you want to edit: ");
            if (!reSer.isExistREInSystem(String.valueOf(id))) {
                System.out.println("This id not existed in system.");
                continue;
            }
            break;
        }
        reSer.update(String.valueOf(id));
    }

    public void managementSearchREByCriteria() {
        String typeRE = reView.getUserChooseREType("search");
        Map<String, Object> criteria = reView.getUserSearchByCriteria(typeRE);
        List<RealEstate> reList = reSer.searchByCriteria(criteria, typeRE);
        reView.displaySearchResults(reList);
    }
    
    public static void main(String[] args) {
        RealEstateController realEstateController = new RealEstateController();
        realEstateController.run();
    }
}
