// package CONGTHUC;
package com.example.models.ThucAnNhanhNuocGiaiKhat.CONGTHUC;
import com.example.models.ThucAnNhanhNuocGiaiKhat.NGUYENLIEU.NguyenLieu;
// import com.example.models.ThucAnNhanhNuocGiaiKhat.NGUYENLIEU.DSNguyenLieu;
// import com.example.models.ThucAnNhanhNuocGiaiKhat.KHO.Kho;

public class CongThuc {
    private String maCongThuc;
    private String tenCongThuc;
    private String moTa;
    private String loai;
    private int phienBan;
    private int thoiGianCheBien;
    private NguyenLieu[] dsNguyenLieu;
    private int soNguyenLieu;

    public CongThuc(String maCT, String tenCT, String moTa, String loai, int phienBan, int thoiGian) {
        this.maCongThuc = maCT;
        this.tenCongThuc = tenCT;
        this.moTa = moTa;
        this.loai = loai;
        this.phienBan = phienBan;
        this.thoiGianCheBien = thoiGian;
        this.dsNguyenLieu = new NguyenLieu[20]; // Gioi han toi da 20 nguyen lieu
        this.soNguyenLieu = 0;
    }

    public boolean themNguyenLieu(NguyenLieu nl) {
        if (soNguyenLieu < dsNguyenLieu.length) {
            dsNguyenLieu[soNguyenLieu++] = nl;
            return true;
        }
        return false;
    }
/* 
    public boolean capNhatCongThuc(String maCTMoi, String tenNL, double dinhLuongMoi, int soLuongSP) {
        for (int i = 0; i < soNguyenLieu; i++) {
            if (dsNguyenLieu[i].getTenNL().equals(tenNL)) {
                dsNguyenLieu[i].setDinhLuong(dinhLuongMoi);
                this.maCongThuc = maCTMoi;
                return true;
            }
        }
        return false;
    }

    public boolean kiemTraKho(Kho kho) {
        // Gia su lop Kho co phuong thuc kiem tra ton kho nguyen lieu
        for (int i = 0; i < soNguyenLieu; i++) {
            if (!kho.coDuNguyenLieu(dsNguyenLieu[i])) {
                return false;
            }
        }
        return true;
    }
*/
    public void hienThiCongThuc() {
        System.out.println("Cong thuc: " + tenCongThuc + " (" + maCongThuc + ")");
        System.out.println("Loai: " + loai + ", Phien ban: " + phienBan);
        System.out.println("Thoi gian che bien: " + thoiGianCheBien + " phut");
        System.out.println("Mo ta: " + moTa);
        System.out.println("Nguyen lieu:");
        for (int i = 0; i < soNguyenLieu; i++) {
            dsNguyenLieu[i].toString();
        }
    }
}


