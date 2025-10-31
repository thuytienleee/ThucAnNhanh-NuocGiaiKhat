package PHIEUNHAP;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PhieuNhap {
    private String maPhieuNhap;
    private String tenPhieuNhap;
    private NguyenLieu[] dsNL;
    private Date ngayNhap;
    private NhanVien nhanVienNhapHang;
    private String trangThai;
    private int soLuongNL;

    // Constructor
    public PhieuNhap(String maPhieuNhap, String tenPhieuNhap, Date ngayNhap, NhanVien nhanVienNhapHang, int kichThuocDS) {
        this.maPhieuNhap = maPhieuNhap;
        this.tenPhieuNhap = tenPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.nhanVienNhapHang = nhanVienNhapHang;
        this.trangThai = "Chua xac nhan";
        this.dsNL = new NguyenLieu[kichThuocDS];
        this.soLuongNL = 0;
    }

    // Them nguyen lieu vao phieu
    public void ThemCTPhieuNhap(NguyenLieu nl) {
        if (soLuongNL < dsNL.length) {
            dsNL[soLuongNL++] = nl;
        } else {
            System.out.println("Danh sach nguyen lieu da day.");
        }
    }

    // Xoa nguyen lieu khoi phieu (theo ten)
    public void XoaCTPhieuNhap(String tenNguyenLieu) {
        for (int i = 0; i < soLuongNL; i++) {
            if (dsNL[i] != null && dsNL[i].toString().contains(tenNguyenLieu)) {
                for (int j = i; j < soLuongNL - 1; j++) {
                    dsNL[j] = dsNL[j + 1];
                }
                dsNL[--soLuongNL] = null;
                System.out.println("Da xoa nguyen lieu: " + tenNguyenLieu);
                return;
            }
        }
        System.out.println("Khong tim thay nguyen lieu: " + tenNguyenLieu);
    }

    // Huy phieu nhap
    public void HuyPhieuNhap() {
        trangThai = "Da huy";
        dsNL = new NguyenLieu[dsNL.length];
        soLuongNL = 0;
        System.out.println("Phieu nhap da bi huy.");
    }

    // Xac nhan phieu nhap (gia lap cap nhat kho)
    public void XacNhanPhieuNhap() {
        if (!trangThai.equals("Da huy")) {
            trangThai = "Da xac nhan";
            System.out.println("Phieu nhap da duoc xac nhan.");
        } else {
            System.out.println("Khong the xac nhan phieu da huy.");
        }
    }

    // Tinh tong tien phieu nhap
    public double TongTienPhieu() {
        double tong = 0;
        for (int i = 0; i < soLuongNL; i++) {
            if (dsNL[i] != null) {
                tong += dsNL[i].tinhTien();
            }
        }
        return tong;
    }

    // Hien thi thong tin phieu nhap
    public void HienThiThongTin() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("===== PHIEU NHAP =====");
        System.out.println("Ma phieu: " + maPhieuNhap);
        System.out.println("Ten phieu: " + tenPhieuNhap);
        System.out.println("Ngay nhap: " + sdf.format(ngayNhap));
        System.out.println("Nhan vien nhap: " + nhanVienNhapHang);
        System.out.println("Trang thai: " + trangThai);
        System.out.println("Danh sach nguyen lieu:");
        for (int i = 0; i < soLuongNL; i++) {
            System.out.println(" - " + dsNL[i]);
        }
        System.out.println("Tong tien: " + TongTienPhieu() + " VND");
        System.out.println("=======================");
    }
}



