/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.RealEstate;
import model.Transaction;
import repository.impl.RealEstateRepository;
import repository.impl.TransactionRepository;
import service.impl.AuthenticationServiceImpl;
import utils.TransactionValidation;

/**
 *
 * @author ADMIN
 */
public class TransactionView {
    private TransactionValidation tv = new TransactionValidation();
    private Validation v = new Validation();
    
    private TransactionRepository tr = new TransactionRepository();
    private RealEstateRepository rr = new RealEstateRepository();

    private RealEstateView rv = new RealEstateView();
    
    private AuthenticationServiceImpl as = new AuthenticationServiceImpl();
    
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
    
    public Transaction createTransactionBuyRE(int buyerID){
        int REID;
        RealEstate realEstate;
        while(true) {
            REID = v.getValidInteger("Enter id of RE you want to buy: ");
            realEstate = rr.findEstateById(String.valueOf(REID));
            if(realEstate != null) {
                break;
            }
            System.out.println("Real estate not found");
        }

        //display RE
        rv.displaySearchSingleResult(realEstate);
        
        int sellerID = realEstate.getOwner();
        
        //display price and get deposit 10% -> 50% price
        double price = realEstate.getPrice();
        showMsg("This property is currently priced at " + price + "\n");
        double deposit = v.checkValidDouble("Please enter the deposit amount: ", "Value must be from 10% to 50% the price! (" + price/10 + " to " + price/2 + ")!", price/10, price/2);
        
        LocalDate today = LocalDate.now();
        LocalDate expirationTime = today.plusDays(10);
         
        return new Transaction(REID, sellerID, buyerID, price, deposit, expirationTime);
    }
}
