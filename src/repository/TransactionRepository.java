package repository;

import java.util.List;
import model.Transaction;

public interface TransactionRepository {
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByStatus(String status);
    List<Transaction> getTransactionsByMonth(int month, int year);
}
