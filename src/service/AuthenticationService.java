package service;

import model.User;


public interface AuthenticationService {
    public User login(User user);

    public User logout();

    public User getLoggedInUser();

    public void register(User user);
}
