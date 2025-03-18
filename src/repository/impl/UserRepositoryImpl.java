package repository.impl;

import dal.UserDAO;
import model.User;
import repository.UserRepository;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private User loggedInUser;
    private static UserRepositoryImpl instance;

    public UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (UserDAO.class) {
                if (instance == null) {
                    instance = new UserRepositoryImpl();
                }
            }
        }
        return instance;
    }
    private final UserDAO userDAO = UserDAO.getInstance();

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

    // Tìm user theo ID (chỉ trả về khi user chưa bị xóa mềm: isdelete = 0)
    public User findById(int id) {
        return userDAO.get(id);
    }

    // Liệt kê tất cả các user chưa bị xóa mềm (isdelete = 0)
    public List<User> listAllUsers() {
        return userDAO.list();
    }

    // Xóa user theo kiểu "xóa mềm": cập nhật isdelete = 1 cho user có id cho trước
    @Override
    public void deleteUser(int id) {
        userDAO.delete(id);
    }

    @Override
    public User loadLoggedInUser() {
        return loggedInUser; // 
    }

    @Override
    public void saveLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    @Override
    public void userLogout() {
        this.loggedInUser = null;
    }
}
