package Dao;

import Model.Product;

import java.util.ArrayList;

public interface IProductDao {
    public void addProduct(Product product);
    public Product findProductByname(String name );
     void showAllProducts();
     void updateProduct(Product product);
     Product findProductById(int id);
     ArrayList<Product>findProductsbyBrand(String brand);
     void deleteProduct(int id);
    ArrayList<Product>findProductsbyPrice(double price);
    ArrayList<Product>findProductsbyStock(int stock);
}
