package repository.impl;

import dal.UserDAO;
import model.User;
import repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private final UserDAO userDAO = new UserDAO();

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User login(String username) {
        return userDAO.login(username);
    }

    @Override
    public User save(User user) {
        if (!existsById(user.getUserId())) {
            return userDAO.insert(user);
        }
        return userDAO.update(user);
    }

    @Override
    public boolean existsById(int id) {
        return userDAO.existsById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDAO.existsByUsername(username);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userDAO.existsByPhone(phone);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDAO.existsByEmail(email);
    }

    @Override
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);  // Gọi phương thức xóa từ UserDAO
    }
}
