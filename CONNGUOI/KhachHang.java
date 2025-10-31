/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONNGUOI;

/**
 *
 * @author Thuy Tien
 */
public class KhachHang {
    private String maKH;
    private String tenKH;
    private int sdtKH;
    
    public KhachHang(){}
    
    public KhachHang(String maKH, String tenKH, int sdtKH){
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdtKH = sdtKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }
    
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(int sdtKH) {
        this.sdtKH = sdtKH;
    }
    
    public String toString(){
        return String.format("Customer ID: %s | Name: %s | Numer phone: %s", maKH, tenKH, sdtKH);
    }
    
}
