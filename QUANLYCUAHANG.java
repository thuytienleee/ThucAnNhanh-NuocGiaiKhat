// package ThucAnNhanh_NuocGiaiKhat;
package com.example.models.ThucAnNhanhNuocGiaiKhat;

import java.util.Scanner;
import java.io.*;

public class QUANLYCUAHANG {
    private DSachSP dsSanPham;
    private DSachNV dsNhanVien;
    private DSachKH dsKhachHang;
    private DSachHD dsHoaDon;

    private Scanner sc = new Scanner(System.in);

    // ✅ Constructor
    public QUANLYCUAHANG() {
        dsSanPham = new DSachSP(100);  // mảng 100 sản phẩm
        dsNhanVien = new DSachNV(50);  // mảng 50 nhân viên
        dsKhachHang = new DSachKH(100);
        dsHoaDon = new DSachHD(100);
    }

    // ✅ Hàm hiển thị menu chính
    public void hienThiMenu() {
        int chon;
        do {
            System.out.println("\n===== MENU QUẢN LÝ CỬA HÀNG =====");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Quản lý khách hàng");
            System.out.println("4. Quản lý hóa đơn");
            System.out.println("5. Quản lý khuyến mãi");
            System.out.println("6. Thống kê doanh thu");
            System.out.println("0. Thoát");
            System.out.print("👉 Nhập lựa chọn: ");
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
                    chonQuanLyKhuyenMai();
                    break;
                case 6:
                    chonThongKe();
                    break;
                case 0:
                    thoat();
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);
    }

    // ✅ Quản lý sản phẩm
    public void chonQuanLySanPham() {
        int chon;
        do {
            System.out.println("\n===== QUẢN LÝ SẢN PHẨM =====");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Tìm kiếm sản phẩm");
            System.out.println("4. Sửa sản phẩm");
            System.out.println("5. Xóa sản phẩm");
            System.out.println("6. Lưu dữ liệu ra file");
            System.out.println("7. Đọc dữ liệu từ file");
            System.out.println("0. Quay lại");
            System.out.print("👉 Chọn: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    dsSanPham.themSanPhamTuBanPhim();
                    break;
                case 2:
                    dsSanPham.hienThiDS();
                    break;
                case 3:
                    dsSanPham.timKiemTuBanPhim();
                    break;
                case 4:
                    dsSanPham.suaSanPhamTuBanPhim();
                    break;
                case 5:
                    dsSanPham.xoaSanPhamTuBanPhim();
                    break;
                case 6:
                    dsSanPham.LuuDuLieu("sanpham.txt");
                    break;
                case 7:
                    dsSanPham.DocDuLieuTuFile("sanpham.txt");
                    break;
                case 0:
                    System.out.println("⬅ Quay lại menu chính...");
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);
    }

    // ✅ Quản lý nhân viên
    public void chonQuanLyNhanVien() {
        System.out.println("\n[Chức năng quản lý nhân viên đang phát triển...]");
        // tương tự như quản lý sản phẩm
    }

    // ✅ Quản lý khách hàng
    public void chonQuanLyKhachHang() {
        System.out.println("\n[Chức năng quản lý khách hàng đang phát triển...]");
        // tương tự như quản lý sản phẩm
    }

    // ✅ Quản lý hóa đơn
    public void chonQuanLyHoaDon() {
        System.out.println("\n[Chức năng quản lý hóa đơn đang phát triển...]");
        // có thể thêm, hiển thị, tìm kiếm hóa đơn,...
    }

    // ✅ Quản lý khuyến mãi
    public void chonQuanLyKhuyenMai() {
        System.out.println("\n[Chức năng quản lý khuyến mãi đang phát triển...]");
    }

    // ✅ Thống kê doanh thu
    public void chonThongKe() {
        System.out.println("\n[Chức năng thống kê đang phát triển...]");
    }

    // ✅ Thoát chương trình
    public void thoat() {
        System.out.println("\n💾 Đang lưu dữ liệu...");
        dsSanPham.LuuDuLieu("sanpham.txt");
        System.out.println("✅ Đã lưu. Hẹn gặp lại!");
    }

    // ✅ Main chạy chương trình
    public static void main(String[] args) {
        QUANLYCUAHANG ql = new QUANLYCUAHANG();
        ql.hienThiMenu();
    }
}
                                                                                                                                                                                                                 