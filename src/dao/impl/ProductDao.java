package dao.impl;

import dao.IProductDao;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utils.DBUtil.getConnection;

public class ProductDao implements IProductDao {
    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO quanlysanpham.product (name, brand, price, stock)\n" +
                "VALUES (?, ?, ?, ?);";
        try (Connection conn =  getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, product.getName());
            pstm.setString(2,product.getBrand());
            pstm.setDouble(3,product.getPrice());

            pstm.setInt(4,product.getStock());

            pstm.executeUpdate();
            System.out.println("Thêm thành công: " + product.getName());

        } catch (SQLException e) {
            System.err.println("Lỗi thêm thêm product: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findProductByname(String name) {
        String sql = "SELECT * FROM quanlysanpham.product WHERE name = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Gán giá trị cho tham số ?
            pstmt.setString(1, name);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return new Product(rs.getInt("id"), rs.getString("name"), rs.getString("brand"), rs.getDouble("price"), rs.getInt("stock"));
                }
                else  return null;

            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }


    @Override
    public void showAllProducts() {
        String sql = "SELECT * FROM quanlysanpham.product order by id ASC ";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("\n" + "=".repeat(86));
            System.out.printf("| %-5s | %-25s | %-15s | %-15s | %-10s |\n",
                    "ID", "Product Name", "Brand", "Price", "Stock");
            System.out.println("-".repeat(86));

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String brand = rs.getString(3);
                double price = rs.getDouble(4);
                int stock = rs.getInt(5);
                System.out.printf("| %-5d | %-25s | %-15s | %-15.2f | %-10d |\n",
                        id, name, brand, price, stock);
            }

            System.out.println("=".repeat(86));
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE quanlysanpham.product SET name = ?, brand = ?, price = ?, stock = ? WHERE id = ?";

        try (Connection conn =  getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, product.getName());
            pstm.setString(2,product.getBrand());
            pstm.setDouble(3,product.getPrice());
            pstm.setInt(4,product.getStock());
            pstm.setInt(5,product.getId());

            pstm.executeUpdate();
            System.out.println("Update thành công: " + product.getName());

        } catch (SQLException e) {
            System.err.println("Lỗi Update product: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public  Product findProductById(int id) {
        String sql = "SELECT * FROM quanlysanpham.product WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Gán giá trị cho tham số ?
            pstmt.setInt(1,id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return new Product(rs.getInt("id"), rs.getString("name"), rs.getString("brand"), rs.getDouble("price"), rs.getInt("stock"));
                }
                else  return null;

            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> findProductsbyBrand(String brand) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM quanlysanpham.product WHERE brand ILIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + brand + "%");

            try (ResultSet rs = pstmt.executeQuery()) {


                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String Brand = rs.getString(3);
                    double pric =rs.getDouble(4);
                    int stock = rs.getInt(5);
                    Product p = new Product(id,name,Brand,pric,stock);
                    products.add(p);
                }

            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void deleteProduct(int id) {
        String sql = "DELETE FROM quanlysanpham.product WHERE id = ?";

        try (Connection conn =  getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1,id);
            pstm.executeUpdate();
            System.out.println("Delete thành công: " );

        } catch (SQLException e) {
            System.err.println("Lỗi Delete: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findProductsbyPrice(double price) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM quanlysanpham.product WHERE price >= ? and price <= ? ";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, price);
            pstmt.setDouble(2, price+100);

            try (ResultSet rs = pstmt.executeQuery()) {


                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String Brand = rs.getString(3);
                    double pric =rs.getDouble(4);
                    int stock = rs.getInt(5);
                    Product p = new Product(id,name,Brand,pric,stock);
                    products.add(p);
                }

            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public List<Product> findProductsbyStock(int stoc) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM quanlysanpham.product WHERE stock=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stoc);


            try (ResultSet rs = pstmt.executeQuery()) {


                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String Brand = rs.getString(3);
                    double pric =rs.getDouble(4);
                    int stock = rs.getInt(5);
                    Product p = new Product(id,name,Brand,pric,stock);
                    products.add(p);
                }

            }
        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return products;
    }

    public static void showProductIdAndName() {
        String sql = "SELECT id, name FROM quanlysanpham.product";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Tổng cộng 40 dấu "=" để bảng cân đối
            System.out.println("\n" + "=".repeat(40));
            System.out.printf("| %-5s | %-28s |\n", "ID", "Product Name");
            System.out.println("-".repeat(40));

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                // Căn chỉnh nội dung chuẩn theo khung
                System.out.printf("| %-5d | %-28s |\n", id, name);
            }

            System.out.println("=".repeat(40));

        } catch (SQLException e) {
            System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
