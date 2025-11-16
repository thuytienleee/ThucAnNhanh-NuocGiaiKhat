// package NHANVIEN;
package CONNGUOI;

import CONNGUOI.NhanVien;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class DSachNV {

    private NhanVien[] danhsach;
    private int soluong;
    static Scanner sc = new Scanner(System.in);

    public DSachNV(int kichThuoc) {
        danhsach = new NhanVien[kichThuoc];
        soluong = 0;
    }

    public void themNhanVien() {
        System.out.println("==================== THEM NHAN VIEN MOI ===================");

        // Validation cho mã nhân viên (format: NV + 3 chữ số)
        String maNV;
        while (true) {
            System.out.print("Enter ID STAFF (format NV001): ");
            maNV = sc.nextLine().trim();

            if (maNV.isEmpty()) {
                System.out.println("Error: Staff ID cannot be empty!");
                continue;
            }

            // Kiểm tra định dạng NV001 (NV + 3 chữ số)
            if (!maNV.matches("^NV\\d{3}$")) {
                System.out.println(
                        "Error: Invalid staff ID format! Must be NV followed by 3 digits (e.g., NV001, NV010)");
                continue;
            }

            // Kiểm tra trùng mã
            boolean trungMa = false;
            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null && danhsach[i].getMaNV().equalsIgnoreCase(maNV)) {
                    trungMa = true;
                    System.out.println("Error: Staff ID '" + maNV + "' already exists in the system!");
                    System.out.println("Existing staff: " + danhsach[i].getTenNV() + " - " + danhsach[i].getChucVuNV());
                    break;
                }
            }

            if (trungMa) {
                System.out.println("Please use a different staff ID.");
                continue;
            } else {
                break;
            }
        }

        // Validation cho tên nhân viên
        String tenNV;
        while (true) {
            System.out.print("Enter NAME STAFF: ");
            tenNV = sc.nextLine().trim();

            if (tenNV.isEmpty()) {
                System.out.println("Error: Staff name cannot be empty!");
                continue;
            }

            // Kiểm tra tên chỉ chứa chữ cái và khoảng trắng
            if (!tenNV.matches("^[a-zA-ZÀ-ỹ\\s]+$")) {
                System.out.println("Error: Staff name can only contain letters and spaces!");
                continue;
            }
            break;
        }

        // Validation cho tuổi
        int tuoiNV = 0;
        while (true) {
            System.out.print("Enter AGE STAFF: ");
            String tuoiStr = sc.nextLine();
            try {
                tuoiNV = Integer.parseInt(tuoiStr);
                if (tuoiNV < 18 || tuoiNV > 65) {
                    System.out.println("Error: Age must be between 18 and 65!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid age format! Please enter a valid number.");
            }
        }

        // Validation cho giới tính
        String gioitinh;
        while (true) {
            System.out.print("Enter Gender (Nam/Nu): ");
            gioitinh = sc.nextLine().trim();

            if (gioitinh.isEmpty()) {
                System.out.println("Error: Gender cannot be empty!");
                continue;
            }

            // Kiểm tra giới tính chỉ được nhập Nam hoặc Nu
            if (!gioitinh.equalsIgnoreCase("Nam") && !gioitinh.equalsIgnoreCase("Nu")) {
                System.out.println("Error: Gender must be 'Nam' or 'Nu'!");
                continue;
            }
            break;
        }

        // Validation cho ngày sinh
        Date ngaySinh = null;
        while (ngaySinh == null) {
            System.out.print("Enter DATE OF BIRTH (dd-MM-yyyy): ");
            String ngaySinhStr = sc.nextLine();

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false);
                ngaySinh = sdf.parse(ngaySinhStr);

                // Kiểm tra tuổi từ ngày sinh
                Date currentDate = new Date();
                long ageInMillis = currentDate.getTime() - ngaySinh.getTime();
                long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);

                if (ageInYears < 18) {
                    System.out.println("Error: Staff must be at least 18 years old!");
                    ngaySinh = null;
                } else if (ageInYears > 65) {
                    System.out.println("Error: Staff cannot be older than 65 years!");
                    ngaySinh = null;
                }
            } catch (ParseException e) {
                System.out.println("Error: Invalid date format! Please use dd-MM-yyyy.");
            }
        }

        // Validation cho số điện thoại
        String sdtNV;
        while (true) {
            System.out.print("Enter PHONE NUMBER (10 digits, start with 0): ");
            sdtNV = sc.nextLine().trim();

            if (sdtNV.isEmpty()) {
                System.out.println("Error: Phone number cannot be empty!");
                continue;
            }

            // Kiểm tra định dạng số điện thoại
            if (!sdtNV.matches("^0\\d{9}$")) {
                System.out.println("Error: Invalid phone number format! Must start with 0 and have exactly 10 digits.");
                continue;
            }

            // Kiểm tra trùng số điện thoại
            boolean trungSDT = false;
            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null && danhsach[i].getSdtNV().equals(sdtNV)) {
                    trungSDT = true;
                    System.out.println("Error: Phone number '" + sdtNV + "' already belongs to staff: "
                            + danhsach[i].getMaNV() + " - " + danhsach[i].getTenNV());
                    break;
                }
            }

            if (!trungSDT) {
                break;
            } else {
                System.out.println("Please use a different phone number.");
            }
        }

        // Validation cho địa chỉ
        String diaChiNV;
        while (true) {
            System.out.print("Enter ADDRESS: ");
            diaChiNV = sc.nextLine().trim();

            if (diaChiNV.isEmpty()) {
                System.out.println("Error: Address cannot be empty!");
                continue;
            }
            break;
        }

        // Validation cho năm vào làm
        int namVaoLam = 0;
        while (true) {
            System.out.print("Enter YEAR OF JOIN: ");
            String namStr = sc.nextLine();
            try {
                namVaoLam = Integer.parseInt(namStr);

                // Lấy năm hiện tại
                int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

                // Kiểm tra năm vào làm hợp lý (không trong tương lai và không quá xa)
                if (namVaoLam > currentYear) {
                    System.out.println("Error: Year of join cannot be in the future!");
                    continue;
                }

                if (namVaoLam < 2000) {
                    System.out.println("Error: Year of join must be from 2000 onwards!");
                    continue;
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid year format! Please enter a valid number.");
            }
        }

        // Validation cho chức vụ
        String chucVuNV;
        while (true) {
            System.out.print("Enter POSITION (Nhan Vien/Quan Ly/Thu Ngan): ");
            chucVuNV = sc.nextLine().trim();

            if (chucVuNV.isEmpty()) {
                System.out.println("Error: Position cannot be empty!");
                continue;
            }

            // Kiểm tra chức vụ hợp lệ
            if (!chucVuNV.equalsIgnoreCase("Nhan Vien")
                    && !chucVuNV.equalsIgnoreCase("Quan Ly")
                    && !chucVuNV.equalsIgnoreCase("Thu Ngan")) {
                System.out.println("Error: Position must be 'Nhan Vien', 'Quan Ly', or 'Thu Ngan'!");
                continue;
            }
            break;
        }

        // Validation cho lương
        double luong = 0;
        while (true) {
            System.out.print("Enter SALARY: ");
            String luongStr = sc.nextLine();
            try {
                luong = Double.parseDouble(luongStr);
                if (luong <= 0) {
                    System.out.println("Error: Salary must be greater than 0!");
                    continue;
                }

                // Kiểm tra mức lương tối thiểu theo chức vụ
                if (chucVuNV.equalsIgnoreCase("Nhan Vien") && luong < 5000000) {
                    System.out.println("Warning: Salary for 'Nhan Vien' is below recommended minimum (5,000,000 VND)");
                } else if (chucVuNV.equalsIgnoreCase("Thu Ngan") && luong < 6000000) {
                    System.out.println("Warning: Salary for 'Thu Ngan' is below recommended minimum (6,000,000 VND)");
                } else if (chucVuNV.equalsIgnoreCase("Quan Ly") && luong < 10000000) {
                    System.out.println("Warning: Salary for 'Quan Ly' is below recommended minimum (10,000,000 VND)");
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid salary format! Please enter a valid number.");
            }
        }

        NhanVien nv = new NhanVien(maNV, tenNV, tuoiNV, gioitinh, ngaySinh, sdtNV, diaChiNV, luong, chucVuNV,
                namVaoLam);

        if (soluong < danhsach.length) {
            danhsach[soluong++] = nv;
            System.out.println("New staff added successfully!");
            System.out.println("Added new staff: " + nv.getMaNV() + " - " + nv.getTenNV() + " - " + nv.getChucVuNV());
            GhiFile(nv);
            hienThiDanhSach();
        } else {
            System.out.println("Error: Staff list is full! Cannot add more staff.");
        }
    }

    public void xoaNhanVien() {
        while (true) {
            System.out.println("===================== DELETE STAFF ===================");
            System.out.print("Enter STAFF ID: ");
            String maNV = sc.nextLine().trim();
            int index = -1;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i].getMaNV().equalsIgnoreCase(maNV)) {
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
                System.out.println("Staff deleted successfully!");
                CapNhatFile();
                hienThiDanhSach();
                break;
            }
        }
    }

    public void CapNhatFile() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("NhanVien.txt"))) {
            for (int i = 0; i < soluong; i++) {
                NhanVien nv = danhsach[i];
                if (nv == null) {
                    continue;
                }

                String line = nv.getMaNV() + "|"
                        + nv.getTenNV() + "|"
                        + nv.getTuoiNV() + "|"
                        + nv.getGioitinh() + "|"
                        + sdf.format(nv.getNgaySinh()) + "|"
                        + nv.getSdtNV() + "|"
                        + nv.getDiaChiNV() + "|"
                        + nv.getNamVaoLam() + "|"
                        + nv.getChucVuNV() + "|"
                        + nv.getLuong();

                bw.write(line);
                bw.newLine();

            }
            System.out.println("File updated successfully!");
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

    public void SuaThongTinNhanVien() {
        while (true) {
            System.out.println("===================== EDIT STAFF INFO ===================");
            System.out.print("Enter STAFF ID: ");
            String maNV = sc.nextLine().trim();
            int index = -1;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null && danhsach[i].getMaNV().equalsIgnoreCase(maNV)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Staff ID not found. Please try again.");
                continue;
            }

            NhanVien nv = danhsach[index];
            System.out.println("Current info: ");
            System.out.println(nv.toString());

            boolean editing = true;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            while (editing) {
                System.out.println("\n===================== EDIT MENU ===================");
                System.out.println("Select field to edit:");
                System.out.println("1. Name");
                System.out.println("2. Age");
                System.out.println("3. Gender");
                System.out.println("4. Date of Birth");
                System.out.println("5. Phone Number");
                System.out.println("6. Address");
                System.out.println("7. Year of Join");
                System.out.println("8. Position");
                System.out.println("9. Salary");
                System.out.println("0. Finish Editing");
                System.out.println("====================================================");
                System.out.print("Your choice: ");

                int choice;
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid choice! Please enter a number.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        // Validation cho tên
                        String newName;
                        while (true) {
                            System.out.print("Enter new Name: ");
                            newName = sc.nextLine().trim();

                            if (newName.isEmpty()) {
                                System.out.println("Error: Staff name cannot be empty!");
                                continue;
                            }

                            if (!newName.matches("^[a-zA-ZÀ-ỹ\\s]+$")) {
                                System.out.println("Error: Staff name can only contain letters and spaces!");
                                continue;
                            }
                            nv.setTenNV(newName);
                            break;
                        }
                        break;

                    case 2:
                        // Validation cho tuổi
                        int newAge = 0;
                        while (true) {
                            System.out.print("Enter new Age: ");
                            String ageStr = sc.nextLine();
                            try {
                                newAge = Integer.parseInt(ageStr);
                                if (newAge < 18 || newAge > 65) {
                                    System.out.println("Error: Age must be between 18 and 65!");
                                    continue;
                                }
                                nv.setTuoiNV(newAge);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Invalid age format! Please enter a valid number.");
                            }
                        }
                        break;

                    case 3:
                        // Validation cho giới tính
                        String newGender;
                        while (true) {
                            System.out.print("Enter new Gender (Nam/Nu): ");
                            newGender = sc.nextLine().trim();

                            if (newGender.isEmpty()) {
                                System.out.println("Error: Gender cannot be empty!");
                                continue;
                            }

                            if (!newGender.equalsIgnoreCase("Nam") && !newGender.equalsIgnoreCase("Nu")) {
                                System.out.println("Error: Gender must be 'Nam' or 'Nu'!");
                                continue;
                            }
                            nv.setGioitinh(newGender);
                            break;
                        }
                        break;

                    case 4:
                        // Validation cho ngày sinh
                        Date newNgaySinh = null;
                        while (newNgaySinh == null) {
                            System.out.print("Enter new Date of Birth (dd-MM-yyyy): ");
                            String ngaySinhStr = sc.nextLine();

                            try {
                                sdf.setLenient(false);
                                newNgaySinh = sdf.parse(ngaySinhStr);

                                // Kiểm tra tuổi từ ngày sinh
                                Date currentDate = new Date();
                                long ageInMillis = currentDate.getTime() - newNgaySinh.getTime();
                                long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);

                                if (ageInYears < 18) {
                                    System.out.println("Error: Staff must be at least 18 years old!");
                                    newNgaySinh = null;
                                } else if (ageInYears > 65) {
                                    System.out.println("Error: Staff cannot be older than 65 years!");
                                    newNgaySinh = null;
                                } else {
                                    nv.setNgaySinh(newNgaySinh);
                                }
                            } catch (ParseException e) {
                                System.out.println("Error: Invalid date format! Please use dd-MM-yyyy.");
                            }
                        }
                        break;

                    case 5:
                        // Validation cho số điện thoại
                        String newPhone;
                        while (true) {
                            System.out.print("Enter new Phone Number (10 digits, start with 0): ");
                            newPhone = sc.nextLine().trim();

                            if (newPhone.isEmpty()) {
                                System.out.println("Error: Phone number cannot be empty!");
                                continue;
                            }

                            if (!newPhone.matches("^0\\d{9}$")) {
                                System.out.println(
                                        "Error: Invalid phone number format! Must start with 0 and have exactly 10 digits.");
                                continue;
                            }

                            // Kiểm tra trùng số điện thoại (trừ chính nhân viên đang sửa)
                            boolean trungSDT = false;
                            for (int i = 0; i < soluong; i++) {
                                if (danhsach[i] != null &&
                                        danhsach[i].getSdtNV().equals(newPhone) &&
                                        !danhsach[i].getMaNV().equals(nv.getMaNV())) {
                                    trungSDT = true;
                                    System.out.println(
                                            "Error: Phone number '" + newPhone + "' already belongs to staff: " +
                                                    danhsach[i].getMaNV() + " - " + danhsach[i].getTenNV());
                                    break;
                                }
                            }

                            if (!trungSDT) {
                                nv.setSdtNV(newPhone);
                                break;
                            } else {
                                System.out.println("Please use a different phone number.");
                            }
                        }
                        break;

                    case 6:
                        // Validation cho địa chỉ
                        String newAddress;
                        while (true) {
                            System.out.print("Enter new Address: ");
                            newAddress = sc.nextLine().trim();

                            if (newAddress.isEmpty()) {
                                System.out.println("Error: Address cannot be empty!");
                                continue;
                            }
                            nv.setDiaChiNV(newAddress);
                            break;
                        }
                        break;

                    case 7:
                        // Validation cho năm vào làm
                        int newYear = 0;
                        while (true) {
                            System.out.print("Enter new Year of Join: ");
                            String yearStr = sc.nextLine();
                            try {
                                newYear = Integer.parseInt(yearStr);

                                // Lấy năm hiện tại
                                int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

                                if (newYear > currentYear) {
                                    System.out.println("Error: Year of join cannot be in the future!");
                                    continue;
                                }

                                if (newYear < 2000) {
                                    System.out.println("Error: Year of join must be from 2000 onwards!");
                                    continue;
                                }

                                nv.setNamVaoLam(newYear);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Invalid year format! Please enter a valid number.");
                            }
                        }
                        break;

                    case 8:
                        // Validation cho chức vụ
                        String newPosition;
                        while (true) {
                            System.out.print("Enter new Position (Nhan Vien/Quan Ly/Thu Ngan): ");
                            newPosition = sc.nextLine().trim();

                            if (newPosition.isEmpty()) {
                                System.out.println("Error: Position cannot be empty!");
                                continue;
                            }

                            if (!newPosition.equalsIgnoreCase("Nhan Vien") &&
                                    !newPosition.equalsIgnoreCase("Quan Ly") &&
                                    !newPosition.equalsIgnoreCase("Thu Ngan")) {
                                System.out.println("Error: Position must be 'Nhan Vien', 'Quan Ly', or 'Thu Ngan'!");
                                continue;
                            }
                            nv.setChucVuNV(newPosition);
                            break;
                        }
                        break;

                    case 9:
                        // Validation cho lương
                        double newSalary = 0;
                        while (true) {
                            System.out.print("Enter new Salary: ");
                            String salaryStr = sc.nextLine();
                            try {
                                newSalary = Double.parseDouble(salaryStr);
                                if (newSalary <= 0) {
                                    System.out.println("Error: Salary must be greater than 0!");
                                    continue;
                                }

                                // Kiểm tra mức lương tối thiểu theo chức vụ
                                String currentPosition = nv.getChucVuNV();
                                if (currentPosition.equalsIgnoreCase("Nhan Vien") && newSalary < 5000000) {
                                    System.out.println(
                                            "Warning: Salary for 'Nhan Vien' is below recommended minimum (5,000,000 VND)");
                                } else if (currentPosition.equalsIgnoreCase("Thu Ngan") && newSalary < 6000000) {
                                    System.out.println(
                                            "Warning: Salary for 'Thu Ngan' is below recommended minimum (6,000,000 VND)");
                                } else if (currentPosition.equalsIgnoreCase("Quan Ly") && newSalary < 10000000) {
                                    System.out.println(
                                            "Warning: Salary for 'Quan Ly' is below recommended minimum (10,000,000 VND)");
                                }

                                nv.setLuong(newSalary);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Invalid salary format! Please enter a valid number.");
                            }
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
                    System.out.println("Updated staff info:");
                    System.out.println(nv.toString());
                    CapNhatFile();

                    System.out.print("\nDo you want to continue editing this staff? (yes/no): ");
                    String ans = sc.nextLine().trim();
                    if (!ans.equalsIgnoreCase("yes")) {
                        editing = false;
                    }
                }
            }

            // Thoát khỏi vòng lặp tìm kiếm nhân viên sau khi sửa xong
            break;
        }
    }

    public void timKiemNhanVienTheoMa() {
        while (true) {
            System.out.println("===================== SEARCH STAFF BY ID ===================");
            System.out.print("Enter STAFF ID: ");
            String maCanTim = sc.nextLine().trim().toLowerCase();
            boolean found = false;

            for (int i = 0; i < soluong; i++) {
                if (danhsach[i] != null) {
                    String maNV = danhsach[i].getMaNV().toLowerCase();
                    if (maNV.equalsIgnoreCase(maCanTim)) {
                        System.out.println("Found product: " + danhsach[i].toString());
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("Staff ID not found. Please try again.");
            } else {
                break;
            }
        }
    }

    public void DocFile() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {

            // ho tro doc file cho HOA DON
            // FileReader fr = new FileReader(
            // "E:\\doandeadline\\src\\src\\comx\\ThucAnNhanhNuocGiaiKhat\\CONNGUOI\\NhanVien.txt");
            FileReader fr = new FileReader("NhanVien.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] p = line.split("\\|");
                String maNV = p[0];
                String tenNV = p[1].trim();
                int tuoiNV = Integer.parseInt(p[2]);
                String gioitinh = p[3].trim();
                Date ngaySinh = sdf.parse(p[4]);
                String sdtNV = p[5];
                String diaChiNV = p[6].trim();
                int namVaoLam = Integer.parseInt(p[7]);
                String chucVuNV = p[8].trim();
                double luong = Double.parseDouble(p[9]);

                NhanVien nv = null;
                nv = new NhanVien(maNV, tenNV, tuoiNV, gioitinh, ngaySinh, sdtNV, diaChiNV, luong, chucVuNV, namVaoLam);
                danhsach[soluong++] = nv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GhiFile(NhanVien nv) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            FileWriter fw = new FileWriter("NhanVien.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            String line = nv.getMaNV() + "|"
                    + nv.getTenNV() + "|"
                    + nv.getTuoiNV() + "|"
                    + nv.getGioitinh() + "|"
                    + sdf.format(nv.getNgaySinh()) + "|"
                    + nv.getSdtNV() + "|"
                    + nv.getDiaChiNV() + "|"
                    + nv.getNamVaoLam() + "|"
                    + nv.getChucVuNV() + "|"
                    + nv.getLuong();

            bw.write(line);
            bw.newLine();
            bw.close();
            fw.close();
            System.out.println("Ghi file NhanVien.txt thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    public void hienThiDanhSach() {
        System.out.print(
                "+-----------------------------------------------------------------------------------------------------------------------------------+\n");
        System.out.print(
                "|                                                           STAFF INFOMATION                                                        |\n");
        System.out.print(
                "+-----------------------------------------------------------------------------------------------------------------------------------+\n");
        System.out.print(String.format(" %-5s| %-20s | %-2s | %-5s | %-9s | %-12s | %-15s | %-10s | %-10s | %-10s%n",
                "ID", "NAME", "AGE", "GENDER", "DATE", "PHONE", "ADDRESS", "YEAR OF JOIN", "POSITION", "SALARY"));
        System.out.print(
                "+-----------------------------------------------------------------------------------------------------------------------------------+\n");
        for (NhanVien nv : danhsach) {
            if (nv != null) {
                System.out.println(nv.toString());
            }
        }
    }

    // Phuong thuc ho tro cho HOA DON

    public NhanVien timKiemNhanVien(String maNV) {
        for (int i = 0; i < soluong; i++) {
            if (danhsach[i] != null && danhsach[i].getMaNV().equalsIgnoreCase(maNV)) {
                return danhsach[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        DSachNV dsnv = new DSachNV(100);
        dsnv.DocFile();
        dsnv.hienThiDanhSach();
        // dsnv.themNhanVien();
        // dsnv.xoaNhanVien();
        dsnv.SuaThongTinNhanVien();
        // dsnv.timKiemNhanVienTheoMa();

    }
}
