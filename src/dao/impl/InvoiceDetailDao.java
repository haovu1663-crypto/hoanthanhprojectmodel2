package dao.impl;

import dao.IInvoiceDetailDao;
import model.InvoiceDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static utils.DBUtil.getConnection;

public class InvoiceDetailDao implements IInvoiceDetailDao {
    @Override
    public void addInvoiceDetail(InvoiceDetail invoiceDetail) {
        String sql = "INSERT INTO quanlysanpham.invoice_details (invoice_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, invoiceDetail.getInvoiceId());
            pstm.setInt(2, invoiceDetail.getProductId());
            pstm.setInt(3, invoiceDetail.getQuantity());
            pstm.setDouble(4, invoiceDetail.getUnitPrice());

            pstm.executeUpdate();
            System.out.println("Thêm chi tiết hóa đơn thành công!");

        } catch (SQLException e) {
            System.err.println("Lỗi thêm invoice_detail: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
