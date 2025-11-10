package KHO;

import NGUYENLIEU.*;
import PHIEUNHAP.*;
import java.io.*;
import java.util.Arrays;

public class Kho {
    private NguyenLieu[] dsKho;
    private int soLuongTon;

    public Kho() {
        this.dsKho = new NguyenLieu[0];
        this.soLuongTon = 0;
    }

    // ======== Đọc danh sách nguyên liệu tồn kho từ file ========
    public void docFile() {
        NguyenLieu[] dsnl = new NguyenLieu[0];
        String str[];
        NguyenLieu nl;

        try {
            FileReader fr = new FileReader("DSnguyenLieu.txt");
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String st = br.readLine();
                if (st == null) break;

                if (st.trim().isEmpty()) continue;

                str = st.split("\\|");
                if (str.length < 4) continue;

                String maNL = str[0].trim();
                String tenNL = str[1].trim();
                String dvt = str[2].trim();
                double sl = Double.parseDouble(str[3].trim());

                nl = new NguyenLieu(maNL, tenNL, dvt, sl);
                dsnl = Arrays.copyOf(dsnl, dsnl.length + 1);
                dsnl[dsnl.length - 1] = nl;
            }

            br.close();
            fr.close();

            this.dsKho = dsnl;
            this.soLuongTon = dsnl.length;

        } catch (Exception e) {
            System.out.println("Loi doc file ton kho: " + e.getMessage());
        }
    }

    // ======== Hiển thị danh sách tồn kho ========
    public void hienThiTonKho() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("               Danh Sach Nguyen Lieu Trong Kho               ");
        System.out.println("+----------+----------------------+--------------+----------+");
        System.out.println("| MaNL     | TenNL                | DonViTinh    | SoLuong  |");
        System.out.println("+----------+----------------------+--------------+----------+");

        for (NguyenLieu x : dsKho) {
            System.out.println(x);
        }

        System.out.println("+----------+----------------------+--------------+----------+");
        System.out.println("Tong so nguyen lieu trong kho: " + soLuongTon);
    }


    // ======== Cập nhật sau khi nhập ========
    public void capNhatSauKhiNhap(PhieuNhap phieu) {
        if (phieu == null) {
            System.out.println("Phieu nhap khong hop le");
            return;
        }

        NguyenLieu[] ds = phieu.getDsNL();
        if (ds == null || ds.length == 0) {
            System.out.println("Phieu nhap khong co nguyen lieu");
            return;
        }

        System.out.println("Cap nhat kho tu phieu nhap: " + phieu.getMaPhieuNhap());

        for (int i = 0; i < phieu.getSoLuongNL(); i++) {
            NguyenLieu nlPhieu = ds[i];
            boolean found = false;

            for (NguyenLieu x : dsKho) {
                if (x.getMaNL().equalsIgnoreCase(nlPhieu.getMaNL())) {
                    x.tangSoLuong(nlPhieu.getSoLuong());
                    found = true;
                    break;
                }
            }

            if (!found) {
                dsKho = Arrays.copyOf(dsKho, dsKho.length + 1);
                dsKho[dsKho.length - 1] = nlPhieu;
                soLuongTon++;
            }
        }

        System.out.println("Da cap nhat ton kho tu phieu nhap " + phieu.getMaPhieuNhap());
    }

    // ======== Cập nhật tồn kho sau khi chế biến (xuất kho) ========
/*  public void capNhatSauKhiCheBien(String maNL, double soLuongSD) {
        for (NguyenLieu x : dsKho) {
            if (x.getMaNL().equalsIgnoreCase(maNL)) {
                x.giamSoluong(soLuongSD);
                System.out.println("Da tru kho cho " + maNL + ": -" + soLuongSD);
                return;
            }
        }
        System.out.println("Khong tim thay ma nguyen lieu " + maNL + " trong kho!");
    }
*/

    // ======== Ghi lại danh sách kho ra file ========
    public void ghiFile() {
        try {
            FileWriter fw = new FileWriter("DSnguyenLieu.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (NguyenLieu x : dsKho) {
                bw.write(String.format("%s | %s | %s | %.2f", 
                    x.getMaNL(), x.getTenNL(), x.getDonViTinh(), x.getSoLuong()));
                bw.newLine();
            }

            bw.close();
            fw.close();

            System.out.println("Da cap nhat kho!");
        } catch (Exception e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public NguyenLieu[] getDsKho() {
        return dsKho;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }
}
