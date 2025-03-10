package dal;

import context.DBContext;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.User;

public class UserDAO extends DBContext<User> {

    public User findByUsername(String username) {
        String sql = "SELECT * FROM [user] WHERE username = ? AND isdelete = 0";
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
                            .delete(rs.getInt("isdelete"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate())
                            .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime().toLocalDate())
                            .build();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public User login(String username) {
        String sql = "SELECT [username], [password] FROM [user] WHERE username = ? AND isdelete = 0";
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
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User insert(User entity) {
        String sql = "INSERT INTO [user] (username, password, lastname, firstname, phone, email, gender, roleid, isdelete, created_at, updated_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), GETDATE())";
        try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, entity.getUsername());
            stm.setString(2, entity.getPassword());
            stm.setString(3, entity.getLastName());
            stm.setString(4, entity.getFirstName());
            stm.setString(5, entity.getPhone());
            stm.setString(6, entity.getEmail());
            stm.setString(7, entity.getGender());
            stm.setInt(8, entity.getRoleId());
            stm.setInt(9, entity.getIsDelete());
            
            int affectedRows = stm.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stm.getGeneratedKeys()) {
                    if (rs.next()) {
                        entity.setUserId(rs.getInt(1));
                    }
                }
                return entity;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public User update(User entity) {
        String sql = "UPDATE [user] SET username = ?, password = ?, lastname = ?, firstname = ?, phone = ?, email = ?, gender = ?, roleid = ?, isdelete = ?, updated_at = GETDATE() WHERE userid = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, entity.getUsername());
            stm.setString(2, entity.getPassword());
            stm.setString(3, entity.getLastName());
            stm.setString(4, entity.getFirstName());
            stm.setString(5, entity.getPhone());
            stm.setString(6, entity.getEmail());
            stm.setString(7, entity.getGender());
            stm.setInt(8, entity.getRoleId());
            stm.setInt(9, entity.getIsDelete());
            stm.setInt(10, entity.getUserId());
            int affectedRows = stm.executeUpdate();
            return affectedRows > 0 ? entity : null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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
}
