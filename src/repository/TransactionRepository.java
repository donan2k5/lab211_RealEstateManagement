package repository;

import java.util.List;
import model.Transaction;
import java.util.ArrayList;

public interface TransactionRepository {
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByStatus(String status);
    List<Transaction> getTransactionsByMonth(int month, int year);
    ArrayList<Transaction> list();
    Transaction get(int id);
    Transaction insert(int entity); 
    Transaction update(int entity); 
    Transaction delete(int id);

}
