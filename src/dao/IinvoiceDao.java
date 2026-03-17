package dao;

import model.Invoice;

import java.time.LocalDate;

public interface IinvoiceDao {
    int addInvoice(Invoice invoice);
    void showAllInvoices();
    void findbynam(String name );
    void findbydate(LocalDate date );


}
