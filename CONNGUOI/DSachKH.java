// package CONNGUOI;
package com.example.models.ThucAnNhanhNuocGiaiKhat.CONNGUOI;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.util.Scanner;

public class DSachKH {
    private KhachHang[] danhSachKH;
    private int soLuongKH;

    public DSachKH() {
        danhSachKH = new KhachHang[100]; // khoi tao mang voi kich thuoc co dinh
        soLuongKH = 0;
    }

    // Them khach hang
    public void themKH(KhachHang kh) {
        if (soLuongKH < danhSachKH.length) {
            danhSachKH[soLuongKH++] = kh;
        } else {
            System.out.println("Danh sach da day, khong the them khach hang.");
        }
    }

    // Xoa khach hang theo ma
    public void xoaKH(String maKH) {
        for (int i = 0; i < soLuongKH; i++) {
            if (danhSachKH[i].getMaKH().equals(maKH)) {
                for (int j = i; j < soLuongKH - 1; j++) {
                    danhSachKH[j] = danhSachKH[j + 1];
                }
                danhSachKH[--soLuongKH] = null;
                System.out.println("Da xoa khach hang co ma: " + maKH);
                return;
            }
        }
        System.out.println("Khong tim thay khach hang co ma: " + maKH);
    }

    // Thay doi thong tin khach hang theo ma
    public void thayDoiThongTinKH(String maKH, KhachHang khMoi) {
        for (int i = 0; i < soLuongKH; i++) {
            if (danhSachKH[i].getMaKH().equals(maKH)) {
                danhSachKH[i] = khMoi;
                System.out.println("Da cap nhat thong tin khach hang co ma: " + maKH);
                return;
            }
        }
        System.out.println("Khong tim thay khach hang de cap nhat.");
    }

    // Tim kiem khach hang theo ma
    public void timKiemKHTheoMa(String maKH) {
        for (int i = 0; i < soLuongKH; i++) {
            if (danhSachKH[i].getMaKH().equals(maKH)) {
                System.out.println(danhSachKH[i]);
                return;
            }
        }
        System.out.println("Khong tim thay khach hang co ma: " + maKH);
    }

    // Tim kiem khach hang theo ten
    public void timKiemKHTheoTen(String ten) {
        boolean timThay = false;
        for (int i = 0; i < soLuongKH; i++) {
            if (danhSachKH[i].getTen().equalsIgnoreCase(ten)) {
                System.out.println(danhSachKH[i]);
                timThay = true;
            }
        }
        if (!timThay) {
            System.out.println("Khong tim thay khach hang co ten: " + ten);
        }
    }

    // In toan bo danh sach khach hang
    public void inDanhSachKH() {
        for (int i = 0; i < soLuongKH; i++) {
            System.out.println(danhSachKH[i]);
        }
    }
}

