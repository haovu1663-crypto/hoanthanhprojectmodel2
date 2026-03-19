package service.impl;

import dao.IProductDao;
import dao.impl.InvoiceDetailDao;
import dao.impl.ProductDao;
import model.InvoiceDetail;
import model.Product;
import presentation.UserView;
import service.IProductService;

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
       List<InvoiceDetail> i  = InvoiceDetailDao.findInvoiceDetailbyidProdusct(id);
       if(i!=null){
           System.out.println(UserView.RED+"sản phẩm này đã được bán ra không thể xóa "+UserView.RESET);
       }
       else{
           productDao.deleteProduct(id);
           System.out.println("=> Đã xóa sản phẩm thành công!");
       }

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
