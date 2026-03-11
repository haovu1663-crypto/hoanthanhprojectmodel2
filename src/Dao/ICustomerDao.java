package Dao;

import Model.Customer;

public interface ICustomerDao {
    void addCustomer(Customer customer);
    Customer findCustomerByEmail(String email);
    void showAllCustomers();
    void updateCustomer(Customer customer);
    Customer findCustomerById(int id);
    void deleteCustomer(int id);
}
