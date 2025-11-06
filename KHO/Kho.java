// package KHO;
package com.example.models.ThucAnNhanhNuocGiaiKhat.KHO;
import com.example.models.ThucAnNhanhNuocGiaiKhat.NGUYENLIEU.nguyenlieu;
import com.example.models.ThucAnNhanhNuocGiaiKhat.PHIEUNHAP.PhieuNhap;
import com.example.models.ThucAnNhanhNuocGiaiKhat.CONGTHUC.congthuc;
// import NGUYENLIEU.nguyenlieu;
// import PHIEUNHAP.PhieuNhap;
// import CONGTHUC.congthuc;
public class Kho {
    private nguyenlieu[] dsKho;
    private int soLuongTon;

    public Kho(nguyenlieu[] dsKho, int soLuongTon) {
        this.dsKho = dsKho;
        this.soLuongTon = soLuongTon;
    }
    
    public Kho(){}

    public nguyenlieu[] getDsKho() {
        return dsKho;
    }

    public void setDsKho(nguyenlieu[] dsKho) {
        this.dsKho = dsKho;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    // Sau khi nhập hàng
    public void capNhatSauKhiNhap(PhieuNhap phieu){
        nguyenlieu[] dsPhieu = phieu.getDsNL();
        int slPhieu = phieu.getSoLuongNL();

        for (int i = 0; i < slPhieu; i++){
            nguyenlieu nlPhieu = dsPhieu[i];
            boolean tonTai = false;


            //Kiem tra trong kho
            for (int j = 0; j < soLuongTon; j++){
                if (dsKho[j].getMaNL().equals(nlPhieu.getMaNL())){
                    dsKho[j].tangSoLuong(nlPhieu.getSoLuong());
                    tonTai = true;
                    break;
                }
            }

            // Chưa có thì thêm mới vào kho
            if (!tonTai){
                if (soLuongTon < dsKho.length){
                    dsKho[soLuongTon++] = nlPhieu;
                } else {
                    System.out.println("Kho đầy không thể thêm "+nlPhieu.getTenNL());
                }
            }
        }
    }

    //Sau chế biến
    public void capNhatSauKhiCheBien(congthuc ct){
        nguyenlieu[] dsNLCT = ct.getDsNL();
        for (int i = 0; i< dsNLCT.length; i++){
            for (int j = 0; j < soLuongTon; j++){
                if (dsKho[i].getMaNL().equals(dsNLCT[i].getMaNL())){
                    dsKho[j].giamSoluong(dsNLCT[i].getSoLuong());
                }
            }
        }
    }
    
    public void xemDSTonKho(){
        System.out.println("Danh sach ton kho:");
        System.out.println("- San pham 1: 10 cai");
        System.out.println("- San pham 2: 5 cai");
    }
}
