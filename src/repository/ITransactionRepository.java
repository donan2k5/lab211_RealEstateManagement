package repository;

import java.util.List;
import model.Transaction;

public interface ITransactionRepository {

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByStatus(String status);

    List<Transaction> getTransactionsByMonth(int month, int year);

    Transaction findTransactionById(int id);

    List<Transaction> findTransactionsByREID(int id);

    List<Transaction> findTransactionBySellerID(int id);

    List<Transaction> findTransactionByBuyerID(int id);

    void deleteTransaction(int id);

    void save(Transaction t);
}