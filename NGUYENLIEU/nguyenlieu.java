package NGUYENLIEU;

public class nguyenlieu {
    private String maNL;
    private String tenNL;
    private String donViTinh;
    private double soLuong;

    public nguyenlieu() {
    }
        
    public nguyenlieu(String maNL, String tenNL, String donViTinh, double soLuong) {
        this.maNL = maNL;
        this.tenNL = tenNL;
        this.donViTinh = donViTinh;
        this.soLuong = soLuong;
    }

    public String getMaNL() {
        return maNL;
    }
    public void setMaNL(String maNL) {
        this.maNL = maNL;
    }
    public String getTenNL() {
        return tenNL;
    }
    public void setTenNL(String tenNL) {
        this.tenNL = tenNL;
    }
    public String getDonViTinh() {
        return donViTinh;
    }
    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
    public double getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }

    public boolean kiemTraTonKho(){
        return soLuong > 0;
    }

    // Nhập hàng 
    public void tangSoLuong (double soLuongSD){
        if (soLuongSD > 0){
            soLuong += soLuongSD;
        }
    }

    // Chế biến
    public void giamSoluong (double soLuongSD){
        if (soLuongSD > 0 && soLuongSD <= soLuong){
            soLuong -= soLuongSD;
        }
    }

    @Override
    public String toString(){
        return String.format("| %-8s | %-20s | %-12s | %-8.2f |",
                                    maNL, tenNL, donViTinh, soLuong);
    }
}
