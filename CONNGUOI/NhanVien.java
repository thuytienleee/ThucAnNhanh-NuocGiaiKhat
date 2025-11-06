// package CONNGUOI;
package com.example.models.ThucAnNhanhNuocGiaiKhat.CONNGUOI;

import java.util.Date;

public class NhanVien {
    private String maNV;
    private String tenNV;
    private int tuoiNV;
    private Date ngaySinh;
    private int sdtNV;
    private String diaChi;
    private double luong;
    private String chucVuNV;
    private int namVaoLam;

    public NhanVien(){}
    
    public NhanVien(String maNV, String tenNV, int tuoiNV, Date ngaySinh, int sdtNV, String diaChi, double luong, String chucVuNV, int namVaoLam){
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tuoiNV = tuoiNV;
        this.ngaySinh = ngaySinh;
        this.sdtNV = sdtNV;
        this.diaChi = diaChi;
        this.luong = luong;
        this.chucVuNV = chucVuNV;
        this.namVaoLam = namVaoLam;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public int getTuoiNV() {
        return tuoiNV;
    }

    public void setTuoiNV(int tuoiNV) {
        this.tuoiNV = tuoiNV;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getSdtNV() {
        return sdtNV;
    }

    public void setSdtNV(int sdtNV) {
        this.sdtNV = sdtNV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public String getChucVuNV() {
        return chucVuNV;
    }

    public void setChucVuNV(String chucVuNV) {
        this.chucVuNV = chucVuNV;
    }

    public int getNamVaoLam() {
        return namVaoLam;
    }

    public void setNamVaoLam(int namVaoLam) {
        this.namVaoLam = namVaoLam;
    }
    
    public String toString(){
        return String.format(" ", maNV, tenNV, tuoiNV, ngaySinh, sdtNV, diaChi, luong, chucVuNV, namVaoLam);
    }

    public void hienThiThongTinNhanVien(){
        System.out.println("==================THONG TIN NHAN VIEN========================");
        toString();
        System.out.println("=============================================================");
    }
}
