package CONGTHUC;
import NGUYENLIEU.NguyenLieu;

public abstract class ThucAn {
    protected String name;
    protected String[] NL;
    protected CongThuc congThuc;

    public ThucAn(String name,String[] NL) { this.name=name; this.NL=NL; }

    public abstract void chuanbi();
    public abstract void nau();
    public abstract void phucvu();

    public void congthuc() {
        System.out.println("\n=== Công thức làm "+name+" ===");
        System.out.println("Nguyên liệu:");
        for(String s:NL) System.out.println(" - "+s);
    }

    public void hienThiCongThuc() { if(congThuc!=null) congThuc.hienThiCongThuc(); }
}
