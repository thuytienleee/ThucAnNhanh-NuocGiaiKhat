/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// package SANPHAM;
package com.example.models.ThucAnNhanhNuocGiaiKhat.SANPHAM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Thuy Tien
 */
public class DSachSP {
    private SanPham[] danhsach;
    private int soluong;
    static Scanner sc = new Scanner(System.in);
    
    public DSachSP(int kichThuoc){
        danhsach = new SanPham[kichThuoc];
        soluong = 0;
    }
    
    
    public void themSanPham(){
        System.out.println("=====ADD NEW PRODUCT=====");
        System.out.println("ID: ");
        String maSP = sc.nextLine();
        
        System.out.println("NAME: ");
        String tenSP = sc.nextLine();
        
        System.out.println("CATEGORY ID: ");
        String maLoai = sc.nextLine();
        
        System.out.println("PRICE: ");
        double gia = Double.parseDouble(sc.nextLine());
        
        System.out.println("NSX: ");
        String nsxStr = sc.nextLine();
        
        System.out.println("HSD: ");
        String hsdStr = sc.nextLine();
        
        Date nsx,hsd;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            nsx = sdf.parse(nsxStr);
            hsd = sdf.parse(hsdStr);
        }catch(ParseException e){
            System.out.println("Lỗi"+ e.getMessage());
            nsx = new Date();
            hsd = new Date();
        }
        
        SanPham sp = null;
        
        switch(maLoai){
            case "PZA_TA":
                System.out.println("Size: ");
                String size = sc.nextLine();
                sp = new Pizza(maSP, tenSP, maLoai, gia, nsx, hsd,0, size);
                break;
            case "HBG_TA":
                System.out.println("Them Rau (Rau/Khong rau):  ");
                boolean rau = Boolean.parseBoolean(sc.nextLine());
                sp = new Hamburger(maSP, tenSP, maLoai, gia, nsx, hsd, 0, rau);
                break;
            case "GRN_TA":
                System.out.println("Vi cay (Cay/Khong cay): ");
                boolean viCay = Boolean.parseBoolean(sc.nextLine());
                sp = new GaRan(maSP, tenSP, maLoai, gia, nsx, hsd, 0, viCay);
                break;
            case "DR_CS":
                sp = new NuocNgot(maSP, tenSP, maLoai, gia, nsx, hsd, 0);
                break;
            case "DR_BW":
                sp = new NuocSuoi(maSP, tenSP, maLoai, gia, nsx, hsd, 0);
                break;
            case "DR":
                sp = new ThucUong(maSP, tenSP, maLoai, gia, nsx, hsd, 0);
                break;
            case "TA":
                sp = new ThucAn(maSP, tenSP, maLoai, gia, nsx, hsd, 0);
                break;
            default: 
                System.out.println("Cannot add new product!");
                break;
        }
        
        if(soluong < danhsach.length){
            danhsach[soluong++] = sp;
            System.out.println("Added new product: "+sp.getMaLoai()+"-"+sp.getTenSP());
            ghiVaoFile(sp);
        }else{
            System.out.println("List product is full");
        }
        
    }
    
    public static void ghiVaoFile(SanPham sp){
        try{
            FileWriter fw= new FileWriter("../SANPHAM/SanPham.txt",true);
            BufferedWriter bw= new BufferedWriter(fw);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String line = sp.getMaSP()+" | "+
                          sp.getTenSP()+" | "+
                          sp.getMaLoai()+" | "+
                          sp.getGia()+" | "+
                          sdf.format(sp.getNgaySX())+" | "+
                          sdf.format(sp.getHanSD());
            if(sp instanceof Pizza){
                line += " | "+((Pizza) sp).getSize();
            }else if(sp instanceof Hamburger){
                line += " | " +((Hamburger) sp).getRau();
            }else if(sp instanceof GaRan){
                line += " | " + ((GaRan) sp).getDoCay();
            }
            
            bw.write(line);
            bw.newLine();
            bw.close();
            fw.close();
            System.out.println("Write success!");
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public  void DocFile(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            FileReader fr = new FileReader("../SANPHAM/SanPham.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
               if(line.trim().isEmpty()) continue;
                String[] p = line.split("\\|");
                
                String maSP = p[0];
                String tenSP = p[1].trim();
                String maLoai = p[2];
                double gia = Double.parseDouble(p[3]);
                Date nsx = sdf.parse(p[4]);
                Date hsd = sdf.parse(p[5]);
                String thuoctinh_rieng = p.length > 6 ? p[6] : "";
                
                SanPham sp = null;
                if(maLoai.equalsIgnoreCase("PZA_TA")){
                    sp = new Pizza(maSP, tenSP, maLoai, gia, nsx, hsd, 0,thuoctinh_rieng);
                }else if(maLoai.equalsIgnoreCase("HBG_TA")){
                    boolean rau = thuoctinh_rieng.equalsIgnoreCase("Rau");
                    sp = new Hamburger(maSP, tenSP, maLoai, gia, nsx, hsd, 0, rau);
                }else if(maLoai.equalsIgnoreCase("GRN_TA")){
                    boolean cay = thuoctinh_rieng.equalsIgnoreCase("Cay");
                    sp = new GaRan(maSP, tenSP, maLoai, gia, nsx, hsd, 0, cay);
                }else if(maLoai.equalsIgnoreCase("DR_CS")){
                    sp = new NuocNgot(maSP, tenSP, maLoai, gia, nsx, hsd, 0);
                }else if(maLoai.equalsIgnoreCase("DR_BW")){
                    sp = new NuocSuoi(maSP, tenSP, maLoai, gia, nsx, hsd, 0);
                }else if(maLoai.equalsIgnoreCase("TA")){
                    sp = new ThucAn(maSP, tenSP, maLoai, gia, nsx, hsd, 0);
                }else if(maLoai.equalsIgnoreCase("DR")){
                    sp = new ThucUong(maSP, tenSP, maLoai, gia, nsx, hsd, 0);
                }else{
                    System.out.println("Cannot read");
                }
                if(soluong < danhsach.length) danhsach[soluong++] = sp;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
   public void hienThiDS(){
       for(SanPham sp : danhsach){
           if(sp != null) System.out.println(sp.toString());
       }
   }
    
    /*public void xoaSanPham(){}
      public void timKiemTheoTen(){}
      public void timKiemTheoMa(){}
      public void SuaSanPham()
    */
   public static void main(String[] agrs){
       DSachSP ds = new DSachSP(1000);
       ds.DocFile();
       ds.hienThiDS();
   }
}
