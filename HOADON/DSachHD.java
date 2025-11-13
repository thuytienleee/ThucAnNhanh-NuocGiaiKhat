/* 

// package ThucAnNhanh-NuocGiaiKhat.HOADON;
package HOADON;

public class DSachHD {
    private HoaDon[] danhSach;
    private int soLuong;

    // Constructor: khoi tao mang voi kich thuoc co dinh
    public DSachHD(int kichThuoc) {
        danhSach = new HoaDon[kichThuoc];
        soLuong = 0;
    }

    // Them nhieu hoa don vao danh sach
    public void themHoaDon(HoaDon[] hoaDons) {
        for (HoaDon hd : hoaDons) {
            if (hd != null && soLuong < danhSach.length) {
                danhSach[soLuong++] = hd;
            } else {
                System.out.println("Khong the them hoa don: danh sach day.");
            }
        }
    }

    // Xoa hoa don theo ma
    public boolean xoaHoaDon(String maHoaDon) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaHoaDon().equalsIgnoreCase(maHoaDon)) {
                // Doi cac phan tu phia sau len
                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }
                danhSach[--soLuong] = null;
                return true;
            }
        }
        return false;
    }

    // Tim hoa don theo ma (tra ve mang ket qua)
    public HoaDon[] timHoaDon(String maHoaDon) {
        HoaDon[] ketQua = new HoaDon[soLuong];
        int dem = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaHoaDon().equalsIgnoreCase(maHoaDon)) {
                ketQua[dem++] = danhSach[i];
            }
        }

        // Thu gon mang ket qua
        HoaDon[] ketQuaChinhXac = new HoaDon[dem];
        for (int i = 0; i < dem; i++) {
            ketQuaChinhXac[i] = ketQua[i];
        }
        return ketQuaChinhXac;
    }

    // In tat ca hoa don
    public void inTatCaHoaDon() {
        if (soLuong == 0) {
            System.out.println("Danh sach hoa don trong.");
            return;
        }
        for (int i = 0; i < soLuong; i++) {
            System.out.println("----- Hoa don thu " + (i + 1) + " -----");
            danhSach[i].inHoaDon();
        }
    }
} */

