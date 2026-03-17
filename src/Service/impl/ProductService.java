package Service.impl;

import Dao.IProductDao;
import Dao.impl.ProductDao;
import Model.Product;
import Service.IProductService;

import java.util.ArrayList;
import java.util.List;

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
    public List<Product> showProductbyBrand(String brand) {
        List<Product> products =productDao.findProductsbyBrand(brand) ;
        return products;
    }

    @Override
    public void deleteProduct(int id) {
        productDao.deleteProduct(id);
    }

    @Override
    public List<Product> showProductbyPrice(double price) {
        List<Product> products =productDao.findProductsbyPrice(price) ;
        return products;
    }

    @Override
    public List<Product> showProductbystock(int stock) {
        List<Product> products =productDao.findProductsbyStock(stock) ;
        return products;
    }
}
