import CONNGUOI.DSachKH;
import CONNGUOI.DSachNV;
import CONNGUOI.KhachHang;
import CONNGUOI.NhanVien;
import HOADON.DSachHD;
import HOADON.HoaDon;
import KHO.Kho;
import NGUYENLIEU.DSNguyenLieu;
import PHIEUNHAP.DSPhieuNhap;
import PHIEUNHAP.PhieuNhap;
import SANPHAM.DSachSP;
import SANPHAM.SanPham;
import java.util.Scanner;
import java.util.Date;

public class QUANLYCUAHANG {

    private DSachSP dsSanPham;
    private DSachNV dsNhanVien;
    private DSachKH dsKhachHang;
    private DSachHD dsHoaDon;
    private DSPhieuNhap dsPhieuNhap;

    private Scanner sc = new Scanner(System.in);

    public QUANLYCUAHANG() {
    }

    public void hienThiMenu() {
        int chon;
        do {
            System.out.println("\n===== MENU QUAN LY CUA HANG =====");
            System.out.println("1. Quan ly san pham");
            System.out.println("2. Quan ly nhan vien");
            System.out.println("3. Quan ly khach hang");
            System.out.println("4. Quan ly hoa don");
            System.out.println("5. Quan ly kho");
            System.out.println("6. Quan ly nguyen lieu");
            System.out.println("7. Quan ly phieu nhap");
            System.out.println("8. Bao cao");
            System.out.println("0. Thoat");
            System.out.println("==================================");
            System.out.print("Nhap lua chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    chonQuanLySanPham();
                    break;
                case 2:
                    chonQuanLyNhanVien();
                    break;
                case 3:
                    chonQuanLyKhachHang();
                    break;
                case 4:
                    chonQuanLyHoaDon();
                    break;
                case 5:
                    chonQuanLyKho();
                    break;
                case 6:
                    chonQuanLyNguyenLieu();
                    break;
                case 7:
                    chonQuanLyPhieuNhap();
                    break;
                case 8:
                    chonBaoCao();
                    break;
                case 0:
                    System.out.println("Tam biet ban !");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    public void chonQuanLySanPham() {
        if (dsSanPham == null) {
            dsSanPham = new DSachSP(1000);
            dsSanPham.DocFile();
        }
        int chon;
        do {
            System.out.println("\n===== QUAN LY SAN PHAM =====");
            System.out.println("1. Hien thi danh sach san pham");
            System.out.println("2. Them san pham");
            System.out.println("3. Tim kiem san pham theo ten");
            System.out.println("4. Tim kiem san pham theo ma");
            System.out.println("5. Xoa san pham");
            System.out.println("6. Sua san pham");
            System.out.println("7. Thoat");
            System.out.println("=============================");
            System.out.print("Chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    dsSanPham.hienThiDS();
                    break;
                case 2:
                    dsSanPham.themSanPham();
                    break;
                case 3:
                    dsSanPham.timKiemTheoTen();
                    break;
                case 4:
                    dsSanPham.timKiemTheoMa();
                    break;
                case 5:
                    dsSanPham.xoaSanPham();
                    break;
                case 6:
                    dsSanPham.suaSanPham();
                    break;
                case 7:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 7);
    }

    public void chonQuanLyNhanVien() {

        if (dsNhanVien == null) {
            dsNhanVien = new DSachNV(1000);
            dsNhanVien.DocFile();
        }
        int chon;
        do {
            System.out.println("\n===== QUAN LY NHAN VIEN =====");
            System.out.println("1. Hien thi danh sach nhan vien");
            System.out.println("2. Them nhan vien");
            System.out.println("3. Tim kiem nhan vien theo ma");
            System.out.println("4. Cap nhat thong tin nhan vien");
            System.out.println("5. Xoa nhan vien");
            System.out.println("6. Thoat");
            System.out.println("=============================");
            System.out.print("Chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    dsNhanVien.hienThiDanhSach();
                    break;
                case 2:
                    dsNhanVien.themNhanVien();
                    break;
                case 3:
                    dsNhanVien.timKiemNhanVienTheoMa();
                    break;
                case 4:
                    dsNhanVien.SuaThongTinNhanVien();
                    break;
                case 5:
                    dsNhanVien.xoaNhanVien();
                    break;
                case 6:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 6);
    }

    public void chonQuanLyKhachHang() {
        if (dsKhachHang == null) {
            dsKhachHang = new DSachKH(1000);
            dsKhachHang.DocFile();
        }
        int chon;
        do {
            System.out.println("\n===== QUAN LY KHACH HANG =====");
            System.out.println("1. Hien thi danh sach khach hang");
            System.out.println("2. Them khach hang");
            System.out.println("3. Tim kiem khach hang theo ma");
            System.out.println("4. Cap nhat thong tin khach hang");
            System.out.println("5. Xoa khach hang");
            System.out.println("6. Thoat");
            System.out.println("=============================");
            System.out.print("Chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    dsKhachHang.hienThiDanhSach();
                    break;
                case 2:
                    dsKhachHang.themKhachHang();
                    break;
                case 3:
                    dsKhachHang.timKiemKhachHangTheoMa();
                    break;
                case 4:
                    dsKhachHang.SuaThongTinKhachHang();
                    break;
                case 5:
                    dsKhachHang.xoaKhachHang();
                    break;
                case 6:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 6);
    }

    public void chonQuanLyHoaDon() {
        // Khởi tạo các danh sách nếu chưa có
        if (dsNhanVien == null) {
            dsNhanVien = new DSachNV(1000);
            dsNhanVien.DocFile();
        }
        if (dsKhachHang == null) {
            dsKhachHang = new DSachKH(1000);
            dsKhachHang.DocFile();
        }
        if (dsSanPham == null) {
            dsSanPham = new DSachSP(1000);
            dsSanPham.DocFile();
        }
        if (dsHoaDon == null) {
            dsHoaDon = new DSachHD(1000, dsNhanVien, dsKhachHang, dsSanPham);
            dsHoaDon.docDanhSachHD();
        }

        int choice;
        do {
            System.out.println("\n========== QUAN LY HOA DON ==========");
            System.out.println("1. Them hoa don moi");
            System.out.println("2. Xoa hoa don");
            System.out.println("3. Tim kiem hoa don theo ma");
            System.out.println("4. Tim kiem hoa don theo khach hang");
            System.out.println("5. In danh sach hoa don");
            System.out.println("6. Tinh tong doanh thu");
            System.out.println("0. Thoat");
            System.out.println("======================================");
            System.out.print("Nhap lua chon: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    dsHoaDon.themHoaDonMoi();
                    break;
                case 2:
                    dsHoaDon.xoaHoaDon();
                    break;
                case 3:
                    dsHoaDon.timKiemHoaDonTheoMa();
                    break;
                case 4:
                    dsHoaDon.timKiemHoaDonTheoKH();
                    break;
                case 5:
                    dsHoaDon.inDanhSachHD();
                    break;
                case 6:
                    dsHoaDon.tinhTongDoanhThu();
                    break;
                case 0:
                    System.out.println("Quay lai menu chinh...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai!");
            }
        } while (choice != 0);
    }

    public void chonQuanLyKho() {
        int chon;
        do {
            System.out.println("\n===== QUAN LY KHO =====");
            System.out.println("1. Xem danh sach ton kho");
            System.out.println("2. Quan ly nguyen lieu");
            System.out.println("3. Quan ly phieu nhap");
            System.out.println("0. Quay lai");
            System.out.println("=========================");
            System.out.print("Chon: ");
            chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    Kho kho = new Kho();
                    kho.docFile();
                    kho.hienThiTonKho();
                    break;
                case 2:
                    chonQuanLyNguyenLieu();
                    break;
                case 3:
                    chonQuanLyPhieuNhap();
                    break;
                case 0:
                    System.out.println("Quay lai menu...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    public void chonQuanLyNguyenLieu() {
        DSNguyenLieu dsNguyenLieu = new DSNguyenLieu();
        int chon;
        do {
            System.out.println("\n======== Quan ly nguyen lieu ========");
            System.out.println("1. Xem danh sach nguyen lieu");
            System.out.println("2. Them nguyen lieu moi.");
            System.out.println("3. Sua thong tin nguyen lieu");
            System.out.println("4. Xoa nguyen lieu.");
            System.out.println("0. Quay lai");
            System.out.println("======================================");
            System.out.print("Chon: ");
            chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    dsNguyenLieu.docFile();
                    break;
                case 2:
                    dsNguyenLieu.AddNL();
                    dsNguyenLieu.ghiFile();
                    break;
                case 3:
                    dsNguyenLieu.suaNguyenLieu();
                    break;
                case 4:
                    dsNguyenLieu.xoaNguyenLieu();
                    break;
                case 0:
                    System.out.println("Quay lai");
                    break;
                default:
                    System.out.println("Lua chon khong hop le");
            }
        } while (chon != 0);
    }

    public void chonQuanLyPhieuNhap() {

    }

    public void chonBaoCao() {
        Scanner sc = new Scanner(System.in);
        BaoCao bc = new BaoCao();

        while (true) {
            System.out.println("\n===== MENU BAO CAO =====");
            System.out.println("1. Tao bao cao moi");
            System.out.println("2. Xem danh sach bao cao");
            System.out.println("3. Xem chi tiet 1 bao cao");
            System.out.println("4. Sua bao cao");
            System.out.println("5. Xoa bao cao");
            System.out.println("6. Thong ke TOP 5 san pham ban chay");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Nhap lua chon: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    bc.taoBC();
                    break;
                case "2":
                    bc.docFile();
                    break;
                case "3":
                    System.out.print("Nhap ID bao cao can xem chi tiet: ");
                    String idXem = sc.nextLine().trim();
                    bc.xemChiTietBaoCao(idXem);
                    break;
                case "4":
                    System.out.print("Nhap ID bao cao can sua: ");
                    String idSua = sc.nextLine().trim();
                    bc.suaBaoCao(idSua);
                    break;
                case "5":
                    System.out.print("Nhap ID bao cao can xoa: ");
                    String idXoa = sc.nextLine().trim();
                    bc.xoaBaoCao(idXoa);
                    break;
                case "6":
                    DSachNV dsNV = new DSachNV(100); dsNV.DocFile();
                    DSachKH dsKH = new DSachKH(100); dsKH.DocFile();
                    DSachSP dsSP = new DSachSP(100); dsSP.DocFile();
                    DSachHD dsHD = new DSachHD(100, dsNV, dsKH, dsSP);
                    dsHD.docDanhSachHD();
                    bc.thongKeTop5SanPhamBanChay(dsHD, dsNV, dsKH, dsSP);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long nhap lai!");
            }
        }
    }


    public static void main(String[] args) {
        QUANLYCUAHANG ql = new QUANLYCUAHANG();
        ql.hienThiMenu();
    }
}
