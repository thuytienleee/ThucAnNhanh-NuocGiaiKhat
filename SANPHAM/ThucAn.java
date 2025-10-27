package SANPHAM;

import java.util.Date;

/**
 *
 * @author Thuy Tien
 */
public class ThucAn extends SanPham {
    public ThucAn(){}
    
    public ThucAn(String maSP, String tenSP, String maLoai, double gia, Date NgaySX, Date HanSD, int soLuong){
        super(maSP, tenSP, maLoai, gia, NgaySX, HanSD, soLuong);
    }
    
    @Override
    public String toString(){
        return String.format("[Món ăn] Mã: %s | Tên: %s | Loai: %s | Giá: %.0f | SL: %d | NSX: %tF | HSD: %tF", maSP, tenSP, maLoai, gia, soLuong, NgaySX, HanSD);
    }
}
