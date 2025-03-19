package service;

import java.util.List;
import model.User;

public interface TransactionService {
    double getTotalRevenue();
    int getTotalHousesSold();
    int getHousesSoldByMonth(int month, int year);
    List<User> getBuyersByMonth(int month, int year);
}
    