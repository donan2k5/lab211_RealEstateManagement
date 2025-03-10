package service;

import model.User;

public interface AuthenticationService {
    User login(User user);
    User logout();
    User getLoggedInUser();
    
    // Thêm deleteUser cho AuthenticationService nếu cần (để đồng bộ với giao diện dự án)
    void deleteUser(int id);
}
