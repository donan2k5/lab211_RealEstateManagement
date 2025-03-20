/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.Transaction;
import repository.impl.TransactionRepository;
import service.ITransactionService;
import utils.TransactionValidation;
import view.TransactionView;

/**
 *
 * @author ADMIN
 */
public class TransactionService implements ITransactionService{
    private TransactionRepository transRepo = new TransactionRepository();
    private List<Transaction> tl = transRepo.readData();
    private TransactionView transView = new TransactionView();
    private TransactionValidation tval = new TransactionValidation();
    
    public boolean isExistTransactionInSystem(int id) {
        return transRepo.findTransactionById(id) != null;
    }
    
    public Transaction getTransactionInSystem(int id){
        return transRepo.findTransactionById(id);
    }
    
    public boolean isExistTransactionInList(int id, List<Transaction> tlist){
        for(Transaction t : tlist){
            if(t.getTransID() == id) return true;
        }
        return false;
    }
    
    @Override
    public List<Transaction> getPendingTransactionList(){
        List<Transaction> tl2 = new ArrayList<>();
        for (Transaction t : tl) {
            if (t.getStatus().equalsIgnoreCase("Pending")) {
                tl2.add(t);
            }
        }
        return tl2;
    }
    
    @Override
    public List<Transaction> getAllTransactionList(){
        return tl;
    }
    
    public List<Transaction> getAllTransactionByUserID(int id){
        List<Transaction> tl2 = new ArrayList<>();
        tl2.addAll(transRepo.findTransactionByBuyerID(id));
        tl2.addAll(transRepo.findTransactionBySellerID(id));
        return tl2;
    }
    
    @Override
    public void add(Transaction t){
        if (isExistTransactionInSystem(t.getTransID())) return;
        tl.add(t);
        transRepo.save(t);
    }
    
    public void delete(int id){
        Transaction t = getTransactionInSystem(id);
        if (t == null) return;
        transRepo.deleteTransaction(t);
        tl.remove(t);
    }
    
    @Override
    public void delete(String id){
        //Transaction ID la int nen khong the xai delete String tu Service.java
    }
    
    @Override
    public void update(String id){
        
    }
    
    public void checkStatusTransaction(){
        //get and display all pending transaction
        List<Transaction> tlist = getPendingTransactionList();
        transView.displayListTransaction(tlist);
        
        boolean confirm = tval.continueConfirm("Do you want to change transaction status (Y/N)? ");
        
        if (confirm){
            while (confirm){
                //check transaction ID trong list pending
                int transID = tval.getInt("Enter id of transaction you want to change status: ");
                    if (!isExistTransactionInList(transID, tlist)) {
                        System.out.println("Transaction " + transID + " is not found!");
                    } else {
                        //neu ton tai, input new status
                        String status = tval.getAndValidStatus("Please input new status: ");
                        //neu status moi ko phai pending, set status va remove khoi danh sach pending
                        Transaction changingTrans = getTransactionInSystem(transID);
                        if (!status.equalsIgnoreCase("Pending")){
                            changingTrans.setStatus(status);
                            tlist.remove(changingTrans);
                            transRepo.save(changingTrans);
                        }
                        //neu status la accepted, cac transactions mua chung property se bi danh denied
                        if (status.equalsIgnoreCase("Accepted")){
                            for (Transaction t : getPendingTransactionList()){
                                if (t.getREID() == changingTrans.getREID()) t.setStatus("Denied");
                                transRepo.save(t);
                            }
                        }
                    }
                confirm = tval.continueConfirm("Do you want to continue changing transaction status (Y/N)? ");
                //lay danh sach pending sau khi chinh sua de tiep tuc vong lap
                if (confirm) tlist = getPendingTransactionList();
            }
        }
    }
}
