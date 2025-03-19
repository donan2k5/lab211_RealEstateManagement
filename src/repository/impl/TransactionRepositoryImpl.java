package repository.impl;

import dal.TransactionDAO;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;
import repository.TransactionRepository;

public class TransactionRepositoryImpl implements TransactionRepository {
    private final TransactionDAO transactionDAO;

    public TransactionRepositoryImpl() {
        this.transactionDAO = TransactionDAO.getInstance();
    }

    private static TransactionRepositoryImpl instance;

    public static TransactionRepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (TransactionRepositoryImpl.class) {
                if (instance == null) {
                    instance = new TransactionRepositoryImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

   
    @Override
    public List<Transaction> getTransactionsByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        return transactionDAO.getTransactionsByStatus(status);
    }

    @Override
    public List<Transaction> getTransactionsByMonth(int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }
        if (year < 0) {
            throw new IllegalArgumentException("Year cannot be negative");
        }
        return transactionDAO.getTransactionsByMonth(month, year);
    }
    

    @Override
    public ArrayList<Transaction> list() {
        return new ArrayList<>(transactionDAO.list());
    }

    @Override
    public Transaction get(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        Transaction transaction = transactionDAO.get(id);
        if (transaction == null) {
            throw new IllegalStateException("Transaction with ID " + id + " not found");
        }
        return transaction;
    }

    public Transaction insert(Transaction entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Transaction entity cannot be null");
        }
        Transaction inserted = transactionDAO.insert(entity);
        if (inserted == null) {
            throw new IllegalStateException("Failed to insert transaction");
        }
        return inserted;
    }

    public Transaction update(Transaction entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Transaction entity cannot be null");
        }
        if (entity.getTransactionId() <= 0) {
            throw new IllegalArgumentException("Transaction ID must be greater than 0");
        }
        Transaction updated = transactionDAO.update(entity);
        if (updated == null) {
            throw new IllegalStateException("Failed to update transaction with ID " + entity.getTransactionId());
        }
        return updated;
    }


    @Override
    public Transaction delete(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        Transaction deleted = transactionDAO.delete(id);
        if (deleted == null) {
            throw new IllegalStateException("Failed to delete transaction with ID " + id);
        }
        return deleted;
    }

    @Override
    public Transaction insert(int entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Transaction update(int entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}