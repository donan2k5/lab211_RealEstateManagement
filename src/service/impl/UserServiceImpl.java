package service.impl;

import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import java.util.List;
import service.AuthenticationService;
import view.UserManagementView;


public class UserServiceImpl implements UserService {
    
    private final UserRepositoryImpl userRepo = UserRepositoryImpl.getInstance();
    private final UserManagementView umView = new UserManagementView();
    private final AuthenticationService authService = new AuthenticationServiceImpl();
    
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
    
}