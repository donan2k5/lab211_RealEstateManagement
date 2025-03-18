package repository.impl;

import dal.UserDAO;
import model.User;

public class UserRepositoryImpl {
    private final UserDAO userDAO = new UserDAO();

    public User findById(int buyerId) {
        String sql = "SELECT userid, username, email, lastname, firstname, phone, gender, roleid, isdelete, created_at, updated_at " +
                     "FROM [user] WHERE userid = ? AND isdelete = 0";
        try (java.sql.PreparedStatement stm = userDAO.connection.prepareStatement(sql)) {
            stm.setInt(1, buyerId);
            try (java.sql.ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return new User.UserBuilder()
                            .userId(rs.getInt("userid"))
                            .username(rs.getString("username"))
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
        } catch (java.sql.SQLException ex) {
            System.err.println("Error fetching user by ID: " + ex.getMessage());
        }
        return null;
    }
}