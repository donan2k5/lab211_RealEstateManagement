package service.impl;

import java.util.List;
import java.util.stream.Collectors;
import model.Transaction;
import model.User;
import repository.TransactionRepository;
import repository.impl.TransactionRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.TransactionService;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepo = new TransactionRepositoryImpl();
    private final UserRepositoryImpl userRepo = new UserRepositoryImpl();

    /**
     * Calculates the total revenue from all completed transactions.
     * @return Total revenue, or 0 if no completed transactions exist.
     */
    @Override
    public double getTotalRevenue() {
        List<Transaction> completedTransactions = transactionRepo.getTransactionsByStatus("completed");
        return completedTransactions.isEmpty() ? 0.0 : completedTransactions.stream()
                .mapToDouble(Transaction::getPrice)
                .sum();
    }

    /**
     * Counts the total number of houses sold (completed transactions).
     * @return Number of houses sold, or 0 if no completed transactions exist.
     */
    @Override
    public int getTotalHousesSold() {
        List<Transaction> completedTransactions = transactionRepo.getTransactionsByStatus("completed");
        return completedTransactions.size();
    }

    /**
     * Counts the number of houses sold in a specific month and year.
     * @param month Month (1-12)
     * @param year Year
     * @return Number of houses sold, or 0 if none found.
     */
    @Override
    public int getHousesSoldByMonth(int month, int year) {
        List<Transaction> transactions = transactionRepo.getTransactionsByMonth(month, year);
        return transactions.size();
    }

    /**
     * Retrieves the list of buyers for a specific month and year.
     * @param month Month (1-12)
     * @param year Year
     * @return List of buyers, or empty list if none found.
     */
    @Override
    public List<User> getBuyersByMonth(int month, int year) {
        List<Transaction> transactions = transactionRepo.getTransactionsByMonth(month, year);
        return transactions.stream()
                .map(t -> userRepo.findById(t.getBuyerId()))
                .filter(u -> u != null)
                .collect(Collectors.toList());
    }
}