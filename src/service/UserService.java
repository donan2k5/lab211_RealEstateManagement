package service;

import model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();       // Lấy danh sách tất cả người dùng
    void deleteUser(int userId);    // Xóa mềm người dùng
//    User updateUser(User user);     // Cập nhật thông tin người dùng
    User getLoggedInUser();         // Lấy thông tin người dùng đang đăng nhập
}