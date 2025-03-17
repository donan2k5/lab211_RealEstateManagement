/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Transaction;
import utils.TransactionValidation;

/**
 *
 * @author ADMIN
 */
public class TransactionView {
    private TransactionValidation tv = new TransactionValidation();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TransactionView() {
    }
    
    public void displaySingleTransaction(Transaction t){
        System.out.println("-----------------------------------------------------------------------------  Transaction  ------------------------------------------------------------------------------");
        System.out.printf("%-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-20s | %-15s | %-20s | %-20s\n",
                "Transaction ID", "Real Estate ID", "Seller ID", "Buyer ID", "Price", "Deposit", "Expiration", "Status", "Create Time", "Update Time");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        System.out.printf("%-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-20s | %-15s | %-20s | %-20s\n",
                t.getTransID(),
                t.getREID(),
                t.getSellerID(),
                t.getBuyerID(),
                t.getPrice(),
                t.getDeposit(),
                t.getExpirationTime().format(dtf),
                t.getStatus(),
                t.getCreateTime().format(dtf),
                t.getUpdateTime().format(dtf));
    }
    
    public void displayListTransaction(List<Transaction> tl){
        System.out.println("-----------------------------------------------------------------------------  Transaction  ------------------------------------------------------------------------------");
        System.out.printf("%-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-20s | %-15s | %-20s | %-20s\n",
                "Transaction ID", "Real Estate ID", "Seller ID", "Buyer ID", "Price", "Deposit", "Expiration", "Status", "Create Time", "Update Time");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Transaction t : tl){
            System.out.printf("%-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-20s | %-15s | %-20s | %-20s\n",
                    t.getTransID(),
                    t.getREID(),
                    t.getSellerID(),
                    t.getBuyerID(),
                    t.getPrice(),
                    t.getDeposit(),
                    t.getExpirationTime().format(dtf),
                    t.getStatus(),
                    t.getCreateTime().format(dtf),
                    t.getUpdateTime().format(dtf));
        }
    }
    
    public void showMsg(String msg){
        System.out.print(msg);
    }
}
