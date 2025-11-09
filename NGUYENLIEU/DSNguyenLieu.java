// package NGUYENLIEU;
package NGUYENLIEU;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class DSNguyenLieu {
    private NguyenLieu[] ds;
    private int soLuong;

    public DSNguyenLieu(){}

    public DSNguyenLieu(int kichThuoc) {
        this.ds = new NguyenLieu[kichThuoc];
        this.soLuong = 0;
    }

    // ======== Thêm nguyên liệu ========
    public void AddNL() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong nguyen lieu muon them: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        for (int i = 0; i < n; i++) {
            if (soLuong >= ds.length) {
                System.out.println("Danh sach da day, khong the them!");
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

            ds[soLuong++] = new NguyenLieu(ma, ten, dvt, sl);
        }
        sc.close();
    }

    // ======== Hàm xóa nguyên liệu ========
    public void xoaNguyenLieu(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nguyen lieu can xoa: ");
        String maXoa = sc.nextLine().trim();
        boolean find = false;
        
        //Doc file
        NguyenLieu[] dsnl = new NguyenLieu[0];
        try {
            BufferedReader br = new BufferedReader(new FileReader("DSnguyenLieu.txt"));
            String st;
            while ((st = br.readLine()) != null){
                String[] str = st.split("\\|");

                String maNL = str[0].trim();
                String tenNL = str[1].trim();
                String dvt = str[2].trim();
                Double sl = Double.parseDouble(str[3].trim());

                NguyenLieu nl = new NguyenLieu(maNL, tenNL, dvt, sl);
                dsnl = Arrays.copyOf(dsnl, dsnl.length + 1);
                dsnl[dsnl.length - 1] = nl;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        //Xoa nguyen lieu
        NguyenLieu[] dsNew = new NguyenLieu[0];
        for (NguyenLieu nl:dsnl){
            if (!nl.getMaNL().equalsIgnoreCase(maXoa)){
                dsNew = Arrays.copyOf(dsNew, dsNew.length+1);
                dsNew[dsNew.length-1] = nl;
            } else {
                find = true;
            }
        }
        if (!find) {
        System.out.println("Khong tim thay ma nguyen lieu: " + maXoa);
        return;
        }

        //Ghi lại file sau xóa
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DSnguyenLieu.txt", false))) {
            for (NguyenLieu x : dsNew) {
            bw.write(x.toString());
            bw.newLine();
        }
            System.out.println("Da xoa nguyen lieu "+maXoa);
        } catch (Exception e) {
            System.out.println(e);
        }
        docFile();
    }

    // ======== Ghi danh sách nguyên liệu vào file ========
    public void ghiFile() {
        try {
            FileWriter fw = new FileWriter("DSnguyenLieu.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < soLuong; i++) {
                bw.write(ds[i].toString() + "\n");
            }

            bw.close();
            fw.close();

            System.out.println("Ghi file thanh cong vao DSnguyenLieu.txt!");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    // ======== Hiển thị danh sách nguyên liệu ========
    public void hienThiDanhSach() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("            Danh sach nguyen lieu vua duoc them: ");
        System.out.println("+----------+----------------------+--------------+----------+");
        System.out.println("| MaNL     | TenNL                | DonViTinh    | SoLuong  |");
        System.out.println("+----------+----------------------+--------------+----------+");

        for (int i = 0; i < soLuong; i++) {
            System.out.println(ds[i]);
        }

        System.out.println("+----------+----------------------+--------------+----------+");
    }

    // ======== Đọc file DSNL ========
    public void docFile(){
        String str[];
        NguyenLieu nl;
        NguyenLieu dsnl[] = new NguyenLieu[0];
        try {
            FileReader fr = new FileReader("DSnguyenLieu.txt");
            BufferedReader br = new BufferedReader(fr);
            while (true) { 
                String st = br.readLine();
                if(st == null){ break; }
                str = st.split("\\|");

                String maNL = str[0].trim();
                String tenNL = str[1].trim();
                String dvt = str[2].trim();
                Double sl = Double.parseDouble(str[3].trim());

                nl = new NguyenLieu(maNL, tenNL, dvt, sl);
                dsnl = Arrays.copyOf(dsnl, dsnl.length + 1);
                dsnl[dsnl.length-1] = nl;
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("-------------------------------------------------------------");
        System.out.println("            Danh sach nguyen lieu cua cua hang: ");
        System.out.println("+----------+----------------------+--------------+----------+");
        System.out.println("| MaNL     | TenNL                | DonViTinh    | SoLuong  |");
        System.out.println("+----------+----------------------+--------------+----------+");
        for (NguyenLieu x:dsnl){
            System.out.println(x);
        }        
        System.out.println("+----------+----------------------+--------------+----------+");
    }

    // ======== Hàm main test thử ========
    public static void main(String[] args) {
        DSNguyenLieu ds = new DSNguyenLieu(100);
        ds.AddNL();
        ds.hienThiDanhSach();
        ds.ghiFile();
        ds.docFile();
    } 
}
