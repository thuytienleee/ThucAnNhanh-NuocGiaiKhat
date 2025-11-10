package CONGTHUC;
import NGUYENLIEU.DSNguyenLieu;

public class MainThucAn {
    public static void main(String[] args) {
        DSNguyenLieu dsNL = new DSNguyenLieu(100);
        dsNL.docFile(); // đọc nguyên liệu từ file DSnguyenLieu.txt

        ThucAn[] menu = { new Hamburger(dsNL), new Pizza(dsNL), new Garan(dsNL) };

        for(ThucAn mon: menu){
            mon.congthuc();
            mon.hienThiCongThuc();
            mon.chuanbi();
            mon.nau();
            mon.phucvu();
            System.out.println("--------------------------------------------------");
        }
    }
}
