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
        System.out.println("=====ADD NEW PRODUCT=====");

        String[] validCategories = { "PZA_TA", "HBG_TA", "GRN_TA", "DR_CS", "DR_BW", "DR", "TA" };

        String maSP;
        while (true) {
            System.out.print("ID: ");
            maSP = sc.nextLine().trim();

            if (maSP.isEmpty()) {
                System.out.println("Product ID cannot be empty! Please try again.");
                continue;
            }

            if (maSP.contains(" ")) {
                System.out.println("Error: Product ID cannot contain spaces!");
                continue;
            }

            if (maSP.length() < 2) {
                System.out.println("Error: Product ID is invalid format!");
                continue;
            }

            // Kiểm tra mã không được quá ngắn (tránh các mã như P, Z, A, etc.)
            if (maSP.length() <= 3 && !maSP.matches(".*[0-9].*")) {
                System.out.println("Error: Product ID is invalid format!");
                continue;
            }

            // Kiểm tra trùng mã
            boolean trungMa = false;
            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null && danhsach[i].getMaSP().equalsIgnoreCase(maSP)) {
                    trungMa = true;
                    break;
                }
            }

            if (trungMa) {
                System.out.println("Product ID already exists! Please use a different ID.");
            } else {
                break;
            }
        }

        System.out.print("NAME: ");
        String tenSP = sc.nextLine();

        // Validation cho mã loại
        String maLoai;
        while (true) {
            System.out.print("CATEGORY ID: ");
            maLoai = sc.nextLine().trim();

            boolean validCategory = false;
            for (String category : validCategories) {
                if (category.equalsIgnoreCase(maLoai)) {
                    validCategory = true;
                    break;
                }
            }

            if (!validCategory) {
                System.out.println("Error: Invalid category ID! Valid categories are:");
                System.out.println("PZA_TA, HBG_TA, GRN_TA, DR_CS, DR_BW, DR, TA");
                System.out.println("Please enter a valid category ID.");
            } else {
                break;
            }
        }

        double gia = 0;
        while (true) {
            System.out.print("PRICE: ");
            String giaStr = sc.nextLine();
            try {
                gia = Double.parseDouble(giaStr);
                if (gia <= 0) {
                    System.out.println("Error: Price must be greater than 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid price format! Please enter a valid number.");
            }
        }

        // Validation cho ngày sản xuất
        Date nsx = null;
        while (nsx == null) {
            System.out.print("NSX (dd-MM-yyyy): ");
            String nsxStr = sc.nextLine();

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false); // Strict date parsing
                nsx = sdf.parse(nsxStr);
                Date currentDate = new Date();

                if (nsx.after(currentDate)) {
                    System.out.println("Error: Manufacture date cannot be after current date!");
                    nsx = null;
                }
            } catch (ParseException e) {
                System.out.println("Error: Invalid date format! Please use dd-MM-yyyy format.");
            }
        }

        // Validation cho hạn sử dụng
        Date hsd = null;
        while (hsd == null) {
            System.out.print("HSD (dd-MM-yyyy): ");
            String hsdStr = sc.nextLine();

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false); // Strict date parsing
                hsd = sdf.parse(hsdStr);

                // Kiểm tra hạn sử dụng phải sau ngày sản xuất
                if (hsd.before(nsx) || hsd.equals(nsx)) {
                    System.out.println("Error: Expiry date must be after manufacture date!");
                    hsd = null;
                }
            } catch (ParseException e) {
                System.out.println("Error: Invalid date format! Please use dd-MM-yyyy format.");
            }
        }

        SanPham sp = null;

        switch (maLoai.toUpperCase()) {
            case "PZA_TA":
                System.out.print("Size: ");
                String size = sc.nextLine();
                sp = new Pizza(maSP, tenSP, maLoai, gia, nsx, hsd, 0, size);
                break;
            case "HBG_TA":
                // Validation cho lựa chọn rau
                String nhapRau;
                while (true) {
                    System.out.print("Them Rau (Rau/Khong rau): ");
                    nhapRau = sc.nextLine().trim();
                    if (nhapRau.equalsIgnoreCase("Rau") || nhapRau.equalsIgnoreCase("Khong rau")) {
                        break;
                    } else {
                        System.out.println("Error: Please enter 'Rau' or 'Khong rau'!");
                    }
                }
                boolean rau = nhapRau.equalsIgnoreCase("Rau");
                sp = new Hamburger(maSP, tenSP, maLoai, gia, nsx, hsd, 0, rau);
                break;
            case "GRN_TA":

                String nhapCay;
                while (true) {
                    System.out.print("Vi cay (Cay/Khong cay): ");
                    nhapCay = sc.nextLine().trim();
                    if (nhapCay.equalsIgnoreCase("Cay") || nhapCay.equalsIgnoreCase("Khong cay")) {
                        break;
                    } else {
                        System.out.println("Error: Please enter 'Cay' or 'Khong cay'!");
                    }
                }
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
        }

        if (sp != null && soluong < danhsach.length) {
            danhsach[soluong++] = sp;
            System.out.println("Added new product: " + sp.getMaLoai() + "-" + sp.getTenSP());
            ghiVaoFile(sp);
            hienThiDS();
        } else {
            System.out.println("Cannot add product! List is full or invalid data.");
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

            // ho tro HOA DON
            // FileReader fr = new FileReader(
            // "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\SANPHAM\\SanPham.txt");
            FileReader fr = new FileReader("../SANPHAM/SanPham.txt");
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("../SANPHAM/SanPham.txt"))) {
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
                    line += "|" + ((Hamburger) sp).getRau();
                } else if (sp instanceof GaRan) {
                    line += "|" + ((GaRan) sp).getDoCay();
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
            System.out.println("===========DELETE PRODUCT===========");
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
            System.out.println("============SEARCH PRODUCT BY NAME============");
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
            System.out.println("============SEARCH PRODUCT BY ID============");
            System.out.print("Enter product ID: ");
            String maCanTim = sc.nextLine().trim().toLowerCase();
            boolean found = false;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null && danhsach[i].getMaLoai().toLowerCase().equals(maCanTim)) {
                    System.out.println("Found product: " + danhsach[i].toString());
                    found = true;
                }
            }

            if (found) {
                break; // Thoát khi tìm thấy
            } else {
                System.out.println("Not found product with ID: " + maCanTim);
                System.out.println("Please try again!");
            }
        }
    }

    public void suaSanPham() {
        while (true) {
            System.out.println("===== EDIT PRODUCT =====");
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
            } else {
                SanPham sp = danhsach[index];
                System.out.println("Current product info:");
                System.out.println(sp.toString());

                boolean continueEditing = true;

                while (continueEditing) {
                    System.out.println("\n===== EDIT MENU =====");
                    System.out.println("1. Edit name");
                    System.out.println("2. Edit price");
                    System.out.println("3. Edit manufacture date (NSX)");
                    System.out.println("4. Edit expiry date (HSD)");
                    System.out.println("5. Edit special attribute (size / rau / cay)");
                    System.out.println("0. Exit");
                    System.out.print("Select option: ");

                    int choice;
                    try {
                        choice = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid choice! Please enter a number.");
                        continue;
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    sdf.setLenient(false);

                    switch (choice) {
                        case 1:
                            System.out.print("Enter new name: ");
                            String newName = sc.nextLine().trim();
                            if (!newName.isEmpty()) {
                                sp.setTenSP(newName);
                            } else {
                                System.out.println("Error: Name cannot be empty!");
                            }
                            break;

                        case 2:
                            while (true) {
                                System.out.print("Enter new price: ");
                                String priceStr = sc.nextLine();
                                try {
                                    double newPrice = Double.parseDouble(priceStr);
                                    if (newPrice <= 0) {
                                        System.out.println("Error: Price must be greater than 0!");
                                        continue;
                                    }
                                    sp.setGia(newPrice);
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Error: Invalid price format! Please enter a valid number.");
                                }
                            }
                            break;

                        case 3:
                            Date newNsx = null;
                            while (newNsx == null) {
                                System.out.print("Enter new manufacture date (dd-MM-yyyy): ");
                                String nsxStr = sc.nextLine();
                                try {
                                    newNsx = sdf.parse(nsxStr);
                                    Date currentDate = new Date();

                                    // Kiểm tra ngày sản xuất không được sau ngày hiện tại
                                    if (newNsx.after(currentDate)) {
                                        System.out.println("Error: Manufacture date cannot be after current date!");
                                        newNsx = null;
                                    } else {
                                        sp.setNgaySX(newNsx);

                                        // Kiểm tra nếu HSD hiện tại trước NSX mới
                                        if (sp.getHanSD().before(newNsx) || sp.getHanSD().equals(newNsx)) {
                                            System.out.println(
                                                    "Warning: Current expiry date is before or equal to new manufacture date!");
                                            System.out.println("Please update expiry date as well.");
                                        }
                                    }
                                } catch (ParseException e) {
                                    System.out.println("Error: Invalid date format! Please use dd-MM-yyyy format.");
                                }
                            }
                            break;

                        case 4:
                            Date newHsd = null;
                            while (newHsd == null) {
                                System.out.print("Enter new expiry date (dd-MM-yyyy): ");
                                String hsdStr = sc.nextLine();
                                try {
                                    newHsd = sdf.parse(hsdStr);

                                    // Kiểm tra hạn sử dụng phải sau ngày sản xuất
                                    if (newHsd.before(sp.getNgaySX()) || newHsd.equals(sp.getNgaySX())) {
                                        System.out.println("Error: Expiry date must be after manufacture date!");
                                        newHsd = null;
                                    } else {
                                        sp.setHanSD(newHsd);
                                    }
                                } catch (ParseException e) {
                                    System.out.println("Error: Invalid date format! Please use dd-MM-yyyy format.");
                                }
                            }
                            break;

                        case 5:
                            String maLoai = sp.getMaLoai();
                            if (maLoai.equalsIgnoreCase("PZA_TA")) {
                                Pizza p = (Pizza) sp;
                                System.out.print("Enter new size: ");
                                String newSize = sc.nextLine().trim();
                                if (!newSize.isEmpty()) {
                                    p.setSize(newSize);
                                } else {
                                    System.out.println("Error: Size cannot be empty!");
                                }
                            } else if (maLoai.equalsIgnoreCase("HBG_TA")) {
                                Hamburger h = (Hamburger) sp;
                                String nhap;
                                while (true) {
                                    System.out.print("Rau / Khong rau: ");
                                    nhap = sc.nextLine().trim();
                                    if (nhap.equalsIgnoreCase("Rau") || nhap.equalsIgnoreCase("Khong rau")) {
                                        h.setRau(nhap.equalsIgnoreCase("Rau"));
                                        break;
                                    } else {
                                        System.out.println("Error: Please enter 'Rau' or 'Khong rau'!");
                                    }
                                }
                            } else if (maLoai.equalsIgnoreCase("GRN_TA")) {
                                GaRan g = (GaRan) sp;
                                String nhap;
                                while (true) {
                                    System.out.print("Cay / Khong cay: ");
                                    nhap = sc.nextLine().trim();
                                    if (nhap.equalsIgnoreCase("Cay") || nhap.equalsIgnoreCase("Khong cay")) {
                                        g.setDoCay(nhap.equalsIgnoreCase("Cay"));
                                        break;
                                    } else {
                                        System.out.println("Error: Please enter 'Cay' or 'Khong cay'!");
                                    }
                                }
                            } else {
                                System.out.println("This product type has no special attribute!");
                            }
                            break;

                        case 0:
                            System.out.println("Exit editing...");
                            return;

                        default:
                            System.out.println("Invalid choice!");
                            continue;
                    }

                    System.out.println("Updated product info:");
                    System.out.println(sp.toString());

                    // Ghi lại toàn bộ danh sách vào file
                    capNhatFileSauKhiXoa();
                    System.out.println("File updated successfully!");

                    // Hỏi người dùng có muốn tiếp tục chỉnh sửa sản phẩm này không
                    System.out.print("\nDo you want to continue editing this product? (yes/no): ");
                    String continueChoice = sc.nextLine().trim();
                    if (!continueChoice.equalsIgnoreCase("yes")) {
                        continueEditing = false;
                    }
                }
                break;
            }
        }
    }

    public static void main(String[] agrs) {
        DSachSP ds = new DSachSP(1000);
        ds.DocFile();
        ds.hienThiDS();
        // ds.themSanPham();
        ds.xoaSanPham();
        // ds.timKiemTheoTen();
        // ds.timKiemTheoMa();
    }
}
