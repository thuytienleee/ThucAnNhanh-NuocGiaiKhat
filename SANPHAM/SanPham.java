
package SANPHAM;

import java.util.Date;

/**
 *
 * @author Thuy Tien
 */
public abstract class SanPham {
    public String maSP;
    public String tenSP;
    public String maLoai;
    public double gia;
    public Date NgaySX;
    public Date HanSD;
    //private CongThuc congThuc;
    public int soLuong;
    
    public SanPham(){}

    public SanPham(String maSP, int soLuong, double gia){
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.gia = gia;
    }
    
    public SanPham(String maSP, String tenSP, String maLoai, Double gia, Date NgaySX, Date HanSD/*, CongThuc congThuc*/, int soLuong){
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLoai = maLoai;
        this.gia = gia;
        this.NgaySX = NgaySX;
        this.HanSD = HanSD;
        //this.congThuc = congThuc;
        this.soLuong = soLuong;
    }

    public String getMaSP() {
        return maSP;
    }
    
    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }
    
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Date getNgaySX() {
        return NgaySX;
    }

    public void setNgaySX(Date NgaySX) {
        this.NgaySX = NgaySX;
    }

    public Date getHanSD() {
        return HanSD;
    }

    public void setHanSD(Date HanSD) {
        this.HanSD = HanSD;
    }
    
    /*public CongThuc getCongThuc(){
        return congThuc;
    }*/
    
    /*public void setCongThuc(CongThuc congthuc){
        this.congThuc = congThuc;\
    }
    */
    
    public int getSoLuong(){
        return soLuong;
    }
    
    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }
    
    public void themNguyenLieu(){}
    public void CheBien(){}
    public double tinhTien(){
        return gia * soLuong;
    }
    //public abstract String toString();
}