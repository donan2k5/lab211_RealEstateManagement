package repository;

import model.User;

public interface UserRepository {

    public User findByUsername(String username);
    public User login(String username);
    public User save(User user);
    public boolean existsById(int id);
    public boolean existsByUsername(String username);
    public boolean existsByPhone(String phone);
    public boolean existsByEmail(String email);
    public void deleteUser(int id);
    public User loadLoggedInUser();
    public void saveLoggedInUser(User user);

    public void userLogout();

}
