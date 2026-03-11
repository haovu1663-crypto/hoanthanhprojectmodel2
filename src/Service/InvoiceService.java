package Service;

import Model.Invoice;

import java.time.LocalDate;

public interface InvoiceService {
    int addInvoice(Invoice invoice);
    void showAllInvoices();
    void findbyname(String name);
    void findbydate(LocalDate date );
}
