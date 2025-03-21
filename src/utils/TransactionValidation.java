/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class TransactionValidation {
    private final Scanner sc = new Scanner(System.in);
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public boolean isInputEmpty(String input){
        if(input.isEmpty()) {
            System.out.println("Input cannot be empty!");
            return true;
        }
        return false;
    }
    
    public String getAndValidValue(String msg, String regex, String cause){
        String input = "";
        while(true){
            System.out.println(msg);
            input = sc.nextLine().trim();
            if(isInputEmpty(input)) continue;
            if(input.matches(regex)) return input;
            System.out.println("Invalid input! " + cause);
        }
    }
    
    public String getAndValidTransactionID(String msg){
        String input = "";
        while(true){
            System.out.println(msg);
            input = sc.nextLine().trim();
            if(isInputEmpty(input)) continue;
            if(input.matches("^\\d+$")) return input;
            System.out.println("Invalid input! Transaction ID has to be positive number!");
        }
    }
    
    public String firstCharacterUppercase(String str){
        if(!str.trim().contains(" ")){
            return Character.toUpperCase(str.trim().charAt(0)) + str.trim().substring(1).toLowerCase();
        }
        String[] words = str.trim().split(" ");
        String result = "";
        for (String word : words) {
            result += Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase() + " ";
        }
        return result.trim();
    }
    
    public String getName(String msg){
        String input = "";
        while(true){
            System.out.println(msg);
            input = sc.nextLine().trim();
            if(isInputEmpty(input)) continue;
            if(input.matches("^([A-Za-z]+)(\\s[A-Za-z]+)*$")){
                return firstCharacterUppercase(input);
            }
            System.out.println("No number or special character accepted!");
        }
    }
    
    public int getInt(String msg){
        String input = "";
        while(true){
            input = getAndValidValue(msg, "^-?\\b\\d+\\b$", "Integer only please!");
            return Integer.parseInt(input);
        }
    }
    
    public boolean continueConfirm(String msg) {
        while (true) {
            System.out.print(msg + "(Press y to continue, n to exit): ");
            String input = sc.nextLine().trim();
            if (isInputEmpty(input)) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }
            if (input.matches("^[yY]$")) {
                return true;
            } else if (input.matches("^[nN]$")) {
                return false;
            }
            System.out.println("Please enter y/Y or n/N. Try again.");
        }
    }
    
    public String getAndValidStatus(String msg){
        String input = "";
        while(true){
            System.out.println(msg);
            input = sc.nextLine().trim();
            if(isInputEmpty(input)) continue;
            if(input.matches("(?i)^(Pending|Accepted|Denied)$")){
                return firstCharacterUppercase(input);
            }
            System.out.println("Please choose 1 in 3 status {Pending, Accepted, Denied}");
        }
    }
}
