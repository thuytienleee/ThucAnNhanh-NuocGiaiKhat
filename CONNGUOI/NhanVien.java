// package CONNGUOI;
package CONNGUOI;

// import java.util.Date;

public class NhanVien extends ConNguoi {
    private String maNV;
    private double luong;
    private String chucVuNV;
    private int namVaoLam;

    public NhanVien() {
        super();
    }

    public NhanVien(String maNV, String ten, String diaChi, String soDT, 
                    String ngaySinh, String gioiTinh, double luong, 
                    String chucVuNV, int namVaoLam) {
        super(ten, diaChi, soDT, ngaySinh, gioiTinh);
        this.maNV = maNV;
        this.luong = luong;
        this.chucVuNV = chucVuNV;
        this.namVaoLam = namVaoLam;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public String getChucVuNV() {
        return chucVuNV;
    }

    public void setChucVuNV(String chucVuNV) {
        this.chucVuNV = chucVuNV;
    }

    public int getNamVaoLam() {
        return namVaoLam;
    }

    public void setNamVaoLam(int namVaoLam) {
        this.namVaoLam = namVaoLam;
    }
    
    @Override
    public String toString() {
        return String.format("Ma NV: %s | Ten: %s | Ngay sinh: %s | SDT: %s | Dia chi: %s | Gioi tinh: %s | Luong: %.2f VND | Chuc vu: %s | Nam vao lam: %d", 
                            maNV, getTen(), getNgaySinh(), getSoDT(), getDiaChi(), 
                            getGioiTinh(), luong, chucVuNV, namVaoLam);
    }

    public void hienThiThongTinNhanVien() {
        System.out.println("==================THONG TIN NHAN VIEN========================");
        System.out.println(toString());
        System.out.println("=============================================================");
    }
}