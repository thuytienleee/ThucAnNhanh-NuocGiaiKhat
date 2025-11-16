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
    private static final String FILE_PATH = "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\hoadontest.txt";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // Ghi một hóa đơn vào file (append mode)
    public static void ghiVaoFile(HoaDon hd) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = hd.getMaHoaDon() + "|"
                    + (hd.getNhanVien() != null ? hd.getNhanVien().getMaNV() : "N/A") + "|"
                    + (hd.getKhachHang() != null ? hd.getKhachHang().getMaKH() : "N/A") + "|"
                    + hd.getTenHoaDon() + "|"
                    + hd.getDiaChi() + "|"
                    + hd.getDiemTichLuy() + "|"
                    + sdf.format(hd.getNgayLap()) + "|";

            // Thêm danh sách mã sản phẩm
            SanPham[] dsChiTiet = hd.getDsChiTiet();
            int soSP = hd.getSoHoaDon();
            for (int i = 0; i < soSP; i++) {
                if (i > 0)
                    line += ",";
                line += dsChiTiet[i].getMaSP();
            }

            bw.write(line);
            bw.newLine();
            System.out.println("Da ghi hoa don vao file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

    // Cập nhật toàn bộ file sau khi xóa
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

                // Thêm danh sách mã sản phẩm
                SanPham[] dsChiTiet = hd.getDsChiTiet();
                int soSP = hd.getSoHoaDon();
                for (int j = 0; j < soSP; j++) {
                    if (j > 0)
                        line += ",";
                    line += dsChiTiet[j].getMaSP();
                }

                bw.write(line);
                bw.newLine();
            }
            System.out.println("Da cap nhat file hoa don thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi cap nhat file: " + e.getMessage());
        }
    }

    // Đọc danh sách hóa đơn từ file
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
                    kh = timKhachHang(dsKH, maKH);
                }

                // Tạo hóa đơn
                HoaDon hd = new HoaDon(maHD, nv, kh, tenHD, diaChi, diemTichLuy, ngayLap);

                // Thêm sản phẩm vào hóa đơn
                if (parts.length > 7 && !parts[7].trim().isEmpty()) {
                    String[] maSPs = parts[7].split(",");
                    for (String maSP : maSPs) {
                        SanPham sp = dsSP.timkiemSP(maSP.trim());
                        if (sp != null) {
                            hd.themSanPham(sp);
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

    // Phương thức hỗ trợ tìm khách hàng
    private static KhachHang timKhachHang(DSachKH dsKH, String maKH) {
        return dsKH.timKiemKH(maKH);
    }

    /*
     * Phương thức hỗ trợ tìm sản phẩm (sử dụng reflection để truy cập private
     * // field)
     * private static SanPham timSanPham(DSachSP dsSP, String maSP) {
     * try {
     * java.lang.reflect.Field danhSachField =
     * DSachSP.class.getDeclaredField("danhsach");
     * danhSachField.setAccessible(true);
     * SanPham[] danhSach = (SanPham[]) danhSachField.get(dsSP);
     * 
     * java.lang.reflect.Field soLuongField =
     * DSachSP.class.getDeclaredField("soluong");
     * soLuongField.setAccessible(true);
     * int soLuong = soLuongField.getInt(dsSP);
     * 
     * for (int i = 0; i < soLuong; i++) {
     * if (danhSach[i] != null && danhSach[i].getMaSP().equalsIgnoreCase(maSP)) {
     * return danhSach[i];
     * }
     * }
     * } catch (Exception e) {
     * System.out.println("Loi khi tim san pham: " + e.getMessage());
     * }
     * return null;
     * }
     */
}