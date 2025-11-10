package CONGTHUC;
import NGUYENLIEU.DSNguyenLieu;

public class Hamburger extends ThucAn {
    public Hamburger(DSNguyenLieu dsNL) {
        super("Hamburger", new String[]{"Bột mì","Phô mai","Rau","Tôm","Mực"});
        congThuc = new CongThuc("CT01","Hamburger Hải Sản",
                "Bánh mì kẹp tôm, mực và rau","FastFood",1,25);
        for(String ten:NL)
            for(int i=0;i<dsNL.getSoLuong();i++)
                if(dsNL.getNguyenLieu(i).getTenNL().equalsIgnoreCase(ten))
                    congThuc.themNguyenLieu(dsNL.getNguyenLieu(i));
    }

    @Override
    public void chuanbi(){ System.out.println("Chuẩn bị: Nướng bánh mì từ bột mì, rửa rau và sơ chế tôm, mực."); }
    @Override
    public void nau(){ System.out.println("Nấu: Chiên tôm và mực, sau đó kẹp vào bánh cùng rau và phô mai."); }
    @Override
    public void phucvu(){ System.out.println("Phục vụ: Cắt đôi hamburger và trang trí với rau xanh, tương cà."); }
}
