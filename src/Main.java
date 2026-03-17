import dao.impl.IvoiceDao;
import presentation.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            UserView.viewStart();
           int choice =UserView.checkNumeric(sc);
            switch (choice){
                case 1: {
                    Adminview.login(sc);
                    while(true){
                        UserView.mainMenu();
                        int choice2 = UserView.checkNumeric(sc);
                        boolean check= false;
                        switch (choice2){
                            case 1: {
                                //quan lý đt
                                while(true){
                                UserView.mainQLDT();
                                boolean check2 = false;
                                    int choice3 = UserView.checkNumeric(sc);
                                    switch(choice3){
                                        case 1: {
                                            // hien thị danh sách sp
                                            ProductView.showAllProducts();
                                        } break;
                                        case 2: {
                                            //thêm sp
                                            ProductView.addProduct(sc);
                                        } break;
                                        case 3: {
                                            //cập nhật thông tin sp
                                            ProductView.updateProduct(sc);
                                        }break;
                                        case 4: {
                                            ProductView.delete(sc);
                                            // xóa sp theo ID
                                        }break;
                                        case 5: {
                                            //TK THEO brand
                                            ProductView.findProductByBrand(sc);
                                        }break;
                                        case 6: {
                                            //tim kim theo giá
                                            ProductView.findProductByPrice(sc);
                                        }break;
                                        case 7: {
                                            // tim kim theo tồn kho
                                            ProductView.findProductByStoc(sc);
                                        }break;
                                        case 8: {
                                            //thoát ra khỏi menu
                                            System.out.println("bạn có muốn thoát khỏi Menu này ko (1 = yes,0 = no)");
                                            int a =UserView.checkNumeric(sc);
                                            if(a==1){
                                                check2 = true;
                                            }

                                        }break;
                                        default:
                                            System.out.println(UserView.RED+"ko có lựa chọn này,vui lòng chọn lại"+UserView.RESET);
                                    }
                                    if(check2){break;}
                                }
                            }
                            break;
                            case 2: {
                                while (true){
                                    UserView.CostomerMenu();
                                    boolean check2 = false;
                                    int choice3 = UserView.checkNumeric(sc);
                                    switch(choice3){
                                        case 1: {
                                            // hiên thi danh sach khách hàng
                                            CustomerView.showAllCustomers();
                                        }break;
                                        case 2: {
                                            // thêm khách hàng mới
                                            CustomerView.addCustomer(sc);
                                        }break;
                                        case 3: {
                                            // cập nhật thông tin khách hàng
                                            CustomerView.updateCustomer(sc);
                                        }break;
                                        case 4: {
                                            //xóa khách hàng theo ID
                                            CustomerView.deleteCustomer(sc);
                                        }break;
                                        case 5: {
                                            System.out.println("Bạn có muốn thoát không : (1 = yes,0 = no)");
                                            int a =UserView.checkNumeric(sc);
                                            if(a==1){
                                                check2 = true;
                                            }
                                        }break;
                                    }
                                    if(check2){break;}
                                }


                            }
                            break;
                            case 3: {
                                // quản lý hoa đơn
                                while(true){
                                    UserView.HoadonMen();
                                    int choice3 = UserView.checkNumeric(sc);
                                    Boolean check3 = false;
                                    switch (choice3){
                                        case 1: {
                                            // hiển thị danh sách hóa đơn
                                             InvioceView.showAllInvoices();
                                        }break;
                                        case 2: {
                                            //thêm mới hóa đơn
                                            InvoiceDetailView.addInvoiceDetail(sc);
                                        }break;
                                        case 3: {
                                            // tìm kiếm hóa đơn
                                            UserView.findBynameordate();
                                            int choice4 = UserView.checkNumeric(sc);
                                            switch(choice4){
                                                case 1: {
                                                    // tìm kiếm theo tên
                                                    InvioceView.findbyname(sc);
                                                }break;
                                                case 2: {
                                                    // tìm kiếm theo ngàytaojo hóa đơn
                                                    InvioceView.findbydate(sc);
                                                }break;
                                                default:
                                                    System.out.println("ko có lựa chọn này ");
                                            }

                                        }break;
                                        case 4: {
                                            System.out.println("Bạn có muốn thoát không : (1 = yes,0 = no)");
                                            int a =UserView.checkNumeric(sc);
                                            if(a==1){
                                                check3 = true;
                                            }
                                        }break;
                                    }
                                    if(check3){break;}
                                }


                            }
                            break;
                            case 4: {
                                // thống kê
                                while(true){
                                    UserView.ThongkeMenu();
                                    int choice4 = UserView.checkNumeric(sc);
                                    boolean check4 = false;
                                    switch(choice4){
                                        case 1: {
                                            // thống k theo ngày
                                            IvoiceDao.thongkedate();
                                        }break;
                                        case 2: {
                                            // thông kê theo tháng
                                            IvoiceDao.thongkeMonth();
                                        }break;
                                        case 3: {
                                            // thống kê theo năm
                                            IvoiceDao.thongkeYear();
                                        }break;
                                        case 4: {
                                            System.out.println("Bạn có muốn thoát không : (1 = yes,0 = no)");
                                            int a =UserView.checkNumeric(sc);
                                            if(a==1){
                                                check4 = true;
                                            }
                                            // thoát
                                        }break;
                                    }
                                    if(check4){break;}
                                }
                            }
                            break;
                            case 5: {
                                // đăng xuất
                                System.out.println("bạn muốn đăng suất chứ (1 = yes,0 = no )");
                                int t = UserView.checkNumeric(sc);
                                if(t==1){
                                check=true;
                                }
                            }break;
                        }
                        if(check){
                            break;
                        }
                    }


                }
                break;
                case 2 :{
                    // đăng ký tài khoản
                    Adminview.addAdmin(sc);
                }break;




                case 3 :
                   return;

                   default:
                       System.out.println("ko có lưạ chọn này ???");
            }
        }
    }
}
