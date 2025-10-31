package NHANVIEN;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DanhSachNV   {
    private ArrayList<NhanVien> danhSachNV;
      public DanhSachNV(){
        this.danhSachNV=new ArrayList<>();
    }
    public void docDS() {
        try (Scanner scan = new Scanner(new File("dsNV.txt"))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split("#");

                NhanVien nv = new NhanVien(); 
                nv.setmaNV(parts[0].trim());
                nv.settenUser(parts[1].trim());
                nv.setdiachi(parts[2].trim());
                nv.setsoDT(parts[3].trim());
                nv.setngaysinh(parts[4].trim());
                nv.setgioitinh(parts[5].trim());
                nv.setmatkhau(parts[6].trim());
                nv.setchucvu(parts[7].trim());
                danhSachNV.add(nv);
            }
        } catch (Exception e) {
            System.out.println("Khong the mo duoc file: " + e.getMessage());
        }
    }
    public ArrayList<NhanVien> getdsnv(){
        return danhSachNV;
    }
    public void setDanhSachNV(ArrayList<NhanVien> ds) {
        this.danhSachNV = ds; 
    }
    public void ghiDS() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dsNhanVien.txt"))) {
            for (NhanVien nv : danhSachNV) {
                writer.write(nv.getmaNV() + "#");
                writer.write(nv.gettenNV() + "#");
                writer.write(nv.getdiachi() + "#");
                writer.write(nv.getsoDT() + "#");
                writer.write(nv.getngaysinh() + "#");
                writer.write(nv.getgioitinh() + "#");
                writer.write(nv.getmatkhau() + "#");
                writer.write(nv.getchucvu()); // Ghi chức vụ trực tiếp
                writer.newLine(); // Ghi xuống dòng sau mỗi nhân viên
            }
        } catch (Exception e) {
            System.out.println("Khong the ghi danh sach nhan vien vao file " + e.getMessage());
        }
    }
