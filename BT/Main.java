import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Nhập mã sách: ");
        String maSach = sc.nextLine();

        System.out.print("Nhập tên sách: ");
        String tenSach = sc.nextLine();

        System.out.print("Nhập tác giả: ");
        String tacGia = sc.nextLine();

        System.out.print("Nhập năm xuất bản: ");
        int namXuatBan = sc.nextInt();

        System.out.print("Nhập đơn giá: ");
        double donGia = sc.nextDouble();

        System.out.print("Nhập số lượng: ");
        int soLuong = sc.nextInt();

        System.out.println("Mã sách      : " + maSach);
        System.out.println("Tên sách     : " + tenSach);
        System.out.println("Tác giả      : " + tacGia);
        System.out.println("Năm xuất bản : " + namXuatBan);
        System.out.printf("Đơn giá      : %,.0f VND%n", donGia);
        System.out.println("Số lượng     : " + soLuong);

        double thanhTien = donGia * soLuong;


        double phiVanChuyen = thanhTien * 0.05;


        double tongTienNhap = thanhTien + phiVanChuyen;

        System.out.printf("Thành tiền       : %,.0f VND%n", thanhTien);
        System.out.printf("Phí vận chuyển   : %,.0f VND%n", phiVanChuyen);
        System.out.printf("Tổng tiền nhập   : %,.0f VND%n", tongTienNhap);

        sc.close();
    }
}