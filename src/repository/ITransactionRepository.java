package repository;

import java.util.List;
import model.Transaction;
import java.util.ArrayList;

public interface ITransactionRepository {
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByStatus(String status);
    List<Transaction> getTransactionsByMonth(int month, int year);

}
