package service.impl;

import java.util.ArrayList;
import utils.TransactionValidation;
import java.util.List;
import java.util.stream.Collectors;
import model.Transaction;
import model.User;
import repository.impl.TransactionRepository;
import service.ITransactionService;
import view.TransactionView;
import repository.impl.UserRepositoryImpl;

public class TransactionService implements ITransactionService {

    private TransactionRepository transRepo = new TransactionRepository();
    private TransactionView transView = new TransactionView();
    private TransactionValidation tval = new TransactionValidation();
    private RealEstateService realEstateService = new RealEstateService();
    private final UserRepositoryImpl userRepo = new UserRepositoryImpl();
    
    public boolean isExistTransactionInSystem(int id) {
        return transRepo.findTransactionById(id) != null;
    }

    public Transaction getTransactionInSystem(int id) {
        return transRepo.findTransactionById(id);
    }

    public boolean isExistTransactionInList(int id, List<Transaction> tlist) {
        for (Transaction t : tlist) {
            if (t.getTransID() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Transaction> getPendingTransactionList(String status) {
        return transRepo.findAllTransactionByStatus(status);
    }

    @Override
    public List<Transaction> getAllTransactionList() {
        return transRepo.getAll();
    }

    public List<Transaction> getAllTransactionByUserID(int id) {
        List<Transaction> tl2 = new ArrayList<>();
        tl2.addAll(transRepo.findTransactionByBuyerID(id));
        tl2.addAll(transRepo.findTransactionBySellerID(id));
        return tl2;
    }

    @Override
    public void add(Transaction t) {
        transRepo.save(t);
    }

    @Override
    public void delete(int id) {
        Transaction t = transRepo.findTransactionById(id);
        if (t == null) {
            System.out.println("Transaction không tồn tại hoặc đã bị xóa.");
            return;
        }
        transRepo.deleteTransaction(id);
    }

    public void checkStatusTransaction() {
        //get and display all pending transaction
        List<Transaction> tlist = getPendingTransactionList("Pending");
        transView.displayListTransaction(tlist);
        
        //change status confirm
        boolean confirm = tval.continueConfirm("Do you want to change transaction status (Y/N)? ");

        if (confirm) {
            while (confirm) {
                //check transaction ID trong list pending
                int transID = tval.getInt("Enter id of transaction you want to change status: ");
                if (!isExistTransactionInList(transID, tlist)) {
                    System.out.println("Transaction " + transID + " is not found!");
                } else {
                    //neu ton tai, input new status
                    String status = tval.getAndValidStatus("Please input new status: ");
                    //neu status moi ko phai pending, set status va remove khoi danh sach pending
                    Transaction changingTrans = getTransactionInSystem(transID);
                    if (!status.equalsIgnoreCase("Pending")) {
                        changingTrans.setStatus(status);
                        tlist.remove(changingTrans);
                        transRepo.save(changingTrans);
                    }
                    //neu status la completed, cac transactions mua chung property se bi danh cancelled
                    if (status.equalsIgnoreCase("Completed")) {
                        for (Transaction t : getPendingTransactionList("Pending")) {
                            if (t.getREID() == changingTrans.getREID()) {
                                t.setStatus("Cancelled");
                            }
                            transRepo.save(t);
                        }
                    }
                }
                confirm = tval.continueConfirm("Do you want to continue changing transaction status (Y/N)? ");
                //lay danh sach pending sau khi chinh sua de tiep tuc vong lap
                if (confirm) {
                    tlist = getPendingTransactionList("Pending");
                }
            }
        }
    }

    @Override
    public double getTotalRevenue() {
        List<Transaction> completedTransactions = transRepo.getTransactionsByStatus("completed");
        return completedTransactions.isEmpty() ? 0.0 : completedTransactions.stream()
                .mapToDouble(Transaction::getPrice)
                .sum();
    }

    @Override
    public int getTotalHousesSold() {
        List<Transaction> completedTransactions = transRepo.getTransactionsByStatus("completed");
        return completedTransactions.size();
    }

    @Override
    public int getHousesSoldByMonth(int month, int year) {
        List<Transaction> transactions = transRepo.getTransactionsByMonth(month, year);
        return transactions.size();
    }

    @Override
    public List<User> getBuyersByMonth(int month, int year) {
        List<Transaction> transactions = transRepo.getTransactionsByMonth(month, year);
        return transactions.stream()
                .map(t -> userRepo.findById(t.getBuyerID()))
                .filter(u -> u != null)
                .collect(Collectors.toList());
    }
}