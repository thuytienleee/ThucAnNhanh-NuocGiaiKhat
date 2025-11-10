package CONGTHUC;
import NGUYENLIEU.DSNguyenLieu;

public class Garan extends ThucAn {
    public Garan(DSNguyenLieu dsNL) {
        super("Gà Rán", new String[]{"Bột mì","Phô mai","Gà","Rau"});
        congThuc = new CongThuc("CT03","Gà Rán Phô Mai",
                "Gà chiên giòn rắc phô mai","FastFood",1,20);
        for(String ten:NL)
            for(int i=0;i<dsNL.getSoLuong();i++)
                if(dsNL.getNguyenLieu(i).getTenNL().equalsIgnoreCase(ten))
                    congThuc.themNguyenLieu(dsNL.getNguyenLieu(i));
    }

    @Override
    public void chuanbi(){ System.out.println("Chuẩn bị: Nhúng gà qua bột mì, cắt rau và làm sạch gà, cắt miếng vừa ăn."); }
    @Override
    public void nau(){ System.out.println("Nấu: Nhúng gà vào bột và chiên trong dầu nóng cho vàng đều."); }
    @Override
    public void phucvu(){ System.out.println("Phục vụ: Vớt gà rán ra, trang trí với rau và rắc phô mai nếu khách yêu cầu."); }
}
