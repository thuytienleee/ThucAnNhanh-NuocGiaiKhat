
package PHIEUNHAP;

import CONNGUOI.NhanVien;
import NGUYENLIEU.NguyenLieu;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhieuNhap {
    private String maPhieuNhap;
    private String tenPhieuNhap;
    private NguyenLieu[] dsNL;
    private Date ngayNhap;
    private NhanVien nhanVienNhapHang;
    private String trangThai;
    private int soLuongNL;

    public PhieuNhap() {
        this.dsNL = new NguyenLieu[100];
        this.soLuongNL = 0;
        this.trangThai = "Chua xac nhan";
    }

    public PhieuNhap(String maPhieuNhap, String tenPhieuNhap, Date ngayNhap,
            NhanVien nhanVienNhapHang, int kichThuocDS) {
        this.maPhieuNhap = maPhieuNhap;
        this.tenPhieuNhap = tenPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.nhanVienNhapHang = nhanVienNhapHang;
        this.trangThai = "Chua xac nhan";
        this.dsNL = new NguyenLieu[kichThuocDS];
        this.soLuongNL = 0;
    }

    // ======== GETTER VA SETTER ========
    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getTenPhieuNhap() {
        return tenPhieuNhap;
    }

    public void setTenPhieuNhap(String tenPhieuNhap) {
        this.tenPhieuNhap = tenPhieuNhap;
    }

    public NguyenLieu[] getDsNL() {
        return dsNL;
    }

    public void setDsNL(NguyenLieu[] dsNL) {
        this.dsNL = dsNL;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public NhanVien getNhanVienNhapHang() {
        return nhanVienNhapHang;
    }

    public void setNhanVienNhapHang(NhanVien nhanVienNhapHang) {
        this.nhanVienNhapHang = nhanVienNhapHang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoLuongNL() {
        return soLuongNL;
    }

    public void setSoLuongNL(int soLuongNL) {
        this.soLuongNL = soLuongNL;
    }

    // ======== PHƯƠNG THỨC THÊM NGUYÊN LIỆU VÀO PHIẾU ========
    public boolean ThemCTPhieuNhap(NguyenLieu nl) {
        if (nl == null) {
            System.out.println("Nguyen lieu khong hop le!");
            return false;
        }

        if (!trangThai.equals("Chua xac nhan")) {
            System.out.println("Chi co the them nguyen lieu vao phieu chua xac nhan!");
            return false;
        }

        if (soLuongNL < dsNL.length) {
            dsNL[soLuongNL++] = nl;
            System.out.println("Them nguyen lieu thanh cong!");
            return true;
        } else {
            System.out.println("Danh sach nguyen lieu da day.");
            return false;
        }
    }

    // ======== PHƯƠNG THỨC XÓA NGUYÊN LIỆU KHỎI PHIẾU ========
    public boolean XoaCTPhieuNhap(String tenNguyenLieu) {
        if (!trangThai.equals("Chua xac nhan")) {
            System.out.println("Chi co the xoa nguyen lieu khoi phieu chua xac nhan!");
            return false;
        }

        for (int i = 0; i < soLuongNL; i++) {
            if (dsNL[i] != null && dsNL[i].getTenNL().equalsIgnoreCase(tenNguyenLieu)) {
                // Dịch chuyển các phần tử phía sau lên
                for (int j = i; j < soLuongNL - 1; j++) {
                    dsNL[j] = dsNL[j + 1];
                }
                dsNL[--soLuongNL] = null;
                System.out.println("Da xoa nguyen lieu: " + tenNguyenLieu);
                return true;
            }
        }
        System.out.println("Khong tim thay nguyen lieu: " + tenNguyenLieu);
        return false;
    }

    // ======== PHƯƠNG THỨC XÓA NGUYÊN LIỆU THEO MÃ ========
    public boolean XoaCTPhieuNhapTheoMa(String maNguyenLieu) {
        if (!trangThai.equals("Chua xac nhan")) {
            System.out.println("Chi co the xoa nguyen lieu khoi phieu chua xac nhan!");
            return false;
        }

        for (int i = 0; i < soLuongNL; i++) {
            if (dsNL[i] != null && dsNL[i].getMaNL().equalsIgnoreCase(maNguyenLieu)) {
                for (int j = i; j < soLuongNL - 1; j++) {
                    dsNL[j] = dsNL[j + 1];
                }
                dsNL[--soLuongNL] = null;
                System.out.println("Da xoa nguyen lieu co ma: " + maNguyenLieu);
                return true;
            }
        }
        System.out.println("Khong tim thay nguyen lieu co ma: " + maNguyenLieu);
        return false;
    }

    // ======== PHƯƠNG THỨC HỦY PHIẾU NHẬP ========
    public void HuyPhieuNhap() {
        if (trangThai.equals("Da xac nhan")) {
            System.out.println("Khong the huy phieu da xac nhan!");
            return;
        }

        trangThai = "Da huy";
        // Xóa tất cả nguyên liệu
        for (int i = 0; i < dsNL.length; i++) {
            dsNL[i] = null;
        }
        soLuongNL = 0;
        System.out.println("Phieu nhap da bi huy.");
    }

    // ======== PHƯƠNG THỨC XÁC NHẬN PHIẾU NHẬP ========
    public boolean XacNhanPhieuNhap() {
        if (trangThai.equals("Da huy")) {
            System.out.println("Khong the xac nhan phieu da huy.");
            return false;
        }

        if (trangThai.equals("Da xac nhan")) {
            System.out.println("Phieu da duoc xac nhan truoc do.");
            return false;
        }

        if (soLuongNL == 0) {
            System.out.println("Khong the xac nhan phieu rong!");
            return false;
        }

        trangThai = "Da xac nhan";
        System.out.println("Phieu nhap da duoc xac nhan thanh cong.");
        return true;
    }

    // ======== PHƯƠNG THỨC TÍNH TỔNG TIỀN PHIẾU ========
    public double TongTienPhieu() {
        double tong = 0;
        for (int i = 0; i < soLuongNL; i++) {
            if (dsNL[i] != null) {
                tong += dsNL[i].tinhTien();
            }
        }
        return tong;
    }

    // ======== PHƯƠNG THỨC TÌM NGUYÊN LIỆU THEO MÃ ========
    public NguyenLieu TimNguyenLieuTheoMa(String maNL) {
        for (int i = 0; i < soLuongNL; i++) {
            if (dsNL[i] != null && dsNL[i].getMaNL().equalsIgnoreCase(maNL)) {
                return dsNL[i];
            }
        }
        return null;
    }

    // ======== PHƯƠNG THỨC TÌM NGUYÊN LIỆU THEO TÊN ========
    public NguyenLieu TimNguyenLieuTheoTen(String tenNL) {
        for (int i = 0; i < soLuongNL; i++) {
            if (dsNL[i] != null && dsNL[i].getTenNL().equalsIgnoreCase(tenNL)) {
                return dsNL[i];
            }
        }
        return null;
    }

    // ======== PHƯƠNG THỨC SỬA THÔNG TIN NGUYÊN LIỆU TRONG PHIẾU ========
    public boolean SuaNguyenLieuTrongPhieu(String maNL, double soLuongMoi, double donGiaMoi) {
        if (!trangThai.equals("Chua xac nhan")) {
            System.out.println("Chi co the sua nguyen lieu trong phieu chua xac nhan!");
            return false;
        }

        NguyenLieu nl = TimNguyenLieuTheoMa(maNL);
        if (nl != null) {
            if (soLuongMoi > 0) {
                nl.setSoLuong(soLuongMoi);
            }
            if (donGiaMoi > 0) {
                nl.setDonGia(donGiaMoi);
            }
            System.out.println("Da cap nhat thong tin nguyen lieu!");
            return true;
        }
        System.out.println("Khong tim thay nguyen lieu co ma: " + maNL);
        return false;
    }

    // ======== PHƯƠNG THỨC KIỂM TRA PHIẾU RỖNG ========
    public boolean KiemTraPhieuRong() {
        return soLuongNL == 0;
    }

    // ======== PHƯƠNG THỨC ĐẾM SỐ LƯỢNG NGUYÊN LIỆU ========
    public int DemSoLuongNguyenLieu() {
        return soLuongNL;
    }

    // ======== PHƯƠNG THỨC HIỂN THỊ DANH SÁCH NGUYÊN LIỆU ========
    public void HienThiDanhSachNguyenLieu() {
        if (soLuongNL == 0) {
            System.out.println("Phieu nhap khong co nguyen lieu nao.");
            return;
        }

        System.out.println("\n+----------+----------------------+--------------+----------+------------+");
        System.out.println("| Ma NL    | Ten NL               | Don vi tinh  | So luong | Don gia    |");
        System.out.println("+----------+----------------------+--------------+----------+------------+");
        for (int i = 0; i < soLuongNL; i++) {
            if (dsNL[i] != null) {
                System.out.printf("| %-8s | %-20s | %-12s | %8.2f | %10.0f |\n",
                        dsNL[i].getMaNL(),
                        dsNL[i].getTenNL(),
                        dsNL[i].getDonViTinh(),
                        dsNL[i].getSoLuong(),
                        dsNL[i].getDonGia());
            }
        }
        System.out.println("+----------+----------------------+--------------+----------+------------+");
    }

    // ======== PHƯƠNG THỨC HIỂN THỊ THÔNG TIN PHIẾU NHẬP ========
    public void HienThiThongTin() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("|                        PHIEU NHAP                              |");
        System.out.println("------------------------------------------------------------------");
        System.out.println("| Ma phieu       : " + String.format("%-43s", maPhieuNhap) + "|");
        System.out.println("| Ten phieu      : " + String.format("%-43s", tenPhieuNhap) + "|");
        System.out.println("| Ngay nhap      : " + String.format("%-43s", sdf.format(ngayNhap)) + "|");
        System.out.println("| Nhan vien nhap : " + String.format("%-43s", nhanVienNhapHang.getTenNV()) + "|");
        System.out.println("| Ma nhan vien   : " + String.format("%-43s", nhanVienNhapHang.getMaNV()) + "|");
        System.out.println("| Trang thai     : " + String.format("%-43s", trangThai) + "|");
        System.out.println("------------------------------------------------------------------");
        System.out.println("|                   DANH SACH NGUYEN LIEU                        |");
        System.out.println("------------------------------------------------------------------");

        HienThiDanhSachNguyenLieu();

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("| TONG TIEN: " + String.format("%49s", String.format("%,.0f VND", TongTienPhieu())) + " |");
        System.out.println("------------------------------------------------------------------");
    }
}
