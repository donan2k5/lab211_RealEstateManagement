package repository.impl;

import dal.TransactionDAO;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;
import repository.ITransactionRepository;
import java.time.format.DateTimeFormatter;

public class TransactionRepository implements ITransactionRepository {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final TransactionDAO transDAO = TransactionDAO.getInstance();

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

    @Override
    public Transaction findTransactionById(int id) {
        return transDAO.get(id);
    }

    @Override
    public List<Transaction> findTransactionsByREID(int id) {
        return transDAO.findTransactionsByREID(id);
    }

    @Override
    public List<Transaction> findTransactionBySellerID(int id) {
        return transDAO.findTransactionBySellerID(id);
    }

    @Override
    public List<Transaction> findTransactionByBuyerID(int id) {
        return transDAO.findTransactionByBuyerID(id);
    }

    @Override
    public void save(Transaction t) {
        if ((findTransactionById(t.getTransID()) == null)) {
            transDAO.insert(t);
            return;
        }
        transDAO.update(t);
    }

    @Override
    public void deleteTransaction(int id) {
        transDAO.delete(id);
    }

//    public int getLastID(){
//        return tl.get(tl.size()-1).getTransID();
//    }
    public List<Transaction> getAll() {
        return transDAO.list();
    }

    public List<Transaction> findAllTransactionByStatus(String status) {
        return transDAO.findAllByStatus(status);
    }
}