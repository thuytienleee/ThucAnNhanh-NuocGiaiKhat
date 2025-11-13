
package CONNGUOI;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    // Doc DSKH
    public void docDS() {
        try (BufferedReader br = new BufferedReader(new FileReader(
                "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\khachhang.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // bỏ qua dòng trống
                if (line.trim().isEmpty())
                    continue;

                // Tách dữ liệu theo dấu '|'
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String maKH = parts[0].trim();
                    String ten = parts[1].trim();
                    String soDT = parts[2].trim();

                    // Các dữ liệu khác tạm để trống
                    String diaChi = "Chua cap nhat";
                    String ngaySinh = "Chua cap nhat";
                    String gioiTinh = "Chua cap nhat";

                    KhachHang kh = new KhachHang(maKH, ten, diaChi, soDT, ngaySinh, gioiTinh);
                    themKH(kh);
                }
            }
            System.out.println(" Da doc danh sach khach hang tu file thanh cong!");
        } catch (IOException e) {
            System.out.println(" Loi khi doc file: " + e.getMessage());
        }
    }

    public KhachHang timKiemKH(String maKH) {
        for (int i = 0; i < soLuongKH; i++) {
            if (danhSachKH[i].getMaKH().equals(maKH)) {
                return danhSachKH[i];
            }
        }
        return null;
    }
}
