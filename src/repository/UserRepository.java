package repository;

import model.User;

public interface UserRepository {
    User findByUsername(String username);
    User login(String username);
    User save(User user);
    boolean existsById(int id);
    boolean existsByUsername(String username);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    
    // Phương thức xóa user theo kiểu "xóa mềm"
    void deleteUser(int id);
}
