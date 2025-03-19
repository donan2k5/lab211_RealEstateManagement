package repository.impl;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;
import repository.TransactionRepository;

public class TransactionRepositoryImpl extends DBContext<Transaction> implements TransactionRepository {

    @Override
    public List<Transaction> getAllTransactions() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Retrieves transactions by status.
     * @param status The status to filter by (e.g., "completed")
     * @return List of transactions matching the status
     */
    @Override
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
    @Override
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

    // Unsupported methods
    @Override
    public ArrayList<Transaction> list() { throw new UnsupportedOperationException("Not supported."); }
    @Override
    public Transaction get(int id) { throw new UnsupportedOperationException("Not supported."); }
    @Override
    public Transaction insert(Transaction entity) { throw new UnsupportedOperationException("Not supported."); }
    @Override
    public Transaction update(Transaction entity) { throw new UnsupportedOperationException("Not supported."); }
    @Override
    public Transaction delete(int id) { throw new UnsupportedOperationException("Not supported."); }
}