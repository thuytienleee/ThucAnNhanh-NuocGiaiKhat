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
public class Pizza extends ThucAn{
    private String size;
    
    public Pizza(){}
    
    public Pizza(String maSP, String tenSP, String maLoai, double gia, Date NgaySX, Date HanSD, int soLuong, String size){
        super(maSP, tenSP, maLoai, gia, NgaySX, HanSD, soLuong);
        this.size = size;
    }
    
    public String getSize(){
        return size;
    }
    
    public void setSize(String size){
        this.size = size;
    }
    
    @Override
    public String toString(){
        return super.toString() + String.format(" | Size: %s", size);
    }
}
