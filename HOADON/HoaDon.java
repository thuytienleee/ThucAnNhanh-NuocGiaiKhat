/*
 * 
 * // package HOADON;
 * // import SANPHAM.SanPham;
 * // import CONNGUOI.NhanVien;
 * // import CONNGUOI.KhachHang;
 * package HOADON;
 * import java.text.SimpleDateFormat;
 * import java.util.Date;
 * import CONNGUOI.NhanVien;
 * import CONNGUOI.KhachHang;
 * import SANPHAM.SanPham;
 * 
 * public class HoaDon {
 * private String maHoaDon;
 * private String tenHoaDon;
 * private NhanVien maNV;
 * private NhanVien tenNV;
 * private KhachHang maKH;
 * private String diaChi;
 * private Date ngayLap;
 * private int diemTichLuy;
 * private double tinhTongTien;
 * private SanPham[] dsChiTiet;
 * private static int soHoaDon = 0;
 * 
 * // Constructor
 * public HoaDon(String maHoaDon, NhanVien maNV, KhachHang maKH, String
 * tenHoaDon, NhanVien tenNV,
 * String diaChi, Date ngayLap, int diemTichLuy, double tinhTongTien, SanPham[]
 * dsChiTiet) {
 * this.maHoaDon = maHoaDon;
 * this.maNV = maNV;
 * this.tenNV = tenNV;
 * this.maKH = maKH;
 * this.tenHoaDon = tenHoaDon;
 * this.diaChi = diaChi;
 * this.diemTichLuy = diemTichLuy;
 * this.ngayLap = ngayLap;
 * this.dsChiTiet = dsChiTiet;
 * this.tinhTongTien = tinhTongTien();
 * soHoaDon++;
 * }
 * 
 * // Getter, Setter
 * 
 * public String getMaHoaDon(){
 * return maHoaDon;
 * }
 * public void setMaHoaDon(String maHoaDon){
 * this.maHoaDon = maHoaDon;
 * }
 * public NhanVien getMaNV(){
 * return maNV;
 * }
 * public void setMaNV(NhanVien maNV){
 * this.maNV = maNV;
 * }
 * public NhanVien getTenNV(){
 * return tenNV;
 * }
 * public void setTenNV(NhanVien tenNV){
 * this.tenNV = tenNV;
 * }
 * public KhachHang getMaKH(){
 * return maKH;
 * }
 * public void setMaKhachHang(KhachHang maKH){
 * this.maKH = maKH;
 * }
 * public String getTenHoaDon(){
 * return tenHoaDon;
 * }
 * public void setTenHoaDon(String tenHoaDon){
 * this.tenHoaDon = tenHoaDon;
 * }
 * public String getDiaChi(){
 * return diaChi;
 * }
 * public void setDiaChi(String diaChi){
 * this.diaChi = diaChi;
 * }
 * public int getDiemTichLuy(){
 * return diemTichLuy;
 * }
 * public void setDiemTichLuy(int diemTichLuy){
 * this.diemTichLuy = diemTichLuy;
 * }
 * 
 * // Tinh tong tien
 * public double tinhTongTien() {
 * double tong = 0;
 * for (int i = 0; i < dsChiTiet.length; i++) {
 * if (dsChiTiet[i] != null) {
 * tong += dsChiTiet[i].tinhTien();
 * }
 * }
 * return tong;
 * }
 * 
 * // Lay so hoa don hien tai
 * public static int laySoHoaDon() {
 * return soHoaDon;
 * }
 * 
 * // In hoa don
 * public void inHoaDon() {
 * SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 * System.out.println("===== HOA DON =====");
 * System.out.println("Ma hoa don: " + maHoaDon);
 * System.out.println("Ten hoa don: " + tenHoaDon);
 * System.out.println("Ngay lap: " + sdf.format(ngayLap));
 * System.out.println("Ma nhan vien: " + maNV);
 * System.out.println("Ma khach hang: " + maKH);
 * System.out.println("Dia chi: " + diaChi);
 * System.out.println("Diem tich luy: " + diemTichLuy);
 * System.out.println("Danh sach san pham:");
 * for (int i = 0; i < dsChiTiet.length; i++) {
 * if (dsChiTiet[i] != null) {
 * System.out.println(" - " + dsChiTiet[i]);
 * }
 * }
 * System.out.println("Tong tien: " + tinhTongTien + " VND");
 * System.out.println("===================");
 * }
 * 
 * }
 */

