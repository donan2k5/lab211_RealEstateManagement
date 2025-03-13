package view;

import java.util.List;
import model.User;

public class TransactionStatisticsView {

    public void displayTotalRevenue(double revenue) {
        System.out.println("Total revenue: " + revenue);
    }
    
    public void displayTotalHousesSold(int count) {
        System.out.println("Total number of houses sold: " + count);
    }
    
    public void displayHousesSoldByMonth(int month, int year, int count) {
        System.out.println("Number of houses sold in " + month + "/" + year + ": " + count);
    }
    
    public void displayBuyersByMonth(int month, int year, List<User> buyers) {
        if (buyers.isEmpty()) {
            System.out.println("No customers purchased real estate in " + month + "/" + year);
        } else {
            System.out.println("List of customers who purchased real estate in " + month + "/" + year + ":");
            System.out.printf("%-10s %-15s %-25s\n", "ID", "Username", "Email");
            for (User u : buyers) {
                System.out.printf("%-10d %-15s %-25s\n", u.getUserId(), u.getUsername(), u.getEmail());
            }
        }
    }
}