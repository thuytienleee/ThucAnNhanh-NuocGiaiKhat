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
}



