package controller;

import view.UserManagementView;
import view.Validation;
import model.User;
import repository.impl.UserRepositoryImpl;
import service.AuthenticationService;
import service.impl.AuthenticationServiceImpl;
import java.util.List;
import java.util.Scanner;

public class UserManagementController {
    
    private final Validation validation = new Validation();
    private final UserManagementView umView = new UserManagementView();
    private final UserRepositoryImpl userRepo = new UserRepositoryImpl();
    private final AuthenticationService authService = new AuthenticationServiceImpl();
    private final Scanner sc = new Scanner(System.in);
    
    // Menu chính cho quản lý người dùng dành cho Admin
    public void runUserManagement() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Admin User Management ---");
            System.out.println("1. List all user");
            System.out.println("2. Delete user");
            System.out.println("3. Edit information");
            System.out.println("4. Return to main menu");
            System.out.print("Select function: ");
            int choice = validation.getValidInteger("Enter selection: ");
            switch (choice) {
                case 1:
                    listAllUsers();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    editInformation();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection, please try again!");
            }
        }
    }
    
    // Task 1: Hiển thị danh sách tất cả user chưa bị xóa mềm
    private void listAllUsers() {
        List<User> users = userRepo.listAllUsers();
        umView.displayUserList(users);
    }
    
    // Task 2: Xóa user theo kiểu xóa mềm (set isDelete = 1)
    private void deleteUser() {
        int id = umView.promptForUserId();
        User userToDelete = userRepo.findById(id);
        if (userToDelete == null) {
            System.out.println("User does not exist or has been deleted.");
            return;
        }
        userRepo.deleteUser(id);
        System.out.println("User " + userToDelete.getUsername() + " has been successfully soft deleted.");
    }
    
    // Task 3: Chỉnh sửa thông tin cá nhân của user đang đăng nhập
    private void editInformation() {
        User currentUser = authService.getLoggedInUser();
        if (currentUser == null) {
            System.out.println("No user logged in, please log in before editing information.");
            return;
        }
        boolean editing = true;
        while (editing) {
            int editChoice = umView.displayEditMenu();
            switch (editChoice) {
                case 1: // Chỉnh sửa số điện thoại
                    String newPhone = umView.promptForNewValue("Phone Number");
                    currentUser.setPhone(newPhone);
                    break;
                case 2: // Chỉnh sửa email
                    String newEmail = umView.promptForNewValue("Email");
                    currentUser.setEmail(newEmail);
                    break;
                case 3: // Chỉnh sửa giới tính
                    String newGender = umView.promptForNewValue("Gender");
                    currentUser.setGender(newGender);
                    break;
                case 4: // Chỉnh sửa họ
                    String newLastName = umView.promptForNewValue("Last Name");
                    currentUser.setLastName(newLastName);
                    break;
                case 5: // Chỉnh sửa tên
                    String newFirstName = umView.promptForNewValue("First Name");
                    currentUser.setFirstName(newFirstName);
                    break;
                case 6:
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid selection, please try again!");
            }
        }
        userRepo.save(currentUser);
        System.out.println("Information updated successfully!");
    }
    
    // Main method demo
    public static void main(String[] args) {
        // Giả lập đăng nhập (demo)
        AuthenticationService authService = new AuthenticationServiceImpl();
        User demoUser = new User.UserBuilder()
                .userId(1)
                .username("admin")
                .password("admin123")
                .lastName("Nguyen")
                .firstName("An")
                .phone("0123456789")
                .email("admin@example.com")
                .gender("Male")
                .roleId(0)
                .build();
        ((AuthenticationServiceImpl) authService).setLoggedInUser(demoUser);
        
        UserManagementController controller = new UserManagementController();
        controller.runUserManagement();
    }
}
