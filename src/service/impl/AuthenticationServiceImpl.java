package service.impl;

import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository = new UserRepositoryImpl();
    private User loggedInUser = null;
    
    @Override
    public User login(User request_user) {
        String username = request_user.getUsername();
        String password = request_user.getPassword();
        String hashPassword = password;
        var oldUser = userRepository.login(username);
        if(oldUser != null) {
            if(hashPassword.equals(oldUser.getPassword())) {
                var user = userRepository.findByUsername(username);
                loggedInUser = user;
                return user;
            }
        }
        return null;
    }

    @Override
    public User logout() {
        User user = loggedInUser;
        loggedInUser = null;
        return user;
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

    @Override
    public void register(User user) {
        userRepository.save(user);
    }
    
}
