package presentation;

import Dao.ICustomerDao;
import Dao.impl.CustomerDao;
import Model.Customer;
import Service.ICustomerService;
import Service.impl.CustomerService;
import java.util.Scanner;

public class CustomerView {
    private static ICustomerService iCustomerService = new CustomerService();
    private static ICustomerDao iCustomerDao = new CustomerDao();
    public static void addCustomer(Scanner scanner) {
        System.out.println("========== Thêm Khách Hàng ==========");

        System.out.print("Nhập tên khách hàng: ");
        String name = scanner.nextLine();

        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine();

        // Kiểm tra email unique
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        while (true) {
            Customer existing = iCustomerDao.findCustomerByEmail(email);
            if (existing != null) {
                System.out.println("Email này đã tồn tại, vui lòng nhập lại: ");
                email = scanner.nextLine();
            } else {
                break;
            }
        }

        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();

        Customer c = new Customer(name, phone, email, address);
        iCustomerService.addCustomer(c);
    }
    public static void showAllCustomers() {
        iCustomerService.showAllCustomers();
    }
    public static void updateCustomer(Scanner scanner) {
        System.out.println("========== Cập Nhật Khách Hàng ==========");
        System.out.print("Nhập ID khách hàng: ");
        int id = UserView.checkNumeric(scanner);

        while (true) {
            Customer customer = iCustomerDao.findCustomerById(id);
            if (customer == null) {
                System.out.println("ID không tồn tại, vui lòng nhập lại: ");
                id = UserView.checkNumeric(scanner);
            } else {
                break;
            }
        }

        Customer c = iCustomerDao.findCustomerById(id);
        System.out.println("\n===========================Khách Hàng Cần Cập Nhật===========================");
        System.out.printf("%-5s %-25s %-15s %-30s %-30s\n", "ID", "Name", "Phone", "Email", "Address");
        System.out.println("-".repeat(105));
        System.out.printf("%-5d %-25s %-15s %-30s %-30s\n", c.getId(), c.getName(), c.getPhone(), c.getEmail(), c.getAddress());
        System.out.println("-".repeat(105));

        System.out.print("Nhập tên mới: ");
        String name = scanner.nextLine();

        System.out.print("Nhập số điện thoại mới: ");
        String phone = scanner.nextLine();

        System.out.print("Nhập email mới: ");
        String email = scanner.nextLine();
        while (true) {
            Customer existing = iCustomerDao.findCustomerByEmail(email);
            if (existing != null && existing.getId() != id) {
                System.out.println("Email này đã tồn tại, vui lòng nhập lại: ");
                email = scanner.nextLine();
            } else {
                break;
            }
        }

        System.out.print("Nhập địa chỉ mới: ");
        String address = scanner.nextLine();

        Customer updated = new Customer(id, name, phone, email, address);
        iCustomerService.updateCustomer(updated);
    }
    public static void deleteCustomer(Scanner scanner) {
        System.out.println("========== Xóa Khách Hàng ==========");
        System.out.print("Nhập ID khách hàng: ");
        int id = UserView.checkNumeric(scanner);

        while (true) {
            Customer customer = iCustomerDao.findCustomerById(id);
            if (customer == null) {
                System.out.println("ID không tồn tại, vui lòng nhập lại: ");
                id = UserView.checkNumeric(scanner);
            } else {
                break;
            }
        }

        Customer c = iCustomerDao.findCustomerById(id);
        System.out.println("\n===========================Khách Hàng Cần Xóa===========================");
        System.out.printf("%-5s %-25s %-15s %-30s %-30s\n", "ID", "Name", "Phone", "Email", "Address");
        System.out.println("-".repeat(105));
        System.out.printf("%-5d %-25s %-15s %-30s %-30s\n", c.getId(), c.getName(), c.getPhone(), c.getEmail(), c.getAddress());
        System.out.println("-".repeat(105));

        System.out.println("Bạn có muốn xóa không? (1 = yes / 0 = no)");
        int confirm = UserView.checkNumeric(scanner);
        if (confirm == 1) {
            iCustomerService.deleteCustomer(id);
        }
    }

}
