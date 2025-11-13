package PHIEUNHAP;

import CONNGUOI.NhanVien;
import NGUYENLIEU.NguyenLieu;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DSPhieuNhap {
    private PhieuNhap[] dsPhieu;
    private int soLuongPhieu;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public DSPhieuNhap() {
        this.dsPhieu = new PhieuNhap[100];
        this.soLuongPhieu = 0;
    }

    // ======== GETTER ========
    public PhieuNhap[] getDsPhieu() {
        return dsPhieu;
    }

    public int getSoLuongPhieu() {
        return soLuongPhieu;
    }

    public void setSoLuongPhieu(int soLuongPhieu) {
        this.soLuongPhieu = soLuongPhieu;
    }

    // ======== ĐỌC DANH SÁCH NGUYÊN LIỆU TỪ FILE ========
    private NguyenLieu[] docDSNguyenLieu() {
        NguyenLieu[] dsnl = new NguyenLieu[0];
        try {
            FileReader fr = new FileReader(
                    "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\DSnguyenLieu.txt");
            BufferedReader br = new BufferedReader(fr);
            String st;

            while ((st = br.readLine()) != null) {
                if (st.trim().isEmpty())
                    continue;

                String[] str = st.split("\\|");
                if (str.length < 4)
                    continue;

                String maNL = str[0].trim();
                String tenNL = str[1].trim();
                String dvt = str[2].trim();
                double sl = Double.parseDouble(str[3].trim());

                NguyenLieu nl = new NguyenLieu(maNL, tenNL, dvt, sl);
                dsnl = Arrays.copyOf(dsnl, dsnl.length + 1);
                dsnl[dsnl.length - 1] = nl;
            }

            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Loi doc file nguyen lieu: " + e.getMessage());
        }
        return dsnl;
    }

    // ======== TÌM NGUYÊN LIỆU THEO MÃ ========
    private NguyenLieu timNguyenLieu(String maNL, NguyenLieu[] dsNL) {
        for (NguyenLieu nl : dsNL) {
            if (nl != null && nl.getMaNL().equalsIgnoreCase(maNL)) {
                return new NguyenLieu(nl.getMaNL(), nl.getTenNL(),
                        nl.getDonViTinh(), nl.getSoLuong());
            }
        }
        return null;
    }

    // ======== ĐỌC FILE PHIẾU NHẬP ========
    public void docFile() {
        NguyenLieu[] dsNL = docDSNguyenLieu();

        try {
            FileReader fr = new FileReader("E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\phieunhap.txt");
            BufferedReader br = new BufferedReader(fr);
            String st;

            while ((st = br.readLine()) != null) {
                if (st.trim().isEmpty())
                    continue;

                // Đọc thông tin phiếu nhập
                String[] info = st.split("\\|");
                if (info.length < 12)
                    continue;

                String maPhieu = info[0].trim();
                String tenPhieu = info[1].trim();
                Date ngayNhap = sdf.parse(info[2].trim());
                String maNV = info[3].trim();
                String tenNV = info[4].trim();
                String soDT = info[5].trim();
                String diaChi = info[6].trim();
                String ngaySinh = info[7].trim();
                String gioiTinh = info[8].trim();
                double luong = Double.parseDouble(info[9].trim());
                String chucVu = info[10].trim();
                int namVaoLam = Integer.parseInt(info[11].trim());

                // Tạo nhân viên
                NhanVien nv = new NhanVien(maNV, tenNV, soDT, diaChi, ngaySinh,
                        gioiTinh, luong, chucVu, namVaoLam);

                // Đọc số lượng nguyên liệu trong phiếu
                st = br.readLine();
                if (st == null)
                    break;
                int soLuongNL = Integer.parseInt(st.trim());

                // Tạo phiếu nhập
                PhieuNhap phieu = new PhieuNhap(maPhieu, tenPhieu, ngayNhap, nv, soLuongNL);

                // Đọc danh sách nguyên liệu trong phiếu
                for (int i = 0; i < soLuongNL; i++) {
                    st = br.readLine();
                    if (st == null)
                        break;

                    String[] nlInfo = st.split("\\|");
                    if (nlInfo.length < 3)
                        continue;

                    String maNL = nlInfo[0].trim();
                    double soLuong = Double.parseDouble(nlInfo[1].trim());
                    double donGia = Double.parseDouble(nlInfo[2].trim());

                    // Tìm nguyên liệu từ danh sách
                    NguyenLieu nl = timNguyenLieu(maNL, dsNL);
                    if (nl != null) {
                        nl.setSoLuong(soLuong);
                        nl.setDonGia(donGia);
                        phieu.ThemCTPhieuNhap(nl);
                    }
                }

                // Đọc dòng trống giữa các phiếu
                st = br.readLine();

                // Thêm phiếu vào danh sách
                if (soLuongPhieu < dsPhieu.length) {
                    dsPhieu[soLuongPhieu++] = phieu;
                }
            }

            br.close();
            fr.close();
            System.out.println("Doc file phieu nhap thanh cong! Tong so phieu: " + soLuongPhieu);
        } catch (Exception e) {
            System.out.println("Loi doc file phieu nhap: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ======== GHI FILE PHIẾU NHẬP ========
    public void ghiFile() {
        try {
            FileWriter fw = new FileWriter("E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\phieunhap.txt",
                    false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < soLuongPhieu; i++) {
                PhieuNhap phieu = dsPhieu[i];
                NhanVien nv = phieu.getNhanVienNhapHang();

                // Ghi thông tin phiếu
                bw.write(phieu.getMaPhieuNhap() + " | " +
                        phieu.getTenPhieuNhap() + " | " +
                        sdf.format(phieu.getNgayNhap()) + " | " +
                        nv.getMaNV() + " | " +
                        nv.getTen() + " | " +
                        nv.getSoDT() + " | " +
                        nv.getDiaChi() + " | " +
                        nv.getNgaySinh() + " | " +
                        nv.getGioiTinh() + " | " +
                        nv.getLuong() + " | " +
                        nv.getChucVuNV() + " | " +
                        nv.getNamVaoLam());
                bw.newLine();

                // Ghi số lượng nguyên liệu
                bw.write(String.valueOf(phieu.getSoLuongNL()));
                bw.newLine();

                // Ghi danh sách nguyên liệu
                NguyenLieu[] dsNL = phieu.getDsNL();
                for (int j = 0; j < phieu.getSoLuongNL(); j++) {
                    bw.write(dsNL[j].getMaNL() + " | " +
                            dsNL[j].getSoLuong() + " | " +
                            dsNL[j].getDonGia());
                    bw.newLine();
                }

                // Ghi dòng trống giữa các phiếu (trừ phiếu cuối)
                if (i < soLuongPhieu - 1) {
                    bw.newLine();
                }
            }

            bw.close();
            fw.close();
            System.out.println("Ghi file phieu nhap thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }
}