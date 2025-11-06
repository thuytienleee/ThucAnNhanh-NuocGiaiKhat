// package ThucAnNhanh-NuocGiaiKhat.HOADON;
package com.example.models.ThucAnNhanhNuocGiaiKhat.HOADON;

public class ThanhToanThe implements ThanhToan {
    private double phiGiaoDich;

    public ThanhToanThe(double phiGiaoDich) {
        this.phiGiaoDich = phiGiaoDich;
    }

    @Override
    public double tinhTien(double tongTien) {
        // tru phi giao dich khi thanh toan bang the
        return tongTien - (tongTien * phiGiaoDich);
    }
}
