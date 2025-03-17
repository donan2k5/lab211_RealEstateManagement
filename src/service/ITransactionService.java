/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Transaction;

/**
 *
 * @author ADMIN
 */
public interface ITransactionService extends Service<Transaction>{
    List<Transaction> getPendingTransactionList();
    List<Transaction> getAllTransactionList();
    
}
