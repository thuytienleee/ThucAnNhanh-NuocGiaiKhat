/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SANPHAM;

import java.util.Date;
    
/**
 *
 * @author Thuy Tien
 */
public class Hamburger extends ThucAn{
    private boolean rau;
    
    public Hamburger(){}
    
    public Hamburger(String maSP, String tenSP, String maLoai, double gia, Date NgaySX, Date HanSD, int soLuong, boolean rau){
        super(maSP, tenSP, maLoai, gia, NgaySX, HanSD, soLuong);
        this.rau = rau;
    }
    
    public boolean getRau(){
        return rau;
    }
    
    public void setRau(boolean rau){
        this.rau = rau;
    }
    
    @Override
    public String toString(){
        return super.toString() + String.format(" | Rau: %s", rau ? "Co Rau" : "Khong Rau");
    }
}
