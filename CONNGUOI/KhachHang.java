
    package CONNGUOI;

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
    }
    
