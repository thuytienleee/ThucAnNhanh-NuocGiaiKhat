// package BAOCAO;
package com.example.models.ThucAnNhanhNuocGiaiKhat.BAOCAO;

public interface ICheck {
    static boolean isValidDateFormat(String date) {
        // Kiểm tra định dạng ngày hợp lệ dd/MM/yyyy
        if (date == null || !date.matches("\\d{2}/\\d{2}/\\d{4}"))
            return false;
        String[] parts = date.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if (month < 1 || month > 12 || day < 1)
            return false;
        int[] daysInMonth = {31, (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 29 : 28,
                             31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return day <= daysInMonth[month - 1];
    }
}
