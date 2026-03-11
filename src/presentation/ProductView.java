package presentation;

import Dao.IProductDao;
import Dao.impl.ProductDao;
import Model.Product;
import Service.IProductService;
import Service.impl.ProductService;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductView {
   private static IProductService iProductService=new ProductService();
   private static IProductDao iProductDao=new ProductDao();
    public static void addProduct(Scanner scanner) {
        System.out.println("========== Thêm Product==========");
        System.out.print("Nhâp name Product : ");
        String name  = scanner.nextLine(); ;
        while(true){

            Product product = iProductDao.findProductByname(name);
            if(product!=null){
                System.out.println("Name product này đã tồn tại vui lòng nhập lại.");
                name=scanner.nextLine();
            }
            else {
              break;
            }
        }
        System.out.print("Nhập Brand : ");
        String brand = scanner.nextLine();
        System.out.print("Nhập Price : ");
        double price = UserView.checkPrice(scanner);
        System.out.print("Nhập Stock : ");
        int stock =UserView.checkNumeric(scanner);
        Product p = new Product(name,brand,price,stock);
        iProductService.addProduct(p);

    }
    public static void showAllProducts() {
        iProductService.showAllProducts();
    }
    public static void updateProduct( Scanner scanner) {
        System.out.println("========== Update Product==========");
        System.out.println("nhập ID product : ");
        int id = Integer.parseInt(scanner.nextLine());
        while(true){

            Product product = iProductDao.findProductById(id);
            if(product==null){
                System.out.println("Id product ko tồn tại vui lòng nhập lại.");
                 id = Integer.parseInt(scanner.nextLine());
            }
            else {
                break;
            }
        }
        // hiên thi theo id
        Product p = iProductDao.findProductById(id);
        System.out.println("\n===========================Sản phẩm Cần Update===========================");
        System.out.printf("%-5s %-25s %-20s %-20s %-20s \n", "ID", "Name", "Brand","Price","Stock");
        System.out.println("-".repeat(80));
        System.out.printf("%-5d %-25s %-20s %-20s %-20s \n", p.getId(),p.getName(),p.getBrand(),p.getStock(),p.getPrice());
        System.out.println("-".repeat(80));

        System.out.print("Nhâp name Product : ");
        String name  = scanner.nextLine(); ;
        while(true){

            Product product = iProductDao.findProductByname(name);
            if(product!=null){
                System.out.println("Name product này đã tồn tại vui lòng nhập lại.");
                name=scanner.nextLine();
            }
            else {
                break;
            }
        }
        System.out.print("Nhập Brand : ");
        String brand = scanner.nextLine();
        System.out.print("Nhập Price : ");
        double price = UserView.checkPrice(scanner);
        System.out.print("Nhập Stock : ");
        int stock =UserView.checkNumeric(scanner);
        Product pro = new Product(id,name,brand,price,stock);
        iProductService.updateProduct(pro);
    }
    public static void findProductByBrand(Scanner scanner) {
        System.out.println("========== TÌm Kiếm Product==========");
        System.out.print("Nhập Brand cần tìm :");
        String brand = scanner.nextLine();
        ArrayList<Product> products = iProductService.showProductbyBrand(brand);
        if(!products.isEmpty()) {
            System.out.println("\n==============================Danh sách==============================");
            System.out.printf("%-5s %-25s %-20s %-20s %-20s \n", "ID", "Name", "Brand","Price","Stock");
            System.out.println("-".repeat(80));
            for(Product p:products){
                System.out.printf("%-5d %-25s %-20s %-20s %-20s \n", p.getId(),p.getName(),p.getBrand(),p.getPrice(),p.getStock());
            }
            System.out.println("-".repeat(80));
        }
        else{
            System.out.println("Không có sản phẩm nào trùng khớp .");
        }
    }
    public static void delete(Scanner scanner) {
        System.out.println("========== Delete Product==========");
        System.out.println("nhập ID product : ");
        int id = Integer.parseInt(scanner.nextLine());
        while(true){

            Product product = iProductDao.findProductById(id);
            if(product==null){
                System.out.println("Id product ko tồn tại vui lòng nhập lại.");
                id = Integer.parseInt(scanner.nextLine());
            }
            else {
                break;
            }
        }
        // hiên thi theo id
        Product p = iProductDao.findProductById(id);
        System.out.println("\n===========================Sản phẩm Cần Delete===========================");
        System.out.printf("%-5s %-25s %-20s %-20s %-20s \n", "ID", "Name", "Brand","Price","Stock");
        System.out.println("-".repeat(80));
        System.out.printf("%-5d %-25s %-20s %-20s %-20s \n", p.getId(),p.getName(),p.getBrand(),p.getStock(),p.getPrice());
        System.out.println("-".repeat(80));
        System.out.println("Bạn có muốn xóa ko (1 = yes / 0 = no)");
        int a = Integer.parseInt(scanner.nextLine());
        if(a==1)
        {
            iProductService.deleteProduct(id);
        }
    }
    public static void findProductByPrice(Scanner scanner) {
        System.out.println("========== TÌm Kiếm Product==========");
        System.out.print("Nhập price cần tìm :");
        double price = Double.parseDouble(scanner.nextLine());
        ArrayList<Product> products = iProductService.showProductbyPrice(price);
        if(!products.isEmpty()) {
            System.out.println("\n==============================Danh sách==============================");
            System.out.printf("%-5s %-25s %-20s %-20s %-20s \n", "ID", "Name", "Brand","Price","Stock");
            System.out.println("-".repeat(80));
            for(Product p:products){
                System.out.printf("%-5d %-25s %-20s %-20s %-20s \n", p.getId(),p.getName(),p.getBrand(),p.getPrice(),p.getStock());
            }
            System.out.println("-".repeat(80));
        }
        else{
            System.out.println("Không có sản phẩm nào trùng khớp .");
        }
    }
    public static void findProductByStoc(Scanner scanner) {
        System.out.println("========== TÌm Kiếm Product==========");
        System.out.print("Nhập price cần tìm :");
        int Stock = Integer.parseInt(scanner.nextLine());
        ArrayList<Product> products = iProductService.showProductbystock(Stock);
        if(!products.isEmpty()) {
            System.out.println("\n==============================Danh sách==============================");
            System.out.printf("%-5s %-25s %-20s %-20s %-20s \n", "ID", "Name", "Brand","Price","Stock");
            System.out.println("-".repeat(80));
            for(Product p:products){
                System.out.printf("%-5d %-25s %-20s %-20s %-20s \n", p.getId(),p.getName(),p.getBrand(),p.getPrice(),p.getStock());
            }
            System.out.println("-".repeat(80));
        }
        else{
            System.out.println("Không có sản phẩm nào trùng khớp .");
        }
    }
}
