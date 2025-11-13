
<<<<<<< HEAD
// package ThucAnNhanh_NuocGiaiKhat;

import CONNGUOI.DSachKH;
import CONNGUOI.DSachNV;
import HOADON.DSachHD;
import HOADON.HoaDon;
import HOADON.HoaDonIO;
import KHO.Kho;
import NGUYENLIEU.DSNguyenLieu;
=======
//package ThucAnNhanh_NuocGiaiKhat;
//import CONNGUOI.DSachKH;
//import CONNGUOI.DSachNV;
//import HOADON.DSachHD;
>>>>>>> 29dce642c885025583de4bea107430705be0abda
import SANPHAM.DSachSP;
import java.util.Scanner;

public class QUANLYCUAHANG {

    private DSachSP dsSanPham;
    //private DSachNV dsNhanVien;
    //private DSachKH dsKhachHang;
    //private DSachHD dsHoaDon;

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
                    //chonQuanLyNhanVien();
                    break;
                case 3:
                    //chonQuanLyKhachHang();
                    break;
                case 4:
                    //chonQuanLyHoaDon();
                    break;
                case 5:
                    //chonQuanLyKho();
                    break;
                case 6:
                    //chonBaoCao();
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

    /*public void chonQuanLyNhanVien() {
        System.out.println("\n[Chuc nang quan ly nhan vien dang phat trien...]");
   
    } */

 /*public void chonQuanLyKhachHang() {
        System.out.println("\n[Chuc nang quan ly khach hang dang phat trien...]");
    }*/

 /*public void chonQuanLyHoaDon() {
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
                                String filePath = "E:\\BaiTapOOP\\src\\com\\example\\models\\ThucAnNhanhNuocGiaiKhat\\hoadon.txt";
                                HoaDon hoaDon = HoaDonIO.docHoaDonTuFile(filePath);
                                hoaDon.inHoaDon();
                            }
                        }
                    } while (chonMot != 0);
                }
            }
        } while (luaChon != 0);
    }*/
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
                /*case 1:
                    Kho kho = new Kho();
<<<<<<< HEAD
                    kho.docFile();
                    kho.hienThiTonKho();
                    break;
=======
                    kho.xemDSTonKho();
                    break;*/
>>>>>>> 29dce642c885025583de4bea107430705be0abda
                case 2:
                    //qlNguyenLieu();
                    break;
                case 0:
                    System.out.println("Quay lai menu...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    //Quan ly nguyen lieu
    /*public void qlNguyenLieu() {
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
            case 4:
                dsNguyenLieu.xoaNguyenLieu();
                break;
            case 0:
                System.out.println("Quay lai");
                break;
            default:
                System.out.println("Lua chon khong hop le");
            } 
        }while(chon != 0);
<<<<<<< HEAD
    }
=======
    }*/
>>>>>>> 29dce642c885025583de4bea107430705be0abda

 /* public void chonBaoCao() {
        System.out.println("\n[Chuc nang thong ke dang phat trien...]");
    } */
    public static void main(String[] args) {
        QUANLYCUAHANG ql = new QUANLYCUAHANG();
        ql.hienThiMenu();
    }
}
