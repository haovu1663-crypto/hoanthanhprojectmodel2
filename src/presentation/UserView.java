package presentation;

import java.util.Scanner;

public class UserView {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";


    public static void viewStart(){
        System.out.println("======== HỆ THỐNG QUẢN LÝ CỬA HÀNG ========");
        System.out.println("1. Đăng nhập Admin");
        System.out.println("2. Đăng ký tài khoản");
        System.out.println("3. Thoát");
        System.out.println("===========================================");
        System.out.print("Nhập lựa chọn: ");

    }
    public static void mainMenu(){
        System.out.println("======== MENU CHÍNH ========");
        System.out.println("1. Quản lý sản phẩm điện thoại");
        System.out.println("2. Quản lý khách hàng");
        System.out.println("3. Quản lý hóa đơn");
        System.out.println("4. Thống kê doanh thu");
        System.out.println("5. Đăng xuất");
        System.out.println("============================");
        System.out.print("Nhập lựa chọn: ");


    }
    public static int checkNumeric(Scanner sc) {
        while (true) {
            String input = sc.nextLine();
            if(input.isEmpty()){
                System.out.println(RED + "vui lòng nhập thông tin :" + RESET);
               continue;
            }
            try {
                return Integer.parseInt(input); // nếu đúng thì trả về số
            } catch (NumberFormatException e) {
                System.out.println(RED + "Bạn phải nhập số, vui lòng nhập lại:" + RESET);

            }
        }
    }
    public static void mainQLDT(){
        System.out.println("\n========= QUẢN LÝ SẢN PHẨM =========");
        System.out.println("1. Hiển thị danh sách sản phẩm");
        System.out.println("2. Thêm sản phẩm mới");
        System.out.println("3. Cập nhật thông tin sản phẩm");
        System.out.println("4. Xóa sản phẩm theo ID");
        System.out.println("5. Tìm kiếm theo Brand");
        System.out.println("6. Tìm kiếm theo khoảng giá");
        System.out.println("7. Tìm kiếm theo tồn kho");
        System.out.println("8. Quay lại menu chính");
        System.out.println("====================================");
        System.out.print("Vui lòng chọn chức năng : ");
    }

    public static int checkPrice(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim(); // Thêm .trim() để xóa khoảng trắng thừa

            if (input.isEmpty()) {
                System.out.println(RED + "Vui lòng nhập thông tin:" + RESET);
                continue;
            }

            try {
                int n = Integer.parseInt(input);

                // Kiểm tra điều kiện số lớn hơn 0
                if (n > 0) {
                    return n;
                } else {
                    System.out.println(RED + "Giá trị phải lớn hơn 0, vui lòng nhập lại:" + RESET);
                }

            } catch (NumberFormatException e) {
                System.out.println(RED + "Bạn phải nhập số nguyên hợp lệ, vui lòng nhập lại:" + RESET);
            }
        }
    }
    public static void CostomerMenu(){
        System.out.println("======== QUẢN LÝ KHÁCH HÀNG ========");
        System.out.println("1. Hiển thị danh sách khách hàng");
        System.out.println("2. Thêm khách hàng mới");
        System.out.println("3. Cập nhật thông tin khách hàng");
        System.out.println("4. Xóa khách hàng theo ID");
        System.out.println("5. Quay lại menu chính");
        System.out.println("===================================");
        System.out.print("Mời bạn chọn: ");

    }
    public static  void HoadonMen(){
        System.out.println("======== QUẢN LÝ HÓA ĐƠN ========");
        System.out.println("1. Hiển thị danh sách hóa đơn");
        System.out.println("2. Thêm mới hóa đơn");
        System.out.println("3. Tìm kiếm hóa đơn");
        System.out.println("4. Quay lại menu chính");
        System.out.println("================================");
        System.out.print("Nhập lựa chọn: ");

    }
    public static void choiceProduct(){
        System.out.println("=====Chọn mặt hàng =====");
        System.out.println("1. Chọn điện thoại ");
        System.out.println("2. thoát");
    }
    public static  void findBynameordate (){
        System.out.println("=========Phương thức tìm kiếm ==========");
        System.out.println("1.tìm kiếm theo Customer Name : ");
        System.out.println("2.tìm kiếm theo ngày tháng tạo hóa đơn");
        System.out.print("lưa chọn ");
    }
    public static  void ThongkeMenu (){
        System.out.println("======== THỐNG KÊ DOANH THU ========");
        System.out.println("1. Doanh thu theo ngày");
        System.out.println("2. Doanh thu theo tháng");
        System.out.println("3. Doanh thu theo năm");
        System.out.println("4. Quay lại menu chính");
        System.out.println("==================================");
        System.out.print("Nhập lựa chọn của bạn: ");

    }
    public static String checkSDT(Scanner scanner) {
        String sdt;
        String regex = "^0[0-9]{9}$";

        while (true) {
            sdt = scanner.nextLine().trim();
            if (sdt.matches(regex)) {
                return  sdt;
            } else {
                System.out.println(RED+"Vui lòng nhập đúng định dạng "+RESET);
                System.out.println("Nhập SDT : ");
            }
        }
    }
    public static String CheckEmail(Scanner scanner) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        while (true) {
            String email = scanner.nextLine().trim();
            if (email.matches(emailRegex)) {

                return email;
            } else {
                System.out.println(RED+" Lỗi: Email không đúng định dạng. Vui lòng thử lại!"+RESET);
                System.out.println("Nhập Email : ");
            }
        }
    }
    public static String noEmptyString(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println(RED+"Chuỗi không được để trống, vui lòng nhập lại!"+RESET);
        }
    }


}
