// package NHANVIEN;
package CONNGUOI;

<<<<<<< HEAD
import java.util.Scanner;

=======
>>>>>>> 6071d028767abc0704412ccd0f261e51a5921d34
import CONNGUOI.NhanVien;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.text.ParseException;

public class DSachNV {
    private NhanVien[] danhsach;
    private int soluong;
    static Scanner sc = new Scanner(System.in);

    public DSachNV(int kichThuoc) {
        danhsach = new NhanVien[kichThuoc];
        soluong = 0;
    }

    public void themNhanVien() {
        System.out.println("==================== THEM NHAN VIEN MOI ===================");
        System.out.print("Enter ID STAFF: ");
        String maNV = sc.nextLine();

        System.out.print("Enter NAME STAFF: ");
        String tenNV = sc.nextLine();

        System.out.print("Enter AGE STAFF: ");
        int tuoiNV = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Gender(Nam/Nu): ");
        String gioitinh = sc.nextLine();

        System.out.print("Enter DATE OF BIRTH (dd-MM-yyyy): ");
        String ngaySinhStr = sc.nextLine();

        System.out.print("Enter PHONE NUMBER: ");
        String sdtNV = sc.nextLine();

        System.out.print("Enter ADDRESS: ");
        String diaChiNV = sc.nextLine();

        System.out.print("Enter YEAR OF JOIN: ");
        int namVaoLam = Integer.parseInt(sc.nextLine());

        System.out.print("Enter POSITION: ");
        String chucVuNV = sc.nextLine();

        System.out.print("Enter SALARY: ");
        double luong = Double.parseDouble(sc.nextLine());

        Date ngaySinh;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            ngaySinh = sdf.parse(ngaySinhStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy.");
            ngaySinh = new Date();
        }

        NhanVien nv = new NhanVien(maNV, tenNV, tuoiNV, gioitinh, ngaySinh, sdtNV, diaChiNV, luong, chucVuNV, namVaoLam);
        danhsach[soluong++] = nv;
        System.out.println("New staff added successfully!");
        System.out.println("Added new staff: " + nv.getMaNV() + "-" + nv.getTenNV());
        GhiFile(nv);
        hienThiDanhSach();
    }

    public void xoaNhanVien() {
        while (true) {
            System.out.println("===================== DELETE STAFF ===================");
            System.out.print("Enter STAFF ID: ");
            String maNV = sc.nextLine().trim();
            int index = -1;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i].getMaNV().equalsIgnoreCase(maNV)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Staff ID not found. Please try again.");
            } else {
                for (int i = index; i < soluong - 1; i++) {
                    danhsach[i] = danhsach[i + 1];
                }
                danhsach[--soluong] = null;
                System.out.println("Staff deleted successfully!");
                CapNhatFile();
                hienThiDanhSach();
                break;
            }
        }
    }

