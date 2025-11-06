// package ThucAnNhanh-NuocGiaiKhat.HOADON;
package com.example.models.ThucAnNhanhNuocGiaiKhat.HOADON;

public class ThanhToanTienMat implements ThanhToan {

    public ThanhToanTienMat() {
        // Constructor mac dinh
    }

    @Override
    public double tinhTien(double tongTien) {
        // Thanh toan tien mat khong co phi, tra du
        return tongTien;
    }
}
