// package NGUYENLIEU;
package com.example.models.ThucAnNhanhNuocGiaiKhat.NGUYENLIEU;

public class testNL {
    public static void main(String[] args) {
        DSNguyenLieu ds = new DSNguyenLieu(100);
        ds.nhapNL();
        ds.hienThiDanhSach();
        ds.ghiFile();
    }
}
