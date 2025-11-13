// xung dot cua toi 
/*
 * // package ThucAnNhanh_NuocGiaiKhat;
 * import CONNGUOI.DSachKH;
 * import CONNGUOI.DSachNV;
 * import CONNGUOI.KhachHang;
 * import CONNGUOI.NhanVien;
 * import HOADON.DSachHD;
 * import HOADON.HoaDon;
 * // import HOADON.HoaDonIO;
 * import KHO.Kho;
 * import NGUYENLIEU.DSNguyenLieu;
 * 
 * import SANPHAM.DSachSP;
 * import SANPHAM.SanPham;
 * 
 * import java.text.SimpleDateFormat;
 * import java.util.Scanner;
 * 
 * public class QUANLYCUAHANG {
 * private DSachSP dsSanPham;
 * private DSachNV dsNhanVien;
 * private DSachKH dsKhachHang;
 * private DSachHD dsHoaDon;
 * 
 * private Scanner sc = new Scanner(System.in);
 * 
 * /*
 * public QUANLYCUAHANG() {
 * dsSanPham = new DSachSP(100);
 * dsNhanVien = new DSachNV(50);
 * dsKhachHang = new DSachKH(100);
 * dsHoaDon = new DSachHD(100);
 * }
 * 
 * // Ham hien thi menu chinh
 * public void hienThiMenu() {
 * int chon;
 * do {
 * System.out.println("\n===== MENU QUAN LY CUA HANG =====");
 * System.out.println("1. Quan ly san pham");
 * System.out.println("2. Quan ly nhan vien");
 * System.out.println("3. Quan ly khach hang");
 * System.out.println("4. Quan ly hoa don");
 * System.out.println("5. Quan ly kho");
 * System.out.println("6. Bao cao");
 * System.out.println("0. Thoat");
 * System.out.println("==================================");
 * System.out.print("Nhap lua chon: ");
 * chon = Integer.parseInt(sc.nextLine());
 * 
 * switch (chon) {
 * case 1:
 * chonQuanLySanPham();
 * break;
 * case 2:
 * chonQuanLyNhanVien();
 * break;
 * case 3:
 * chonQuanLyKhachHang();
 * break;
 * case 4:
 * chonQuanLyHoaDon();
 * break;
 * case 5:
 * // chonQuanLyKho();
 * break;
 * case 6:
 * chonBaoCao();
 * break;
 * case 0:
 * System.out.println("Tam biet ban !");
 * break;
 * default:
 * System.out.println("Lua chon khong hop le!");
 * }
 * } while (chon != 0);
 * }
 * 
 * private void thoat() {
 * 
 * }
 * 
 * public void chonQuanLySanPham() {
 * int chon;
 * do {
 * System.out.println("\n===== QUAN LY SAN PHAM =====");
 * System.out.println("1. Hien thi danh sach san pham");
 * System.out.println("2. Them san pham");
 * System.out.println("3. Tim kiem san pham theo ten");
 * System.out.println("4. Tim kiem san pham theo ma");
 * System.out.println("5. Xoa san pham");
 * System.out.println("6. Sua san pham");
 * System.out.println("7. Thoat");
 * System.out.println("=============================");
 * System.out.print("Chon: ");
 * chon = Integer.parseInt(sc.nextLine());
 * 
 * switch (chon) {
 * case 1:
 * dsSanPham.hienThiDS();
 * break;
 * case 2:
 * dsSanPham.themSanPham();
 * break;
 * case 3:
 * dsSanPham.timKiemTheoTen();
 * break;
 * case 4:
 * dsSanPham.timKiemTheoMa();
 * break;
 * case 5:
 * dsSanPham.xoaSanPham();
 * break;
 * case 6:
 * dsSanPham.suaSanPham();
 * break;
 * case 7:
 * break;
 * case 0:
 * System.out.println("Quay lai menu chinh...");
 * break;
 * default:
 * System.out.println("Lua chon khong hop le!");
 * }
 * } while (chon != 0);
 * }
 * 
 * // Quan ly nhan vien
 * public void chonQuanLyNhanVien() {
 * System.out.println("\n[Chuc nang quan ly nhan vien dang phat trien...]");
 * // tuong tu nhu quan ly san pham
 * }
 * 
 * // Quan ly khach hang
 * public void chonQuanLyKhachHang() {
 * System.out.println("\n[Chuc nang quan ly khach hang dang phat trien...]");
 * // tuong tu nhu quan ly san pham
 * }
 * 
 * // Quan ly hoa don
 * public void chonQuanLyHoaDon() {
 * int luaChon; // dung de chon menu quan ly hoa don
 * int chonMot; // dung de chon chuc nang chi tiet danh sach hoa don
 * int chonHai; // dung de chon chuc nang chi tiet phieu nhap
 * do {
 * System.out.println("\n===== QUAN LY HOA DON VA PHIEU NHAP =====");
 * System.out.println("1. Chi tiet danh sach hoa don");
 * System.out.println("2. Chi tiet phieu nhap");
 * System.out.println("0. Quay lai menu truoc");
 * System.out.println("=============================");
 * System.out.print("Moi ban nhap lua chon: ");
 * luaChon = Integer.parseInt(sc.nextLine());
 * switch (luaChon) {
 * case 1: {
 * do {
 * System.out.println("===== CHI TIET DANH SACH HOA DON =====");
 * System.out.println("1. Them hoa don");
 * System.out.println("2. Xoa hoa don");
 * System.out.println("3. Tim hoa don");
 * System.out.println("4. In tat ca hoa don");
 * System.out.println("0. Quay lai menu truoc");
 * System.out.print("Moi ban chon chuc nang chi tiet danh sach hoa don: ");
 * chonMot = Integer.parseInt(sc.nextLine());
 * switch (chonMot) {
 * case 1: {
 * DSachHD ds = new DSachHD();
 * ds.DocDuLieuNhanVien(
 * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\nhanvien.txt");
 * ds.DocDuLieuKhachHang(
 * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\khachhang.txt");
 * ds.DocDuLieuSanPham(
 * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\sanpham.txt");
 * ds.DocDuLieuTuFile(
 * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\hoadonn.txt");
 * System.out.println("\n\n========================================");
 * System.out.println("===== THEM HOA DON MOI =====");
 * System.out.println("========================================");
 * 
 * try {
 * SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 * 
 * // Lay nhan vien va khach hang tu danh sach da doc
 * NhanVien nvTest = ds.timNhanVien("NV001");
 * KhachHang khTest = ds.timKhachHang("KH001");
 * 
 * if (nvTest != null && khTest != null) {
 * // Tao hoa don moi
 * HoaDon hdMoi = new HoaDon(
 * "HD003",
 * nvTest,
 * khTest,
 * "Hoa don test",
 * "Da Nang",
 * 15,
 * sdf.parse("12/11/2025"));
 * 
 * // Them san pham vao hoa don
 * SanPham sp1 = ds.timSanPham("SP001");
 * SanPham sp2 = ds.timSanPham("SP002");
 * 
 * if (sp1 != null) {
 * hdMoi.themSanPham(sp1);
 * System.out.println("- Da them san pham: " + sp1.getTenSP());
 * }
 * if (sp2 != null) {
 * hdMoi.themSanPham(sp2);
 * System.out.println("- Da them san pham: " + sp2.getTenSP());
 * }
 * 
 * // Them hoa don vao danh sach
 * System.out.println("\n=> Dang them hoa don HD003...");
 * ds.themHoaDon(hdMoi);
 * 
 * // In danh sach sau khi them
 * System.out.println("\n===== DANH SACH SAU KHI THEM =====");
 * ds.inTatCaHoaDon();
 * System.out.println("Tong so hoa don: " + ds.getSoLuong());
 * 
 * } else {
 * System.out.println("LOI: Khong tim thay nhan vien hoac khach hang!");
 * }
 * 
 * } catch (Exception e) {
 * System.out.println("LOI khi them hoa don: " + e.getMessage());
 * e.printStackTrace();
 * }
 * break;
 * }
 * case 2: {
 * break;
 * }
 * case 3: {
 * break;
 * }
 * case 4: {
 * DSachHD ds1 = new DSachHD();
 * 
 * // Doc du lieu tu file
 * System.out.println("===== DOC DU LIEU TU FILE =====");
 * ds1.DocDuLieuNhanVien(
 * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\nhanvien.txt");
 * ds1.DocDuLieuKhachHang(
 * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\khachhang.txt");
 * ds1.DocDuLieuSanPham(
 * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\sanpham.txt");
 * ds1.DocDuLieuTuFile(
 * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\hoadonn.txt");
 * ds1.inTatCaHoaDon();
 * System.out.println("Tong so hoa don: " + ds1.getSoLuong());
 * break;
 * }
 * case 0: {
 * break;
 * }
 * default: {
 * System.out.println("Lua chon khong hop le, vui long nhap lai");
 * }
 * }
 * } while (chonMot != 0);
 * }
 * case 2: {
 * do {
 * System.out.println("===== CHI TIET PHIEU NHAP =====");
 * System.out.println("1. Xac nhan phieu nhap ");
 * System.out.println("2. Them chi tiet phieu nhap");
 * System.out.println("3. Xoa chi tiet phieu nhap");
 * System.out.println("4. Huy phieu nhap");
 * System.out.println("5. Hien thi thong tin");
 * System.out.println("6. Tong tien phieu");
 * System.out.println("0. Quay lai menu truoc");
 * System.out.print("Moi ban chon chuc nang chi tiet phieu nhap: ");
 * chonHai = Integer.parseInt(sc.nextLine());
 * switch (chonHai) {
 * case 1: {
 * break;
 * }
 * case 2: {
 * break;
 * }
 * case 3: {
 * break;
 * }
 * case 4: {
 * break;
 * }
 * case 5: {
 * break;
 * }
 * case 6: {
 * break;
 * }
 * case 0: {
 * break;
 * }
 * default: {
 * System.out.println("Lua chon khong hop le, vui long nhap lai");
 * }
 * }
 * } while (chonHai != 0);
 * }
 * }
 * } while (luaChon != 0);
 * }
 * 
 * /*
 * // Quan ly kho
 * public void chonQuanLyKho() {
 * int chon;
 * do {
 * System.out.println("\n===== QUAN LY KHO =====");
 * System.out.println("1. Xem danh sach ton kho");
 * System.out.println("2. Quan ly nguyen lieu");
 * System.out.println("3. Quan ly phieu nhap");
 * System.out.println("0. Quay lai");
 * System.out.println("=========================");
 * System.out.print("Chon: ");
 * chon = Integer.parseInt(sc.nextLine());
 * switch (chon) {
 * case 1:
 * Kho kho = new Kho();
 * kho.xemDSTonKho();
 * break;
 * case 2:
 * qlNguyenLieu();
 * break;
 * case 0:
 * System.out.println("Quay lai menu...");
 * break;
 * default:
 * System.out.println("Lua chon khong hop le!");
 * }
 * } while (chon != 0);
 * }
 * 
 * // BaoCao
 * public void chonBaoCao() {
 * System.out.println("\n[Chuc nang thong ke dang phat trien...]");
 * }
 * 
 * /*
 * public void thoat() {
 * System.out.println("\nDang luu du lieu...");
 * dsSanPham.LuuDuLieu("sanpham.txt");
 * System.out.println("Da luu. Hen gap lai!");
 * }
 * 
 * // Main chay chuong trinh
 * public static void main(String[] args) {
 * QUANLYCUAHANG ql = new QUANLYCUAHANG();
 * ql.hienThiMenu();
 * }
 * }
 */
