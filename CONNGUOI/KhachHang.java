
package CONNGUOI;

<<<<<<< HEAD
    public class KhachHang{
        private String maKH;
        private String tenKH;
        private String sdtKH;
        private String diaChiKH;

        public KhachHang() {}

        public KhachHang(String maKH, String tenKH, String sdtKH, String diaChiKH) {
            this.maKH = maKH;
            this.tenKH = tenKH;
            this.sdtKH = sdtKH;
            this.diaChiKH = diaChiKH;
        }

    public String getMaKH() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }

    public String toString(){
        return String.format(" %-10s | %-20s | %-10s | %-10s", maKH, tenKH, sdtKH, diaChiKH);
    }
=======
public class KhachHang extends ConNguoi {
    private String maKH;

    public KhachHang() {
        super();
>>>>>>> 6071d028767abc0704412ccd0f261e51a5921d34
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