/* 



package HOADON;

import SANPHAM.SanPhamThucAn;
import java.text.SimpleDateFormat;
import java.util.Date;
import CONNGUOI.NhanVien;
import CONNGUOI.KhachHang;
import SANPHAM.SanPham;
import java.io.*;
import java.text.ParseException;

public class DSachHD {
    private HoaDon[] danhSach;
    private int soLuong;
    private static final int MAX_SIZE = 100;
    private static final String FILE_NHANVIEN = "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\nhanvien.txt";
    private static final String FILE_KHACHHANG = "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\khachhang.txt";
    private static final String FILE_SANPHAM = "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\sanpham.txt";
    private static final String FILE_HOADON = "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\hoadonn.txt";

    private NhanVien[] dsNhanVien;
    private int soNhanVien;
    private KhachHang[] dsKhachHang;
    private int soKhachHang;
    private SanPham[] dsSanPham;
    private int soSanPham;

    public DSachHD() {
        danhSach = new HoaDon[MAX_SIZE];
        soLuong = 0;
        dsNhanVien = new NhanVien[MAX_SIZE];
        soNhanVien = 0;
        dsKhachHang = new KhachHang[MAX_SIZE];
        soKhachHang = 0;
        dsSanPham = new SanPham[MAX_SIZE];
        soSanPham = 0;
    }

    // Doc file nhan vien
    public void DocDuLieuNhanVien(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 9) {
                    NhanVien nv = new NhanVien(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim(),
                            data[4].trim(),
                            data[5].trim(),
                            Double.parseDouble(data[6].trim()),
                            data[7].trim(),
                            Integer.parseInt(data[8].trim()));
                    dsNhanVien[soNhanVien++] = nv;
                }
            }
            System.out.println("Doc thanh cong " + soNhanVien + " nhan vien");
        } catch (IOException /* | ParseException e) {
            System.out.println("Loi doc file nhan vien: " + e.getMessage());
        }
    }

    // Doc file khach hang
    public void DocDuLieuKhachHang(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 3) {
                    KhachHang kh = new KhachHang(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim());
                    dsKhachHang[soKhachHang++] = kh;
                }
            }
            System.out.println("Doc thanh cong " + soKhachHang + " khach hang");
        } catch (IOException e) {
            System.out.println("Loi doc file khach hang: " + e.getMessage());
        }
    }

    // Doc file san pham
    public void DocDuLieuSanPham(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 7) {
                    SanPham sp = new SanPhamThucAn(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            Double.parseDouble(data[3].trim()),
                            sdf.parse(data[4].trim()),
                            sdf.parse(data[5].trim()),
                            Integer.parseInt(data[6].trim()));
                    dsSanPham[soSanPham++] = sp;
                    /*
                     * String loai = data[0].trim();
                     * SanPham sp = null;
                     * 
                     * if (loai.equals("NUOC")) {
                     * sp = new Nuoc(
                     * data[1].trim(),
                     * data[2].trim(),
                     * data[3].trim(),
                     * Double.parseDouble(data[4].trim()),
                     * sdf.parse(data[5].trim()),
                     * sdf.parse(data[6].trim()),
                     * null,
                     * data[7].trim(),
                     * data[8].trim());
                     * } else if (loai.equals("THUCAN")) {
                     * sp = new ThucAn(
                     * data[1].trim(),
                     * data[2].trim(),
                     * data[3].trim(),
                     * Double.parseDouble(data[4].trim()),
                     * sdf.parse(data[5].trim()),
                     * sdf.parse(data[6].trim()),
                     * null,
                     * data[7].trim(),
                     * data[8].trim());
                     * }
                     * 
                     * if (sp != null) {
                     * dsSanPham[soSanPham++] = sp;
                     * }
                     
                }
            }
            System.out.println("Doc thanh cong " + soSanPham + " san pham");
        } catch (IOException | ParseException e) {
            System.out.println("Loi doc file san pham: " + e.getMessage());
        }
    }

    // Doc file hoa don
    public void DocDuLieuTuFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 9 && data[0].trim().equals("HOADON")) {
                    NhanVien nv = timNhanVien(data[2].trim());
                    KhachHang kh = timKhachHang(data[3].trim());

                    HoaDon hd = new HoaDon(
                            data[1].trim(),
                            nv,
                            kh,
                            data[4].trim(),
                            data[5].trim(),
                            Integer.parseInt(data[6].trim()),
                            sdf.parse(data[7].trim()));

                    int soSP = Integer.parseInt(data[8].trim());
                    for (int i = 0; i < soSP && (9 + i) < data.length; i++) {
                        SanPham sp = timSanPham(data[9 + i].trim());
                        if (sp != null) {
                            hd.themSanPham(sp);
                        }
                    }

                    danhSach[soLuong++] = hd;
                }
            }
            System.out.println("Doc thanh cong " + soLuong + " hoa don");
        } catch (IOException | ParseException e) {
            System.out.println("Loi doc file hoa don: " + e.getMessage());
        }
    }

    // Ghi file hoa don
    public void GhiDuLieuLenFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < soLuong; i++) {
                bw.write(danhSach[i].toString());
                bw.newLine();
            }
            System.out.println("Ghi thanh cong " + soLuong + " hoa don vao file");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    // Them hoa don
    public void themHoaDon(HoaDon hoaDon) {
        if (soLuong < MAX_SIZE) {
            danhSach[soLuong++] = hoaDon;
            GhiDuLieuLenFile(FILE_HOADON);
            System.out.println("Them hoa don thanh cong!");
        } else {
            System.out.println("Danh sach da day!");
        }
    }

    // Xoa hoa don
    public boolean xoaHoaDon(String maHoaDon) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaHoaDon().equals(maHoaDon)) {
                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }
                danhSach[--soLuong] = null;
                GhiDuLieuLenFile(FILE_HOADON);
                System.out.println("Xoa hoa don thanh cong!");
                return true;
            }
        }
        System.out.println("Khong tim thay hoa don!");
        return false;
    }

    // Tim hoa don
    public HoaDon timHoaDon(String maHoaDon) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaHoaDon().equals(maHoaDon)) {
                return danhSach[i];
            }
        }
        return null;
    }

    // In tat ca hoa don
    public void inTatCaHoaDon() {
        System.out.println("\n===== DANH SACH HOA DON =====");
        for (int i = 0; i < soLuong; i++) {
            danhSach[i].inHoaDon();
        }
    }

    // Tim nhan vien
    private NhanVien timNhanVien(String maNV) {
        for (int i = 0; i < soNhanVien; i++) {
            if (dsNhanVien[i].getMaNV().equals(maNV)) {
                return dsNhanVien[i];
            }
        }
        return null;
    }

    // Tim khach hang
    private KhachHang timKhachHang(String maKH) {
        for (int i = 0; i < soKhachHang; i++) {
            if (dsKhachHang[i].getMaKH().equals(maKH)) {
                return dsKhachHang[i];
            }
        }
        return null;
    }

    // Tim san pham
    private SanPham timSanPham(String maSP) {
        for (int i = 0; i < soSanPham; i++) {
            if (dsSanPham[i].getMaSP().equals(maSP)) {
                return dsSanPham[i];
            }
        }
        return null;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public static void main(String[] args) {
        DSachHD ds = new DSachHD();

        // Doc du lieu tu file
        System.out.println("===== DOC DU LIEU TU FILE =====");
        ds.DocDuLieuNhanVien("E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\nhanvien.txt");
        ds.DocDuLieuKhachHang("E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\khachhang.txt");
        ds.DocDuLieuSanPham("E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\sanpham.txt");
        ds.DocDuLieuTuFile("E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\hoadonn.txt");

        // In danh sach hoa don
        ds.inTatCaHoaDon();

        // Test tim hoa don
        System.out.println("\n===== TIM HOA DON HD001 =====");
        HoaDon hd = ds.timHoaDon("HD001");
        if (hd != null) {
            hd.inHoaDon();
        }

        // Test xoa hoa don
        // System.out.println("\n===== XOA HOA DON HD001 =====");
        // ds.xoaHoaDon("HD001");

        // In lai danh sach sau khi xoa
        ds.inTatCaHoaDon();

        System.out.println("\nTong so hoa don: " + ds.getSoLuong());
    }
}*/

