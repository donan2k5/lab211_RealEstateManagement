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
import view.TransactionView;

/**
 *
 * @author ADMIN
 */
public class TransactionService implements ITransactionService{
    private TransactionRepository transRepo = new TransactionRepository();
    private List<Transaction> tl = transRepo.readData();
    private TransactionView transView = new TransactionView();
    
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
    
    @Override
    public void add(Transaction t){
        if (isExistTransactionInSystem(t.getTransID())) return;
        tl.add(t);
        transRepo.save();
    }
    
    public void delete(int id){
        Transaction t = getTransactionInSystem(id);
        if (t == null) return;
        transRepo.deleteTransaction(t);
        transRepo.save();
    }
    
    @Override
    public void delete(String id){
        //Transaction ID la int nen khong the xai delete String tu Service.java
    }
    
    @Override
    public void update(String id){
        
    }
}
