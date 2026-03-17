package presentation;

import dao.ICustomerDao;
import dao.impl.CustomerDao;
import model.Customer;
import model.Invoice;
import service.InvoiceService;
import service.impl.IvoiceService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InvioceView {
  private static   InvoiceService invoiceService = new IvoiceService();
  private static ICustomerDao iCustomerDao = new CustomerDao();
    public static int addInvoice(Scanner scanner) {
        System.out.println("==========Tạo hóa Đơn ==========");
        System.out.println("nhập Customer_id");
        int customerId = UserView.checkNumeric(scanner);
        while(true){
            Customer customer = iCustomerDao.findCustomerById(customerId) ;
            if(customer==null){
                System.out.println("customer_ID này không tồn tại vui lòng nhập lại.");
                customerId=UserView.checkNumeric(scanner);
            }
            else {
                break;
            }
        }

        return invoiceService.addInvoice(new Invoice(customerId));
    }
    public static void showAllInvoices() {
        invoiceService.showAllInvoices();
    }
    public static void findbyname(Scanner scanner) {
        System.out.println("==========Tìm kiếm hóa đơn theo Customer name =========");
        System.out.println("nhập name : ");
        String name = scanner.nextLine();
        invoiceService.findbyname(name);
    }
    public static void findbydate(Scanner scanner) {
        System.out.println("==========Tìm kiếm hóa đơn theo Create_at =========");
        System.out.println("nhập create_at : (yyyy/MM/dd) ");
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(input, formatter);
        invoiceService.findbydate(date);
    }
}
