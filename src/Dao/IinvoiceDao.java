package Dao;

import Model.Invoice;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IinvoiceDao {
    int addInvoice(Invoice invoice);
    void showAllInvoices();
    void findbynam(String name );
    void findbydate(LocalDate date );


}
