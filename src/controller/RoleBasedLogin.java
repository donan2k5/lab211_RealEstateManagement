//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class RoleBasedLogin {
//    public static void main(String[] args) {
//        // Danh sách tài khoản (username -> password)
//        Map<String, String> users = new HashMap<>();
//        users.put("tpnqb", "123");  // User thường
//        users.put("admin", "admin123"); // Admin
//        
//        // Danh sách vai trò (username -> role)
//        Map<String, String> roles = new HashMap<>();
//        roles.put("tpnqb", "User");
//        roles.put("admin", "Admin");
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {  // Cho phép đăng nhập lại sau khi logout
//            System.out.print("\nNhập username: ");
//            String username = scanner.nextLine();
//
//            System.out.print("Nhập password: ");
//            String password = scanner.nextLine();
//
//            // Kiểm tra thông tin đăng nhập
//            if (users.containsKey(username) && users.get(username).equals(password)) {
//                String role = roles.get(username);
//                System.out.println("Đăng nhập thành công! Vai trò của bạn là: " + role);
//
//                boolean loggedIn = true;
//                while (loggedIn) { // Vòng lặp menu
//                    loggedIn = showMenu(scanner, role);
//                }
//
//            } else {
//                System.out.println("Sai username hoặc password!");
//            }
//        }
//    }
//
//    // Hiển thị menu và xử lý lựa chọn
//    public static boolean showMenu(Scanner scanner, String role) {
//        System.out.println("\n===== MENU =====");
//        if (role.equals("Admin")) {
//            System.out.println("1. Quản lý người dùng");
//            System.out.println("2. Xem báo cáo");
//            System.out.println("3. Cấu hình hệ thống");
//        } else if (role.equals("User")) {
//            System.out.println("1. Xem thông tin cá nhân");
//            System.out.println("2. Thay đổi mật khẩu");
//        }
//        System.out.println("0. Logout");
//
//        System.out.print("Chọn chức năng: ");
//        int choice = scanner.nextInt();
//        scanner.nextLine(); // Xử lý ký tự xuống dòng
//
//        if (choice == 0) {
//            System.out.println("Bạn đã logout.\n");
//            return false; // Kết thúc vòng lặp menu, quay lại màn hình đăng nhập
//        } else {
//            System.out.println("Bạn đã chọn chức năng " + choice + ". (Chức năng chưa được triển khai)");
//            return true; // Tiếp tục hiển thị menu
//        }
//    }
//}