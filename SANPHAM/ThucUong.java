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
public class ThucUong extends SanPham{
    public ThucUong(){}
    
    public ThucUong(String maSP, String tenSP, String maLoai, double gia, Date NgaySX, Date HanSD, int soLuong){
        super(maSP, tenSP, maLoai, gia, NgaySX, HanSD, soLuong);
    }
    
    @Override
    public String toString(){
        return String.format("[Thuc Uong] ID: %s | Name: %s | Category: %s | Price: %.0f | NSX: %tF | HSD: %tF", maSP, tenSP, maLoai, gia, NgaySX, HanSD);
    }
}