/* 
package HOADON;

import SANPHAM.SanPhamThucAn;
import java.text.SimpleDateFormat;
import java.util.Date;
import CONNGUOI.NhanVien;
import CONNGUOI.KhachHang;
import SANPHAM.SanPham;
import java.io.*;
import java.text.ParseException;

public class DSachHD {
    private HoaDon[] danhSach;
    private int soLuong;
    private static final int MAX_SIZE = 100;
    private static final String FILE_NHANVIEN = "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\nhanvien.txt";
    private static final String FILE_KHACHHANG = "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\khachhang.txt";
    private static final String FILE_SANPHAM = "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\sanpham.txt";
    private static final String FILE_HOADON = "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\hoadonn.txt";

    private NhanVien[] dsNhanVien;
    private int soNhanVien;
    private KhachHang[] dsKhachHang;
    private int soKhachHang;
    private SanPham[] dsSanPham;
    private int soSanPham;

    public DSachHD() {
        danhSach = new HoaDon[MAX_SIZE];
        soLuong = 0;
        dsNhanVien = new NhanVien[MAX_SIZE];
        soNhanVien = 0;
        dsKhachHang = new KhachHang[MAX_SIZE];
        soKhachHang = 0;
        dsSanPham = new SanPham[MAX_SIZE];
        soSanPham = 0;
    }

    // Doc file nhan vien
    public void DocDuLieuNhanVien(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 9) {
                    NhanVien nv = new NhanVien(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim(),
                            data[4].trim(),
                            data[5].trim(),
                            Double.parseDouble(data[6].trim()),
                            data[7].trim(),
                            Integer.parseInt(data[8].trim()));
                    dsNhanVien[soNhanVien++] = nv;
                }
            }
            System.out.println("Doc thanh cong " + soNhanVien + " nhan vien");
        } catch (IOException e) {
            System.out.println("Loi doc file nhan vien: " + e.getMessage());
        }
    }

    // Doc file khach hang
    public void DocDuLieuKhachHang(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 3) {
                    KhachHang kh = new KhachHang(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim());
                    dsKhachHang[soKhachHang++] = kh;
                }
            }
            System.out.println("Doc thanh cong " + soKhachHang + " khach hang");
        } catch (IOException e) {
            System.out.println("Loi doc file khach hang: " + e.getMessage());
        }
    }

    // Doc file san pham
    public void DocDuLieuSanPham(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 7) {
                    // SUA: Tao doi tuong SanPhamThucAn dung cach
                    SanPham sp = new SanPhamThucAn(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            Double.parseDouble(data[3].trim()),
                            sdf.parse(data[4].trim()),
                            sdf.parse(data[5].trim()),
                            Integer.parseInt(data[6].trim()));
                    dsSanPham[soSanPham++] = sp;
                }
            }
            System.out.println("Doc thanh cong " + soSanPham + " san pham");
        } catch (IOException | ParseException e) {
            System.out.println("Loi doc file san pham: " + e.getMessage());
            e.printStackTrace(); // Them de xem chi tiet loi
        }
    }

    // Doc file hoa don (DA SUA)
    public void DocDuLieuTuFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 9 && data[0].trim().equals("HOADON")) {
                    NhanVien nv = timNhanVien(data[2].trim());
                    KhachHang kh = timKhachHang(data[3].trim());

                    // SUA: Kiem tra null truoc khi tao hoa don
                    if (nv == null) {
                        System.out.println("CANH BAO: Khong tim thay nhan vien " + data[2].trim());
                        continue;
                    }
                    if (kh == null) {
                        System.out.println("CANH BAO: Khong tim thay khach hang " + data[3].trim());
                        continue;
                    }

                    HoaDon hd = new HoaDon(
                            data[1].trim(),
                            nv,
                            kh,
                            data[4].trim(),
                            data[5].trim(),
                            Integer.parseInt(data[6].trim()),
                            sdf.parse(data[7].trim()));

                    int soSP = Integer.parseInt(data[8].trim());

                    // SUA: Kiem tra du so san pham trong file
                    if (data.length < 9 + soSP) {
                        System.out.println("CANH BAO: Hoa don " + data[1].trim() +
                                " thieu san pham (khai bao " + soSP +
                                " nhung chi co " + (data.length - 9) + ")");
                        soSP = data.length - 9; // Lay so san pham thuc te
                    }

                    // Them san pham vao hoa don
                    for (int i = 0; i < soSP; i++) {
                        if ((9 + i) < data.length) {
                            SanPham sp = timSanPham(data[9 + i].trim());
                            if (sp != null) {
                                hd.themSanPham(sp);
                            } else {
                                System.out.println("CANH BAO: Khong tim thay san pham " + data[9 + i].trim());
                            }
                        }
                    }

                    danhSach[soLuong++] = hd;
                }
            }
            System.out.println("Doc thanh cong " + soLuong + " hoa don");
        } catch (IOException | ParseException e) {
            System.out.println("Loi doc file hoa don: " + e.getMessage());
            e.printStackTrace(); // Them de xem chi tiet loi
        } catch (NumberFormatException e) {
            System.out.println("Loi dinh dang so: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Ghi file hoa don
    public void GhiDuLieuLenFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < soLuong; i++) {
                bw.write(danhSach[i].toString());
                bw.newLine();
            }
            System.out.println("Ghi thanh cong " + soLuong + " hoa don vao file");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    // Them hoa don
    public void themHoaDon(HoaDon hoaDon) {
        if (soLuong < MAX_SIZE) {
            danhSach[soLuong++] = hoaDon;
            GhiDuLieuLenFile(FILE_HOADON);
            System.out.println("Them hoa don thanh cong!");
        } else {
            System.out.println("Danh sach da day!");
        }
    }

    // Xoa hoa don
    public boolean xoaHoaDon(String maHoaDon) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaHoaDon().equals(maHoaDon)) {
                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }
                danhSach[--soLuong] = null;
                GhiDuLieuLenFile(FILE_HOADON);
                System.out.println("Xoa hoa don thanh cong!");
                return true;
            }
        }
        System.out.println("Khong tim thay hoa don!");
        return false;
    }

    // Tim hoa don
    public HoaDon timHoaDon(String maHoaDon) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaHoaDon().equals(maHoaDon)) {
                return danhSach[i];
            }
        }
        return null;
    }

    // In tat ca hoa don
    public void inTatCaHoaDon() {
        System.out.println("\n===== DANH SACH HOA DON =====");
        if (soLuong == 0) {
            System.out.println("Danh sach trong!");
        } else {
            for (int i = 0; i < soLuong; i++) {
                danhSach[i].inHoaDon();
            }
        }
    }

    // Tim nhan vien
    public NhanVien timNhanVien(String maNV) {
        for (int i = 0; i < soNhanVien; i++) {
            if (dsNhanVien[i].getMaNV().equals(maNV)) {
                return dsNhanVien[i];
            }
        }
        return null;
    }

    // Tim khach hang
    public KhachHang timKhachHang(String maKH) {
        for (int i = 0; i < soKhachHang; i++) {
            if (dsKhachHang[i].getMaKH().equals(maKH)) {
                return dsKhachHang[i];
            }
        }
        return null;
    }

    // Tim san pham
    public SanPham timSanPham(String maSP) {
        for (int i = 0; i < soSanPham; i++) {
            if (dsSanPham[i].getMaSP().equals(maSP)) {
                return dsSanPham[i];
            }
        }
        return null;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public static void main(String[] args) {
        /*
         * DSachHD ds = new DSachHD();
         * 
         * // Doc du lieu tu file
         * System.out.println("===== DOC DU LIEU TU FILE =====");
         * ds.DocDuLieuNhanVien(
         * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\nhanvien.txt");
         * ds.DocDuLieuKhachHang(
         * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\khachhang.txt");
         * ds.DocDuLieuSanPham(
         * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\sanpham.txt");
         * ds.DocDuLieuTuFile(
         * "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\hoadonn.txt");
         * 
         * // In danh sach hoa don
         * ds.inTatCaHoaDon();
         * 
         * // Test tim hoa don
         * System.out.println("\n===== TIM HOA DON HD001 =====");
         * HoaDon hd = ds.timHoaDon("HD001");
         * if (hd != null) {
         * hd.inHoaDon();
         * } else {
         * System.out.println("Khong tim thay hoa don HD001");
         * }
         * 
         * System.out.println("\nTong so hoa don: " + ds.getSoLuong());
         * }
         

        DSachHD ds = new DSachHD();

        // Doc du lieu tu file
        System.out.println("===== DOC DU LIEU TU FILE =====");
        ds.DocDuLieuNhanVien("E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\nhanvien.txt");
        ds.DocDuLieuKhachHang("E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\khachhang.txt");
        ds.DocDuLieuSanPham("E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\sanpham.txt");
        ds.DocDuLieuTuFile("E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\hoadonn.txt");

        // In danh sach hoa don ban dau
        System.out.println("\n===== DANH SACH HOA DON BAN DAU =====");
        ds.inTatCaHoaDon();
        System.out.println("Tong so hoa don: " + ds.getSoLuong());

        // TEST 1: THEM HOA DON MOI
        System.out.println("\n\n========================================");
        System.out.println("===== TEST THEM HOA DON MOI =====");
        System.out.println("========================================");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Lay nhan vien va khach hang tu danh sach da doc
            NhanVien nvTest = ds.timNhanVien("NV006");
            KhachHang khTest = ds.timKhachHang("KH001");

            if (nvTest != null && khTest != null) {
                // Tao hoa don moi
                HoaDon hdMoi = new HoaDon(
                        "HD003",
                        nvTest,
                        khTest,
                        "Hoa don test",
                        "Da Nang",
                        15,
                        sdf.parse("12/11/2025"));

                // Them san pham vao hoa don
                SanPham sp1 = ds.timSanPham("SP001");
                SanPham sp2 = ds.timSanPham("SP002");

                if (sp1 != null) {
                    hdMoi.themSanPham(sp1);
                    System.out.println("- Da them san pham: " + sp1.getTenSP());
                }
                if (sp2 != null) {
                    hdMoi.themSanPham(sp2);
                    System.out.println("- Da them san pham: " + sp2.getTenSP());
                }

                // Them hoa don vao danh sach
                System.out.println("\n=> Dang them hoa don HD003...");
                ds.themHoaDon(hdMoi);

                // In danh sach sau khi them
                System.out.println("\n===== DANH SACH SAU KHI THEM =====");
                ds.inTatCaHoaDon();
                System.out.println("Tong so hoa don: " + ds.getSoLuong());

            } else {
                System.out.println("LOI: Khong tim thay nhan vien hoac khach hang!");
            }

        } catch (Exception e) {
            System.out.println("LOI khi them hoa don: " + e.getMessage());
            e.printStackTrace();
        }

        // TEST 2: TIM HOA DON VUA THEM
        System.out.println("\n\n========================================");
        System.out.println("===== TEST TIM HOA DON VUA THEM =====");
        System.out.println("========================================");
        HoaDon hdTim = ds.timHoaDon("HD003");
        if (hdTim != null) {
            System.out.println("=> Tim thay hoa don HD003:");
            hdTim.inHoaDon();
        } else {
            System.out.println("=> Khong tim thay hoa don HD003");
        }

        // TEST 3: XOA HOA DON
        System.out.println("\n\n========================================");
        System.out.println("===== TEST XOA HOA DON =====");
        System.out.println("========================================");

        // Thu xoa hoa don HD002
        System.out.println("=> Dang xoa hoa don HD002...");
        boolean ketQuaXoa = ds.xoaHoaDon("HD002");

        if (ketQuaXoa) {
            System.out.println("\n===== DANH SACH SAU KHI XOA HD002 =====");
            ds.inTatCaHoaDon();
            System.out.println("Tong so hoa don: " + ds.getSoLuong());

            // Kiem tra xem con tim thay HD002 khong
            System.out.println("\n=> Kiem tra lai hoa don HD002:");
            HoaDon hdDaXoa = ds.timHoaDon("HD002");
            if (hdDaXoa == null) {
                System.out.println("=> Xac nhan: HD002 da bi xoa thanh cong!");
            } else {
                System.out.println("=> LOI: HD002 van con trong danh sach!");
            }
        }

        // TEST 4: THU XOA HOA DON KHONG TON TAI
        System.out.println("\n\n========================================");
        System.out.println("===== TEST XOA HOA DON KHONG TON TAI =====");
        System.out.println("========================================");
        System.out.println("=> Dang xoa hoa don HD999 (khong ton tai)...");
        ds.xoaHoaDon("HD999");

        // TEST 5: THEM NHIEU HOA DON
        System.out.println("\n\n========================================");
        System.out.println("===== TEST THEM NHIEU HOA DON =====");
        System.out.println("========================================");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (int i = 4; i <= 6; i++) {
                NhanVien nv = ds.timNhanVien("NV00" + (i % 2 == 0 ? "6" : "7"));
                KhachHang kh = ds.timKhachHang("KH00" + (i % 2 + 1));

                if (nv != null && kh != null) {
                    HoaDon hd = new HoaDon(
                            "HD00" + i,
                            nv,
                            kh,
                            "Hoa don " + i,
                            "Ha Noi",
                            i * 5,
                            sdf.parse("13/11/2025"));

                    // Them san pham
                    SanPham sp = ds.timSanPham("SP00" + (i % 2 + 1));
                    if (sp != null) {
                        hd.themSanPham(sp);
                    }

                    ds.themHoaDon(hd);
                    System.out.println("- Da them hoa don HD00" + i);
                }
            }

            System.out.println("\n===== DANH SACH CUOI CUNG =====");
            ds.inTatCaHoaDon();
            System.out.println("Tong so hoa don: " + ds.getSoLuong());

        } catch (Exception e) {
            System.out.println("LOI: " + e.getMessage());
        }

        // TEST 6: THONG KE
        System.out.println("\n\n========================================");
        System.out.println("===== THONG KE CUOI CUNG =====");
        System.out.println("========================================");
        System.out.println("So hoa don hien tai: " + ds.getSoLuong());
        System.out.println("Cac ma hoa don co trong he thong:");
        for (int i = 0; i < ds.getSoLuong(); i++) {
            HoaDon hd = ds.danhSach[i];
            System.out.println("  + " + hd.getMaHoaDon() + " - " +
                    hd.laySoHoaDon() + " san pham - " +
                    hd.tinhTongTien() + " VND");
        }
    }
}

*/

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
                    danhSachHD[i].getKhachHang().getTen().toLowerCase().contains(tenKH.toLowerCase())) {
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