/* 
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

    public HoaDon() {
        dsChiTiet = new SanPham[100];
        soHoaDon = 0;
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
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void themSanPham(SanPham sp) {
        if (soHoaDon < dsChiTiet.length) {
            dsChiTiet[soHoaDon++] = sp;
        }
    }

    public double tinhTongTien() {
        tongTien = 0;
        for (int i = 0; i < soHoaDon; i++) {
            tongTien += dsChiTiet[i].getGia();
        }
        return tongTien;
    }

    public void inHoaDon() {
        System.out.println("\n========== HOA DON ==========");
        System.out.println("Ma hoa don: " + maHoaDon);
        System.out.println("Ten hoa don: " + tenHoaDon);
        System.out.println("Nhan vien: " + (nhanVien != null ? nhanVien.getTen() : "N/A"));
        System.out.println("Khach hang: " + (khachHang != null ? khachHang.getTen() : "N/A"));
        System.out.println("Dia chi: " + diaChi);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ngay lap: " + sdf.format(ngayLap));
        System.out.println("Diem tich luy: " + diemTichLuy);
        System.out.println("\n--- Chi tiet san pham ---");
        for (int i = 0; i < soHoaDon; i++) {
            System.out.println((i + 1) + ". " + dsChiTiet[i].getTenSP() + " - " + dsChiTiet[i].getGia() + " VND");
        }
        System.out.println("\nTong tien: " + tinhTongTien() + " VND");
        System.out.println("=============================\n");
    }

    public int laySoHoaDon() {
        return soHoaDon;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("HOADON,").append(maHoaDon).append(",")
                .append(nhanVien.getMaNV()).append(",")
                .append(khachHang.getMaKH()).append(",")
                .append(tenHoaDon).append(",")
                .append(diaChi).append(",")
                .append(diemTichLuy).append(",")
                .append(sdf.format(ngayLap)).append(",")
                .append(soHoaDon);

        for (int i = 0; i < soHoaDon; i++) {
            sb.append(",").append(dsChiTiet[i].getMaSP());
        }

        return sb.toString();
    }
}

*/

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

    public HoaDon() {
        dsChiTiet = new SanPham[100];
        soHoaDon = 0;
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
    }

    // Getters và Setters bổ sung
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

    // Thêm sản phẩm vào hóa đơn
    public void themSanPham(SanPham sp) {
        if (soHoaDon < dsChiTiet.length) {
            dsChiTiet[soHoaDon++] = sp;
        }
    }

    public double tinhTongTien() {
        tongTien = 0;
        for (int i = 0; i < soHoaDon; i++) {
            tongTien += dsChiTiet[i].getGia();
        }
        return tongTien;
    }

    public void inHoaDon() {
        System.out.println("\n========== HOA DON ==========");
        System.out.println("Ma hoa don: " + maHoaDon);
        System.out.println("Ten hoa don: " + tenHoaDon);
        System.out.println("Nhan vien: " + (nhanVien != null ? nhanVien.getTen() : "N/A"));
        System.out.println("Khach hang: " + (khachHang != null ? khachHang.getTen() : "N/A"));
        System.out.println("Dia chi: " + diaChi);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ngay lap: " + sdf.format(ngayLap));
        System.out.println("Diem tich luy: " + diemTichLuy);
        System.out.println("\n--- Chi tiet san pham ---");
        for (int i = 0; i < soHoaDon; i++) {
            System.out.println((i + 1) + ". " + dsChiTiet[i].getTenSP() + " - " + dsChiTiet[i].getGia() + " VND");
        }
        System.out.println("\nTong tien: " + tinhTongTien() + " VND");
        System.out.println("=============================\n");
    }

    public int laySoHoaDon() {
        return soHoaDon;
    }
}
