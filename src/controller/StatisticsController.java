package controller;

import java.util.Scanner;
import service.TransactionService;
import service.impl.TransactionServiceImpl;
import view.TransactionStatisticsView;

public class StatisticsController {

    private final Scanner sc = new Scanner(System.in);
    private final TransactionService transactionService = new TransactionServiceImpl();
    private final TransactionStatisticsView statsView = new TransactionStatisticsView();

    /**
     * Runs the statistics menu with user interaction.
     */
    public void runStatisticsMenu() {
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Select an option: ");
            try {
                int choice = Integer.parseInt(sc.nextLine().trim());
                switch (choice) {
                    case 1:
                        double revenue = transactionService.getTotalRevenue();
                        statsView.displayTotalRevenue(revenue);
                        break;
                    case 2:
                        int totalSold = transactionService.getTotalHousesSold();
                        statsView.displayTotalHousesSold(totalSold);
                        break;
                    case 3:
                        processHousesSoldByMonth();
                        break;
                    case 4:
                        processBuyersByMonth();
                        break;
                    case 5:
                        running = false;
                        System.out.println("Exiting statistics menu...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please select a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    /**
     * Displays the statistics menu options.
     */
    private void displayMenu() {
        System.out.println("\n--- Transaction Statistics ---");
        System.out.println("1. Revenue Statistics");
        System.out.println("2. Number of Houses Sold");
        System.out.println("3. Houses Sold by Month");
        System.out.println("4. Customers Buying Real Estate by Month");
        System.out.println("5. Exit");
    }

    /**
     * Processes the "Houses Sold by Month" option with input validation.
     */
    private void processHousesSoldByMonth() {
        try {
            System.out.print("Enter month (1-12): ");
            int month = Integer.parseInt(sc.nextLine().trim());
            if (month < 1 || month > 12) {
                System.out.println("Invalid month! Please enter a value between 1 and 12.");
                return;
            }
            System.out.print("Enter year (e.g., 2023): ");
            int year = Integer.parseInt(sc.nextLine().trim());
            if (year < 1900 || year > 9999) {
                System.out.println("Invalid year! Please enter a realistic year.");
                return;
            }
            int soldByMonth = transactionService.getHousesSoldByMonth(month, year);
            statsView.displayHousesSoldByMonth(month, year, soldByMonth);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values for month and year.");
        }
    }

    /**
     * Processes the "Customers Buying Real Estate by Month" option with input validation.
     */
    private void processBuyersByMonth() {
        try {
            System.out.print("Enter month (1-12): ");
            int month = Integer.parseInt(sc.nextLine().trim());
            if (month < 1 || month > 12) {
                System.out.println("Invalid month! Please enter a value between 1 and 12.");
                return;
            }
            System.out.print("Enter year (e.g., 2023): ");
            int year = Integer.parseInt(sc.nextLine().trim());
            if (year < 1900 || year > 9999) {
                System.out.println("Invalid year! Please enter a realistic year.");
                return;
            }
            var buyers = transactionService.getBuyersByMonth(month, year);
            statsView.displayBuyersByMonth(month, year, buyers);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values for month and year.");
        }
    }

    public static void main(String[] args) {
        StatisticsController controller = new StatisticsController();
        controller.runStatisticsMenu();
    }
}