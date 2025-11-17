
package PHIEUNHAP;

import CONNGUOI.NhanVien;
import NGUYENLIEU.NguyenLieu;
import NGUYENLIEU.DSNguyenLieu;
import KHO.Kho;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class DSPhieuNhap {
    private PhieuNhap[] dsPhieu;
    private int soLuongPhieu;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Scanner sc = new Scanner(System.in);
    private int viTriPhieuHienTai = -1; // Để lưu vị trí phiếu đang làm việc
    private Kho kho = new Kho();

    public DSPhieuNhap() {
        this.dsPhieu = new PhieuNhap[100];
        this.soLuongPhieu = 0;
    }

    // ======== GETTER ========
    public PhieuNhap[] getDsPhieu() {
        return dsPhieu;
    }

    public int getSoLuongPhieu() {
        return soLuongPhieu;
    }

    public void setSoLuongPhieu(int soLuongPhieu) {
        this.soLuongPhieu = soLuongPhieu;
    }

    public int getViTriPhieuHienTai() {
        return viTriPhieuHienTai;
    }

    public void setViTriPhieuHienTai(int viTriPhieuHienTai) {
        this.viTriPhieuHienTai = viTriPhieuHienTai;
    }

    // ======== ĐỌC DANH SÁCH NGUYÊN LIỆU TỪ FILE ========
    private NguyenLieu[] docDSNguyenLieu() {
        NguyenLieu[] dsnl = new NguyenLieu[0];
        try {
            FileReader fr = new FileReader(
                    "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\DSnguyenLieu.txt");
            BufferedReader br = new BufferedReader(fr);
            String st;

            while ((st = br.readLine()) != null) {
                if (st.trim().isEmpty())
                    continue;

                String[] str = st.split("\\|");
                if (str.length < 4)
                    continue;

                String maNL = str[0].trim();
                String tenNL = str[1].trim();
                String dvt = str[2].trim();
                double sl = Double.parseDouble(str[3].trim());

                NguyenLieu nl = new NguyenLieu(maNL, tenNL, dvt, sl);
                dsnl = Arrays.copyOf(dsnl, dsnl.length + 1);
                dsnl[dsnl.length - 1] = nl;
            }

            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Loi doc file nguyen lieu: " + e.getMessage());
        }
        return dsnl;
    }

    // ======== TÌM NGUYÊN LIỆU THEO MÃ ========
    private NguyenLieu timNguyenLieu(String maNL, NguyenLieu[] dsNL) {
        for (NguyenLieu nl : dsNL) {
            if (nl != null && nl.getMaNL().equalsIgnoreCase(maNL)) {
                return new NguyenLieu(nl.getMaNL(), nl.getTenNL(),
                        nl.getDonViTinh(), nl.getSoLuong());
            }
        }
        return null;
    }

    // ======== ĐỌC FILE PHIẾU NHẬP ========
    public void docFile() {
        NguyenLieu[] dsNL = docDSNguyenLieu();

        try {
            FileReader fr = new FileReader("E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\phieunhap.txt");
            BufferedReader br = new BufferedReader(fr);
            String st;

            while ((st = br.readLine()) != null) {
                if (st.trim().isEmpty())
                    continue;

                // Đọc thông tin phiếu nhập
                String[] info = st.split("\\|");
                if (info.length < 12)
                    continue;

                String maPhieu = info[0].trim();
                String tenPhieu = info[1].trim();
                Date ngayNhap = sdf.parse(info[2].trim());
                String maNV = info[3].trim();
                String tenNV = info[4].trim();
                String soDT = info[5].trim();
                String diaChi = info[6].trim();
                Date ngaySinh = sdf.parse(info[7].trim());
                String gioiTinh = info[8].trim();
                double luong = Double.parseDouble(info[9].trim());
                String chucVu = info[10].trim();
                int namVaoLam = Integer.parseInt(info[11].trim());

                // Tạo nhân viên
                NhanVien nv = new NhanVien(maNV, tenNV, diaChi, ngaySinh,
                        gioiTinh, luong, chucVu, namVaoLam);

                // Đọc số lượng nguyên liệu trong phiếu
                st = br.readLine();
                if (st == null)
                    break;
                int soLuongNL = Integer.parseInt(st.trim());

                // Tạo phiếu nhập
                PhieuNhap phieu = new PhieuNhap(maPhieu, tenPhieu, ngayNhap, nv, soLuongNL);

                // Đọc danh sách nguyên liệu trong phiếu
                for (int i = 0; i < soLuongNL; i++) {
                    st = br.readLine();
                    if (st == null)
                        break;

                    String[] nlInfo = st.split("\\|");
                    if (nlInfo.length < 3)
                        continue;

                    String maNL = nlInfo[0].trim();
                    double soLuong = Double.parseDouble(nlInfo[1].trim());
                    double donGia = Double.parseDouble(nlInfo[2].trim());

                    // Tìm nguyên liệu từ danh sách
                    NguyenLieu nl = timNguyenLieu(maNL, dsNL);
                    if (nl != null) {
                        nl.setSoLuong(soLuong);
                        nl.setDonGia(donGia);
                        phieu.ThemCTPhieuNhap(nl);
                    }
                }

                // Đọc dòng trống giữa các phiếu
                st = br.readLine();

                // Thêm phiếu vào danh sách
                if (soLuongPhieu < dsPhieu.length) {
                    dsPhieu[soLuongPhieu++] = phieu;
                }
            }

            br.close();
            fr.close();
            System.out.println("Doc file phieu nhap thanh cong! Tong so phieu: " + soLuongPhieu);
        } catch (Exception e) {
            System.out.println("Loi doc file phieu nhap: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ======== GHI FILE PHIẾU NHẬP ========
    public void ghiFile() {
        try {
            FileWriter fw = new FileWriter("E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\phieunhap.txt",
                    false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < soLuongPhieu; i++) {
                PhieuNhap phieu = dsPhieu[i];
                NhanVien nv = phieu.getNhanVienNhapHang();

                // Ghi thông tin phiếu
                bw.write(phieu.getMaPhieuNhap() + " | " +
                        phieu.getTenPhieuNhap() + " | " +
                        sdf.format(phieu.getNgayNhap()) + " | " +
                        nv.getMaNV() + " | " +
                        nv.getTenNV() + " | " +
                        nv.getSdtNV() + " | " +
                        nv.getDiaChiNV() + " | " +
                        nv.getNgaySinh() + " | " +
                        nv.getGioitinh() + " | " +
                        nv.getLuong() + " | " +
                        nv.getChucVuNV() + " | " +
                        nv.getNamVaoLam());
                bw.newLine();

                // Ghi số lượng nguyên liệu
                bw.write(String.valueOf(phieu.getSoLuongNL()));
                bw.newLine();

                // Ghi danh sách nguyên liệu
                NguyenLieu[] dsNL = phieu.getDsNL();
                for (int j = 0; j < phieu.getSoLuongNL(); j++) {
                    bw.write(dsNL[j].getMaNL() + " | " +
                            dsNL[j].getSoLuong() + " | " +
                            dsNL[j].getDonGia());
                    bw.newLine();
                }

                // Ghi dòng trống giữa các phiếu (trừ phiếu cuối)
                if (i < soLuongPhieu - 1) {
                    bw.newLine();
                }
            }

            bw.close();
            fw.close();
            System.out.println("Ghi file phieu nhap thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    // ======== CÁC PHƯƠNG THỨC QUẢN LÝ ========

    public void hienThiMenu() {
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("|           QUAN LY PHIEU NHAP NGUYEN LIEU                   |");
        System.out.println("--------------------------------------------------------------");
        if (viTriPhieuHienTai >= 0 && viTriPhieuHienTai < soLuongPhieu) {
            PhieuNhap phieu = dsPhieu[viTriPhieuHienTai];
            System.out.println("| Phieu dang lam viec: " + String.format("%-36s", phieu.getMaPhieuNhap()) + "|");
            System.out.println("--------------------------------------------------------------");
        }
        System.out.println("|  1. Hien thi tat ca phieu nhap                             |");
        System.out.println("|  2. Chon phieu de lam viec                                 |");
        System.out.println("|  3. Tao phieu nhap moi                                     |");
        System.out.println("| ---------------------------------------------------------- |");
        System.out.println("|  QUAN LY NGUYEN LIEU TRONG PHIEU                           |");
        System.out.println("|  4. Them nguyen lieu vao phieu                             |");
        System.out.println("|  5. Xoa nguyen lieu khoi phieu                             |");
        System.out.println("|  6. Sua thong tin nguyen lieu trong phieu                  |");
        System.out.println("|  7. Tim nguyen lieu trong phieu                            |");
        System.out.println("|  8. Hien thi danh sach nguyen lieu cua phieu               |");
        System.out.println("| ---------------------------------------------------------- |");
        System.out.println("|  XU LY PHIEU                                               |");
        System.out.println("|  9. Xac nhan phieu nhap                                    |");
        System.out.println("| 10. Huy phieu nhap                                         |");
        System.out.println("| 11. Tinh tong tien phieu                                   |");
        System.out.println("| 12. Kiem tra phieu rong                                    |");
        System.out.println("| 13. Dem so luong nguyen lieu trong phieu                   |");
        System.out.println("| ---------------------------------------------------------- |");
        System.out.println("| 14. Hien thi ton kho                                       |");
        System.out.println("| 15. Luu va thoat                                           |");
        System.out.println("|  0. Thoat khong luu                                        |");
        System.out.println("--------------------------------------------------------------");
    }

    // ======== 1. HIỂN THỊ TẤT CẢ PHIẾU ========
    public void hienThiTatCaPhieu() {
        if (soLuongPhieu == 0) {
            System.out.println("\nDanh sach phieu nhap rong!");
            return;
        }

        System.out.println("\n========== DANH SACH TAT CA PHIEU NHAP ==========");
        for (int i = 0; i < soLuongPhieu; i++) {
            dsPhieu[i].HienThiThongTin();
            System.out.println();
        }
    }

    // ======== 2. CHỌN PHIẾU ĐỂ LÀM VIỆC ========
    public void chonPhieuLamViec() {
        if (soLuongPhieu == 0) {
            System.out.println("\nChua co phieu nao trong he thong!");
            return;
        }

        System.out.println("\n=== DANH SACH PHIEU ===");
        for (int i = 0; i < soLuongPhieu; i++) {
            System.out.printf("%d. %s - %s - Trang thai: %s\n",
                    i + 1,
                    dsPhieu[i].getMaPhieuNhap(),
                    dsPhieu[i].getTenPhieuNhap(),
                    dsPhieu[i].getTrangThai());
        }

        System.out.print("\nChon so thu tu phieu (0 de huy): ");
        int chon = sc.nextInt();
        sc.nextLine();

        if (chon > 0 && chon <= soLuongPhieu) {
            viTriPhieuHienTai = chon - 1;
            System.out.println("Da chon phieu: " + dsPhieu[viTriPhieuHienTai].getMaPhieuNhap());
            dsPhieu[viTriPhieuHienTai].HienThiThongTin();
        } else if (chon != 0) {
            System.out.println("Lua chon khong hop le!");
        }
    }

    // ======== 3. TẠO PHIẾU NHẬP MỚI ========
    public void taoPhieuNhapMoi() {
        System.out.println("\n=== TAO PHIEU NHAP MOI ===");

        System.out.print("Ma phieu nhap: ");
        String maPhieu = sc.nextLine();

        System.out.print("Ten phieu nhap: ");
        String tenPhieu = sc.nextLine();

        Date ngayNhap = new Date();

        System.out.print("Ma nhan vien: ");
        String maNV = sc.nextLine();
        System.out.print("Ten nhan vien: ");
        String tenNV = sc.nextLine();
        System.out.print("So dien thoai: ");
        String soDT = sc.nextLine();
        System.out.print("Dia chi: ");
        String diaChi = sc.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        System.out.print("Ngay sinh (dd/MM/yyyy): ");
        String ngaySinh = sc.nextLine();
        Date ngay = null;
        try {
            ngay = sdf.parse(ngaySinh);
        } catch (ParseException e) {
            System.out.println("Ngay sinh khong hop le!");
        }
        System.out.print("Gioi tinh: ");
        String gioiTinh = sc.nextLine();
        System.out.print("Luong: ");
        double luong = sc.nextDouble();
        sc.nextLine();
        System.out.print("Chuc vu: ");
        String chucVu = sc.nextLine();
        System.out.print("Nam vao lam: ");
        int namVaoLam = sc.nextInt();
        sc.nextLine();

        NhanVien nv = new NhanVien(maNV, tenNV, diaChi, ngay,
                gioiTinh, luong, chucVu, namVaoLam);

        System.out.print("So luong nguyen lieu toi da: ");
        int kichThuoc = sc.nextInt();
        sc.nextLine();

        PhieuNhap phieu = new PhieuNhap(maPhieu, tenPhieu, ngayNhap, nv, kichThuoc);

        // Thêm vào danh sách
        if (soLuongPhieu < dsPhieu.length) {
            dsPhieu[soLuongPhieu] = phieu;
            viTriPhieuHienTai = soLuongPhieu;
            soLuongPhieu++;
            System.out.println("Tao phieu nhap thanh cong!");
        } else {
            System.out.println("Danh sach phieu da day!");
        }
    }

    // ======== 4. THÊM NGUYÊN LIỆU VÀO PHIẾU ========
    public void themNguyenLieuVaoPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieu[viTriPhieuHienTai];
        System.out.println("\n=== THEM NGUYEN LIEU VAO PHIEU: " + phieu.getMaPhieuNhap() + " ===");

        System.out.print("Ma nguyen lieu: ");
        String maNL = sc.nextLine();
        System.out.print("Ten nguyen lieu: ");
        String tenNL = sc.nextLine();
        System.out.print("Don vi tinh: ");
        String dvt = sc.nextLine();
        System.out.print("So luong: ");
        double sl = sc.nextDouble();
        System.out.print("Don gia: ");
        double donGia = sc.nextDouble();
        sc.nextLine();

        NguyenLieu nl = new NguyenLieu(maNL, tenNL, dvt, sl);
        nl.setDonGia(donGia);
        phieu.ThemCTPhieuNhap(nl);
    }

    // ======== 5. XÓA NGUYÊN LIỆU KHỎI PHIẾU ========
    public void xoaNguyenLieuKhoiPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieu[viTriPhieuHienTai];
        System.out.println("\n=== XOA NGUYEN LIEU KHOI PHIEU: " + phieu.getMaPhieuNhap() + " ===");

        phieu.HienThiDanhSachNguyenLieu();

        System.out.println("\n1. Xoa theo ma nguyen lieu");
        System.out.println("2. Xoa theo ten nguyen lieu");
        System.out.print("Chon cach xoa: ");
        int chon = sc.nextInt();
        sc.nextLine();

        if (chon == 1) {
            System.out.print("Nhap ma nguyen lieu can xoa: ");
            String ma = sc.nextLine();
            phieu.XoaCTPhieuNhapTheoMa(ma);
        } else if (chon == 2) {
            System.out.print("Nhap ten nguyen lieu can xoa: ");
            String ten = sc.nextLine();
            phieu.XoaCTPhieuNhap(ten);
        } else {
            System.out.println("Lua chon khong hop le!");
        }
    }

    // ======== 6. SỬA NGUYÊN LIỆU TRONG PHIẾU ========
    public void suaNguyenLieuTrongPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieu[viTriPhieuHienTai];
        System.out.println("\n=== SUA NGUYEN LIEU TRONG PHIEU: " + phieu.getMaPhieuNhap() + " ===");

        phieu.HienThiDanhSachNguyenLieu();

        System.out.print("\nNhap ma nguyen lieu can sua: ");
        String maNL = sc.nextLine();

        NguyenLieu nl = phieu.TimNguyenLieuTheoMa(maNL);
        if (nl != null) {
            System.out.println("\nThong tin hien tai:");
            System.out.println(nl);

            System.out.print("\nNhap so luong moi (0 de giu nguyen): ");
            double slMoi = sc.nextDouble();
            System.out.print("Nhap don gia moi (0 de giu nguyen): ");
            double giaMoi = sc.nextDouble();
            sc.nextLine();

            phieu.SuaNguyenLieuTrongPhieu(maNL, slMoi, giaMoi);
        }
    }

    // ======== 7. TÌM NGUYÊN LIỆU TRONG PHIẾU ========
    public void timNguyenLieuTrongPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieu[viTriPhieuHienTai];
        System.out.println("\n=== TIM NGUYEN LIEU TRONG PHIEU: " + phieu.getMaPhieuNhap() + " ===");

        System.out.println("1. Tim theo ma nguyen lieu");
        System.out.println("2. Tim theo ten nguyen lieu");
        System.out.print("Chon cach tim: ");
        int chon = sc.nextInt();
        sc.nextLine();

        NguyenLieu nl = null;
        if (chon == 1) {
            System.out.print("Nhap ma nguyen lieu: ");
            String ma = sc.nextLine();
            nl = phieu.TimNguyenLieuTheoMa(ma);
        } else if (chon == 2) {
            System.out.print("Nhap ten nguyen lieu: ");
            String ten = sc.nextLine();
            nl = phieu.TimNguyenLieuTheoTen(ten);
        }

        if (nl != null) {
            System.out.println("\n=== TIM THAY NGUYEN LIEU ===");
            System.out.println(nl);
            System.out.printf("Thanh tien: %,.0f VND\n", nl.tinhTien());
        } else {
            System.out.println("\nKhong tim thay nguyen lieu!");
        }
    }

    // ======== 8. HIỂN THỊ DANH SÁCH NGUYÊN LIỆU CỦA PHIẾU ========
    public void hienThiDanhSachNguyenLieuCuaPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieu[viTriPhieuHienTai];
        System.out.println("\n=== DANH SACH NGUYEN LIEU CUA PHIEU: " + phieu.getMaPhieuNhap() + " ===");
        phieu.HienThiDanhSachNguyenLieu();
    }

    // ======== 9. XÁC NHẬN PHIẾU ========
    public void xacNhanPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieu[viTriPhieuHienTai];
        System.out.println("\n=== XAC NHAN PHIEU: " + phieu.getMaPhieuNhap() + " ===");

        if (phieu.XacNhanPhieuNhap()) {
            // Cập nhật kho sau khi xác nhận
            kho.capNhatSauKhiNhap(phieu);
        }
    }

    // ======== 10. HỦY PHIẾU ========
    public void huyPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieu[viTriPhieuHienTai];
        System.out.println("\n=== HUY PHIEU: " + phieu.getMaPhieuNhap() + " ===");

        System.out.print("Ban co chac chan muon huy phieu nay? (Y/N): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            phieu.HuyPhieuNhap();
        } else {
            System.out.println("Da huy thao tac!");
        }
    }

    // ======== 11. TÍNH TỔNG TIỀN PHIẾU ========
    public void tinhTongTienPhieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieu[viTriPhieuHienTai];
        double tongTien = phieu.TongTienPhieu();

        System.out.println("\n=== TONG TIEN PHIEU: " + phieu.getMaPhieuNhap() + " ===");
        System.out.printf("Tong tien: %,.0f VND\n", tongTien);
    }

    // ======== 12. KIỂM TRA PHIẾU RỖNG ========
    public void kiemTraPhieuRong() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieu[viTriPhieuHienTai];

        if (phieu.KiemTraPhieuRong()) {
            System.out.println("\nPhieu " + phieu.getMaPhieuNhap() + " RONG (chua co nguyen lieu)");
        } else {
            System.out.println("\nPhieu " + phieu.getMaPhieuNhap() + " co " + phieu.getSoLuongNL() + " nguyen lieu");
        }
    }

    // ======== 13. ĐẾM SỐ LƯỢNG NGUYÊN LIỆU ========
    public void demSoLuongNguyenLieu() {
        if (viTriPhieuHienTai < 0) {
            System.out.println("\nVui long chon phieu truoc! (Chon option 2)");
            return;
        }

        PhieuNhap phieu = dsPhieu[viTriPhieuHienTai];
        int soLuong = phieu.DemSoLuongNguyenLieu();

        System.out.println("\n=== THONG KE PHIEU: " + phieu.getMaPhieuNhap() + " ===");
        System.out.println("So luong nguyen lieu: " + soLuong);
    }

    // ======== 14. HIỂN THỊ TỒN KHO ========
    public void hienThiTonKho() {
        kho.hienThiTonKho();
    }

    // ======== 15. LƯU VÀ THOÁT ========
    public void luuVaThoat() {
        System.out.println("\n=== LUU DU LIEU ===");
        ghiFile();
        kho.ghiFile();
        System.out.println("\nCam on ban da su dung chuong trinh!");
    }

    // ======== KHỞI TẠO KHO ========
    public void khoiTaoKho() {
        kho.docFile();
    }
}