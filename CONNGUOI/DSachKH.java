// package CONNGUOI;
package com.example.models.ThucAnNhanhNuocGiaiKhat.CONNGUOI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class DSachKH {
    private KhachHang[] danhSachKH;
    private static int soluongKH;
    static Scanner sc = new Scanner(System.in);

    public DSachKH(int KichThuoc){
        danhSachKH = new KhachHang[KichThuoc];
        soluongKH = 0;
    }

    public void themKhachHang(){}

    public static void ghiVaoFile(){}

    public void DocFile(){
        try{
            FileReader fr = new FileReader("../CONNGUOI/danhSachKhangHang.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                if(line.trim().isEmpty()) continue;
                String[] p = line.split("\\|");
                String maKH = p[0];
                String tenKH = p[1].trim();
                String sdtKH = p[2];

                KhachHang kh = null;
                kh = new KhachHang(maKH, tenKH, sdtKH);
                if (soluongKH < danhSachKH.length) danhSachKH[soluongKH++] = kh;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }    


    public void hienThiDS(){
        for(KhachHang kh : danhSachKH){
            if(kh != null) System.out.println(kh.toString());
        }
    }

    public static void main(String[] args) {
        DSachKH dskh = new DSachKH(100);
        dskh.DocFile();
        dskh.hienThiDS();
    }
}
