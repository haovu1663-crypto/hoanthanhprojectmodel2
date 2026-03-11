package Service.impl;

import Dao.ICustomerDao;
import Dao.impl.CustomerDao;
import Model.Customer;
import Service.ICustomerService;

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
        customerDao.deleteCustomer(id);
    }
}
