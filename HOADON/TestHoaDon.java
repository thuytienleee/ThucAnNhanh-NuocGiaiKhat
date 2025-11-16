package HOADON;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import CONNGUOI.*;
import SANPHAM.*;

public class TestHoaDon {
    static Scanner sc = new Scanner(System.in);
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        // Khởi tạo các danh sách
        DSachNV dsNV = new DSachNV(100);
        DSachKH dsKH = new DSachKH(100);
        DSachSP dsSP = new DSachSP(100);
        DSachHD dsHD = new DSachHD(100, dsNV, dsKH, dsSP);

        // Đọc dữ liệu từ file
        System.out.println("========== KHOI TAO DU LIEU ==========");
        // docDuLieuNhanVien(dsNV);
        dsNV.DocFile();
        dsKH.DocFile();
        dsSP.DocFile();
        dsHD.docDanhSachHD();

        int choice;
        do {
            hienThiMenu();
            System.out.print("Nhap lua chon: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    themHoaDonMoi(dsHD, dsNV, dsKH, dsSP);
                    break;
                case 2:
                    dsHD.xoaHoaDon();
                    break;
                case 3:
                    dsHD.timKiemHoaDonTheoMa();
                    break;
                case 4:
                    dsHD.timKiemHoaDonTheoKH();
                    break;
                case 5:
                    dsHD.inDanhSachHD();
                    break;
                case 6:
                    dsHD.tinhTongDoanhThu();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh. Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai!");
            }
        } while (choice != 0);
    }

    private static void hienThiMenu() {
        System.out.println("\n========== QUAN LY HOA DON ==========");
        System.out.println("1. Them hoa don moi");
        System.out.println("2. Xoa hoa don");
        System.out.println("3. Tim kiem hoa don theo ma");
        System.out.println("4. Tim kiem hoa don theo khach hang");
        System.out.println("5. In danh sach hoa don");
        System.out.println("6. Tinh tong doanh thu");
        System.out.println("0. Thoat");
        System.out.println("======================================");
    }

    private static void themHoaDonMoi(DSachHD dsHD, DSachNV dsNV, DSachKH dsKH, DSachSP dsSP) {
        try {
            System.out.println("\n========== THEM HOA DON MOI ==========");

            System.out.print("Ma hoa don: ");
            String maHD = sc.nextLine();

            System.out.print("Ten hoa don: ");
            String tenHD = sc.nextLine();

            System.out.print("Ma nhan vien (hoac N/A): ");
            String maNV = sc.nextLine();
            NhanVien nv = null;
            if (!maNV.equalsIgnoreCase("N/A")) {
                nv = dsNV.timKiemNhanVien(maNV);
                if (nv == null) {
                    System.out.println("Khong tim thay nhan vien. Tao hoa don khong co nhan vien.");
                }
            }

            System.out.print("Ma khach hang (hoac N/A): ");
            String maKH = sc.nextLine();
            KhachHang kh = null;
            if (!maKH.equalsIgnoreCase("N/A")) {
                kh = dsKH.timKiemKH(maKH);
                if (kh == null) {
                    System.out.println("Khong tim thay khach hang. Tao hoa don khong co khach hang.");
                }
            }

            System.out.print("Dia chi: ");
            String diaChi = sc.nextLine();

            System.out.print("Diem tich luy: ");
            int diemTichLuy = Integer.parseInt(sc.nextLine());

            Date ngayLap = new Date();

            // Tạo hóa đơn
            HoaDon hd = new HoaDon(maHD, nv, kh, tenHD, diaChi, diemTichLuy, ngayLap);

            // Thêm sản phẩm
            System.out.print("So luong san pham: ");
            int soSP = Integer.parseInt(sc.nextLine());

            for (int i = 0; i < soSP; i++) {
                System.out.print("Ma san pham thu " + (i + 1) + ": ");
                String maSP = sc.nextLine();

                SanPham sp = timSanPhamTrongDS(dsSP, maSP);
                if (sp != null) {
                    hd.themSanPham(sp);
                    System.out.println("Da them san pham: " + sp.getTenSP());
                } else {
                    System.out.println("Khong tim thay san pham co ma: " + maSP);
                    i--; // Nhập lại
                }
            }

            // Thêm vào danh sách
            dsHD.themHoaDon(hd);
            System.out.println("\nHoa don da duoc tao thanh cong!");
            hd.inHoaDon();

        } catch (Exception e) {
            System.out.println("Loi khi tao hoa don: " + e.getMessage());
        }
    }

    private static SanPham timSanPhamTrongDS(DSachSP dsSP, String maSP) {
        try {
            java.lang.reflect.Field danhSachField = DSachSP.class.getDeclaredField("danhsach");
            danhSachField.setAccessible(true);
            SanPham[] danhSach = (SanPham[]) danhSachField.get(dsSP);

            java.lang.reflect.Field soLuongField = DSachSP.class.getDeclaredField("soluong");
            soLuongField.setAccessible(true);
            int soLuong = soLuongField.getInt(dsSP);

            for (int i = 0; i < soLuong; i++) {
                if (danhSach[i] != null && danhSach[i].getMaSP().equalsIgnoreCase(maSP)) {
                    return danhSach[i];
                }
            }
        } catch (Exception e) {
            System.out.println("Loi khi tim san pham: " + e.getMessage());
        }
        return null;
    }
}

/*
 * 
 * private static void docDuLieuNhanVien(DSachNV dsNV) {
 * // Đọc từ file NhanVien.txt
 * try (java.io.BufferedReader br = new java.io.BufferedReader(
 * new java.io.FileReader(
 * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\CONNGUOI\\danhsachNV.txt"
 * ))) {
 * String line;
 * while ((line = br.readLine()) != null) {
 * if (line.trim().isEmpty())
 * continue;
 * 
 * String[] parts = line.split("\\|");
 * if (parts.length >= 9) {
 * String maNV = parts[0].trim();
 * String ten = parts[1].trim();
 * String sdt = parts[2].trim();
 * String diaChi = parts[3].trim();
 * String ngaySinh = parts[4].trim();
 * String gioiTinh = parts[5].trim();
 * double luong = Double.parseDouble(parts[6].trim());
 * String chucVu = parts[7].trim();
 * int namVaoLam = Integer.parseInt(parts[8].trim());
 * 
 * NhanVien nv = new NhanVien(maNV, ten, sdt, diaChi,
 * ngaySinh, gioiTinh, luong, chucVu, namVaoLam);
 * dsNV.themNhanVien(nv);
 * }
 * }
 * System.out.println("Da doc danh sach nhan vien tu file thanh cong!");
 * } catch (Exception e) {
 * System.out.println("Loi khi doc file nhan vien: " + e.getMessage());
 * }
 * }
 */