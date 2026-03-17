package service.impl;

import dao.IInvoiceDetailDao;
import dao.impl.InvoiceDetailDao;
import model.InvoiceDetail;
import service.IInvoiceDetailService;

public class InvoiceDetailService implements IInvoiceDetailService {
    IInvoiceDetailDao invoiceDetailDao = new InvoiceDetailDao();
    @Override
    public void addInvoiceDetail(InvoiceDetail invoiceDetail) {
        invoiceDetailDao.addInvoiceDetail(invoiceDetail);
    }
}
