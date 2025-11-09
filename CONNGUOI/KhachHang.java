/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// package CONNGUOI;
package CONNGUOI;

/**
 *
 * @author Thuy Tien
 */
public class KhachHang extends ConNguoi {
    private String maKH;
    
    public KhachHang() {
        super();
    }
    
    public KhachHang(String maKH, String ten, String diaChi, String soDT, 
                     String ngaySinh, String gioiTinh) {
        super(ten, diaChi, soDT, ngaySinh, gioiTinh);
        this.maKH = maKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }
    
    @Override
    public String toString() {
        return String.format("Ma KH: %s | Ten: %s | SDT: %s | Dia chi: %s | Ngay sinh: %s | Gioi tinh: %s", 
                            maKH, getTen(), getSoDT(), getDiaChi(), getNgaySinh(), getGioiTinh());
    }
}