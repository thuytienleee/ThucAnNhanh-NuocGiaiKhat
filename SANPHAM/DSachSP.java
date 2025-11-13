/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// package SANPHAM;
package SANPHAM;

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

    public DSachSP(int kichThuoc) {
        danhsach = new SanPham[kichThuoc];
        soluong = 0;
    }

    public void themSanPham() {
        System.out.println("\n=====ADD NEW PRODUCT=====");
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

        Date nsx, hsd;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            nsx = sdf.parse(nsxStr);
            hsd = sdf.parse(hsdStr);
        } catch (ParseException e) {
            System.out.println("Lỗi" + e.getMessage());
            nsx = new Date();
            hsd = new Date();
        }

        SanPham sp = null;

        switch (maLoai) {
            case "PZA_TA":
                System.out.println("Size: ");
                String size = sc.nextLine();
                sp = new Pizza(maSP, tenSP, maLoai, gia, nsx, hsd, 0, size);
                break;
            case "HBG_TA":
                System.out.println("Them Rau (Rau/Khong rau):  ");
                String nhapRau = sc.nextLine().trim();
                boolean rau = nhapRau.equalsIgnoreCase("Rau");
                sp = new Hamburger(maSP, tenSP, maLoai, gia, nsx, hsd, 0, rau);
                break;
            case "GRN_TA":
                System.out.println("Vi cay (Cay/Khong cay): ");
                String nhapCay = sc.nextLine().trim();
                boolean viCay = nhapCay.equalsIgnoreCase("Cay");
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

        if (soluong < danhsach.length) {
            danhsach[soluong++] = sp;
            System.out.println("Added new product: " + sp.getMaLoai() + "-" + sp.getTenSP());
            ghiVaoFile(sp);
        } else {
            System.out.println("List product is full");
        }

    }

    public static void ghiVaoFile(SanPham sp) {
        try {
            FileWriter fw = new FileWriter("../SANPHAM/SanPham.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String line = sp.getMaSP() + "|"
                    + sp.getTenSP() + "|"
                    + sp.getMaLoai() + "|"
                    + sp.getGia() + "|"
                    + sdf.format(sp.getNgaySX()) + "|"
                    + sdf.format(sp.getHanSD());
            if (sp instanceof Pizza) {
                line += "|" + ((Pizza) sp).getSize();
            } else if (sp instanceof Hamburger) {
                boolean rau = ((Hamburger) sp).getRau();
                line += "|" + (rau ? "Rau" : "Khong Rau");
            } else if (sp instanceof GaRan) {
                boolean cay = ((GaRan) sp).getDoCay();
                line += "|" + (cay ? "Cay" : "Khong Cay");
            }

            bw.write(line);
            bw.newLine();
            bw.close();
            fw.close();
            System.out.println("Write success!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void DocFile() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // xung dot cua toi
            FileReader fr = new FileReader(
                    "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\SANPHAM\\SanPham.txt");
            // ngan cach
            FileReader fr = new FileReader("SANPHAM/SanPham.txt");
            // xung dot tu xa
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] p = line.split("\\|");
                // for (int i = 0; i < p.length; i++) {
                // p[i] = p[i].trim();
                // }
                String maSP = p[0];
                String tenSP = p[1].trim();
                String maLoai = p[2];
                double gia = Double.parseDouble(p[3]);
                Date nsx = sdf.parse(p[4]);
                Date hsd = sdf.parse(p[5]);
                String thuoctinh_rieng = p.length > 6 ? p[6].trim() : "";

                SanPham sp = null;
                if (maLoai.equalsIgnoreCase("PZA_TA")) {
                    sp = new Pizza(maSP, tenSP, maLoai, gia, nsx, hsd, 0, thuoctinh_rieng);
                } else if (maLoai.equalsIgnoreCase("HBG_TA")) {
                    boolean rau = thuoctinh_rieng.equalsIgnoreCase("Rau");
                    sp = new Hamburger(maSP, tenSP, maLoai, gia, nsx, hsd, 0, rau);
                } else if (maLoai.equalsIgnoreCase("GRN_TA")) {
                    boolean cay = thuoctinh_rieng.equalsIgnoreCase("Cay");
                    sp = new GaRan(maSP, tenSP, maLoai, gia, nsx, hsd, 0, cay);
                } else if (maLoai.equalsIgnoreCase("DR_CS")) {
                    sp = new NuocNgot(maSP, tenSP, maLoai, gia, nsx, hsd, 0);
                } else if (maLoai.equalsIgnoreCase("DR_BW")) {
                    sp = new NuocSuoi(maSP, tenSP, maLoai, gia, nsx, hsd, 0);
                } else if (maLoai.equalsIgnoreCase("TA")) {
                    sp = new ThucAn(maSP, tenSP, maLoai, gia, nsx, hsd, 0);
                } else if (maLoai.equalsIgnoreCase("DR")) {
                    sp = new ThucUong(maSP, tenSP, maLoai, gia, nsx, hsd, 0);
                }
                if (soluong < danhsach.length) {
                    danhsach[soluong++] = sp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hienThiDS() {
        System.out.print(
                "+------------------------------------------------------------------------------------------------+\n");
        System.out.print(
                "|                                  PRODUCTION INFOMATION                                         |\n");
        System.out.print(
                "+------------------------------------------------------------------------------------------------+\n");
        System.out.print(String.format(" %-10s | %-20s | %-10s | %-10s | %-10s | %-10s\n",
                "ID", "NAME", "CATEGORY ID", "PRICE", "NSX", "HSD"));
        System.out.print(
                "+------------------------------------------------------------------------------------------------+\n");
        for (SanPham sp : danhsach) {
            if (sp != null) {
                System.out.println(sp.toString());
            }
        }
    }

    public void capNhatFileSauKhiXoa() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("SANPHAM/SanPham.txt"))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < soluong; i++) {
                SanPham sp = danhsach[i];
                if (sp == null) {
                    continue;
                }
                String line = sp.getMaSP() + "|" + sp.getTenSP() + "|" + sp.getMaLoai() + "|"
                        + sp.getGia() + "|" + sdf.format(sp.getNgaySX()) + "|" + sdf.format(sp.getHanSD());
                if (sp instanceof Pizza) {
                    line += "|" + ((Pizza) sp).getSize();
                } else if (sp instanceof Hamburger) {
                    boolean rau = ((Hamburger) sp).getRau();
                    line += "|" + (rau ? "Rau" : "Khong Rau");
                } else if (sp instanceof GaRan) {
                    boolean cay = ((GaRan) sp).getDoCay();
                    line += "|" + (cay ? "Cay" : "Khong Cay");
                }

                bw.write(line);
                bw.newLine();
            }
            System.out.println("File is updated!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void xoaSanPham() {
        while (true) {
            System.out.println("\n===========DELETE PRODUCT===========");
            System.out.println("Enter product ID:");
            String ma = sc.nextLine().trim();
            int index = -1;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i].getMaSP().equalsIgnoreCase(ma)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Not found product ID: " + ma);
            } else {
                for (int i = index; i < soluong - 1; i++) {
                    danhsach[i] = danhsach[i + 1];
                }
                danhsach[--soluong] = null;
                System.out.println("Deleted product ID: " + ma);
                capNhatFileSauKhiXoa();
                hienThiDS();
                break;
            }
        }
    }

    public void timKiemTheoTen() {
        while (true) {
            System.out.println("\n============SEARCH PRODUCT BY NAME============");
            System.out.print("Enter product name: ");
            String keyword = sc.nextLine().trim().toLowerCase();
            boolean found = false;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null && danhsach[i].getTenSP().toLowerCase().contains(keyword)) {
                    System.out.println("Found product: " + danhsach[i].toString());
                    found = true;
                }
            }

            if (found) {
                break;
            } else {
                System.out.println("Not found product with name: " + keyword);
                System.out.println("Please try again!");
            }
        }

    }

    public void timKiemTheoMa() {
        while (true) {
            System.out.println("\n============ SEARCH PRODUCT BY ID OR TYPE ID ============");
            System.out.print("Enter product ID or category ID: ");
            String maCanTim = sc.nextLine().trim().toLowerCase();
            boolean found = false;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null) {
                    String maSP = danhsach[i].getMaSP().toLowerCase();
                    String maLoai = danhsach[i].getMaLoai().toLowerCase();

                    if (maSP.contains(maCanTim) || maLoai.contains(maCanTim)) {
                        System.out.println("Found product: " + danhsach[i]);
                        found = true;
                    }
                }
            }

            if (found) {
                break;
            } else {
                System.out.println("Not found any product with ID or category ID: " + maCanTim);
                System.out.println("Please try again!");
            }
        }
    }

    public void suaSanPham() {
        while (true) {
            // xung dot cua toi
            System.out.println("===== EDIT PRODUCT =====");
            // ngan cach
            System.out.println("\n=========== EDIT PRODUCT =========");
            // xung dot tu xa
            System.out.print("Enter product ID: ");
            String maSP = sc.nextLine().trim();

            int index = -1;
            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null && danhsach[i].getMaSP().equalsIgnoreCase(maSP)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Product ID not found, please try again!");
                // xung dot cua toi
            } else {
                SanPham sp = danhsach[index];
                System.out.println("Current product info:");
                System.out.println(sp.toString());

                System.out.println("\n===== EDIT MENU =====");
                System.out.println("1. Edit name");
                System.out.println("2. Edit price");
                System.out.println("3. Edit manufacture date (NSX)");
                System.out.println("4. Edit expiry date (HSD)");
                System.out.println("5. Edit special attribute (size / rau / cay)");
                System.out.println("0. Exit");
                System.out.print("Select option: ");
                int choice = Integer.parseInt(sc.nextLine());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                switch (choice) {
                    case 1:
                        System.out.print("Enter new name: ");
                        sp.setTenSP(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter new price: ");
                        sp.setGia(Double.parseDouble(sc.nextLine()));
                        break;
                    case 3:
                        System.out.print("Enter new manufacture date (yyyy-MM-dd): ");
                        try {
                            sp.setNgaySX(sdf.parse(sc.nextLine()));
                        } catch (Exception e) {
                            System.out.println("Invalid date format!");
                        }
                        break;
                    case 4:
                        System.out.print("Enter new expiry date (yyyy-MM-dd): ");
                        try {
                            sp.setHanSD(sdf.parse(sc.nextLine()));
                        } catch (Exception e) {
                            System.out.println("Invalid date format!");
                        }
                        break;
                    case 5:
                        String maLoai = sp.getMaLoai();
                        if (maLoai.equalsIgnoreCase("PZA_TA")) {
                            Pizza p = (Pizza) sp;
                            System.out.print("Enter new size: ");
                            p.setSize(sc.nextLine());
                        } else if (maLoai.equalsIgnoreCase("HBG_TA")) {
                            Hamburger h = (Hamburger) sp;
                            System.out.print("Rau / Khong rau: ");
                            String nhap = sc.nextLine().trim();
                            h.setRau(nhap.equalsIgnoreCase("Rau"));
                        } else if (maLoai.equalsIgnoreCase("GRN_TA")) {
                            GaRan g = (GaRan) sp;
                            System.out.print("Cay / Khong cay: ");
                            String nhap = sc.nextLine().trim();
                            g.setDoCay(nhap.equalsIgnoreCase("Cay"));
                        } else {
                            System.out.println("This product type has no special attribute!");
                        }
                        break;
                    case 0:
                        System.out.println("Exit editing...");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }

                System.out.println("Updated product info:");
                System.out.println(sp.toString());

                // Ghi lại toàn bộ danh sách vào file
                capNhatFileSauKhiXoa();

                System.out.println("File updated successfully!");
                break;
            }
            // ngan cach
            continue;
        }

        SanPham sp = danhsach[index];
        System.out.println("Current product info:");
        System.out.println(sp.toString());

        boolean tiepTuc = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (tiepTuc) {
            System.out.println("\n===== EDIT MENU =====");
            System.out.println("1. Edit name");
            System.out.println("2. Edit price");
            System.out.println("3. Edit manufacture date (NSX)");
            System.out.println("4. Edit expiry date (HSD)");
            System.out.println("5. Edit special attribute (size / rau / cay)");
            System.out.println("0. Exit");
            System.out.print("Select option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    sp.setTenSP(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new price: ");
                    sp.setGia(Double.parseDouble(sc.nextLine()));
                    break;
                case 3:
                    System.out.print("Enter new manufacture date (yyyy-MM-dd): ");
                    try {
                        sp.setNgaySX(sdf.parse(sc.nextLine()));
                    } catch (Exception e) {
                        System.out.println("Invalid date format!");
                    }
                    break;
                case 4:
                    System.out.print("Enter new expiry date (yyyy-MM-dd): ");
                    try {
                        sp.setHanSD(sdf.parse(sc.nextLine()));
                    } catch (Exception e) {
                        System.out.println("Invalid date format!");
                    }
                    break;
                case 5:
                    String maLoai = sp.getMaLoai();
                    if (maLoai.equalsIgnoreCase("PZA_TA")) {
                        Pizza p = (Pizza) sp;
                        System.out.print("Enter new size: ");
                        p.setSize(sc.nextLine());
                    } else if (maLoai.equalsIgnoreCase("HBG_TA")) {
                        Hamburger h = (Hamburger) sp;
                        System.out.print("Rau / Khong rau: ");
                        String nhap = sc.nextLine().trim();
                        h.setRau(nhap.equalsIgnoreCase("Rau"));
                    } else if (maLoai.equalsIgnoreCase("GRN_TA")) {
                        GaRan g = (GaRan) sp;
                        System.out.print("Cay / Khong cay: ");
                        String nhap = sc.nextLine().trim();
                        g.setDoCay(nhap.equalsIgnoreCase("Cay"));
                    } else {
                        System.out.println("This product type has no special attribute!");
                    }
                    break;
                case 0:
                    System.out.println("Exit editing...");
                    tiepTuc = false;
                    continue;
                default:
                    System.out.println("Invalid choice!");
                    continue;
            }
            System.out.println("Updated product info:");
            System.out.println(sp.toString());
            capNhatFileSauKhiXoa();
            System.out.println("File updated successfully!");

            System.out.print("\nDo you want to continue? (yes/no): ");
            String ans = sc.nextLine().trim();
            if (!ans.equalsIgnoreCase("yes")) {
                tiepTuc = false;
            }
        }
        break;
        // xung dot tu xa
    }

    }

    public static void main(String[] agrs) {
        DSachSP ds = new DSachSP(1000);
        // ds.themSanPham();
        ds.DocFile();
        // xung dot cua toi
        // ds.xoaSanPham();
        ds.timKiemTheoTen();
        ds.timKiemTheoMa();
        // ds.hienThiDS();
        // ngan cach
        // ds.xoaSanPham();
        // ds.timKiemTheoTen();
        // ds.timKiemTheoMa();
        ds.suaSanPham();
        ds.hienThiDS();

        // xung dot tu xa
    }
}
