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
        //counting transaction ID
        int transID = tr.getLastID() + 1;
        
        int REID = v.getValidInteger("Enter id of RE you want to buy: ");

        //dang bi loi REID la String ben RealEstate
        RealEstate estate = rr.findEstateById(REID);
        
        //return null neu khong tim duoc RealEstate can mua
        if (estate == null) {
            showMsg("Cant find RealEstate with ID " + REID + "!");
            return null;
        }
        
        //display RE
        rv.displaySearchSingleResult(estate);
        
        //get property's owner, dang bi loi vi Owner la String ben RealEstate, can OwnerID (chua co method)
        int sellerID = estate.getOwner();
        
        //display price and get deposit 10% -> 50% price
        double price = estate.getPrice();
        showMsg("This property is currently priced at " + price + "\n");
        double deposit = v.checkValidDouble("Please enter the deposit amount: ", "Value must be from 10% to 50% the price! (" + price/10 + " to " + price/2 + ")!", price/10, price/2);
        
        //set status default pending
        String status = "Pending";
        
        LocalDate today = LocalDate.now();
        LocalDate expirationTime = today.plusDays(10);
        LocalDate createTime = today;
        LocalDate updateTime = today;
        
        return new Transaction(transID, REID, sellerID, buyerID, price, deposit, expirationTime, status, createTime, updateTime);
    }
}
