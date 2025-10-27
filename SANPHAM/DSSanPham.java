package SANPHAM;

/**
 *
 * @author Thuy Tien
 */
public class DSSanPham {
    private SanPham[] danhsach;
    private int soluong;
    
    public DSSanPham(int kichThuoc){
        danhsach = new SanPham[kichThuoc];
        soluong = 0;
    }
    
    public boolean themSanPham(SanPham sp){
        if(soluong >= danhsach.length){
            System.out.println("Danh sách đã đầy, không thể thêm sản phẩm mới");
            return false;
        }
        danhsach[soluong++] = sp;
        System.out.println("Thêm sản phẩm "+sp.getTenSP());
        return true;
    }
    
    /*public void xoaSanPham(){}
      public void timKiemTheoTen(){}
      public void timKiemTheoMa(){}
      public void hienThiDS()
      public void SuaSanPham()
    */
}
