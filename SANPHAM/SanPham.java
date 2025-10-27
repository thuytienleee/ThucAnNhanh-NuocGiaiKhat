package SANPHAM;

public abstract  class SanPham {
    private String maSP;
    private String tenSP;
    private double giaSP;
    //private CongThuc congThuc;
    
    public SanPham() {}

    public SanPham(String maSP, String tenSP, double giaSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(double giaSP) {
        this.giaSP = giaSP;
    }

    /*public void themNguyenLieu() {
        
    }*/

    /*public void Kho(Kho kho) {
        kho.ThemSP(this);
    }*/

    public abstract String toString();

}