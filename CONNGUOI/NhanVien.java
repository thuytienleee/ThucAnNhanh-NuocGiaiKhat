// package CONNGUOI;
package CONNGUOI;

// import java.util.Date;

import java.util.Date;


public class NhanVien {
    private String maNV;
    private String tenNV;
    private int tuoiNV;
    private String gioitinh;
    private Date ngaySinh;
    private String sdtNV;
    private String diaChiNV;
    private double luong;
    private String chucVuNV;
    private int namVaoLam;

    public NhanVien() {}

    public NhanVien(String maNV, String tenNV, int tuoiNV, String gioitinh, Date ngaySinh, String sdtNV, 
                    String diaChiNV, double luong, String chucVuNV, int namVaoLam) {

        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tuoiNV = tuoiNV;
        this.gioitinh = gioitinh;
        this.ngaySinh = ngaySinh;
        this.sdtNV = sdtNV;
        this.diaChiNV = diaChiNV;
        this.luong = luong;
        this.chucVuNV = chucVuNV;
        this.namVaoLam = namVaoLam;

    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
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

    public String getSdtNV() {
        return sdtNV;
    }

    public void setSdtNV(String sdtNV) {
        this.sdtNV = sdtNV;
    }

    public String getDiaChiNV() {
        return diaChiNV;
    }

    public void setDiaChiNV(String diaChiNV) {
        this.diaChiNV = diaChiNV;
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
        return String.format("%-5s | %-20s | %-3d | %-5s | %tF | %-12s | %-12d | %-10s | %-10.1f | %-15s",
        maNV, tenNV, tuoiNV, gioitinh, ngaySinh, sdtNV, namVaoLam, chucVuNV, luong, diaChiNV);
    }
}