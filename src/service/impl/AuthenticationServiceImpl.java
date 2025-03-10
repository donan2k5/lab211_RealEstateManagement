package service.impl;

import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepo = new UserRepositoryImpl();
    private User loggedInUser = null;
    
    @Override
    public User login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        User dbUser = userRepo.login(username);
        if(dbUser != null && dbUser.getPassword().equals(password)) {
            loggedInUser = userRepo.findByUsername(username);
            return loggedInUser;
        }
        return null;
    }
    
    @Override
    public User logout() {
        User temp = loggedInUser;
        loggedInUser = null;
        return temp;
    }
    
    @Override
    public User getLoggedInUser() {
        return loggedInUser;
    }
    
    @Override
    public void deleteUser(int id) {
        // Đối với AuthenticationService, chúng ta có thể delegate việc xóa sang repository
        userRepo.deleteUser(id);
    }
    
    // Cho mục đích demo, cho phép set user đăng nhập từ bên ngoài
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }
}
