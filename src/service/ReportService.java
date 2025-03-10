package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import context.DBContext;

public class ReportService {
    public double getTotalRevenue() {
        double totalRevenue = 0;
        String query = "SELECT COALESCE(SUM(price), 0) FROM transaction WHERE status = 'Completed'";
        try (Connection conn = new DBContext().connection;
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalRevenue = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRevenue;
    }
    
    public int getTotalHousesSold() {
        int totalHouses = 0;
        String query = "SELECT COUNT(*) FROM transaction WHERE status = 'Completed'";
        try (Connection conn = new DBContext().connection;
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalHouses = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalHouses;
    }
    
    public int getHousesSoldByMonth(int month, int year) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM transaction WHERE status = 'Completed' AND MONTH(created_at) = ? AND YEAR(created_at) = ?";
        try (Connection conn = new DBContext().connection;
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public List<String> getCustomersByMonth(int month, int year) {
        List<String> customers = new ArrayList<>();
        String query = "SELECT u.id, u.username, u.email FROM transaction t JOIN [user] u ON t.buyerID = u.id WHERE t.status = 'Completed' AND MONTH(t.created_at) = ? AND YEAR(t.created_at) = ?";
        try (Connection conn = new DBContext().connection;
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customers.add("ID: " + rs.getInt("id") + ", Name: " + rs.getString("username") + ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    
    public int getHousesSoldByYear(int year) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM transaction WHERE status = 'Completed' AND YEAR(created_at) = ?";
        try (Connection conn = new DBContext().connection;
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public List<String> getCustomersByYear(int year) {
        List<String> customers = new ArrayList<>();
        String query = "SELECT u.id, u.username, u.email FROM transaction t JOIN [user] u ON t.buyerID = u.id WHERE t.status = 'Completed' AND YEAR(t.created_at) = ?";
        try (Connection conn = new DBContext().
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customers.add("ID: " + rs.getInt("id") + ", Name: " + rs.getString("username") + ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
