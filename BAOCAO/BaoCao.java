// package BAOCAO;
package com.example.models.ThucAnNhanhNuocGiaiKhat.BAOCAO;

import java.util.Arrays;

public class BaoCao {
    private String IDBaoCao;
    private String IDNhanVien;
    private String reportDay;
    private String ngayBatDau;
    private String ngayKetThuc;
    private int tongSoHD;
    private int tongDoanhThu;
    private int SLKhachMoi;
    private String[] IDSanPhamArr;
    private int[] SLSanPham;

    public BaoCao() {}

    public BaoCao(String IDBaoCao, String IDNhanVien, String reportDay, String ngayBatDau,
                  String ngayKetThuc, int tongSoHD, int tongDoanhThu, int SLKhachMoi,
                  String[] IDSanPhamArr, int[] SLSanPham) {
        this.IDBaoCao = IDBaoCao;
        this.IDNhanVien = IDNhanVien;
        this.reportDay = reportDay;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tongSoHD = tongSoHD;
        this.tongDoanhThu = tongDoanhThu;
        this.SLKhachMoi = SLKhachMoi;
        this.IDSanPhamArr = IDSanPhamArr;
        this.SLSanPham = SLSanPham;
    }

    public String getIDBaoCao() { return IDBaoCao; }
    public String getIDNhanVien() { return IDNhanVien; }
    public String getReportDay() { return reportDay; }
    public String getNgayBatDau() { return ngayBatDau; }
    public String getNgayKetThuc() { return ngayKetThuc; }
    public int getTongSoHD() { return tongSoHD; }
    public int getTongDoanhThu() { return tongDoanhThu; }
    public int getSLKhachMoi() { return SLKhachMoi; }
    public String[] getIDSanPhamArr() { return IDSanPhamArr; }
    public int[] getSLSanPham() { return SLSanPham; }

    public void setIDBaoCao(String IDBaoCao) { this.IDBaoCao = IDBaoCao; }
    public void setIDNhanVien(String IDNhanVien) { this.IDNhanVien = IDNhanVien; }
    public void setReportDay(String reportDay) { this.reportDay = reportDay; }
    public void setNgayBatDau(String ngayBatDau) { this.ngayBatDau = ngayBatDau; }
    public void setNgayKetThuc(String ngayKetThuc) { this.ngayKetThuc = ngayKetThuc; }
    public void setTongSoHD(int tongSoHD) { this.tongSoHD = tongSoHD; }
    public void setTongDoanhThu(int tongDoanhThu) { this.tongDoanhThu = tongDoanhThu; }
    public void setSLKhachMoi(int SLKhachMoi) { this.SLKhachMoi = SLKhachMoi; }
    public void setIDSanPhamArr(String[] IDSanPhamArr) { this.IDSanPhamArr = IDSanPhamArr; }
    public void setSLSanPham(int[] SLSanPham) { this.SLSanPham = SLSanPham; }

    public void printReport() {
        System.out.println("=========== BAO CAO KINH DOANH ===========");
        System.out.println("ID Bao cao: " + IDBaoCao);
        System.out.println("Nhan vien: " + IDNhanVien);
        System.out.println("Ngay lap: " + reportDay);
        System.out.println("Tu ngay: " + ngayBatDau + "  den: " + ngayKetThuc);
        System.out.println("Tong so HD: " + tongSoHD);
        System.out.println("Tong doanh thu: " + tongDoanhThu);
        System.out.println("So luong khach moi: " + SLKhachMoi);
        System.out.println("Danh sach san pham:");
        for (int i = 0; i < IDSanPhamArr.length; i++) {
            System.out.printf(" - %s: %d san pham%n", IDSanPhamArr[i], SLSanPham[i]);
        }
        System.out.println("==========================================");
    }

    @Override
    public String toString() {
        return IDBaoCao + ";" + IDNhanVien + ";" + reportDay + ";" + ngayBatDau + ";" + ngayKetThuc + ";" +
               tongSoHD + ";" + tongDoanhThu + ";" + SLKhachMoi + ";" +
               Arrays.toString(IDSanPhamArr) + ";" + Arrays.toString(SLSanPham);
    }

}
