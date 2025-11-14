
package CONNGUOI;

public class KhachHang {
    private String maKH;
    private String tenKH;
    private String sdtKH;
    private String diaChiKH;

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, String sdtKH, String diaChiKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdtKH = sdtKH;
        this.diaChiKH = diaChiKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-25s | %-15s | %-25s",
                maKH, tenKH, sdtKH, diaChiKH);
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }
}

