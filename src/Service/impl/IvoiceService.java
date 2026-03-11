package Service.impl;

import Dao.IinvoiceDao;
import Dao.impl.IvoiceDao;
import Model.Invoice;
import Service.InvoiceService;

import java.time.LocalDate;

public class IvoiceService implements InvoiceService {
    IinvoiceDao invoiceDao = new IvoiceDao();
    @Override
    public int addInvoice(Invoice invoice) {
        return invoiceDao.addInvoice(invoice);
    }

    @Override
    public void showAllInvoices() {
        invoiceDao.showAllInvoices();
    }

    @Override
    public void findbyname(String name) {
        invoiceDao.findbynam(name);
    }

    @Override
    public void findbydate(LocalDate date) {
      invoiceDao.findbydate(date);
    }
}
