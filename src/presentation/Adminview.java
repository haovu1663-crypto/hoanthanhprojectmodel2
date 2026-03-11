package presentation;

import Model.Admin;
import Service.IadminService;
import Service.impl.AdminService;

import java.util.Scanner;

public class Adminview {
    public static Admin userlogin=null;
    public static IadminService iadminService=new AdminService();
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
}
