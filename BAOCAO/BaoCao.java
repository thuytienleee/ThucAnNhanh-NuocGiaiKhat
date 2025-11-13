package BAOCAO;

import java.time.LocalDate;
import java.util.Arrays;

public class BaoCao {
    private String IDBaoCao;
    private String IDNhanVien;
    private String reportDay;
    private String ngayBatDau;
    private String ngayKetThuc;
    private int tongSoHD;
    private double tongDoanhThu;
    private String[] IDSanPhamArr;
    private int[] SLSanPham;

    // Constructor mặc định
    public BaoCao() {}

    // Constructor tự động lấy ngày hôm nay
    public BaoCao(String IDBaoCao, String IDNhanVien,
                  String ngayBatDau, String ngayKetThuc,
                  int tongSoHD, double tongDoanhThu,
                  String[] IDSanPhamArr, int[] SLSanPham) {
        this.IDBaoCao = IDBaoCao;
        this.IDNhanVien = IDNhanVien;
        this.reportDay = LocalDate.now().toString(); // ✅ tự động lấy ngày hôm nay
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tongSoHD = tongSoHD;
        this.tongDoanhThu = tongDoanhThu;
        this.IDSanPhamArr = IDSanPhamArr;
        this.SLSanPham = SLSanPham;
    }

    // GETTER
    public String getIDBaoCao() { return IDBaoCao; }
    public String getIDNhanVien() { return IDNhanVien; }
    public String getReportDay() { return reportDay; }
    public String getNgayBatDau() { return ngayBatDau; }
    public String getNgayKetThuc() { return ngayKetThuc; }
    public int getTongSoHD() { return tongSoHD; }
    public double getTongDoanhThu() { return tongDoanhThu; }
    public String[] getIDSanPhamArr() { return IDSanPhamArr; }
    public int[] getSLSanPham() { return SLSanPham; }

    // SETTER
    public void setIDBaoCao(String IDBaoCao) { this.IDBaoCao = IDBaoCao; }
    public void setIDNhanVien(String IDNhanVien) { this.IDNhanVien = IDNhanVien; }
    public void setReportDay(String reportDay) { this.reportDay = reportDay; }
    public void setNgayBatDau(String ngayBatDau) { this.ngayBatDau = ngayBatDau; }
    public void setNgayKetThuc(String ngayKetThuc) { this.ngayKetThuc = ngayKetThuc; }
    public void setTongSoHD(int tongSoHD) { this.tongSoHD = tongSoHD; }
    public void setTongDoanhThu(double tongDoanhThu) { this.tongDoanhThu = tongDoanhThu; }
    public void setIDSanPhamArr(String[] IDSanPhamArr) { this.IDSanPhamArr = IDSanPhamArr; }
    public void setSLSanPham(int[] SLSanPham) { this.SLSanPham = SLSanPham; }

    // In báo cáo ra màn hình
    public void printReport() {
        System.out.println("=========== BAO CAO KINH DOANH ===========");
        System.out.println("ID Bao cao: " + IDBaoCao);
        System.out.println("Nhan vien: " + IDNhanVien);
        System.out.println("Ngay lap: " + reportDay);
        System.out.println("Tu ngay: " + ngayBatDau + "  den: " + ngayKetThuc);
        System.out.println("Tong so HD: " + tongSoHD);
        System.out.println("Tong doanh thu: " + tongDoanhThu);
        System.out.println("Danh sach san pham:");
        for (int i = 0; i < IDSanPhamArr.length; i++) {
            System.out.printf(" - %s: %d san pham%n", IDSanPhamArr[i], SLSanPham[i]);
        }
        System.out.println("==========================================");
    }

    @Override
    public String toString() {
        return IDBaoCao + ";" + IDNhanVien + ";" + reportDay + ";" + ngayBatDau + ";" + ngayKetThuc + ";" +
                tongSoHD + ";" + tongDoanhThu + ";" +
                Arrays.toString(IDSanPhamArr) + ";" + Arrays.toString(SLSanPham);
    }

    // Hàm main để test
    public static void main(String[] args) {
        String[] idSanPham = {"SP01", "SP02", "SP03"};
        int[] soLuongSP = {10, 5, 8};

        BaoCao baoCao = new BaoCao(
                "BC001",
                "NV01",
                "2025-11-01",
                "2025-11-13",
                15,
                12000000.5,
                idSanPham,
                soLuongSP
        );

        baoCao.printReport();
    }
}
