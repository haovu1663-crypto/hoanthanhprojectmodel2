package service.impl;

import dao.ICustomerDao;
import dao.impl.CustomerDao;
import dao.impl.IvoiceDao;
import model.Customer;
import model.Invoice;
import presentation.UserView;
import service.ICustomerService;

import java.util.List;

public class CustomerService implements ICustomerService {
    ICustomerDao customerDao = new CustomerDao();
    @Override
    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }

    @Override
    public void showAllCustomers() {
        customerDao.showAllCustomers();
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int id) {

       List<Invoice> a =IvoiceDao.findInvoicebyidProdusct(id);
       if(a!=null){
           System.out.println(UserView.RED+"Khác hàng này đã mua hàng khổng thể xóa"+UserView.RESET);
       }
       else {
           customerDao.deleteCustomer(id);
           System.out.println("=> Đã xóa khách hàng thành công!");
       }

    }
}
