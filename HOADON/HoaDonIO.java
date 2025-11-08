package com.example.models.ThucAnNhanhNuocGiaiKhat.HOADON;
import com.example.models.ThucAnNhanhNuocGiaiKhat.CONNGUOI.NhanVien;
import com.example.models.ThucAnNhanhNuocGiaiKhat.CONNGUOI.KhachHang;
import com.example.models.ThucAnNhanhNuocGiaiKhat.SANPHAM.SanPhamTestFile;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class HoaDonIO {

    public static HoaDon docHoaDonTuFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line != null) {
                String[] parts = line.split(";");
                String maHD = parts[0];
                String tenHD = parts[1];
                NhanVien maNV = new NhanVien();
                maNV.setMaNV(parts[2]);
                NhanVien tenNV = new NhanVien();
                maNV.setTen(parts[3]);
                KhachHang maKH = new KhachHang();
                maKH.setMaKH(parts[4]);
                String diaChi = parts[5];
                Date ngayLap = new SimpleDateFormat("dd/MM/yyyy").parse(parts[6]);
                int diemTichLuy = Integer.parseInt(parts[7]);

                // Danh sach san pham
                String[] spData = parts[8].split("\\|");
                SanPhamTestFile[] dsSP = new SanPhamTestFile[spData.length];
                for (int i = 0; i < spData.length; i++) {
                    String[] sp = spData[i].split(",");
                    dsSP[i] = new SanPhamTestFile(sp[0], Integer.parseInt(sp[1]), Double.parseDouble(sp[2]));
                }

                return new HoaDon(maHD, maNV, maKH, tenHD, tenNV, diaChi, ngayLap, diemTichLuy, 0, dsSP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


