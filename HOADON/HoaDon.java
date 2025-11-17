
package HOADON;

import java.text.SimpleDateFormat;
import java.util.Date;
import CONNGUOI.NhanVien;
import CONNGUOI.KhachHang;
import SANPHAM.SanPham;

public class HoaDon {
    private String maHoaDon;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private String tenHoaDon;
    private String diaChi;
    private int diemTichLuy;
    private Date ngayLap;
    private double tongTien;
    private int soHoaDon;
    private SanPham[] dsChiTiet;
    private int[] soLuongSP;

    public HoaDon() {
        dsChiTiet = new SanPham[100];
        soHoaDon = 0;
        soLuongSP = new int[100];
    }

    public HoaDon(String maHoaDon, NhanVien nhanVien, KhachHang khachHang,
            String tenHoaDon, String diaChi, int diemTichLuy, Date ngayLap) {
        this.maHoaDon = maHoaDon;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.tenHoaDon = tenHoaDon;
        this.diaChi = diaChi;
        this.diemTichLuy = diemTichLuy;
        this.ngayLap = ngayLap;
        this.dsChiTiet = new SanPham[100];
        this.soHoaDon = 0;
        this.tongTien = 0;
        this.soLuongSP = new int[100];
    }

    // Getters và Setters
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public String getTenHoaDon() {
        return tenHoaDon;
    }

    public void setTenHoaDon(String tenHoaDon) {
        this.tenHoaDon = tenHoaDon;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public SanPham[] getDsChiTiet() {
        return dsChiTiet;
    }

    public int getSoHoaDon() {
        return soHoaDon;
    }

    public int[] getSoLuongSP() {
        return soLuongSP;
    }

    public void themSanPham(SanPham sp, int soLuong) {
        if (soHoaDon < dsChiTiet.length) {
            dsChiTiet[soHoaDon] = sp;
            soLuongSP[soHoaDon] = soLuong;
            soHoaDon++;
        }
    }

    public void themSanPham(SanPham sp) {
        themSanPham(sp, 1);
    }

    public double tinhTongTien() {
        tongTien = 0;
        for (int i = 0; i < soHoaDon; i++) {
            tongTien += dsChiTiet[i].getGia() * soLuongSP[i];
        }
        return tongTien;
    }

    public void inHoaDon() {
        System.out.println("\n========== HOA DON ==========");
        System.out.println("Ma hoa don: " + maHoaDon);
        System.out.println("Ten hoa don: " + tenHoaDon);
        System.out.println("Nhan vien: " + (nhanVien != null ? nhanVien.getTenNV() : "N/A"));
        System.out.println("Khach hang: " + (khachHang != null ? khachHang.getTenKH() : "N/A"));
        System.out.println("Dia chi: " + diaChi);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ngay lap: " + sdf.format(ngayLap));
        System.out.println("Diem tich luy: " + diemTichLuy);
        System.out.println("\n--- Chi tiet san pham ---");
        for (int i = 0; i < soHoaDon; i++) {
            double thanhTien = dsChiTiet[i].getGia() * soLuongSP[i];
            System.out.println((i + 1) + ". " + dsChiTiet[i].getTenSP()
                    + " - SL: " + soLuongSP[i]
                    + " - Gia: " + dsChiTiet[i].getGia() + " VND"
                    + " - Thanh tien: " + thanhTien + " VND");
        }
        System.out.println("\nTong tien: " + tinhTongTien() + " VND");
        System.out.println("=============================\n");
    }

    public int laySoHoaDon() {
        return soHoaDon;
    }
}