<<<<<<< HEAD
    public void CapNhatFile() {
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("NhanVien.txt"))) {
        for (int i = 0; i < soluong; i++) {
            NhanVien nv = danhsach[i];
            if (nv == null) continue;

            String line = nv.getMaNV() + "|" 
                        + nv.getTenNV() + "|" 
                        + nv.getTuoiNV() + "|" 
                        + nv.getGioitinh() + "|" 
                        + sdf.format(nv.getNgaySinh()) + "|" 
                        + nv.getSdtNV() + "|" 
                        + nv.getDiaChiNV() + "|" 
                        + nv.getNamVaoLam() + "|" 
                        + nv.getChucVuNV() + "|" 
                        + nv.getLuong();

            bw.write(line);
            bw.newLine();
=======
    public boolean thayDoiThongTinNhanVien(String maNhanVien, String ten, int tuoi, String sdt, String diaChi) {
        NhanVien nv = timKiemNhanVien(maNhanVien);
        if (nv != null) {
            nv.setTen(ten);
            // nv.setTuoi(tuoi);
            nv.setSoDT(sdt);
            nv.setDiaChi(diaChi);
            return true;
>>>>>>> 6071d028767abc0704412ccd0f261e51a5921d34
        }
        System.out.println("File updated successfully!");
    } catch (IOException e) {
        System.out.println("Error updating file: " + e.getMessage());
    }
    }

    public void SuaThongTinNhanVien() {
        while (true) {
            System.out.println("===================== EDIT STAFF INFO ===================");
            System.out.print("Enter STAFF ID: ");
            String maNV = sc.nextLine().trim();
            int index = -1;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null && danhsach[i].getMaNV().equalsIgnoreCase(maNV)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Staff ID not found. Please try again.");
                continue;
            }

            NhanVien nv = danhsach[index];
            System.out.println("Current info: ");
            System.out.println(nv.toString());

            boolean editing = true;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            while (editing) {
                System.out.println("\n===================== EDIT MENU ===================");
                System.out.println("Select field to edit:");
                System.out.println("1. Name");
                System.out.println("2. Age");
                System.out.println("3. Gender");
                System.out.println("4. Date of Birth");
                System.out.println("5. Phone Number");
                System.out.println("6. Address");
                System.out.println("7. Year of Join");
                System.out.println("8. Position");
                System.out.println("9. Salary");
                System.out.println("0. Finish Editing");
                System.out.println("====================================================");
                System.out.print("Your choice: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter new Name: ");
                        nv.setTenNV(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter new Age: ");
                        nv.setTuoiNV(Integer.parseInt(sc.nextLine()));
                        break;
                    case 3:
                        System.out.print("Enter new Gender: ");
                        nv.setGioitinh(sc.nextLine());
                        break;
                    case 4:
                        System.out.print("Enter new Date of Birth (dd-MM-yyyy): ");
                        try {
                            nv.setNgaySinh(sdf.parse(sc.nextLine()));
                        } catch (Exception e) {
                            System.out.println("Invalid date format. Please use dd-MM-yyyy.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter new Phone Number: ");
                        nv.setSdtNV(sc.nextLine());
                        break;
                    case 6:
                        System.out.print("Enter new Address: ");
                        nv.setDiaChiNV(sc.nextLine());
                        break;
                    case 7:
                        System.out.print("Enter new Year of Join: ");
                        nv.setNamVaoLam(Integer.parseInt(sc.nextLine()));
                        break;
                    case 8:
                        System.out.print("Enter new Position: ");
                        nv.setChucVuNV(sc.nextLine());
                        break;
                    case 9:
                        System.out.print("Enter new Salary: ");
                        nv.setLuong(Double.parseDouble(sc.nextLine()));
                        break;
                    case 0:
                        System.out.println("Exit editing...");
                        editing = false;
                        continue;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                }
                System.out.println("Updated staff info:");
                System.out.println(nv.toString());
                CapNhatFile();
                System.out.println("\nDo you want to continue? (yes/no): ");
                String ans = sc.nextLine().trim();
                if (!ans.equalsIgnoreCase("yes")) {
                    editing = false;
                }
                break;
            }
        }
    }

<<<<<<< HEAD
    public void timKiemNhanVienTheoMa() {
        while (true) {
            System.out.println("===================== SEARCH STAFF BY ID ===================");
            System.out.print("Enter STAFF ID: ");
            String maCanTim = sc.nextLine().trim().toLowerCase();
            boolean found = false;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null) {
                    String maNV = danhsach[i].getMaNV().toLowerCase();
                    if (maNV.equalsIgnoreCase(maCanTim)) {
                        System.out.println("Found product: " + danhsach[i].toString());
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("Staff ID not found. Please try again.");
            } else {
                break;
            }
=======
    public boolean chamCong(String maNhanVien) {
        NhanVien nv = timKiemNhanVien(maNhanVien);
        if (nv != null) {
            // nv.setChamCong(true);
            return true;
>>>>>>> 6071d028767abc0704412ccd0f261e51a5921d34
        }
    }

<<<<<<< HEAD
    public void DocFile() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            FileReader fr = new FileReader("NhanVien.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                String[] p = line.split("\\|");
                String maNV = p[0];
                String tenNV = p[1].trim();
                int tuoiNV = Integer.parseInt(p[2]);
                String gioitinh = p[3].trim();
                Date ngaySinh = sdf.parse(p[4]);
                String sdtNV = p[5];
                String diaChiNV = p[6].trim();
                int namVaoLam = Integer.parseInt(p[7]);
                String chucVuNV = p[8].trim();
                double luong = Double.parseDouble(p[9]);

                NhanVien nv = null;
                nv = new NhanVien(maNV, tenNV, tuoiNV, gioitinh, ngaySinh, sdtNV, diaChiNV, luong, chucVuNV, namVaoLam);
                danhsach[soluong++] = nv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GhiFile(NhanVien nv) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            FileWriter fw = new FileWriter("NhanVien.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            String line = nv.getMaNV() + "|"
                    + nv.getTenNV() + "|"
                    + nv.getTuoiNV() + "|"
                    + nv.getGioitinh() + "|"
                    + sdf.format(nv.getNgaySinh()) + "|"
                    + nv.getSdtNV() + "|"
                    + nv.getDiaChiNV() + "|"
                    + nv.getNamVaoLam() + "|"
                    + nv.getChucVuNV() + "|"
                    + nv.getLuong();

            bw.write(line);
            bw.newLine();
            bw.close();
            fw.close();
            System.out.println("Ghi file NhanVien.txt thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

=======
>>>>>>> 6071d028767abc0704412ccd0f261e51a5921d34
    public void hienThiDanhSach() {
        System.out.print(
                "+-----------------------------------------------------------------------------------------------------------------------------------+\n");
        System.out.print(
                "|                                                           STAFF INFOMATION                                                        |\n");
        System.out.print(
                "+-----------------------------------------------------------------------------------------------------------------------------------+\n");
        System.out.print(String.format(" %-5s| %-20s | %-2s | %-5s | %-9s | %-12s | %-15s | %-10s | %-10s | %-10s%n",
                "ID", "NAME", "AGE", "GENDER", "DATE", "PHONE", "ADDRESS", "YEAR OF JOIN", "POSITION", "SALARY"));
        System.out.print(
                "+-----------------------------------------------------------------------------------------------------------------------------------+\n");
        for (NhanVien nv : danhsach) {
            if (nv != null) {
                System.out.println(nv.toString());
            }
        }
    }

    public static void main(String[] args) {
        DSachNV dsnv = new DSachNV(100);
        dsnv.DocFile();
        dsnv.hienThiDanhSach();
        //dsnv.themNhanVien();
        //dsnv.xoaNhanVien();
        dsnv.SuaThongTinNhanVien();
        //dsnv.timKiemNhanVienTheoMa();

    }
}
