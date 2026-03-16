package Dao.impl;

import Dao.IinvoiceDao;
import Model.Customer;
import Model.Invoice;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static utils.DBUtil.getConnection;

public class IvoiceDao implements IinvoiceDao {
    @Override
    public int addInvoice(Invoice invoice) {
        String sql = "INSERT INTO quanlysanpham.invoice (customer_id, total_amount) VALUES (?, 0) RETURNING id";
        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, invoice.getCustomerId());

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int newId = rs.getInt("id");
                    System.out.println("Thêm hóa đơn thành công! ID: " + newId);
                    return newId;
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi thêm invoice: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public void showAllInvoices() {
        String sql = "SELECT DISTINCT i.id AS invoice_id,\n" +
                "                c.name AS customer_name,\n" +
                "                i.created_at,\n" +
                "                i.total_amount\n" +
                "FROM quanlysanpham.invoice i\n" +
                "         JOIN quanlysanpham.customer c\n" +
                "              ON i.customer_id = c.id\n" +
                "         JOIN quanlysanpham.invoice_details d\n" +
                "              ON i.id = d.invoice_id;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {

                System.out.println("\n" + "=".repeat(72));
                System.out.printf("| %-5s | %-25s | %-15s | %-15s | \n", "ID", "Customer Name", "Created At", "Total Amount");
                System.out.println("-".repeat(72));

                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    java.time.LocalDate datee = rs.getDate(3).toLocalDate();
                    double total = rs.getDouble(4);

                    System.out.printf("| %-5d | %-25s | %-15s | %-15.2f | \n", id, name, datee.toString(), total);
                }
                System.out.println("=".repeat(72));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findbynam(String name) {
        String sql = "SELECT i.id AS invoice_id,\n" +
                "       c.name AS customer_name,\n" +
                "       p.name AS product_name,\n" +
                "       i.created_at,\n" +
                "       d.quantity,\n" +
                "       (d.quantity * p.price) AS total_line_amount\n" +
                "FROM quanlysanpham.invoice i\n" +
                "JOIN quanlysanpham.customer c \n" +
                "       ON i.customer_id = c.id\n" +
                "JOIN quanlysanpham.invoice_details d \n" +
                "       ON i.id = d.invoice_id\n" +
                "JOIN quanlysanpham.product p \n" +
                "       ON d.product_id = p.id\n" +
                "WHERE c.name = ?;\n";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            System.out.println("\n" + "=".repeat(109));
            System.out.printf("| %-5s | %-20s | %-25s | %-15s | %-10s | %-15s |\n",
                    "ID", "Customer Name", "Product Name", "Created At", "Quantity", "Total Amount");
            System.out.println("-".repeat(109));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String customerName = rs.getString(2);
                    String productName = rs.getString(3);
                    java.time.LocalDate datee = rs.getDate(4).toLocalDate();
                    int quantity = rs.getInt(5);
                    double totalLine = rs.getDouble(6);
                    System.out.printf("| %-5d | %-20s | %-25s | %-15s | %-10d | %-15.2f |\n",
                            id, customerName, productName, datee.toString(), quantity, totalLine);
                }
                System.out.println("=".repeat(109));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findbydate(LocalDate date) {
        String sql = "SELECT i.id AS invoice_id,\n" +
                "       c.name AS customer_name,\n" +
                "       p.name AS product_name,\n" +
                "       i.created_at,\n" +
                "       d.quantity,\n" +
                "       (d.quantity * p.price) AS total_line_amount\n" +
                "FROM quanlysanpham.invoice i\n" +
                "JOIN quanlysanpham.customer c \n" +
                "       ON i.customer_id = c.id\n" +
                "JOIN quanlysanpham.invoice_details d \n" +
                "       ON i.id = d.invoice_id\n" +
                "JOIN quanlysanpham.product p \n" +
                "       ON d.product_id = p.id\n" +
                "WHERE i.created_at::date = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, date);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("\n" + "=".repeat(109));
                System.out.printf("| %-5s | %-20s | %-25s | %-15s | %-10s | %-15s |\n",
                        "ID", "Customer Name", "Product Name", "Created At", "Quantity", "Total Amount");
                System.out.println("-".repeat(109));

                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;
                    int id = rs.getInt(1);
                    String customerName = rs.getString(2);
                    String productName = rs.getString(3);
                    java.time.LocalDate datee = rs.getDate(4).toLocalDate();
                    int quantity = rs.getInt(5);
                    double totalLine = rs.getDouble(6);
                    System.out.printf("| %-5d | %-20s | %-25s | %-15s | %-10d | %-15.2f |\n",
                            id, customerName, productName, datee.toString(), quantity, totalLine);
                }

