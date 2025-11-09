// package BAOCAO;
package BAOCAO;

import java.io.*;
import java.util.*;

public class DSBaoCao implements ICheck {
    private BaoCao[] DSBaoCao;
    private int soLuong;

    public DSBaoCao(int kichThuoc) {
        DSBaoCao = new BaoCao[kichThuoc];
        soLuong = 0;
    }

    // Tao bao cao moi
    public void taoBaoCao() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ID bao cao: ");
        String id = sc.nextLine();

        System.out.print("Nhap ID nhan vien: ");
        String employeeID = sc.nextLine();

        System.out.print("Nhap ngay bao cao (dd/MM/yyyy): ");
        String reportDay = sc.nextLine();

        while (!ICheck.isValidDateFormat(reportDay)) {
            System.out.print("Ngay khong hop le, nhap lai (dd/MM/yyyy): ");
            reportDay = sc.nextLine();
        }

        System.out.print("Nhap ngay bat dau: ");
        String start = sc.nextLine();

        System.out.print("Nhap ngay ket thuc: ");
        String end = sc.nextLine();

        System.out.print("Tong so hoa don: ");
        int tongHD = sc.nextInt();

        System.out.print("Tong doanh thu: ");
        int doanhThu = sc.nextInt();

        System.out.print("So khach moi: ");
        int khach = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhap so loai san pham: ");
        int sl = sc.nextInt();
        sc.nextLine();

        String[] idSP = new String[sl];
        int[] slSP = new int[sl];
        for (int i = 0; i < sl; i++) {
            System.out.print("Ma SP " + (i + 1) + ": ");
            idSP[i] = sc.nextLine();
            System.out.print("So luong SP: ");
            slSP[i] = sc.nextInt();
            sc.nextLine();
        }

        BaoCao bc = new BaoCao(id, employeeID, reportDay, start, end, tongHD, doanhThu, khach, idSP, slSP);
        if (soLuong < DSBaoCao.length) {
            DSBaoCao[soLuong++] = bc;
        } else {
            System.out.println("Danh sach day, khong the them bao cao moi!");
        }
    }

    // Ghi ra file
    public void ghiReportToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DSBaoCao.txt"))) {
            for (int i = 0; i < soLuong; i++) {
                bw.write(DSBaoCao[i].toString());
                bw.newLine();
            }
            System.out.println("Ghi bao cao thanh cong vao DSBaoCao.txt");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    // Doc tu file
    public void docReportTuFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("DSBaoCao.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    // In tat ca bao cao
    public void printAllReport() {
        for (int i = 0; i < soLuong; i++) {
            DSBaoCao[i].printReport();
        }
    }

    // public static void main(String[] args) {
    //     DSBaoCao ds = new DSBaoCao(50);
    //     ds.taoBaoCao();  // Nhap tat ca thong tin, ke ca ID nhan vien
    //     ds.printAllReport();
    //     ds.ghiReportToFile();
    // }
}
