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
        String sql = "SELECT i.id AS invoice_id,\n" +
                "       p.name AS product_name,\n" +
                "       i.created_at,\n" +
                "       i.total_amount\n" +
                "FROM quanlysanpham.invoice i\n" +
                "JOIN quanlysanpham.invoice_details d \n" +
                "       ON i.id = d.invoice_id\n" +
                "JOIN quanlysanpham.product p \n" +
                "       ON d.product_id = p.id;\n ";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("\n==============================Danh sách==============================");
                System.out.printf("%-5s %-25s %-20s %-20s  \n", "ID", "Customer name", "Create at","Total Amount ");
                System.out.println("-".repeat(80));

                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    java.time.LocalDate datee = rs.getDate(3).toLocalDate();
                    double pric =rs.getDouble(4);


                    System.out.printf("%-5d %-25s %-20s %-20s  \n", id, name, datee.toString(), pric);
                }
                System.out.println("-".repeat(80));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findbynam(String name) {
        String sql = "SELECT \n" +
                "    i.id AS invoice_id, \n" +
                "    c.name AS customer_name, \n" +
                "    i.created_at, \n" +
                "    i.total_amount\n" +
                "FROM quanlysanpham.invoice i\n" +
                "JOIN quanlysanpham.customer c ON i.customer_id = c.id\n" +
                "WHERE c.name = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            System.out.println("\n==============================Danh sách==============================");
            System.out.printf("%-5s %-25s %-20s %-20s  \n", "ID", "Customer name", "Create at","Total Amount ");
            System.out.println("-".repeat(80));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String namen = rs.getString(2);
                    java.time.LocalDate datee = rs.getDate(3).toLocalDate();
                    double pric =rs.getDouble(4);
                    System.out.printf("%-5d %-25s %-20s %-20s  \n", id, namen, datee.toString(), pric);
                }  ;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void findbydate(LocalDate date) {
        String sql = "SELECT " +
                "    i.id AS invoice_id, " +
                "    c.name AS customer_name, " +
                "    i.created_at, " +
                "    i.total_amount " +
                "FROM quanlysanpham.invoice i " +
                "JOIN quanlysanpham.customer c ON i.customer_id = c.id " +
                "WHERE i.created_at::date = ?;"; // Sử dụng ::date để so sánh ngày

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Truyền trực tiếp LocalDate vào (JDBC 4.2+ hỗ trợ rất tốt)
            pstmt.setObject(1, date);

            System.out.println("\n============================== DANH SÁCH HÓA ĐƠN NGÀY " + date + " ==============================");
            System.out.printf("%-5s %-25s %-20s %-20s\n", "ID", "Customer name", "Created at", "Total Amount");
            System.out.println("-".repeat(85));

            try (ResultSet rs = pstmt.executeQuery()) {
                boolean hasData = false;

                while (rs.next()) {
                    hasData = true;
                    int id = rs.getInt("invoice_id");
                    String customerName = rs.getString("customer_name");


                    java.time.LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                    double total = rs.getDouble("total_amount");

                    System.out.printf("%-5d %-25s %-20s %-20.2f\n",
                            id, customerName, createdAt.toLocalDate().toString(), total);
                }

                if (!hasData) {
                    System.out.println("Không có hóa đơn nào trong ngày này.");
                }
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

            System.out.println("\n========== BÁO CÁO DOANH THU THEO NGÀY ==========");
            System.out.printf("%-15s %-20s\n", "Ngày", "Tổng doanh thu");
            System.out.println("-".repeat(40));

            while (rs.next()) {
                LocalDate date = rs.getDate("order_date").toLocalDate();
                String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                double total = rs.getDouble("daily_total");

                System.out.printf("%-15s %-20.2f\n", formattedDate, total);
            }
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

            System.out.println("\n========== BÁO CÁO DOANH THU THEO THÁNG ==========");
            System.out.printf("%-15s %-20s %-20s\n", "Tháng/Năm", "Số lượng HĐ", "Tổng doanh thu");
            System.out.println("-".repeat(60));

            while (rs.next()) {
                String month = rs.getString("month_period");
                int count = rs.getInt("total_invoices");
                double total = rs.getDouble("monthly_revenue");

                System.out.printf("%-15s %-20d %-20.2f\n", month, count, total);
            }
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

            System.out.println("\n========== BẢNG THỐNG KÊ DOANH THU THEO NĂM ==========");
            System.out.printf("%-10s %-15s %-20s\n", "Năm", "Số lượng HĐ", "Tổng doanh thu");
            System.out.println("-".repeat(50));

            while (rs.next()) {
                int year = rs.getInt("order_year");
                int count = rs.getInt("total_invoices");
                double total = rs.getDouble("yearly_revenue");

                System.out.printf("%-10d %-15d %-20.2f\n", year, count, total);
            }
            System.out.println("-".repeat(50));

        } catch (SQLException e) {
            System.err.println("Lỗi khi thống kê theo năm: " + e.getMessage());
        }
    }
}
