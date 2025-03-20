/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import model.Transaction;

/**
 *
 * @author ADMIN
 */
public class TransactionDAO extends DBContext<Transaction> {

    public Transaction findTransactionById(int id) {
        String sql = "SELECT * FROM [transaction] WHERE transactionid = ? AND isdelete = 0";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return new Transaction(
                            rs.getInt("transactionid"),
                            rs.getInt("realestate_id"),
                            rs.getInt("seller_id"),
                            rs.getInt("buyer_id"),
                            rs.getDouble("price"),
                            rs.getDouble("deposit_amount"),
                            rs.getTimestamp("expiration_date").toLocalDateTime().toLocalDate(),
                            rs.getString("status"),
                            rs.getTimestamp("created_at").toLocalDateTime().toLocalDate(),
                            rs.getTimestamp("updated_at").toLocalDateTime().toLocalDate()
                        );
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public List<Transaction> findTransactionsByREID(int id) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM [transaction] WHERE transactionid = ? AND isdelete = 0";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    if (rs.getInt("realestate_id") != id) continue; 
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
    
    public List<Transaction> findTransactionBySellerID(int id){
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM [transaction] WHERE transactionid = ? AND isdelete = 0";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    if (rs.getInt("seller_id") != id) continue; 
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

    public List<Transaction> findTransactionByBuyerID(int id){
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM [transaction] WHERE transactionid = ? AND isdelete = 0";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    if (rs.getInt("buyer_id") != id) continue; 
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
    
    @Override
    public ArrayList<Transaction> list() {
        ArrayList<Transaction> tl = new ArrayList<>();
        String sql = "SELECT * FROM [transaction] WHERE isdelete = 0";
        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
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

                tl.add(new Transaction(transID, REID, sellerID, buyerID, price, deposit, expirationTime, status, createTime, updateTime));
            }
        } catch (SQLException ex) {
            System.out.println("Error reading from DB: " + ex);
        }
        return tl;
    }


//////////////////////////////////////////////////////////////////////////////////////////////    
    public void deleteTransaction(Transaction transaction) {
        String sql = "UPDATE [transaction] SET isdelete = 1 WHERE transactionid = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, transaction.getTransID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updateTransaction(Transaction transaction) {
        String sql = "UPDATE [transaction] SET " +
                "realestate_id = ?, " +
                "seller_id = ?, " +
                "buyer_id = ?, " +
                "price = ?, " +
                "deposit_amount = ?, " +
                "expiration_date = ?, " +
                "status = ?, " +
                "updated_at = ? " +
                "WHERE transactionid = ? AND isdelete = 0";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, transaction.getREID());
            stm.setInt(2, transaction.getSellerID());
            stm.setInt(3, transaction.getBuyerID());
            stm.setDouble(4, transaction.getPrice());
            stm.setDouble(5, transaction.getDeposit());
            stm.setDate(6, Date.valueOf(transaction.getExpirationTime()));
            stm.setString(7, transaction.getStatus());
            stm.setDate(8, Date.valueOf(transaction.getUpdateTime()));
            stm.setInt(9, transaction.getTransID());

            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Update failed: " + ex);
        }
    }
    
    public void insertTransaction(Transaction transaction) {
    String sql = "INSERT INTO [transaction] (" +
            "transactionid, realestate_id, seller_id, buyer_id, price, deposit_amount, expiration_date, status, created_at, updated_at, isdelete" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";

    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setInt(1, transaction.getTransID());
        stm.setInt(2, transaction.getREID());
        stm.setInt(3, transaction.getSellerID());
        stm.setInt(4, transaction.getBuyerID());
        stm.setDouble(5, transaction.getPrice());
        stm.setDouble(6, transaction.getDeposit());
        stm.setDate(7, Date.valueOf(transaction.getExpirationTime()));
        stm.setString(8, transaction.getStatus());
        stm.setDate(9, Date.valueOf(transaction.getCreateTime()));
        stm.setDate(10, Date.valueOf(transaction.getUpdateTime()));

        stm.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("Insert failed: " + ex);
    }
}


//////////////////////////////////////////////////////////////////////////////////////////////    

    @Override
    public Transaction get(int id){
        return null;
    }

    @Override
    public Transaction insert(Transaction transaction){
        return null;
    }

    @Override
    public Transaction update(Transaction transaction){
        return null;
    }

    @Override
    public Transaction delete(int id){
        return null;
    }
}
