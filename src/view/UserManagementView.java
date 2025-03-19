package view;

import java.util.List;
import java.util.Scanner;
import model.User;

public class UserManagementView {
    private final Scanner sc = new Scanner(System.in);
    
    // Hiển thị danh sách user (chỉ những user chưa bị xóa mềm)
    public void displayUserList(List<User> users) {
        if(users.isEmpty()){
            System.out.println("There are no users to display.");
            return;
        }
        System.out.println("User List :");
        System.out.printf("%-10s %-15s %-15s %-15s %-15s %-25s\n", 
                "ID", "Username", "LastName", "FirstName", "Phone", "Email");
        for(User user : users) {
            System.out.printf("%-10d %-15s %-15s %-15s %-15s %-25s\n",
                    user.getUserId(), user.getUsername(), user.getLastName(),
                    user.getFirstName(), user.getPhone(), user.getEmail());
        }
    }
    
    // Nhập ID của user cần thao tác (ví dụ: để xóa)
    public int promptForUserId() {
        System.out.print("Enter user ID: ");
        while (true) {
            try {
                int id = Integer.parseInt(sc.nextLine().trim());
                return id;
            } catch (NumberFormatException e) {
                System.out.print("Invalid ID, please re-enter : ");
            }
        }
    }
    
    // Hiển thị menu chỉnh sửa thông tin cá nhân và trả về lựa chọn
    // 1. Số điện thoại, 2. Email, 3. Giới tính, 4. Họ, 5. Tên, 6. Lưu và thoát
    public int displayEditMenu() {
        System.out.println("\n--- Menu Chỉnh Sửa Thông Tin ---");
        System.out.println("1. Phone number");
        System.out.println("2. Email");
        System.out.println("3. Gender");
        System.out.println("4. Last Name");
        System.out.println("5. First Name");
        System.out.println("6. Save and exit");
        System.out.print("Select the item to edit: ");
        while (true) {
            try {
                int choice = Integer.parseInt(sc.nextLine().trim());
                if (choice >= 1 && choice <= 6) {
                    return choice;
                } else {
                    System.out.print("Invalid selection, please re-enter: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid selection, please re-enter: ");
            }
        }
    }
    
    // Nhập giá trị mới cho một field cụ thể (ví dụ: Phone, Email,...)
    public String promptForNewValue(String field) {
        System.out.print("Enter new value for " + field + ": ");
        return sc.nextLine().trim();
    }
}
