package CONGTHUC;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

public class data {
    public static void main(String[] args) {
        String[] loai = {"PZA_TA", "HBG_TA", "GRN_TA"};
        String[] tenPizza = {"Pho Mai", "Hai San", "Ga BBQ", "Jambon"};
        String[] tenHBG = {"Bo", "Tom", "Ca"};
        String[] tenGRN = {"Chien Gion", "Sot Cay", "Tam Nuoc Mam"};
        String[] ghiChuPizza = {"L", "M"};
        String[] ghiChuHBG = {"Rau", "Khong Rau"};
        String[] ghiChuGRN = {"Cay", "Khong Cay"};

        Random rd = new Random();
        LocalDate ngayNhap = LocalDate.now();
        LocalDate ngayCapNhat = ngayNhap.plusDays(1);

        try (FileWriter fw = new FileWriter("danhSachMonAn.txt")) {
 // --- Pizza ---
            for (int i = 1; i <= 6; i++) {
                String ma = String.format("PZA%03d", i + 1);
                String ten = "Pizza " + tenPizza[rd.nextInt(tenPizza.length)];
                double gia = 90000 + rd.nextInt(40000);
                String ghiChu = ghiChuPizza[rd.nextInt(ghiChuPizza.length)];
                fw.write(String.format("%s|%s|%s|%.1f|%s|%s|%s%n",
                        ma, ten, loai[0], gia, ngayNhap, ngayCapNhat, ghiChu));
            }

// --- Hamburger ---
            for (int i = 1; i <= 6; i++) {
                String ma = String.format("HBG00%d%c", i, (char) ('A' + rd.nextInt(3)));
                String ten = "Hamburger " + tenHBG[rd.nextInt(tenHBG.length)];
                double gia = 44000 + rd.nextInt(10000);
                String ghiChu = ghiChuHBG[rd.nextInt(ghiChuHBG.length)];
                fw.write(String.format("%s|%s|%s|%.1f|%s|%s|%s%n",
                        ma, ten, loai[1], gia, ngayNhap, ngayCapNhat, ghiChu));
            }

 // --- Gà rán ---
            for (int i = 1; i <= 6; i++) {
                String ma = String.format("GRN00%d%c", i, (char) ('A' + rd.nextInt(3)));
                String ten = "Ga Ran " + tenGRN[rd.nextInt(tenGRN.length)];
                double gia = 35000 + rd.nextInt(15000);
                String ghiChu = ghiChuGRN[rd.nextInt(ghiChuGRN.length)];
                fw.write(String.format("%s|%s|%s|%.1f|%s|%s|%s%n",
                        ma, ten, loai[2], gia, ngayNhap, ngayCapNhat, ghiChu));
            }

            System.out.println("✅ Da tao file danhSachMonAn.txt thanh cong!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
