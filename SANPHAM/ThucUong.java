package SANPHAM;

import java.util.Date;

/**
 *
 * @author Thuy Tien
 */
public class ThucUong extends SanPham{
    public ThucUong(){}
    
    public ThucUong(String maSP, String tenSP, String maLoai, double gia, Date NgaySX, Date HanSD, int soLuong){
        super(maSP, tenSP, maLoai, gia, NgaySX, HanSD, soLuong);
    }
    
    @Override
    public String toString(){
        return String.format("[Thức Uống] Mã: %s | Tên: %s | Loai: %s | Giá: %.0f | SL: %d | NSX: %tF | HSD: %tF", maSP, tenSP, maLoai, gia, soLuong, NgaySX, HanSD);
    }
}
