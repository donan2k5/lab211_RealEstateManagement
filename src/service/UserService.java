package service;

import model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();       // Lấy danh sách tất cả người dùng
    void deleteUser(int userId);    // Xóa mềm người dùng
}