import java.util.Scanner;
import java.time.Year;

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

        int namHienTai = Year.now().getValue();
        boolean hopLe = true;

        if (donGia <= 0) {
            System.out.println("Lỗi: đơn giá phải lớn hơn 0");
            hopLe = false;
        }
        if (soLuong < 0) {
            System.out.println("Lỗi: số lượng phải lớn hơn 0");
            hopLe = false;
        }
        if (namXuatBan > namHienTai) {
            System.out.println("Lỗi: Năm xuất bản phải bé hơn " + namHienTai);
            hopLe = false;
        }
        if (!hopLe) {
            sc.close();
            return ;
        }
        System.out.println("Dữ liệu hợp lệ");


        String loaiGia;
        if (donGia < 50000) {
            loaiGia = "Sách giá thấp";
        } else if (donGia <= 100000) {
            loaiGia = "Sách giá trung bình";
        } else if (donGia <= 200000) {
            loaiGia = "Sách giá cao";
        } else {
            loaiGia = "Sách giá đặc biệt";
        }


        String tonKho;
        if (soLuong == 0) {
            tonKho = "Hết sách";
        } else if (soLuong <= 5) {
            tonKho = "Sắp hết";
        } else if (soLuong <= 20) {
            tonKho = "Còn ít";
        } else {
            tonKho = "Còn nhiều";
        }


        System.out.println("1. Sách giáo khoa");
        System.out.println("2. Sách tham khảo");
        System.out.println("3. Sách chuyên ngành");
        System.out.println("4. Sách khác");
        System.out.print("Nhập lựa chọn (1-4): ");
        int luaChon = sc.nextInt();

        String loaiSach;
        switch (luaChon) {
            case 1:
                loaiSach = "Sách giáo khoa";
                break;
            case 2:
                loaiSach = "Sách tham khảo";
                break;
            case 3:
                loaiSach = "Sách chuyên ngành";
                break;
            case 4:
                loaiSach = "Sách khác";
                break;
            default:
                loaiSach = "Không xác định (lựa chọn không hợp lệ)";
        }



        System.out.println("Mã sách      : " + maSach);
        System.out.println("Tên sách     : " + tenSach);
        System.out.println("Tác giả      : " + tacGia);
        System.out.println("Năm xuất bản : " + namXuatBan);
        System.out.printf("Đơn giá      : %,.0f VND%n", donGia);
        System.out.println("Số lượng     : " + soLuong);
        System.out.println("Phân loại giá: " + loaiGia);
        System.out.println("Tồn kho      : " + tonKho);
        System.out.println("Loại sách    : " + loaiSach);

        sc.close();

    }
}