
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

    public int getSoLuongHD() {
        return soLuongHD;
    }

    public HoaDon[] getDanhSachHD() {
        return danhSachHD;
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

    public void themHoaDonMoi() {
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

            System.out.print("So luong san pham: ");
            int soSP = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < soSP; i++) {
                System.out.print("Ma san pham thu " + (i + 1) + ": ");
                String maSP = sc.nextLine();

                SanPham sp = dsSP.timkiemSP(maSP);
                if (sp != null) {
                    System.out.print("So luong: ");
                    int soLuong = Integer.parseInt(sc.nextLine());

                    hd.themSanPham(sp, soLuong);
                    System.out.println("Da them san pham: " + sp.getTenSP() + " - SL: " + soLuong);
                } else {
                    System.out.println("Khong tim thay san pham co ma: " + maSP);
                    i--;
                }
            }

            // Thêm vào danh sách
            themHoaDon(hd);
            System.out.println("\nHoa don da duoc tao thanh cong!");
            hd.inHoaDon();

        } catch (Exception e) {
            System.out.println("Loi khi tao hoa don: " + e.getMessage());
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

    // Phương thức hỗ trợ cho báo cáo
    public void themHoaDonKhongGhiFile(HoaDon hd) {
        if (soLuongHD < danhSachHD.length) {
            danhSachHD[soLuongHD++] = hd;
        }
    }

    // Phương thức hỗ trợ cho báo cáo
    public HoaDon[] locHoaDonTheoKhoangNgay(String ngayBD, String ngayKT) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        HoaDon[] temp = new HoaDon[soLuongHD];
        int count = 0;

        try {
            Date start = sdf.parse(ngayBD);
            Date end = sdf.parse(ngayKT);

            for (int i = 0; i < soLuongHD; i++) {
                HoaDon hd = danhSachHD[i];
                if (hd == null)
                    continue;

                Date ngayHD = hd.getNgayLap();

                // kiểm tra khoảng ngày
                if (!ngayHD.before(start) && !ngayHD.after(end)) {
                    temp[count++] = hd;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        HoaDon[] result = new HoaDon[count];
        for (int i = 0; i < count; i++)
            result[i] = temp[i];

        return result;
    }
}
