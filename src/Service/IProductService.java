package Service;

import Model.Product;

import java.util.ArrayList;
import java.util.List;

public interface IProductService {
    void addProduct(Product product);
    void showAllProducts();
    void updateProduct(Product product);
    List<Product> showProductbyBrand(String brand);
    void deleteProduct(int id);
    List<Product> showProductbyPrice(double price);
    List<Product> showProductbystock(int stock);
}
