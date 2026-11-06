import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    // Bài 3
    static ArrayList<String> danhSachSach = new ArrayList<>();

    //Hàm đọc dữ liệu an toàn
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


    static String docChuoi(String msg) {
        String s;
        do {
            System.out.print(msg);
            s = sc.nextLine().trim().replace("|", " ");
            if (s.isEmpty()) System.out.println("Lỗi: không được để trống!");
        } while (s.isEmpty());
        return s;
    }

    // Phân loại
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

    //BÀI 1
    static void bai1() {
        int[] soLuong = new int[5];

        for (int i = 0; i < soLuong.length; i++) {
            do {
                soLuong[i] = docInt("Nhập số lượng cuốn sách thứ " + (i + 1) + ": ");
                if (soLuong[i] < 0) System.out.println("Lỗi: số lượng không được âm");
            } while (soLuong[i] < 0);
        }

        System.out.println("Danh sách số lượng:");
        int tong = 0;
        for (int i = 0; i < soLuong.length; i++) {
            System.out.println("Sách " + (i + 1) + ": " + soLuong[i]);
            tong += soLuong[i];
        }
        System.out.println("Tổng số lượng: " + tong);

        int max = soLuong[0];
        for (int i = 1; i < soLuong.length; i++) {
            if (soLuong[i] > max) max = soLuong[i];
        }
        System.out.print("Số lượng lớn nhất: " + max + " (sách thứ");
        for (int i = 0; i < soLuong.length; i++) {
            if (soLuong[i] == max) System.out.print(" " + (i + 1));
        }
        System.out.println(")");
    }

    //BÀI 2
    static void bai2() {
        ArrayList<String> dsMa = new ArrayList<>();
        int chon;
        do {
            System.out.println("\n--- QUẢN LÝ MÃ SÁCH ---");
            System.out.println("1. Thêm mã sách");
            System.out.println("2. Hiển thị mã sách");
            System.out.println("3. Tìm mã sách");
            System.out.println("4. Xóa mã sách");
            System.out.println("0. Quay lại");
            chon = docInt("Nhập lựa chọn: ");

            switch (chon) {
                case 1: {
                    String ma = docChuoi("Nhập mã sách: ");
                    if (dsMa.contains(ma)) System.out.println("Mã đã tồn tại!");
                    else {
                        dsMa.add(ma);
                        System.out.println("Đã thêm mã " + ma);
                    }
                    break;
                }
                case 2:
                    if (dsMa.isEmpty()) System.out.println("Danh sách trống!");
                    else {
                        System.out.println("Có " + dsMa.size() + " mã sách:");
                        for (int i = 0; i < dsMa.size(); i++)
                            System.out.println((i + 1) + ". " + dsMa.get(i));
                    }
                    break;
                case 3: {
                    String ma = docChuoi("Nhập mã cần tìm: ");
                    int vt = dsMa.indexOf(ma);
                    if (vt >= 0) System.out.println("Tìm thấy " + ma + " ở vị trí " + (vt + 1));
                    else System.out.println("Không tìm thấy mã " + ma);
                    break;
                }
                case 4: {
                    String ma = docChuoi("Nhập mã cần xóa: ");
                    if (dsMa.remove(ma)) System.out.println("Đã xóa mã " + ma);
                    else System.out.println("Không tìm thấy mã " + ma);
                    break;
                }
                case 0: break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);
    }


    //BÀI 3 + 4


    static int timViTri(String ma) {
        for (int i = 0; i < danhSachSach.size(); i++) {
            String[] f = danhSachSach.get(i).split("\\|");
            if (f[0].equalsIgnoreCase(ma)) return i;
        }
        return -1;
    }


    static String nhapChiTiet(String ma) {
        String ten = docChuoi("Nhập tên sách: ");
        String tacGia = docChuoi("Nhập tác giả: ");
        int namHienTai = Year.now().getValue();

        int nam;
        do {
            nam = docInt("Nhập năm xuất bản: ");
            if (nam <= 0 || nam > namHienTai)
                System.out.println("Lỗi: năm phải từ 1 đến " + namHienTai);
        } while (nam <= 0 || nam > namHienTai);

        double gia;
        do {
            gia = docDouble("Nhập đơn giá: ");
            if (gia <= 0) System.out.println("Lỗi: đơn giá phải lớn hơn 0");
        } while (gia <= 0);

        int sl;
        do {
            sl = docInt("Nhập số lượng: ");
            if (sl < 0) System.out.println("Lỗi: số lượng không được âm");
        } while (sl < 0);

        return ma + "|" + ten + "|" + tacGia + "|" + nam + "|" + gia + "|" + sl;
    }

    static void inSach(String s) {
        String[] f = s.split("\\|");
        double gia = Double.parseDouble(f[4]);
        int sl = Integer.parseInt(f[5]);
        System.out.println("Mã sách      : " + f[0]);
        System.out.println("Tên sách     : " + f[1]);
        System.out.println("Tác giả      : " + f[2]);
        System.out.println("Năm xuất bản : " + f[3]);
        System.out.printf("Đơn giá      : %,.0f VND%n", gia);
        System.out.println("Số lượng     : " + sl);
        System.out.println("Phân loại giá: " + phanLoaiGia(gia));
        System.out.println("Tồn kho      : " + tinhTonKho(sl));
    }

    static void themSach() {
        String ma = docChuoi("Nhập mã sách: ");
        if (timViTri(ma) != -1) {
            System.out.println("Lỗi: mã sách đã tồn tại!");
            return;
        }
        danhSachSach.add(nhapChiTiet(ma));
        System.out.println("Đã thêm sách.");
    }

    static void hienThiSach() {
        if (danhSachSach.isEmpty()) {
            System.out.println("Thư viện chưa có sách nào!");
            return;
        }
        System.out.println("Thư viện có " + danhSachSach.size() + " sách:");
        for (int i = 0; i < danhSachSach.size(); i++) {
            System.out.println("Sách " + (i + 1));
            inSach(danhSachSach.get(i));
        }
    }

    static void timSach() {
        String ma = docChuoi("Nhập mã sách cần tìm: ");
        int vt = timViTri(ma);
        if (vt == -1) System.out.println("Không tìm thấy sách có mã: " + ma);
        else {
            System.out.println("Đã tìm thấy:");
            inSach(danhSachSach.get(vt));
        }
    }

    static void xoaSach() {
        String ma = docChuoi("Nhập mã sách cần xóa: ");
        int vt = timViTri(ma);
        if (vt == -1) System.out.println("Không tìm thấy sách có mã: " + ma);
        else {
            danhSachSach.remove(vt);
            System.out.println("Đã xóa sách.");
        }
    }

    static void suaSach() {
        String ma = docChuoi("Nhập mã sách cần sửa: ");
        int vt = timViTri(ma);
        if (vt == -1) {
            System.out.println("Không tìm thấy sách có mã: " + ma);
            return;
        }
        System.out.println("Thông tin hiện tại:");
        inSach(danhSachSach.get(vt));
        System.out.println("Nhập thông tin mới (mã sách giữ nguyên):");
        String maGoc = danhSachSach.get(vt).split("\\|")[0];
        danhSachSach.set(vt, nhapChiTiet(maGoc));
        System.out.println("Đã cập nhật sách.");
    }

    static void tongSoLuong() {
        int tong = 0;
        for (int i = 0; i < danhSachSach.size(); i++) {
            tong += Integer.parseInt(danhSachSach.get(i).split("\\|")[5]);
        }
        System.out.println("Tổng số lượng sách: " + tong);
    }

    static void tongGiaTri() {
        double tong = 0;
        for (int i = 0; i < danhSachSach.size(); i++) {
            String[] f = danhSachSach.get(i).split("\\|");
            tong += Double.parseDouble(f[4]) * Integer.parseInt(f[5]);
        }
        System.out.printf("Tổng giá trị sách: %,.0f VND%n", tong);
    }

    //Bài 4
    static void menuQuanLySach() {
        int chon;
        do {
            System.out.println("\n===== QUẢN LÝ THƯ VIỆN =====");
            System.out.println("1. Thêm sách");
            System.out.println("2. Hiển thị sách");
            System.out.println("3. Tìm sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Sửa sách");
            System.out.println("6. Tính tổng số lượng");
            System.out.println("7. Tính tổng giá trị");
            System.out.println("8. Thoát");
            chon = docInt("Nhập lựa chọn: ");

            switch (chon) {
                case 1: themSach(); break;
                case 2: hienThiSach(); break;
                case 3: timSach(); break;
                case 4: xoaSach(); break;
                case 5: suaSach(); break;
                case 6: tongSoLuong(); break;
                case 7: tongGiaTri(); break;
                case 8: System.out.println("Quay lại menu chính."); break;
                default: System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        } while (chon != 8);
    }

    //Menu chính
    public static void main(String[] args) {
        int chon;
        do {
            System.out.println("\nBÀI THỰC HÀNH TUẦN 6");
            System.out.println("1. Bài 1: Mảng số lượng 5 cuốn sách");
            System.out.println("2. Bài 2: ArrayList mã sách");
            System.out.println("3. Bài 3 + 4: Quản lý sách (menu hoàn chỉnh)");
            System.out.println("0. Thoát chương trình");
            chon = docInt("Nhập lựa chọn: ");

            switch (chon) {
                case 1: bai1(); break;
                case 2: bai2(); break;
                case 3: menuQuanLySach(); break;
                case 0: System.out.println("Tạm biệt!"); break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);

        sc.close();
    }
}