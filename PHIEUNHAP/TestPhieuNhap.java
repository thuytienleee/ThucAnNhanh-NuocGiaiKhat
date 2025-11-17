package PHIEUNHAP;

import CONNGUOI.NhanVien;
import NGUYENLIEU.NguyenLieu;
import KHO.Kho;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class TestPhieuNhap {
    private static DSPhieuNhap dsPhieuNhap = new DSPhieuNhap();
    private static Kho kho = new Kho();
    private static Scanner sc = new Scanner(System.in);
    private static int viTriPhieuHienTai = -1; // Để lưu vị trí phiếu đang làm việc

    public static void main(String[] args) {
        // Đọc dữ liệu từ file khi khởi động
        System.out.println("=== KHOI TAO HE THONG ===");
        kho.docFile();
        dsPhieuNhap.docFile();

        int choice;
        do {
            hienThiMenu();
            System.out.print("Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    hienThiTatCaPhieu();
                    break;
                case 2:
                    chonPhieuLamViec();
                    break;
                case 3:
                    taoPhieuNhapMoi();
                    break;
                case 4:
                    themNguyenLieuVaoPhieu();
                    break;
                case 5:
                    xoaNguyenLieuKhoiPhieu();
                    break;
                case 6:
                    suaNguyenLieuTrongPhieu();
                    break;
                case 7:
                    timNguyenLieuTrongPhieu();
                    break;
                case 8:
                    hienThiDanhSachNguyenLieuCuaPhieu();
                    break;
                case 9:
                    xacNhanPhieu();
                    break;
                case 10:
                    huyPhieu();
                    break;
                case 11:
                    tinhTongTienPhieu();
                    break;
                case 12:
                    kiemTraPhieuRong();
                    break;
                case 13:
                    demSoLuongNguyenLieu();
                    break;
                case 14:
                    hienThiTonKho();
                    break;
                case 15:
                    luuVaThoat();
                    break;
                case 0:
                    System.out.println("\nCam on ban da su dung chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    private static void hienThiMenu() {
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("|           QUAN LY PHIEU NHAP NGUYEN LIEU                   |");
        System.out.println("--------------------------------------------------------------");
        if (viTriPhieuHienTai >= 0 && viTriPhieuHienTai < dsPhieuNhap.getSoLuongPhieu()) {
            PhieuNhap phieu = dsPhieuNhap.getDsPhieu()[viTriPhieuHienTai];
            System.out.println("| Phieu dang lam viec: " + String.format("%-36s", phieu.getMaPhieuNhap()) + "|");
            System.out.println("--------------------------------------------------------------");
        }
        System.out.println("|  1. Hien thi tat ca phieu nhap                             |");
        System.out.println("|  2. Chon phieu de lam viec                                 |");
        System.out.println("|  3. Tao phieu nhap moi                                     |");
        System.out.println("| ---------------------------------------------------------- |");
        System.out.println("|  QUAN LY NGUYEN LIEU TRONG PHIEU                           |");
        System.out.println("|  4. Them nguyen lieu vao phieu                             |");
        System.out.println("|  5. Xoa nguyen lieu khoi phieu                             |");
        System.out.println("|  6. Sua thong tin nguyen lieu trong phieu                  |");
        System.out.println("|  7. Tim nguyen lieu trong phieu                            |");
        System.out.println("|  8. Hien thi danh sach nguyen lieu cua phieu               |");
        System.out.println("| ---------------------------------------------------------- |");
        System.out.println("|  XU LY PHIEU                                               |");
        System.out.println("|  9. Xac nhan phieu nhap                                    |");
        System.out.println("| 10. Huy phieu nhap                                         |");
        System.out.println("| 11. Tinh tong tien phieu                                   |");
        System.out.println("| 12. Kiem tra phieu rong                                    |");
        System.out.println("| 13. Dem so luong nguyen lieu trong phieu                   |");
        System.out.println("| ---------------------------------------------------------- |");
        System.out.println("| 14. Hien thi ton kho                                       |");
        System.out.println("| 15. Luu va thoat                                           |");
        System.out.println("|  0. Thoat khong luu                                        |");
        System.out.println("--------------------------------------------------------------");
    }

    // ======== 1. HIỂN THỊ TẤT CẢ PHIẾU ========
    private static void hienThiTatCaPhieu() {
        if (dsPhieuNhap.getSoLuongPhieu() == 0) {
            System.out.println("\nDanh sach phieu nhap rong!");
            return;
        }

        System.out.println("\n========== DANH SACH TAT CA PHIEU NHAP ==========");
        PhieuNhap[] dsPhieu = dsPhieuNhap.getDsPhieu();
        for (int i = 0; i < dsPhieuNhap.getSoLuongPhieu(); i++) {
            dsPhieu[i].HienThiThongTin();
            System.out.println();
        }
    }

    // ======== 2. CHỌN PHIẾU ĐỂ LÀM VIỆC ========
    private static void chonPhieuLamViec() {
        if (dsPhieuNhap.getSoLuongPhieu() == 0) {
            System.out.println("\nChua co phieu nao trong he thong!");
            return;
        }

        System.out.println("\n=== DANH SACH PHIEU ===");
        PhieuNhap[] dsPhieu = dsPhieuNhap.getDsPhieu();
        for (int i = 0; i < dsPhieuNhap.getSoLuongPhieu(); i++) {
            System.out.printf("%d. %s - %s - Trang thai: %s\n",
                    i + 1,
                    dsPhieu[i].getMaPhieuNhap(),
                    dsPhieu[i].getTenPhieuNhap(),
                    dsPhieu[i].getTrangThai());
        }

        System.out.print("\nChon so thu tu phieu (0 de huy): ");
        int chon = sc.nextInt();
        sc.nextLine();

        if (chon > 0 && chon <= dsPhieuNhap.getSoLuongPhieu()) {
            viTriPhieuHienTai = chon - 1;
            System.out.println("Da chon phieu: " + dsPhieu[viTriPhieuHienTai].getMaPhieuNhap());
            dsPhieu[viTriPhieuHienTai].HienThiThongTin();
        } else if (chon != 0) {
            System.out.println("Lua chon khong hop le!");
        }
    }

    // ======== 3. TẠO PHIẾU NHẬP MỚI ========
    private static void taoPhieuNhapMoi() {
        System.out.println("\n=== TAO PHIEU NHAP MOI ===");

        System.out.print("Ma phieu nhap: ");
        String maPhieu = sc.nextLine();

        System.out.print("Ten phieu nhap: ");
        String tenPhieu = sc.nextLine();

        Date ngayNhap = new Date();

        System.out.print("Ma nhan vien: ");
        String maNV = sc.nextLine();
        System.out.print("Ten nhan vien: ");
        String tenNV = sc.nextLine();
        System.out.print("So dien thoai: ");
        String soDT = sc.nextLine();
        System.out.print("Dia chi: ");
        String diaChi = sc.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        System.out.print("Ngay sinh (dd/MM/yyyy): ");
        String ngaySinh = sc.nextLine();
        Date ngay = null;
        try {
            ngay = sdf.parse(ngaySinh);
        } catch (ParseException e) {
            System.out.println("Ngay sinh khong hop le!");
        }
        System.out.print("Gioi tinh: ");
        String gioiTinh = sc.nextLine();
        System.out.print("Luong: ");
        double luong = sc.nextDouble();
        sc.nextLine();
        System.out.print("Chuc vu: ");
        String chucVu = sc.nextLine();
        System.out.print("Nam vao lam: ");
        int namVaoLam = sc.nextInt();
        sc.nextLine();

        NhanVien nv = new NhanVien(maNV, tenNV, diaChi, ngay,
                gioiTinh, luong, chucVu, namVaoLam);

        System.out.print("So luong nguyen lieu toi da: ");
        int kichThuoc = sc.nextInt();
        sc.nextLine();

        PhieuNhap phieu = new PhieuNhap(maPhieu, tenPhieu, ngayNhap, nv, kichThuoc);

        // Thêm vào danh sách
        PhieuNhap[] dsPhieu = dsPhieuNhap.getDsPhieu();
        if (dsPhieuNhap.getSoLuongPhieu() < dsPhieu.length) {
            dsPhieu[dsPhieuNhap.getSoLuongPhieu()] = phieu;
            viTriPhieuHienTai = dsPhieuNhap.getSoLuongPhieu();
            dsPhieuNhap.setSoLuongPhieu(dsPhieuNhap.getSoLuongPhieu() + 1);
            System.out.println("Tao phieu nhap thanh cong!");
        } else {
            System.out.println("Danh sach phieu da day!");
        }
    }

    // ======== 4. THÊM NGUYÊN LIỆU VÀO PHIẾU ========
    private static void themNguyenLieuVaoPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieuNhap.getDsPhieu()[viTriPhieuHienTai];
        System.out.println("\n=== THEM NGUYEN LIEU VAO PHIEU: " + phieu.getMaPhieuNhap() + " ===");

        System.out.print("Ma nguyen lieu: ");
        String maNL = sc.nextLine();
        System.out.print("Ten nguyen lieu: ");
        String tenNL = sc.nextLine();
        System.out.print("Don vi tinh: ");
        String dvt = sc.nextLine();
        System.out.print("So luong: ");
        double sl = sc.nextDouble();
        System.out.print("Don gia: ");
        double donGia = sc.nextDouble();
        sc.nextLine();

        NguyenLieu nl = new NguyenLieu(maNL, tenNL, dvt, sl);
        nl.setDonGia(donGia);
        phieu.ThemCTPhieuNhap(nl);
    }

    // ======== 5. XÓA NGUYÊN LIỆU KHỎI PHIẾU ========
    private static void xoaNguyenLieuKhoiPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieuNhap.getDsPhieu()[viTriPhieuHienTai];
        System.out.println("\n=== XOA NGUYEN LIEU KHOI PHIEU: " + phieu.getMaPhieuNhap() + " ===");

        phieu.HienThiDanhSachNguyenLieu();

        System.out.println("\n1. Xoa theo ma nguyen lieu");
        System.out.println("2. Xoa theo ten nguyen lieu");
        System.out.print("Chon cach xoa: ");
        int chon = sc.nextInt();
        sc.nextLine();

        if (chon == 1) {
            System.out.print("Nhap ma nguyen lieu can xoa: ");
            String ma = sc.nextLine();
            phieu.XoaCTPhieuNhapTheoMa(ma);
        } else if (chon == 2) {
            System.out.print("Nhap ten nguyen lieu can xoa: ");
            String ten = sc.nextLine();
            phieu.XoaCTPhieuNhap(ten);
        } else {
            System.out.println("Lua chon khong hop le!");
        }
    }

    // ======== 6. SỬA NGUYÊN LIỆU TRONG PHIẾU ========
    private static void suaNguyenLieuTrongPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieuNhap.getDsPhieu()[viTriPhieuHienTai];
        System.out.println("\n=== SUA NGUYEN LIEU TRONG PHIEU: " + phieu.getMaPhieuNhap() + " ===");

        phieu.HienThiDanhSachNguyenLieu();

        System.out.print("\nNhap ma nguyen lieu can sua: ");
        String maNL = sc.nextLine();

        NguyenLieu nl = phieu.TimNguyenLieuTheoMa(maNL);
        if (nl != null) {
            System.out.println("\nThong tin hien tai:");
            System.out.println(nl);

            System.out.print("\nNhap so luong moi (0 de giu nguyen): ");
            double slMoi = sc.nextDouble();
            System.out.print("Nhap don gia moi (0 de giu nguyen): ");
            double giaMoi = sc.nextDouble();
            sc.nextLine();

            phieu.SuaNguyenLieuTrongPhieu(maNL, slMoi, giaMoi);
        }
    }

    // ======== 7. TÌM NGUYÊN LIỆU TRONG PHIẾU ========
    private static void timNguyenLieuTrongPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieuNhap.getDsPhieu()[viTriPhieuHienTai];
        System.out.println("\n=== TIM NGUYEN LIEU TRONG PHIEU: " + phieu.getMaPhieuNhap() + " ===");

        System.out.println("1. Tim theo ma nguyen lieu");
        System.out.println("2. Tim theo ten nguyen lieu");
        System.out.print("Chon cach tim: ");
        int chon = sc.nextInt();
        sc.nextLine();

        NguyenLieu nl = null;
        if (chon == 1) {
            System.out.print("Nhap ma nguyen lieu: ");
            String ma = sc.nextLine();
            nl = phieu.TimNguyenLieuTheoMa(ma);
        } else if (chon == 2) {
            System.out.print("Nhap ten nguyen lieu: ");
            String ten = sc.nextLine();
            nl = phieu.TimNguyenLieuTheoTen(ten);
        }

        if (nl != null) {
            System.out.println("\n=== TIM THAY NGUYEN LIEU ===");
            System.out.println(nl);
            System.out.printf("Thanh tien: %,.0f VND\n", nl.tinhTien());
        } else {
            System.out.println("\nKhong tim thay nguyen lieu!");
        }
    }

    // ======== 8. HIỂN THỊ DANH SÁCH NGUYÊN LIỆU CỦA PHIẾU ========
    private static void hienThiDanhSachNguyenLieuCuaPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieuNhap.getDsPhieu()[viTriPhieuHienTai];
        System.out.println("\n=== DANH SACH NGUYEN LIEU CUA PHIEU: " + phieu.getMaPhieuNhap() + " ===");
        phieu.HienThiDanhSachNguyenLieu();
    }

    // ======== 9. XÁC NHẬN PHIẾU ========
    private static void xacNhanPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieuNhap.getDsPhieu()[viTriPhieuHienTai];
        System.out.println("\n=== XAC NHAN PHIEU: " + phieu.getMaPhieuNhap() + " ===");

        if (phieu.XacNhanPhieuNhap()) {
            // Cập nhật kho sau khi xác nhận
            kho.capNhatSauKhiNhap(phieu);
        }
    }

    // ======== 10. HỦY PHIẾU ========
    private static void huyPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieuNhap.getDsPhieu()[viTriPhieuHienTai];
        System.out.println("\n=== HUY PHIEU: " + phieu.getMaPhieuNhap() + " ===");

        System.out.print("Ban co chac chan muon huy phieu nay? (Y/N): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            phieu.HuyPhieuNhap();
        } else {
            System.out.println("Da huy thao tac!");
        }
    }

    // ======== 11. TÍNH TỔNG TIỀN PHIẾU ========
    private static void tinhTongTienPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieuNhap.getDsPhieu()[viTriPhieuHienTai];
        double tongTien = phieu.TongTienPhieu();

        System.out.println("\n=== TONG TIEN PHIEU: " + phieu.getMaPhieuNhap() + " ===");
        System.out.printf("Tong tien: %,.0f VND\n", tongTien);
    }

    // ======== 12. KIỂM TRA PHIẾU RỖNG ========
    private static void kiemTraPhieuRong() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieuNhap.getDsPhieu()[viTriPhieuHienTai];

        if (phieu.KiemTraPhieuRong()) {
            System.out.println("\nPhieu " + phieu.getMaPhieuNhap() + " RONG (chua co nguyen lieu)");
        } else {
            System.out.println("\nPhieu " + phieu.getMaPhieuNhap() + " co " + phieu.getSoLuongNL() + " nguyen lieu");
        }
    }

    // ======== 13. ĐẾM SỐ LƯỢNG NGUYÊN LIỆU ========
    private static void demSoLuongNguyenLieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieuNhap.getDsPhieu()[viTriPhieuHienTai];
        int soLuong = phieu.DemSoLuongNguyenLieu();

        System.out.println("\n=== THONG KE PHIEU: " + phieu.getMaPhieuNhap() + " ===");
        System.out.println("So luong nguyen lieu: " + soLuong);
    }

    // ======== 14. HIỂN THỊ TỒN KHO ========
    private static void hienThiTonKho() {
        kho.hienThiTonKho();
    }

    // ======== 15. LƯU VÀ THOÁT ========
    private static void luuVaThoat() {
        System.out.println("\n=== LUU DU LIEU ===");
        dsPhieuNhap.ghiFile();
        kho.ghiFile();
        System.out.println("\nCam on ban da su dung chuong trinh!");
        System.exit(0);
    }
}