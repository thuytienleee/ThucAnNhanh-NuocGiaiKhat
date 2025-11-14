import CONNGUOI.DSachKH;
import CONNGUOI.DSachNV;
import HOADON.DSachHD;
import KHO.Kho;
import NGUYENLIEU.DSNguyenLieu;
import PHIEUNHAP.DSPhieuNhap;
import PHIEUNHAP.PhieuNhap;
import SANPHAM.DSachSP;
import java.util.Scanner;

public class QUANLYCUAHANG {

    private DSachSP dsSanPham;
    private DSachNV dsNhanVien;
    private DSachKH dsKhachHang;
    private DSachHD dsHoaDon;

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
        int luaChon;
        int chonMot;
        do {
            System.out.println("\n===== QUAN LY HOA DON =====");
            System.out.println("1. Chi tiet hoa don");
            System.out.println("2. Chi tiet danh sach hoa don");
            System.out.println("3. Chi tiet thanh toan");
            System.out.println("0. Quay lai menu truoc");
            System.out.println("=============================");
            System.out.println("Moi ban nhap lua chon: ");
            luaChon = Integer.parseInt(sc.nextLine());
            switch (luaChon) {
                case 1: {
                    do {
                        System.out.println("===== CHI TIET HOA DON =====");
                        System.out.println("4. Tinh tong tien");
                        System.out.println("5. In hoa don");
                        System.out.println("6. Quay lai menu truoc");
                        System.out.println("Moi ban chon chuc nang chi tiet hoa don: ");
                        chonMot = Integer.parseInt(sc.nextLine());
                        switch (chonMot) {
                            case 4: {
                                String filePath = "hoadon.txt";
                                //HoaDon hoaDon = HoaDonIO.docHoaDonTuFile(filePath);
                                //hoaDon.inHoaDon();
                                break;
                            }
                        }
                    } while (chonMot != 0);
                    break;
                }
            }
        } while (luaChon != 0);
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

    public void chonQuanLyPhieuNhap(){
        DSPhieuNhap dspn = new DSPhieuNhap();
        dspn.docFile();
        int chon;
        do {
            System.out.println("\n======== Quan ly phieu nhap ========");
            System.out.println("1. Xem danh sach phieu nhap");
            System.out.println("2. Them phieu nhap moi.");
            System.out.println("3. Sua thong tin phieu");
            System.out.println("4. Huy phieu nhap.");
            System.out.println("0. Quay lai");
            System.out.println("======================================");
            System.out.print("Chon: ");
            chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    PhieuNhap[] ds = dspn.getDsPhieu();
                    int sl = dspn.getSoLuongPhieu();
                    if(sl == 0) { 
                        System.out.println("Khong co phieu nhap nao");
                    } else {
                        System.out.println("\n===== DANH SACH PHIEU NHAP =====");
                        for (int i = 0; i < sl; i++) {
                            System.out.println("Phieu thu " + (i + 1) + ":");
                            ds[i].HienThiThongTin();
                            System.out.println("-------------------------------");
                        }
                    }
                    break;
                case 2:
                    //dspn.themPhieuNhap();
                    break;
/*                 case 3:
                    dsNguyenLieu.suaNguyenLieu();
                    break;
                case 4:
                    dsNguyenLieu.xoaNguyenLieu();
                    break; */
                case 0:
                    System.out.println("Quay lai");
                    break;
                default:
                    System.out.println("Lua chon khong hop le");
            }
        } while (chon != 0);
    }

    public void chonBaoCao() {
        System.out.println("\n[Chuc nang thong ke dang phat trien...]");
    }

    public static void main(String[] args) {
        QUANLYCUAHANG ql = new QUANLYCUAHANG();
        ql.hienThiMenu();
    }
}
