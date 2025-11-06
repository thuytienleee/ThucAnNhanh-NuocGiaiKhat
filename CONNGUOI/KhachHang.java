/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// package CONNGUOI;
package com.example.models.ThucAnNhanhNuocGiaiKhat.CONNGUOI;

/**
 *
 * @author Thuy Tien
 */
public class KhachHang {
    private String maKH;
    private String tenKH;
    private String sdtKH;
    
    public KhachHang(){}
    
    public KhachHang(String maKH, String tenKH, String sdtKH){
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

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }
    
    public String toString(){
        return String.format("Customer ID: %s | Name: %s | Numer phone: %s", maKH, tenKH, sdtKH);
    }
    
}
