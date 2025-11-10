package CONGTHUC;
import NGUYENLIEU.DSNguyenLieu;

public class Pizza extends ThucAn {
    public Pizza(DSNguyenLieu dsNL) {
        super("Pizza", new String[]{"Bột mì","Phô mai","Rau","Tôm","Mực"});
        congThuc = new CongThuc("CT02","Pizza Hải Sản",
                "Pizza topping tôm, mực và rau","FastFood",1,30);
        for(String ten:NL)
            for(int i=0;i<dsNL.getSoLuong();i++)
                if(dsNL.getNguyenLieu(i).getTenNL().equalsIgnoreCase(ten))
                    congThuc.themNguyenLieu(dsNL.getNguyenLieu(i));
    }

    @Override
    public void chuanbi(){ System.out.println("Chuẩn bị: Nhào bột mì làm đế bánh, cắt rau, làm sạch tôm và mực."); }
    @Override
    public void nau(){ System.out.println("Nấu: Xếp topping tôm, mực, rau và phô mai lên đế bánh rồi nướng."); }
    @Override
    public void phucvu(){ System.out.println("Phục vụ: Cắt pizza thành miếng và rắc thêm phô mai nếu khách yêu cầu."); }
}
