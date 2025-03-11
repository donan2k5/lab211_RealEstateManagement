package service.impl;

import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import java.util.List;
import view.UserManagementView;


public class UserServiceImpl implements UserService {
    
    // Inject UserRepository thay vì khởi tạo trực tiếp để dễ mở rộng (có thể dùng DI framework như Spring sau này)
    private final UserRepositoryImpl userRepo = new UserRepositoryImpl();
    private final UserManagementView umView = new UserManagementView();
    
    // Lấy danh sách tất cả người dùng chưa bị xóa mềm
    @Override
    public List<User> getAllUsers() {
        return userRepo.listAllUsers(); // Gọi tới repository để lấy danh sách
    }
    
    // Xóa mềm một người dùng theo ID
    @Override
    public void deleteUser(int userId) {
        User userToDelete = userRepo.findById(userId);
        if (userToDelete == null) {
            System.out.println("User không tồn tại hoặc đã bị xóa.");
            return;
        }
        userRepo.deleteUser(userId); // Gọi repository để thực hiện xóa mềm
        System.out.println("User " + userToDelete.getUsername() + " đã được xóa mềm thành công.");
    }
    
    public void editInformation() {
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
    // Cập nhật thông tin người dùng
//    @Override
//    public User updateUser(User user) {
//        // Logic chỉnh sửa thông tin người dùng được chuyển từ UserManagementController
//        // Tuy nhiên, để tối ưu, logic giao diện (như hiển thị menu) nên nằm trong View, 
//        // Service chỉ xử lý nghiệp vụ cập nhật
//        if (user == null) {
//            System.out.println("Không có người dùng để cập nhật.");
//            return null;
//        }
//        // Lưu thông tin đã cập nhật vào repository
//        User updatedUser = userRepo.save(user);
//        if (updatedUser != null) {
//            System.out.println("Cập nhật thông tin thành công!");
//            return updatedUser;
//        } else {
//            System.out.println("Cập nhật thông tin thất bại.");
//            return null;
//        }
//    }
    
    // Lấy thông tin người dùng hiện đang đăng nhập
    // Tạm thời sử dụng AuthenticationService để lấy thông tin (dựa trên file AuthenticationServiceImpl)
    private final AuthenticationServiceImpl authService = new AuthenticationServiceImpl();
    
    @Override
    public User getLoggedInUser() {
        return authService.getLoggedInUser(); // Lấy từ AuthenticationService
    }
    
    // Phương thức bổ sung từ yêu cầu của bạn (updateUserInfo với các tham số cụ thể)
    public void updateUserInfo(User currentUser, String newPhone, String newEmail, 
                              String newGender, String newLastName, String newFirstName) {
        if (currentUser == null) {
            System.out.println("Không có người dùng để cập nhật.");
            return;
        }
        // Cập nhật các trường thông tin
        currentUser.setPhone(newPhone);
        currentUser.setEmail(newEmail);
        currentUser.setGender(newGender);
        currentUser.setLastName(newLastName);
        currentUser.setFirstName(newFirstName);
        
        // Lưu vào repository
        userRepo.save(currentUser);
        System.out.println("Thông tin người dùng đã được cập nhật.");
    }
}