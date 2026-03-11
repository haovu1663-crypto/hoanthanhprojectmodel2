package presentation;

import Dao.IProductDao;
import Dao.impl.ProductDao;
import Model.Customer;
import Model.InvoiceDetail;
import Model.Product;
import Service.IInvoiceDetailService;
import Service.impl.InvoiceDetailService;

import java.util.Scanner;

public class InvoiceDetailView {
    private static IProductDao productDao = new ProductDao();
  private static   InvioceView in = new InvioceView();
  private static IInvoiceDetailService iInvoiceDetailService = new InvoiceDetailService();
    public static void addInvoiceDetail(Scanner scanner) {
        int Customer_ID = in.addInvoice(scanner);
        while (true){
            UserView.choiceProduct();
            Boolean Check = false;
            int choice = UserView.checkNumeric(scanner);
            switch (choice){
                case 1:{
                    ProductDao.showProductIdAndName();
                    System.out.println("nhập id sản phẩm ");
                    int productId = UserView.checkNumeric(scanner);
                    Product pro;
                    while(true){
                         pro =  productDao.findProductById(productId)  ;
                        if(pro ==null){
                            System.out.println("customer_ID này không tồn tại vui lòng nhập lại.");
                            productId=UserView.checkNumeric(scanner);
                        }
                        else {
                            break;
                        }
                    }
                    System.out.println("price :"+pro.getPrice());
                    System.out.println("nhập số lương :");
                    int quantytail =  UserView.checkNumeric(scanner);
                    iInvoiceDetailService.addInvoiceDetail(new InvoiceDetail(Customer_ID,productId,quantytail,pro.getPrice()));

                }
                break;
                case 2:{
                    System.out.println("Bạn có muốn thoát không : (1 = yes,0 = no)");
                    int a =UserView.checkNumeric(scanner);
                    if(a==1){
                        Check = true;
                    }
                }break;
            }
            if(Check){break;}
        }
    }
}
