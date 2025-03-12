package repository.impl;

import dal.UserDAO;
import model.User;
import repository.UserRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    // Tìm user theo ID (chỉ trả về khi user chưa bị xóa mềm: isdelete = 0)
    public User findById(int id) {
        String sql = "SELECT * FROM [user] WHERE userid = ? AND isdelete = 0";
        try (PreparedStatement stm = userDAO.connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return new User.UserBuilder()
                            .userId(rs.getInt("userid"))
                            .username(rs.getString("username"))
                            .password(rs.getString("password"))
                            .lastName(rs.getString("lastname"))
                            .firstName(rs.getString("firstname"))
                            .phone(rs.getString("phone"))
                            .email(rs.getString("email"))
                            .gender(rs.getString("gender"))
                            .roleId(rs.getInt("roleid"))
                            .delete(rs.getInt("isdelete"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    // Liệt kê tất cả các user chưa bị xóa mềm (isdelete = 0)
    public List<User> listAllUsers() {
        return userDAO.list();
    }
    
    // Xóa user theo kiểu "xóa mềm": cập nhật isdelete = 1 cho user có id cho trước
    @Override
    public void deleteUser(int id) {
        String sql = "UPDATE [user] SET isdelete = 1 WHERE userid = ?";
        try (PreparedStatement stm = userDAO.connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
