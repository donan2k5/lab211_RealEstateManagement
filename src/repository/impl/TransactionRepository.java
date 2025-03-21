package repository.impl;

import dal.TransactionDAO;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;
import repository.ITransactionRepository;

public class TransactionRepository implements ITransactionRepository {
    private final TransactionDAO transactionDAO;

    public TransactionRepository() {
        this.transactionDAO = TransactionDAO.getInstance();
    }

    private static ITransactionRepository instance;

    public static ITransactionRepository getInstance() {
        if (instance == null) {
            synchronized (ITransactionRepository.class) {
                if (instance == null) {
                    instance = new TransactionRepository();
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
    
}