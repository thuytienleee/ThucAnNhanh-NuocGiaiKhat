package BAOCAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

interface ICheck {
    static boolean isValidDateFormat(String dateStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate.parse(dateStr, df);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    static boolean isDateInRange(Date ngayLap, String bd, String kt) {
        if (ngayLap == null || bd == null || kt == null) return false;

        DateTimeFormatter dfInput = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start = LocalDate.parse(bd, dfInput);
        LocalDate end   = LocalDate.parse(kt, dfInput);

        LocalDate ngay = new java.sql.Date(ngayLap.getTime()).toLocalDate();

        return (!ngay.isBefore(start) && !ngay.isAfter(end));
    }

}
