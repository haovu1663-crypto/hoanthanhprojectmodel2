package Service.impl;

import Dao.IProductDao;
import Dao.impl.ProductDao;
import Model.Product;
import Service.IProductService;

import java.util.ArrayList;

public class ProductService implements IProductService {
    IProductDao productDao = new ProductDao();
    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public void showAllProducts() {
        productDao.showAllProducts();
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public ArrayList<Product> showProductbyBrand(String brand) {
        ArrayList<Product> products =productDao.findProductsbyBrand(brand) ;
        return products;
    }

    @Override
    public void deleteProduct(int id) {
        productDao.deleteProduct(id);
    }

    @Override
    public ArrayList<Product> showProductbyPrice(double price) {
        ArrayList<Product> products =productDao.findProductsbyPrice(price) ;
        return products;
    }

    @Override
    public ArrayList<Product> showProductbystock(int stock) {
        ArrayList<Product> products =productDao.findProductsbyStock(stock) ;
        return products;
    }
}
