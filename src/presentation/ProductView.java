package presentation;

import Dao.IProductDao;
import Dao.impl.ProductDao;
import Model.Product;
import Service.IProductService;
import Service.impl.ProductService;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
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
        int stock =UserView.checkPrice(scanner);
        Product p = new Product(name,brand,price,stock);
        iProductService.addProduct(p);

    }
    public static void showAllProducts() {
        iProductService.showAllProducts();
    }
    public static void updateProduct(Scanner scanner) {
        System.out.println("\n========== UPDATE PRODUCT ==========");
        System.out.print("Nhập ID product cần sửa: ");
        int id = Integer.parseInt(scanner.nextLine());

        Product p;
        while (true) {
            p = iProductDao.findProductById(id);
            if (p == null) {
                System.out.print("ID không tồn tại! Vui lòng nhập lại: ");
                id = Integer.parseInt(scanner.nextLine());
            } else {
                break;
            }
        }
        System.out.println("\n" + "=".repeat(86));
        System.out.printf("| %-5s | %-25s | %-15s | %-15s | %-10s |\n",
                "ID", "Name", "Brand", "Price", "Stock");
        System.out.println("-".repeat(86));
        System.out.printf("| %-5d | %-25s | %-15s | %-15.2f | %-10d |\n",
                p.getId(), p.getName(), p.getBrand(), p.getPrice(), p.getStock());
        System.out.println("=".repeat(86));
        System.out.print("\nNhập Name mới: ");
        String name = scanner.nextLine();
        while (true) {
            Product existingProduct = iProductDao.findProductByname(name);
            if (existingProduct != null && existingProduct.getId() != id) {
                System.out.print("Tên sản phẩm này đã tồn tại! Nhập lại: ");
                name = scanner.nextLine();
            } else {
                break;
            }
        }

        System.out.print("Nhập Brand mới: ");
        String brand = scanner.nextLine();

        System.out.print("Nhập Price mới: ");
        double price = UserView.checkPrice(scanner);

        System.out.print("Nhập Stock mới: ");
        int stock = UserView.checkNumeric(scanner);

        // Cập nhật
        Product pro = new Product(id, name, brand, price, stock);
        iProductService.updateProduct(pro);
        System.out.println("\n=> Cập nhật sản phẩm thành công!");
    }
    public static void findProductByBrand(Scanner scanner) {
        System.out.println("\n========== TÌM KIẾM THEO BRAND ==========");
        System.out.print("Nhập Brand cần tìm: ");
        String brand = scanner.nextLine();

        List<Product> products = iProductService.showProductbyBrand(brand);

        if (!products.isEmpty()) {
            System.out.println("\n" + "=".repeat(86));
            System.out.printf("| %-5s | %-25s | %-15s | %-15s | %-10s |\n",
                    "ID", "Name", "Brand", "Price", "Stock");
            System.out.println("-".repeat(86));
            for (Product p : products) {
                System.out.printf("| %-5d | %-25s | %-15s | %-15.2f | %-10d |\n",
                        p.getId(), p.getName(), p.getBrand(), p.getPrice(), p.getStock());
            }

            System.out.println("=".repeat(86));
        } else {
            System.out.println("\n[!] Không có sản phẩm nào thuộc thương hiệu: " + brand);
        }
    }
    public static void delete(Scanner scanner) {
        System.out.println("\n========== DELETE PRODUCT ==========");
        System.out.print("Nhập ID product cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());

        Product p;
        while (true) {
            p = iProductDao.findProductById(id);
            if (p == null) {
                System.out.print("ID không tồn tại! Vui lòng nhập lại: ");
                id = Integer.parseInt(scanner.nextLine());
            } else {
                break;
            }
        }
        System.out.println("\n" + "=".repeat(86));
        System.out.printf("| %-5s | %-25s | %-15s | %-15s | %-10s |\n",
                "ID", "Name", "Brand", "Price", "Stock");
        System.out.println("-".repeat(86));
        System.out.printf("| %-5d | %-25s | %-15s | %-15.2f | %-10d |\n",
                p.getId(), p.getName(), p.getBrand(), p.getPrice(), p.getStock());
        System.out.println("=".repeat(86));
        System.out.print("Bạn có chắc chắn muốn xóa không? (1 = Yes / 0 = No): ");
        try {
            int confirm = Integer.parseInt(scanner.nextLine());
            if (confirm == 1) {
                iProductService.deleteProduct(id);
                System.out.println("=> Đã xóa sản phẩm thành công!");
            } else {
                System.out.println("=> Đã hủy thao tác xóa.");
            }
        } catch (NumberFormatException e) {
            System.out.println("=> Lựa chọn không hợp lệ, hủy thao tác.");
        }
    }
    public static void findProductByPrice(Scanner scanner) {
        System.out.println("\n========== TÌM KIẾM THEO GIÁ ==========");
        System.out.print("Nhập mức giá cần tìm: ");
        double price = Double.parseDouble(scanner.nextLine());
        List<Product> products = iProductService.showProductbyPrice(price);
        if (!products.isEmpty()) {
            System.out.println("\n" + "=".repeat(86));
            System.out.printf("| %-5s | %-25s | %-15s | %-15s | %-10s |\n",
                    "ID", "Name", "Brand", "Price", "Stock");
            System.out.println("-".repeat(86));
            for (Product p : products) {
                System.out.printf("| %-5d | %-25s | %-15s | %-15.2f | %-10d |\n",
                        p.getId(), p.getName(), p.getBrand(), p.getPrice(), p.getStock());
            }

            System.out.println("=".repeat(86));
        } else {
            System.out.println("\n[!] Không có sản phẩm nào có mức giá: " + price);
        }
    }
    public static void findProductByStoc(Scanner scanner) {
        System.out.println("\n========== TÌM KIẾM THEO TỒN KHO ==========");
        System.out.print("Nhập số lượng tồn kho cần tìm: ");
        int stock = UserView.checkPrice(scanner);
        List<Product> products = iProductService.showProductbystock(stock);
        if (!products.isEmpty()) {
            System.out.println("\n" + "=".repeat(86));
            System.out.printf("| %-5s | %-25s | %-15s | %-15s | %-10s |\n",
                    "ID", "Name", "Brand", "Price", "Stock");
            System.out.println("-".repeat(86));
            for (Product p : products) {
                System.out.printf("| %-5d | %-25s | %-15s | %-15.2f | %-10d |\n",
                        p.getId(), p.getName(), p.getBrand(), p.getPrice(), p.getStock());
            }
            System.out.println("=".repeat(86));
        } else {
            System.out.println("\n[!] Không có sản phẩm nào có số lượng tồn kho là: " + stock);
        }
    }
}
