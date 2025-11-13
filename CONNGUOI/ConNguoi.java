package CONNGUOI;

public class ConNguoi {
    private String ten;
    private String diaChi;
    private String soDT;
    private String ngaySinh;
    private String gioiTinh;

    public ConNguoi() {
    }

    public ConNguoi(String ten, String soDT) {
        this.ten = ten;
        this.soDT = soDT;
    }

    public ConNguoi(String ten, String diaChi, String soDT, String ngaySinh, String gioiTinh) {
        this.ten = ten;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return String.format("Ten: %s | Dia chi: %s | SDT: %s | Ngay sinh: %s | Gioi tinh: %s",
                ten, diaChi, soDT, ngaySinh, gioiTinh);
    }
}