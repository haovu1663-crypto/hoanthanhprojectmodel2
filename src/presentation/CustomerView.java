package presentation;

import dao.ICustomerDao;
import dao.impl.CustomerDao;
import model.Customer;
import service.ICustomerService;
import service.impl.CustomerService;
import java.util.Scanner;

public class CustomerView {
    private static ICustomerService iCustomerService = new CustomerService();
    private static ICustomerDao iCustomerDao = new CustomerDao();
    public static void addCustomer(Scanner scanner) {
        System.out.println("========== Thêm Khách Hàng ==========");
        System.out.print("Nhập tên khách hàng: ");
        String name = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phone = UserView.checkSDT(scanner);
        // Kiểm tra email
        System.out.print("Nhập email: ");
        String email = UserView.CheckEmail(scanner);
        while (true) {
            Customer existing = iCustomerDao.findCustomerByEmail(email);
            if (existing != null) {
                System.out.println("Email này đã tồn tại, vui lòng nhập lại: ");
                email = UserView.CheckEmail(scanner);
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
        System.out.println("\n========== CẬP NHẬT KHÁCH HÀNG ==========");
        System.out.print("Nhập ID khách hàng: ");
        int id = UserView.checkNumeric(scanner);

        Customer c;
        while (true) {
            c = iCustomerDao.findCustomerById(id);
            if (c == null) {
                System.out.print("ID không tồn tại, vui lòng nhập lại: ");
                id = UserView.checkNumeric(scanner);
            } else {
                break;
            }
        }
        System.out.println("\n" + "=".repeat(106));
        System.out.printf("| %-5s | %-20s | %-12s | %-26s | %-25s |\n", "ID", "Name", "Phone", "Email", "Address");
        System.out.println("-".repeat(106));
        System.out.printf("| %-5d | %-20s | %-12s | %-26s | %-25s |\n", c.getId(), c.getName(), c.getPhone(), c.getEmail(), c.getAddress());
        System.out.println("=".repeat(106));
        System.out.print("\nNhập tên mới: ");
        String name = UserView.noEmptyString(scanner);
        System.out.print("Nhập số điện thoại mới: ");
        String phone =UserView.checkSDT(scanner);
        System.out.print("Nhập email mới: ");
        String email =UserView.CheckEmail(scanner);
        while (true) {
            Customer existing = iCustomerDao.findCustomerByEmail(email);
            if (existing != null && existing.getId() != id) {
                System.out.print("Email này đã tồn tại, vui lòng nhập lại: ");
                email =UserView.CheckEmail(scanner);
            } else {
                break;
            }
        }
        System.out.print("Nhập địa chỉ mới: ");
        String address = UserView.noEmptyString(scanner);
        Customer updated = new Customer(id, name, phone, email, address);
        iCustomerService.updateCustomer(updated);
        System.out.println("=> Cập nhật khách hàng thành công!");
    }
    public static void deleteCustomer(Scanner scanner) {
        System.out.println("\n========== XÓA KHÁCH HÀNG ==========");
        System.out.print("Nhập ID khách hàng: ");
        int id = UserView.checkNumeric(scanner);
        Customer c;
        while (true) {
            c = iCustomerDao.findCustomerById(id);
            if (c == null) {
                System.out.print("ID không tồn tại, vui lòng nhập lại: ");
                id = UserView.checkNumeric(scanner);
            } else {
                break;
            }
        }
        System.out.println("\n" + "=".repeat(106));
        System.out.printf("| %-5s | %-20s | %-12s | %-26s | %-25s |\n", "ID", "Name", "Phone", "Email", "Address");
        System.out.println("-".repeat(106));
        System.out.printf("| %-5d | %-20s | %-12s | %-26s | %-25s |\n", c.getId(), c.getName(), c.getPhone(), c.getEmail(), c.getAddress());
        System.out.println("=".repeat(106));
        System.out.print("Bạn có muốn xóa không? (1 = yes / 0 = no): ");
        int confirm = UserView.checkNumeric(scanner);
        if (confirm == 1) {

            iCustomerService.deleteCustomer(id);
            System.out.println("=> Đã xóa khách hàng thành công!");
        } else {
            System.out.println("=> Đã hủy thao tác xóa.");
        }
    }

}
