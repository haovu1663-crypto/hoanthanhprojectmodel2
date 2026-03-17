package dao;

import model.Product;

import java.util.List;

public interface IProductDao {
    public void addProduct(Product product);
    public Product findProductByname(String name );
     void showAllProducts();
     void updateProduct(Product product);
     Product findProductById(int id);
     List<Product>findProductsbyBrand(String brand);
     void deleteProduct(int id);
    List<Product>findProductsbyPrice(double price);
    List<Product> findProductsbyStock(int stock);
}
