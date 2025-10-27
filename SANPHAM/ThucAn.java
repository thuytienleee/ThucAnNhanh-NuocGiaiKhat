package SANPHAM;

public class ThucAn extends SanPham {
    private String hanSD;

    public ThucAn() {       
    }

    public ThucAn(String maSP, String tenSP, double giaSP, String hanSD) {
        super(maSP, tenSP, giaSP);
        this.hanSD = hanSD;
    }

    public String getHanSD() {
        return hanSD;
    }

    public void setHanSD(String hanSD) {
        this.hanSD = hanSD;
    }

    @Override
    public String toString() {
        return super.toString() + ", Hạn sử dụng: " + hanSD;
    }   
    
}
