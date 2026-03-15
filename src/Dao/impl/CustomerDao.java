package Dao.impl;

import Dao.ICustomerDao;
import Model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.DBUtil.getConnection;

public class CustomerDao implements ICustomerDao {
    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO quanlysanpham.customer (name, phone, email, address) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, customer.getName());
            pstm.setString(2, customer.getPhone());
            pstm.setString(3, customer.getEmail());
            pstm.setString(4, customer.getAddress());

            pstm.executeUpdate();
            System.out.println("Thêm thành công: " + customer.getName());

        } catch (SQLException e) {
            System.err.println("Lỗi thêm customer: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        String sql = "SELECT * FROM quanlysanpham.customer WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String customerEmail = rs.getString("email");
                    String address = rs.getString("address");
                    return new Customer(id, name, phone, customerEmail, address);
                } else return null;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void showAllCustomers() {
        String sql = "SELECT * FROM quanlysanpham.customer";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("\n" + "=".repeat(106));
                System.out.printf("| %-5s | %-20s | %-12s | %-28s | %-25s |\n",
                        "ID", "Customer Name", "Phone", "Email", "Address");
                System.out.println("-".repeat(106));

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String address = rs.getString("address");

                    System.out.printf("| %-5d | %-20s | %-12s | %-28s | %-25s |\n",
                            id, name, phone, email, address);
                }
                System.out.println("=".repeat(106));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE quanlysanpham.customer SET name = ?, phone = ?, email = ?, address = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, customer.getName());
            pstm.setString(2, customer.getPhone());
            pstm.setString(3, customer.getEmail());
            pstm.setString(4, customer.getAddress());
            pstm.setInt(5, customer.getId());

            pstm.executeUpdate();
            System.out.println("Update thành công: " + customer.getName());

        } catch (SQLException e) {
            System.err.println("Lỗi Update customer: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findCustomerById(int id) {
        String sql = "SELECT * FROM quanlysanpham.customer WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int customerId = rs.getInt("id");
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    return new Customer(customerId, name, phone, email, address);
                } else return null;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(int id) {
        String sql = "DELETE FROM quanlysanpham.customer WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();
            System.out.println("Delete thành công!");

        } catch (SQLException e) {
            System.err.println("Lỗi Delete: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
