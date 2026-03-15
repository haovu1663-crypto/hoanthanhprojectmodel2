package presentation;

import Dao.IAdminDao;
import Dao.impl.AdminDao;
import Model.Admin;
import Service.IadminService;
import Service.impl.AdminService;

import java.util.Scanner;

public class Adminview {
    public static Admin userlogin=null;
    public static IadminService iadminService=new AdminService();
    public static IAdminDao  iAdminDao=new AdminDao();
    public static void login (Scanner scanner){
        System.out.println("==========Đăng nhập=========");
        System.out.print("Tài Khoản : ");
        String username = scanner.nextLine();
        System.out.print("Mật Khẩu : ");
        String password = scanner.nextLine();
        Admin a =iadminService.loginAdmin(username,password);
        if(a!=null){
            System.out.println("Đăng nhâp thành công ");
            userlogin=a;

        }
        else{
            System.out.println("đăng nhập ko thành công , xin vui lòng nhập lại");
            login(scanner);
        }
    }
    public static void addAdmin(Scanner scanner){
        System.out.println("===============Đăng ký tài khoản===============");
        System.out.print("Nhập họ và tên : ");
        String name =  scanner.nextLine();
        System.out.print("Nhập SDT : ");
        String SDT = UserView.checkSDT(scanner);
        System.out.print("Nhập Username : ");
        String username = scanner.nextLine();
        while (true){
            Admin a = iAdminDao.findAdminByUsername(username);
            if(a!=null){
                System.out.println(UserView.RED+"Username này đã tồn tại . Vui lòng nhập lai "+UserView.RESET);
                username = scanner.nextLine();
            }
            else{
               break;
            }
        }

        System.out.println("nhập Mật khẩu : ");
        String password = scanner.nextLine();
        iadminService.addAdmin(new Admin(username,password));

    }
}
