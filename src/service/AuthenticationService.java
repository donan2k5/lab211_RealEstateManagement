package service;

import model.User;

public interface AuthenticationService {
    public User login(User user);
    public User logout();
    public User getLoggedInUser();
    public void register(User user);
    public User updateUser(User user);
    public boolean deleteUser(int userId);  // Thêm phương thức xóa người dùng
}
