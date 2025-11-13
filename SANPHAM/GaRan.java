package SANPHAM;

import java.util.Date;

/**
 *
 * @author Thuy Tien
 */
public class GaRan extends ThucAn {
    private boolean viCay;

    public GaRan() {
    }

    public GaRan(String maSP, String tenSP, String maLoai, double gia, Date NgaySX, Date HanSD, int soLuong,
            boolean viCay) {
        super(maSP, tenSP, maLoai, gia, NgaySX, HanSD, soLuong);
        this.viCay = viCay;
    }

    public boolean getDoCay() {
        return viCay;
    }

    public void setDoCay(boolean viCay) {
        this.viCay = viCay;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Vi cay: %s ", viCay ? "Cay" : "Khong Cay");
    }
}
