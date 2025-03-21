package service.impl;

import java.util.List;
import java.util.stream.Collectors;
import model.Transaction;
import model.User;
import repository.impl.TransactionRepository;
import repository.impl.UserRepositoryImpl;
import service.ITransactionService;

public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepo = new TransactionRepository();
    private final UserRepositoryImpl userRepo = new UserRepositoryImpl();

    @Override
    public double getTotalRevenue() {
        List<Transaction> completedTransactions = transactionRepo.getTransactionsByStatus("completed");
        return completedTransactions.isEmpty() ? 0.0 : completedTransactions.stream()
                .mapToDouble(Transaction::getPrice)
                .sum();
    }

    @Override
    public int getTotalHousesSold() {
        List<Transaction> completedTransactions = transactionRepo.getTransactionsByStatus("completed");
        return completedTransactions.size();
    }

    @Override
    public int getHousesSoldByMonth(int month, int year) {
        List<Transaction> transactions = transactionRepo.getTransactionsByMonth(month, year);
        return transactions.size();
    }

    @Override
    public List<User> getBuyersByMonth(int month, int year) {
        List<Transaction> transactions = transactionRepo.getTransactionsByMonth(month, year);
        return transactions.stream()
                .map(t -> userRepo.findById(t.getBuyerID()))
                .filter(u -> u != null)
                .collect(Collectors.toList());
    }
}
