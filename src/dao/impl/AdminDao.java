package dao.impl;

import dao.IAdminDao;
import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.DBUtil.getConnection;

public class AdminDao implements IAdminDao {
    @Override
    public  Admin findAdminByUsername(String username) {

        String sql = "SELECT * FROM quanlysanpham.admin WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Gán giá trị cho tham số ?
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {

               if (rs.next()) {
                return new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
               }
               else  return null;

            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addAdmin(Admin admin) {
        String sql = "INSERT INTO quanlysanpham.admin (username,password) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, admin.getUsername());
            pstm.setString(2, admin.getPassword());


            pstm.executeUpdate();
            System.out.println("Thêm thành công tài khoản : " + admin.getUsername());

        } catch (SQLException e) {
            System.err.println("Lỗi thêm customer: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
