package controller;

import model.User;
import service.AuthenticationService;
import service.impl.AuthenticationServiceImpl;
import utils.Utils;
import view.AuthenticationView;
import view.Menu;

public class RealEstateController extends Menu {

    private final AuthenticationView authView = new AuthenticationView();
    private final AuthenticationService authService = new AuthenticationServiceImpl();

    public RealEstateController() {
        super("=== Welcome to Real Estate Web ===", new String[]{
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
                realEstateManagement();
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
    
    
    private void realEstateManagement() {
        User user = authService.getLoggedInUser();
        int roleId = user.getRoleId();
        String menuTitle = roleId == 0 ? "Admin Menu" : "User Menu";

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
                            showMenuUser();
                        }
                    }
                    case 2 ->
                        logout();
                }
            }
        };

        userMenu.run();
    }

    private void showMenuAdmin() {
        String[] options = {
            "Edit information",
            "Real Estate",
            "Admin",
            "Return to main menu"
        };
        Menu menu = new Menu("Admin Management", options) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 ->
                        System.out.println("Sua doi thong tin ca nhan");
                    case 2 ->
                        System.out.println("Them xoa sua bat dong san");
                    case 3 ->
                        System.out.println("admin");
                    case 4 -> {
                        this.stop();
                    }
                }
            }
        };
        menu.run();
    }

    private void showMenuUser() {
        String[] options = {
            "1",
            "2",
            "user",
            "Return to main menu"
        };
        Menu menu = new Menu("Employee Management", options) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 ->
                        System.out.println(1);
                    case 2 ->
                        System.out.println(2);
                    case 3 ->
                        System.out.println("user");
                    case 4 -> {
                        this.stop();
                    }
                    default ->
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        };
        menu.run();
    }

    public static void main(String[] args) {
        RealEstateController realEstateController = new RealEstateController();
        realEstateController.run();
    }

}
