
package CONNGUOI;

public class KhachHang extends ConNguoi {
    private String maKH;

    public KhachHang() {
        super();
    }

    public KhachHang(String maKH, String ten, String soDT) {
        super(ten, soDT);
        this.maKH = maKH;
    }

    public KhachHang(String maKH, String ten, String diaChi, String soDT,
            String ngaySinh, String gioiTinh) {
        super(ten, diaChi, soDT, ngaySinh, gioiTinh);
        this.maKH = maKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    @Override
    public String toString() {
        return String.format("Ma KH: %s | Ten: %s | SDT: %s | Dia chi: %s | Ngay sinh: %s | Gioi tinh: %s",
                maKH, getTen(), getSoDT(), getDiaChi(), getNgaySinh(), getGioiTinh());
    }

    // public static void main(String[] args) {
    // DSachKH ds = new DSachKH();
    // ds.docDS();
    // ds.inDanhSachKH();
    // }
}
