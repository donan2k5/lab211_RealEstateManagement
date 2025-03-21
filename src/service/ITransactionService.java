package service;

import java.util.List;
import model.Transaction;
import model.User;

public interface ITransactionService {

    double getTotalRevenue();

    int getTotalHousesSold();

    int getHousesSoldByMonth(int month, int year);

    List<User> getBuyersByMonth(int month, int year);

    public List<Transaction> getPendingTransactionList(String status);

    List<Transaction> getAllTransactionList();

    public void add(Transaction t);

    public void delete(int id);
}