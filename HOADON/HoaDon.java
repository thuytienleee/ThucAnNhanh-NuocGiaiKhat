// package HOADON;
// import SANPHAM.SanPham;
// import CONNGUOI.NhanVien;
// import CONNGUOI.KhachHang;
package com.example.models.ThucAnNhanhNuocGiaiKhat.HOADON;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.example.models.ThucAnNhanhNuocGiaiKhat.CONNGUOI.NhanVien;
import com.example.models.ThucAnNhanhNuocGiaiKhat.CONNGUOI.KhachHang;
import com.example.models.ThucAnNhanhNuocGiaiKhat.SANPHAM.SanPham;

public class HoaDon {
    private String maHoaDon;
    private String tenHoaDon;
    private NhanVien maNV;
    private NhanVien tenNV;
    private KhachHang maKH;
    private String diaChi;
    private Date ngayLap;
    private int diemTichLuy;
    private double tinhTongTien;
    private SanPham[] dsChiTiet;
    private static int soHoaDon = 0;

    // Constructor
    public HoaDon(String maHoaDon, NhanVien maNV, KhachHang maKH, String tenHoaDon, NhanVien tenNV,
                  String diaChi, Date ngayLap, int diemTichLuy, double tinhTongTien, SanPham[] dsChiTiet) {
        this.maHoaDon = maHoaDon;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.maKH = maKH;
        this.tenHoaDon = tenHoaDon;
        this.diaChi = diaChi;
        this.diemTichLuy = diemTichLuy;
        this.ngayLap = ngayLap;
        this.dsChiTiet = dsChiTiet;
        this.tinhTongTien = tinhTongTien();
        soHoaDon++;
    }

    // Getter, Setter

    public String getMaHoaDon(){
        return maHoaDon;
    }
    public void setMaHoaDon(String maHoaDon){
        this.maHoaDon = maHoaDon;
    }
    public NhanVien getMaNV(){
        return maNV;
    }
    public void setMaNV(NhanVien maNV){
        this.maNV = maNV;
    }
    public NhanVien getTenNV(){
        return tenNV;
    }
    public void setTenNV(NhanVien tenNV){
        this.tenNV = tenNV;
    }
    public KhachHang getMaKH(){
        return maKH;
    }
    public void setMaKhachHang(KhachHang maKH){
        this.maKH = maKH;
    }
    public String getTenHoaDon(){
        return tenHoaDon;
    }
    public void setTenHoaDon(String tenHoaDon){
        this.tenHoaDon = tenHoaDon;
    }
    public String getDiaChi(){
        return diaChi;
    }
    public void setDiaChi(String diaChi){
        this.diaChi = diaChi;
    }
    public int getDiemTichLuy(){
        return diemTichLuy;
    }
    public void setDiemTichLuy(int diemTichLuy){
        this.diemTichLuy = diemTichLuy;
    }

    // Tinh tong tien
    public double tinhTongTien() {
        double tong = 0;
        for (int i = 0; i < dsChiTiet.length; i++) {
            if (dsChiTiet[i] != null) {
                tong += dsChiTiet[i].tinhTien();
            }
        }
        return tong;
    }

    // Lay so hoa don hien tai
    public static int laySoHoaDon() {
        return soHoaDon;
    }

    // In hoa don
    public void inHoaDon() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("===== HOA DON =====");
        System.out.println("Ma hoa don: " + maHoaDon);
        System.out.println("Ten hoa don: " + tenHoaDon);
        System.out.println("Ngay lap: " + sdf.format(ngayLap));
        System.out.println("Ma nhan vien: " + maNV);
        System.out.println("Ma khach hang: " + maKH);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("Diem tich luy: " + diemTichLuy);
        System.out.println("Danh sach san pham:");
        for (int i = 0; i < dsChiTiet.length; i++) {
            if (dsChiTiet[i] != null) {
                System.out.println(" - " + dsChiTiet[i]);
            }
        }
        System.out.println("Tong tien: " + tinhTongTien + " VND");
        System.out.println("===================");
    }
/* 
    // Doc du lieu tu file
    public static void DocDuLieuTuFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Du lieu tu file:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    // Ghi du lieu ra file
    public static void GhiDuLieuLenFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write("So hoa don hien tai: " + soHoaDon);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }
    */
            
}