// ngan cach 

// xung dot cua toi 
// package ThucAnNhanh_NuocGiaiKhat;

import CONNGUOI.DSachKH;
import CONNGUOI.DSachNV;
import HOADON.DSachHD;
import HOADON.HoaDon;
import HOADON.HoaDonIO;
import KHO.Kho;
import NGUYENLIEU.DSNguyenLieu;
// ngan cach 
//package ThucAnNhanh_NuocGiaiKhat;
//import CONNGUOI.DSachKH;
//import CONNGUOI.DSachNV;
//import HOADON.DSachHD;
// xung dot tu xa 
import SANPHAM.DSachSP;
import java.util.Scanner;

public class QUANLYCUAHANG {

    private DSachSP dsSanPham;
    // private DSachNV dsNhanVien;
    // private DSachKH dsKhachHang;
    // private DSachHD dsHoaDon;

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
            System.out.println("6. Bao cao");
            System.out.println("0. Thoat");
            System.out.println("==================================");
            System.out.print("Nhap lua chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    chonQuanLySanPham();
                    break;
                case 2:
                    // chonQuanLyNhanVien();
                    break;
                case 3:
                    // chonQuanLyKhachHang();
                    break;
                case 4:
                    // chonQuanLyHoaDon();
                    break;
                case 5:
                    // chonQuanLyKho();
                    break;
                case 6:
                    // chonBaoCao();
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

    /*
     * public void chonQuanLyNhanVien() {
     * System.out.println("\n[Chuc nang quan ly nhan vien dang phat trien...]");
     * 
     * }
     */

    /*
     * public void chonQuanLyKhachHang() {
     * System.out.println("\n[Chuc nang quan ly khach hang dang phat trien...]");
     * }
     */

    /*
     * public void chonQuanLyHoaDon() {
     * int luaChon;
     * int chonMot;
     * do {
     * System.out.println("\n===== QUAN LY HOA DON =====");
     * System.out.println("1. Chi tiet hoa don");
     * System.out.println("2. Chi tiet danh sach hoa don");
     * System.out.println("3. Chi tiet thanh toan");
     * System.out.println("0. Quay lai menu truoc");
     * System.out.println("=============================");
     * System.out.println("Moi ban nhap lua chon: ");
     * luaChon = Integer.parseInt(sc.nextLine());
     * switch (luaChon) {
     * case 1: {
     * do {
     * System.out.println("===== CHI TIET HOA DON =====");
     * System.out.println("4. Tinh tong tien");
     * System.out.println("5. In hoa don");
     * System.out.println("6. Quay lai menu truoc");
     * System.out.println("Moi ban chon chuc nang chi tiet hoa don: ");
     * chonMot = Integer.parseInt(sc.nextLine());
     * switch (chonMot) {
     * case 4: {
     * String filePath =
     * "E:\\BaiTapOOP\\src\\com\\example\\models\\ThucAnNhanhNuocGiaiKhat\\hoadon.txt";
     * HoaDon hoaDon = HoaDonIO.docHoaDonTuFile(filePath);
     * hoaDon.inHoaDon();
     * }
     * }
     * } while (chonMot != 0);
     * }
     * }
     * } while (luaChon != 0);
     * }
     */
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
                /*
                 * case 1:
                 * Kho kho = new Kho();
                 * // xung dot cua toi
                 * kho.docFile();
                 * kho.hienThiTonKho();
                 * break;
                 * // ngan cach
                 * kho.xemDSTonKho();
                 * break;
                 */
                // xung dot tu xa
                case 2:
                    // qlNguyenLieu();
                    break;
                case 0:
                    System.out.println("Quay lai menu...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    // Quan ly nguyen lieu
    /*
     * public void qlNguyenLieu() {
     * DSNguyenLieu dsNguyenLieu = new DSNguyenLieu();
     * int chon;
     * do {
     * System.out.println("\n======== Quan ly nguyen lieu ========");
     * System.out.println("1. Xem danh sach nguyen lieu");
     * System.out.println("2. Them nguyen lieu moi.");
     * System.out.println("3. Sua thong tin nguyen lieu");
     * System.out.println("4. Xoa nguyen lieu.");
     * System.out.println("0. Quay lai");
     * System.out.println("======================================");
     * System.out.print("Chon: ");
     * chon = Integer.parseInt(sc.nextLine());
     * switch (chon) {
     * case 1:
     * dsNguyenLieu.docFile();
     * break;
     * case 2:
     * dsNguyenLieu.AddNL();
     * dsNguyenLieu.ghiFile();
     * break;
     * case 3:
     * dsNguyenLieu.suaNguyenLieu();
     * case 4:
     * dsNguyenLieu.xoaNguyenLieu();
     * break;
     * case 0:
     * System.out.println("Quay lai");
     * break;
     * default:
     * System.out.println("Lua chon khong hop le");
     * }
     * }while(chon != 0);
     * <<<<<<< HEAD
     * }
     * =======
     * }
     */

    /*
     * public void chonBaoCao() {
     * System.out.println("\n[Chuc nang thong ke dang phat trien...]");
     * }
     */
    public static void main(String[] args) {
        QUANLYCUAHANG ql = new QUANLYCUAHANG();
        ql.hienThiMenu();
    }
}
