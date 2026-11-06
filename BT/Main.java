import java.time.Year;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static String[] maSach, tenSach, tacGia;
    static int[] namXuatBan, soLuong;
    static double[] donGia;
    static int n = 0; // số sách hiện có

    //Hàm đọc số an toàn
    static int docInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: vui lòng nhập số nguyên!");
            }
        }
    }

    static double docDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: vui lòng nhập số!");
            }
        }
    }

    static String docString(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    //Phân loại
    static String phanLoaiGia(double gia) {
        if (gia < 50000) return "Sách giá thấp";
        else if (gia <= 100000) return "Sách giá trung bình";
        else if (gia <= 200000) return "Sách giá cao";
        else return "Sách giá đặc biệt";
    }

    static String tinhTonKho(int sl) {
        if (sl == 0) return "Hết sách";
        else if (sl <= 5) return "Sắp hết";
        else if (sl <= 20) return "Còn ít";
        else return "Còn nhiều";
    }

    //Bài 1
    static void nhapSach() {
        int soSachMoi;
        do {
            soSachMoi = docInt("Nhập số lượng sách cần nhập (n > 0): ");
            if (soSachMoi <= 0) System.out.println("Lỗi: n phải lớn hơn 0");
        } while (soSachMoi <= 0);


        int tong = n + soSachMoi;
        maSach = mo(maSach, tong);
        tenSach = mo(tenSach, tong);
        tacGia = mo(tacGia, tong);
        namXuatBan = mo(namXuatBan, tong);
        soLuong = mo(soLuong, tong);
        donGia = mo(donGia, tong);

        int namHienTai = Year.now().getValue();

        for (int i = 0; i < soSachMoi; i++) {
            System.out.println("--- Nhập sách thứ " + (n + 1) + " ---");
            maSach[n] = docString("Nhập mã sách: ");
            tenSach[n] = docString("Nhập tên sách: ");
            tacGia[n] = docString("Nhập tác giả: ");

            do {
                namXuatBan[n] = docInt("Nhập năm xuất bản: ");
                if (namXuatBan[n] > namHienTai)
                    System.out.println("Lỗi: năm xuất bản không được lớn hơn " + namHienTai);
            } while (namXuatBan[n] > namHienTai);

            do {
                donGia[n] = docDouble("Nhập đơn giá: ");
                if (donGia[n] <= 0) System.out.println("Lỗi: đơn giá phải lớn hơn 0");
            } while (donGia[n] <= 0);

            do {
                soLuong[n] = docInt("Nhập số lượng: ");
                if (soLuong[n] < 0) System.out.println("Lỗi: số lượng không được âm");
            } while (soLuong[n] < 0);

            n++;
        }
        System.out.println("Đã nhập xong " + soSachMoi + " sách.");
    }

    // Hàm mở rộng mảng
    static String[] mo(String[] a, int size) {
        String[] b = new String[size];
        for (int i = 0; i < n; i++) b[i] = a[i];
        return b;
    }

    static int[] mo(int[] a, int size) {
        int[] b = new int[size];
        for (int i = 0; i < n; i++) b[i] = a[i];
        return b;
    }

    static double[] mo(double[] a, int size) {
        double[] b = new double[size];
        for (int i = 0; i < n; i++) b[i] = a[i];
        return b;
    }

    //In thông tin 1 sách
    static void inSach(int i) {
        System.out.println("Mã sách      : " + maSach[i]);
        System.out.println("Tên sách     : " + tenSach[i]);
        System.out.println("Tác giả      : " + tacGia[i]);
        System.out.println("Năm xuất bản : " + namXuatBan[i]);
        System.out.printf("Đơn giá      : %,.0f VND%n", donGia[i]);
        System.out.println("Số lượng     : " + soLuong[i]);
        System.out.println("Phân loại giá: " + phanLoaiGia(donGia[i]));
        System.out.println("Tồn kho      : " + tinhTonKho(soLuong[i]));
    }

    static void hienThiDanhSach() {
        if (n == 0) {
            System.out.println("Thư viện chưa có sách nào!");
            return;
        }
        for (int i = 0; i < n; i++) {
            System.out.println("===== Sách " + (i + 1) + " =====");
            inSach(i);
        }
    }

    //Bài 2
    static void tinhTong() {
        if (n == 0) {
            System.out.println("Thư viện chưa có sách nào!");
            return;
        }
        int tongSoLuong = 0;
        double tongGiaTri = 0;
        for (int i = 0; i < n; i++) {
            tongSoLuong += soLuong[i];
            tongGiaTri += donGia[i] * soLuong[i];
        }
        System.out.println("Tổng số lượng sách: " + tongSoLuong);
        System.out.printf("Tổng giá trị sách : %,.0f VND%n", tongGiaTri);
    }

    //Bài 4
    static void timTheoMa() {
        if (n == 0) {
            System.out.println("Thư viện chưa có sách nào!");
            return;
        }
        String ma = docString("Nhập mã sách cần tìm: ");
        boolean timThay = false;
        for (int i = 0; i < n; i++) {
            if (maSach[i].equalsIgnoreCase(ma)) {
                System.out.println("Đã tìm thấy:");
                inSach(i);
                timThay = true;
                break;
            }
        }
        if (!timThay) System.out.println("Không tìm thấy sách có mã: " + ma);
    }

    //Bài 3
    public static void main(String[] args) {
        int chon;
        do {
            System.out.println("\nQUẢN LÝ THƯ VIỆN");
            System.out.println("1. Nhập sách");
            System.out.println("2. Hiển thị danh sách sách");
            System.out.println("3. Tính tổng số lượng và giá trị");
            System.out.println("4. Tìm sách theo mã");
            System.out.println("0. Thoát");
            chon = docInt("Nhập lựa chọn: ");

            switch (chon) {
                case 1: nhapSach(); break;
                case 2: hienThiDanhSach(); break;
                case 3: tinhTong(); break;
                case 4: timTheoMa(); break;
                case 0: System.out.println("Tạm biệt!"); break;
                default: System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        } while (chon != 0);

        sc.close();
    }
}