package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;

public class TransactionDAO extends DBContext<Transaction> {

    private static TransactionDAO instance;

    private TransactionDAO() {
    }

    public static TransactionDAO getInstance() {
        if (instance == null) {
            synchronized (TransactionDAO.class) {
                if (instance == null) {
                    instance = new TransactionDAO();
                }
            }
        }
        return instance;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT transactionId, buyer_id, seller_id, price, status, created_at FROM [transaction]";
        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                transactions.add(createTransactionFromResultSet(rs));
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching all transactions: " + ex.getMessage());
        }
        return transactions;
    }

    /**
     * Retrieves transactions by status.
     * @param status The status to filter by (e.g., "completed")
     * @return List of transactions matching the status
     */
    public List<Transaction> getTransactionsByStatus(String status) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT transactionId, buyer_id, seller_id, price, status, created_at " +
                     "FROM [transaction] WHERE status = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, status);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    transactions.add(createTransactionFromResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching transactions by status: " + ex.getMessage());
        }
        return transactions;
    }

    /**
     * Retrieves completed transactions for a specific month and year.
     * @param month Month (1-12)
     * @param year Year
     * @return List of transactions matching the criteria
     */
    public List<Transaction> getTransactionsByMonth(int month, int year) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT transactionId, buyer_id, seller_id, price, status, created_at " +
                     "FROM [transaction] WHERE MONTH(created_at) = ? AND YEAR(created_at) = ? AND status = 'completed'";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, month);
            stm.setInt(2, year);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    transactions.add(createTransactionFromResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching transactions by month: " + ex.getMessage());
        }
        return transactions;
    }

    /**
     * Helper method to create a Transaction object from a ResultSet.
     */
    private Transaction createTransactionFromResultSet(ResultSet rs) throws SQLException {
        Transaction t = new Transaction();
        t.setTransactionId(rs.getInt("transactionId"));
        t.setBuyerId(rs.getInt("buyer_id"));
        t.setSellerId(rs.getInt("seller_id"));
        t.setPrice(rs.getDouble("price"));
        t.setStatus(rs.getString("status"));
        t.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return t;
    }

    @Override
    public List<Transaction> list() {
        return getAllTransactions();
    }

    @Override
    public Transaction get(int id) {
        String sql = "SELECT transactionId, buyer_id, seller_id, price, status, created_at " +
                     "FROM [transaction] WHERE transactionId = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return createTransactionFromResultSet(rs);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching transaction by ID: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public Transaction insert(Transaction entity) {
        String sql = "INSERT INTO [transaction] (buyer_id, seller_id, price, status, created_at) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1, entity.getBuyerId());
            stm.setInt(2, entity.getSellerId());
            stm.setDouble(3, entity.getPrice());
            stm.setString(4, entity.getStatus());
            stm.setTimestamp(5, Timestamp.valueOf(entity.getCreatedAt()));
            
            int affectedRows = stm.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stm.getGeneratedKeys()) {
                    if (rs.next()) {
                        entity.setTransactionId(rs.getInt(1));
                    }
                }
                return entity;
            }
        } catch (SQLException ex) {
            System.err.println("Error inserting transaction: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public Transaction update(Transaction entity) {
        String sql = "UPDATE [transaction] SET buyer_id = ?, seller_id = ?, price = ?, status = ?, created_at = ? " +
                     "WHERE transactionId = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, entity.getBuyerId());
            stm.setInt(2, entity.getSellerId());
            stm.setDouble(3, entity.getPrice());
            stm.setString(4, entity.getStatus());
            stm.setTimestamp(5, Timestamp.valueOf(entity.getCreatedAt()));
            stm.setInt(6, entity.getTransactionId());

            int affectedRows = stm.executeUpdate();
            return affectedRows > 0 ? entity : null;
        } catch (SQLException ex) {
            System.err.println("Error updating transaction: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public Transaction delete(int id) {
        Transaction transaction = get(id);
        if (transaction == null) {
            return null;
        }

        String sql = "DELETE FROM [transaction] WHERE transactionId = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            int affectedRows = stm.executeUpdate();
            return affectedRows > 0 ? transaction : null;
        } catch (SQLException ex) {
            System.err.println("Error deleting transaction: " + ex.getMessage());
        }
        return null;
    }
}