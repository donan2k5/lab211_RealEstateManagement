/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;
import repository.ITransactionRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ADMIN
 */
public class TransactionRepository implements ITransactionRepository{
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private String filename = "src/data/transaction.txt";
    private List<Transaction> tl = new ArrayList<>();

    @Override
    public Transaction findTransactionById(int id) {
        for (Transaction t : tl) {
            if (t.getTransID() == id) {
                return t;
            }
        }
        return null;
    }
    
    @Override
    public List<Transaction> findTransactionsByREID(String id){
        List<Transaction> tl2 = new ArrayList<>();
        for (Transaction t : tl) {
            if (t.getREID().equals(id.trim())) {
                tl2.add(t);
            }
        }
        return tl2;
    }
    
    @Override
    public List<Transaction> findTransactionBySellerID(int id){
        List<Transaction> tl2 = new ArrayList<>();
        for (Transaction t : tl) {
            if (t.getSellerID() == id) {
                tl2.add(t);
            }
        }
        return tl2;
    }
    
    @Override
    public List<Transaction> findTransactionByBuyerID(int id){
        List<Transaction> tl2 = new ArrayList<>();
        for (Transaction t : tl) {
            if (t.getBuyerID() == id) {
                tl2.add(t);
            }
        }
        return tl2;
    }
    
    @Override
    public List<Transaction> readData() {
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line = "";
            while ((line = br.readLine()) != null){
                if (line.trim().isEmpty()) continue;
                String[] split = line.split(",");
                int transID = Integer.parseInt(split[0].trim());
                String REID = split[1].trim();
                int sellerID = Integer.parseInt(split[2].trim());
                int buyerID = Integer.parseInt(split[3].trim());
                double price = Double.parseDouble(split[4].trim());
                double deposit = Double.parseDouble(split[5].trim());
                LocalDate expirationTime = LocalDate.parse(split[6].trim(), dtf);
                String status = split[7].trim();
                LocalDate createTime = LocalDate.parse(split[8].trim(), dtf);
                LocalDate updateTime = LocalDate.parse(split[9].trim(), dtf);
                
                tl.add(new Transaction(transID, REID, sellerID, buyerID, price, deposit, expirationTime, status, createTime, updateTime));
            }
        } catch (Exception ex){
            System.out.println(ex.toString());
        }
        return tl;
    }
    
    @Override
    public void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            if (tl.isEmpty()) {
                return;
            }
            for (Transaction t : tl) {
                bw.write(t.toString());
                bw.newLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void deleteTransaction(Transaction t){
        tl.remove(t);
    }
    
    public int getLastID(){
        return tl.get(tl.size()-1).getTransID();
    }
}
