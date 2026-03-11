package Dao.impl;

import Dao.IAdminDao;
import Model.Admin;

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

    public static void main(String[] args) {
     IAdminDao adminDao = new AdminDao();
       Admin a = adminDao.findAdminByUsername("vuhao");
        System.out.println(a.getUsername());
    }
}
