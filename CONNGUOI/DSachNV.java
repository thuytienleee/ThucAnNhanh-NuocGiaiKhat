// package NHANVIEN;
package CONNGUOI;
import CONNGUOI.NhanVien;

public class DSachNV {
    private NhanVien[] DanhsachNhanVien;
    private int soLuongNhanVien;

    public DSachNV(int kichThuocToiDa) {
        DanhsachNhanVien = new NhanVien[kichThuocToiDa];
        soLuongNhanVien = 0;
    }

    public NhanVien themNhanVien(NhanVien nv) {
        if (soLuongNhanVien < DanhsachNhanVien.length) {
            DanhsachNhanVien[soLuongNhanVien++] = nv;
            return nv;
        }
        return null;
    }

    public boolean xoaNhanVien(String maNhanVien) {
        for (int i = 0; i < soLuongNhanVien; i++) {
            if (DanhsachNhanVien[i].getMaNV().equals(maNhanVien)) {
                for (int j = i; j < soLuongNhanVien - 1; j++) {
                    DanhsachNhanVien[j] = DanhsachNhanVien[j + 1];
                }
                DanhsachNhanVien[--soLuongNhanVien] = null;
                return true;
            }
        }
        return false;
    }

    public boolean thayDoiThongTinNhanVien(String maNhanVien, String ten, int tuoi, String sdt, String diaChi) {
        NhanVien nv = timKiemNhanVien(maNhanVien);
        if (nv != null) {
            nv.setTen(ten);
            nv.setTuoi(tuoi);
            nv.setSoDT(sdt);
            nv.setDiaChi(diaChi);
            return true;
        }
        return false;
    }

    public NhanVien timKiemNhanVien(String maNhanVien) {
        for (int i = 0; i < soLuongNhanVien; i++) {
            if (DanhsachNhanVien[i].getMaNV().equals(maNhanVien)) {
                return DanhsachNhanVien[i];
            }
        }
        return null;
    }

    public boolean chamCong(String maNhanVien) {
        NhanVien nv = timKiemNhanVien(maNhanVien);
        if (nv != null) {
            nv.setChamCong(true);
            return true;
        }
        return false;
    }
    public void hienThiDanhSach() {
        for (int i = 0; i < soLuongNhanVien; i++) {
            DanhsachNhanVien[i].hienThiThongTinNhanVien();
        }
    }
}
