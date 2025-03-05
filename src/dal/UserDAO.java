package dal;

import context.DBContext;
import model.User;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO extends DBContext<User> {
    
    // Cập nhật thông tin người dùng
    @Override
    public User update(User entity) {
        String sql = "UPDATE [user] "
                + "SET phone = ?, email = ?, gender = ?, lastname = ?, firstname = ?, updated_at = GETDATE() "
                + "WHERE userid = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            // Thiết lập giá trị các tham số trong câu lệnh SQL
            stm.setString(1, entity.getPhone());
            stm.setString(2, entity.getEmail());
            stm.setString(3, entity.getGender());
            stm.setString(4, entity.getLastName());
            stm.setString(5, entity.getFirstName());
            stm.setInt(6, entity.getUserId());

            // Thực hiện câu lệnh SQL và kiểm tra số dòng bị ảnh hưởng
            int affectedRows = stm.executeUpdate();
            return affectedRows > 0 ? entity : null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Xóa người dùng (soft delete, thay đổi trường isdelete thành 1)
    public boolean deleteUser(int id) {
        String sql = "UPDATE [user] SET isdelete = 1 WHERE userid = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            // Kiểm tra số dòng bị ảnh hưởng để xác nhận việc cập nhật đã thành công
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Tìm người dùng theo username
    public User findByUsername(String username) {
        String sql = "SELECT * FROM [user] WHERE username = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, username);
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
                            .isDelete(rs.getInt("isdelete"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate())
                            .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime().toLocalDate())
                            .build();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Kiểm tra đăng nhập của người dùng
    public User login(String username) {
        String sql = "SELECT * FROM [user] WHERE username = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, username);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return new User.UserBuilder()
                            .username(rs.getString("username"))
                            .password(rs.getString("password"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Kiểm tra sự tồn tại của người dùng theo ID
    public boolean existsById(int id) {
        String sql = "SELECT 1 FROM [user] WHERE userid = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Kiểm tra sự tồn tại của người dùng theo Username
    public boolean existsByUsername(String username) {
        String sql = "SELECT 1 FROM [user] WHERE username = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, username);
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Kiểm tra sự tồn tại của người dùng theo Phone
    public boolean existsByPhone(String phone) {
        String sql = "SELECT 1 FROM [user] WHERE phone = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, phone);
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Kiểm tra sự tồn tại của người dùng theo Email
    public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM [user] WHERE email = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, email);
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Phương thức thêm người dùng mới
    @Override
    public User insert(User user) {
        String sql = "INSERT INTO [user] (username, password, lastname, firstname, phone, email, gender, roleid, isdelete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getLastName());
            stm.setString(4, user.getFirstName());
            stm.setString(5, user.getPhone());
            stm.setString(6, user.getEmail());
            stm.setString(7, user.getGender());
            stm.setInt(8, user.getRoleId());
            stm.setInt(9, user.getIsDelete());

            int affectedRows = stm.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stm.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setUserId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
