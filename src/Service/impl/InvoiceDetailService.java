package Service.impl;

import Dao.IInvoiceDetailDao;
import Dao.impl.InvoiceDetailDao;
import Model.InvoiceDetail;
import Service.IInvoiceDetailService;

public class InvoiceDetailService implements IInvoiceDetailService {
    IInvoiceDetailDao invoiceDetailDao = new InvoiceDetailDao();
    @Override
    public void addInvoiceDetail(InvoiceDetail invoiceDetail) {
        invoiceDetailDao.addInvoiceDetail(invoiceDetail);
    }
}
