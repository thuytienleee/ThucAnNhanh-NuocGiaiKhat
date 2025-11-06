// package NGUYENLIEU;
package com.example.models.ThucAnNhanhNuocGiaiKhat.NGUYENLIEU;

import java.io.*;
import java.util.Scanner;

public class DSNguyenLieu {
    private nguyenlieu[] ds;
    private int soLuong;

    public DSNguyenLieu(int kichThuoc) {
        ds = new nguyenlieu[kichThuoc];
        soLuong = 0;
    }

    public void nhapNL() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong nguyen lieu muon them: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            if (soLuong >= ds.length) {
                System.out.println("Danh sach day, khong the them!");
                break;
            }
            System.out.println("\nNhap thong tin nguyen lieu thu " + (soLuong + 1) + ":");
            System.out.print("Ma nguyen lieu: ");
            String ma = sc.nextLine();
            System.out.print("Ten nguyen lieu: ");
            String ten = sc.nextLine();
            System.out.print("Don vi tinh: ");
            String dvt = sc.nextLine();
            System.out.print("So luong: ");
            double sl = sc.nextDouble();
            sc.nextLine();

            ds[soLuong++] = new nguyenlieu(ma, ten, dvt, sl);
        }
    }

    public void ghiFile() {
        File file = new File("DSnguyenLieu.txt");
        boolean fileRong = (file.length() == 0);

        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            if (fileRong) {
                bw.write("+----------+----------------------+--------------+----------+\n");
                bw.write("| MaNL     | TenNL                | DonViTinh    | SoLuong  |\n");
                bw.write("+----------+----------------------+--------------+----------+\n");
            }

            for (int i = 0; i < soLuong; i++) {
                bw.write(ds[i].toString() + "\n");
                bw.write("+----------+----------------------+--------------+----------+\n");
            }

            bw.flush();
            System.out.println(" Ghi file thanh cong vao DSnguyenLieu.txt");

        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void hienThiDanhSach() {
        System.out.println("+----------+----------------------+--------------+----------+");
        System.out.println("| MaNL     | TenNL                | DonViTinh    | SoLuong  |");
        System.out.println("+----------+----------------------+--------------+----------+");
        for (int i = 0; i < soLuong; i++) {
            System.out.println(ds[i]);
        }
        System.out.println("+----------+----------------------+--------------+----------+");
    }

    public static void main(String[] args) {
        DSNguyenLieu ds = new DSNguyenLieu(100);
        ds.nhapNL();
        ds.hienThiDanhSach();
        ds.ghiFile();
    }
}
