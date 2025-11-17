// package NHANVIEN;
package CONNGUOI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DSachKH {

    private KhachHang[] danhsach;
    private int soluong;
    static Scanner sc = new Scanner(System.in);

    public DSachKH(int kichThuoc) {
        danhsach = new KhachHang[kichThuoc];
        soluong = 0;
    }

    public void themKhachHang() {
        System.out.println("==================== THEM KHACH HANG MOI ===================");

        String maKH;
        while (true) {
            System.out.print("Enter ID CUSTOMER: ");
            maKH = sc.nextLine().trim();

            if (maKH.isEmpty()) {
                System.out.println("Error: Customer ID cannot be empty!");
                continue;
            }

            // Kiểm tra định dạng KH001 (KH + 3 chữ số)
            if (!maKH.matches("^KH\\d{3}$")) {
                System.out.println("Error: Invalid customer ID format");
                continue;
            }

            // Kiểm tra trùng mã - CHẶT CHẼ HƠN
            boolean trungMa = false;
            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null && danhsach[i].getMaKH().equalsIgnoreCase(maKH)) {
                    trungMa = true;
                    System.out.println("Error: Customer ID '" + maKH + "' already exists in the system!");
                    System.out.println("Existing customer: " + danhsach[i].getTenKH() + " - " + danhsach[i].getSdtKH());
                    break;
                }
            }

            if (trungMa) {
                System.out.println("Please use a different customer ID.");
                continue;
            } else {
                break;
            }
        }

        String tenKH;
        while (true) {
            System.out.print("Enter NAME CUSTOMER: ");
            tenKH = sc.nextLine().trim();

            if (tenKH.isEmpty()) {
                System.out.println("Error: Customer name cannot be empty!");
                continue;
            }

            /*
             * if (!tenKH.matches("^[a-zA-ZÀ-ỹ\\s]+$")) {
             * System.out.
             * println("Error: Customer name can only contain letters and spaces!");
             * continue;
             * }
             */
            break;
        }

        String sdtKH;
        while (true) {
            System.out.print("Enter phone: ");
            sdtKH = sc.nextLine().trim();

            if (sdtKH.isEmpty()) {
                System.out.println("Error: Phone number cannot be empty!");
                continue;
            }

            // Kiểm tra định dạng số điện thoại: bắt đầu bằng 0 và có đúng 10 chữ số
            if (!sdtKH.matches("^0\\d{9}$")) {
                System.out.println("Error: Invalid phone number format! Must start with 0 and have exactly 10 digits.");
                continue;
            }

            // Kiểm tra trùng số điện thoại (tùy chọn)
            boolean trungSDT = false;
            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null && danhsach[i].getSdtKH().equals(sdtKH)) {
                    trungSDT = true;
                    System.out.println("Warning: Phone number '" + sdtKH + "' already belongs to customer: "
                            + danhsach[i].getMaKH() + " - " + danhsach[i].getTenKH());
                    System.out.print("Do you want to continue with this phone number? (yes/no): ");
                    String confirm = sc.nextLine().trim();
                    if (!confirm.equalsIgnoreCase("yes")) {
                        trungSDT = true;
                    } else {
                        trungSDT = false;
                    }
                    break;
                }
            }

            if (!trungSDT) {
                break;
            }
        }

        // Validation cho địa chỉ
        String diachiKH;
        while (true) {
            System.out.print("Enter ADDRESS: ");
            diachiKH = sc.nextLine().trim();

            if (diachiKH.isEmpty()) {
                System.out.println("Error: Address cannot be empty!");
                continue;
            }
            break;
        }

        KhachHang kh = new KhachHang(maKH, tenKH, sdtKH, diachiKH);

        if (soluong < danhsach.length) {
            danhsach[soluong++] = kh;
            System.out.println("New customer added successfully!");
            System.out.println("Added new customer: " + kh.getMaKH() + " - " + kh.getTenKH());
            GhiFile(kh);
            hienThiDanhSach();
        } else {
            System.out.println("Error: Customer list is full! Cannot add more customers.");
        }
    }

    public void xoaKhachHang() {
        while (true) {
            System.out.println("===================== DELETE CUSTOMER ===================");
            System.out.print("Enter CUSTOMER ID: ");
            String maKH = sc.nextLine().trim();
            int index = -1;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i].getMaKH().equalsIgnoreCase(maKH)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Staff ID not found. Please try again.");
            } else {
                for (int i = index; i < soluong - 1; i++) {
                    danhsach[i] = danhsach[i + 1];
                }
                danhsach[--soluong] = null;
                System.out.println("Customer deleted successfully!");
                CapNhatFile();
                hienThiDanhSach();
                break;
            }
        }
    }

    public void CapNhatFile() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("CONNGUOI/KhachHang.txt"))) {
            for (int i = 0; i < soluong; i++) {
                KhachHang kh = danhsach[i];
                if (kh == null) {
                    continue;
                }

                String line = kh.getMaKH() + "|"
                        + kh.getTenKH() + "|"
                        + kh.getSdtKH() + "|"
                        + kh.getDiaChiKH();

                bw.write(line);
                bw.newLine();

            }
            System.out.println("File updated successfully!");
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

    public void SuaThongTinKhachHang() {
        while (true) {
            System.out.println("===================== EDIT CUSTOMER INFO ===================");
            System.out.print("Enter CUSTOMER ID: ");
            String maKH = sc.nextLine().trim();
            int index = -1;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null && danhsach[i].getMaKH().equalsIgnoreCase(maKH)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Customer ID not found. Please try again.");
                continue;
            }

            KhachHang kh = danhsach[index];
            System.out.println("Current info: ");
            System.out.println(kh.toString());

            boolean editing = true;

            while (editing) {
                System.out.println("\n===================== EDIT MENU ===================");
                System.out.println("Select field to edit:");
                System.out.println("1. Name");
                System.out.println("2. Phone");
                System.out.println("3. Address");
                System.out.println("0. Finish Editing");
                System.out.println("====================================================");
                System.out.print("Your choice: ");

                int choice;
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid choice! Please enter again.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        // Validation cho tên
                        String newName;
                        while (true) {
                            System.out.print("Enter new name: ");
                            newName = sc.nextLine().trim();

                            if (newName.isEmpty()) {
                                System.out.println("Error: Customer name cannot be empty!");
                                continue;
                            }

                            // Kiểm tra tên chỉ chứa chữ cái và khoảng trắng
                            if (!newName.matches("^[a-zA-ZÀ-ỹ\\s]+$")) {
                                System.out.println("Error: Customer name can only contain letters and spaces!");
                                continue;
                            }
                            kh.setTenKH(newName);
                            break;
                        }
                        break;

                    case 2:
                        // Validation cho số điện thoại
                        String newPhone;
                        while (true) {
                            System.out.print("Enter new phone: ");
                            newPhone = sc.nextLine().trim();

                            if (newPhone.isEmpty()) {
                                System.out.println("Error: Phone number cannot be empty!");
                                continue;
                            }

                            // Kiểm tra định dạng số điện thoại
                            if (!newPhone.matches("^0\\d{9}$")) {
                                System.out.println(
                                        "Error: Invalid phone number format! Must start with 0 and have exactly 10 digits.");
                                continue;
                            }

                            // Kiểm tra trùng số điện thoại (trừ chính khách hàng đang sửa)
                            boolean trungSDT = false;
                            for (int i = 0; i < soluong; i++) {
                                if (danhsach[i] != null
                                        && danhsach[i].getSdtKH().equals(newPhone)
                                        && !danhsach[i].getMaKH().equals(kh.getMaKH())) {
                                    trungSDT = true;
                                    System.out.println(
                                            "Error: Phone number '" + newPhone + "' already belongs to customer: "
                                                    + danhsach[i].getMaKH() + " - " + danhsach[i].getTenKH());
                                    break;
                                }
                            }

                            if (!trungSDT) {
                                kh.setSdtKH(newPhone);
                                break;
                            } else {
                                System.out.println("Please use a different phone number.");
                            }
                        }
                        break;

                    case 3:
                        String newAddress;
                        while (true) {
                            System.out.print("Enter new address: ");
                            newAddress = sc.nextLine().trim();

                            if (newAddress.isEmpty()) {
                                System.out.println("Error: Address cannot be empty!");
                                continue;
                            }
                            kh.setDiaChiKH(newAddress);
                            break;
                        }
                        break;

                    case 0:
                        System.out.println("Exit editing...");
                        editing = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                }

                if (choice != 0) {
                    System.out.println("Updated customer info:");
                    System.out.println(kh.toString());
                    CapNhatFile();
                    System.out.println("File updated successfully!");

                    System.out.print("\nDo you want to continue editing this customer? (yes/no): ");
                    String ans = sc.nextLine().trim();
                    if (!ans.equalsIgnoreCase("yes")) {
                        editing = false;
                    }
                }
            }

            // Thoát khỏi vòng lặp tìm kiếm khách hàng sau khi sửa xong
            break;
        }
    }

    public void timKiemKhachHangTheoMa() {
        while (true) {
            System.out.println("===================== SEARCH CUSTOMER BY ID ===================");
            System.out.print("Enter CUSTOMER ID: ");
            String maCanTim = sc.nextLine().trim().toLowerCase();
            boolean found = false;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null) {
                    String maKH = danhsach[i].getMaKH().toLowerCase();
                    if (maKH.equalsIgnoreCase(maCanTim)) {
                        System.out.println("Found product: " + danhsach[i].toString());
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("Customer ID not found. Please try again.");
            } else {
                break;
            }
        }
    }

    public void DocFile() {
        soluong = 0;
        try {

            // ho tro doc file cho HOA DON
            //FileReader fr = new FileReader( "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\CONNGUOI\\KhachHang.txt");
             FileReader fr = new FileReader("CONNGUOI/KhachHang.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] p = line.split("\\|");
                String maKH = p[0];
                String tenKH = p[1].trim();
                String sdtKH = p[2].trim();
                String diachiKH = p[3].trim();

                KhachHang kh = null;
                kh = new KhachHang(maKH, tenKH, sdtKH, diachiKH);
                danhsach[soluong++] = kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GhiFile(KhachHang kh) {

        try {
            FileWriter fw = new FileWriter("CONNGUOI/KhachHang.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            String line = kh.getMaKH() + "|"
                    + kh.getTenKH() + "|"
                    + kh.getSdtKH() + "|"
                    + kh.getDiaChiKH();

            bw.write(line);
            bw.newLine();
            bw.close();
            fw.close();
            System.out.println("Ghi file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    public void hienThiDanhSach() {
        System.out.print(
                "+-------------------------------------------------------------------------------------------+\n");
        System.out.print(
                "|                                     CUSTOMER INFOMATION                                   |\n");
        System.out.print(
                "+-------------------------------------------------------------------------------------------+\n");
        System.out.print(String.format(" %-5s| %-25s | %-15s | %-25s%n",
                "ID", "NAME", "PHONE", "ADDRESS"));
        System.out.print(
                "+-------------------------------------------------------------------------------------------+\n");
        for (KhachHang kh : danhsach) {
            if (kh != null) {
                System.out.println(kh.toString());
            }
        }
    }

    // Phuong thuc ho tro cho HOA DON

    public KhachHang timKiemKH(String maKH) {
        for (int i = 0; i < soluong; i++) {
            if (danhsach[i] != null && danhsach[i].getMaKH().equalsIgnoreCase(maKH)) {
                return danhsach[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        DSachKH dskh = new DSachKH(100);
        dskh.DocFile();
        dskh.hienThiDanhSach();
        // dskh.themKhachHang();
        // dskh.xoaKhachHang();
        dskh.SuaThongTinKhachHang();
        // dskh.timKiemKhachHangTheoMa();

    }
}
