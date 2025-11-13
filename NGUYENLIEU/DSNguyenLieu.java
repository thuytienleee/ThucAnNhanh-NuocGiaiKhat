// package NGUYENLIEU;
package NGUYENLIEU;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class DSNguyenLieu {
    private NguyenLieu[] ds;
    private int soLuong;

    public DSNguyenLieu() {
        this.ds = new NguyenLieu[100];
        this.soLuong = 0;
    }

    public DSNguyenLieu(int kichThuoc) {
        this.ds = new NguyenLieu[kichThuoc];
        this.soLuong = 0;
    }

    // ======== Kiểm tra mã nguyên liệu đã tồn tại trong file hay chưa ========
    public boolean tonTaiMa(String maKiemTra) {
        try (BufferedReader br = new BufferedReader(new FileReader("DSnguyenLieu.txt"))) {
            String st;
            while ((st = br.readLine()) != null) {
                String[] str = st.split("\\|");
                if (str.length < 4)
                    continue;
                String maNL = str[0].trim();
                if (maNL.equalsIgnoreCase(maKiemTra.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    // ======== Thêm nguyên liệu ========
    public void AddNL() {
        this.ds = new NguyenLieu[100];
        this.soLuong = 0;

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
            String ma;
            while (true) {
                System.out.print("Ma nguyen lieu: ");
                ma = sc.nextLine();

                boolean trung = tonTaiMa(ma);
                for (int j = 0; j < soLuong; j++) {
                    if (ds[j].getMaNL().equalsIgnoreCase(ma)) {
                        trung = true;
                    }
                }
                if (trung) {
                    System.out.println("Ma nguyen lieu da ton tai, vui long nhap ma khac!");
                } else {
                    break;
                }
            }

            System.out.print("Ten nguyen lieu: ");
            String ten = sc.nextLine();

            System.out.print("Don vi tinh: ");
            String dvt = sc.nextLine();

            System.out.print("So luong: ");
            double sl = sc.nextDouble();
            sc.nextLine();

            ds[soLuong++] = new NguyenLieu(ma, ten, dvt, sl);
        }
    }

    // ======== Hàm sửa nguyên liệu ========
    public void suaNguyenLieu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nguyen lieu can sua: ");
        String maSua = sc.nextLine().trim();
        boolean find = false;

        // Đọc file vào mảng
        NguyenLieu[] dsnl = new NguyenLieu[0];
        try (BufferedReader br = new BufferedReader(new FileReader("DSnguyenLieu.txt"))) {
            String st;
            while ((st = br.readLine()) != null) {
                String[] str = st.split("\\|");

                String maNL = str[0].trim();
                String tenNL = str[1].trim();
                String dvt = str[2].trim();
                double sl = Double.parseDouble(str[3].trim());

                NguyenLieu nl = new NguyenLieu(maNL, tenNL, dvt, sl);
                dsnl = Arrays.copyOf(dsnl, dsnl.length + 1);
                dsnl[dsnl.length - 1] = nl;
            }
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
            return;
        }

        // Tìm và sửa nguyên liệu
        for (NguyenLieu nl : dsnl) {
            if (nl.getMaNL().equalsIgnoreCase(maSua)) {
                find = true;
                System.out.println("\n=== Thong tin hien tai cua nguyen lieu ===");
                System.out.println(nl);

                System.out.println("\nNhap thong tin moi (bo qua neu khong muon thay doi):");

                System.out.print("Ten nguyen lieu moi: ");
                String tenMoi = sc.nextLine().trim();
                if (!tenMoi.isEmpty())
                    nl.setTenNL(tenMoi);

                System.out.print("Don vi tinh moi: ");
                String dvtMoi = sc.nextLine().trim();
                if (!dvtMoi.isEmpty())
                    nl.setDonViTinh(dvtMoi);

                System.out.print("So luong moi: ");
                String slMoi = sc.nextLine().trim();
                if (!slMoi.isEmpty()) {
                    try {
                        double slValue = Double.parseDouble(slMoi);
                        nl.setSoLuong(slValue);
                    } catch (NumberFormatException e) {
                        System.out.println("So luong khong hop le, giu nguyen gia tri cu!");
                    }
                }

                System.out.println("\nDa cap nhat thong tin nguyen lieu!");
                break;
            }
        }

        if (!find) {
            System.out.println("Khong tim thay ma nguyen lieu: " + maSua);
            return;
        }

        // Ghi lại file sau khi sửa
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DSnguyenLieu.txt", false))) {
            for (NguyenLieu x : dsnl) {
                bw.write(x.toString());
                bw.newLine();
            }
            System.out.println("Da ghi lai file DSnguyenLieu.txt sau khi sua!");
        } catch (Exception e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }

        docFile();
    }

    // ======== Hàm xóa nguyên liệu ========
    public void xoaNguyenLieu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nguyen lieu can xoa: ");
        String maXoa = sc.nextLine().trim();
        boolean find = false;

        // Doc file
        NguyenLieu[] dsnl = new NguyenLieu[0];
        try {
            BufferedReader br = new BufferedReader(new FileReader("DSnguyenLieu.txt"));
            String st;
            while ((st = br.readLine()) != null) {
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

        // Xoa nguyen lieu
        NguyenLieu[] dsNew = new NguyenLieu[0];
        for (NguyenLieu nl : dsnl) {
            if (!nl.getMaNL().equalsIgnoreCase(maXoa)) {
                dsNew = Arrays.copyOf(dsNew, dsNew.length + 1);
                dsNew[dsNew.length - 1] = nl;
            } else {
                find = true;
            }
        }
        if (!find) {
            System.out.println("Khong tim thay ma nguyen lieu: " + maXoa);
            return;
        }

        // Ghi lại file sau xóa
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DSnguyenLieu.txt", false))) {
            for (NguyenLieu x : dsNew) {
                bw.write(x.toString());
                bw.newLine();
            }
            System.out.println("Da xoa nguyen lieu " + maXoa);
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
    public void docFile() {
        String str[];
        NguyenLieu nl;
        NguyenLieu dsnl[] = new NguyenLieu[0];
        try {
            FileReader fr = new FileReader("DSnguyenLieu.txt");
            BufferedReader br = new BufferedReader(fr);
            while (true) {
                String st = br.readLine();
                if (st == null) {
                    break;
                }
                str = st.split("\\|");

                String maNL = str[0].trim();
                String tenNL = str[1].trim();
                String dvt = str[2].trim();
                Double sl = Double.parseDouble(str[3].trim());

                nl = new NguyenLieu(maNL, tenNL, dvt, sl);
                dsnl = Arrays.copyOf(dsnl, dsnl.length + 1);
                dsnl[dsnl.length - 1] = nl;
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
        for (NguyenLieu x : dsnl) {
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
