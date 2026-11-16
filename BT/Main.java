import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;


class Sach {
    private String maSach;
    private String tieuDe;
    private String tacGia;
    private int namXuatBan;
    private int soLuong;

    public Sach() {
    }

    public Sach(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.soLuong = soLuong;
    }

    // Getter / Setter
    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }

    public String getTieuDe() { return tieuDe; }
    public void setTieuDe(String tieuDe) { this.tieuDe = tieuDe; }

    public String getTacGia() { return tacGia; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }

    public int getNamXuatBan() { return namXuatBan; }
    public void setNamXuatBan(int namXuatBan) { this.namXuatBan = namXuatBan; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    // Phân loại tồn kho
    public String tinhTonKho() {
        if (soLuong == 0) return "Hết sách";
        else if (soLuong <= 5) return "Sắp hết";
        else if (soLuong <= 20) return "Còn ít";
        else return "Còn nhiều";
    }

    //YÊU CẦU 2: In toàn bộ thông tin cuốn sách
    public void hienThiThongTin() {
        System.out.println("Mã sách      : " + maSach);
        System.out.println("Tiêu đề      : " + tieuDe);
        System.out.println("Tác giả      : " + tacGia);
        System.out.println("Năm xuất bản : " + namXuatBan);
        System.out.println("Số lượng     : " + soLuong);
        System.out.println("Tồn kho      : " + tinhTonKho());
    }
}


public class Main {
    static Scanner sc = new Scanner(System.in);

    //YÊU CẦU 4: Danh sách Sach bằng ArrayList
    static ArrayList<Sach> danhSachSach = new ArrayList<>();

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

    static String docChuoi(String msg) {
        String s;
        do {
            System.out.print(msg);
            s = sc.nextLine().trim();
            if (s.isEmpty()) System.out.println("Lỗi: không được để trống!");
        } while (s.isEmpty());
        return s;
    }

    static int docNam() {
        int namHienTai = Year.now().getValue();
        int nam;
        do {
            nam = docInt("Nhập năm xuất bản: ");
            if (nam <= 0 || nam > namHienTai)
                System.out.println("Lỗi: năm phải từ 1 đến " + namHienTai);
        } while (nam <= 0 || nam > namHienTai);
        return nam;
    }

    static int docSoLuong() {
        int sl;
        do {
            sl = docInt("Nhập số lượng: ");
            if (sl < 0) System.out.println("Lỗi: số lượng không được âm");
        } while (sl < 0);
        return sl;
    }
    static void demoTaoDoiTuong() {
        Sach s1 = new Sach("S01", "Lập trình Java", "Nguyễn Văn A", 2020, 15);
        Sach s2 = new Sach();
        s2.setMaSach("S02");
        s2.setTieuDe("Cấu trúc dữ liệu");
        s2.setTacGia("Trần Thị B");
        s2.setNamXuatBan(2018);
        s2.setSoLuong(3);

        System.out.println("Đối tượng s1 (constructor có tham số):");
        s1.hienThiThongTin();
        System.out.println("\nĐối tượng s2 (constructor rỗng + setter):");
        s2.hienThiThongTin();
    }


    static int timViTri(String ma) {
        for (int i = 0; i < danhSachSach.size(); i++) {
            if (danhSachSach.get(i).getMaSach().equalsIgnoreCase(ma)) return i;
        }
        return -1;
    }

    static void themSach() {
        String ma = docChuoi("Nhập mã sách: ");
        if (timViTri(ma) != -1) {
            System.out.println("Lỗi: mã sách đã tồn tại!");
            return;
        }
        String tieuDe = docChuoi("Nhập tiêu đề: ");
        String tacGia = docChuoi("Nhập tác giả: ");
        int nam = docNam();
        int sl = docSoLuong();
        danhSachSach.add(new Sach(ma, tieuDe, tacGia, nam, sl));
        System.out.println("Đã thêm sách.");
    }

    static void hienThiSach() {
        if (danhSachSach.isEmpty()) {
            System.out.println("Thư viện chưa có sách nào!");
            return;
        }
        System.out.println("Thư viện có " + danhSachSach.size() + " sách:");
        for (int i = 0; i < danhSachSach.size(); i++) {
            System.out.println("\nSách " + (i + 1));
            danhSachSach.get(i).hienThiThongTin();
        }
    }

    static void timSach() {
        String ma = docChuoi("Nhập mã sách cần tìm: ");
        int vt = timViTri(ma);
        if (vt == -1) System.out.println("Không tìm thấy sách có mã: " + ma);
        else {
            System.out.println("Đã tìm thấy:");
            danhSachSach.get(vt).hienThiThongTin();
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
        Sach s = danhSachSach.get(vt);
        System.out.println("Thông tin hiện tại:");
        s.hienThiThongTin();
        System.out.println("Nhập thông tin mới (mã sách giữ nguyên):");
        s.setTieuDe(docChuoi("Nhập tiêu đề: "));
        s.setTacGia(docChuoi("Nhập tác giả: "));
        s.setNamXuatBan(docNam());
        s.setSoLuong(docSoLuong());
        System.out.println("Đã cập nhật sách.");
    }

    static void tongSoLuong() {
        int tong = 0;
        for (int i = 0; i < danhSachSach.size(); i++) {
            tong += danhSachSach.get(i).getSoLuong();
        }
        System.out.println("Tổng số lượng sách: " + tong);
    }

    static void menuQuanLySach() {
        int chon;
        do {
            System.out.println("\nQUẢN LÝ THƯ VIỆN");
            System.out.println("1. Thêm sách");
            System.out.println("2. Hiển thị sách");
            System.out.println("3. Tìm sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Sửa sách");
            System.out.println("6. Tính tổng số lượng");
            System.out.println("7. Quay lại");
            chon = docInt("Nhập lựa chọn: ");

            switch (chon) {
                case 1: themSach(); break;
                case 2: hienThiSach(); break;
                case 3: timSach(); break;
                case 4: xoaSach(); break;
                case 5: suaSach(); break;
                case 6: tongSoLuong(); break;
                case 7: System.out.println("Quay lại menu chính."); break;
                default: System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        } while (chon != 7);
    }

    //Menu chính
    public static void main(String[] args) {
        int chon;
        do {
            System.out.println("\nCHƯƠNG TRÌNH QUẢN LÝ SÁCH TRONG THƯ VIỆN");
            System.out.println("1. Yêu cầu 1 + 2 + 3: Tạo đối tượng Sach và hiển thị thông tin");
            System.out.println("2. Yêu cầu 4: Quản lý danh sách Sach bằng ArrayList");
            System.out.println("0. Thoát chương trình");
            chon = docInt("Nhập lựa chọn: ");

            switch (chon) {
                case 1: demoTaoDoiTuong(); break;
                case 2: menuQuanLySach(); break;
                case 0: System.out.println("Tạm biệt!"); break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);

        sc.close();
    }
}

