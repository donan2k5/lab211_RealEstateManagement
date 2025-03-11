//package controller;
//
//import view.UserManagementView;
//import view.Validation;
//import model.User;
//import repository.impl.UserRepositoryImpl;
//import service.AuthenticationService;
//import service.impl.AuthenticationServiceImpl;
//import java.util.List;
//import java.util.Scanner;
//import service.UserService;
//import service.impl.UserServiceImpl;
//
//public class UserManagementController {
//    
//    private final Validation validation = new Validation();
//    
//    private final UserRepositoryImpl userRepo = new UserRepositoryImpl();
//    private final AuthenticationService authService = new AuthenticationServiceImpl();
//    
//    private final Scanner sc = new Scanner(System.in);
//    
//    // Menu chính cho quản lý người dùng dành cho Admin
//    public void runUserManagement() {
//        boolean running = true;
//        while (running) {
//            System.out.println("\n--- Admin User Management ---");
//            System.out.println("1. List all user");
//            System.out.println("2. Delete user");
//            System.out.println("3. Edit information");
//            System.out.println("4. Return to main menu");
//            System.out.print("Select function: ");
//            int choice = validation.getValidInteger("Enter selection: ");
//            switch (choice) {
//                case 1:
//                    listAllUsers();
//                    break;
//                case 2:
//                    deleteUser();
//                    break;
//                case 3:
//                    editInformation();
//                    break;
//                case 4:
//                    running = false;
//                    break;
//                default:
//                    System.out.println("Invalid selection, please try again!");
//            }
//        }
//    }
//    
//    // Goi ham
//    private void listAllUsers() {
//        List<User> users = userservice.getAllUsers();
//        umView.displayUserList(users);
//    }
//    
//    // Task 2: Xóa user theo kiểu xóa mềm (set isDelete = 1)
//    private void deleteUser() {
//        int id = umView.promptForUserId();
//        
//        userservice.deleteUser(id);
//       
//    }
//    
//    // Task 3: Chỉnh sửa thông tin cá nhân của user đang đăng nhập
//    private void editInformation() {
//        userservice.editInformation();
//    }
//    
//    // Main method demo
//    public static void main(String[] args) {
//        
//    }
//}
