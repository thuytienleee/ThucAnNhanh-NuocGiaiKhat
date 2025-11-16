
package HOADON;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import CONNGUOI.NhanVien;
import CONNGUOI.KhachHang;
import CONNGUOI.DSachNV;
import CONNGUOI.DSachKH;
import SANPHAM.SanPham;
import SANPHAM.DSachSP;

public class DSachHD {
    private HoaDon[] danhSachHD;
    private int soLuongHD;
    private DSachNV dsNV;
    private DSachKH dsKH;
    private DSachSP dsSP;
    static Scanner sc = new Scanner(System.in);

    public DSachHD(int kichThuoc, DSachNV dsNV, DSachKH dsKH, DSachSP dsSP) {
        this.danhSachHD = new HoaDon[kichThuoc];
        this.soLuongHD = 0;
        this.dsNV = dsNV;
        this.dsKH = dsKH;
        this.dsSP = dsSP;
    }

    // Thêm hóa đơn
    public void themHoaDon(HoaDon hd) {
        if (soLuongHD < danhSachHD.length) {
            danhSachHD[soLuongHD++] = hd;
            System.out.println("Da them hoa don: " + hd.getMaHoaDon());
            // Ghi vào file sau khi thêm
            FileHoaDon.ghiVaoFile(hd);
        } else {
            System.out.println("Danh sach hoa don da day!");
        }
    }

    // Xóa hóa đơn theo mã
    public void xoaHoaDon() {
        System.out.println("========== XOA HOA DON ==========");
        System.out.print("Nhap ma hoa don can xoa: ");
        String maHD = sc.nextLine().trim();

        int index = -1;
        for (int i = 0; i < soLuongHD; i++) {
            if (danhSachHD[i].getMaHoaDon().equalsIgnoreCase(maHD)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Khong tim thay hoa don co ma: " + maHD);
        } else {
            // Dịch chuyển các phần tử
            for (int i = index; i < soLuongHD - 1; i++) {
                danhSachHD[i] = danhSachHD[i + 1];
            }
            danhSachHD[--soLuongHD] = null;
            System.out.println("Da xoa hoa don co ma: " + maHD);

            // Cập nhật file sau khi xóa
            FileHoaDon.capNhatFile(danhSachHD, soLuongHD);
        }
    }

    // Tìm kiếm hóa đơn theo mã
    public void timKiemHoaDonTheoMa() {
        System.out.println("========== TIM KIEM HOA DON ==========");
        System.out.print("Nhap ma hoa don can tim: ");
        String maHD = sc.nextLine().trim();

        boolean timThay = false;
        for (int i = 0; i < soLuongHD; i++) {
            if (danhSachHD[i].getMaHoaDon().equalsIgnoreCase(maHD)) {
                danhSachHD[i].inHoaDon();
                timThay = true;
                break;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay hoa don co ma: " + maHD);
        }
    }

    // Tìm kiếm hóa đơn theo tên khách hàng
    public void timKiemHoaDonTheoKH() {
        System.out.println("========== TIM KIEM HOA DON THEO KHACH HANG ==========");
        System.out.print("Nhap ten khach hang: ");
        String tenKH = sc.nextLine().trim();

        boolean timThay = false;
        for (int i = 0; i < soLuongHD; i++) {
            if (danhSachHD[i].getKhachHang() != null &&
                    danhSachHD[i].getKhachHang().getTenKH().toLowerCase().contains(tenKH.toLowerCase())) {
                danhSachHD[i].inHoaDon();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay hoa don cua khach hang: " + tenKH);
        }
    }

    // In toàn bộ danh sách hóa đơn
    public void inDanhSachHD() {
        System.out.println("\n========== DANH SACH HOA DON ==========");
        if (soLuongHD == 0) {
            System.out.println("Danh sach hoa don trong!");
        } else {
            for (int i = 0; i < soLuongHD; i++) {
                danhSachHD[i].inHoaDon();
            }
        }
    }

    // Tính tổng doanh thu
    public void tinhTongDoanhThu() {
        double tongDoanhThu = 0;
        for (int i = 0; i < soLuongHD; i++) {
            tongDoanhThu += danhSachHD[i].tinhTongTien();
        }
        System.out.println("\n========== TONG DOANH THU ==========");
        System.out.printf("Tong doanh thu: %.2f VND\n", tongDoanhThu);
        System.out.println("====================================\n");
    }

    // Đọc danh sách hóa đơn từ file
    public void docDanhSachHD() {
        HoaDon[] dsHD = FileHoaDon.docFile(dsNV, dsKH, dsSP);
        if (dsHD != null) {
            for (HoaDon hd : dsHD) {
                if (hd != null && soLuongHD < danhSachHD.length) {
                    danhSachHD[soLuongHD++] = hd;
                }
            }
            System.out.println("Da doc " + soLuongHD + " hoa don tu file.");
        }
    }

    public int getSoLuongHD() {
        return soLuongHD;
    }

    public HoaDon[] getDanhSachHD() {
        return danhSachHD;
    }
}