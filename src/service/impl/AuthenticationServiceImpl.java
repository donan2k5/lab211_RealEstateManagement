package service.impl;

import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository = UserRepositoryImpl.getInstance();

    @Override
    public User login(User request_user) {
        String username = request_user.getUsername();
        String password = request_user.getPassword();
        String hashPassword = password;
        var oldUser = userRepository.login(username);
        if (oldUser != null) {
            if (hashPassword.equals(oldUser.getPassword())) {
                var user = userRepository.findByUsername(username);
                userRepository.saveLoggedInUser(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public User logout() {
        User user = userRepository.loadLoggedInUser();
        userRepository.userLogout();
        return user;
    }

    @Override
    public User getLoggedInUser() {
        return userRepository.loadLoggedInUser();
    }

    @Override
    public void register(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean isLoggedIn() {
        if (userRepository.loadLoggedInUser() != null) {
            return true;
        }
        return false;
    }

}