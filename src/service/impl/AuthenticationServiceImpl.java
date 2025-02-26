/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.HashMap;
import java.util.Map;
import model.User;
import service.AuthenticationService;

/**
 *
 * @author PC
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    static Map<String, String> users = new HashMap<>();
    static Map<String, Integer> roles = new HashMap<>();
    static {
        users.put("tpnqb", "123");
        users.put("admin", "admin");
        roles.put("tpnqb", 1);
        roles.put("admin", 0);
    }
    private User loggedInUser = null;
    @Override
    public User login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (users.containsKey(username) && users.get(username).equals(password)) {
            user.setRoleId(roles.get(user.getUsername()));
            loggedInUser = user;
            return user;
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
    public void register(User user) {
        users.put(user.getUsername(), user.getPassword());
        roles.put(user.getUsername(), 1);
    }
    
}