                if (!hasData) {
                    System.out.println("| " + String.format("%-82s", "Không có hóa đơn nào trong ngày này.") + " |");
                }
                System.out.println("=".repeat(109));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    // thống kê theo ngày
    public static void thongkedate() {
        String sql = "SELECT created_at::date AS order_date, SUM(total_amount) AS daily_total " +
                "FROM quanlysanpham.invoice " +
                "GROUP BY created_at::date " +
                "ORDER BY order_date DESC;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n" + "=".repeat(42));
            System.out.printf("| %-15s | %-20s |\n", "Ngày", "Tổng doanh thu");
            System.out.println("-".repeat(42));

            while (rs.next()) {
                LocalDate date = rs.getDate("order_date").toLocalDate();
                String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                double total = rs.getDouble("daily_total");

                System.out.printf("| %-15s | %-20.2f |\n", formattedDate, total);
            }
            System.out.println("=".repeat(42));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // thông kê tháng
    public static void thongkeMonth() {
        String sql = "SELECT " +
                "    TO_CHAR(DATE_TRUNC('month', created_at), 'YYYY/MM') AS month_period, " +
                "    COUNT(id) AS total_invoices, " +
                "    SUM(total_amount) AS monthly_revenue " +
                "FROM quanlysanpham.invoice " +
                "GROUP BY DATE_TRUNC('month', created_at) " +
                "ORDER BY DATE_TRUNC('month', created_at) DESC;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n" + "=".repeat(63));
            System.out.printf("| %-15s | %-18s | %-20s |\n", "Tháng/Năm", "Số lượng HĐ", "Tổng doanh thu");
            System.out.println("-".repeat(63));

            while (rs.next()) {
                String month = rs.getString("month_period");
                int count = rs.getInt("total_invoices");
                double total = rs.getDouble("monthly_revenue");

                System.out.printf("| %-15s | %-18d | %-20.2f |\n", month, count, total);
            }
            System.out.println("=".repeat(63));

        } catch (SQLException e) {
            System.err.println("Lỗi thống kê: " + e.getMessage());
        }
    }
    // năm
    public static void thongkeYear() {
        String sql = "SELECT " +
                "    EXTRACT(YEAR FROM created_at) AS order_year, " +
                "    COUNT(id) AS total_invoices, " +
                "    SUM(total_amount) AS yearly_revenue " +
                "FROM quanlysanpham.invoice " +
                "GROUP BY order_year " +
                "ORDER BY order_year DESC;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n" + "=".repeat(57));
            System.out.printf("| %-10s | %-15s | %-22s |\n", "Năm", "Số lượng HĐ", "Tổng doanh thu");
            System.out.println("-".repeat(57));
            while (rs.next()) {
                int year = rs.getInt("order_year");
                int count = rs.getInt("total_invoices");
                double total = rs.getDouble("yearly_revenue");

                System.out.printf("| %-10d | %-15d | %-22.2f |\n", year, count, total);
            }
            System.out.println("=".repeat(57));

        } catch (SQLException e) {
            System.err.println("Lỗi khi thống kê theo năm: " + e.getMessage());
        }
    }
}
