package controller;

import model.User;
import service.impl.AuthenticationServiceImpl;
import service.AuthenticationService;
import utils.Utils;
import view.AuthenticationView;
import view.Menu;
import view.Validation;

public class RealEstateController extends Menu {

    private final AuthenticationView authView = new AuthenticationView();
    private final AuthenticationService authService = new AuthenticationServiceImpl();
    private final Validation validation = new Validation(); // Thêm đối tượng validation

    public RealEstateController() {
        super("=== Welcome to Real Estate Web ===", new String[] {
            "Register",
            "Login",
            "Exit"
        });
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> register();
            case 2 -> login();
            case 3 -> System.exit(0);
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
            "Update Information",
            "Logout",
            "Delete User" // Thêm option cho Admin để xóa người dùng
        };

        Menu userMenu = new Menu(menuTitle, options) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 -> {
                        User updatedUser = authView.updateUserDetails(user); // Cập nhật thông tin người dùng
                        if (authService.updateUser(updatedUser) != null) {
                            System.out.println("User information updated successfully.");
                        } else {
                            System.out.println("Failed to update user information.");
                        }
                    }
                    case 2 -> logout();
                    case 3 -> {
                        if (roleId == 0) {  // Kiểm tra quyền Admin
                            deleteUser();
                        } else {
                            System.out.println("You do not have permission to delete users.");
                        }
                    }
                }
            }
        };

        userMenu.run();
    }

    private void deleteUser() {
    int userId = validation.checkValidInt("Enter user ID to delete: ", "Invalid ID, try again", 1, Integer.MAX_VALUE);
    if (authService.deleteUser(userId)) {
        System.out.println("User deleted successfully.");
    } else {
        System.out.println("Failed to delete user.");
    }
}


    public static void main(String[] args) {
        RealEstateController realEstateController = new RealEstateController();
        realEstateController.run();
    }
}
