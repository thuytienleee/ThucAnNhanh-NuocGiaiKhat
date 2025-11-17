package BAOCAO;

import CONNGUOI.DSachKH;
import CONNGUOI.DSachNV;
import CONNGUOI.NhanVien;
import HOADON.DSachHD;
import HOADON.HoaDon;
import SANPHAM.DSachSP;
import SANPHAM.SanPham;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class BaoCao implements ICheck {
    private String IDBaoCao;
    private String IDNhanVien;
    private String tenNhanVien;
    private String reportDay;
    private String ngayBatDau;
    private String ngayKetThuc;
    private int tongSoHD;
    private double tongDoanhThu;
    private String[] IDSanPhamArr;
    private int[] SLSanPham;
    private double[] tongTienSP;

    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public BaoCao(){}
    public BaoCao(String IDBaoCao, String IDNhanVien, String tenNhanVien, String ngayBatDau, String ngayKetThuc, DSachHD dsHD, DSachNV dsNV) {
        if (!ICheck.isValidDateFormat(ngayBatDau) || !ICheck.isValidDateFormat(ngayKetThuc)) {
            throw new IllegalArgumentException("Ngay khong hop le, dinh dang la dd-MM-yyyy");
        }

        this.IDBaoCao = IDBaoCao;
        this.IDNhanVien = IDNhanVien;

        NhanVien nv = dsNV.timKiemNhanVien(IDNhanVien);
        if (nv == null) {
            System.out.println("Khong tim thay nhan vien co ID: "+ IDNhanVien);
            this.tenNhanVien = "Unknown";
        } else {
            this.tenNhanVien = nv.getTenNV();
        }

        this.reportDay = LocalDate.now().format(df);
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;

        tinhBaoCao(dsHD);
    }

    private void nhapKhoangNgay(Scanner sc) {
        LocalDate today = LocalDate.now();
        LocalDate bdDate = null;
        LocalDate ktDate = null;

        while (true) {
            System.out.print("Nhap ngay bat dau (dd-MM-yyyy): ");
            String bd = sc.nextLine().trim();
            while (!ICheck.isValidDateFormat(bd)) {
                System.out.print("Ngay khong hop le. Nhap lai ngay bat dau: ");
                bd = sc.nextLine().trim();
            }
            bdDate = LocalDate.parse(bd, df);

            System.out.print("Nhap ngay ket thuc (dd-MM-yyyy): ");
            String kt = sc.nextLine().trim();
            while (!ICheck.isValidDateFormat(kt)) {
                System.out.print("Ngay khong hop le. Nhap lai ngay ket thuc: ");
                kt = sc.nextLine().trim();
            }
            ktDate = LocalDate.parse(kt, df);

            // Check logic ngày
            if (bdDate.isAfter(ktDate)) {
                System.out.println("Ngay bat dau khong duoc sau ngay ket thuc. Nhap lai!");
                continue;
            }
            if (ktDate.isAfter(today)) {
                System.out.println("Ngay ket thuc khong duoc lon hon ngay hien tai. Nhap lai!");
                continue;
            }

            this.ngayBatDau = bdDate.format(df);
            this.ngayKetThuc = ktDate.format(df);
            break;
        }
    }


    private void tinhBaoCao(DSachHD dsHD) {
        HoaDon[] dshdTheoNgay = dsHD.locHoaDonTheoKhoangNgay(ngayBatDau, ngayKetThuc);

        IDSanPhamArr = new String[10];
        SLSanPham = new int[10];
        tongTienSP = new double[10];
        int spCount = 0;

        tongSoHD = 0;
        tongDoanhThu = 0;

        for (HoaDon hd : dshdTheoNgay) {
            if (hd == null) continue;

            tongDoanhThu += hd.tinhTongTien();
            tongSoHD++;

            int soSP = hd.laySoHoaDon();
            SanPham[] dsChiTiet = hd.getDsChiTiet();
            int[] soLuongSP = hd.getSoLuongSP();

            for (int i = 0; i < soSP; i++) {
                if (dsChiTiet[i] == null) continue;

                String spID = dsChiTiet[i].getMaSP();
                int sl = soLuongSP[i];
                double donGia = dsChiTiet[i].getGia();
                double thanhTien = sl * donGia;

                int idx = -1;
                for (int j = 0; j < spCount; j++) {
                    if (IDSanPhamArr[j].equals(spID)) {
                        idx = j;
                        break;
                    }
                }

                if (idx != -1) {
                    SLSanPham[idx] += sl;
                    tongTienSP[idx] += thanhTien;
                } else {
                    // Nếu mảng đầy, tăng gấp đôi kích thước
                    if (spCount == IDSanPhamArr.length) {
                        IDSanPhamArr = Arrays.copyOf(IDSanPhamArr, IDSanPhamArr.length * 2);
                        SLSanPham = Arrays.copyOf(SLSanPham, SLSanPham.length * 2);
                    }
                    IDSanPhamArr[spCount] = spID;
                    SLSanPham[spCount] = sl;
                    tongTienSP[spCount] = thanhTien;
                    spCount++;
                }
            }
        }

        IDSanPhamArr = Arrays.copyOf(IDSanPhamArr, spCount);
        SLSanPham = Arrays.copyOf(SLSanPham, spCount);
        tongTienSP = Arrays.copyOf(tongTienSP, spCount);

        if (tongSoHD == 0) {
            System.out.println("\n==> Khong co hoa don nao trong khong ngay tu " + ngayBatDau + " den " + ngayKetThuc);
        }
    }

    public void printReport() {
        System.out.println("=========== BAO CAO KINH DOANH ===========");
        System.out.println("Bao cao " + IDBaoCao);
        System.out.println("Nhan vien: " + IDNhanVien + " - " + tenNhanVien);
        System.out.println("Ngay lap: " + reportDay);
        System.out.println("Tu ngay: " + ngayBatDau + "  den: " + ngayKetThuc);
        System.out.println("Tong so HD: " + tongSoHD);
        System.out.printf("Tong doanh thu: %.2f VND\n", tongDoanhThu);
        System.out.println("Danh sach san pham:");
        System.out.println("+----------+---------+------------+");
        System.out.println("| MaSP     | SoLuong | ThanhTien  |");
        System.out.println("+----------+---------+------------+");
        for (int i = 0; i < IDSanPhamArr.length; i++) {
            System.out.printf("| %-8s | %-7d | %-10.2f |\n", 
                            IDSanPhamArr[i], SLSanPham[i], tongTienSP[i]);
        }
        System.out.println("==========================================");
    }

    @Override
    public String toString() {
        return IDBaoCao + "|" + IDNhanVien + "|" + tenNhanVien + "|" + reportDay + "|" + ngayBatDau + "|" + ngayKetThuc + "|" +
                tongSoHD + "|" + tongDoanhThu + "|" +
                Arrays.toString(IDSanPhamArr) + "|" + Arrays.toString(SLSanPham) + "|" + Arrays.toString(tongTienSP);
    }

    // ======== Ktra ID trùng =========
    private boolean isIDBaoCaoTrung(String id){
        try (BufferedReader br = new BufferedReader(new FileReader("DSBaoCao.txt"))){
            String line;
            while ((line = br.readLine()) != null){
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts[0].trim().equals(id)){
                    return true;
                }
            }
        } catch (Exception e){

        }
        return false;
    }

    // ======== Tạo báo cáo mới =========
    public void taoBC() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== Tao Bao Cao Moi =====");


        String idBaoCaoMoi;
    while (true) {
        System.out.print("Nhap ID bao cao: ");
        idBaoCaoMoi = sc.nextLine().trim();
        if (isIDBaoCaoTrung(idBaoCaoMoi)) {
            System.out.println("ID bao cao da ton tai! Vui long nhap ID khac.");
        } else {
            break;
        }
    }
    this.IDBaoCao = idBaoCaoMoi;
        System.out.print("Nhap ID nhan vien: ");
        this.IDNhanVien = sc.nextLine().trim();

        // --- Doc danh sach ---
        DSachNV dsNV = new DSachNV(100);
        dsNV.DocFile();

        DSachKH dsKH = new DSachKH(100);
        dsKH.DocFile();

        DSachSP dsSP = new DSachSP(100);
        dsSP.DocFile();

        DSachHD dsHD = new DSachHD(100, dsNV, dsKH, dsSP);
        dsHD.docDanhSachHD();
        HoaDon[] dshd = dsHD.getDanhSachHD();

        NhanVien nv = dsNV.timKiemNhanVien(IDNhanVien);
        this.tenNhanVien = (nv != null) ? nv.getTenNV() : "Unknown";

        nhapKhoangNgay(sc);

        this.reportDay = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // --- Loc hoa don theo khoang ngay ---
        DSachHD dsHDThoiGian = new DSachHD(100, dsNV, dsKH, dsSP);
        if (dshd != null) {
            for (HoaDon hd : dshd) {
                if (hd != null && ICheck.isDateInRange(hd.getNgayLap(), ngayBatDau, ngayKetThuc)) {
                dsHDThoiGian.themHoaDonKhongGhiFile(hd);
    }
}
        }

        tinhBaoCao(dsHDThoiGian); // tinh tong doanh thu, san pham
        printReport();
        ghiFile();
    }

    // ======== Xoa bao cao =========
    public void xoaBaoCao(String maBaoCao){
        try {
            BufferedReader br = new BufferedReader(new FileReader("DSBaoCao.txt"));
            StringBuilder sb = new StringBuilder();
            
            String line;
            boolean found = false;

            while((line = br.readLine()) != null){
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                String IDBaoCao = parts[0].trim();
                if (!IDBaoCao.equals(maBaoCao)){
                    sb.append(line).append("\n");
                } else {
                    found = true;
                }
            }
            br.close();

            if (found){
                BufferedWriter bw = new BufferedWriter(new FileWriter("DSBaoCao.txt"));
                bw.write(sb.toString());
                bw.close();
                System.out.println("Da xoa bao cao "+maBaoCao);
            } else {
                System.out.println("Khong tim thay bao cao " + maBaoCao);
            }
        } catch (Exception e) {
            System.out.println("Loi xoa bao cao: "+e.getMessage());
        }
    }



    // ======== Ghi file =========
    public void ghiFile() {
        try {
            FileWriter fw = new FileWriter("DSBaoCao.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(this.toString());
            bw.newLine();

            bw.close();
            fw.close();
            System.out.println("Ghi file thanh cong vao DSBaoCao.txt!");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }


    // ========= Đọc file ===========
    public void docFile() {
    try (BufferedReader br = new BufferedReader(new FileReader("DSBaoCao.txt"))) {
        String line;
        int count = 0;

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split("\\|");

            String IDBaoCao     = parts[0].trim();
            String IDNhanVien   = parts[1].trim();
            String tenNhanVien  = parts[2].trim();
            String reportDay    = parts[3].trim();
            String ngayBatDau   = parts[4].trim();
            String ngayKetThuc  = parts[5].trim();
            String tongSoHD     = parts[6].trim();
            String tongDoanhThu = parts[7].trim();
            String dsSPStr      = parts[8].trim();
            String dsSLStr      = parts[9].trim();
            String dsTienStr    = parts[10].trim();

            dsSPStr = dsSPStr.replace("[", "").replace("]", "").trim();
            dsSLStr = dsSLStr.replace("[", "").replace("]", "").trim();
            dsTienStr = dsTienStr.replace("[", "").replace("]", "").trim();

            String[] tongTienStrArr = dsTienStr.isEmpty() ? new String[0] : dsTienStr.split(",\\s*");
            double[] tongTienSP = new double[tongTienStrArr.length];
            for (int i = 0; i < tongTienStrArr.length; i++) {
                tongTienSP[i] = Double.parseDouble(tongTienStrArr[i]);
            }
            String[] IDSanPhamArr = dsSPStr.isEmpty() ? new String[0] : dsSPStr.split(",\\s*");
            String[] SLSanPhamStr = dsSLStr.isEmpty() ? new String[0] : dsSLStr.split(",\\s*");
            int[] SLSanPham = new int[SLSanPhamStr.length];
            for (int i = 0; i < SLSanPhamStr.length; i++) {
                SLSanPham[i] = Integer.parseInt(SLSanPhamStr[i]);
            }

            System.out.println("\n=========== BAO CAO KINH DOANH ===========");
            System.out.println("Bao cao " + IDBaoCao);
            System.out.println("Nhan vien: " + IDNhanVien + " - " + tenNhanVien);
            System.out.println("Ngay lap: " + reportDay);
            System.out.println("Tu ngay: " + ngayBatDau + "  den: " + ngayKetThuc);
            System.out.println("Tong so HD: " + tongSoHD);
            System.out.printf("Tong doanh thu: %s VND\n", tongDoanhThu);
            System.out.println("+----------+---------+------------+");
            System.out.println("| MaSP     | SoLuong | ThanhTien  |");
            System.out.println("+----------+---------+------------+");
            for (int i = 0; i < IDSanPhamArr.length; i++) {
                System.out.printf("| %-8s | %-7d | %-10.2f |\n", 
                                IDSanPhamArr[i], SLSanPham[i], tongTienSP[i]);
            }
            System.out.println("==========================================");
        }

    } catch (Exception e) {
        System.out.println("Loi doc file: " + e.getMessage());
    }
}

    // ========== Sua bao cao ============
    public void suaBaoCao(String maBaoCao) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("DSBaoCao.txt"));
            StringBuilder sb = new StringBuilder();
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                String IDBaoCaoFile = parts[0].trim();

                if (IDBaoCaoFile.equals(maBaoCao)) {
                    found = true;
                    Scanner sc = new Scanner(System.in);

                    System.out.println("\n=== Thong tin bao cao hien tai ===");
                    System.out.println("ID Bao Cao: " + IDBaoCaoFile);
                    System.out.println("Nhan vien: " + parts[1] + " - " + parts[2]);
                    System.out.println("Tu ngay: " + parts[4] + "  den: " + parts[5]);
                    System.out.println("Tong so HD: " + parts[6]);
                    System.out.println("Tong doanh thu: " + parts[7]);

                    System.out.print("Nhap ID nhan vien moi (de trong neu khong doi): ");
                    String newIDNV = sc.nextLine().trim();
                    if (!newIDNV.isEmpty()) {
                        parts[1] = newIDNV;

                        DSachNV dsNV = new DSachNV(100);
                        dsNV.DocFile();
                        NhanVien nv = dsNV.timKiemNhanVien(newIDNV);
                        parts[2] = (nv != null) ? nv.getTenNV() : "Unknown";
                    }

                    System.out.print("Nhap ngay bat dau moi (dd-MM-yyyy) (de trong neu khong doi): ");
                    String newBD = sc.nextLine().trim();
                    if (!newBD.isEmpty()) {
                        while (!ICheck.isValidDateFormat(newBD)) {
                            System.out.print("Ngay khong hop le. Nhap lai: ");
                            newBD = sc.nextLine().trim();
                        }
                        parts[4] = newBD;
                    }

                    System.out.print("Nhap ngay ket thuc moi (dd-MM-yyyy) (de trong neu khong doi): ");
                    String newKT = sc.nextLine().trim();
                    if (!newKT.isEmpty()) {
                        while (!ICheck.isValidDateFormat(newKT)) {
                            System.out.print("Ngay khong hop le. Nhap lai: ");
                            newKT = sc.nextLine().trim();
                        }
                        parts[5] = newKT;
                    }

                    DSachNV dsNV = new DSachNV(100); dsNV.DocFile();
                    DSachKH dsKH = new DSachKH(100); dsKH.DocFile();
                    DSachSP dsSP = new DSachSP(100); dsSP.DocFile();
                    DSachHD dsHD = new DSachHD(100, dsNV, dsKH, dsSP);
                    dsHD.docDanhSachHD();

                    this.ngayBatDau = parts[4];
                    this.ngayKetThuc = parts[5];

                    DSachHD dsHDThoiGian = new DSachHD(100, dsNV, dsKH, dsSP);
                    for (HoaDon hd : dsHD.getDanhSachHD()) {
                        if (hd != null && ICheck.isDateInRange(hd.getNgayLap(), ngayBatDau, ngayKetThuc)) {
                            dsHDThoiGian.themHoaDonKhongGhiFile(hd);
                        }
                    }

                    tinhBaoCao(dsHDThoiGian); 

                    parts[6] = String.valueOf(tongSoHD);
                    parts[7] = String.valueOf(tongDoanhThu);
                    parts[8] = Arrays.toString(IDSanPhamArr);
                    parts[9] = Arrays.toString(SLSanPham);
                    parts[10] = Arrays.toString(tongTienSP);

                    // Nối lại dòng
                    line = String.join("|", parts);
                }

                sb.append(line).append("\n");
            }
            br.close();

            if (found) {
                BufferedWriter bw = new BufferedWriter(new FileWriter("DSBaoCao.txt"));
                bw.write(sb.toString());
                bw.close();
                System.out.println("Da sua bao cao " + maBaoCao);
            } else {
                System.out.println("Khong tim thay bao cao " + maBaoCao);
            }

        } catch (Exception e) {
            System.out.println("Loi sua bao cao: " + e.getMessage());
        }
    }

    // ======== Thong ke TOP 5 san pham ban chay =========
    public void thongKeTop5SanPhamBanChay(DSachHD dsHD, DSachNV dsNV, DSachKH dsKH, DSachSP dsSP) {
        Scanner sc = new Scanner(System.in);

        nhapKhoangNgay(sc);

        DSachHD dsHDThoiGian = new DSachHD(100, dsNV, dsKH, dsSP);
        for (HoaDon hd : dsHD.getDanhSachHD()) {
            if (hd != null && ICheck.isDateInRange(hd.getNgayLap(), ngayBatDau, ngayKetThuc)) {
                dsHDThoiGian.themHoaDonKhongGhiFile(hd);
            }
        }

        tinhBaoCao(dsHDThoiGian);

        if (IDSanPhamArr == null || IDSanPhamArr.length == 0) {
            System.out.println("Chua co san pham nao duoc ban trong khoang thoi gian nay.");
            return;
        }

        String[] spArr = Arrays.copyOf(IDSanPhamArr, IDSanPhamArr.length);
        int[] slArr = Arrays.copyOf(SLSanPham, SLSanPham.length);
        double[] tienArr = Arrays.copyOf(tongTienSP, tongTienSP.length);

        for (int i = 0; i < slArr.length - 1; i++) {
            for (int j = i + 1; j < slArr.length; j++) {
                if (slArr[i] < slArr[j]) {
                    int tmpSL = slArr[i]; slArr[i] = slArr[j]; slArr[j] = tmpSL;
                    String tmpSP = spArr[i]; spArr[i] = spArr[j]; spArr[j] = tmpSP;
                    double tmpTien = tienArr[i]; tienArr[i] = tienArr[j]; tienArr[j] = tmpTien;
                }
            }
        }

        int top = Math.min(5, spArr.length);

        System.out.println("\n===== TOP " + top + " SAN PHAM BAN CHAY =====");
        System.out.println("Tu ngay: " + ngayBatDau + "  den: " + ngayKetThuc);
        System.out.println("+----------+---------+------------+");
        System.out.println("| MaSP     | SoLuong | ThanhTien  |");
        System.out.println("+----------+---------+------------+");
        for (int i = 0; i < top; i++) {
            System.out.printf("| %-8s | %-7d | %-10.2f |\n", spArr[i], slArr[i], tienArr[i]);
        }
        System.out.println("=======================================");
    }

    // ======== Xem chi tiet bao cao =========
    public void xemChiTietBaoCao(String maBaoCao) {
        try (BufferedReader br = new BufferedReader(new FileReader("DSBaoCao.txt"))) {
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\|");
                String IDBaoCaoFile = parts[0].trim();

                if (IDBaoCaoFile.equals(maBaoCao)) {
                    found = true;

                    String IDNhanVien   = parts[1].trim();
                    String tenNhanVien  = parts[2].trim();
                    String reportDay    = parts[3].trim();
                    String ngayBatDau   = parts[4].trim();
                    String ngayKetThuc  = parts[5].trim();
                    String tongSoHD     = parts[6].trim();
                    String tongDoanhThu = parts[7].trim();
                    String dsSPStr      = parts[8].trim();
                    String dsSLStr      = parts[9].trim();
                    String dsTienStr    = parts[10].trim();

                    dsSPStr = dsSPStr.replace("[","").replace("]","").trim();
                    dsSLStr = dsSLStr.replace("[","").replace("]","").trim();
                    dsTienStr = dsTienStr.replace("[","").replace("]","").trim();

                    String[] IDSanPhamArr = dsSPStr.isEmpty() ? new String[0] : dsSPStr.split(",\\s*");
                    String[] SLSanPhamStr = dsSLStr.isEmpty() ? new String[0] : dsSLStr.split(",\\s*");
                    String[] tongTienStrArr = dsTienStr.isEmpty() ? new String[0] : dsTienStr.split(",\\s*");

                    int[] SLSanPham = new int[SLSanPhamStr.length];
                    for (int i = 0; i < SLSanPhamStr.length; i++) {
                        SLSanPham[i] = SLSanPhamStr[i].isEmpty() ? 0 : Integer.parseInt(SLSanPhamStr[i]);
                    }

                    double[] tongTienSP = new double[tongTienStrArr.length];
                    for (int i = 0; i < tongTienStrArr.length; i++) {
                        tongTienSP[i] = tongTienStrArr[i].isEmpty() ? 0 : Double.parseDouble(tongTienStrArr[i]);
                    }

                    System.out.println("\n=========== CHI TIET BAO CAO ===========");
                    System.out.println("ID Bao Cao: " + IDBaoCaoFile);
                    System.out.println("Nhan vien: " + IDNhanVien + " - " + tenNhanVien);
                    System.out.println("Ngay lap: " + reportDay);
                    System.out.println("Tu ngay: " + ngayBatDau + "  den: " + ngayKetThuc);
                    System.out.println("Tong so HD: " + tongSoHD);
                    System.out.printf("Tong doanh thu: %s VND\n", tongDoanhThu);
                    System.out.println("+----------+---------+------------+");
                    System.out.println("| MaSP     | SoLuong | ThanhTien  |");
                    System.out.println("+----------+---------+------------+");
                    for (int i = 0; i < IDSanPhamArr.length; i++) {
                        System.out.printf("| %-8s | %-7d | %-10.2f |\n", IDSanPhamArr[i], SLSanPham[i], tongTienSP[i]);
                    }
                    System.out.println("=======================================");
                    break;
                }
            }

            if (!found) {
                System.out.println("Khong tim thay bao cao co ID: " + maBaoCao);
            }

        } catch (Exception e) {
            System.out.println("Loi xem chi tiet bao cao: " + e.getMessage());
        }
    }

}
