package Service;

import Model.Customer;

public interface ICustomerService {
    void addCustomer(Customer customer);
    void showAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
}
