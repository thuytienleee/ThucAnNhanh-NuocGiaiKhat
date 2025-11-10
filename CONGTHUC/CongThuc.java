// package CONGTHUC;
package CONGTHUC;
import NGUYENLIEU.NguyenLieu;
// import com.example.models.ThucAnNhanhNuocGiaiKhat.NGUYENLIEU.DSNguyenLieu;
// import com.example.models.ThucAnNhanhNuocGiaiKhat.KHO.Kho;

public class CongThuc {
    private String maCongThuc;
    private String tenCongThuc;
    private String moTa;
    private String loai;
    private int phienBan;
    private int thoiGianCheBien;
    private NguyenLieu[] dsNguyenLieu;
    private int soNguyenLieu;

    

    public String getMaCongThuc() {
        return maCongThuc;
    }

    public void setMaCongThuc(String maCongThuc) {
        this.maCongThuc = maCongThuc;
    }

    public String getTenCongThuc() {
        return tenCongThuc;
    }

    public void setTenCongThuc(String tenCongThuc) {
        this.tenCongThuc = tenCongThuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getPhienBan() {
        return phienBan;
    }

    public void setPhienBan(int phienBan) {
        this.phienBan = phienBan;
    }

    public int getThoiGianCheBien() {
        return thoiGianCheBien;
    }

    public void setThoiGianCheBien(int thoiGianCheBien) {
        this.thoiGianCheBien = thoiGianCheBien;
    }

    public NguyenLieu[] getDsNguyenLieu() {
        return dsNguyenLieu;
    }

    public void setDsNguyenLieu(NguyenLieu[] dsNguyenLieu) {
        this.dsNguyenLieu = dsNguyenLieu;
    }

    public int getSoNguyenLieu() {
        return soNguyenLieu;
    }

    public void setSoNguyenLieu(int soNguyenLieu) {
        this.soNguyenLieu = soNguyenLieu;
    }

    public CongThuc(String maCT, String tenCT, String moTa, String loai, int phienBan, int thoiGian) {
        this.maCongThuc = maCT;
        this.tenCongThuc = tenCT;
        this.moTa = moTa;
        this.loai = loai;
        this.phienBan = phienBan;
        this.thoiGianCheBien = thoiGian;
        this.dsNguyenLieu = new NguyenLieu[20]; // Gioi han toi da 20 nguyen lieu
        this.soNguyenLieu = 0;
    }

    public boolean themNguyenLieu(NguyenLieu nl) {
        if (soNguyenLieu < dsNguyenLieu.length) {
            dsNguyenLieu[soNguyenLieu++] = nl;
            return true;
        }
        return false;
    }
/* 
    public boolean capNhatCongThuc(String maCTMoi, String tenNL, double dinhLuongMoi, int soLuongSP) {
        for (int i = 0; i < soNguyenLieu; i++) {
            if (dsNguyenLieu[i].getTenNL().equals(tenNL)) {
                dsNguyenLieu[i].setDinhLuong(dinhLuongMoi);
                this.maCongThuc = maCTMoi;
                return true;
            }
        }
        return false;
    }

    public boolean kiemTraKho(Kho kho) {
        // Gia su lop Kho co phuong thuc kiem tra ton kho nguyen lieu
        for (int i = 0; i < soNguyenLieu; i++) {
            if (!kho.coDuNguyenLieu(dsNguyenLieu[i])) {
                return false;
            }
        }
        return true;
    }
*/
    public void hienThiCongThuc() {
        System.out.println("Cong thuc: " + tenCongThuc + " (" + maCongThuc + ")");
        System.out.println("Loai: " + loai + ", Phien ban: " + phienBan);
        System.out.println("Thoi gian che bien: " + thoiGianCheBien + " phut");
        System.out.println("Mo ta: " + moTa);
        System.out.println("Nguyen lieu:");
        for (int i = 0; i < soNguyenLieu; i++) {
            String s = dsNguyenLieu[i].toString();
            System.out.println(s);
        }
    }
}


