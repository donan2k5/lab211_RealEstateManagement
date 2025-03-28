package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
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
        try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
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
     *
     * @param status The status to filter by (e.g., "completed")
     * @return List of transactions matching the status
     */
    public List<Transaction> getTransactionsByStatus(String status) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT transactionId, buyer_id, seller_id, price, status, created_at "
                + "FROM [transaction] WHERE status = ?";
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
     *
     * @param month Month (1-12)
     * @param year Year
     * @return List of transactions matching the criteria
     */
    public List<Transaction> getTransactionsByMonth(int month, int year) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT transactionId, buyer_id, seller_id, price, status, created_at "
                + "FROM [transaction] WHERE MONTH(created_at) = ? AND YEAR(created_at) = ? AND status = 'completed'";
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
        t.setTransID(rs.getInt("transactionId"));
        t.setBuyerID(rs.getInt("buyer_id"));
        t.setSellerID(rs.getInt("seller_id"));
        t.setPrice(rs.getDouble("price"));
        t.setStatus(rs.getString("status"));
        t.setCreateTime(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
        return t;
    }

    public List<Transaction> findTransactionsByREID(int id) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM [transaction] WHERE transactionid = ? AND isdelete = 0";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    if (rs.getInt("realestate_id") != id) {
                        continue;
                    }
                    Transaction t = new Transaction();

                    t.setTransID(rs.getInt("transactionid"));
                    t.setREID(rs.getInt("realestate_id"));
                    t.setSellerID(rs.getInt("seller_id"));
                    t.setBuyerID(rs.getInt("buyer_id"));
                    t.setPrice(rs.getDouble("price"));
                    t.setDeposit(rs.getDouble("deposit_amount"));
                    t.setExpirationTime(rs.getTimestamp("expiration_date").toLocalDateTime().toLocalDate());
                    t.setStatus(rs.getString("status"));
                    t.setCreateTime(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
                    t.setUpdateTime(rs.getTimestamp("updated_at").toLocalDateTime().toLocalDate());

                    transactions.add(t);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return transactions;
    }

    public List<Transaction> findTransactionBySellerID(int id) {
        List<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * "
                + "FROM [transaction] "
                + "WHERE seller_id = ? AND isdelete = 0 ";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int transID = rs.getInt("transactionid");
                    int REID = rs.getInt("realestate_id");
                    int sellerID = rs.getInt("seller_id");
                    int buyerID = rs.getInt("buyer_id");
                    double price = rs.getDouble("price");
                    double deposit = rs.getDouble("deposit_amount");
                    LocalDate expirationTime = rs.getDate("expiration_date").toLocalDate();
                    String status = rs.getString("status");
                    LocalDate createTime = rs.getDate("created_at").toLocalDate();
                    LocalDate updateTime = rs.getDate("updated_at").toLocalDate();
                    boolean isDelete = rs.getBoolean("isdelete");
                    transactions.add(new Transaction(transID, REID, sellerID, buyerID, price, deposit, expirationTime, status, isDelete, createTime, updateTime));
                }
            }
        } catch (SQLException ex) {
            System.out.println("[ERROR] Failed to retrieve transactions: " + ex.getMessage());
        }
        return transactions;
    }

    public List<Transaction> findTransactionByBuyerID(int id) {
        List<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * "
                + "FROM [transaction] "
                + "WHERE buyer_id = ? AND isdelete = 0 ";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int transID = rs.getInt("transactionid");
                    int REID = rs.getInt("realestate_id");
                    int sellerID = rs.getInt("seller_id");
                    int buyerID = rs.getInt("buyer_id");
                    double price = rs.getDouble("price");
                    double deposit = rs.getDouble("deposit_amount");
                    LocalDate expirationTime = rs.getDate("expiration_date").toLocalDate();
                    String status = rs.getString("status");
                    LocalDate createTime = rs.getDate("created_at").toLocalDate();
                    LocalDate updateTime = rs.getDate("updated_at").toLocalDate();
                    boolean isDelete = rs.getBoolean("isdelete");
                    transactions.add(new Transaction(transID, REID, sellerID, buyerID, price, deposit, expirationTime, status, isDelete, createTime, updateTime));
                }
            }
        } catch (SQLException ex) {
            System.out.println("[ERROR] Failed to retrieve transactions: " + ex.getMessage());
        }
        return transactions;
    }

    @Override
    public List<Transaction> list() {
        List<Transaction> tl = new ArrayList<>();
        String sql = "SELECT * FROM [transaction] WHERE isdelete = 0";
        try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                int transID = rs.getInt("transactionid");
                int REID = rs.getInt("realestate_id");
                int sellerID = rs.getInt("seller_id");
                int buyerID = rs.getInt("buyer_id");
                double price = rs.getDouble("price");
                double deposit = rs.getDouble("deposit_amount");
                LocalDate expirationTime = rs.getDate("expiration_date").toLocalDate();
                String status = rs.getString("status");
                LocalDate createTime = rs.getDate("created_at").toLocalDate();
                LocalDate updateTime = rs.getDate("updated_at").toLocalDate();
                boolean isDelete = rs.getBoolean("isdelete");
                tl.add(new Transaction(transID, REID, sellerID, buyerID, price, deposit, expirationTime, status, isDelete, createTime, updateTime));
            }
        } catch (SQLException ex) {
            System.out.println("Error reading from DB: " + ex);
        }
        return tl;
    }

    @Override
    public Transaction get(int id) {
        String sql = "SELECT * FROM [transaction] WHERE transactionid = ? AND isdelete = 0";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    int transID = rs.getInt("transactionid");
                    int REID = rs.getInt("realestate_id");
                    int sellerID = rs.getInt("seller_id");
                    int buyerID = rs.getInt("buyer_id");
                    double price = rs.getDouble("price");
                    double deposit = rs.getDouble("deposit_amount");
                    LocalDate expirationTime = rs.getDate("expiration_date").toLocalDate();
                    String status = rs.getString("status");
                    LocalDate createTime = rs.getDate("created_at").toLocalDate();
                    LocalDate updateTime = rs.getDate("updated_at").toLocalDate();
                    boolean isDelete = rs.getBoolean("isdelete");
                    return new Transaction(transID, REID, sellerID, buyerID, price, deposit, expirationTime, status, isDelete, createTime, updateTime);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public Transaction insert(Transaction transaction) {
        String sql = "INSERT INTO [transaction] (realestate_id, seller_id, buyer_id, price, deposit_amount, expiration_date) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, transaction.getREID());
            stm.setInt(2, transaction.getSellerID());
            stm.setInt(3, transaction.getBuyerID());
            stm.setDouble(4, transaction.getPrice());
            stm.setDouble(5, transaction.getDeposit());
            stm.setDate(6, Date.valueOf(transaction.getExpirationTime()));

            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Insert failed: " + ex);
        }
        return transaction;
    }

    @Override
    public Transaction update(Transaction transaction) {
        String sql = "UPDATE [transaction] SET "
                + "realestate_id = ?, "
                + "seller_id = ?, "
                + "buyer_id = ?, "
                + "price = ?, "
                + "deposit_amount = ?, "
                + "expiration_date = ?, "
                + "status = ? "
                + "WHERE transactionid = ? AND isdelete = 0";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, transaction.getREID());
            stm.setInt(2, transaction.getSellerID());
            stm.setInt(3, transaction.getBuyerID());
            stm.setDouble(4, transaction.getPrice());
            stm.setDouble(5, transaction.getDeposit());
            stm.setDate(6, Date.valueOf(transaction.getExpirationTime()));
            stm.setString(7, transaction.getStatus());
            stm.setInt(8, transaction.getTransID());

            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Update failed: " + ex);
        }
        return transaction;
    }

    @Override
    public Transaction delete(int id) {
        String sql = "UPDATE [transaction] SET isdelete = 1 WHERE transactionid = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public List<Transaction> findAllByStatus(String status) {
        List<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * "
                + "FROM [transaction] "
                + "WHERE status = ? AND isdelete = 0 ";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, status);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int transID = rs.getInt("transactionid");
                    int REID = rs.getInt("realestate_id");
                    int sellerID = rs.getInt("seller_id");
                    int buyerID = rs.getInt("buyer_id");
                    double price = rs.getDouble("price");
                    double deposit = rs.getDouble("deposit_amount");
                    LocalDate expirationTime = rs.getDate("expiration_date").toLocalDate();
                    LocalDate createTime = rs.getDate("created_at").toLocalDate();
                    LocalDate updateTime = rs.getDate("updated_at").toLocalDate();
                    boolean isDelete = rs.getBoolean("isdelete");
                    transactions.add(new Transaction(transID, REID, sellerID, buyerID, price, deposit, expirationTime, status, isDelete, createTime, updateTime));
                }
            }
        } catch (SQLException ex) {
            System.out.println("[ERROR] Failed to retrieve transactions: " + ex.getMessage());
        }
        return transactions;
    }
}
