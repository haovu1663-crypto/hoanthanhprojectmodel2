package Service;

import Model.Product;

import java.util.ArrayList;

public interface IProductService {
    void addProduct(Product product);
    void showAllProducts();
    void updateProduct(Product product);
    ArrayList<Product> showProductbyBrand(String brand);
    void deleteProduct(int id);
    ArrayList<Product> showProductbyPrice(double price);
    ArrayList<Product> showProductbystock(int stock);
}
