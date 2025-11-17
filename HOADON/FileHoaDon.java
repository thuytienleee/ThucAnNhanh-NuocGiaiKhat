
package HOADON;

import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import CONNGUOI.NhanVien;
import CONNGUOI.KhachHang;
import CONNGUOI.DSachNV;
import CONNGUOI.DSachKH;
import SANPHAM.SanPham;
import SANPHAM.DSachSP;

public class FileHoaDon {
    private static final String FILE_PATH = "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\hoadon.txt";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void ghiVaoFile(HoaDon hd) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = hd.getMaHoaDon() + "|"
                    + (hd.getNhanVien() != null ? hd.getNhanVien().getMaNV() : "N/A") + "|"
                    + (hd.getKhachHang() != null ? hd.getKhachHang().getMaKH() : "N/A") + "|"
                    + hd.getTenHoaDon() + "|"
                    + hd.getDiaChi() + "|"
                    + hd.getDiemTichLuy() + "|"
                    + sdf.format(hd.getNgayLap()) + "|";

            // Lưu thông tin chi tiết sản phẩm
            SanPham[] dsChiTiet = hd.getDsChiTiet();
            int[] soLuongSP = hd.getSoLuongSP();
            int soSP = hd.getSoHoaDon();

            for (int i = 0; i < soSP; i++) {
                if (i > 0)
                    line += ";"; // Dùng dấu ; để phân cách các sản phẩm

                // Format: MaSP:TenSP:SoLuong:DonGia
                line += dsChiTiet[i].getMaSP() + ":"
                        + dsChiTiet[i].getTenSP() + ":"
                        + soLuongSP[i] + ":"
                        + dsChiTiet[i].getGia();
            }

            // Thêm tổng tiền vào cuối
            line += "|" + hd.tinhTongTien();

            bw.write(line);
            bw.newLine();
            System.out.println("Da ghi hoa don vao file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

    public static void capNhatFile(HoaDon[] danhSachHD, int soLuongHD) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < soLuongHD; i++) {
                HoaDon hd = danhSachHD[i];
                if (hd == null)
                    continue;

                String line = hd.getMaHoaDon() + "|"
                        + (hd.getNhanVien() != null ? hd.getNhanVien().getMaNV() : "N/A") + "|"
                        + (hd.getKhachHang() != null ? hd.getKhachHang().getMaKH() : "N/A") + "|"
                        + hd.getTenHoaDon() + "|"
                        + hd.getDiaChi() + "|"
                        + hd.getDiemTichLuy() + "|"
                        + sdf.format(hd.getNgayLap()) + "|";

                // Lưu thông tin chi tiết sản phẩm
                SanPham[] dsChiTiet = hd.getDsChiTiet();
                int[] soLuongSP = hd.getSoLuongSP();
                int soSP = hd.getSoHoaDon();

                for (int j = 0; j < soSP; j++) {
                    if (j > 0)
                        line += ";";
                    line += dsChiTiet[j].getMaSP() + ":"
                            + dsChiTiet[j].getTenSP() + ":"
                            + soLuongSP[j] + ":"
                            + dsChiTiet[j].getGia();
                }

                line += "|" + hd.tinhTongTien();

                bw.write(line);
                bw.newLine();
            }
            System.out.println("Da cap nhat file hoa don thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi cap nhat file: " + e.getMessage());
        }
    }

    public static HoaDon[] docFile(DSachNV dsNV, DSachKH dsKH, DSachSP dsSP) {
        HoaDon[] danhSach = new HoaDon[100];
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                String[] parts = line.split("\\|");
                if (parts.length < 7)
                    continue;

                String maHD = parts[0].trim();
                String maNV = parts[1].trim();
                String maKH = parts[2].trim();
                String tenHD = parts[3].trim();
                String diaChi = parts[4].trim();
                int diemTichLuy = Integer.parseInt(parts[5].trim());
                Date ngayLap = null;

                try {
                    ngayLap = sdf.parse(parts[6].trim());
                } catch (ParseException e) {
                    ngayLap = new Date();
                }

                // Tìm nhân viên
                NhanVien nv = null;
                if (!maNV.equals("N/A")) {
                    nv = dsNV.timKiemNhanVien(maNV);
                }

                // Tìm khách hàng
                KhachHang kh = null;
                if (!maKH.equals("N/A")) {
                    kh = dsKH.timKiemKH(maKH);
                }

                // Tạo hóa đơn
                HoaDon hd = new HoaDon(maHD, nv, kh, tenHD, diaChi, diemTichLuy, ngayLap);

                // Đọc thông tin chi tiết sản phẩm
                if (parts.length > 7 && !parts[7].trim().isEmpty()) {
                    String[] dsSanPham = parts[7].split(";");

                    for (String spInfo : dsSanPham) {
                        String[] chiTiet = spInfo.split(":");

                        if (chiTiet.length == 4) {
                            // Format: MaSP:TenSP:SoLuong:DonGia
                            String maSP = chiTiet[0].trim();
                            int soLuong = Integer.parseInt(chiTiet[2].trim());

                            SanPham sp = dsSP.timkiemSP(maSP);
                            if (sp != null) {
                                hd.themSanPham(sp, soLuong);
                            }
                        }
                    }
                }

                danhSach[count++] = hd;
            }
        } catch (IOException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
            return null;
        }

        return danhSach;
    }
}