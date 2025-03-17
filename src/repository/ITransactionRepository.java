/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.Transaction;

/**
 *
 * @author ADMIN
 */
public interface ITransactionRepository extends Repository<Transaction>{
        Transaction findTransactionById(int id);
        List<Transaction> findTransactionsByREID(String id);
        List<Transaction> findTransactionBySellerID(int id);
        List<Transaction> findTransactionByBuyerID(int id);
        void deleteTransaction(Transaction t);

}
