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
public class GaRan extends ThucAn {
    public GaRan(){}
    
    public GaRan(String maSP, String tenSP, String maLoai, double gia, Date NgaySX, Date HanSD, int soLuong){
        super(maSP, tenSP, maLoai, gia, NgaySX, HanSD, soLuong);
    }
}
